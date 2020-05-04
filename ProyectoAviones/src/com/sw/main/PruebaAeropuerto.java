package com.sw.main;

import com.sw.controller.Controlador;
import com.sw.model.Aeropuerto;
import com.sw.view.VistaPrincipal;
import java.awt.EventQueue;

public class PruebaAeropuerto
{

    public static void main(String[] args)
    {
        EventQueue.invokeLater(() ->
        {
            VistaPrincipal vista = new VistaPrincipal();
            vista.setLocationRelativeTo(null);
            vista.setVisible(true);
            new Controlador(vista, new Aeropuerto());
        });
    }

}
