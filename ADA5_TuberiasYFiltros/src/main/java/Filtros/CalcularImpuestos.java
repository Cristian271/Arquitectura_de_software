package Filtros;

import Main.Pedido;

public class CalcularImpuestos implements Filtro{
    @Override
    public Pedido procesar(Pedido pedido) {
        float base = pedido.getSubtotal() - pedido.getDescuento();
        float impuestos = base * 0.16f;
        float total = base + impuestos;

        pedido.setImpuestos(impuestos);
        pedido.setTotal(total);
        return pedido;
    }
}
