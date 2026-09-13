package Filtros;
import Main.Pedido;

public interface Filtro {
    Pedido procesar(Pedido pedido);
}
