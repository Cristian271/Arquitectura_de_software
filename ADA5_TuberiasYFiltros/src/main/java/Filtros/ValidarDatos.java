package Filtros;

import Main.Pedido;

public class ValidarDatos implements Filtro {
    @Override
    public Pedido procesar(Pedido pedido) {

        if (pedido.getCliente() == null || pedido.getCliente().trim().isEmpty()) {
            throw new IllegalArgumentException(" El pedido no tiene cliente");
        }

        if (pedido.getProductos() == null || pedido.getProductos().isEmpty()) {
            throw new IllegalArgumentException(" El pedido no contiene productos");
        }

        return pedido;
    }
}
