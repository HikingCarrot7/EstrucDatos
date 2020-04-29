package com.sw.model;

/**
 *
 * @author HikingCarrot7
 */
public class EcuacionGeneralLinea
{

    private double terminoX;
    private double terminoY;
    private double terminoIndependiente;

    public EcuacionGeneralLinea(double terminoX, double terminoY, double terminoIndependiente)
    {
        this.terminoX = terminoX;
        this.terminoY = terminoY;
        this.terminoIndependiente = terminoIndependiente;
    }

    public double getTerminoX()
    {
        return terminoX;
    }

    public double getTerminoY()
    {
        return terminoY;
    }

    public double getTerminoIndependiente()
    {
        return terminoIndependiente;
    }

    public void setTerminoX(double terminoX)
    {
        this.terminoX = terminoX;
    }

    public void setTerminoY(double terminoY)
    {
        this.terminoY = terminoY;
    }

    public void setTerminoIndependiente(double terminoIndependiente)
    {
        this.terminoIndependiente = terminoIndependiente;
    }

    @Override
    public String toString()
    {
        return "EcuacionGeneralLinea{" + "x=" + terminoX + ", y=" + terminoY + ", c=" + terminoIndependiente + '}';
    }

}
