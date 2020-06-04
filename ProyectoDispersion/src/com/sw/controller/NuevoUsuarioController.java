package com.sw.controller;

import com.sw.model.CRUDContactosUsuario;
import com.sw.model.CRUDRuta;
import com.sw.model.CRUDUser;
import com.sw.model.Usuario;
import com.sw.model.exceptions.CorreoNoDisponibleException;
import com.sw.model.exceptions.DatosInvalidosException;
import com.sw.model.exceptions.RutaInvalidaException;
import com.sw.model.exceptions.UsuarioNoExistenteException;
import com.sw.view.FrmNuevoUsuario;
import com.sw.view.VistaSelecRuta;
import java.awt.event.ActionEvent;

/**
 *
 * @author HikingCarrot7
 */
public class NuevoUsuarioController
{

    private final FrmNuevoUsuario frmNuevoUsuario;
    private final CRUDContactosUsuario crudContactosUser;
    private final CRUDUser crudUser;
    private final CRUDRuta crudRuta;

    public NuevoUsuarioController(FrmNuevoUsuario frmNuevoUsuario)
    {
        this.frmNuevoUsuario = frmNuevoUsuario;
        this.crudUser = CRUDUser.getInstance();
        this.crudRuta = CRUDRuta.getInstance();
        this.crudContactosUser = CRUDContactosUsuario.getInstance();
        initComponents();
    }

    private void initComponents()
    {
        frmNuevoUsuario.getTxtEdad().setFormatterFactory(new IntegerFormatter());
        frmNuevoUsuario.getBtnGuardar().addActionListener(this::accionBtnGuardar);
        frmNuevoUsuario.getBtnCancelar().addActionListener(this::accionBtnCancelar);
    }

    private void accionBtnGuardar(ActionEvent e)
    {
        try
        {
            if (!todosCamposValidos())
                throw new DatosInvalidosException();

            if (existeCorreoRegistrado(getCorreo()))
                throw new CorreoNoDisponibleException();

            String rutaAlmacenarMisContactos = solicitarRutaAAlmacenarMisContactos();

            crudRuta.anadirRuta(getCorreo(), rutaAlmacenarMisContactos);
            crudContactosUser.crearArbolContactos(getCorreo());
            crudUser.anadirUsuario(getNuevoUsuario());
            DialogUtils.quitarDialog(frmNuevoUsuario);

        } catch (CorreoNoDisponibleException | DatosInvalidosException | RutaInvalidaException ex)
        {
            Alerta.mostrarError(frmNuevoUsuario, ex.getMessage());
        }
    }

    private void accionBtnCancelar(ActionEvent e)
    {
        if (Alerta.mostrarConfirmacion(frmNuevoUsuario, "¿Está seguro?",
                "Se borrarán todos los elementos ingresados en el formulario."))
            DialogUtils.quitarDialog(frmNuevoUsuario);
    }

    private String solicitarRutaAAlmacenarMisContactos() throws RutaInvalidaException
    {
        VistaSelecRuta vistaSelecRuta = new VistaSelecRuta(frmNuevoUsuario);
        SelecRutaController selecRutaController = new SelecRutaController(vistaSelecRuta);
        DialogUtils.showDialogAndWait(frmNuevoUsuario, vistaSelecRuta);

        if (selecRutaController.seInsertoUnaRutaValida())
            return selecRutaController.getRutaSeleccionada() + String.format("%s%s.txt", System.getProperty("file.separator"), getCorreo());

        throw new RutaInvalidaException();
    }

    private boolean existeCorreoRegistrado(String correo)
    {
        try
        {
            return crudUser.getUsuario(correo) != null;

        } catch (UsuarioNoExistenteException e)
        {
            return false;
        }
    }

    private Usuario getNuevoUsuario()
    {
        return new Usuario(getNombre(),
                getCorreo(),
                getPassword(),
                Integer.parseInt(getEdad()),
                getGeneroSeleccionado());
    }

    private String getNombre()
    {
        return frmNuevoUsuario.getTxtNombre().getText().trim();
    }

    private String getEdad()
    {
        return frmNuevoUsuario.getTxtEdad().getText().trim();
    }

    private boolean getGeneroSeleccionado()
    {
        return SwingUtils.getBotonSeleccionado(frmNuevoUsuario.getGeneroGrupo())
                .equals("Hombre") ? Usuario.HOMBRE : Usuario.MUJER;
    }

    private String getCorreo()
    {
        return frmNuevoUsuario.getTxtCorreo().getText().trim();
    }

    private String getPassword()
    {
        return new String(frmNuevoUsuario.getTxtPassword().getPassword()).trim();
    }

    private boolean todosCamposValidos()
    {
        return !(getNombre().isEmpty()
                || getEdad().isEmpty()
                || getCorreo().isEmpty()
                || getPassword().isEmpty());
    }

}
