package com.sw.controller;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author HikingCarrot7
 */
public class Alerta
{

    public static void mostrarError(Component parentComponent, String text)
    {
        JOptionPane.showMessageDialog(parentComponent, text, "Error!", JOptionPane.ERROR_MESSAGE);
    }

    public static boolean mostrarConfirmacion(Component parentComponent, String title, String text)
    {
        return JOptionPane.showConfirmDialog(parentComponent, text, title, JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION;
    }

    public static int mostrarConfirmacion(Component parentComponent, String title, String text, Object[] options)
    {
        return JOptionPane.showOptionDialog(parentComponent, text, title, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);
    }

    public static void mostrarMensaje(Component parentComponent, String title, String text)
    {
        JOptionPane.showMessageDialog(parentComponent, text, title, JOptionPane.INFORMATION_MESSAGE);
    }

}
