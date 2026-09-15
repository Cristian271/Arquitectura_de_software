package Main;
import java.util.ArrayList;
public class Pedido {
    private String cliente;
    private ArrayList<Producto> productos = new ArrayList<>();
    private float subtotal;
    private float impuestos;
    private float total;
    private String estado;
    private float descuento;


    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    public float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }

    public float getImpuestos() {
        return impuestos;
    }

    public void setImpuestos(float impuestos) {
        this.impuestos = impuestos;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public float getDescuento() {return descuento; }

    public void setDescuento(float descuento) { this.descuento = descuento; }

    @Override
    public String toString() {
        int cantidadProductos;

        if (productos != null) {
            cantidadProductos = productos.size();
        } else {
            cantidadProductos = 0;
        }
        return "\nPedido:" + "\n" +
                "Cliente: " + cliente +
                ", Productos: " + cantidadProductos +
                ", Subtotal: $" + subtotal +
                ", Descuento: $" + descuento +
                ", Impuestos: $" + impuestos +
                ", Total: $" + total +
                ", Estado: " + estado;
    }
}
