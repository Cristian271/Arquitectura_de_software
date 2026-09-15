package Filtros;

import Main.Pedido;

public class AplicarDescuento implements Filtro{
    @Override
    public Pedido procesar(Pedido pedido) {
        if (pedido.getSubtotal() >= 1000) {
            pedido.setDescuento(pedido.getSubtotal() * 0.10f);
        } else {
            pedido.setDescuento(0.0f);
        }
        return pedido;
    }
}
