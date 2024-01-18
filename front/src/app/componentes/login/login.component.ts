import {Component, OnInit, signal} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {LoginService} from "../../core/service/login-service";
import {NgClass} from "@angular/common";


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

  formularioIngreso : FormGroup;


  constructor(private fb : FormBuilder,private service: LoginService) {
    this.formularioIngreso = this.fb.group({
      username :['',Validators.required],
      password : ['',Validators.required]

    })

  }
  ngOnInit(): void {
    // TODO document why this method 'ngOnInit' is empty

  }

  hasErrors(controlName: string, errorType: string): null | false | boolean {
    const control = this.formularioIngreso.get(controlName);
    return control && control.hasError(errorType) && (control.dirty || control.touched);
  }

  onSubmit(){
    console.log(this.formularioIngreso)
  }




}
