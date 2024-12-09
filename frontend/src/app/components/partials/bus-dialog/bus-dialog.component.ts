import { Component, EventEmitter, Output, Input } from '@angular/core';
import { LineCreationService } from '../../../services/data-stream.service';
//import { Connection } from '../../../shared/models/connection-model';
//import { EcuService } from '../../../services/ecu.service';
import { Hardware } from '../../../shared/models/hardware';

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
    //console.log(this.dialogData)
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

   // Check for existiond Data Streams between connected ECU and some other ECU
   for (let j = 0; j < this.dialogData.dataStreams.length; j++) {
    let connectedFromKey: number | undefined;
    let connectedToKey: number | undefined;

    // Search for keys for connectedFrom and connectedTo
    for (const [key, services] of this.dialogData.servicesMap.entries()) {
      if (services.some((service: { id: { toString: () => string; }; }) => service.id.toString() === this.dialogData.dataStreams[j].connectedFrom)) {
        connectedFromKey = key;
      }
      if (services.some((service: { id: { toString: () => string; }; }) => service.id.toString() === this.dialogData.dataStreams[j].connectedTo)) {
        connectedToKey = key;
      }

      // If both keys are found, break the loop
      if ((connectedFromKey === connectedECU.id && connectedToKey !== connectedECU.id) || (connectedFromKey !== connectedECU.id && connectedToKey === connectedECU.id)) {
       break;
     }
    }

    // Check if connectedFrom and connectedTo belong to different keys
    if ((connectedFromKey === connectedECU.id && connectedToKey !== connectedECU.id) || (connectedFromKey !== connectedECU.id && connectedToKey === connectedECU.id)) {
     alert("Connection can not be deleted if Data Streams go through it");
      return;
    }
  }



    for(let i = 0; i < this.dialogData.ecus.length; i++){
      if(this.dialogData.ecus[i].id == connectedFromEcuId){
        //connectedFromEcu = this.dialogData.ecus[i];
        this.dialogData.ecus[i].connectedTo = ''
      //  console.log(this.dialogData.ecus[i])
      }
      if(this.dialogData.ecus[i].id == connectedToEcuId){
        //connectedToEcu = this.dialogData.ecus[i];
        this.dialogData.ecus[i].connectedTo = ''
       // console.log(this.dialogData.ecus[i])
      }
    }

    this.dialogData.connections = this.dialogData.connections.filter((item: { id: any; }) => item.id !== this.dialogData.selectedConnection.id);
    //console.log(this.dialogData.connections)
    this.deleteBus(this.dialogData.selectedConnection.id);
    this.closeDialog.emit(true);
  }

  constructor(private lineCreationService: LineCreationService/*, private ecuService:EcuService*/) {}

  private deleteBus(id: BigInt){
    this.lineCreationService.deleteBus(id).subscribe();

    let indexToRemove = this.dialogData.connections.findIndex((obj: { id: BigInt; }) => obj.id === id);

    // Удаляем объект, если он найден
    if (indexToRemove > -1) {
      this.dialogData.connections.splice(indexToRemove, 1);
    }
  }

}

