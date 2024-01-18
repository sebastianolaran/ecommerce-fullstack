import { Component } from '@angular/core';
import {NavBarComponent} from "../../componentes/nav-bar/nav-bar.component";
import {CommonModule} from "@angular/common";
import {RouterOutlet} from "@angular/router";

@Component({
  selector: 'app-inicio-dashboard',
  templateUrl: './inicio-dashboard.component.html',
  standalone: true,
  imports: [
    CommonModule,
    RouterOutlet,
    NavBarComponent
  ],

  styleUrl: './inicio-dashboard.component.css',
})
export class InicioDashboardComponent {

}
