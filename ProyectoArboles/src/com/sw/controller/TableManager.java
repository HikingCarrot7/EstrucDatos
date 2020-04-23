package com.sw.controller;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Nicolás
 */
public class TableManager
{

    private static TableManager instance;

    private TableManager()
    {

    }

    public void quitarCabeceraTabla(JTable table)
    {
        table.getTableHeader().setUI(null);
    }

    public void initTable(JTable table)
    {
        table.setRowHeight(25);
    }

    public void establecerFilas(JTable table, Object[][] elementos)
    {
        vaciarTabla(table);
        anadirFilas(table, elementos);
    }

    public void anadirFila(JTable table, Object[] fila)
    {
        DefaultTableModel tableModel = getDefaultTableModel(table);
        tableModel.addRow(fila);
    }

    public void anadirFilas(JTable table, Object[][] filas)
    {
        DefaultTableModel tableModel = getDefaultTableModel(table);

        for (Object[] fila : filas)
            tableModel.addRow(fila);
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

    public int getRowClicked(JTable table, double y)
    {
        return (int) (y / table.getRowHeight());
    }

    public void limpiarSeleccion(JTable table)
    {
        table.getSelectionModel().clearSelection();
    }

    public DefaultTableModel getDefaultTableModel(JTable table)
    {
        return (DefaultTableModel) table.getModel();
    }

    public static synchronized TableManager getInstance()
    {
        if (instance == null)
            instance = new TableManager();

        return instance;
    }
}
