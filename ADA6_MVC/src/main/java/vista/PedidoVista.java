package vista;

import modelo.Pedido;

public class PedidoVista {
    public  void menuPresentacion() {
        System.out.println("╔════════════════════════════════════════════════════╗");
        System.out.println("║         SISTEMA DE VENTA DE PRODUCTOS              ║");
        System.out.println("╠════════════════════════════════════════════════════╣");
        System.out.println("║  Integrantes del Equipo:                           ║");
        System.out.println("║    • Mia Valentina Reza Islas                      ║");
        System.out.println("║    • Alessandra Anelisse Rivera Manzanero          ║");
        System.out.println("║    • Cristian Uriel Sánchez Sánchez                ║");
        System.out.println("╚════════════════════════════════════════════════════╝");

    }

    public  void menuInicial() {
        System.out.println("╔════════════════════════════════════════════════════╗");
        System.out.println("║              OPERACIONES DISPONIBLES               ║");
        System.out.println("╠════════════════════════════════════════════════════╣");
        System.out.println("║  [1] Registrar pedido                              ║");
        System.out.println("║  [2] Consultar pedido por ID                       ║");
        System.out.println("║  [3] Listar pedidos                                ║");
        System.out.println("║  [4] Salir                                         ║");
        System.out.println("╚════════════════════════════════════════════════════╝");
        System.out.print("Seleccione una opción: ");
    }

    public void menuRegistrarPedido() {
        System.out.println("╔════════════════════════════════════════════════════╗");
        System.out.println("║                 GENERAR PEDIDO                     ║");
        System.out.println("╚════════════════════════════════════════════════════╝");
    }

    public void menuConsultarPedidoPorId() {
        System.out.println("╔════════════════════════════════════════════════════╗");
        System.out.println("║             CONSULTAR PEDIDO POR ID                ║");
        System.out.println("╚════════════════════════════════════════════════════╝");
        System.out.print("Ingrese el ID del pedido a consultar: ");
    }

    public void menuListarPedidos() {
        System.out.println("╔════════════════════════════════════════════════════╗");
        System.out.println("║                 LISTA DE PEDIDOS                   ║");
        System.out.println("╚════════════════════════════════════════════════════╝");
    }

    public void menuSalir() {
        System.out.println("╔════════════════════════════════════════════════════╗");
        System.out.println("║                CERRANDO PROGRAMA                   ║");
        System.out.println("╚════════════════════════════════════════════════════╝");
    }

    public void imprimirPedidoNOValido() {
        System.out.println("╔════════════════════════════════════════════════════╗");
        System.out.println("║                [!] PEDIDO NO VÁLIDO                ║");
        System.out.println("║   Revise los datos del pedido e intente de nuevo   ║");
        System.out.println("╚════════════════════════════════════════════════════╝");
    }


    public void imprimirSinPedidosRegistrados() {
        System.out.println("╔════════════════════════════════════════════════════╗");
        System.out.println("║       NO HAY PEDIDOS REGISTRADOS EN EL SISTEMA     ║");
        System.out.println("╚════════════════════════════════════════════════════╝");
    }

    public void imprimirPedido(Pedido pedido) {
        System.out.println("╔════════════════════════════════════════════════════╗");
        System.out.println("║                 DATOS DEL PEDIDO                   ║");
        System.out.println("╠════════════════════════════════════════════════════╣");
        System.out.printf("║ ID Pedido:   %-37d ║%n", pedido.getId());
        System.out.printf("║ Cliente:     %-37s ║%n", pedido.getCliente());
        System.out.printf("║ Subtotal:    $%-36.2f ║%n", pedido.getSubtotal());
        System.out.printf("║ Descuento:   $%-36.2f ║%n", pedido.getDescuento());
        System.out.printf("║ Impuestos:   $%-36.2f ║%n", pedido.getImpuestos());
        System.out.printf("║ Total:       $%-36.2f ║%n", pedido.getTotal());
        System.out.printf("║ Estado:      %-37s ║%n", pedido.getEstado());
        System.out.println("╚════════════════════════════════════════════════════╝");
    }
}
