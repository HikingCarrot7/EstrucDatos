package com.sw.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.EventObject;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.event.CellEditorListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Nicolás
 */
public interface UIConstants
{

    public Color LIST_DEFAULT_BACKGROUND = (Color) UIManager.get("List.background");
    public Color COMBOBOX_DEFAULT_BACKGROUND = (Color) UIManager.get("ComboBox.background");
    public Color TABLE_DEFAULT_BACKGROUND = (Color) UIManager.get("Table.background");

    public Color LIST_SELECTION_BACKGROUND = (Color) UIManager.get("List.selectionBackground");
    public Color COMBOBOX_SELECTION_BACKGROUND = (Color) UIManager.get("ComboBox.selectionBackground");
    public Color TABLE_SELECTION_BACKGROUND = (Color) UIManager.get("Table.selectionBackground");

    public Border LIST_HIGHLIGHT_BORDER = (Border) UIManager.get("List.focusCellHighlightBorder");
    public Border TABLE_HIGHLIGHT_BORDER = (Border) UIManager.get("List.focusCellHighlightBorder");

    public TableCellRenderer MY_TABLE_HEADER_RENDERER = (table, value, isSelected, hasFocus, row, column) ->
    {
        final Color BACKGROUND_COLOR = new Color(70, 160, 190);

        JLabel label = new JLabel(String.valueOf(value));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Tahoma", Font.BOLD, 13));
        label.setSize(30, label.getWidth());
        label.setPreferredSize(new Dimension(6, label.getWidth()));

        label.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.WHITE));
        label.setOpaque(true);
        label.setBackground(BACKGROUND_COLOR);
        label.setForeground(Color.white);

        return label;
    };

    public TableCellRenderer MY_TABLE_CELL_RENDERER = new DefaultTableCellRenderer()
    {
        @Override public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
        {
            final Color BACKGROUND_COLOR = new Color(200, 228, 235);
            final Color SELECTED_BACKGROUND_COLOR = BACKGROUND_COLOR.darker();

            JLabel label = new JLabel(String.valueOf(value));
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setFont(new Font("Tahoma", Font.PLAIN, 13));
            label.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.WHITE));
            label.setOpaque(true);

            if (!isSelected)
            {
                label.setBackground(row % 2 == 0 ? BACKGROUND_COLOR : Color.WHITE);
                label.setForeground(Color.BLACK);

            } else
            {
                label.setBackground(SELECTED_BACKGROUND_COLOR);
                label.setForeground(Color.WHITE);
            }

            return label;
        }
    };

    public DefaultCellEditor COLUMNA_NO_EDITABLE = new DefaultCellEditor(new JTextField())
    {
        @Override public boolean isCellEditable(EventObject anEvent)
        {
            return false;
        }
    };

    public TableCellEditor TABLA_NO_EDITABLE = new TableCellEditor()
    {
        @Override public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column)
        {
            return null;
        }

        @Override public Object getCellEditorValue()
        {
            return null;
        }

        @Override public boolean isCellEditable(EventObject e)
        {
            return false;
        }

        @Override public boolean shouldSelectCell(EventObject anEvent)
        {
            return true;
        }

        @Override public boolean stopCellEditing()
        {
            return false;
        }

        @Override public void cancelCellEditing()
        {
        }

        @Override public void addCellEditorListener(CellEditorListener l)
        {
        }

        @Override public void removeCellEditorListener(CellEditorListener l)
        {
        }
    };

    public TableCellRenderer COLUMNA_TEXTO_CENTRADO = (table, value, isSelected, hasFocus, row, column) ->
    {
        JLabel label = new JLabel(String.valueOf(value));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setOpaque(true);
        label.setBackground(isSelected ? TABLE_SELECTION_BACKGROUND : TABLE_DEFAULT_BACKGROUND);

        if (hasFocus)
            label.setBorder(TABLE_HIGHLIGHT_BORDER);

        return label;
    };

}
