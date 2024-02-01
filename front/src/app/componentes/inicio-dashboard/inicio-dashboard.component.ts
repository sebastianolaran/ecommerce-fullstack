import { Component, OnInit } from '@angular/core';
import {NavBarComponent} from "../../componentes/nav-bar/nav-bar.component";
import {CommonModule} from "@angular/common";
import { RouterOutlet} from "@angular/router";
import { InicioService } from '../../core/service/inicio.service';

@Component({
  selector: 'app-inicio-dashboard',
  templateUrl: './inicio-dashboard.component.html',
  standalone: true,
  imports: [
    CommonModule,
    RouterOutlet,
    NavBarComponent,

  ],

  styleUrl: './inicio-dashboard.component.css',
})


export class InicioDashboardComponent implements OnInit{

  ordenResponse: any;

  constructor(private inicioService: InicioService) { }

  ngOnInit(): void {
    this.obtenerInfoOrdenes();
  }

  obtenerInfoOrdenes() {
    this.inicioService.obtenerOrdenesEnFechas().subscribe(
      data => {
        console.log(data);
        this.ordenResponse = data;
      },
      error => {
        console.error(error);
      }
    );
  }


}
