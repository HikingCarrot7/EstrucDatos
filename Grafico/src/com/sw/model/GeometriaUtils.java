package com.sw.model;

/**
 *
 * @author HikingCarrot7
 */
public class GeometriaUtils
{

    public static EcuacionGeneralLinea obtenerEcuacionRecta(Recta linea)
    {
        if (esVertical(linea))
            return new EcuacionGeneralLinea(1, 0, -linea.getPuntoInicial().getX());

        return new EcuacionGeneralLinea(obtenerPendiente(linea), -1, -obtenerPendiente(linea) * linea.getPuntoInicial().getX() + linea.getPuntoInicial().getY());
    }

    public static EcuacionGeneralLinea obtenerEcuacionRecta(Punto p1, double pendiente)
    {
        return new EcuacionGeneralLinea(pendiente, -1, -pendiente * p1.getX() + p1.getY());
    }

    public static EcuacionGeneralLinea obtenerEcuacionDeLaRectaPerpendicular(Recta linea)
    {
        double nuevaPendiente = -obtenerPendiente(linea);
        Punto puntoMedio = obtenerPuntoMedio(linea.getPuntoInicial(), linea.getPuntoFinal());
        return obtenerEcuacionRecta(puntoMedio, nuevaPendiente);
    }

    public static EcuacionGeneralLinea obtenerEcuacionDeLaRectaParalela(Recta linea, double distancia)
    {
        EcuacionGeneralLinea ecuacionLinea = GeometriaUtils.obtenerEcuacionRecta(linea);
        double terminoIndependiente = distancia * Math.sqrt(Math.pow(obtenerPendiente(linea), 2) + 1) + ecuacionLinea.getTerminoIndependiente();
        return new EcuacionGeneralLinea(ecuacionLinea.getTerminoX(), ecuacionLinea.getTerminoY(), terminoIndependiente);
    }

    public static double distanciaEntreDosPuntos(Punto p1, Punto p2)
    {
        return Math.sqrt(Math.pow((p2.getX() - p1.getX()), 2) + Math.pow((p2.getY() - p1.getY()), 2));
    }

    public static double distanciaEntreDosPuntos(double inicialX, double inicialY, double finalX, double finalY)
    {
        return distanciaEntreDosPuntos(new Punto(inicialX, inicialY), new Punto(finalX, finalY));
    }

    public static double distanciaEntreDosRectasParalelas(Recta linea1, Recta linea2)
    {
        if (sonParalelas(linea1, linea2))
            return (GeometriaUtils.obtenerEcuacionRecta(linea2).getTerminoIndependiente() - GeometriaUtils.obtenerEcuacionRecta(linea1).getTerminoIndependiente()) / (Math.sqrt(Math.pow(obtenerPendiente(linea2), 2) + 1));

        return 0;
    }

    public static Punto interseccionEntreDosRectas(Recta linea1, Recta linea2)
    {
        return GeometriaUtils.interseccionEntreDosRectas(GeometriaUtils.obtenerEcuacionRecta(linea1), GeometriaUtils.obtenerEcuacionRecta(linea2));
    }

    public static Punto interseccionEntreDosRectas(EcuacionGeneralLinea ecuacion1, EcuacionGeneralLinea ecuacion2)
    {
        double determinanteSistema = AlgebraLinealUtils.getDeterminante2x2(ecuacion1.getTerminoX(), ecuacion1.getTerminoY(), ecuacion2.getTerminoX(), ecuacion2.getTerminoY());
        double determinanteX = AlgebraLinealUtils.getDeterminante2x2(ecuacion1.getTerminoIndependiente() * -1, ecuacion1.getTerminoY(), ecuacion2.getTerminoIndependiente() * -1, ecuacion2.getTerminoY());
        double determinanteY = AlgebraLinealUtils.getDeterminante2x2(ecuacion1.getTerminoX(), ecuacion1.getTerminoIndependiente() * -1, ecuacion2.getTerminoX(), ecuacion2.getTerminoIndependiente() * -1);

        return new Punto(determinanteX / determinanteSistema, determinanteY / determinanteSistema);
    }

    public static double obtenerAnguloGradosEntreDosRectas(EcuacionGeneralLinea recta1, EcuacionGeneralLinea recta2)
    {
        return Math.toDegrees(obtenerAnguloRadianesEntreDosRectas(recta1, recta2));
    }

    public static double obtenerAnguloRadianesEntreDosRectas(EcuacionGeneralLinea recta1, EcuacionGeneralLinea recta2)
    {
        if (sonParalelas(recta1, recta2))
            return 0;

        else if (esVertical(recta2))
            return Math.PI / 2;

        return Math.atan(obtenerPendiente(recta1) - obtenerPendiente(recta2) / (obtenerPendiente(recta1) * obtenerPendiente(recta2) + 1));
    }

    public static double obtenerAnguloRadianesInclinacionRecta(EcuacionGeneralLinea ecuaRecta)
    {
        return Math.atan(obtenerPendiente(ecuaRecta));
    }

    public static double obtenerAnguloGradosInclinacionRecta(EcuacionGeneralLinea ecuaRecta)
    {
        return Math.toDegrees(obtenerAnguloRadianesInclinacionRecta(ecuaRecta));
    }

    public static Punto obtenerPuntoMedio(Punto p1, Punto p2)
    {
        return new Punto((p1.getX() + p2.getX()) / 2, (p1.getY() + p2.getY()) / 2);
    }

    public static double obtenerPendiente(Recta linea)
    {
        return esVertical(linea) ? Double.POSITIVE_INFINITY : (linea.getPuntoFinal().getY() - linea.getPuntoInicial().getY()) / (linea.getPuntoFinal().getX() - linea.getPuntoInicial().getX());
    }

    public static double obtenerPendiente(EcuacionGeneralLinea linea)
    {
        return -linea.getTerminoX() / linea.getTerminoY();
    }

    public static boolean esVertical(Recta linea)
    {
        return linea.getPuntoFinal().getX() - linea.getPuntoInicial().getX() == 0;
    }

    public static boolean esVertical(EcuacionGeneralLinea ecuacionRecta)
    {
        return ecuacionRecta.getTerminoY() == 0;
    }

    public static boolean sonParalelas(Recta linea1, Recta linea2)
    {
        return obtenerPendiente(linea1) == obtenerPendiente(linea2);
    }

    public static boolean sonParalelas(EcuacionGeneralLinea e1, EcuacionGeneralLinea e2)
    {
        return obtenerPendiente(e1) == obtenerPendiente(e2);
    }

}
