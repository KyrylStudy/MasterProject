import { Component, ElementRef, Input, QueryList,Renderer2, ViewChild, ViewChildren } from '@angular/core';
import { Service } from '../../../shared/models/service';
import { NewDataStream } from '../../../shared/models/data_stream';
import { HardwareService } from '../../../services/hardware.service';
import { DataStream } from '../../../shared/models/data_stream';
import { LineCreationService } from '../../../services/data-stream.service';
import { Hardware } from '../../../shared/models/hardware';
import { ServiceService } from '../../../services/service.service';
import { ArchitectureService } from '../../../services/architecture.service'; 

@Component({
  selector: 'app-service',
  templateUrl: './service.component.html',
  styleUrl: './service.component.scss'
})
export class ServiceComponent {

  @Input() serviceData: any | null = null; 
  dataFromServicesDashbord: any = this;
  showDataStreamDialog: boolean = false;

  dataForServiceDialog = this;

  setSenderRecieverStylesOnDataStreams(){
    this.dataStreams.forEach((item, index)=>{
      const element = this.el.nativeElement.querySelector(`[dataStreamId="${item.id}"]`);
      if(element)
      element.style.cssText += 'marker-start: url(#arrowhead)';
    })
  }


  goBack(){ 
    this.serviceData.showService = false; 
    this.serviceData.dialogData.showHardwareDetailsDialogContent = true; 
  }


  openServiceDetailsDialog(service: Service){ 
    this.updateCurrentState();
    this.serviceService.setSelectedService(service)
  }


  constructor(private architectureService:ArchitectureService, private serviceService:ServiceService, private renderer: Renderer2, private el: ElementRef,
     private hardwareService: HardwareService, private lineCreationService: LineCreationService) { 
  }
 

