import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';

import {NavBarComponent} from "./componentes/nav-bar/nav-bar.component";
import { ReactiveFormsModule } from '@angular/forms';
import { InicioDashboardComponent } from './pages/inicio-dashboard/inicio-dashboard.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, InicioDashboardComponent, NavBarComponent,ReactiveFormsModule],
  templateUrl: './app.component.html',

})
export class AppComponent {
  title = 'front';
}
