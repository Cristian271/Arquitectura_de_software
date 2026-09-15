package controlador;

import modelo.Pedido;
import modelo.PedidoModelo;
import vista.PedidoVista;

public class PedidoControlador {
    private PedidoVista vista;  // <- Con esta variable van a hacer todos los prints
    private PedidoModelo modelo;

    public PedidoControlador(PedidoModelo modelo, PedidoVista vista){
        this.modelo = modelo;
        this.vista = vista;
    }

    //El Controlador no deberá contener las reglas de cálculo del pedido.



}
