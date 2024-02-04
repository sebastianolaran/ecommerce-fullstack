import {Component, OnInit} from '@angular/core';
import {Categorias} from "../../interfaces/categorias";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {DataService} from "../../core/service/productos-service";
import {ActivatedRoute, Router} from "@angular/router";
import {catchError, EMPTY} from "rxjs";

@Component({
  selector: 'app-editar-producto',
  standalone: true,
  imports: [],
  templateUrl: './editar-producto.component.html',
  styleUrl: './editar-producto.component.css'
})
export class EditarProductoComponent implements OnInit {


  opcionesEnum = Object.values(Categorias);

  id_producto: string | undefined

  formularioEdicion: FormGroup | undefined; // Definir el formulario


  constructor(private service: DataService, private router: Router, private fb: FormBuilder, private route: ActivatedRoute) {

  }


  ngOnInit(): void {

    this.route.queryParams.subscribe(params => {
      this.id_producto = params['id'];
      // Obtén el producto usando el servicio (asumiendo que es un Observable)
      this.service.obtenerProducto(this.id_producto).subscribe(
        (productoResultado: any) => {
          // Asigna valores al formulario
          this.formularioEdicion = this.fb.group({
            nombre: [productoResultado.nombre, Validators.required],
            precio: [productoResultado.precio, Validators.required],
            categoria: [productoResultado.categoria, Validators.required],
            descripcion: [productoResultado.descripcion, Validators.required]
          });
        },
        error => {
          console.error('Error al obtener el producto:', error);
        }
      );

      console.log('ID Producto:', this.id_producto);
    });


  }


}
