package datos;

import modelo.Pedido;
import java.io.*;
import java.util.*;

public class PedidoRepositoryArchivo implements PedidoRepository {

    private final String ARCHIVO = "pedidos.txt";

    @Override
    public int guardar(Pedido pedido) {

        List<Pedido> existentes = listarPedidos();
        int nuevoId = existentes.size() + 1;
        pedido.setId(nuevoId);

        try (PrintWriter writer = new PrintWriter(new FileWriter(ARCHIVO, true))) {
            writer.println(pedido.getId() + "|" + pedido.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return pedido.getId();
    }

    @Override
    public Pedido buscarPorId(int id) {
        for (Pedido p : listarPedidos()) {
            if (p.getId() == id) return p;
        }
        return null;
    }

    @Override
    public List<Pedido> listarPedidos() {
        List<Pedido> lista = new ArrayList<>();
        File file = new File(ARCHIVO);

        if (!file.exists()) return lista;

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                if (!linea.trim().isEmpty()) {
                    String[] datos = linea.split("\\|", 2);
                    int id = Integer.parseInt(datos[0]);

                    Pedido pedido = new Pedido();
                    pedido.setId(id);
                    lista.add(pedido);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lista;
    }
}