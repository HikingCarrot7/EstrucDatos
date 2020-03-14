package com.sw.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 *
 * @author HikingCarrot7
 */
public class Punto
{

    private DoubleProperty xProperty;
    private DoubleProperty yProperty;

    public Punto(double x, double y)
    {
        this.xProperty = new SimpleDoubleProperty(x);
        this.yProperty = new SimpleDoubleProperty(y);
    }

    public double getX()
    {
        return xProperty.getValue();
    }

    public void setX(double x)
    {
        if (xProperty == null)
            xProperty = new SimpleDoubleProperty(x);

        this.xProperty.set(x);
    }

    public double getY()
    {
        return yProperty.getValue();
    }

    public void setY(double y)
    {
        if (yProperty == null)
            yProperty = new SimpleDoubleProperty(y);

        this.yProperty.set(y);
    }

    public DoubleProperty getXProperty()
    {
        return xProperty;
    }

    public DoubleProperty getYProperty()
    {
        return yProperty;
    }

    @Override
    public String toString()
    {
        return "Punto{" + "x=" + xProperty + ", y=" + yProperty + '}';
    }

}
