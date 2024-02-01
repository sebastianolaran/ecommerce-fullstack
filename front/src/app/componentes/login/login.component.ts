import {Component, OnInit} from '@angular/core';

import {LoginService} from '../../core/service/login-service';
import {Router, RouterLink} from '@angular/router';
import {FormBuilder, ReactiveFormsModule, Validators} from '@angular/forms';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  standalone: true,
  imports: [ReactiveFormsModule,RouterLink],
})
export class LoginComponent implements OnInit {
  formularioIngreso = this.fb.group({
    email: ['', Validators.required],
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
    const emailControl = this.formularioIngreso.get("email");
    const passwordControl = this.formularioIngreso.get("password");

    if (emailControl && emailControl.value && passwordControl && passwordControl.value) {
      const email = emailControl.value;
      const password = passwordControl.value;
      this.service.login(email, password).subscribe(
        (response) => {
          // Manejar la respuesta del servidor, por ejemplo, almacenar el token en el almacenamiento local.
          console.log('Token de autenticación recibido:', response.token);
          this.router.navigate(['/inicio']);
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

