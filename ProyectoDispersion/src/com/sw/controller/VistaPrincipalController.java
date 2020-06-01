package com.sw.controller;

import com.sw.model.Usuario;
import com.sw.view.VistaBuscarUsuario;
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
    private final Usuario usuarioActual;

    public VistaPrincipalController(VistaPrincipal vistaPrincipal, Usuario usuarioActual)
    {
        this.vistaPrincipal = vistaPrincipal;
        this.usuarioActual = usuarioActual;
        initComponents();
    }

    private void initComponents()
    {
        vistaPrincipal.getMnItmListarTodosUsuarios().addActionListener(this::accionMnItmListarTodosUsuarios);
        vistaPrincipal.getMnItmListarMisContactos().addActionListener(this::accionMnItmListarMisContactos);

        vistaPrincipal.getMnItmBuscarUsuario().addActionListener(this::accionMnItmBuscarUsuario);
        vistaPrincipal.getMnItmEliminarContacto().addActionListener(this::accionMnItmEliminarContacto);
        vistaPrincipal.getMnItmEliminarCuenta().addActionListener(this::accionMnItmEliminarCuenta);
    }

    private void accionMnItmListarTodosUsuarios(ActionEvent e)
    {

    }

    private void accionMnItmListarMisContactos(ActionEvent e)
    {

    }

    private void accionMnItmBuscarUsuario(ActionEvent e)
    {
        VistaBuscarUsuario vistaBuscarUsuario = new VistaBuscarUsuario(vistaPrincipal);
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
