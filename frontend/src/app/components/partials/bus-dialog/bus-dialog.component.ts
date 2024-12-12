import { Component, EventEmitter, Output, Input } from '@angular/core';
import { LineCreationService } from '../../../services/data-stream.service';

@Component({
  selector: 'bus-dialog',
  templateUrl: './bus-dialog.component.html',
  styleUrl: './bus-dialog.component.scss'
})
export class BusDialogComponent {
  @Input() dialogData: any | null = null;
  @Output() closeDialog = new EventEmitter<boolean>();

  close(): void {
    this.closeDialog.emit(true);
  }

  delete(): void { 
    var connectedFromEcuId = this.dialogData.selectedConnection.connectedFrom;
    var connectedToEcuId = this.dialogData.selectedConnection.connectedTo;

  var connectedECU: any;
   for(let i = 0; i < this.dialogData.ecus.length; i++){
    if((this.dialogData.ecus[i].id == connectedFromEcuId && this.dialogData.ecus[i].type == "ECU") ||
     (this.dialogData.ecus[i].id == connectedToEcuId && this.dialogData.ecus[i].type == "ECU")){
      connectedECU = this.dialogData.ecus[i];
     }
   }

   for (let j = 0; j < this.dialogData.dataStreams.length; j++) {
    let connectedFromKey: number | undefined;
    let connectedToKey: number | undefined;

    for (const [key, services] of this.dialogData.servicesMap.entries()) {
      if (services.some((service: { id: { toString: () => string; }; }) => service.id.toString() === this.dialogData.dataStreams[j].connectedFrom)) {
        connectedFromKey = key;
      }
      if (services.some((service: { id: { toString: () => string; }; }) => service.id.toString() === this.dialogData.dataStreams[j].connectedTo)) {
        connectedToKey = key;
      }

      if ((connectedFromKey === connectedECU.id && connectedToKey !== connectedECU.id) || (connectedFromKey !== connectedECU.id && connectedToKey === connectedECU.id)) {
       break;
     }
    }

    if ((connectedFromKey === connectedECU.id && connectedToKey !== connectedECU.id) || (connectedFromKey !== connectedECU.id && connectedToKey === connectedECU.id)) {
     alert("Connection can not be deleted if Data Streams go through it");
      return;
    }
  }

    for(let i = 0; i < this.dialogData.ecus.length; i++){
      if(this.dialogData.ecus[i].id == connectedFromEcuId){
        this.dialogData.ecus[i].connectedTo = '';
      }
      if(this.dialogData.ecus[i].id == connectedToEcuId){
        this.dialogData.ecus[i].connectedTo = '';
      }
    }

    this.dialogData.connections = this.dialogData.connections.filter((item: { id: any; }) => item.id !== this.dialogData.selectedConnection.id);
    this.deleteBus(this.dialogData.selectedConnection.id);
    this.closeDialog.emit(true);
  }

  constructor(private lineCreationService: LineCreationService) {}

  private deleteBus(id: BigInt){
    this.lineCreationService.deleteBus(id).subscribe();

    let indexToRemove = this.dialogData.connections.findIndex((obj: { id: BigInt; }) => obj.id === id);

    if (indexToRemove > -1) {
      this.dialogData.connections.splice(indexToRemove, 1);
    }
  }

}

