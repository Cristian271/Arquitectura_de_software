package Filtros;

import Main.Pedido;
import Main.Producto;

public class CalcularSubtotal implements Filtro{
    @Override
    public Pedido procesar(Pedido pedido) {
        float subtotal = 0;
        for (Producto producto : pedido.getProductos()) {
            subtotal += producto.getPrecio() * producto.getCantidad();
        }
        pedido.setSubtotal(subtotal);
        return pedido;
    }
}
