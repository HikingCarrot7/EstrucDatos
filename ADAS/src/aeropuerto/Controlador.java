package aeropuerto;

import com.sun.istack.internal.NotNull;
import deque.DequeEmptyException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * @author HikingCarrot7
 */
public class Controlador implements ActionListener
{

    private Vista vista;
    private Aeropuerto aeropuerto;

    public Controlador(Vista vista)
    {
        this.vista = vista;
        aeropuerto = new Aeropuerto();
        aeropuerto.generarVuelos(50);

        vista.getPanelEsquema().setEnabled(false);
        vista.getPanelEsquema().setVisible(true);

        vista.getSiguienteVuelo().addActionListener(this);
        repintar();
    }

    @Override
    public void actionPerformed(@NotNull ActionEvent e)
    {

        switch (e.getActionCommand())
        {
            case "siguienteVuelo":
                try
                {
                    aeropuerto.eliminarSiguienteVuelo();

                } catch (DequeEmptyException ex)
                {
                    System.out.println(ex.getMessage()); // JOptionPane.
                }
                break;
            default:
                throw new AssertionError();
        }

        repintar();
    }

    private void repintar()
    {
        Rectangle tamanio = vista.getPanelEsquema().getBounds();
        vista.getPanel().removeAll();
        vista.setPanelEsquema(new JInternalFrame("Representación de los vuelos.", true));
        vista.getPanel().add(vista.getPanelEsquema(), JLayeredPane.DEFAULT_LAYER);
        vista.getPanelEsquema().setVisible(true);
        vista.getPanelEsquema().setBounds(tamanio);
        vista.getPanelEsquema().setEnabled(false);
        vista.getPanelEsquema().add(new Esquema(aeropuerto), BorderLayout.CENTER);
    }

}
