package com.sw.controller;

import com.sw.model.Usuario;
import com.sw.view.UIConstants;
import com.sw.view.VistaListadoUsuarios;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JTable;

/**
 *
 * @author HikingCarrot7
 */
public class ListadoUsuariosController implements UIConstants
{

    private final VistaListadoUsuarios vistaListadoUsuarios;

    public ListadoUsuariosController(VistaListadoUsuarios vistaListadoUsuarios, ArrayList<Usuario> usuariosAMostrar, String title)
    {
        this.vistaListadoUsuarios = vistaListadoUsuarios;
        initComponents(usuariosAMostrar, title);
    }

    private void initComponents(ArrayList<Usuario> usuariosAMostrar, String title)
    {
        vistaListadoUsuarios.getPanelTitulo().setBorder(BorderFactory.createTitledBorder(title));
        vistaListadoUsuarios.getBtnAceptar().addActionListener(this::accionBtnAceptar);

        initTabla(usuariosAMostrar);
    }

    private void initTabla(ArrayList<Usuario> usuariosAMostrar)
    {
        TableManager tableManager = TableManager.getInstance();
        JTable tabla = vistaListadoUsuarios.getTablaListaUsuarios();
        tableManager.initTableSelectionBehavior(tabla);

        for (int i = 0; i < tabla.getColumnCount(); i++)
            tabla.getColumnModel().getColumn(i).setCellEditor(COLUMNA_NO_EDITABLE);

        usuariosAMostrar.forEach(user ->
        {
            tableManager.anadirFila(vistaListadoUsuarios.getTablaListaUsuarios(), new Object[]
            {
                user.getNombreCompleto(), user.getEdad(), user.getCorreo()
            });
        });
    }

    private void accionBtnAceptar(ActionEvent e)
    {
        quitarVentana();
    }

    private void quitarVentana()
    {
        vistaListadoUsuarios.dispose();
    }

}
