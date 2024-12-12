import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { HardwareProperty, NewHardwareProperty } from '../shared/models/hardware_property';

@Injectable({
  providedIn: 'root'
})
export class HardwarePropertyService {

  constructor(private httpClient: HttpClient,) { }

  private hardwareProreriesSubject = new BehaviorSubject<HardwareProperty[]>([]);
  hardwareProreries$ = this.hardwareProreriesSubject.asObservable();

  hardwarePropertyUrl = "http://localhost:8080/api/ecu/";

  loadAllHardwareProperties(hardwareId: BigInt): Observable<HardwareProperty[]>{
    const hardwareProreries = this.httpClient.get<HardwareProperty[]>(`${this.hardwarePropertyUrl + hardwareId + '/hardwares' }`);
    hardwareProreries.pipe(
      tap(hardwareProreries => this.hardwareProreriesSubject.next(hardwareProreries))
    ).subscribe();

    return hardwareProreries;
  }

  createHardwareProperty(newHardwareProperty: NewHardwareProperty, hardwareId: BigInt): void {
    this.httpClient.post<NewHardwareProperty>(`${this.hardwarePropertyUrl + hardwareId + '/hardware'}`, newHardwareProperty).pipe(
      tap(() => this.loadAllHardwareProperties(hardwareId))  
    ).subscribe(); 
  }

  updateHardwareProperty(hardwareProperty: HardwareProperty, id: BigInt, hardwareId: BigInt): void {
    this.httpClient.put<HardwareProperty>(`${this.hardwarePropertyUrl + 'hardware/' + id + '/update'}`, hardwareProperty).pipe(
      tap(() => this.loadAllHardwareProperties(hardwareId))  
    ).subscribe();
  }

  deleteHardwareProperty(id: BigInt, hardwareId: BigInt): void {
    this.httpClient.delete<HardwareProperty>(`${this.hardwarePropertyUrl + 'hardware/' + id + '/delete'}`).pipe(
      tap(() => this.loadAllHardwareProperties(hardwareId))  
    ).subscribe();
  }

}
