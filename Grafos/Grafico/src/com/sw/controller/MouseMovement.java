package com.sw.controller;

import com.sw.model.Punto;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author HikingCarrot7
 */
public class MouseMovement implements EventHandler<MouseEvent>
{

    private final Punto CURSOR;

    public MouseMovement()
    {
        CURSOR = new Punto(0, 0);
    }

    @Override
    public void handle(MouseEvent e)
    {
        CURSOR.setX(e.getSceneX());
        CURSOR.setY(e.getSceneY());
    }

    public void ponerCoordenadasCursor(double x, double y)
    {
        CURSOR.setX(x);
        CURSOR.setY(y);
    }

    public Punto getCursor()
    {
        return CURSOR;
    }

}
