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

    // Constructores
    public PedidoService(PedidoRepository repositorio) {
        this.repositorio = repositorio;
    }

    public boolean validarDatos(Pedido pedido){
        // Validando que el cliente exista
        if (pedido.getCliente() == null || pedido.getCliente().trim().isEmpty()) {
            return false;
        }
        // validar si tiene productos
        if (pedido.getProductos() == null || pedido.getProductos().isEmpty()) {
            return false;
        }
        for (Producto p : pedido.getProductos()) {
            // cantidad mayor a cero
            if (p.getCantidad() <= 0) {
                return false;
            }
            // no se puede solicitar más de lo que hay
            if (p.getCantidad() > p.getExistencias()) {
                return false;
            }
        }
        return true;
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
        //float total = base + impuestos;

        pedido.setImpuestos(impuestos);
        //pedido.setTotal(total);
        return pedido;
    }
    public void calcularTotal(Pedido pedido) {
        float total = pedido.getSubtotal() - pedido.getDescuento() + pedido.getImpuestos();
        pedido.setTotal(total);
    }
    // Determinar el estado del pedido (no implementado)
    public Pedido determinarEstado(Pedido pedido){
        return pedido;
    }

    public Pedido consultarPedido(int id){
        return repositorio.buscarPorId(id);
    }


    public Pedido procesarPedido(Pedido pedido) {
        if (!validarDatos(pedido)) {
            return null;
        }
        calcularSubtotal(pedido);
        aplicarDescuento(pedido);
        aplicarImpuestos(pedido);
        calcularTotal(pedido);
        pedido.setEstado("PROCESADO");

        int idGenerado = repositorio.guardar(pedido);
        pedido.setId(idGenerado);

        return pedido;
    }

    public List<Pedido> listarPedidos() {
        return repositorio.listarPedidos();

    }

}
