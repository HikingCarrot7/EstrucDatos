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

    public Object[][] getTableItems(JTable table)
    {
        Object[][] items = new Object[table.getRowCount()][table.getColumnCount()];

        for (int i = 0; i < items.length; i++)
            for (int j = 0; j < items[i].length; j++)
                items[i][j] = table.getValueAt(i, j);

        return items;
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

    public static synchronized TableManager getInstance()
    {
        if (instance == null)
            instance = new TableManager();

        return instance;
    }
}
