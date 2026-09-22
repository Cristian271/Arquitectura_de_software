package negocio;

import datos.PedidoRepository;
import datos.PedidoRepositoryMemoria;
import modelo.Pedido;
import modelo.Producto;
import presentacion.PedidoUI;

import java.util.List;
import java.util.Scanner;

public class PedidoService {
    private final PedidoRepository repositorio;
    private final PedidoUI presentacion;

    // Constructores
    public PedidoService(PedidoRepository repositorio, PedidoUI presentacion){
        this.repositorio = repositorio;
        this.presentacion = presentacion;
    }

    // Validar datos (no implementado)
    public boolean validarDatos(Pedido pedido){
        boolean datosCorrectos = false;
        return datosCorrectos;
    }
    public Pedido calcularSubtotal(Pedido pedido){
        float subtotal = 0;
        for (Producto producto : pedido.getProductos()) {
            subtotal += producto.getPrecio() * producto.getCantidad();
        }
        pedido.setSubtotal(subtotal);
        return pedido;
    }

    public Pedido aplicarDescuento(Pedido pedido){
        if (pedido.getSubtotal() >= 1000) {
            pedido.setDescuento(pedido.getSubtotal() * 0.10f);
        } else {
            pedido.setDescuento(0.0f);
        }
        return pedido;
    }

    public Pedido aplicarImpuestos(Pedido pedido){
        float base = pedido.getSubtotal() - pedido.getDescuento();
        float impuestos = base * 0.16f;
        float total = base + impuestos;

        pedido.setImpuestos(impuestos);
        pedido.setTotal(total);
        return pedido;
    }
    // Determinar el estado del pedido (no implementado)
    public Pedido determinarEstado(Pedido pedido){
        return pedido;
    }
    // Coordinar el registro y consulta de pedidos
    // (no implementado)
    public Pedido registrarPedido(){
        // pedir todos los datos del pedido
        // Todos los prints deben ser con el objeto presentacion
        Pedido pedido = new Pedido();
        return pedido;
    }
    // (no implementado)
    public void consultarPedido(int id){

    }

    public Pedido procesarPedido(Pedido pedido) {
        if (!validarDatos(pedido)) {
            presentacion.imprimirPedidoNOValido();
            return null;
        }

        calcularSubtotal(pedido);
        aplicarDescuento(pedido);
        aplicarImpuestos(pedido);

        pedido.setTotal(pedido.getSubtotal() - pedido.getDescuento() + pedido.getImpuestos());
        pedido.setEstado("PROCESADO");


        int idGenerado = repositorio.guardar(pedido);
        pedido.setId(idGenerado);

        return pedido;
    }

    public void listarPedidos() {

        List<Pedido> listaPedidos = repositorio.listarPedidos();

        if (listaPedidos.isEmpty()) {
            presentacion.imprimirSinPedidosRegistrados();
            return;
        }

        for (Pedido pedido : listaPedidos) {
            presentacion.imprimirPedido(pedido);
        }

    }

}
