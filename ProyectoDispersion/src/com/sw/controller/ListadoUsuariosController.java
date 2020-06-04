package com.sw.controller;

import com.sw.model.Usuario;
import com.sw.view.UIConstants;
import com.sw.view.VistaListadoUsuarios;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JTable;

/**
 *
 * @author HikingCarrot7
 */
public class ListadoUsuariosController implements UIConstants
{

    private final VistaListadoUsuarios vistaListadoUsuarios;

    public ListadoUsuariosController(VistaListadoUsuarios vistaListadoUsuarios, List<Usuario> usuariosAMostrar, String title)
    {
        this.vistaListadoUsuarios = vistaListadoUsuarios;
        initComponents(usuariosAMostrar, title);
    }

    private void initComponents(List<Usuario> usuariosAMostrar, String title)
    {
        vistaListadoUsuarios.getPanelTitulo().setBorder(BorderFactory.createTitledBorder(title));
        vistaListadoUsuarios.getBtnAceptar().addActionListener(this::accionBtnAceptar);

        initTabla(usuariosAMostrar);
    }

    private void initTabla(List<Usuario> usuariosAMostrar)
    {
        TableManager tableManager = TableManager.getInstance();
        JTable tabla = vistaListadoUsuarios.getTablaListaUsuarios();
        tableManager.initTabla(tabla);
        tableManager.initTableSelectionBehavior(tabla);
        tabla.getColumnModel().getColumn(0).setCellRenderer(COLUMNA_NOMBRE_USUARIO_RENDERER);

        usuariosAMostrar.forEach(user -> tableManager.addFila(tabla, new Object[]
        {
            user, user.getEdad(), user.getCorreo()
        }));
    }

    private void accionBtnAceptar(ActionEvent e)
    {
        DialogUtils.quitarDialog(vistaListadoUsuarios);
    }

}
