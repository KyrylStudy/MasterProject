import { Injectable } from '@angular/core';
import { BehaviorSubject, tap } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { DataStreamProperty, NewDataStreamProperty } from '../shared/models/data_stream_property';

@Injectable({
  providedIn: 'root'
})
export class DataStreamPropertyService {

  constructor(private httpClient: HttpClient,) { }

  private dataStreamProreriesSubject = new BehaviorSubject<DataStreamProperty[]>([]); 
  dataStreamProreries$ = this.dataStreamProreriesSubject.asObservable();

  dataStreamPropertyUrl = "http://localhost:8080/api/data-stream_property/"

  loadAllDataStreamProperties(dataStreamId: BigInt): any{
    const dataStreamPropery = this.httpClient.get<DataStreamProperty[]>(`${this.dataStreamPropertyUrl + 'get-all/' + dataStreamId }`);
    dataStreamPropery.pipe(
      tap(dataStreamProreries => this.dataStreamProreriesSubject.next(dataStreamProreries)) 
    ).subscribe();

    return dataStreamPropery;
  }

  createDataStreamProperty(newDataStreamProperty: NewDataStreamProperty, dataStreamId: BigInt): void {
    this.httpClient.post<NewDataStreamProperty>(`${this.dataStreamPropertyUrl + dataStreamId }`, newDataStreamProperty).pipe(
      tap(() => this.loadAllDataStreamProperties(dataStreamId))  
    ).subscribe(); 
  }

  updateDataStreamProperty(dataStreamProperty: DataStreamProperty, id: BigInt, dataStreamId: BigInt): void {
    this.httpClient.put<DataStreamProperty>(`${this.dataStreamPropertyUrl + id + '/update'}`, dataStreamProperty).pipe(
      tap(() => this.loadAllDataStreamProperties(dataStreamId))  
    ).subscribe();
  }

  deleteDataStreamProperty(id: BigInt, dataStreamId: BigInt): void {
    this.httpClient.delete<DataStreamProperty>(`${this.dataStreamPropertyUrl + id + '/delete'}`).pipe(
      tap(() => this.loadAllDataStreamProperties(dataStreamId))  
    ).subscribe();
  }

}