  rewriteLine(service: Service) {
    var ParentEcuId:any;
    this.servicesMap.forEach((value:any, key:any)=>{
      for(let i = 0; i < value.length; i++){
        if(value[i].id === service.id){
          ParentEcuId = key;
        }
        
      }
    })

    const parentEcu:any = document.getElementById(`drag-boundary-${ParentEcuId}`);
    const parentPosition = parentEcu.getBoundingClientRect();

    var positionX =  parentPosition.left;
    var positionY =  parentPosition.top;

    const workArea:any = document.getElementById("horisontal-wrapper");
    const workAreaPosition = workArea.getBoundingClientRect();

    var workAreaPositionX =  workAreaPosition.left;
    var workAreaPositionY =  workAreaPosition.top;

if(this.zoomLevel === 1){
  for (let i = 0; i < this.dataStreams.length; i++) {
    if (this.dataStreams[i].connectedFrom === service.id.toString()) {
        this.dataStreams[i].positionFromX = (service.positionX + (this.ServiceWidth / 2) + positionX - workAreaPositionX - 2).toString();
        this.dataStreams[i].positionFromY = (service.positionY + (this.ServiceHeight / 2) + positionY - workAreaPositionY - 5).toString();
    } else if (this.dataStreams[i].connectedTo === service.id.toString()) {
        this.dataStreams[i].positionToX = (service.positionX + (this.ServiceWidth / 2) + positionX - workAreaPositionX - 2).toString();
        this.dataStreams[i].positionToY = (service.positionY + (this.ServiceHeight / 2) + positionY - workAreaPositionY - 5).toString();
    }
  }
}else if(this.zoomLevel === 2){
  for (let i = 0; i < this.dataStreams.length; i++) {
    if (this.dataStreams[i].connectedFrom === service.id.toString()) {
        this.dataStreams[i].positionFromX = (service.positionX + (this.ServiceWidth / 2) + positionX - workAreaPositionX + (38)).toString();
        this.dataStreams[i].positionFromY = (service.positionY + (this.ServiceHeight / 2) + positionY - workAreaPositionY + (28)).toString();
    } else if (this.dataStreams[i].connectedTo === service.id.toString()) {
        this.dataStreams[i].positionToX = (service.positionX + (this.ServiceWidth / 2) + positionX - workAreaPositionX + (38)).toString();
        this.dataStreams[i].positionToY = (service.positionY + (this.ServiceHeight / 2) + positionY - workAreaPositionY + (28)).toString();
    }
  }
}   
}



handleDragMoved(event: any, item: any): void {
  this.setElementPosition(event, item);
  this.rewriteLine(item);
}

setElementPosition(event: any, ecu: Hardware): void {
  const element = event.source.getRootElement();
  
  
  const boundingClientRect = element.getBoundingClientRect();
  const parentPosition = this.getElementPosition(element.parentElement);

  ecu.positionX = (boundingClientRect.x - parentPosition.left);
  ecu.positionY = (boundingClientRect.y - parentPosition.top) ;
}


private getElementPosition(element: any): { left: number, top: number } {
  const rect = element.getBoundingClientRect();
  const scrollTop = window.scrollY || document.documentElement.scrollTop || document.body.scrollTop || 0;
  const scrollLeft = window.scrollX || document.documentElement.scrollLeft || document.body.scrollLeft || 0;

  return { top: rect.top + scrollTop, left: rect.left + scrollLeft };
}


//---------------zoom-----------
zoomLevel: number = 1; // Initial zoom level

zoomIn() {
 // if(this.zoomLevel === 1)
 // this.zoomLevel += 1; // Increase zoom level 
 // this.getDataStreams(this.selectedArchitecture.id);

}

zoomOut() {
 // if(this.zoomLevel !== 1){
 //   this.zoomLevel -= 1; // Decrease zoom level, ensuring it doesn't go below 0.1
 //   this.getDataStreams(this.selectedArchitecture.id);
 // }
}

selectedService: any | null = null;
subscribeOnSelectedService(){
  this.serviceService.selectedService$.subscribe(
      {
        next: data => {
          this.selectedService = data;
        },
        error: error => {
          console.error(error);
        }
      }
  );
}

selectedArchitecture: any | null = null;
subscribeOnSelectedArchitecture(){
  this.architectureService.selectedArchitecture$.subscribe(
      {
        next: data => {
          this.selectedArchitecture = data;
        },
        error: error => {
          console.error(error);
        }
      }
  );
}

selectedEcu: any | null = null;
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

hardwares: any[] = [];
subscribeOnHardwares(){
  this.hardwareService.hardwares$.subscribe(
      {
        next: data => {
          this.hardwares = data;
        },
        error: error => {
          console.error(error);
        }
      }
  );
}

servicesMap: any = new Map(); 
subscribeOnServices(){
  this.serviceService.allServicesInArchitectureMap$.subscribe(
      {
        next: data => {
          this.servicesMap = data;
        },
        error: error => {
          console.error(error);
        }
      }
  );
}

subscribeOnServicesOfSelectedEcu(){
  this.serviceService.services$.subscribe(
      {
        next: data => {
          this.servicesOfSelectedEcu = data;
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

ngOnInit(): void {
  this.subscribeOnDataStreams();

  this.subscribeOnSelectedService();

  this.subscribeOnSelectedArchitecture(); 

  this.subscribeOnServices();

  this.subscribeOnServicesOfSelectedEcu();
 
  this.subscribeOnSelectedHardware();
  this.subscribeOnHardwares();

  this.scrollableEcu.nativeElement.addEventListener('scroll', this.onElementScroll.bind(this));
  this.getDataStreams(this.selectedArchitecture.id)

  this.servicesMap.forEach((items: any[], ecuId: any) => {
    if (ecuId !== this.selectedEcu.id) {
      items.forEach((item) => {
        if (item.positionY > 300) {
          item.positionY = 200;
        }
      });
    }
  });

}

@ViewChildren('line') lines!: QueryList<ElementRef>;


ngAfterViewInit() {
  this.setSenderRecieverStylesOnDataStreams();
}


private getDataStreams(architectureId: number){
  
  this.lineCreationService.getAllDataStreams(architectureId).subscribe({
      next: (data) => { 

        if(this.selectedOption){
          this.getDataStreamsOfSelectdService(data); 
        }else{
          this.dataStreams = data;
          this.rewriteAllDataStreams();
        }
        this.lineCreationService.setDataStreams(this.dataStreams);
      },
      error: (error) => {
        // Handle the error here if needed
        console.error('Error geting Data Stream', error);
      }
    }
    
  );
}


//------------------------------create new line(data stream)
ServiceWidth = 40 + 4;//content + border 
ServiceHeight = 40 + 4 + 12; //content + border + lable with margin


  startTargetEcuElementNewBus: any;
  endTargetEcuElementNewBus: any;
  startEcu: Service | null = null;
  endEcu: Service | null = null;
  startLinePsition: any;

  dataStreamName: any = null;
  dataStreamDescription: any = null;

  onEcuClick(ecu: Service, event: MouseEvent){ 

    
    if(this.creatingDatastreamModus){
      if (!this.startEcu) {
        // First click, select start ECU
        this.startTargetEcuElementNewBus = event.target as HTMLElement;
        this.renderer.addClass(this.startTargetEcuElementNewBus, 'selected');
  
        this.startEcu = ecu;

        const ecuDragging: any = event.target as HTMLElement;

        var ecuRect = ecuDragging.getBoundingClientRect();
        this.startLinePsition = ecuRect;
      } else if (!this.endEcu) {
        // Second click, select end ECU and create line
        this.endTargetEcuElementNewBus = event.target as HTMLElement;
        this.renderer.addClass(this.endTargetEcuElementNewBus, 'selected');

        this.endEcu = ecu;
        if (this.startEcu !== this.endEcu) {
          // Ensure start and end ECUs are different
          const ecuDragging: any = event.target as HTMLElement;

          var ecuRect = ecuDragging.getBoundingClientRect();

          const newDataStream: NewDataStream = {
          name: this.dataStreamName,
          type: 'Data Stream',
          description: this.dataStreamDescription,
          positionFromX: (this.startLinePsition.left).toString(),
          positionFromY: (this.startLinePsition.top - ((this.ServiceHeight/2) / this.zoomLevel)).toString(),
          positionToX: (ecuRect.left).toString(),
          positionToY: (ecuRect.top - ((this.ServiceHeight/2) / this.zoomLevel)).toString(),
          connectedFrom: this.startEcu.id.toString(),
          connectedTo: this.endEcu.id.toString(), twoWayConnection: false};


          this.lineCreationService.createDataStream(this.selectedArchitecture.id, newDataStream).subscribe(data =>{

            this.getDataStreams(this.selectedArchitecture.id)
   
          });

        } else {
          alert('Start and end Service cannot be the same');
        }
        // Reset start and end ECUs
        
        this.startEcu = null;
        this.endEcu = null;
        this.creatingDatastreamModus = false;
        setTimeout(()=>{
          this.renderer.removeClass(this.startTargetEcuElementNewBus, 'selected');
          this.renderer.removeClass(this.endTargetEcuElementNewBus, 'selected');
        }, 1000)
  
      }
    } else {
      // Default ECU click handling logic
    }
    }

//-----------------------------onScroll

  @ViewChild('scrollableEcu', { static: true }) scrollableEcu!: ElementRef;




  previousScrollY: any = 0;
  onElementScroll(): void {
    
    const element = this.scrollableEcu.nativeElement;
    const scrollTop = element.scrollTop;
    

      for (let dataStream of this.dataStreams) {
        let adjustFromY = true;
        let adjustToY = true;
      
        for (let service of this.servicesOfSelectedEcu) {
          if (dataStream.connectedFrom == service.id.toString()) {
            adjustFromY = false;
          }
          if (dataStream.connectedTo == service.id.toString()) {
            adjustToY = false;
          }
        }
      
        //scroll for normal zoom
        if(this.zoomLevel === 1){
          if (adjustFromY) {
            dataStream.positionFromY = (Number(dataStream.positionFromY) - (scrollTop - this.previousScrollY)).toString();
          }
          
          if (adjustToY) {
            dataStream.positionToY = (Number(dataStream.positionToY) - (scrollTop - this.previousScrollY)).toString();
          }
          //scroll for zoom x2
        }else if(this.zoomLevel === 2){
          if (adjustFromY) {
            dataStream.positionFromY = (Number(dataStream.positionFromY) - 0.5*(scrollTop - this.previousScrollY)).toString();
          }
          
          if (adjustToY) {
            dataStream.positionToY = (Number(dataStream.positionToY) - 0.5*(scrollTop - this.previousScrollY)).toString();
          }
        }

      }

   
    this.previousScrollY = scrollTop

    this.lineCreationService.setDataStreams(this.dataStreams);

  
  }
//---------------------------------------------01.06

updateCurrentState() {

  for(let i = 0; i < this.hardwares.length; i++){
   // debugger
    var servicesOfEcu = this.serviceService.servicesMap.get(this.hardwares[i].id);
    if(servicesOfEcu)
    for(let j = 0; j < servicesOfEcu.length; j++){
        this.serviceService.updadeService(servicesOfEcu[j], servicesOfEcu[j].id, )
    }
  }

  for(let i = 0; i < this.dataStreams.length; i++){
    this.updateDataStream(this.dataStreams[i], this.dataStreams[i].id)
  }
}

private updateDataStream(DataStream: DataStream, id: BigInt){

  this.lineCreationService.updateDataStream(DataStream, id).subscribe();
}

//----------------------------17.06

showDropdown = false; 
servicesOfSelectedEcu: Service[] = [];
toggleDropdownServiceForShowDataStreams(): void { 
  this.updateCurrentState();
  this.servicesOfSelectedEcu = this.servicesMap.get(this.selectedEcu.id);
  this.showDropdown = !this.showDropdown;
}


options:any = [];
selectedOption: any = null;
selectServiceForShowDataStreams(option: any): void {

  if(option){
    this.selectedOption = option;
    this.dataStreams = [];
    this.getDataStreams(this.selectedArchitecture.id);
  }else{
    this.selectedOption = null;
  }
 this.showDropdown = false; 

}

getDataStreamsOfSelectdService(allDataStreams: any){
    var selectedServiceDataStreams:any = [];
    var connectedServices: any = [];

    for(let i = 0; i < allDataStreams.length; i++){
      if(allDataStreams[i].connectedFrom == this.selectedOption.id){
        selectedServiceDataStreams.push(allDataStreams[i]);
        const service = this.el.nativeElement.querySelector(`[serviceId="${Number(allDataStreams[i].connectedTo)}"]`);
        connectedServices.push(service)
      }else if(allDataStreams[i].connectedTo == this.selectedOption.id){
        selectedServiceDataStreams.push(allDataStreams[i]);
        const service = this.el.nativeElement.querySelector(`[serviceId="${Number(allDataStreams[i].connectedFrom)}"]`);
        connectedServices.push(service)
      }
    }
    this.dataStreams = selectedServiceDataStreams;
    for(let i = 0; i < connectedServices.length; i++){
      this.rewriteLine(connectedServices[i])
    }
    this.rewriteLine(this.selectedOption)
  }


  rewriteAllDataStreams(){ 
    let that = this;
    that.servicesMap.forEach((serviscesOfEcu: any, ecu: any)=>{
      for(let i = 0; i < serviscesOfEcu.length; i++){
        that.rewriteLine(serviscesOfEcu[i])
      }
    });

 
  }

  selectAllServices(){ 
    this.updateCurrentState();
    this.selectedService = null;
    this.selectedOption = null;
    this.getDataStreams(this.selectedArchitecture.id);
  }

  selectedDataStream: any; 

  openDataStreamDetails(dataStream: DataStream){
    this.updateCurrentState();
    this.selectedDataStream = dataStream;
    this.showDataStreamDialog = true;
  }

    showCreateServiceDialog: boolean = false;
    openCreateServiceDialog(){
      this.updateCurrentState();
      this.showCreateServiceDialog = true;
    }

    creatingDatastreamModus: Boolean = false;
    showCreateDataStreamDialog: Boolean = false;
    openCreateDatastreamDialog(){
      this.updateCurrentState();
      this.creatingDatastreamModus = true;
      this.showCreateDataStreamDialog = true;
    }
  
}



