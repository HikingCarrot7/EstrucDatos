package com.sw.controller;

import com.sw.model.CRUDContactosUsuario;
import com.sw.model.CRUDRuta;
import com.sw.model.CRUDUser;
import com.sw.model.Sesion;
import com.sw.model.Usuario;
import com.sw.model.dao.DAO;
import com.sw.model.exceptions.CorreoNoDisponibleException;
import com.sw.model.exceptions.DatosInvalidosException;
import com.sw.model.exceptions.RutaInvalidaException;
import com.sw.view.VistaEditarPerfil;
import com.sw.view.VistaProgreso;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.io.File;
import java.util.List;
import java.util.Observable;
import java.util.concurrent.ExecutionException;
import javax.swing.SwingWorker;

/**
 *
 * @author HikingCarrot7
 */
public class EditarPerfilController extends Observable
{

    private final VistaEditarPerfil vistaEditarPerfil;
    private final CRUDContactosUsuario crudContactosUsers;
    private final CRUDUser crudUser;
    private final CRUDRuta crudRuta;
    private final Sesion sesion;

    private VistaProgreso vistaProgreso;
    private Usuario usuarioAEditar;

    public EditarPerfilController(VistaEditarPerfil vistaEditarPerfil, Usuario usuarioAEditar)
    {
        this.vistaEditarPerfil = vistaEditarPerfil;
        this.crudContactosUsers = CRUDContactosUsuario.getInstance();
        this.crudUser = CRUDUser.getInstance();
        this.crudRuta = CRUDRuta.getInstance();
        this.sesion = Sesion.getInstance();
        this.usuarioAEditar = usuarioAEditar;
        initComponents();
        initCamposConDatos(usuarioAEditar);
    }

    private void initComponents()
    {
        vistaEditarPerfil.getBtnGuardar().addActionListener(this::accionBtnGuardar);
        vistaEditarPerfil.getBtnCancelar().addActionListener(this::accionBtnCancelar);
        vistaEditarPerfil.getBtnCambiarRuta().addActionListener(this::accionBtnCambiarRuta);
    }

    private void initCamposConDatos(Usuario usuarioAEditar)
    {
        vistaEditarPerfil.getTxtNombre().setText(usuarioAEditar.getNombre());
        vistaEditarPerfil.getTxtEdad().setText(String.valueOf(usuarioAEditar.getEdad()));
        vistaEditarPerfil.getRdBtnHombre().setSelected(usuarioAEditar.getGenero() == Usuario.HOMBRE);
        vistaEditarPerfil.getRdBtnMujer().setSelected(usuarioAEditar.getGenero() == Usuario.MUJER);
        vistaEditarPerfil.getTxtCorreo().setText(usuarioAEditar.getCorreo());
        vistaEditarPerfil.getTxtPassword().setText(usuarioAEditar.getPassword());
        vistaEditarPerfil.getTxtRuta().setText(DAO.getParent(crudRuta.getRuta(usuarioAEditar.getCorreo())));
    }

    private void accionBtnGuardar(ActionEvent e)
    {
        try
        {
            if (!DAO.esRutaValida(getNuevaRuta()))
                throw new RutaInvalidaException();

            if (!todosCamposValidos())
                throw new DatosInvalidosException();

            if (existeCorreoRegistrado(getCorreo()))
                throw new CorreoNoDisponibleException();

            SwingWorker<Long, Long> backgroundTask = new SwingWorker<Long, Long>()
            {
                @Override protected Long doInBackground() throws Exception
                {
                    long before = System.currentTimeMillis();

                    habilitarFormulario(false);
                    vistaProgreso = new VistaProgreso(vistaEditarPerfil);
                    DialogUtils.showDialog(vistaEditarPerfil, vistaProgreso);

                    crudUser.actualizarUsuario(usuarioAEditar, getDatosNuevoUsuario());
                    crudRuta.actualizarRuta(usuarioAEditar, getCorreo(), getNuevaRuta() + String.format("/%s.txt", getCorreo()));

                    List<Usuario> usuarios = crudUser.getTodosLosUsuarios();

                    usuarios.forEach(user -> crudContactosUsers.actualizarContactoUsuario(user.getCorreo(),
                            usuarioAEditar,
                            getDatosNuevoUsuario()));

                    sesion.setUsuarioActual(getDatosNuevoUsuario());

                    DialogUtils.quitarDialog(vistaProgreso);
                    DialogUtils.quitarDialog(vistaEditarPerfil);

                    return System.currentTimeMillis() - before;
                }
            };

            backgroundTask.addPropertyChangeListener(this::esperarEdicionDeMiPerfil);
            SwingUtils.ejecutarTareaEnSegundoPlano(backgroundTask);

        } catch (RutaInvalidaException | DatosInvalidosException | CorreoNoDisponibleException ex)
        {
            Alerta.mostrarError(vistaEditarPerfil, ex.getMessage());
        }
    }

