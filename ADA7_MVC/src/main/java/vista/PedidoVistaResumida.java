package vista;
import modelo.Pedido;


public class PedidoVistaResumida extends PedidoVista{
    @Override
    public void imprimirPedido(Pedido pedido) {
        System.out.println("╔════════════════════════════════════════════════════╗");
        System.out.println("║                 DATOS DEL PEDIDO                   ║");
        System.out.println("╠════════════════════════════════════════════════════╣");
        System.out.printf("║ ID Pedido:   %-37d ║%n", pedido.getId());
        System.out.printf("║ Total:       $%-36.2f ║%n", pedido.getTotal());
        System.out.printf("║ Estado:      %-37s ║%n", pedido.getEstado());
        System.out.println("╚════════════════════════════════════════════════════╝");
    }

    @Override
    public void mostrarResultado(Pedido pedido) {
        System.out.println("\n------ PEDIDO REGISTRADO Y CONFIRMADO (VR) ------");
        imprimirPedido(pedido);
    }

    @Override
    public void mostrarPedido(Pedido pedido) {
        System.out.println("\n------ DETALLE DEL PEDIDO (VR) ------");
        imprimirPedido(pedido);
    }
}
