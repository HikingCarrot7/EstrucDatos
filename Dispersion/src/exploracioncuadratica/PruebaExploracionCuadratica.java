package exploracioncuadratica;

/**
 *
 * @author HikingCarrot7
 */
public class PruebaExploracionCuadratica
{

    public static void main(String[] args)
    {
        TablaDispersa tablaDispersa = new TablaDispersa();
        tablaDispersa.insertar(new CasaRural("12345", "5", "Mérida", 5, 1400));
        tablaDispersa.insertar(new CasaRural("4334543", "2", "Mérida", 2, 2000));
        tablaDispersa.insertar(new CasaRural("54355", "7", "Mérida", 8, 5000));
        tablaDispersa.insertar(new CasaRural("1654645", "1", "Mérida", 4, 2300));
        tablaDispersa.insertar(new CasaRural("134534", "3", "Mérida", 5, 1700));

        tablaDispersa.buscar("12345").muestra();
        tablaDispersa.buscar("134534").muestra();
        tablaDispersa.buscar("54355").muestra();
    }
}
