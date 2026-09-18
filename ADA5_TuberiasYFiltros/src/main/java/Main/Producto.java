package Main;

public class Producto {
    private String nombre;
     private int cantidad;
     private int existencia;
     private float precio;

     public Producto(String nombre, int cantidad, int existencia, float precio){
         this.nombre = nombre;
         this.cantidad = cantidad;
         this.existencia = existencia;
         this.precio = precio;
     }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getExistencia() {
        return existencia;
    }

    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }



    public float getPrecio() { return precio; }

    public void setPrecio(float precio) { this.precio = precio; }
}
