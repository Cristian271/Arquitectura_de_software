package Filtros;

import Main.Pedido;
import Main.Producto;

public class ComprobarDisponibilidad implements Filtro{
    @Override
    public Pedido procesar(Pedido pedido) {
        for (Producto producto: pedido.getProductos()){
            if (producto.getCantidad()> producto.getExistencia()){
                throw new IllegalArgumentException("No hay suficiente existencia de " + producto.getNombre());
            }
        }
        return pedido;
    }
}
