import {HttpClient, HttpHeaders} from '@angular/common/http';
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
      // Obtener el token JWT de donde lo tengas guardado (supongamos que está en localStorage)
      const token = localStorage.getItem('token');


      // Configurar el encabezado con el token JWT
      const httpOptions = {
         headers: new HttpHeaders({
            'Authorization': `Bearer ${token}`
         })
      };

      // Hacer la solicitud HTTP GET con el encabezado configurado
      return this.http.get<any>(`${this.apiUrl}/info`, httpOptions);
   }

}
