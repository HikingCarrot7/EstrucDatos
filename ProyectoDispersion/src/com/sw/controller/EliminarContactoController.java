package com.sw.controller;

import com.sw.model.CRUDContactosUsuario;
import com.sw.model.Sesion;
import com.sw.model.Usuario;
import com.sw.view.UIConstants;
import com.sw.view.VistaEliminarContacto;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;

/**
 *
 * @author HikingCarrot7
 */
public class EliminarContactoController implements UIConstants
{

    private final VistaEliminarContacto vistaEliminarContacto;
    private final CRUDContactosUsuario crudContactosUsuario;
    private final Sesion sesion;
    private final TableManager tableManager;

    private int[] idxSeleccionados;

    public EliminarContactoController(VistaEliminarContacto vistaDatosUsuario)
    {
        this.vistaEliminarContacto = vistaDatosUsuario;
        this.crudContactosUsuario = CRUDContactosUsuario.getInstance();
        this.sesion = Sesion.getInstance();
        this.tableManager = TableManager.getInstance();
        initComponents();
    }

    private void initComponents()
    {
        vistaEliminarContacto.getBtnEliminar().addActionListener(this::accionBtnEliminarDeMisContactos);
        vistaEliminarContacto.getBtnCancelar().addActionListener(this::accionBtnCancelar);
        habilitarBtnEliminar(false);
        initTabla();
    }

    private void initTabla()
    {
        JTable tabla = vistaEliminarContacto.getTablaListaContactos();
        List<Usuario> contactos = crudContactosUsuario.getContactosUsuario(sesion.getCorreoUsuarioActual());
        tableManager.initTabla(tabla);
        tableManager.initTableSelectionBehavior(tabla, e -> habilitarBtnEliminar(false));

        tabla.getSelectionModel().addListSelectionListener(e ->
        {
            int[] indices = tableManager.getFilasSeleccionadas(tabla);

            if (indices.length != 0)
                idxSeleccionados = indices;

            habilitarBtnEliminar(true);
        });

        rellenarTabla(tabla, contactos);
    }

    private void accionBtnEliminarDeMisContactos(ActionEvent e)
    {
        JTable table = vistaEliminarContacto.getTablaListaContactos();

        if (Alerta.mostrarConfirmacion(vistaEliminarContacto, "Confirmación",
                "Está apunto de eliminar " + idxSeleccionados.length + " contacto(s)."))
        {
            List<Usuario> usuariosAEliminar = new ArrayList<>();
            List<Usuario> contactosUsuario = crudContactosUsuario.getContactosUsuario(sesion.getCorreoUsuarioActual());

            for (int i : idxSeleccionados)
                usuariosAEliminar.add(contactosUsuario.get(i));

            crudContactosUsuario.eliminarContactosUsuario(sesion.getCorreoUsuarioActual(), usuariosAEliminar);
            Alerta.mostrarMensaje(vistaEliminarContacto, "Completado", "Se han eliminado los contactos.");
            habilitarBtnEliminar(false);
            actualizarTabla();

        } else
            tableManager.seleccionarFilas(table, idxSeleccionados);

    }

    private void accionBtnCancelar(ActionEvent e)
    {
        quitarVentana();
    }

    private void actualizarTabla()
    {
        List<Usuario> contactosUsuario = crudContactosUsuario.getContactosUsuario(sesion.getCorreoUsuarioActual());

        if (contactosUsuario.isEmpty())
        {
            quitarVentana();
            return;
        }

        JTable table = vistaEliminarContacto.getTablaListaContactos();
        tableManager.vaciarTabla(table);
        rellenarTabla(table, contactosUsuario);
        tableManager.limpiarSeleccion(table);
    }

    private void rellenarTabla(JTable table, List<Usuario> contactos)
    {
        contactos.forEach(user -> tableManager.addFila(table, new Object[]
        {
            user.getNombreCompleto(), user.getEdad(), user.getCorreo()
        }));
    }

    private void habilitarBtnEliminar(boolean habilitar)
    {
        vistaEliminarContacto.getBtnEliminar().setEnabled(habilitar);
    }

    private void quitarVentana()
    {
        vistaEliminarContacto.dispose();
    }
}
