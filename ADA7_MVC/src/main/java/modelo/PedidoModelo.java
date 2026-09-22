package modelo;

import vista.PedidoVista;

import java.util.HashMap;
import java.util.Map;

public class PedidoModelo {
    private Map<Integer, Pedido> pedidos = new HashMap<>();
    private  int siguienteId = 1;


    // nada implementado
    public Pedido registrarPedido(Pedido pedido){
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
