import { Component, OnInit, Renderer2 } from '@angular/core';
import { Hardware } from '../../../shared/models/hardware';
import { Connection } from '../../../shared/models/connection-model';
import { NewConnection } from '../../../shared/models/connection-model';
import { HardwareService } from '../../../services/hardware.service';
import { LineCreationService } from '../../../services/data-stream.service';
import { HardwareProperty } from '../../../shared/models/hardware_property';
import { Architecture } from '../../../shared/models/architectures';
import { Service } from '../../../shared/models/service';
import { firstValueFrom} from 'rxjs';
import { DataStream } from '../../../shared/models/data_stream';
import { ArchitectureService } from '../../../services/architecture.service';
import { HardwarePropertyService } from '../../../services/hardware-property.service';
import { ServiceService } from '../../../services/service.service';
import { ServiceProperyService } from '../../../services/service-propery.service';
import { DataStreamPropertyService } from '../../../services/data-stream-property.service';



@Component({
  selector: 'app-main-screen',
  templateUrl: './main-screen.component.html',
  styleUrl: './main-screen.component.scss',

})
export class MainScreenComponent implements OnInit{

  showBusDialog: boolean = false;
  dialogData: any = this; //data for export

  showHardwareDetailsDialogContent = false;

  openHarwareDetailsDialog(hardware: Hardware): void { 
    this.updateCurrentState();
    this.hardwareService.setSelectedHardware(hardware);
    this.showHardwareDetailsDialogContent = true;
    
    this.hardwarePropertyService.loadAllHardwareProperties(hardware.id);
  }

  closeHarwareDetailsDialog(): void {
    this.hardwareService.setSelectedHardware(null)
    this.showHardwareDetailsDialogContent = false;
  }

  selectedConnection: Connection | null = null;
  openHardwareConnectionDetailsDialog(connection: Connection): void {
    this.updateCurrentState();
    this.selectedConnection = connection;
    this.showBusDialog = true;
  }

  onCloseBusDialog(): void {
    this.showBusDialog = false;
  }

 connections: Connection[] = [];
 
  ECUwidth = 150 + 4;//content + border 
  ECUheight = 75 + 4 + 25; //content + border + lable with margin

  ecus:Hardware[] = [];

  isSidebarOpen = false;
  servisecOfSelectedEcu: Hardware | null = null;
  selectedEcu: Hardware | null = null;


  dataFromHeader: any;

  constructor(private serviceService:ServiceService, private hardwarePropertyService:HardwarePropertyService, private architectureService:ArchitectureService,
   private hardwareService:HardwareService, private lineCreationService: LineCreationService, private renderer: Renderer2,
   private serviceProperyService:ServiceProperyService, private dataStreamPropertyService:DataStreamPropertyService) { 


  }
  

  setElementPosition(event: any, ecu: Hardware): void {
    const element = event.source.getRootElement();
    const boundingClientRect = element.getBoundingClientRect();
    const parentPosition = this.getElementPosition(element.parentElement);

    ecu.positionX = boundingClientRect.x - parentPosition.left;
    ecu.positionY = boundingClientRect.y - parentPosition.top; 
  }


  private getElementPosition(element: any): { left: number, top: number } {
    const rect = element.getBoundingClientRect();
    const scrollTop = window.scrollY || document.documentElement.scrollTop || document.body.scrollTop || 0;
    const scrollLeft = window.scrollX || document.documentElement.scrollLeft || document.body.scrollLeft || 0;

    return { top: rect.top + scrollTop, left: rect.left + scrollLeft };
  }

