package controlador;

import modelo.Pedido;
import modelo.PedidoModelo;
import vista.PedidoVista;
import java.util.Map;

public class PedidoControlador {
    private PedidoVista vista;  // <- Con esta variable van a hacer todos los prints
    private PedidoModelo modelo;

    public PedidoControlador(PedidoModelo modelo, PedidoVista vista){
        this.modelo = modelo;
        this.vista = vista;
    }

    //Nota: manejar la excepcion del numero al hacer scann -- borrar despues

    public void setVista(PedidoVista nuevaVista) {
        this.vista = nuevaVista;
    }

    //Aqui van controlador de registrar, listar y consultar pedidos por id
    public void registrarPedido() {

    }

    public void consultarPedido(int id) {

    }

    public void listarPedidos() {

    }

    public void iniciarMenu() {
        vista.menuPresentacion();
        //aqui debe de estar el switch con las opciones, llamando a los metodos de arriba
    }



}
