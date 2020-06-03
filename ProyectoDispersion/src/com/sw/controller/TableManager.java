package com.sw.controller;

import com.sw.view.UIConstants;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Nicolás
 */
public class TableManager implements UIConstants
{

    private static TableManager instance;

    public static synchronized TableManager getInstance()
    {
        if (instance == null)
            instance = new TableManager();

        return instance;
    }

    private TableManager()
    {

    }

    public void initTabla(JTable table)
    {
        table.setDefaultRenderer(Object.class, MY_TABLE_CELL_RENDERER);
        JTableHeader jTableHeader = table.getTableHeader();
        jTableHeader.setDefaultRenderer(MY_TABLE_HEADER_RENDERER);
        jTableHeader.setReorderingAllowed(false);
        table.setTableHeader(jTableHeader);
        table.setDefaultEditor(Object.class, TABLA_NO_EDITABLE);
        table.setGridColor(new Color(237, 237, 237));
        table.setRowHeight(20);
        table.setRowSelectionAllowed(true);
        table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    }

    public void quitarCabeceraTabla(JTable table)
    {
        table.getTableHeader().setUI(null);
    }

    public void initTable(JTable table)
    {
        table.setRowHeight(25);
    }

    public void initTableSelectionBehavior(JTable table)
    {
        initTableSelectionBehavior(table, (ActionListener) null);
    }

    public void initTableSelectionBehavior(JTable table, ActionListener action)
    {
        initTableSelectionBehavior(table, new FocusAdapter()
        {
            @Override public void focusLost(FocusEvent e)
            {
                table.clearSelection();
            }

        }, action);
    }

    public void initTableSelectionBehavior(JTable table, FocusListener focusListener)
    {
        initTableSelectionBehavior(table, focusListener, null);
    }

    public void initTableSelectionBehavior(JTable table, FocusListener focusListener, ActionListener action)
    {
        table.addFocusListener(focusListener);

        table.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("ESCAPE"), "Clear selection");
        table.getActionMap().put("Clear selection", new AbstractAction()
        {
            @Override public void actionPerformed(ActionEvent e)
            {
                table.getSelectionModel().clearSelection();

                if (action != null)
                    action.actionPerformed(e);
            }
        });
    }

    public void setFilas(JTable table, Object[][] elementos)
    {
        vaciarTabla(table);
        addFilas(table, elementos);
    }

    public void addFila(JTable table, Object[] fila)
    {
        DefaultTableModel tableModel = getDefaultTableModel(table);
        tableModel.addRow(fila);
    }

    public void addFilas(JTable table, Object[][] filas)
    {
        DefaultTableModel tableModel = getDefaultTableModel(table);

        for (int i = 0; i < filas.length; i++)
            tableModel.addRow(filas[i]);
    }

    public void vaciarTabla(JTable table)
    {
        DefaultTableModel tableModel = getDefaultTableModel(table);

        while (tableModel.getRowCount() > 0)
            tableModel.removeRow(0);
    }

    public Object[] getSelectedRowData(JTable table)
    {
        int selectedRow = table.getSelectedRow();

        if (selectedRow < 0)
            return null;

        Object[] data = new Object[table.getColumnCount()];

        for (int i = 0; i < table.getColumnCount(); i++)
            data[i] = table.getValueAt(selectedRow, i);

        return data;
    }

    public void selecionarFila(JTable table, int fila)
    {
        ListSelectionModel listSelectionModel = table.getSelectionModel();
        listSelectionModel.clearSelection();
        listSelectionModel.addSelectionInterval(fila, fila);
    }

    public void seleccionarFilas(JTable table, int[] indices)
    {
        for (int i : indices)
            table.getSelectionModel().addSelectionInterval(i, i);
    }

    public int getRowClicked(JTable table, double y)
    {
        return (int) (y / table.getRowHeight());
    }

    public void limpiarSeleccion(JTable table)
    {
        table.getSelectionModel().clearSelection();
    }

    public void selecionarUltimaFila(JTable table)
    {
        table.clearSelection();
        int lastIndex = table.getModel().getRowCount() - 1;
        table.getSelectionModel().setSelectionInterval(lastIndex, lastIndex);
    }

    public int[] getFilasSeleccionadas(JTable table)
    {
        return table.getSelectedRows();
    }

    public DefaultTableModel getDefaultTableModel(JTable table)
    {
        return (DefaultTableModel) table.getModel();
    }

    public Object[][] recortarFilaItems(Object[][] items, int row)
    {
        Object[][] newItems = new Object[items.length - 1][items[0].length];

        for (int i = 0; i < items.length - 1; i++)
            System.arraycopy(items[i + (i >= row ? 1 : 0)], 0, newItems[i], 0, items[i].length);

        return newItems;
    }

    public Object[][] recortarFilasItems(Object[][] items, int rowInicio, int rowFin)
    {
        Object[][] newItems = new Object[items.length - (rowFin - rowInicio) - 1][items[0].length];

        for (int i = 0; i < items.length - (rowFin - rowInicio) - 1; i++)
            System.arraycopy(items[i + (i >= rowInicio ? rowFin : 0)], 0, newItems[i], 0, items[i].length);

        return newItems;
    }

}
