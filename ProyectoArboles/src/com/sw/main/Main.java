package com.sw.main;

import com.sw.controller.VistaController;
import com.sw.model.Egresado;
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
            Egresado[] egresados = new Egresado[7];
            egresados[0] = new Egresado("Nicolás", "LIS", 20);
            egresados[1] = new Egresado("Nicolás", "FIQ", 20);
            egresados[2] = new Egresado("Nicolás", "LCC", 20);
            egresados[3] = new Egresado("Emmanuel", "LIS", 20);
            egresados[4] = new Egresado("Eusebio", "LIC", 20);
            egresados[5] = new Egresado("Carlos", "LCC", 20);
            egresados[6] = new Egresado("Antonio", "LIC", 20.67);

            Vista vista = new Vista();
            vista.setVisible(true);
            vista.setLocationRelativeTo(null);
            new VistaController(vista, egresados);
        });
    }

}
