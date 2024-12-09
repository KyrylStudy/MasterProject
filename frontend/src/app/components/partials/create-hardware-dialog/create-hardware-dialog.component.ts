import { Component, Input } from '@angular/core';
import { HardwareService } from '../../../services/hardware.service';
import { NewHardware } from '../../../shared/models/hardware';
import { ServiceService } from '../../../services/service.service';
//import { ArchitectureService } from '../../../services/architecture.service';
//import { Architecture } from '../../../shared/models/architectures';


@Component({
  selector: 'create-hardware-dialog',
  templateUrl: './create-hardware-dialog.component.html',
  styleUrl: './create-hardware-dialog.component.scss'
})
export class CreateHardwareDialogComponent {

  constructor( private hardwareService:HardwareService/*, private serviceService:ServiceService, private architectureService:ArchitectureService*/) { 
  }

/*selectedArchitecture: Architecture | null = null;
subscribeOnSelectedArchitecture(){
  this.architectureService.selectedArchitecture$.subscribe(
      {
        next: data => {
          this.selectedArchitecture = data;
          if (data) {
           this.hardwareService.loadAllHardwares(data.id).subscribe(data => {
            this.serviceService.getAllServices(data);
            //console.log(data)
           });
          }
        },
        error: error => {
          console.error(error);
        }
      }
  );
}*/

  ngOnInit(): void {
    //this.subscribeOnSelectedArchitecture();
    //this.architectureService.loadAllArchitectures();
  }


  @Input() createHardwareData: any | null = null;

  close(): void {
    this.createHardwareData.showCreateHardwareDialog = false;
  }

  selectedOption: any = null;
  showDropdown: boolean = false;
  toggleDropdownType(){
    this.showDropdown = !this.showDropdown;
  }

  options = [
    { id: 1, label: 'ECU' },
    { id: 2, label: 'Network' }
  ];


  newHardwareName: any = null;
  newHardwareDescription: any = null;

  selectOption(option: any){
    if (option.label === 'ECU') {

      this.showDropdown = !this.showDropdown;
      this.selectedOption = option;
    }else if(option.label === 'Network'){

      this.showDropdown = !this.showDropdown;
      this.selectedOption = option;
    }else{
      return;
    }
  }

  save(){

      if (this.selectedOption && this.newHardwareName && this.newHardwareDescription) {
          const newEcu: NewHardware = {
            label: this.newHardwareName,
            type: this.selectedOption.label, //=== 'ECU' ? this.selectedOption.label : 'BUS',
            description: this.newHardwareDescription,
            positionX: 228,
            positionY: 229,
            connectedTo: this.createHardwareData.ecus.length};
            this.hardwareService.createHardware(newEcu, this.createHardwareData.selectedArchitecture.id);
            
            this.createHardwareData.showCreateHardwareDialog = false;



            //  this.subscribeOnSelectedArchitecture();
            //  this.architectureService.loadAllArchitectures();

      }else {
        console.log("All required feelds have to be filled!")
      }
  }

}
