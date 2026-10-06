import controlador.PedidoControlador;
import modelo.PedidoModelo;
import vista.PedidoVista;
import vista.PedidoVistaResumida;

public class Principal {

    public static void main(String[] args) {
        PedidoModelo modelo = new PedidoModelo();
        PedidoVista vista = new PedidoVista();
        //PedidoVista vista = new PedidoVistaResumida()
        PedidoControlador controlador = new PedidoControlador(modelo, vista);
        modelo.attach(vista);
        controlador.iniciarMenu();
    }
}