  rewriteLine(event: any, ecu: Hardware) {
    this.setElementPosition(event, ecu);

    //positioning of the connections in the case if user moves Network (special case because of the different form of the element)
    if (ecu.type == 'Network') {

      const ecuDragging: any = document.querySelector('.cdk-drag-dragging');
      var ecuRect = ecuDragging.getBoundingClientRect();//get position of dragging BUS
      var numberOfConnections = 0;
  
      //calculate number of connections with this Network(needed for calculation of horisontal gap on Network between connections)
      for (let i = 0; i < this.connections.length; i++) {
          if (this.connections[i].connectedFrom == ecu.id.toString() ||
              this.connections[i].connectedTo == ecu.id.toString()) {
              numberOfConnections++;
          }
      }
      
      //calculate horisontal gap on Network between connections
      var positionOfConnection = 0;
      if (numberOfConnections > 1) {
          positionOfConnection = Number(ecuRect.width) / (numberOfConnections - 1);
      }
  
      var numberOfCurrentConnection = 0;

        for (let i = 0; i < this.connections.length; i++) {
            //find connections connected to the Network
            if (this.connections[i].connectedFrom == ecu.id.toString() ||
                this.connections[i].connectedTo == ecu.id.toString()) {

                //set coordinates of the end the connection in case, when the connection directed TO the Network 
                if (this.connections[i].connectedTo == ecu.id.toString()) {
                  
                    this.connections[i].positionToX = (ecu.positionX + positionOfConnection * numberOfCurrentConnection).toString();
                    this.connections[i].positionToY = (ecu.positionY + 3 + 25).toString();

                 //set coordinates of the end the connection in case, when the connection directed FROM the Network    
                } else {

                    this.connections[i].positionFromX = (ecu.positionX + positionOfConnection * numberOfCurrentConnection).toString();
                    this.connections[i].positionFromY = (ecu.positionY + 3 + 25).toString();
                }
                numberOfCurrentConnection++;
            }
        }
    } else {
        for (let i = 0; i < this.connections.length; i++) {
            if (this.connections[i].connectedFrom == ecu.id.toString()) {
                this.connections[i].positionFromX = (ecu.positionX + (this.ECUwidth / 2)).toString();
                this.connections[i].positionFromY = (ecu.positionY + (this.ECUheight / 2)).toString();
            } else if (this.connections[i].connectedTo == ecu.id.toString()) {
                this.connections[i].positionToX = (ecu.positionX + (this.ECUwidth / 2)).toString();
                this.connections[i].positionToY = (ecu.positionY + (this.ECUheight / 2)).toString();
            }
        }
    }
}
 

  zoomLevel: number = 1; // Initial zoom level

  zoomIn() {
   // if(this.zoomLevel < 1.1){
    //  this.zoomLevel += 0.1; // Increase zoom level 
   //  }

  }

  zoomOut() {
   // if (this.zoomLevel > 0.7) {
    //  this.zoomLevel -= 0.1; // Decrease zoom level, ensuring it doesn't go below 0.1
   // }
    
  }

creatingLine = false;

subscribeOnSelectedHardware(){
  this.hardwareService.selectedHardware$.subscribe(
      {
        next: data => {
          this.selectedEcu = data;
        },
        error: error => {
          console.error(error);
        }
      }
  );
}


propertiesOfAllHardwares:HardwareProperty[] = [];

subscribeOnHardwares(){
  this.hardwareService.hardwares$.subscribe(
      {
        next: data => {
          this.ecus = data;
        },
        error: error => {
          console.error(error);
        }
      }
  );
}

architectures: Architecture[] | null = null;
subscribeOnArchitectures(){
  this.architectureService.architectures$.subscribe(
      {
        next: data => {
          this.architectures = data;
        },
        error: error => {
          console.error(error);
        }
      }
  );
}

selectedArchitecture: Architecture | null = null;
subscribeOnSelectedArchitecture(){
  this.architectureService.selectedArchitecture$.subscribe(
      {
        next: data => {
          this.selectedArchitecture = data;
          if(this.selectedArchitecture)
          this.lineCreationService.getAllDataStreams(this.selectedArchitecture.id).subscribe(data=>{
            this.lineCreationService.setDataStreams(data);
          });
          if (data) {
           this.hardwareService.loadAllHardwares(data.id).subscribe(data => {
            this.serviceService.getAllServices(data);
           });
          }
          
        },
        error: error => {
          console.error(error);
        }
      }
  );
}

hardwareProperties: HardwareProperty[] | null = null;
subscribeOnHardwareProperties(){
  this.hardwarePropertyService.hardwareProreries$.subscribe(
      {
        next: data => {
          this.hardwareProperties = data;
        },
        error: error => {
          console.error(error);
        }
      }
  );
}

servicesMap: Map<BigInt, Service[]> = new Map();
subscribeOnServices() {
  this.serviceService.allServicesInArchitectureMap$.subscribe({
    next: data => {
      this.servicesMap = data;
    },
    error: error => {
      console.error(error);
    }
  });
}

servicesCountMap: Map<BigInt, number> = new Map();
subscribeOnServicesCount(){
  this.serviceService.allServicesInArchitectureCountMap$.subscribe(
      {
        next: data => {
          this.servicesCountMap = data;
        },
        error: error => {
          console.error(error);
        }
      }
  );
}

dataStreams: DataStream[] = [];
subscribeOnDataStreams(){
  this.lineCreationService.dataStreams$.subscribe(
      {
        next: data => {
          this.dataStreams = data;
        },
        error: error => {
          console.error(error);
        }
      }
  );
}

