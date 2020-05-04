package com.sw.controller;

import com.sw.model.Aeropuerto;
import com.sw.util.DequeEmptyException;
import com.sw.view.Esquema;
import com.sw.view.VistaPrincipal;
import java.util.InputMismatchException;
import javax.swing.JOptionPane;

/**
 *
 * @author Nicolás
 */
public class Controlador
{

    private final String REGEX_NATURAL_VALIDO = "^[0-9]+$";
    private final VistaPrincipal vista;
    private final Aeropuerto aeropuerto;
    private final Esquema esquema;

    public Controlador(VistaPrincipal vista, Aeropuerto aeropuerto)
    {
        this.vista = vista;
        this.aeropuerto = aeropuerto;
        this.esquema = new Esquema(aeropuerto);
        initComponents();
    }

    private void initComponents()
    {
        vista.getPanelGrafico().add(esquema);
        vista.getBtnGenerarVuelos().addActionListener(e -> generarVuelos());
        vista.getBtnSigVuelo().addActionListener(e -> eliminarSigVuelo());
        vista.getBtnEliminarVuelo().addActionListener(e -> eliminarVueloEspecifico());

        vista.getBtnSigVuelo().setEnabled(false);
        vista.getBtnEliminarVuelo().setEnabled(false);
    }

    private void generarVuelos()
    {
        try
        {
            int numeroVuelos = pedirEntrada("Número de vuelos.", REGEX_NATURAL_VALIDO);
            aeropuerto.generarVuelos(numeroVuelos);
            vista.getBtnGenerarVuelos().setEnabled(false);
            vista.getBtnSigVuelo().setEnabled(true);
            vista.getBtnEliminarVuelo().setEnabled(true);

        } catch (InputMismatchException | DequeEmptyException ex)
        {
            mostrarError(ex.getMessage());

        } catch (NullPointerException exc)
        {
        }

        repintarGrafico();
        revisarVuelos();
    }

    private void eliminarSigVuelo()
    {
        try
        {
            aeropuerto.eliminarSiguienteVuelo();

        } catch (DequeEmptyException ex)
        {
            mostrarError(ex.getMessage());
        }

        repintarGrafico();
        revisarVuelos();
    }

    private void eliminarVueloEspecifico()
    {
        try
        {
            int indiceVuelo = pedirEntrada("Índice del vuelo:", REGEX_NATURAL_VALIDO) - 1;

            if (!aeropuerto.esVueloValido(indiceVuelo))
                throw new InputMismatchException("No existe tal vuelo.");

            aeropuerto.eliminarVueloAt(indiceVuelo);

        } catch (InputMismatchException | DequeEmptyException ex)
        {
            mostrarError(ex.getMessage());

        } catch (NullPointerException exc)
        {
        }

        repintarGrafico();
        revisarVuelos();
    }

    private void revisarVuelos()
    {
        if (!aeropuerto.existenVuelosEnCola())
        {
            vista.getBtnGenerarVuelos().setEnabled(true);
            vista.getBtnSigVuelo().setEnabled(false);
            vista.getBtnEliminarVuelo().setEnabled(false);
        }
    }

    private void repintarGrafico()
    {
        esquema.repintar();
    }

    private int pedirEntrada(String mensaje, String regex) throws NullPointerException, InputMismatchException
    {
        String entrada = JOptionPane.showInputDialog(vista, mensaje);

        if (entrada == null)
            throw new NullPointerException();

        if (entradaValida(entrada, regex))
            return Integer.parseInt(entrada);

        throw new InputMismatchException("La entrada no es válida.");
    }

    private void mostrarError(String mensaje)
    {
        JOptionPane.showMessageDialog(vista, mensaje, "Error.", JOptionPane.ERROR_MESSAGE);
    }

    private boolean entradaValida(String text, String regex)
    {
        return text.matches(regex) && Integer.parseInt(text) > 0 && Integer.parseInt(text) <= 10000;
    }

}