    public void esperarEdicionDeMiPerfil(PropertyChangeEvent e)
    {
        try
        {
            if (e.getNewValue() == SwingWorker.StateValue.DONE)
            {
                setChanged();
                notifyObservers();
                System.out.println(((SwingWorker<?, ?>) e.getSource()).get() + " milisegundos.");
            }

        } catch (InterruptedException | ExecutionException ex)
        {
            DialogUtils.quitarDialog(vistaProgreso);
            habilitarFormulario(true);
        }
    }

    private void accionBtnCambiarRuta(ActionEvent e)
    {
        SeleccionadorDirectorios seleccionadorDirectorios = SeleccionadorDirectorios.getInstance();

        File ruta = seleccionadorDirectorios.seleccionarDirectorio(vistaEditarPerfil,
                "Seleccione la ruta...",
                new File(DAO.getParent(crudRuta.getRuta(usuarioAEditar.getCorreo()))));

        if (ruta != null)
            vistaEditarPerfil.getTxtRuta().setText(ruta.getAbsolutePath());
    }

    private void accionBtnCancelar(ActionEvent e)
    {
        DialogUtils.quitarDialog(vistaEditarPerfil);
    }

    private boolean existeCorreoRegistrado(String correo)
    {
        List<Usuario> usuarios = crudUser.getTodosLosUsuarios();

        return usuarios.stream()
                .anyMatch(user -> !user.getCorreo().equals(usuarioAEditar.getCorreo()) && user.getCorreo().equals(correo));
    }

    private boolean todosCamposValidos()
    {
        return !(getNombre().isEmpty()
                || getEdad().isEmpty()
                || getCorreo().isEmpty()
                || getPassword().isEmpty());
    }

    private Usuario getDatosNuevoUsuario()
    {
        return new Usuario(getNombre(), getCorreo(), getPassword(), Integer.parseInt(getEdad()), getGeneroSeleccionado());
    }

    private String getNombre()
    {
        return vistaEditarPerfil.getTxtNombre().getText().trim();
    }

    private String getEdad()
    {
        return vistaEditarPerfil.getTxtEdad().getText().trim();
    }

    private boolean getGeneroSeleccionado()
    {
        return SwingUtils.getBotonSeleccionado(vistaEditarPerfil.getGeneroGrupo())
                .equals("Hombre") ? Usuario.HOMBRE : Usuario.MUJER;
    }

    private String getCorreo()
    {
        return vistaEditarPerfil.getTxtCorreo().getText().trim();
    }

    private String getPassword()
    {
        return new String(vistaEditarPerfil.getTxtPassword().getPassword()).trim();
    }

    private String getNuevaRuta()
    {
        return vistaEditarPerfil.getTxtRuta().getText().trim();
    }

    private void habilitarFormulario(boolean habilitar)
    {
        SwingUtils.setPanelEnabled(vistaEditarPerfil.getPanelDatosPersonales(), habilitar);
        SwingUtils.setPanelEnabled(vistaEditarPerfil.getPanelUbicacion(), habilitar);
        vistaEditarPerfil.getBtnGuardar().setEnabled(habilitar);
        vistaEditarPerfil.getBtnCancelar().setEnabled(habilitar);
    }

}
