package com.sw.controller;

import com.sw.model.CRUDUser;
import com.sw.model.Sesion;
import com.sw.model.Usuario;
import com.sw.model.exceptions.UsuarioNoExistenteException;
import com.sw.view.VistaBuscarUsuario;
import com.sw.view.VistaDatosUsuario;
import java.awt.event.ActionEvent;

/**
 *
 * @author HikingCarrot7
 */
public class BuscarUsuarioController
{

    private final VistaBuscarUsuario vistaBuscarUsuario;
    private final CRUDUser crudUser;
    private final Sesion sesion;

    public BuscarUsuarioController(VistaBuscarUsuario vistaBuscarUsuario)
    {
        this.vistaBuscarUsuario = vistaBuscarUsuario;
        this.crudUser = CRUDUser.getInstance();
        this.sesion = Sesion.getInstance();
        initComponets();
    }

    private void initComponets()
    {
        vistaBuscarUsuario.getBtnBuscar().addActionListener(this::accionBtnBuscar);
        vistaBuscarUsuario.getBtnCancelar().addActionListener(this::accionBtnCancelar);
    }

    private void accionBtnBuscar(ActionEvent e)
    {
        try
        {
            Usuario usuario = crudUser.getUsuario(getCorreo());
            boolean mismoUsuario = usuario.getCorreo().equals(sesion.getCorreoUsuarioActual());

            VistaDatosUsuario vistaDatosUsuario = new VistaDatosUsuario(vistaBuscarUsuario);
            new DatosUsuarioController(vistaDatosUsuario, usuario, mismoUsuario);
            Util.showDialogAndWait(vistaBuscarUsuario, vistaDatosUsuario);

        } catch (UsuarioNoExistenteException ex)
        {
            Alerta.mostrarError(vistaBuscarUsuario, ex.getMessage());
        }
    }

    private void accionBtnCancelar(ActionEvent e)
    {
        quitarVentana();
    }

    private String getCorreo()
    {
        return vistaBuscarUsuario.getTxtCorreo().getText().trim();
    }

    private void quitarVentana()
    {
        vistaBuscarUsuario.dispose();
    }

}
