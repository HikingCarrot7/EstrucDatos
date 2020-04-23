package com.sw.view;

import java.awt.Color;
import java.util.EventObject;
import javax.swing.DefaultCellEditor;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Nicolás
 */
public interface UIConstants
{

    public static final Color LIST_DEFAULT_BACKGROUND = (Color) UIManager.get("List.background");
    public static final Color COMBOBOX_DEFAULT_BACKGROUND = (Color) UIManager.get("ComboBox.background");
    public static final Color TABLE_DEFAULT_BACKGROUND = (Color) UIManager.get("Table.background");

    public static final Color LIST_SELECTION_BACKGROUND = (Color) UIManager.get("List.selectionBackground");
    public static final Color COMBOBOX_SELECTION_BACKGROUND = (Color) UIManager.get("ComboBox.selectionBackground");
    public static final Color TABLE_SELECTION_BACKGROUND = (Color) UIManager.get("Table.selectionBackground");

    public static final Border LIST_HIGHLIGHT_BORDER = (Border) UIManager.get("List.focusCellHighlightBorder");
    public static final Border TABLE_HIGHLIGHT_BORDER = (Border) UIManager.get("Table.focusCellHighlightBorder");

    public static final DefaultCellEditor COLUMNA_NO_EDITABLE = new DefaultCellEditor(new JTextField())
    {
        @Override
        public boolean isCellEditable(EventObject anEvent)
        {
            return false;
        }
    };

    public static final TableCellRenderer COLUMNA_TEXTO_CENTRADO = (table, value, isSelected, hasFocus, row, column) ->
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
