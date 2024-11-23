import { Component, Input } from '@angular/core';
import { HardwareService } from '../../../services/hardware.service';
import { LineCreationService } from '../../../services/data-stream.service';
import { ServiceService } from '../../../services/service.service';
import { Service } from '../../../shared/models/service';
import { DataStream } from '../../../shared/models/data_stream';
import { ArchitectureService } from '../../../services/architecture.service';
import { ServiceProperyService } from '../../../services/service-propery.service';
import { ServiceProperty, NewServiceProperty } from '../../../shared/models/service_property';

@Component({
  selector: 'app-service-dialog',
  templateUrl: './service-dialog.component.html',
  styleUrl: './service-dialog.component.scss'
})
export class ServiceDialogComponent {

  nameEditMod:boolean = false;
  editName(){
    this.nameEditMod = true;
  }

  canselEditingName(){
    this.nameEditMod = false;
  }

  saveName(){
    this.serviceService.updadeService(this.selectedService, this.selectedService.id/*, this.selectedEcu.id*/);
    this.nameEditMod = false;
  }

  descriptionEditMod:boolean = false;

  editDescription(){
    this.descriptionEditMod = true; 
    setTimeout(()=>{
      this.autoResizeTextarea();
    },50)
  }

  canselEditingDescription(){
    this.descriptionEditMod = false;  
  }

  saveDescription(){
    this.serviceService.updadeService(this.selectedService, this.selectedService.id);
    this.descriptionEditMod = false; 
  }

  servicePropertyId: any = null; 
  editProperty(serviceProperty: ServiceProperty){
    this.servicePropertyId = serviceProperty.id;
  }

  canselEditingProperty(){
    this.servicePropertyId = null;
  }

  deleteProperty(serviceProperty: ServiceProperty){
    this.serviceProperyService.deleteServiceProperty(serviceProperty.id, this.selectedService.id)
  }

  saveProperty(serviceProperty: ServiceProperty){
    this.serviceProperyService.updateServiceProperty(serviceProperty, serviceProperty.id, this.selectedService.id)
    this.servicePropertyId = null;
  }

  constructor(private serviceProperyService:ServiceProperyService, private architectureService:ArchitectureService, private serviceService:ServiceService, private hardwareService:HardwareService ,
     private lineCreationService: LineCreationService) { 

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

  servicesMap: Map<BigInt, Service[]> = new Map();
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

serviceProperties: ServiceProperty[] = [];
subscribeOnServiceProperties(){
  this.serviceProperyService.serviceProreries$.subscribe(
      {
        next: data => {
          this.serviceProperties = data;
        },
        error: error => {
          console.error(error);
        }
      }
  );
}

ngOnInit(): void{
  this.subscribeOnSelectedArchitecture();

  this.subscribeOnSelectedService();

  this.subscribeOnServices();

  this.subscribeOnDataStreams();

  this.subscribeOnServiceProperties();
  this.serviceProperyService.loadAllServiceProperties(this.selectedService.id);
}

  @Input() serviceDetilsData: any | null = null;


    close(){ 
      this.serviceService.setSelectedService(null);
    }

  delete(){ 
    var dataStreamsIdDeleteArray: any[] = [];

    for(let i = 0; i < this.dataStreams.length; i++){ 
      
      if(this.dataStreams[i].connectedFrom === this.selectedService.id.toString()){
        dataStreamsIdDeleteArray.push(this.dataStreams[i].id);
        this.deleteDataStream(this.dataStreams[i].id);
      }else if(this.dataStreams[i].connectedTo === this.selectedService.id.toString()){        
        dataStreamsIdDeleteArray.push(this.dataStreams[i].id);
        this.deleteDataStream(this.dataStreams[i].id);        
      }
    }
    this.deleteService(this.serviceDetilsData.selectedService.id);

    this.serviceService.setSelectedService(null)
  }

  private deleteService(id: BigInt){
    this.serviceService.deleteService(id);
  }

  private deleteDataStream(id: BigInt){
    this.lineCreationService.deleteDataStream(id).subscribe({ 
      next: (data) => {      
        this.serviceDetilsData.getDataStreams(this.selectedArchitecture.id);  
      },
      error: (error) => {
        console.error('Error deleting Data Stream', error);
      }
    });
  }

  propertyKey: string = '';
  propertyValue: string = '';

  addServiceProperty(): void {
    if (this.selectedService && this.propertyKey && this.propertyValue) {

      const newServiceProperty: NewServiceProperty = {name: this.propertyKey, value: this.propertyValue};

      this.serviceProperyService.createServiceProperty(newServiceProperty, this.selectedService.id);

      this.propertyKey = '';
      this.propertyValue = '';
    }
  }

  autoResizeTextarea() {
    const textarea = document.getElementById('textarea-description');
    if(textarea){
      textarea.style.height = textarea.scrollHeight + 'px';
    }
  }

}
