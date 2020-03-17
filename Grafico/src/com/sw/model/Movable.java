package com.sw.model;

import javafx.beans.property.DoubleProperty;

/**
 *
 * @author HikingCarrot7
 */
public interface Movable
{

    public DoubleProperty getXProperty();

    public DoubleProperty getYProperty();

    public double getXDoubleValue();

    public double getYDoubleValue();
}
