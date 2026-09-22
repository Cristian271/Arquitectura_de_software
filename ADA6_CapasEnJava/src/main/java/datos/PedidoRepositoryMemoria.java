package datos;
import modelo.Pedido;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PedidoRepositoryMemoria implements PedidoRepository {

    private final Map<Integer, Pedido> almacenamiento = new HashMap<>();

    private int contadorId = 1;

    @Override
    public int guardar(Pedido pedido) {
        // Asignamos un nuevo ID y aumentamos el contador
        pedido.setId(contadorId);
        almacenamiento.put(contadorId, pedido);
        contadorId++;

        return pedido.getId();
    }

    @Override
    public Pedido buscarPorId(int id) {

        return almacenamiento.get(id);
    }

    @Override
    public List<Pedido> listarPedidos() {
        return new ArrayList<>(almacenamiento.values());
    }

}
