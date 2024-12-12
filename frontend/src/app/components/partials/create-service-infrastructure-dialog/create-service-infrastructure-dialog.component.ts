import { Component, Input } from '@angular/core';
import { HardwareService } from '../../../services/hardware.service';
import { newService } from '../../../shared/models/service';
import { Hardware } from '../../../shared/models/hardware';
import { ServiceService } from '../../../services/service.service';


@Component({
  selector: 'app-create-service-infrastructure-dialog',
  templateUrl: './create-service-infrastructure-dialog.component.html',
  styleUrl: './create-service-infrastructure-dialog.component.scss'
})
export class CreateServiceInfrastructureDialogComponent {

  constructor(private serviceService:ServiceService, private hardwareService:HardwareService) { }

  selectedEcu: Hardware | null = null;
  ngOnInit(): void{

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

  @Input() createServiceDialogData: any | null = null;

  close(): void {
    this.createServiceDialogData.showCreateServiceDialog = false;
  }

  newServiceName: any = null;
  newServiceDescription: any = null;
  
  save(){
      if (this.newServiceName && this.newServiceDescription) {
          const newService: newService = {
            name: this.newServiceName,
            type: "Service",
            description: this.newServiceDescription,
            positionX: 228,
            positionY: 229,
            connectedTo: '9'};
           
            if(this.selectedEcu)
            this.serviceService.createService(newService, this.selectedEcu.id);
  
            this.createServiceDialogData.showCreateServiceDialog = false;
      }else {
            console.log("All required feelds have to be filled!")
      }
        
  }
    
}
