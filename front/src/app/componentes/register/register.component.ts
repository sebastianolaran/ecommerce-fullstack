import { Component } from '@angular/core';
import {AbstractControl, FormBuilder, ReactiveFormsModule, Validators} from '@angular/forms';
import {Router,RouterLink } from '@angular/router';
import { LoginService } from '../../core/service/login-service';
import { RegisterService } from '../../core/service/register-service.service';

@Component({
  selector: 'app-register',
  standalone: true,
  templateUrl: './register.component.html',
  styleUrl: './register.component.css',
  imports: [ReactiveFormsModule,RouterLink],
})
export class RegisterComponent {
  formularioRegistro = this.fb.group({
    username: ['', Validators.required],
    email: ['', [Validators.required, Validators.email]],
    password: ['', Validators.required],
    confirmPassword: ['', Validators.required]
  },{ validator: this.passwordMatchValidator });

  mostrarMensaje: string | undefined;

  constructor(private service: RegisterService, private router: Router, private fb: FormBuilder) {

  }

  passwordMatchValidator(group: AbstractControl): { [key: string]: boolean } | null {
    const password = group.get('password')?.value;
    const confirmPassword = group.get('confirmPassword')?.value;

    return password === confirmPassword ? null : { 'passwordMismatch': true };
  }


  onRegister() {
    const usernameControl = this.formularioRegistro.get("username")
    const emailControl = this.formularioRegistro.get("email");
    const passwordControl = this.formularioRegistro.get("password");

    if (emailControl && emailControl.value && passwordControl && passwordControl.value && usernameControl && usernameControl.value) {
      const username = usernameControl.value;
      const email = emailControl.value;
      const password = passwordControl.value;
      this.service.register(username,email, password).subscribe(
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

    }
  }




}
