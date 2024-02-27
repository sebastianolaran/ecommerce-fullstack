import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {



  constructor(private http: HttpClient) {
  }

  private apiUrl = 'http://localhost:8080/api/usuarios';
  register(username:string,email: string, password: string): Observable<any> {
    const credentials = {username,email, password};

    // Realiza la solicitud POST al endpoint de inicio de sesión en tu servidor
    return this.http.post<any>(`${this.apiUrl}/register`, credentials);

  }
}
