package com.sw.controller;

import com.sw.model.Usuario;
import com.sw.model.exceptions.CorreoNoDisponibleException;
import com.sw.view.FrmNuevoUsuario;
import java.awt.event.ActionEvent;

/**
 *
 * @author HikingCarrot7
 */
public class NuevoUsuarioController
{

    private final FrmNuevoUsuario frmNuevoUsuario;
    private final Usuario nuevoUsuario;
    private final UserManager userManager;

    private boolean seAceptoNuevoUsuario;

    public NuevoUsuarioController(FrmNuevoUsuario frmNuevoUsuario, Usuario nuevoUsuario)
    {
        this.frmNuevoUsuario = frmNuevoUsuario;
        this.nuevoUsuario = nuevoUsuario;
        this.userManager = UserManager.getInstance();
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
                if (userManager.existeCorreoRegistrado(getCorreo()))
                    throw new CorreoNoDisponibleException();

                seAceptoNuevoUsuario = true;
                rellenarDatosNuevoUsuario();
                quitarVentana();

            } else
                Alerta.mostrarError(frmNuevoUsuario, "Algún campo es incorrecto");

        } catch (CorreoNoDisponibleException ex)
        {
            Alerta.mostrarError(frmNuevoUsuario, ex.getMessage());
        }
    }

    private void accionBtnCancelar(ActionEvent e)
    {
        if (Alerta.mostrarConfirmacion(frmNuevoUsuario, "¿Está seguro?", "Se borrarán todos los elementos ingresados en el formulario."))
            quitarVentana();
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
