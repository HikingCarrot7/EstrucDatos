package aeropuerto;

import java.awt.EventQueue;

public class PruebaAeropuerto
{

    public static void main(String[] args)
    {
        EventQueue.invokeLater(() ->
        {
            VistaPrincipal2 vista = new VistaPrincipal2();
            vista.setLocationRelativeTo(null);
            vista.setVisible(true);
            new Controlador(vista);
        });

    }

}