  ngOnInit(): void{ 

    this.subscribeOnArchitectures();
    this.architectureService.loadAllArchitectures();

    this.subscribeOnSelectedHardware();
    this.subscribeOnHardwares();

    this.subscribeOnDataStreams();

    this.subscribeOnSelectedArchitecture();
    
    this.subscribeOnHardwareProperties();

    this.subscribeOnServicesCount();
    this.subscribeOnServices();
  }

  private getAllBus(architectureId: number){
    this.lineCreationService.getAllBus(architectureId).subscribe(data => {
      this.connections = data;
    });
  }

private graph: { [key: string]: string[] } = {};


  public initializeGraph(lines: Connection[]): void {
    this.graph = {};

    lines.forEach(line => {
      if (!this.graph[line.connectedFrom]) {
        this.graph[line.connectedFrom] = [];
      }
      if (!this.graph[line.connectedTo]) {
        this.graph[line.connectedTo] = [];
      }

      // Add both directions, as the order does not matter.
      this.graph[line.connectedFrom].push(line.connectedTo);
      this.graph[line.connectedTo].push(line.connectedFrom);

      // If the connection is bidirectional, add feedback.
      if (line.twoWayConnection) {
        this.graph[line.connectedTo].push(line.connectedFrom);
        this.graph[line.connectedFrom].push(line.connectedTo);
      }
    });
  }

  // Method for Checking the Feasibility of Transitioning from One Block to Another
  public canReach(start: string, end: string): boolean {
    if (start === end) {
      return false;
    }

    let visited: Set<string> = new Set();
    let queue: string[] = [start];

    while (queue.length > 0) {
      const current = queue.shift();
      if (current === undefined) continue;

      if (current === end) {
        return true;
      }

      visited.add(current);

      if(this.graph[current]){
        for (let neighbor of this.graph[current]) {
          if (!visited.has(neighbor)) {
            queue.push(neighbor);
          }
        }
      }
      
    }

    return false;
  }


//----------------------------------------------------------------------------HEADER--------


   updateBus(Line: Connection, id: BigInt){
    this.lineCreationService.updateBus(Line, id).subscribe();
   }

    updateEcu(Ecu: Hardware, id: BigInt): void {
    this.hardwareService.updateHardware(Ecu, id);
  }

