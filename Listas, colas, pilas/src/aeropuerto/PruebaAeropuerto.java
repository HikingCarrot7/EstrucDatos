package aeropuerto;

import java.awt.EventQueue;

public class PruebaAeropuerto
{

    public static void main(String[] args)
    {
        EventQueue.invokeLater(() ->
        {
            VistaPrincipal vista = new VistaPrincipal();
            vista.setLocationRelativeTo(null);
            vista.setVisible(true);
            new Controlador(vista, new Aeropuerto());
        });
    }

}
