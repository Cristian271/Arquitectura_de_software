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
        while(true){
            String nombreProducto;
            int cantidad;
            int existencia;
            float precio;
            System.out.println("Ingrese el nombre del producto o presione enter si no sea ingresar productos:");
            nombreProducto = scanner.nextLine();
            if(nombreProducto.isEmpty()){
                System.out.println("No ingresó ningún producto");
                break;
            } else{
                System.out.print("Ingrese el precio unitario: ");
                precio = scanner.nextFloat();
                System.out.println("Ingrese la cantidad a comprar");
                cantidad = scanner.nextInt();
                System.out.println("Ingrese la cantidad de unidades disponibles");
                existencia = scanner.nextInt();
                scanner.nextLine();
                Producto producto = new Producto(nombreProducto, cantidad, existencia, precio);
                productos.add(producto);
                System.out.println("Producto creado exitosamente, ingrese 1 si desea crear otro producto, ingrese 0 de lo contrario");
                int opcion = scanner.nextInt();
                scanner.nextLine();
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
                new VerificarFraude(),
                new AplicarDescuento(),
                new CalcularImpuestos(),
                new ConfirmarPedido()
        );
        Scanner scanner = new Scanner(System.in);
        System.out.println("-------------------------------------------------------------");
        System.out.println("Sistema de ventas del equipo:");
        System.out.println("Mia Valentina Reza Islas");
        System.out.println("Alessandra Anelisse Rivera Manzanero");
        System.out.println("Cristian Uriel Sánchez Sánchez");
        System.out.println("-------------------------------------------------------------");
        while (true) {
            System.out.println("¿Desea crear pedido?");
            System.out.println("Ingresa 1 para crear pedidos, cualquier otro numero de lo contrario");
            int opcion = scanner.nextInt();
            scanner.nextLine();
            if (opcion == 1) {
                Pedido pedido = creacionPedido(scanner);
                try {
                    System.out.println("\nINICIO DEL FLUJO ");

                    for (Filtro filtro : tuberia) {
                        pedido = filtro.procesar(pedido);

                        // Lineas para Imprimir el estado del pedido después de cada filtro para poder observar el flujo
                        System.out.println("\nFiltro aplicado: " + filtro.getClass().getSimpleName());
                        System.out.println(pedido);
                    }

                    System.out.println("\n Pedido completado exitosamente");

                } catch (IllegalArgumentException e) {
                    pedido.setEstado("CANCELADO");
                    System.out.println("ERROR: " + e.getMessage());
                    System.out.println("El flujo fue interrumpido ,el estado final es: " + pedido);
                }
            } else{
                System.out.println("Cerrando programa");
                break;
            }
        }
    }
}