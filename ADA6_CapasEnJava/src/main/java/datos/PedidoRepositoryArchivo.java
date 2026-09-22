package datos;

import modelo.Pedido;
import modelo.Producto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class PedidoRepositoryArchivo implements PedidoRepository {
    private final String archivoRuta = "pedidos.txt";
    private int contadorId = 1;

    public PedidoRepositoryArchivo() {
        List <Pedido> guardados = listarPedidos();
        for (Pedido p : guardados) {
            if (p.getId() >= contadorId) {
                contadorId = p.getId() + 1;
            }
        }
    }

    @Override
    public int guardar(Pedido pedido) {
        pedido.setId(contadorId);
        contadorId++;

        try (PrintWriter escritor = new PrintWriter(new FileWriter(archivoRuta, true))) {
            StringBuilder linea = new StringBuilder();
            linea.append(pedido.getId()).append("|")
                    .append(pedido.getCliente()).append("|")
                    .append(pedido.getSubtotal()).append("|")
                    .append(pedido.getDescuento()).append("|")
                    .append(pedido.getImpuestos()).append("|")
                    .append(pedido.getTotal()).append("|")
                    .append(pedido.getEstado()).append("|");

            if (pedido.getProductos() != null && !pedido.getProductos().isEmpty()) {
                for (int i = 0; i < pedido.getProductos().size(); i++) {
                    Producto p = pedido.getProductos().get(i);
                    linea.append(p.getNombre()).append(",")
                            .append(p.getPrecio()).append(",")
                            .append(p.getCantidad()).append(",")
                            .append(p.getExistencias());

                    if (i < pedido.getProductos().size() - 1) {
                        linea.append(";");
                    }
                }
            }

            escritor.println(linea.toString());
        } catch (IOException e) {
            System.out.println("Error al guardar en el archivo: " + e.getMessage());
        }

        return pedido.getId();
    }

    @Override
    public Pedido buscarPorId(int id) {
        List<Pedido> pedidos = listarPedidos();
        for (Pedido p : pedidos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    @Override
    public List<Pedido> listarPedidos() {
        List <Pedido> lista= new ArrayList<>();
        File archivo = new File(archivoRuta);

        if (!archivo.exists()) {
            return lista;
        }

        try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;

                String[] datos = linea.split("\\|", -1);
                if (datos.length >= 7) {
                    Pedido pedido = new Pedido();
                    pedido.setId(Integer.parseInt(datos[0]));
                    pedido.setCliente(datos[1]);
                    pedido.setSubtotal(Float.parseFloat(datos[2]));
                    pedido.setDescuento(Float.parseFloat(datos[3]));
                    pedido.setImpuestos(Float.parseFloat(datos[4]));
                    pedido.setTotal(Float.parseFloat(datos[5]));
                    pedido.setEstado(datos[6]);

                    if (datos.length > 7 && !datos[7].isEmpty()) {
                        String[] itemsTexto = datos[7].split(";");
                        for (String itemStr : itemsTexto) {
                            String[] valores = itemStr.split(",");
                            if (valores.length == 4) {
                                String nom = valores[0];
                                float prec = Float.parseFloat(valores[1]);
                                int cant = Integer.parseInt(valores[2]);
                                int exist = Integer.parseInt(valores[3]);

                                pedido.getProductos().add(new Producto(nom, prec, cant, exist));
                            }
                        }
                    }

                    lista.add(pedido);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }

        return lista;
    }
}