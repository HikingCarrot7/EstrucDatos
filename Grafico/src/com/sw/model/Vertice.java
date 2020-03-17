package com.sw.model;

import com.sw.controller.Utilidades;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.effect.InnerShadow;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.Paint;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;

/**
 *
 * @author HikingCarrot7
 */
public class Vertice extends Circle implements Movable
{

    public static final double DEFAULT_RADIO = 30;
    public static int n_vertice;
    private Label nombre;
    private boolean selected;

    public static final Paint DEFAULT_SKIN = new RadialGradient(0, 0, 0.2, 0.3, 0.5, true, CycleMethod.NO_CYCLE, new Stop[]
    {
        new Stop(0, Color.rgb(250, 250, 255)),
        new Stop(1, Color.ORANGE)
    });

    public static final Paint SELECTED_SKIN = new RadialGradient(0, 0, 0.2, 0.3, 0.5, true, CycleMethod.NO_CYCLE, new Stop[]
    {
        new Stop(0, Color.rgb(250, 250, 255)),
        new Stop(1, Color.YELLOW)
    });

    public static final Paint HOVER_SKIN = new RadialGradient(0, 0, 0.2, 0.3, 0.5, true, CycleMethod.NO_CYCLE, new Stop[]
    {
        new Stop(0, Color.rgb(250, 250, 255)),
        new Stop(1, Color.BISQUE)
    });

    public Vertice(double centerX, double centerY, double radio)
    {
        setFill(DEFAULT_SKIN);
        setEffect(new InnerShadow(7, Color.ORANGE.darker().darker()));
        setStroke(Color.BLACK);
        setStrokeWidth(3);
        setCenterX(centerX);
        setCenterY(centerY);
        setRadius(radio);
        setCursor(Cursor.HAND);
        nombre = new Label(String.valueOf(++n_vertice));
        nombre.setTranslateX(centerX - Utilidades.getFontWidth(String.valueOf(n_vertice), nombre.getFont()) / 2);
        nombre.setTranslateY(centerY - Utilidades.getFontHeight(nombre.getFont()) / 2);
    }

    public Vertice(double centerX, double centerY)
    {
        this(centerX, centerY, DEFAULT_RADIO);
    }

    public void setSelected(boolean selected)
    {
        this.selected = selected;
        setFill(selected ? SELECTED_SKIN : DEFAULT_SKIN);
    }

    public Label getNombre()
    {
        return nombre;
    }

    public boolean isSelected()
    {
        return selected;
    }

    @Override
    public DoubleProperty getXProperty()
    {
        return centerXProperty();
    }

    @Override
    public DoubleProperty getYProperty()
    {
        return centerYProperty();
    }

    @Override
    public double getXDoubleValue()
    {
        return getXProperty().doubleValue();
    }

    @Override
    public double getYDoubleValue()
    {
        return getYProperty().doubleValue();
    }

}
