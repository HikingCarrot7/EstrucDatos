package com.sw.view;

import java.awt.Color;
import java.util.EventObject;
import javax.swing.DefaultCellEditor;
import javax.swing.Icon;
import javax.swing.ImageIcon;
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

    public Color LIST_DEFAULT_BACKGROUND = (Color) UIManager.get("List.background");
    public Color COMBOBOX_DEFAULT_BACKGROUND = (Color) UIManager.get("ComboBox.background");
    public Color TABLE_DEFAULT_BACKGROUND = (Color) UIManager.get("Table.background");

    public Color LIST_SELECTION_BACKGROUND = (Color) UIManager.get("List.selectionBackground");
    public Color COMBOBOX_SELECTION_BACKGROUND = (Color) UIManager.get("ComboBox.selectionBackground");
    public Color TABLE_SELECTION_BACKGROUND = (Color) UIManager.get("Table.selectionBackground");

    public Border LIST_HIGHLIGHT_BORDER = (Border) UIManager.get("List.focusCellHighlightBorder");
    public Border TABLE_HIGHLIGHT_BORDER = (Border) UIManager.get("List.focusCellHighlightBorder");

    public Icon EDITAR_ICON = new ImageIcon(UIConstants.class.getClass().getResource("/com/sw/img/editar.png"));
    public Icon DELETE_ICON = new ImageIcon(UIConstants.class.getClass().getResource("/com/sw/img/delete.png"));

    public DefaultCellEditor COLUMNA_NO_EDITABLE = new DefaultCellEditor(new JTextField())
    {
        @Override
        public boolean isCellEditable(EventObject anEvent)
        {
            return false;
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
