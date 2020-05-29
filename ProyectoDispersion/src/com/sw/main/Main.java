package com.sw.main;

import com.sw.view.VistaDatosUsuario;
import java.awt.EventQueue;

/**
 *
 * @author HikingCarrot7
 */
public class Main
{

    public static void main(String[] args)
    {
        EventQueue.invokeLater(() ->
        {
            VistaDatosUsuario vista = new VistaDatosUsuario();
            vista.setVisible(true);
            vista.setLocationRelativeTo(null);
        });
    }
}
