import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {map, Observable} from "rxjs";
import {Orden} from "../../interfaces/orden";

@Injectable({
   providedIn: 'root'
})
export class OrdenService {

   constructor(private http: HttpClient) {
   }

   private apiUrl = 'http://localhost:8080/api/ordenes'; // Reemplaza con la URL de tu servidor

   obtenerOrdenes(): Observable<Orden[]> {
      const token = localStorage.getItem('token');


      // Configurar el encabezado con el token JWT
      const httpOptions = {
         headers: new HttpHeaders({
            'Authorization': `Bearer ${token}`
         })
      };
      return this.http.get<Orden[]>(`${this.apiUrl}/`,httpOptions)
   }

   eliminarOrden(id_orden: string): Observable<any> {
      const credencial = {id_orden}
      return this.http.post<any>(`${this.apiUrl}/eliminar`, credencial)
   }


   obtenerProductos(id_orden: string) {
      const token = localStorage.getItem('token');

      console.log(token)

      // Configurar el encabezado con el token JWT
      const httpOptions = {
         headers: new HttpHeaders({
            'Authorization': `Bearer ${token}`
         })
      };
      const credencial = {id_orden}
      console.log(credencial);
      return this.http.post<any>(`${this.apiUrl}/productos`, credencial,httpOptions)
   }


   obtenerNuevoid(): Observable<string> {
      return this.http.get<any>(`${this.apiUrl}/id`).pipe(
         map(response => response.id_orden)
      );
   }

   agregarProductoConOrden(id_orden: string, id_producto: number, cantidad: number, precio_unitario: number) {
      const credencial = {id_orden, id_producto, cantidad, precio_unitario}

      return this.http.post<any>(`${this.apiUrl}/producto/agregar`, credencial)

   }

   agregarOrden(idOrden: string, valorTotal: number, estado: string) {
      const credencial = {idOrden, valorTotal, estado}
      console.log(credencial);
      return this.http.post<any>(`${this.apiUrl}/guardar`, credencial)


   }
}
