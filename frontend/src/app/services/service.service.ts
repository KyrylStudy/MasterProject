import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Service, newService } from '../shared/models/service';
import { HardwareService } from './hardware.service';
import { Hardware } from '../shared/models/hardware';

@Injectable({
  providedIn: 'root'
})
export class ServiceService /*implements OnInit, AfterViewInit*/{

  constructor(private httpClient: HttpClient, private hardwareService:HardwareService,) { }

  hardwares: Hardware[] = [];
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


    //--------secected-service

    private selectedServiceSubject = new BehaviorSubject<Service | null>(null);
    selectedService$ = this.selectedServiceSubject.asObservable();
  
    setSelectedService(selectedService: Service | null): void {
      this.selectedServiceSubject.next(selectedService);
    }
  
    getSelectedService(): Observable<Service | null> {
      return this.selectedService$;
    }


  //---------------------Service
 

private servicesSubject = new BehaviorSubject<Service[]>([]);
services$ = this.servicesSubject.asObservable();

serviceUrl = "http://localhost:8080/api/services/"

getAllServicesByEcuId(ecu_id: BigInt):Observable<Service[]>{
  return this.httpClient.get<Service[]>(`${this.serviceUrl + 'ecu/' + ecu_id }`);
}

loadAllServices(ecuId: BigInt): void{
  this.httpClient.get<Service[]>(`${this.serviceUrl + 'ecu/' + ecuId }`).pipe(
    tap(services => {
      this.servicesSubject.next(services);
    })
  ).subscribe();
}


createService(newService: newService, ecuId: BigInt): void {
  this.httpClient.post<newService>(`${this.serviceUrl + ecuId}`, newService).pipe(
    tap(() => {
      this.loadAllServices(ecuId);
      this.getAllServices(this.hardwares)
    })  
  ).subscribe(); 
}

updadeService(Service: Service, id: BigInt): void {
  this.httpClient.put<Service>(`${this.serviceUrl + id + '/update'}`, Service).pipe(
    tap(() => { 
        this.getAllServices(this.hardwares);
    })  
  ).subscribe();
}

selectedHardware: Hardware | null = null;
deleteService(id: BigInt): void {
  this.httpClient.delete<Service>(`${this.serviceUrl + id + '/delete'}`).pipe(
    tap(() => {
      this.hardwareService.getSelectedHardware().subscribe(data => {
        this.selectedHardware = data;
      })
      
      if(this.selectedHardware){
        this.loadAllServices(this.selectedHardware.id);
      }
      this.getAllServices(this.hardwares)
    })  
  ).subscribe();
}





servicesCountMap: Map<BigInt, number> = new Map();
servicesMap: Map<BigInt, Service[]> = new Map();

private allServicesInArchitectureCountMapSubject = new BehaviorSubject<Map<BigInt, number>>(new Map());
allServicesInArchitectureCountMap$ = this.allServicesInArchitectureCountMapSubject.asObservable();

private allServicesInArchitectureMapSubject = new BehaviorSubject<Map<BigInt, Service[]> >(new Map());
allServicesInArchitectureMap$ = this.allServicesInArchitectureMapSubject.asObservable(); 



  getAllServices(hardwares: Hardware[]): any {
    this.subscribeOnHardwares();

   this.servicesMap = new Map();
    if (hardwares.length) {

      hardwares.map(hardware => this.getAllServicesByEcuId(hardware.id).subscribe(
        services => {
          this.servicesMap.set(hardware.id, services);
          this.allServicesInArchitectureMapSubject.next(new Map(this.servicesMap));
          this.servicesCountMap.set(hardware.id, services.length); 
          this.allServicesInArchitectureCountMapSubject.next(new Map(this.servicesCountMap));
          
        }
      )); 
      
    }
  }

}
