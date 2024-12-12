import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-create-data-stream-dialog',
  templateUrl: './create-data-stream-dialog.component.html',
  styleUrl: './create-data-stream-dialog.component.scss'
})
export class CreateDataStreamDialogComponent {

  @Input() createDataStreamDialogData: any | null = null;

  close(): void {
    this.createDataStreamDialogData.showCreateDataStreamDialog = false;
  }

  newDataStreamName: any = null;
  newDataStreamDescription: any = null;

  save(){
    if (this.newDataStreamName && this.newDataStreamDescription) {
      this.createDataStreamDialogData.dataStreamName = this.newDataStreamName;
      this.createDataStreamDialogData.dataStreamDescription = this.newDataStreamDescription;
      this.createDataStreamDialogData.showCreateDataStreamDialog = false;
    }else {
      console.log("All required feelds have to be filled!")
    }
  }
}