   updateCurrentState() {

      for(let i = 0; i < this.connections.length; ++i){
        
        this.updateBus(this.connections[i], this.connections[i].id);
      }
      for(let i = 0; i < this.ecus.length; ++i){
        
        this.updateEcu(this.ecus[i], this.ecus[i].id);
      }
   }

showDropdownCreate = false; 
toggleDropdownCreate(): void {
    this.showDropdownCreate = !this.showDropdownCreate;
}


showDropdownSelectArchitecture = false; 
toggleDropdownSelectArchitecture(): void {
    this.showDropdownSelectArchitecture = !this.showDropdownSelectArchitecture;
}

selectArchitecture(option: Architecture): void {
  this.updateCurrentState();
  this.architectureService.setSelectedArchitecture(option);

  const svgContainer = document.getElementById('svg-container');

  if (!svgContainer) {
      return; 
  }

  const connections = svgContainer.querySelectorAll('line');
  connections.forEach(connection => {
      svgContainer.removeChild(connection); // Remove each line element
    });

  this.getAllBus(option.id);
  this.showDropdownSelectArchitecture = false;
  
}

showArchitectureDetails: boolean = false;
openArchitectureDetails(){
  this.showArchitectureDetails = true;
  console.log(this.selectedArchitecture)
}

startEcu: Hardware | null = null;
endEcu: Hardware | null = null;
creatingBusModus: Boolean = false;
startTargetEcuElementNewBus: any;
endTargetEcuElementNewBus: any;
busWidthStart: any;
busWidthEnd: any;

onEcuClick(ecu: Hardware, event: MouseEvent){
  if(this.creatingBusModus){
    if (!this.startEcu) {
      // First click, select start ECU
      this.startTargetEcuElementNewBus = event.target as HTMLElement;
      this.renderer.addClass(this.startTargetEcuElementNewBus, 'selected');

      this.startEcu = ecu;
      if(this.startEcu.type == "Network"){
        this.busWidthStart = event.target as HTMLElement;
      }

    } else if (!this.endEcu) {
      // Second click, select end ECU and create line
      this.endTargetEcuElementNewBus = event.target as HTMLElement;
      this.renderer.addClass(this.endTargetEcuElementNewBus, 'selected');

      this.endEcu = ecu;
      if(this.endEcu.type == "Network"){
        this.busWidthEnd = event.target as HTMLElement;
      }

      if (this.startEcu !== this.endEcu) {

        if(this.startEcu.type == "Network"){

          var numberOfConnections = 0;
          if(this.startEcu){
            for(let i = 0; i < this.connections.length; i++){
              if(this.connections[i].connectedFrom == this.startEcu.id.toString()
                || this.connections[i].connectedTo == this.startEcu.id.toString()){
                  numberOfConnections++;
              }
            }
          }

          var positionOfConnection = 0;
          if(numberOfConnections >= 1){
             positionOfConnection = Number(this.busWidthStart.offsetWidth)/(numberOfConnections);
          }else{
            positionOfConnection = 0
          }

          numberOfConnections = 0;
          if(this.startEcu){
            for(let i = 0; i < this.connections.length; i++){
              if(this.connections[i].connectedFrom == this.startEcu.id.toString()
                || this.connections[i].connectedTo == this.startEcu.id.toString()){
                  if(this.connections[i].connectedFrom == this.startEcu.id.toString()){
                    this.connections[i].positionFromX = (this.startEcu.positionX + positionOfConnection*numberOfConnections).toString();
                  }else {
                    this.connections[i].positionToX = (this.startEcu.positionX + positionOfConnection*numberOfConnections).toString();
                  }
                  numberOfConnections++;
              }
            }
          }

          const newLine: NewConnection = {
          name: 'Connection ' + (this.connections.length + 1), type: 'Connection',
          description: 'default description',
          positionFromX:  (this.startEcu.positionX + positionOfConnection*numberOfConnections).toString(),
          positionFromY: (this.startEcu.positionY + 3 + 25).toString(),
          positionToX:(this.endEcu.positionX + (this.ECUwidth / 2)).toString(),
          positionToY: (this.endEcu.positionY + (this.ECUheight / 2)).toString(),
          connectedFrom: this.startEcu.id.toString(),
          connectedTo: this.endEcu.id.toString(),
          twoWayConnection: false};

          if(this.selectedArchitecture)
          this.lineCreationService.createBus(this.selectedArchitecture.id, newLine).subscribe(data =>{
            this.connections[this.connections.length] = data

          });
        }else if(this.endEcu.type == "Network"){

          
          var numberOfConnections = 0;
          if(this.endEcu){
            for(let i = 0; i < this.connections.length; i++){
              if(this.connections[i].connectedFrom == this.endEcu.id.toString()
                || this.connections[i].connectedTo == this.endEcu.id.toString()){
                  numberOfConnections++;
              }
            }
          }

          var positionOfConnection = 0;
          if(numberOfConnections >= 1){
            var positionOfConnection = Number(this.busWidthEnd.offsetWidth)/(numberOfConnections);
          }else{
            positionOfConnection = 0
          }

          numberOfConnections = 0;
          if(this.endEcu){
            for(let i = 0; i < this.connections.length; i++){
              if(this.connections[i].connectedFrom == this.endEcu.id.toString()
                || this.connections[i].connectedTo == this.endEcu.id.toString()){
                  if(this.connections[i].connectedTo == this.endEcu.id.toString()){
                    this.connections[i].positionToX = (this.endEcu.positionX + positionOfConnection*numberOfConnections).toString();
                  }else {
                    this.connections[i].positionFromX = (this.endEcu.positionX + positionOfConnection*numberOfConnections).toString();
                  }
                  numberOfConnections++;
              }
            }
          }

          const newLine: NewConnection = {
          name: 'Connection ' + (this.connections.length + 1), type: 'Connection',
          description: 'default description', 
          positionFromX: (this.startEcu.positionX + (this.ECUwidth / 2)).toString(),
          positionFromY: (this.startEcu.positionY + (this.ECUheight / 2)).toString(), 
          positionToX: (this.endEcu.positionX + positionOfConnection*numberOfConnections).toString(),
          positionToY: (this.endEcu.positionY + 3 + 25).toString(), 
          connectedFrom: this.startEcu.id.toString(),
          connectedTo: this.endEcu.id.toString(), 
          twoWayConnection: false};

          if(this.selectedArchitecture)
          this.lineCreationService.createBus(this.selectedArchitecture.id, newLine).subscribe(data =>{
            this.connections[this.connections.length] = data
          });
         
        }else{
          alert("ECU has to be connected with the Network")
        }
      } else {
        alert('Start and end ECUs cannot be the same');
      }
      // Reset start and end ECUs
      
      this.startEcu = null;
      this.endEcu = null;
      this.creatingBusModus = false;
      setTimeout(()=>{
        this.renderer.removeClass(this.startTargetEcuElementNewBus, 'selected');
        this.renderer.removeClass(this.endTargetEcuElementNewBus, 'selected');
      }, 1000)

    }
  } else {
    // Default ECU click handling logic
  }
  }
  
