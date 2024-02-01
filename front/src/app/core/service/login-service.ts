import {Injectable} from '@angular/core';

import {Observable} from 'rxjs';
import {Producto} from "../../interfaces/producto";
import {HttpClient} from '@angular/common/http';
import {Usuario} from "../../interfaces/usuario";


@Injectable({
  providedIn: 'root'
})
export class LoginService {


  constructor(private http: HttpClient) {
  }

  private apiUrl = 'http://localhost:8080/api/usuarios'; // Reemplaza con la URL de tu servidor

  obtenerUsuarios(): Observable<Usuario[]> {
    console.log('Realizando solicitud HTTP...');
    return this.http.get<Usuario[]>(`${this.apiUrl}/`)
  }

  encontrarUsuario(usuario: Usuario): Observable<Usuario> {
    return this.http.get<Usuario>(`${this.apiUrl}/buscar/${usuario.id_usuario}`)
  }

  encontrarUsuarioPorUsername(username: string): Observable<Usuario> {
    return this.http.get<Usuario>(`${this.apiUrl}/buscar/${username}`)
  }

  login(email: string, password: string): Observable<any> {
    const credentials = {email, password};

    // Realiza la solicitud POST al endpoint de inicio de sesión en tu servidor
    return this.http.post<any>(`${this.apiUrl}/login`, credentials);

  }

}
