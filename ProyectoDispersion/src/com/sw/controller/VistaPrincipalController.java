package com.sw.controller;

import com.sw.model.CRUDContactosUsuario;
import com.sw.model.CRUDRuta;
import com.sw.model.CRUDUser;
import com.sw.model.Sesion;
import com.sw.model.Usuario;
import com.sw.model.exceptions.NoTengoContactosException;
import com.sw.view.VistaBuscarUsuario;
import com.sw.view.VistaEditarPerfil;
import com.sw.view.VistaEliminarContacto;
import com.sw.view.VistaListadoUsuarios;
import com.sw.view.VistaPrincipal;
import com.sw.view.VistaProgreso;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.util.List;
import java.util.Observable;
import java.util.concurrent.ExecutionException;
import javax.swing.SwingWorker;

/**
 *
 * @author HikingCarrot7
 */
public class VistaPrincipalController extends Observable
{

    private final VistaPrincipal vistaPrincipal;
    private final CRUDUser crudUsuarios;
    private final CRUDRuta crudRuta;
    private final CRUDContactosUsuario crudContactosUsers;

    private VistaProgreso vistaProgreso;
    private final Sesion sesion;

    public VistaPrincipalController(VistaPrincipal vistaPrincipal)
    {
        this.vistaPrincipal = vistaPrincipal;
        this.crudUsuarios = CRUDUser.getInstance();
        this.crudRuta = CRUDRuta.getInstance();
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

        vistaPrincipal.getBtnEditarPerfil().addActionListener(this::accionBtnEditarPerfil);

        vistaPrincipal.getBtnCerrarSesion().addActionListener(e -> quitarVistaPrincipal());
        modificarTitulo("Bienvenido: " + sesion.getUsuarioActual().getNombre());
        habilitarMenuPrincipal(true);
    }

    private void accionBtnMostrarTodosUsuarios(ActionEvent e)
    {
        VistaListadoUsuarios vistaListadoUsuarios = new VistaListadoUsuarios(vistaPrincipal);
        new ListadoUsuariosController(vistaListadoUsuarios,
                crudUsuarios.getTodosLosUsuarios(),
                "Usuarios registrados en el sistema");

        DialogUtils.showDialogAndWait(vistaPrincipal, vistaListadoUsuarios);
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
            DialogUtils.showDialogAndWait(vistaPrincipal, vistaListadoUsuarios);

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
        DialogUtils.showDialogAndWait(vistaPrincipal, vistaBuscarUsuario);
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
            DialogUtils.showDialogAndWait(vistaPrincipal, vistaEliminarContacto);

        } catch (NoTengoContactosException ex)
        {
            if (Alerta.mostrarConfirmacion(vistaPrincipal, "No tienes contactos",
                    "Aún no tienes contactos añadidos, ¿deseas añadir alguno?"))
                accionBtnBuscarUsuario(e);
        }
    }

    private void accionBtnEliminarCuenta(ActionEvent e)
    {

        if (Alerta.mostrarConfirmacion(vistaPrincipal, "¿Está seguro?",
                "¿Está seguro que quiere eliminar su perfil? Toda su información se perderá"))
        {
            SwingWorker<Long, Long> backgroundTask = new SwingWorker<Long, Long>()
            {
                @Override protected Long doInBackground() throws Exception
                {
                    long before = System.currentTimeMillis();

                    habilitarMenuPrincipal(false);
                    vistaProgreso = new VistaProgreso(vistaPrincipal);
                    DialogUtils.showDialog(vistaPrincipal, vistaProgreso);

                    Usuario usuarioActual = sesion.getUsuarioActual();
                    List<Usuario> usuarios = crudUsuarios.getTodosLosUsuarios();

                    usuarios.remove(usuarioActual);
                    crudRuta.eliminarRuta(usuarioActual.getCorreo());
                    usuarios.forEach(user -> crudContactosUsers.eliminarContactoUsuario(user.getCorreo(), usuarioActual));

                    crudUsuarios.actualizarUsuarios(usuarios);

                    DialogUtils.quitarDialog(vistaProgreso);
                    quitarVistaPrincipal();

                    return System.currentTimeMillis() - before;
                }
            };

            backgroundTask.addPropertyChangeListener(this::esperarEliminacionDeMiPerfil);
            SwingUtils.ejecutarTareaEnSegundoPlano(backgroundTask);
        }
    }

    private void accionBtnEditarPerfil(ActionEvent e)
    {
        VistaEditarPerfil vistaEditarPerfil = new VistaEditarPerfil(vistaPrincipal);
        EditarPerfilController editarPerfilController = new EditarPerfilController(vistaEditarPerfil, sesion.getUsuarioActual());
        editarPerfilController.addObserver((o, a) -> modificarTitulo("Bienvenido: " + sesion.getUsuarioActual().getNombre()));
        DialogUtils.showDialogAndWait(vistaPrincipal, vistaEditarPerfil);
    }

    public void esperarEliminacionDeMiPerfil(PropertyChangeEvent e)
    {
        try
        {
            if (e.getNewValue() == SwingWorker.StateValue.DONE)
                System.out.println(((SwingWorker<?, ?>) e.getSource()).get() + " milisegundos.");

        } catch (InterruptedException | ExecutionException ex)
        {
            DialogUtils.quitarDialog(vistaProgreso);
            habilitarMenuPrincipal(true);
        }
    }

    private void quitarVistaPrincipal()
    {
        setChanged();
        notifyObservers();
        vistaPrincipal.setVisible(false);
    }

    private void habilitarMenuPrincipal(boolean habilitar)
    {
        SwingUtils.setPanelEnabled(vistaPrincipal.getPanelBotones(), habilitar);
        vistaPrincipal.getBtnCerrarSesion().setEnabled(habilitar);
        vistaPrincipal.getMnArchivo().setEnabled(habilitar);
        vistaPrincipal.getMnEdicion().setEnabled(habilitar);
    }

    private void modificarTitulo(String title)
    {
        vistaPrincipal.setTitle(title);
    }

}
