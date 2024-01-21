import {Component, OnInit} from '@angular/core';

import {LoginService} from '../../core/service/login-service';
import {Router} from '@angular/router';
import {Usuario} from '../../interfaces/usuario';
import {FormBuilder, ReactiveFormsModule, Validators} from '@angular/forms';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  standalone: true,
  imports: [ReactiveFormsModule],
})
export class LoginComponent implements OnInit {
  formularioIngreso = this.fb.group({
    username: ['', Validators.required],
    password: ['', Validators.required]
  });
  mostrarMensaje: string | undefined;

  constructor(private service: LoginService, private router: Router, private fb: FormBuilder) {

  }

  ngOnInit(): void {

  }

  hasErrors(controlName: string, errorType: string): null | false | boolean {
    const control = this.formularioIngreso.get(controlName);
    return control && control.hasError(errorType) && (control.dirty || control.touched);
  }

  onSubmit() {
    const usernameControl = this.formularioIngreso.get("username");
    const passwordControl = this.formularioIngreso.get("password");


    if (usernameControl && usernameControl.value && passwordControl && passwordControl.value) {
      const username = usernameControl.value.toLowerCase();
      const password = passwordControl.value;
      this.service.login(username, password).subscribe(
        (response) => {
          // Manejar la respuesta del servidor, por ejemplo, almacenar el token en el almacenamiento local.
          console.log('Token de autenticación recibido:', response.token);
        },
        (error) => {
          // Manejar errores, por ejemplo, mostrar un mensaje de error al usuario.
          console.error('Error al iniciar sesión:', error);
        }
      );
      this.resetForm();
    }

  }


  resetForm() {
    // Resetea los campos del formulario
    this.formularioIngreso.reset();
  }
}