  optionsDropdownCreate = [
    { id: 1, label: 'new Hardware' },
    { id: 3, label: 'new Connection' },
    { id: 4, label: 'new Architecture' },
  ];

showCreateHardwareDialog: boolean = false;
showCreateArchitectureDialog: boolean = false;
selectOptioWhatToCreate(option: any): void {
    this.updateCurrentState();
   if (option.label === 'new Hardware') {
    this.showCreateHardwareDialog = true;
  } else if (option.label === 'new Connection') {
    this.creatingBusModus = true;
  } else if (option.label === 'new Architecture'){
    this.showCreateArchitectureDialog = true;  
  } 
  this.showDropdownCreate = false;  
}

creatingNewLine = false;

//---------------------------------------------------JSON-Token------------------

async generateToken() {
  // Map each hardware item to a Promise that loads its properties
  const hardwareWithProperties = await Promise.all(
    this.ecus.map(async (hardwareItem) => {
      const { positionX, positionY, connectedTo, ...hardwareWithoutPosition } = hardwareItem; // Exclude unwanted properties

      let dataStreamsForHardware: any[] = [];
      const properties = await firstValueFrom(this.hardwarePropertyService.loadAllHardwareProperties(hardwareItem.id));
      const services = await Promise.all(
        (this.servicesMap.get(hardwareItem.id) || []).map(async (service) => {
          const { positionX, positionY, connectedTo, ...serviceWithoutPosition } = service; // Exclude unwanted properties

          // Filter dataStreams that belong to the service
          const serviceDataStreams = this.dataStreams.filter(
            (dataStream) =>
              dataStream.connectedFrom === service.id.toString() || dataStream.connectedTo === service.id.toString()
          );

          let dataStreamsForToken = [];
          for (let i = 0; i < serviceDataStreams.length; i++) {
            const { positionFromX, positionFromY, positionToX, positionToY, ...dataStreamWithoutPosition } =
              serviceDataStreams[i]; // Exclude unwanted properties

            dataStreamsForToken[i] = {
              ...dataStreamWithoutPosition,
              properties: await firstValueFrom(this.dataStreamPropertyService.loadAllDataStreamProperties(serviceDataStreams[i].id)),
            };
          }

          // Load service properties asynchronously
          const serviceProperties = await firstValueFrom(this.serviceProperyService.loadAllServiceProperties(service.id));
          dataStreamsForHardware = dataStreamsForHardware.concat(dataStreamsForToken);
          return {
            ...serviceWithoutPosition,
            properties: serviceProperties || [], // Ensure properties are correctly attached
            //dataStreams: dataStreamsForToken, // Assign filtered dataStreams to the service
          };
        })
      );

      return {
        ...hardwareWithoutPosition,
        properties: properties || [], // Use the loaded properties or an empty array if none are found
        services, // Attach services with their corresponding dataStreams
        dataStreams: dataStreamsForHardware
      };
    })
  );

  // Filter and transform connections to include only the required properties
  const filteredConnections = this.connections.map(connection => {
    const { id, name, type, connectedFrom, connectedTo } = connection; // Destructure only the required properties
    return { id, name, type, connectedFrom, connectedTo }; // Reconstruct the object with only the desired properties
  });

  // Construct the token object with each hardware item containing its associated services and properties
  const token = {
    architecture: {
      name: this.selectedArchitecture?.name,
      description: this.selectedArchitecture?.description,
      hardware: hardwareWithProperties,
      connections: filteredConnections // Use the filtered connections
    },
  };

  // Convert to JSON format
  const jsonToken = JSON.stringify(token);
  console.log("Generated Token:", jsonToken);

  // Automatically download the JSON token
  const blob = new Blob([jsonToken], { type: "application/json" }); 
  const url = URL.createObjectURL(blob); 

  const a = document.createElement("a"); 
  a.href = url; 
  a.download = "token.json"; 
  a.click(); 

  URL.revokeObjectURL(url); 

  return jsonToken;
}




}











