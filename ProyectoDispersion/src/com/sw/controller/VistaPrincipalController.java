package com.sw.controller;

import com.sw.model.CRUDContactosUsuario;
import com.sw.model.CRUDUser;
import com.sw.model.Sesion;
import com.sw.model.Usuario;
import com.sw.model.exceptions.NoTengoContactosException;
import com.sw.view.VistaBuscarUsuario;
import com.sw.view.VistaEliminarContacto;
import com.sw.view.VistaListadoUsuarios;
import com.sw.view.VistaPrincipal;
import java.awt.event.ActionEvent;
import java.util.List;
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

        vistaPrincipal.getBtnEliminarContacto().addActionListener(this::accionBtnEliminarContacto);
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

        Utils.showDialogAndWait(vistaPrincipal, vistaListadoUsuarios);
    }

    private void accionBtnListarMisContactos(ActionEvent e)
    {
        try
        {
            List<Usuario> contactos = crudContactosUsers.getContactosUsuario(sesion.getCorreoUsuarioActual());

            if (contactos.isEmpty())
                throw new NoTengoContactosException();

            VistaListadoUsuarios vistaListadoUsuarios = new VistaListadoUsuarios(vistaPrincipal);
            new ListadoUsuariosController(vistaListadoUsuarios, contactos, "Sus contactos agregados");
            Utils.showDialogAndWait(vistaPrincipal, vistaListadoUsuarios);

        } catch (NoTengoContactosException ex)
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
        Utils.showDialogAndWait(vistaPrincipal, vistaBuscarUsuario);
    }

    private void accionBtnEliminarContacto(ActionEvent e)
    {
        try
        {
            List<Usuario> contactos = crudContactosUsers.getContactosUsuario(sesion.getCorreoUsuarioActual());

            if (contactos.isEmpty())
                throw new NoTengoContactosException();

            VistaEliminarContacto vistaEliminarContacto = new VistaEliminarContacto(vistaPrincipal);
            new EliminarContactoController(vistaEliminarContacto);
            Utils.showDialogAndWait(vistaPrincipal, vistaEliminarContacto);

        } catch (NoTengoContactosException ex)
        {
            if (Alerta.mostrarConfirmacion(vistaPrincipal, "No tienes contactos",
                    "Aún no tienes contactos añadidos, ¿deseas añadir alguno?"))
                accionBtnBuscarUsuario(e);
        }
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
