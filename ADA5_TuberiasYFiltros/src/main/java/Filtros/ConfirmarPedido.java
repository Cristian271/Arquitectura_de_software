package Filtros;

import Main.Pedido;

public class ConfirmarPedido implements Filtro{
    @Override
    public Pedido procesar(Pedido pedido) {
        pedido.setEstado("PROCESADO");
        return pedido;
    }
}
