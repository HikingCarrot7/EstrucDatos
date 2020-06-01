package com.sw.controller;

import com.sw.model.Usuario;
import com.sw.model.exceptions.UsuarioNoExistenteException;
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
    private final UserManager userManager;

    public LoginController(Login login)
    {
        this.login = login;
        this.userManager = UserManager.getInstance();
        initComponents();
    }

    private void initComponents()
    {
        login.getBtnEntrar().addActionListener(this::accionBtnEntrar);
        login.getBtnNuevo().addActionListener(this::accionBtnNuevo);
    }

    private void accionBtnEntrar(ActionEvent e)
    {
        try
        {
            Usuario usuario = userManager.getUsuario(getCorreo(), getPassword());

            VistaPrincipal vistaPrincipal = new VistaPrincipal();
            new VistaPrincipalController(vistaPrincipal, usuario);
            quitarLogin();
            showDialogAndWait(vistaPrincipal);
            emergerLogin();

        } catch (UsuarioNoExistenteException ex)
        {
            Alerta.mostrarError(login, ex.getMessage());
        }
    }

    private void accionBtnNuevo(ActionEvent e)
    {
        Usuario nuevoUsuario = new Usuario();

        FrmNuevoUsuario frmNuevoUsuario = new FrmNuevoUsuario();
        NuevoUsuarioController nuevoUsuarioController = new NuevoUsuarioController(frmNuevoUsuario, nuevoUsuario);

        quitarLogin();
        showDialogAndWait(frmNuevoUsuario);
        emergerLogin();

        if (nuevoUsuarioController.seAceptoNuevoUsuario())
            userManager.registrarNuevoUsuario(nuevoUsuario);

    }

    private String getCorreo()
    {
        return login.getTxtCorreo().getText().trim();
    }

    private String getPassword()
    {
        return new String(login.getTxtPassword().getPassword()).trim();
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
