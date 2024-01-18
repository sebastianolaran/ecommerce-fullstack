import {Component, OnInit, signal} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {LoginService} from "../../core/service/login-service";
import {NgClass} from "@angular/common";
import {ActivatedRoute, Router} from '@angular/router';
import { Usuario } from '../../interfaces/usuario';


@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    NgClass
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit {

  formularioIngreso: FormGroup;
  mostrarMensaje: string | undefined;


  constructor(private fb: FormBuilder, private service: LoginService, private route: ActivatedRoute,
              private router: Router) {
    this.formularioIngreso = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required]

    })

  }

  ngOnInit(): void {
    // TODO document why this method 'ngOnInit' is empty

  }

  hasErrors(controlName: string, errorType: string): null | false | boolean {
    const control = this.formularioIngreso.get(controlName);
    return control && control.hasError(errorType) && (control.dirty || control.touched);
  }

  onSubmit() {
    console.log(this.formularioIngreso);

    // Get the values from the form controls
    // @ts-ignore
    const username = this.formularioIngreso.get("username").value;
    // @ts-ignore
    const password = this.formularioIngreso.get("password").value;


    this.service.encontrarUsuarioPorUsername(username).subscribe(
      (usuarioIngresado: Usuario) => {
        if (usuarioIngresado) {
          console.log("Existe el usuario");
          console.log("Verificamos la contraseña");

          if (usuarioIngresado.password === password) {
            console.log("Contraseña ingresada correctamente");
            // Redirige al usuario a /inicio
            this.router.navigate(['/inicio']);
          } else {
            console.log("Contraseña incorrecta");
            // Muestra un mensaje de contraseña incorrecta
            this.mostrarMensaje = 'Contraseña incorrecta';
          }
        } else {
          console.log("Usuario no existe");
          // Muestra un mensaje de usuario no existente
          this.mostrarMensaje = 'Usuario no existente, ¿quieres registrarte?';
        }
      },
      (error) => {
        console.error('Error retrieving user:', error);
      }
    );
  }


}
