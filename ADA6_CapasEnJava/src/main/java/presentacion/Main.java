package presentacion;
import datos.PedidoRepositoryMemoria;
import modelo.Pedido;
import negocio.PedidoService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PedidoRepositoryMemoria repositorio = new PedidoRepositoryMemoria();
        PedidoUI presentacion = new PedidoUI();
        PedidoService servicio = new PedidoService(repositorio, presentacion); // Aqui la capa de servicio ya tiene acceso a los datos
        Scanner scanner = new Scanner(System.in);

        presentacion.menuPresentacion();
        while(true){
            presentacion.menuInicial();
            int opcion = scanner.nextInt();
            if(opcion == 4){
                presentacion.menuSalir();
                break;
            }

            switch (opcion){
                case 1: // Registrar pedido
                    presentacion.menuRegistrarPedido();
                    System.out.println();
                    Pedido pedido = new Pedido();
                    pedido = servicio.registrarPedido();

                    break;
                case 2: // Consultar pedido por id
                    presentacion.menuConsultarPedidoPorId();
                    System.out.println();
                    int id = scanner.nextInt();
                    servicio.consultarPedido(id);
                    break;
                case 3: // ListarPedidos
                    presentacion.menuListarPedidos();
                    System.out.println();
                    servicio.listarPedidos();
                    break;
            }


        }


    }
}
