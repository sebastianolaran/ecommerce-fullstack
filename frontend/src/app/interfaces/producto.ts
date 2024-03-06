export interface Producto {
  id_producto: number,
  nombre: string,
  precio: number,
  categoria: string,
  descripcion: string
}


export interface ProductoConCantidad extends Producto{
  cantidad: number
}
