package modelo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PedidoModelo {
    private Map<Integer, Pedido> pedidos = new HashMap<>();
    private  int siguienteId = 1;

    private List<Observer> observers= new ArrayList<>();

    public void attach(Observer observer) {
        observers.add(observer);
    }

    public void dettach(Observer observer) {
        observers.remove(observer);
    }
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    public Pedido registrarPedido(Pedido pedido){
        //Validaciones
        if (pedido.getCliente() == null || pedido.getCliente().trim().isEmpty()) {
            throw new IllegalArgumentException("El cliente no puede estar vacio");
        }

        if (pedido.getProductos() == null || pedido.getProductos().isEmpty()) {
            throw new IllegalArgumentException("Debe existir al menos un producto");
        }

        for (Producto producto: pedido.getProductos()){
            if(producto.getCantidad()<=0){
                throw new IllegalArgumentException("La cantidad de " + producto.getNombre() + " debe ser mayor a cero");
            }
            if (producto.getCantidad()> producto.getExistencia()){
                throw new IllegalArgumentException("La cantidad de " + producto.getNombre() +
                        " no puede superar la existencia: " +  producto.getExistencia());
            }
        }

        // Calculos (Logica de Negocio)
        float subtotal = 0;
        float descuento = 0;
        float impuestos;
        float total;
        for (Producto producto : pedido.getProductos()) {
            subtotal += producto.getPrecio() * producto.getCantidad();
        }
        if (subtotal >=1000){
            descuento = subtotal* 0.10f;
        }
        impuestos = (subtotal-descuento) * 0.16f;
        total = subtotal-descuento + impuestos;

        pedido.setId(siguienteId);
        pedido.setSubtotal(subtotal);
        pedido.setDescuento(descuento);
        pedido.setImpuestos(impuestos);
        pedido.setTotal(total);
        pedido.setEstado("PROCESADO");

        pedidos.put(pedido.getId(), pedido);
        notifyObservers();
        siguienteId++;

        return pedido;
    }

    public Pedido consultarPedido(int id){
        return pedidos.get(id);
    }

    /*
    El Modelo deberá contener:

    información de pedidos;
    reglas de validación;
    cálculo de subtotal;
    cálculo de descuento;
    cálculo de impuestos;
    cálculo del total;
    registro y consulta de pedidos.
    El Modelo no deberá imprimir información ni solicitar datos al usuario.
     */




}
