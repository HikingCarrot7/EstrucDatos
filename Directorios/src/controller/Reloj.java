package controller;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author HikingCarrot7
 */
public class Reloj
{

    private SimpleStringProperty tiempoTranscurrido;
    private volatile boolean detenido;

    public Reloj(SimpleStringProperty tiempoTranscurrido)
    {
        this.tiempoTranscurrido = tiempoTranscurrido;
    }

    public void correrReloj()
    {
        long tiempo = 0;

        try
        {
            Thread.sleep(1);
            tiempoTranscurrido.set("Tiempo transcurrido: " + (tiempo++) + "ms");

        } catch (InterruptedException ex)
        {
            System.out.println(ex.getMessage());
        }

    }

    public void detener()
    {
        detenido = true;
    }

}
