import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class InicioService {

  constructor(private http: HttpClient) {
  }

  private apiUrl = 'http://localhost:8080/api/ordenes'; // Reemplaza con la URL de tu servidor


  obtenerOrdenesEnFechas(): Observable<any>{
    return this.http.get<any>(`${this.apiUrl}/info`)
  }

}
