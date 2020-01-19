package aeropuerto;

import java.awt.EventQueue;

public class PruebaAeropuerto
{

    public static void main(String[] args)
    {
        EventQueue.invokeLater(() ->
        {
            Interfaz vista = new Interfaz();
            vista.setLocationRelativeTo(null);
            vista.setVisible(true);
            new Controlador(vista);
        });

    }

}
