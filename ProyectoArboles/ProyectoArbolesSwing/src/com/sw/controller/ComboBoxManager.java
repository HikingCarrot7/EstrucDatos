package com.sw.controller;

import java.util.Arrays;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

/**
 *
 * @author Nicolás
 */
@SuppressWarnings("unchecked")
public class ComboBoxManager
{

    private static ComboBoxManager instance;

    private ComboBoxManager()
    {

    }

    public void establecerElementosComboBox(JComboBox<?> comboBox, Object[] elementos)
    {
        vaciarComboBox(comboBox);
        anadirElementosAlComboBox(comboBox, elementos);
    }

    public void establecerElementosComboBox(JComboBox<?> comboBox, List<?> elementos)
    {
        vaciarComboBox(comboBox);
        anadirElemento(comboBox, elementos);
    }

    public void anadirElementosAlComboBox(JComboBox<?> comboBox, Object[] elementos)
    {
        anadirElementosAlComboBox(comboBox, Arrays.asList(elementos));
    }

    public void anadirElementosAlComboBox(JComboBox<?> comboBox, List<?> elementos)
    {
        elementos.forEach(elemento -> anadirElemento(comboBox, elemento));
    }

    public void anadirElemento(JComboBox<?> comboBox, Object element)
    {
        DefaultComboBoxModel<Object> comboBoxModel = getDefaultComboBoxModel(comboBox);
        comboBoxModel.addElement(element);
    }

    public void vaciarComboBox(JComboBox<?> comboBox)
    {
        vaciarComboBox(getDefaultComboBoxModel(comboBox));
    }

    private void vaciarComboBox(DefaultComboBoxModel<Object> comboBoxModel)
    {
        comboBoxModel.removeAllElements();
    }

    public DefaultComboBoxModel<Object> getDefaultComboBoxModel(JComboBox<?> comboBox)
    {
        return (DefaultComboBoxModel<Object>) comboBox.getModel();
    }

    public static synchronized ComboBoxManager getInstance()
    {
        if (instance == null)
            instance = new ComboBoxManager();

        return instance;
    }
}
