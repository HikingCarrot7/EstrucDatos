package com.sw.controller;

import com.sw.controller.util.Alerta;
import com.sw.controller.util.DialogUtils;
import com.sw.controller.util.SeleccionadorDirectorios;
import com.sw.model.dao.DAO;
import com.sw.view.VistaSelecRuta;
import java.awt.event.ActionEvent;
import java.io.File;

/**
 *
 * @author HikingCarrot7
 */
public class SelecRutaController
{

    private final VistaSelecRuta vistaSelecRuta;
    private boolean rutaValida;

    public SelecRutaController(VistaSelecRuta vistaSelecRuta)
    {
        this.vistaSelecRuta = vistaSelecRuta;
        initComponents();
    }

    private void initComponents()
    {
        vistaSelecRuta.getBtnSeleccionarRuta().addActionListener(this::accionBtnSeleccionarRuta);
        vistaSelecRuta.getBtnAceptar().addActionListener(this::accionBtnAceptar);
        vistaSelecRuta.getBtnCancelar().addActionListener(this::accionBtnCancelar);

        vistaSelecRuta.getTxtRuta().setText(String.format("system_data%scontactos", DAO.getSystemFileSeparator()));
    }

    private void accionBtnSeleccionarRuta(ActionEvent e)
    {
        SeleccionadorDirectorios seleccionadorDirectorios = SeleccionadorDirectorios.getInstance();
        File ruta = seleccionadorDirectorios.seleccionarDirectorio(vistaSelecRuta, "Seleccione la ruta...");

        if (ruta != null)
            vistaSelecRuta.getTxtRuta().setText(ruta.getAbsolutePath());
    }

    private void accionBtnAceptar(ActionEvent e)
    {
        if (DAO.esRutaValida(getRutaSeleccionada()))
        {
            rutaValida = true;
            DialogUtils.quitarDialog(vistaSelecRuta);

        } else if (Alerta.mostrarConfirmacion(vistaSelecRuta, "Ruta inválida.",
                "La ruta no es válida, ¿desea usar la ruta por defecto?"))
        {
            vistaSelecRuta.getTxtRuta().setText(String.format("system_data%scontactos", DAO.getSystemFileSeparator()));
            rutaValida = true;
            DialogUtils.quitarDialog(vistaSelecRuta);
        }
    }

    private void accionBtnCancelar(ActionEvent e)
    {
        DialogUtils.quitarDialog(vistaSelecRuta);
    }

    public String getRutaSeleccionada()
    {
        return vistaSelecRuta.getTxtRuta().getText().trim();
    }

    public boolean seInsertoUnaRutaValida()
    {
        return rutaValida;
    }

}
