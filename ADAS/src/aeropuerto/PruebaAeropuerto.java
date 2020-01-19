package aeropuerto;

public class PruebaAeropuerto
{
    public static void main(String[] args)
    {
        Aeropuerto aeropuerto = new Aeropuerto();

        aeropuerto.generarVuelos(20);

        aeropuerto.imprimirColaVuelos();

        aeropuerto.eliminarVueloAt(0);

        aeropuerto.imprimirColaVuelos();

        aeropuerto.eliminarSiguienteVuelo();

        aeropuerto.imprimirColaVuelos();

    }
}
