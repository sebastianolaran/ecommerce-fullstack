import { Routes} from '@angular/router';
import {ProductosComponent} from "./pages/productos/productos.component";
import {ConfiguracionesComponent} from "./pages/configuraciones/configuraciones.component";
import {OrdenesComponent} from "./pages/ordenes/ordenes.component";
import {LoginComponent} from "./componentes/login/login.component";

export const routes: Routes = [
  {path: '',component : LoginComponent},
  {path: "productos",component: ProductosComponent },
  {path: "config",component: ConfiguracionesComponent },
  {path: "ordenes",component: OrdenesComponent }
];


