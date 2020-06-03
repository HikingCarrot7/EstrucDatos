package com.sw.controller;

import com.sw.model.CRUDContactosUsuario;
import com.sw.model.CRUDRuta;
import com.sw.model.CRUDUser;
import com.sw.model.Sesion;
import com.sw.model.Usuario;
import com.sw.model.exceptions.NoTengoContactosException;
import com.sw.view.VistaBuscarUsuario;
import com.sw.view.VistaEliminarContacto;
import com.sw.view.VistaListadoUsuarios;
import com.sw.view.VistaPrincipal;
import com.sw.view.VistaProgreso;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.util.List;
import java.util.Observable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import javax.swing.JPanel;
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

        vistaPrincipal.getBtnCerrarSesion().addActionListener(e -> quitarVistaPrincipal());
        vistaPrincipal.setTitle("Bienvenido: " + sesion.getUsuarioActual().getNombreCompleto());
        habilitarMenuPrincipal(true);
    }

    private void accionBtnMostrarTodosUsuarios(ActionEvent e)
    {
        VistaListadoUsuarios vistaListadoUsuarios = new VistaListadoUsuarios(vistaPrincipal);
        new ListadoUsuariosController(vistaListadoUsuarios,
                crudUsuarios.getTodosLosUsuarios(),
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

        if (Alerta.mostrarConfirmacion(vistaPrincipal, "¿Está seguro?",
                "¿Está seguro que quiere eliminar su perfil? Toda su información se perderá"))
        {
            SwingWorker<Long, Long> backgroundTask = new SwingWorker<Long, Long>()
            {
                @Override protected Long doInBackground() throws Exception
                {
                    long before = System.currentTimeMillis();

                    habilitarMenuPrincipal(false);
                    VistaProgreso vistaProgreso = new VistaProgreso(vistaPrincipal);
                    Utils.showDialog(vistaPrincipal, vistaProgreso);

                    Usuario usuarioActual = sesion.getUsuarioActual();
                    List<Usuario> usuarios = crudUsuarios.getTodosLosUsuarios();

                    usuarios.remove(usuarioActual);
                    crudRuta.eliminarRuta(usuarioActual.getCorreo());
                    usuarios.forEach(user -> crudContactosUsers.eliminarContactoUsuario(user.getCorreo(), usuarioActual));

                    crudUsuarios.actualizarUsuarios(usuarios);

                    Utils.quitarDialog(vistaProgreso);
                    quitarVistaPrincipal();

                    return System.currentTimeMillis() - before;
                }
            };

            backgroundTask.addPropertyChangeListener(this::esperarEliminacionDeMiPerfil);
            ejecutarTareaEnSegundoPlano(backgroundTask);
        }
    }

    private void esperarEliminacionDeMiPerfil(PropertyChangeEvent e)
    {
        try
        {
            if (e.getNewValue() == SwingWorker.StateValue.DONE)
                System.out.println(((Future<?>) e.getSource()).get() + " ms");

        } catch (InterruptedException | ExecutionException ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    private void ejecutarTareaEnSegundoPlano(Runnable tarea)
    {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(tarea);
        executorService.shutdown();
    }

    private void quitarVistaPrincipal()
    {
        setChanged();
        notifyObservers();
        vistaPrincipal.setVisible(false);
    }

    private void habilitarMenuPrincipal(boolean habilitar)
    {
        setPanelEnabled(vistaPrincipal.getPanelBotones(), habilitar);
        vistaPrincipal.getBtnCerrarSesion().setEnabled(habilitar);
        vistaPrincipal.getMnArchivo().setEnabled(habilitar);
        vistaPrincipal.getMnEdicion().setEnabled(habilitar);
    }

    private void setPanelEnabled(JPanel panel, boolean isEnabled)
    {
        panel.setEnabled(isEnabled);

        Component[] components = panel.getComponents();

        for (Component component : components)
        {
            if (component instanceof JPanel)
                setPanelEnabled((JPanel) component, isEnabled);

            component.setEnabled(isEnabled);
        }
    }

}
