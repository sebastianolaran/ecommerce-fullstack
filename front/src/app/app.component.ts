import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {InicioDashboardComponent} from "./pages/inicio-dashboard/inicio-dashboard.component";
import {NavBarComponent} from "./componentes/nav-bar/nav-bar.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, InicioDashboardComponent, NavBarComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'front';
}
