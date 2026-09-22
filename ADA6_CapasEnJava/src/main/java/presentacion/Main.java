package presentacion;
import datos.PedidoRepository;
import datos.PedidoRepositoryArchivo;
import datos.PedidoRepositoryMemoria;
import modelo.Pedido;
import negocio.PedidoService;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PedidoRepository repositorio = new PedidoRepositoryMemoria();
        PedidoService servicio = new PedidoService(repositorio); // Aqui la capa de servicio ya tiene acceso a los datos
        PedidoUI presentacion = new PedidoUI();
        Scanner scanner = new Scanner(System.in);

        presentacion.menuPresentacion();
        while(true){
            presentacion.menuInicial();
            int opcion = scanner.nextInt();
            scanner.nextLine();
            if(opcion == 4){
                presentacion.menuSalir();
                break;
            }

            switch (opcion){
                case 1: // Registrar pedido
                    Pedido nuevoPedido = presentacion.capturarDatosPedido(scanner);
                    Pedido registrado = servicio.procesarPedido(nuevoPedido);
                    if (registrado != null) {
                        presentacion.imprimirPedido(registrado);
                        presentacion.actualizarStock(registrado);
                    } else {
                        presentacion.imprimirPedidoNOValido();
                    }
                    break;
                case 2: // Consultar pedido por id
                    int id = presentacion.consultarPedidoPorId(scanner);
                    Pedido pedidoEncontrado = servicio.consultarPedido(id);
                    if (pedidoEncontrado != null) {
                        presentacion.imprimirPedido(pedidoEncontrado);
                    } else {
                        presentacion.imprimirPedidoNoEncontrado();
                    }
                    break;
                case 3: // ListarPedidos
                    presentacion.menuListarPedidos();
                    List <Pedido> pedidos = servicio.listarPedidos();
                    if (pedidos.isEmpty()) {
                        presentacion.imprimirSinPedidosRegistrados();
                    } else {
                        for (Pedido p : pedidos) {
                            presentacion.imprimirPedido(p);
                        }
                    }
                    break;
            }


        }


    }
}
