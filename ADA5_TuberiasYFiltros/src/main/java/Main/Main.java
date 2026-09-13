package Main;
import Filtros.*;

import java.sql.SQLOutput;
import java.util.List;
import  java.util.Scanner;
import java.util.ArrayList;

public class Main {


    public static Pedido creacionPedido(Scanner scanner){
        String cliente;
        ArrayList<Producto> productos = new ArrayList<>();
        Pedido pedido = new Pedido();
        System.out.println("Ingrese el nombre del cliente: ");
        cliente = scanner.nextLine();
        /*La tarea pide que puedan haber pedidos sin productos para probar
         validarlo en el filtro de ValidarDatos */
        while(true){
            String nombreProducto;
            int cantidad;
            int existencia;
            int porcentaje;
            float descuento;
            System.out.println("Ingrese el nombre del producto o presione enter si no sea ingresar productos:");
            nombreProducto = scanner.nextLine();
            if(nombreProducto.isEmpty()){
                System.out.println("No ingresó ningún producto");
                break;
            } else{
                System.out.println("Ingrese la cantidad a comprar");
                cantidad = scanner.nextInt();
                System.out.println("Ingrese la cantidad de unidades disponibles");
                existencia = scanner.nextInt();
                System.out.println("Ingrese el porcentaje de descuento");
                porcentaje = scanner.nextInt();
                descuento = (float) porcentaje /100;
                Producto producto = new Producto(nombreProducto, cantidad, existencia, descuento);
                productos.add(producto);
                System.out.println("Producto creado exitosamente, ingrese 1 si desea crear otro producto, ingrese 0 de lo contrario");
                int opcion = scanner.nextInt();
                if(opcion != 1){
                    break;
                }
            }
        }

        pedido.setCliente(cliente);
        pedido.setProductos(productos);

        return pedido;
    }

    public static void main(String[] args) {
        // Implementación sugerida por el maestro para la tuberia
        List<Filtro> tuberia = List.of(
                new ValidarDatos(),
                new ComprobarDisponibilidad(),
                new CalcularSubtotal(),
                new AplicarDescuento(),
                new CalcularImpuestos(),
                new ConfirmarPedido()
        );
        Scanner scanner = new Scanner(System.in);
        System.out.println("-------------------------------------------------------------");
        System.out.println("Sistema de ventas del equipo:");
        System.out.println("Mia Valentina Reza Islas");
        System.out.println("Aleesandra Anelisse Rivera Manzanero");
        System.out.println("Cristian Uriel Sánchez Sánchez");
        System.out.println("-------------------------------------------------------------");
        while (true) {
            System.out.println("¿Desea crear pedido?");
            System.out.println("Ingresa 1 para crear pedidos, cualquier otro numero de lo contrario");
            int opcion = scanner.nextInt();
            if (opcion == 1) {
                Pedido pedido = creacionPedido(scanner);
                /* Aqui se procesa el pedido en el orden declarado arriba (de arriba a abajo)
                 Ojo <- No pueden crear un metodo en los filtros que no se llame procesar, en caso de que lo hagan
                 deberá ser llamado desde el metodo procesar para que funcione el for correctamente :) */ //<- Borrar este comentario luego
                for (Filtro filtro : tuberia) {
                    pedido = filtro.procesar(pedido);
                }
            } else{
                System.out.println("Cerrando programa");
                break;
            }
        }
    }















}



