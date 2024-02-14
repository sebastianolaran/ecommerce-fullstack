import {Component, OnInit} from '@angular/core';
import {NavBarComponent} from "../../componentes/nav-bar/nav-bar.component";
import {CommonModule} from "@angular/common";
import {RouterOutlet} from "@angular/router";
import {InicioService} from '../../core/service/inicio.service';

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


export class InicioDashboardComponent implements OnInit {

   ordenResponse: any;
   ventasDiarias: number | undefined;
   ventasSemanal: number | undefined;
   ventasMensual: number | undefined;
   montoDiario: number | undefined;
   montoSemanal: number | undefined;
   montoMensual: number | undefined;

   constructor(private inicioService: InicioService) {
      this.ventasSemanal = 0;
      this.ventasDiarias = 0;
      this.ventasMensual = 0;
      this.montoDiario = 0;
      this.montoMensual = 0;
      this.montoSemanal = 0;
   }

   ngOnInit(): void {

      this.obtenerInfoOrdenes();
   }

   obtenerInfoOrdenes() {
      this.inicioService.obtenerOrdenesEnFechas().subscribe(
         data => {
            this.ventasDiarias = data.ventasDiarias;
            this.ventasMensual = data.ventasMensual;
            this.ventasSemanal = data.ventasSemanal;
            this.montoMensual= data.montoMensual;
            this.montoDiario = data.montoDiario;
            this.montoSemanal = data.montoSemanal;
         },
         error => {
            console.error(error);
         }
      );
   }


}
