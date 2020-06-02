package com.sw.controller;

import com.sw.model.CRUDContactosUsuario;
import com.sw.model.CRUDUser;
import com.sw.model.Sesion;
import com.sw.model.exceptions.ArbolVacioException;
import com.sw.view.VistaBuscarUsuario;
import com.sw.view.VistaListadoUsuarios;
import com.sw.view.VistaPrincipal;
import java.awt.event.ActionEvent;
import java.util.Observable;

/**
 *
 * @author HikingCarrot7
 */
public class VistaPrincipalController extends Observable
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
        vistaPrincipal.getBtnMostrarTodosUsuarios().addActionListener(this::accionBtnMostrarTodosUsuarios);
        vistaPrincipal.getMnItmListarTodosUsuarios().addActionListener(this::accionBtnMostrarTodosUsuarios);

        vistaPrincipal.getBtnListarMisContactos().addActionListener(this::accionBtnListarMisContactos);
        vistaPrincipal.getMnItmListarMisContactos().addActionListener(this::accionBtnListarMisContactos);

        vistaPrincipal.getBtnBuscarUsuario().addActionListener(this::accionBtnBuscarUsuario);
        vistaPrincipal.getMnItmBuscarUsuario().addActionListener(this::accionBtnBuscarUsuario);

        vistaPrincipal.getBtnEliminarContacto().addActionListener(this::accionBtnEliminarCuenta);
        vistaPrincipal.getMnItmEliminarContacto().addActionListener(this::accionBtnEliminarContacto);

        vistaPrincipal.getBtnEliminarCuenta().addActionListener(this::accionBtnEliminarCuenta);
        vistaPrincipal.getMnItmEliminarCuenta().addActionListener(this::accionBtnEliminarCuenta);

        vistaPrincipal.getBtnCerrarSesion().addActionListener(e -> quitarVentana());
        vistaPrincipal.setTitle("Bienvenido: " + sesion.getUsuarioActual().getNombreCompleto());
    }

    private void accionBtnMostrarTodosUsuarios(ActionEvent e)
    {
        VistaListadoUsuarios vistaListadoUsuarios = new VistaListadoUsuarios(vistaPrincipal);
        new ListadoUsuariosController(vistaListadoUsuarios,
                crudUser.getTodosLosUsuarios(),
                "Usuarios registrados en el sistema");

        Util.showDialogAndWait(vistaPrincipal, vistaListadoUsuarios);
    }

    private void accionBtnListarMisContactos(ActionEvent e)
    {
        try
        {
            VistaListadoUsuarios vistaListadoUsuarios = new VistaListadoUsuarios(vistaPrincipal);
            new ListadoUsuariosController(vistaListadoUsuarios,
                    crudContactosUsers.getContactosUsuario(sesion.getCorreoUsuarioActual()),
                    "Sus contactos agregados");

            Util.showDialogAndWait(vistaPrincipal, vistaListadoUsuarios);

        } catch (ArbolVacioException ex)
        {
            if (Alerta.mostrarConfirmacion(vistaPrincipal, "No tienes contactos",
                    "Aún no tienes contactos añadidos, ¿deseas añadir alguno?"))
                accionBtnBuscarUsuario(e);
        }
    }

    private void accionBtnBuscarUsuario(ActionEvent e)
    {
        VistaBuscarUsuario vistaBuscarUsuario = new VistaBuscarUsuario(vistaPrincipal);
        new BuscarUsuarioController(vistaBuscarUsuario);
        Util.showDialogAndWait(vistaPrincipal, vistaBuscarUsuario);
    }

    private void accionBtnEliminarContacto(ActionEvent e)
    {

    }

    private void accionBtnEliminarCuenta(ActionEvent e)
    {

    }

    private void quitarVentana()
    {
        setChanged();
        notifyObservers();
        vistaPrincipal.setVisible(false);
    }

}
