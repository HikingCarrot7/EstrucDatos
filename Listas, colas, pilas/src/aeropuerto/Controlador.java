package aeropuerto;

import excepciones.DequeEmptyException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.InputMismatchException;
import javax.swing.*;

/**
 * @author HikingCarrot7
 */
public class Controlador implements ActionListener
{

    private VistaPrincipal vista;
    private Aeropuerto aeropuerto;

    public Controlador(VistaPrincipal vista)
    {
        this.vista = vista;
        aeropuerto = new Aeropuerto();

        vista.getPanelEsquema().setEnabled(false);
        vista.getPanelEsquema().setVisible(true);

        vista.getSiguienteVuelo().addActionListener(this);
        vista.getGenerarVuelos().addActionListener(this);
        vista.getEliminarUnVuelo().addActionListener(this);

        vista.getSiguienteVuelo().setEnabled(false);
        vista.getEliminarUnVuelo().setEnabled(false);
        repintar();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        try
        {
            switch (e.getActionCommand())
            {
                case "generarVuelos":
                    int numeroVuelos = pedirEntrada("Número de vuelos.", "^[0-9]+$");
                    aeropuerto.generarVuelos(numeroVuelos);
                    vista.getGenerarVuelos().setEnabled(false);
                    vista.getSiguienteVuelo().setEnabled(true);
                    vista.getEliminarUnVuelo().setEnabled(true);
                    break;

                case "siguienteVuelo":
                    aeropuerto.eliminarSiguienteVuelo();
                    break;

                case "eliminarUnVuelo":

                    if (!aeropuerto.existenVuelosEnCola())
                        throw new DequeEmptyException("No hay más vuelos.");

                    int indiceVuelo = pedirEntrada("Índice del vuelo:", "^[0-9]+$") - 1;

                    if (!aeropuerto.esVueloValido(indiceVuelo))
                        throw new InputMismatchException("No existe tal vuelo.");

                    aeropuerto.eliminarVueloAt(indiceVuelo);
                    break;

            }

        } catch (InputMismatchException | DequeEmptyException ex)
        {
            JOptionPane.showMessageDialog(vista, ex.getMessage(), "Error.", JOptionPane.ERROR_MESSAGE);

        } catch (NullPointerException exc)
        {
        }

        if (!aeropuerto.existenVuelosEnCola())
        {
            vista.getGenerarVuelos().setEnabled(true);
            vista.getSiguienteVuelo().setEnabled(false);
            vista.getEliminarUnVuelo().setEnabled(false);
        }

        repintar();
    }

    private int pedirEntrada(String mensaje, String regex)
    {
        String entrada = JOptionPane.showInputDialog(vista, mensaje);

        if (entrada == null)
            throw new NullPointerException();

        if (entradaValida(entrada, regex))
            return Integer.parseInt(entrada);

        throw new InputMismatchException("La entrada no es válida.");
    }

    public boolean entradaValida(String text, String regex)
    {
        return text.matches(regex) && Integer.parseInt(text) > 0 && Integer.parseInt(text) <= 10000;
    }

    private void repintar()
    {
        Rectangle tamanio = vista.getPanelEsquema().getBounds();
        vista.getPanel().removeAll();
        vista.setPanelEsquema(new JInternalFrame("Representación de los vuelos.", true));
        vista.getPanel().add(vista.getPanelEsquema(), JLayeredPane.DEFAULT_LAYER);
        vista.getPanelEsquema().setBounds(tamanio);
        vista.getPanelEsquema().setVisible(true);
        vista.getPanelEsquema().setEnabled(false);
        vista.getPanelEsquema().add(new Esquema(aeropuerto), BorderLayout.CENTER);
    }

}
