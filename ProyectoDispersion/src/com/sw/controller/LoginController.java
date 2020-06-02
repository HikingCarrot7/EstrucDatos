package com.sw.controller;

import com.sw.model.CRUDUser;
import com.sw.model.Sesion;
import com.sw.model.Usuario;
import com.sw.model.exceptions.UsuarioNoExistenteException;
import com.sw.view.FrmNuevoUsuario;
import com.sw.view.Login;
import com.sw.view.VistaPrincipal;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JDialog;

/**
 *
 * @author HikingCarrot7
 */
public class LoginController
{

    private final Login login;
    private final CRUDUser crudUser;
    private final Sesion sesion;

    public LoginController(Login login)
    {
        this.login = login;
        this.crudUser = CRUDUser.getInstance();
        this.sesion = Sesion.getInstance();
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
            Usuario usuario = crudUser.getUsuario(getCorreo(), getPassword());
            sesion.setUsuarioActual(usuario);

            VistaPrincipal vistaPrincipal = new VistaPrincipal();
            new VistaPrincipalController(vistaPrincipal);
            quitarLogin();
            mostrarVistaPrincipal(vistaPrincipal);

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
        showDialogAndWait(frmNuevoUsuario);

        if (nuevoUsuarioController.seAceptoNuevoUsuario())
            crudUser.registrarUsuario(nuevoUsuario);
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

    private void mostrarVistaPrincipal(VistaPrincipal vistaPrincipal)
    {
        vistaPrincipal.setLocationRelativeTo(null);
        vistaPrincipal.setVisible(true);
        vistaPrincipal.addWindowListener(new WindowAdapter()
        {
            @Override public void windowClosing(WindowEvent e)
            {
                emergerLogin();
            }
        });
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
