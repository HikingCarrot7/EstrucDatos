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
    private VistaPrincipal2 vista2;
    private Aeropuerto aeropuerto;

    public Controlador(VistaPrincipal vista)
    {
        this.vista = vista;

        aeropuerto = new Aeropuerto();
        aeropuerto.addPropertyChangeListener(p ->
        {
            System.out.println(p.getNewValue());
        });

        vista.getPanelEsquema().setEnabled(false);
        vista.getPanelEsquema().setVisible(true);

        vista.getSiguienteVuelo().addActionListener(this);
        vista.getGenerarVuelos().addActionListener(this);
        vista.getEliminarUnVuelo().addActionListener(this);

        vista.getSiguienteVuelo().setEnabled(false);
        vista.getEliminarUnVuelo().setEnabled(false);
        repintar();
    }

    public Controlador(VistaPrincipal2 vista2)
    {
        this.vista2 = vista2;

        aeropuerto = new Aeropuerto();
        vista2.getProgressBar().setValue(0);
        vista2.getProgressBar().setVisible(false);

        aeropuerto.addPropertyChangeListener(p ->
        {
            vista2.getProgressBar().setValue((int) p.getNewValue());
        });

        vista2.getPanelEsquema().setEnabled(false);
        vista2.getPanelEsquema().setVisible(true);

        vista2.getSiguienteVuelo().addActionListener(this);
        vista2.getGenerarVuelos().addActionListener(this);
        vista2.getEliminarVuelo().addActionListener(this);

        vista2.getSiguienteVuelo().setEnabled(false);
        vista2.getEliminarVuelo().setEnabled(false);
        vista2.getProgressBar().setStringPainted(true);
        repintar2();
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
                    vista2.getGenerarVuelos().setEnabled(false);
                    vista2.getSiguienteVuelo().setEnabled(true);
                    vista2.getEliminarVuelo().setEnabled(true);
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
            vista2.getGenerarVuelos().setEnabled(true);
            vista2.getSiguienteVuelo().setEnabled(false);
            vista2.getEliminarVuelo().setEnabled(false);
        }

        repintar2();
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

    private void repintar2()
    {
        Rectangle tamanio = vista2.getPanelEsquema().getBounds();
        vista2.getPanel().removeAll();
        vista2.setPanelEsquema(new JInternalFrame("Representación de los vuelos.", true));
        vista2.getPanel().add(vista2.getPanelEsquema(), JLayeredPane.DEFAULT_LAYER);
        vista2.getPanelEsquema().setBounds(tamanio);
        vista2.getPanelEsquema().setVisible(true);
        vista2.getPanelEsquema().setEnabled(false);
        Esquema esquema = new Esquema(aeropuerto);
        vista2.getPanelEsquema().add(esquema, BorderLayout.CENTER);

        GroupLayout panelLayout = new GroupLayout(vista2.getPanel());
        vista2.getPanel().setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
                panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(vista2.getPanelEsquema(), GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE)
        );
        panelLayout.setVerticalGroup(
                panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(vista2.getPanelEsquema(), GroupLayout.DEFAULT_SIZE, 475, Short.MAX_VALUE)
        );

        GroupLayout panelEsquemaLayout = new GroupLayout(vista2.getPanelEsquema().getContentPane());
        vista2.getPanelEsquema().getContentPane().setLayout(panelEsquemaLayout);
        panelEsquemaLayout.setHorizontalGroup(
                panelEsquemaLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(esquema, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelEsquemaLayout.setVerticalGroup(
                panelEsquemaLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(panelEsquemaLayout.createSequentialGroup()
                                .addComponent(esquema, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );

        System.out.println(vista2.getPanelEsquema().getWidth());
        System.out.println(vista2.getPanelEsquema().getHeight());

    }

}
