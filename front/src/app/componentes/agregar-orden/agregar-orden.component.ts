import {Component, OnInit} from '@angular/core';
import {Categorias} from "../../interfaces/categorias";
import {FormBuilder, FormsModule, Validators} from "@angular/forms";
import {DataService} from "../../core/service/productos-service";
import {Router} from "@angular/router";

@Component({
   selector: 'app-agregar-orden',
   standalone: true,
   imports: [
      FormsModule
   ],
   templateUrl: './agregar-orden.component.html',
   styleUrl: './agregar-orden.component.css'
})
export class AgregarOrdenComponent {

   opcionesEnum = ['producto1', 'producto2', 'producto3']; // Ajusta según tus opciones
   seleccionados: { [key: string]: boolean } = {};
   cantidades: { [key: string]: number } = {};

   puedoEnviar(): boolean {
      for (const opcion of this.opcionesEnum) {
         if (this.seleccionados[opcion] && this.cantidades[opcion] > 0) {
            return true;
         }
      }
      return false;
   }

   agregarCantidades() {

      // Aquí envía los datosAEnviar a tu servicio o realiza la lógica que necesites
      console.log('Datos a enviar:', this.seleccionados);
   }


}
