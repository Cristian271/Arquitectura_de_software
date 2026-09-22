package modelo;

public class Producto {
    private String nombre;
    private float precio;
    private int cantidad;
    private int existencias;

    public Producto(String nombre, float precio, int cantidad, int existencias) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.existencias = existencias;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getExistencias() {
        return existencias;
    }

    public void setExistencias(int existencias) {
        this.existencias = existencias;
    }
}
