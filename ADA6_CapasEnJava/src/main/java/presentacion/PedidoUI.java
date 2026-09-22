package presentacion;

import modelo.Pedido;
import modelo.Producto;
import java.util.Scanner;
import java.util.ArrayList;

public class PedidoUI {

    // Catálogo base de prueba
    private Producto[] catalogo = new Producto[] {
            new Producto("Laptop", 12000.0f, 0, 5),
            new Producto("Mouse", 350.0f, 0, 10),
            new Producto("Teclado", 850.0f, 0, 3)
    };

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

    public int consultarPedidoPorId(Scanner scanner) {
        System.out.println("╔════════════════════════════════════════════════════╗");
        System.out.println("║             CONSULTAR PEDIDO POR ID                ║");
        System.out.println("╚════════════════════════════════════════════════════╝");
        System.out.print("Ingrese el ID del pedido a consultar: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        return id;
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


    public void imprimirPedidoNoEncontrado() {
        System.out.println("╔════════════════════════════════════════════════════╗");
        System.out.println("║                [!] PEDIDO NO ENCONTRADO            ║");
        System.out.println("║   No se encontró ningún pedido con ese ID          ║");
        System.out.println("╚════════════════════════════════════════════════════╝");
    }
    public void imprimirPedido(Pedido pedido) {
        System.out.println("╔════════════════════════════════════════════════════╗");
        System.out.println("║                 DATOS DEL PEDIDO                   ║");
        System.out.println("╠════════════════════════════════════════════════════╣");
        System.out.println("   ID Pedido:   " + pedido.getId());
        System.out.println("   Cliente:    " + pedido.getCliente());
        System.out.println("   Productos:");
        if (pedido.getProductos() != null) {
            for (Producto p : pedido.getProductos()) {
                System.out.println("     * " + p.getNombre() + " x" + p.getCantidad() + " ($" + p.getPrecio() + " c/u)");
            }
        }
        System.out.println("   Subtotal:   " + pedido.getSubtotal());
        System.out.println("   Descuento:   $" + pedido.getDescuento());
        System.out.println("   Impuestos:   $" + pedido.getImpuestos());
        System.out.println("   Total:   $" + pedido.getTotal());
        System.out.println("   Estado:   " + pedido.getEstado());
        System.out.println("╚════════════════════════════════════════════════════╝");
    }

    public Pedido capturarDatosPedido(Scanner scanner) {
        menuRegistrarPedido();

        System.out.print("Nombre del cliente: ");
        String cliente = scanner.nextLine();

        System.out.println("\n╔════════════════════════════════════════════════════╗");
        System.out.println("║               PRODUCTOS DISPONIBLES                ║");
        System.out.println("║  1. Laptop ($12000) - Stock: "+ catalogo[0].getExistencias()+"                     ║");
        System.out.println("║  2. Mouse ($350) - Stock: "+ catalogo[1].getExistencias()+"                       ║");
        System.out.println("║  3. Teclado ($850) - Stock: "+ catalogo[2].getExistencias()+"                      ║");
        System.out.println("╚════════════════════════════════════════════════════╝");

        ArrayList<Producto> listaProductos = new ArrayList<>();
        String respuesta = "s";

        while (respuesta.equalsIgnoreCase("s")) {
            System.out.print("\nElige el producto (1, 2 o 3): ");
            int opcion = scanner.nextInt();

            if (opcion >= 1 && opcion <= 3) {
                Producto base = catalogo[opcion - 1];

                System.out.print("Cantidad: ");
                int cantidad = scanner.nextInt();

                Producto item = new Producto(
                        base.getNombre(),
                        base.getPrecio(),
                        cantidad,
                        base.getExistencias()
                );

                listaProductos.add(item);
            } else {
                System.out.println("Opción no válida");
            }

            System.out.print("¿Deseas agregar otro producto? (s/n): ");
            respuesta = scanner.next();
        }

        scanner.nextLine();

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setProductos(listaProductos);

        return pedido;
    }
    public void imprimirSinPedidosRegistrados() {
        System.out.println("╔════════════════════════════════════════════════════╗");
        System.out.println("║       NO HAY PEDIDOS REGISTRADOS EN EL SISTEMA     ║");
        System.out.println("╚════════════════════════════════════════════════════╝");
    }
    public void actualizarStock(Pedido pedido) {
        for (Producto itemComprado : pedido.getProductos()) {
            for (Producto pCatalogo : catalogo) {
                if (pCatalogo.getNombre().equals(itemComprado.getNombre())) {
                    int nuevoStock = pCatalogo.getExistencias() - itemComprado.getCantidad();
                    pCatalogo.setExistencias(nuevoStock);
                }
            }
        }
    }
}
