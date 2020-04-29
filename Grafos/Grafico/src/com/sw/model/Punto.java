package com.sw.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 *
 * @author HikingCarrot7
 */
public class Punto implements Movable
{

    private DoubleProperty xProperty;
    private DoubleProperty yProperty;

    public Punto(double x, double y)
    {
        this.xProperty = new SimpleDoubleProperty(x);
        this.yProperty = new SimpleDoubleProperty(y);
    }

    @Override
    public double getXDoubleValue()
    {
        return xProperty.doubleValue();
    }

    public void setX(double x)
    {
        if (xProperty == null)
            xProperty = new SimpleDoubleProperty(x);

        this.xProperty.set(x);
    }

    @Override
    public double getYDoubleValue()
    {
        return yProperty.doubleValue();
    }

    public void setY(double y)
    {
        if (yProperty == null)
            yProperty = new SimpleDoubleProperty(y);

        this.yProperty.set(y);
    }

    @Override
    public DoubleProperty getXProperty()
    {
        return xProperty;
    }

    @Override
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
