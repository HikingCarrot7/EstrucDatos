package com.sw.controller.util;

import java.awt.Component;
import java.util.Enumeration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;

/**
 *
 * @author HikingCarrot7
 */
public class SwingUtils
{

    public static void ejecutarTareaEnSegundoPlano(Runnable tarea)
    {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(tarea);
        executorService.shutdown();
    }

    public static void setPanelEnabled(JPanel panel, boolean isEnabled)
    {
        panel.setEnabled(isEnabled);

        Component[] components = panel.getComponents();

        for (Component component : components)
        {
            if (component instanceof JPanel)
                setPanelEnabled((JPanel) component, isEnabled);

            component.setEnabled(isEnabled);
        }
    }

    public static String getBotonSeleccionado(ButtonGroup buttonGroup)
    {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();)
        {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected())
                return button.getActionCommand();
        }

        return null;
    }
}
