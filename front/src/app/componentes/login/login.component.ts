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
export class LoginComponent  {
  formularioIngreso = this.fb.group({
    email: ['', Validators.required],
    password: ['', Validators.required]
  });
  mostrarMensaje: string | undefined;

  constructor(private service: LoginService, private router: Router, private fb: FormBuilder) {

  }

   onSubmit() {
    const emailControl = this.formularioIngreso.get("email");
    const passwordControl = this.formularioIngreso.get("password");

    if (emailControl && emailControl.value && passwordControl && passwordControl.value) {
      const email = emailControl.value;
      const password = passwordControl.value;
      this.service.login(email, password).subscribe(
        (response) => {

          if (response.mensaje == "Login exitoso"){
             this.router.navigate(['/inicio'])

          }
          else {
             this.mostrarMensaje = response.mensaje

          }


        },

      );
      this.resetForm();
    }


}


  resetForm() {
    // Resetea los campos del formulario
    this.formularioIngreso.reset();
  }
}

