package com.sw.controller;

import com.sw.model.CRUDContactosUsuario;
import com.sw.model.CRUDUser;
import com.sw.model.Sesion;
import com.sw.model.exceptions.ArbolVacioException;
import com.sw.view.VistaBuscarUsuario;
import com.sw.view.VistaListadoUsuarios;
import com.sw.view.VistaPrincipal;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import javax.swing.JDialog;

/**
 *
 * @author HikingCarrot7
 */
public class VistaPrincipalController
{

    private final VistaPrincipal vistaPrincipal;
    private final CRUDUser crudUser;
    private final CRUDContactosUsuario crudContactosUsers;
    private final Sesion sesion;

    public VistaPrincipalController(VistaPrincipal vistaPrincipal)
    {
        this.vistaPrincipal = vistaPrincipal;
        this.crudUser = CRUDUser.getInstance();
        this.crudContactosUsers = CRUDContactosUsuario.getInstance();
        this.sesion = Sesion.getInstance();
        initComponents();
    }

    private void initComponents()
    {
        vistaPrincipal.getMnItmListarTodosUsuarios().addActionListener(this::accionMnItmListarTodosUsuarios);
        vistaPrincipal.getMnItmListarMisContactos().addActionListener(this::accionMnItmListarMisContactos);

        vistaPrincipal.getMnItmBuscarUsuario().addActionListener(this::accionMnItmBuscarUsuario);
        vistaPrincipal.getMnItmEliminarContacto().addActionListener(this::accionMnItmEliminarContacto);
        vistaPrincipal.getMnItmEliminarCuenta().addActionListener(this::accionMnItmEliminarCuenta);

        vistaPrincipal.setTitle("Bienvenido: " + sesion.getUsuarioActual().getNombreCompleto());
    }

    private void accionMnItmListarTodosUsuarios(ActionEvent e)
    {
        VistaListadoUsuarios vistaListadoUsuarios = new VistaListadoUsuarios(vistaPrincipal);
        new ListadoUsuariosController(vistaListadoUsuarios,
                crudUser.getTodosLosUsuarios(),
                "Usuarios registrados en el sistema");

        showDialogAndWait(vistaListadoUsuarios);
    }

    private void accionMnItmListarMisContactos(ActionEvent e)
    {
        try
        {
            VistaListadoUsuarios vistaListadoUsuarios = new VistaListadoUsuarios(vistaPrincipal);
            new ListadoUsuariosController(vistaListadoUsuarios,
                    crudContactosUsers.getContactosUsuario(sesion.getCorreoUsuarioActual()),
                    "Sus contactos agregados");

            showDialogAndWait(vistaListadoUsuarios);

        } catch (ArbolVacioException ex)
        {
            if (Alerta.mostrarConfirmacion(vistaPrincipal, "No tienes contactos",
                    "Aún no tienes contactos añadidos, ¿deseas añadir alguno?"))
                accionMnItmBuscarUsuario(e);
        }
    }

    private void accionMnItmBuscarUsuario(ActionEvent e)
    {
        VistaBuscarUsuario vistaBuscarUsuario = new VistaBuscarUsuario(vistaPrincipal);
        new BuscarUsuarioController(vistaBuscarUsuario);
        showDialogAndWait(vistaBuscarUsuario);
    }

    private void accionMnItmEliminarContacto(ActionEvent e)
    {

    }

    private void accionMnItmEliminarCuenta(ActionEvent e)
    {

    }

    private void showDialogAndWait(JDialog dialog)
    {
        dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setLocationRelativeTo(vistaPrincipal);
        dialog.setVisible(true);
    }

}
