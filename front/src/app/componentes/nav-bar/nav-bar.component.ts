import {Component, Input, signal} from '@angular/core';
import {Producto} from "../../interfaces/producto";
import {RouterLink} from "@angular/router";

@Component({
  selector: 'app-nav-bar',
  standalone: true,
  imports: [
    RouterLink
  ],
  templateUrl: './nav-bar.component.html',
  styleUrl: './nav-bar.component.css'
})
export class NavBarComponent {
  handleButtonClick(){
    const sidebar = document.querySelector('.sidebar')
    // @ts-ignore
    sidebar.classList.toggle('active')
  }

}
