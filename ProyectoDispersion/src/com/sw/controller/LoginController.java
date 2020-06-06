package com.sw.controller;

import com.sw.controller.util.Alerta;
import com.sw.controller.util.DialogUtils;
import com.sw.model.CRUDUser;
import com.sw.model.Sesion;
import com.sw.model.Usuario;
import com.sw.model.exceptions.UsuarioNoExistenteException;
import com.sw.view.FrmNuevoUsuario;
import com.sw.view.Login;
import com.sw.view.VistaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 *
 * @author HikingCarrot7
 */
public class LoginController
{

    private final WindowListener ACCION_VENTANA_PRINCIPAL_CERRADA = new WindowAdapter()
    {
        @Override public void windowClosing(WindowEvent e)
        {
            mostrarLogin(true);
        }
    };

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
            VistaPrincipalController vistaPrincipalController = new VistaPrincipalController(vistaPrincipal);
            vistaPrincipalController.addObserver((o, a) -> mostrarLogin(true));
            mostrarLogin(false);
            mostrarVistaPrincipal(vistaPrincipal);

        } catch (UsuarioNoExistenteException ex)
        {
            Alerta.mostrarError(login, ex.getMessage());
        }
    }

    private void accionBtnNuevo(ActionEvent e)
    {
        FrmNuevoUsuario frmNuevoUsuario = new FrmNuevoUsuario();
        new NuevoUsuarioController(frmNuevoUsuario);
        DialogUtils.showDialogAndWait(login, frmNuevoUsuario);
    }

    private String getCorreo()
    {
        return login.getTxtCorreo().getText().trim();
    }

    private String getPassword()
    {
        return new String(login.getTxtPassword().getPassword()).trim();
    }

    private void mostrarVistaPrincipal(VistaPrincipal vistaPrincipal)
    {
        vistaPrincipal.setLocationRelativeTo(null);
        vistaPrincipal.setVisible(true);
        vistaPrincipal.removeWindowListener(ACCION_VENTANA_PRINCIPAL_CERRADA);
        vistaPrincipal.addWindowListener(ACCION_VENTANA_PRINCIPAL_CERRADA);
    }

    private void mostrarLogin(boolean mostrar)
    {
        login.setVisible(mostrar);
    }

}
