package com.sw.controller;

import com.sw.model.CRUDContactosUsuario;
import com.sw.model.Sesion;
import com.sw.model.Usuario;
import com.sw.model.exceptions.ArbolVacioException;
import com.sw.view.VistaDatosUsuario;
import java.awt.event.ActionEvent;
import java.util.List;

/**
 *
 * @author HikingCarrot7
 */
public class DatosUsuarioController
{

    private final VistaDatosUsuario vistaDatosUsuario;
    private final Usuario usuarioAMostrarDatos;
    private final CRUDContactosUsuario crudContactosUsuario;
    private final Sesion sesion;

    public DatosUsuarioController(VistaDatosUsuario vistaDatosUsuario, Usuario usuarioAMostrarDatos, boolean mismoUsuario)
    {
        this.vistaDatosUsuario = vistaDatosUsuario;
        this.usuarioAMostrarDatos = usuarioAMostrarDatos;
        this.crudContactosUsuario = CRUDContactosUsuario.getInstance();
        this.sesion = Sesion.getInstance();
        initComponents(mismoUsuario);
    }

    private void initComponents(boolean mismoUsuario)
    {
        vistaDatosUsuario.getBtnAgregar().addActionListener(this::accionBtnAgregar);
        vistaDatosUsuario.getBtnAgregarContactos().addActionListener(this::accionBtnAgregarContactos);
        vistaDatosUsuario.getBtnCancelar().addActionListener(this::accionBtnCancelar);

        vistaDatosUsuario.getLblNombre().setText(usuarioAMostrarDatos.getNombreCompleto());
        vistaDatosUsuario.getLblEdad().setText(String.valueOf(usuarioAMostrarDatos.getEdad()));
        vistaDatosUsuario.getLblCorreo().setText(usuarioAMostrarDatos.getCorreo());

        if (mismoUsuario)
            activarBotonesPrimarios(false);

        else
            validarSiEsContactoAnadido();
    }

    private void accionBtnAgregar(ActionEvent e)
    {
        crudContactosUsuario.anadirContactoAUsuario(sesion.getCorreoUsuarioActual(), usuarioAMostrarDatos);
        habilitarBtnAgregar(false);
        Alerta.mostrarMensaje(vistaDatosUsuario, "Contacto añadido!", "Se ha añadido a tus contactos: " + usuarioAMostrarDatos.getNombreCompleto());
    }

    private void accionBtnAgregarContactos(ActionEvent e)
    {

    }

    private void accionBtnEliminarDeMisContactos(ActionEvent e)
    {

    }

    private void accionBtnCancelar(ActionEvent e)
    {

    }

    private void validarSiEsContactoAnadido()
    {
        try
        {
            List<Usuario> misContactos = crudContactosUsuario.getContactosUsuario(sesion.getCorreoUsuarioActual());

            habilitarBtnAgregar(!misContactos
                    .stream()
                    .anyMatch(user -> user.getCorreo().equals(usuarioAMostrarDatos.getCorreo())));

        } catch (ArbolVacioException e)
        {
            habilitarBtnAgregar(true);
        }
    }

    private void activarBotonesPrimarios(boolean activar)
    {
        habilitarBtnAgregar(activar);
        vistaDatosUsuario.getBtnAgregarContactos().setEnabled(activar);
    }

    private void habilitarBtnAgregar(boolean habilitar)
    {
        vistaDatosUsuario.getBtnAgregar().setEnabled(habilitar);
    }

}
