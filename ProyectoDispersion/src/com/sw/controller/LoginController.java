package com.sw.controller;

import com.sw.view.FrmNuevoUsuario;
import com.sw.view.Login;
import com.sw.view.VistaPrincipal;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import javax.swing.JDialog;

/**
 *
 * @author HikingCarrot7
 */
public class LoginController
{

    private final Login login;

    public LoginController(Login login)
    {
        this.login = login;
        initComponents();
    }

    private void initComponents()
    {
        login.getBtnEntrar().addActionListener(this::accionBtnEntrar);
        login.getBtnNuevo().addActionListener(this::accionBtnNuevo);
    }

    private void accionBtnEntrar(ActionEvent e)
    {
        VistaPrincipal vistaPrincipal = new VistaPrincipal();
        new VistaPrincipalController(vistaPrincipal);
        quitarLogin();
        showDialogAndWait(vistaPrincipal);
        emergerLogin();
    }

    private void accionBtnNuevo(ActionEvent e)
    {
        FrmNuevoUsuario frmNuevoUsuario = new FrmNuevoUsuario();
        quitarLogin();
        showDialogAndWait(frmNuevoUsuario);
        emergerLogin();
    }

    private void showDialogAndWait(JDialog dialog)
    {
        dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setLocationRelativeTo(login);
        dialog.setVisible(true);
    }

    private void quitarLogin()
    {
        login.setVisible(false);
    }

    private void emergerLogin()
    {
        login.setVisible(true);
    }

}
