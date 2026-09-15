package Filtros;

import Main.Pedido;

public class VerificarFraude implements Filtro {
    @Override
    public Pedido procesar(Pedido pedido) {
        if (pedido.getSubtotal() > 5000){
            throw new IllegalArgumentException(" Se detectó fraude en el registro del pedido");
        }
        return pedido;
    }
}
