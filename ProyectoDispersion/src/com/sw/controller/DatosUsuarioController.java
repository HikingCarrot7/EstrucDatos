package com.sw.controller;

import com.sw.model.CRUDContactosUsuario;
import com.sw.model.Sesion;
import com.sw.model.Usuario;
import com.sw.view.VistaDatosUsuario;
import com.sw.view.VistaListadoUsuarios;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HikingCarrot7
 */
public class DatosUsuarioController
{

    private final VistaDatosUsuario vistaDatosUsuario;
    private final Usuario usuarioAMostrarDatos;
    private final CRUDContactosUsuario crudContactosUsuario;
    private final Sesion sesion;

    public DatosUsuarioController(VistaDatosUsuario vistaDatosUsuario, Usuario usuarioAMostrarDatos, boolean mismoUsuario)
    {
        this.vistaDatosUsuario = vistaDatosUsuario;
        this.usuarioAMostrarDatos = usuarioAMostrarDatos;
        this.crudContactosUsuario = CRUDContactosUsuario.getInstance();
        this.sesion = Sesion.getInstance();
        initComponents(mismoUsuario);
    }

    private void initComponents(boolean mismoUsuario)
    {
        vistaDatosUsuario.getBtnAgregar().addActionListener(this::accionBtnAgregar);
        vistaDatosUsuario.getBtnAgregarContactos().addActionListener(this::accionBtnAgregarContactos);
        vistaDatosUsuario.getBtnCancelar().addActionListener(this::accionBtnCancelar);

        vistaDatosUsuario.getLblNombre().setText(usuarioAMostrarDatos.getNombreCompleto());
        vistaDatosUsuario.getLblEdad().setText(String.valueOf(usuarioAMostrarDatos.getEdad()));
        vistaDatosUsuario.getLblCorreo().setText(usuarioAMostrarDatos.getCorreo());

        if (mismoUsuario)
            activarBotonesPrimarios(false);

        else
            validarSiEsContactoAnadido();

        habilitarBtnAgregarContactos(!contactosQueNoTengoAnadido().isEmpty());
    }

    private void accionBtnAgregar(ActionEvent e)
    {
        crudContactosUsuario.anadirContactoAUsuario(sesion.getCorreoUsuarioActual(), usuarioAMostrarDatos);
        habilitarBtnAgregar(false);
        Alerta.mostrarMensaje(vistaDatosUsuario, "Contacto añadido!", "Se ha añadido a tus contactos: " + usuarioAMostrarDatos.getNombreCompleto());
    }

    private void accionBtnAgregarContactos(ActionEvent e)
    {
        List<Usuario> contactosQueNoTengoAnadido = contactosQueNoTengoAnadido();

        switch (Alerta.mostrarConfirmacion(vistaDatosUsuario,
                "Confirmación", "Se agregarán " + contactosQueNoTengoAnadido.size() + " contacto(s)",
                new Object[]
                {
                    "Ver contactos", "Ok", "Cancelar"
                }))
        {
            case 0:
                VistaListadoUsuarios vistaListadoUsuarios = new VistaListadoUsuarios(vistaDatosUsuario);
                new ListadoUsuariosController(vistaListadoUsuarios, contactosQueNoTengoAnadido, "Contactos que se añadirán");
                Util.showDialogAndWait(vistaDatosUsuario, vistaListadoUsuarios);
                accionBtnAgregarContactos(e);
                break;
            case 1:
                crudContactosUsuario.anadirContactosAUsuario(sesion.getCorreoUsuarioActual(), contactosQueNoTengoAnadido);
                habilitarBtnAgregarContactos(false);
                break;
        }
    }

    private void accionBtnEliminarDeMisContactos(ActionEvent e)
    {

    }

    private void accionBtnCancelar(ActionEvent e)
    {
        quitarVentana();
    }

    private void validarSiEsContactoAnadido()
    {
        List<Usuario> misContactos = crudContactosUsuario.getContactosUsuario(sesion.getCorreoUsuarioActual());

        habilitarBtnAgregar(!misContactos
                .stream()
                .anyMatch(user -> user.getCorreo().equals(usuarioAMostrarDatos.getCorreo())));
    }

    private List<Usuario> contactosQueNoTengoAnadido()
    {
        List<Usuario> misContactos = crudContactosUsuario.getContactosUsuario(sesion.getCorreoUsuarioActual());
        List<Usuario> contactosUsuarioMostrado = crudContactosUsuario.getContactosUsuario(usuarioAMostrarDatos.getCorreo());
        List<Usuario> contactosQueNoTengoAnadido = new ArrayList<>();

        contactosUsuarioMostrado.stream()
                .filter(usuario -> !(misContactos.contains(usuario) || usuario.getCorreo().equals(sesion.getCorreoUsuarioActual())))
                .forEachOrdered(contactosQueNoTengoAnadido::add);

        return contactosQueNoTengoAnadido;
    }

    private void activarBotonesPrimarios(boolean activar)
    {
        habilitarBtnAgregar(activar);
        habilitarBtnAgregarContactos(activar);
    }

    private void habilitarBtnAgregar(boolean habilitar)
    {
        vistaDatosUsuario.getBtnAgregar().setEnabled(habilitar);
    }

    private void habilitarBtnAgregarContactos(boolean habilitar)
    {
        vistaDatosUsuario.getBtnAgregarContactos().setEnabled(habilitar);
    }

    private void quitarVentana()
    {
        vistaDatosUsuario.dispose();
    }

}
