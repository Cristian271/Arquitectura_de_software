package controlador;

import modelo.Pedido;
import modelo.PedidoModelo;
import modelo.Producto;
import vista.PedidoVista;
import java.util.Map;

public class PedidoControlador {
    private PedidoVista vista;
    private PedidoModelo modelo;

    public PedidoControlador(PedidoModelo modelo, PedidoVista vista){
        this.modelo = modelo;
        this.vista = vista;
    }

    public void setVista(PedidoVista nuevaVista) {
        this.vista = nuevaVista;
    }

    public void registrarPedido() {
        try {
            Pedido pedido = vista.capturarPedido();
            Pedido pedidoProcesado = modelo.registrarPedido(pedido);
            vista.mostrarResultado(pedidoProcesado);
        } catch (IllegalArgumentException e) {
            vista.mostrarError(e.getMessage());
        }
    }

    public void consultarPedido() {
        int id = vista.pedirIdConsulta();
        if (id == -1) {
            vista.mostrarError("El ID ingresado no es válido.");
            return;
        }
        Pedido pedido = modelo.consultarPedido(id);
        if (pedido != null) {
            vista.mostrarPedido(pedido);
        } else {
            vista.mostrarError("No se encontró ningún pedido con el ID: " + id);
        }
    }

    public void listarPedidos() {
        vista.menuListarPedidos();
        Map<Integer, Pedido> pedidos = modelo.getPedidos();

        if (pedidos.isEmpty()) {
            vista.imprimirSinPedidosRegistrados();
        } else {
            for (Pedido pedido : pedidos.values()) {
                vista.imprimirPedido(pedido);
            }
        }
    }

    public void iniciarMenu() {
        vista.menuPresentacion();
        int controlMenu = 1;
        do{
            vista.menuInicial();
            int opcionMenu = vista.pedirOpcionMenu();
            switch (opcionMenu){
                case 1:
                    registrarPedido();
                    break;
                case 2:
                    consultarPedido();
                    break;
                case 3:
                    listarPedidos();
                    break;
                case 4:
                    vista.menuSalir();
                    controlMenu = 0;
                    break;
                default:
                    vista.imprimirOpcionNOValida();
                    break;
            }
        } while (controlMenu == 1);
    }
}
