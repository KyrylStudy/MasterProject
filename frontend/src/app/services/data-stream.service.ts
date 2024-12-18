import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, of } from 'rxjs';
import { Connection } from '../shared/models/connection-model';
import { NewConnection } from '../shared/models/connection-model';
import { HttpClient } from '@angular/common/http';
import { DataStream } from '../shared/models/data_stream';
import { NewDataStream } from '../shared/models/data_stream';

@Injectable({
  providedIn: 'root'
})
export class LineCreationService {
  private creatingLineSubject: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  creatingLine$: Observable<boolean> = this.creatingLineSubject.asObservable();

  setCreatingLine(value: boolean): void {
    this.creatingLineSubject.next(value);
  }

  baseUrl = "http://localhost:8080/api/bus/"
  constructor(private httpClient: HttpClient) { }

  getAllBus(architectureId: number): Observable<Connection[]>{

    return this.httpClient.get<Connection[]>(`${this.baseUrl + 'architecture/' + architectureId}`);
  }

  updateBus(Line: Connection, id: BigInt): Observable<any> {
  return this.httpClient.put(`${this.baseUrl + id + '/' + 'update'}`, Line);
  }

  createBus(architectureId: number, NewLine: NewConnection): Observable<any> {
      return this.httpClient.post<any>(`${this.baseUrl + architectureId}`, NewLine);
  }

  deleteBus(id: BigInt): Observable<any> {
    return this.httpClient.delete(`${this.baseUrl + id + '/delete'}`);
    }

    private dataStreamsSubject = new BehaviorSubject<DataStream[]>([]);
    dataStreams$ = this.dataStreamsSubject.asObservable();

    setDataStreams(dataStreams: DataStream[]): void {
      this.dataStreamsSubject.next(dataStreams);
    }
  
    getDataStreams(): Observable<DataStream[]> {
      return this.dataStreams$;
    }



    
    baseDataStreamUrl = "http://localhost:8080/api/data-stream/" 
    getAllDataStreams(architectureId: number): Observable<DataStream[]>{

      return this.httpClient.get<DataStream[]>(`${this.baseDataStreamUrl + 'architecture/' + architectureId}`);
    }
  
    updateDataStream(DataStream: DataStream, id: BigInt): Observable<any> {
    return this.httpClient.put(`${this.baseDataStreamUrl + id + '/update'}`, DataStream);
    }

    createDataStream(architectureId: number, NewDataStream: NewDataStream): Observable<any> {
        return this.httpClient.post<any>(`${this.baseDataStreamUrl + architectureId}`, NewDataStream);
    }
  
    deleteDataStream(id: BigInt): Observable<any> {
      return this.httpClient.delete(`${this.baseDataStreamUrl + id + '/delete'}`);
      }


}
