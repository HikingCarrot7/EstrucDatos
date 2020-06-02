package com.sw.controller;

import com.sw.model.CRUDContactosUsuario;
import com.sw.model.Sesion;
import com.sw.model.Usuario;
import com.sw.view.VistaEliminarContacto;
import java.awt.event.ActionEvent;

/**
 *
 * @author HikingCarrot7
 */
public class EliminarContactoController
{

    private final VistaEliminarContacto vistaDatosUsuario;
    private final Usuario usuarioAMostrarDatos;
    private final CRUDContactosUsuario crudContactosUsuario;
    private final Sesion sesion;

    public EliminarContactoController(VistaEliminarContacto vistaDatosUsuario, Usuario usuarioAMostrarDatos, boolean mismoUsuario)
    {
        this.vistaDatosUsuario = vistaDatosUsuario;
        this.usuarioAMostrarDatos = usuarioAMostrarDatos;
        this.crudContactosUsuario = CRUDContactosUsuario.getInstance();
        this.sesion = Sesion.getInstance();
        initComponents(mismoUsuario);
    }

    private void initComponents(boolean mismoUsuario)
    {
        vistaDatosUsuario.getBtnCancelar().addActionListener(this::accionBtnCancelar);

        vistaDatosUsuario.getLblNombre().setText(usuarioAMostrarDatos.getNombreCompleto());
        vistaDatosUsuario.getLblEdad().setText(String.valueOf(usuarioAMostrarDatos.getEdad()));
        vistaDatosUsuario.getLblCorreo().setText(usuarioAMostrarDatos.getCorreo());

        habilitarBtnEliminar(!mismoUsuario);
    }

    private void accionBtnEliminarDeMisContactos(ActionEvent e)
    {

    }

    private void accionBtnCancelar(ActionEvent e)
    {
        quitarVentana();
    }

    private void habilitarBtnEliminar(boolean habilitar)
    {
        vistaDatosUsuario.getBtnEliminar().setEnabled(habilitar);
    }

    private void quitarVentana()
    {
        vistaDatosUsuario.dispose();
    }
}
