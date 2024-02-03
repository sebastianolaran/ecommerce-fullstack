
import { Injectable } from '@angular/core';

import { Observable} from 'rxjs';
import {Producto} from "../../interfaces/producto";
import {HttpClient} from '@angular/common/http';


@Injectable({
  providedIn : 'root'
})
export class DataService {
  constructor(private http: HttpClient) { }

  private apiUrl = 'http://localhost:8080/api/productos'; // Reemplaza con la URL de tu servidor

  obtenerProductos(): Observable<Producto[]> {
    console.log('Realizando solicitud HTTP...');
    return this.http.get<Producto[]>(`${this.apiUrl}/`)
  }


  eliminarProducto(id_producto : String):  Observable<any>{
    const credencial = {id_producto}
    return this.http.post<any>(`${this.apiUrl}/eliminar`,credencial)
  }


}
