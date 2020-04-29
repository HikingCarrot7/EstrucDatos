package com.sw.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Cursor;
import javafx.scene.effect.InnerShadow;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.PathElement;
import javafx.scene.shape.Shape;

/**
 *
 * @author HikingCarrot7
 */
public class ShapeFactory
{

    public static ObservableList<PathElement> createTriangle(double x, double y, double length)
    {
        ObservableList<PathElement> elementos = FXCollections.observableArrayList();

        double offset = Math.sqrt(Math.pow(length, 2) - Math.pow((length / 2), 2));

        double[] puntosX =
        {
            x, x - offset, x - offset
        };

        double[] puntosY =
        {
            y, y + offset, y - offset
        };

        MoveTo moveTo = new MoveTo();
        moveTo.setX(x - offset);
        moveTo.setY(y - offset);
        elementos.add(moveTo);

        for (int i = 0; i < puntosX.length; i++)
        {
            LineTo lineTo = new LineTo();
            lineTo.setX(puntosX[i]);
            lineTo.setY(puntosY[i]);
            elementos.add(lineTo);
        }

        return elementos;
    }

    public static Shape createTriangle(double x, double y, double length, double angle)
    {
        Path path = new Path();
        double offset = Math.sqrt(Math.pow(length, 2) - Math.pow((length / 2), 2));

        double[] puntosX =
        {
            x, x - offset, x - offset
        };

        double[] puntosY =
        {
            y, y + offset, y - offset
        };

        MoveTo moveTo = new MoveTo();
        moveTo.setX(x - offset);
        moveTo.setY(y - offset);
        path.getElements().add(moveTo);

        for (int i = 0; i < puntosX.length; i++)
        {
            LineTo lineTo = new LineTo();
            lineTo.setX(puntosX[i]);
            lineTo.setY(puntosY[i]);
            path.getElements().add(lineTo);
        }

        path.setRotate(angle);
        return path;
    }

    public static Shape createRow(Recta recta, double length)
    {
        Path path = new Path();

        MoveTo moveTo = new MoveTo();
        moveTo.setX(recta.getPuntoInicial().getXDoubleValue());
        moveTo.setY(recta.getPuntoInicial().getYDoubleValue());
        Path triangle = (Path) createTriangle(recta.getPuntoFinal().getXDoubleValue(), recta.getPuntoFinal().getYDoubleValue(), length);

        LineTo line = new LineTo(recta.getPuntoFinal().getXDoubleValue(), recta.getPuntoFinal().getYDoubleValue());

        path.getElements().add(moveTo);
        path.getElements().add(line);

        double offset = Math.sqrt(Math.pow(length, 2) - Math.pow(length / 2, 2));

        triangle.setTranslateX(triangle.getTranslateX() + offset / 2);
        triangle.setFill(Color.BLACK);
        triangle.setRotate(GeometriaUtils.obtenerAnguloGradosInclinacionRecta(GeometriaUtils.obtenerEcuacionRecta(recta)));

        return (Path) Path.union(path, triangle);
    }

    public static Circle createCircle(Punto puntoInicial, double radius, Paint paint)
    {
        final Circle circle = new Circle(radius, paint);

        circle.setCenterX(puntoInicial.getXDoubleValue());
        circle.setCenterY(puntoInicial.getYDoubleValue());

        //add a shadow effect
        circle.setEffect(new InnerShadow(7, Color.ORANGE.darker().darker()));

        //change a cursor when it is over circle
        circle.setCursor(Cursor.HAND);

        return circle;
    }

}
