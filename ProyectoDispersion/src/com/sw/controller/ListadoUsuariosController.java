package com.sw.controller;

import com.sw.view.VistaListadoUsuarios;
import java.awt.event.ActionEvent;

/**
 *
 * @author HikingCarrot7
 */
public class ListadoUsuariosController
{

    private final VistaListadoUsuarios vistaListadoUsuarios;

    public ListadoUsuariosController(VistaListadoUsuarios vistaListadoUsuarios)
    {
        this.vistaListadoUsuarios = vistaListadoUsuarios;
        initComponents();
    }

    private void initComponents()
    {
        vistaListadoUsuarios.getBtnAceptar().addActionListener(this::accionBtnAceptar);
        vistaListadoUsuarios.getBtnCancelar().addActionListener(this::accionBtnCancelar);
    }

    private void accionBtnAceptar(ActionEvent e)
    {

    }

    private void accionBtnCancelar(ActionEvent e)
    {

    }

}
