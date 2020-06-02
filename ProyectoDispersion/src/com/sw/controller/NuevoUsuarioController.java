package com.sw.controller;

import com.sw.model.CRUDRuta;
import com.sw.model.CRUDUser;
import com.sw.model.Usuario;
import com.sw.model.exceptions.CorreoNoDisponibleException;
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
    private final Usuario nuevoUsuario;
    private final CRUDUser crudUser;
    private final CRUDRuta crudRuta;

    private boolean seAceptoNuevoUsuario;

    public NuevoUsuarioController(FrmNuevoUsuario frmNuevoUsuario, Usuario nuevoUsuario)
    {
        this.frmNuevoUsuario = frmNuevoUsuario;
        this.nuevoUsuario = nuevoUsuario;
        this.crudUser = CRUDUser.getInstance();
        this.crudRuta = CRUDRuta.getInstance();

        initComponents();
    }

    private void initComponents()
    {
        frmNuevoUsuario.getTxtEdad().setFormatterFactory(new EdadFormatter());
        frmNuevoUsuario.getBtnGuardar().addActionListener(this::accionBtnGuardar);
        frmNuevoUsuario.getBtnCancelar().addActionListener(this::accionBtnCancelar);
    }

    private void accionBtnGuardar(ActionEvent e)
    {
        try
        {
            if (todosCamposValidos())
            {
                if (existeCorreoRegistrado(getCorreo()))
                    throw new CorreoNoDisponibleException();

                if (solicitarRuta())
                {
                    seAceptoNuevoUsuario = true;
                    rellenarDatosNuevoUsuario();
                    quitarVentana();
                }

            } else
                Alerta.mostrarError(frmNuevoUsuario, "Algún campo es incorrecto");

        } catch (CorreoNoDisponibleException ex)
        {
            Alerta.mostrarError(frmNuevoUsuario, ex.getMessage());
        }
    }

    private void accionBtnCancelar(ActionEvent e)
    {
        if (Alerta.mostrarConfirmacion(frmNuevoUsuario, "¿Está seguro?",
                "Se borrarán todos los elementos ingresados en el formulario."))
            quitarVentana();
    }

    private boolean solicitarRuta()
    {
        VistaSelecRuta vistaSelecRuta = new VistaSelecRuta(frmNuevoUsuario);
        SelecRutaController selecRutaController = new SelecRutaController(vistaSelecRuta);
        Util.showDialogAndWait(frmNuevoUsuario, vistaSelecRuta);

        if (selecRutaController.seInsertoUnaRutaValida())
        {
            crudRuta.guardarRuta(getCorreo(),
                    selecRutaController.getRutaSeleccionada() + String.format("/%s.txt", getCorreo()));
            return true;
        }

        return false;
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

    private void rellenarDatosNuevoUsuario()
    {
        nuevoUsuario.setNombreCompleto(getNombre());
        nuevoUsuario.setEdad(Integer.parseInt(getEdad()));
        nuevoUsuario.setCorreo(getCorreo());
        nuevoUsuario.setPassword(getPassword());
    }

    private String getNombre()
    {
        return frmNuevoUsuario.getTxtNombre().getText().trim();
    }

    private String getEdad()
    {
        return frmNuevoUsuario.getTxtEdad().getText().trim();
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

    private void quitarVentana()
    {
        frmNuevoUsuario.dispose();
    }

    public boolean seAceptoNuevoUsuario()
    {
        return seAceptoNuevoUsuario;
    }

}
