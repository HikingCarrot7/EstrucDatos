package com.sw.main;

import com.sw.view.Vista;
import java.awt.EventQueue;

/**
 *
 * @author Nicolás
 */
public class Main
{

    public static void main(String[] args)
    {
        EventQueue.invokeLater(() ->
        {
            Vista vista = new Vista();
            vista.setLocationRelativeTo(null);
            vista.setVisible(true);
        });
    }

}
