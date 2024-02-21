import { Routes} from '@angular/router';
import {ProductosComponent} from "./pages/productos/productos.component";
import {ConfiguracionesComponent} from "./pages/configuraciones/configuraciones.component";
import {OrdenesComponent} from "./pages/ordenes/ordenes.component";
import {LoginComponent} from "./componentes/login/login.component";

import { RegisterComponent } from './componentes/register/register.component';
import { InicioDashboardComponent } from './pages/inicio-dashboard/inicio-dashboard.component';
import {AgregarProductoComponent} from "./componentes/agregar-producto/agregar-producto.component";
import {EditarProductoComponent} from "./componentes/editar-producto/editar-producto.component";
import {AgregarOrdenComponent} from "./componentes/agregar-orden/agregar-orden.component";

export const routes: Routes = [
  {path: '',component : LoginComponent},
  {path: 'inicio',component : InicioDashboardComponent },
  {path: "productos",component: ProductosComponent },
  {path: "config",component: ConfiguracionesComponent },
  {path: "ordenes",component: OrdenesComponent },
  {path: "registrar",component: RegisterComponent },
  {path: "productos/agregar",component: AgregarProductoComponent },
  {path: "productos/editar",component: EditarProductoComponent },
   {path: "ordenes/agregar",component: AgregarOrdenComponent }


];


