package exploracioncuadratica;

/**
 *
 * @author HikingCarrot7
 */
public class TablaDispersa
{

    private static final int TAM_TABLA = 101;
    private int numElementos;
    private double factorCarga;
    private CasaRural[] tabla;

    public TablaDispersa()
    {
        tabla = new CasaRural[TAM_TABLA];

        for (int j = 0; j < TAM_TABLA; j++)
            tabla[j] = null;

        numElementos = 0;
        factorCarga = 0.0;
    }

    private int direccion(String clave)
    {
        int i = 0, p;
        long d = transformaCadena(clave);

        // aplica aritmética modular para obtener dirección base
        p = (int) (d % TAM_TABLA);

        while (tabla[p] != null && !tabla[p].getCodigo().equals(clave))
        {
            i++;
            p += i * i;
            p %= TAM_TABLA; // considera el array como circular
        }

        return p;
    }

    private long transformaCadena(String c)
    {
        long d = 0;

        for (int j = 0; j < Math.min(10, c.length()); j++)
            d = d * 27 + c.charAt(j);

        if (d < 0)
            d = -d;

        return d;
    }

    public void insertar(CasaRural r)
    {
        int posicion;
        posicion = direccion(r.getCodigo());
        tabla[posicion] = r;
        numElementos++;

        factorCarga = (double) (numElementos) / TAM_TABLA;

        if (factorCarga > 0.5)
            System.out.println("\n!! Factor de carga supera el 50%.!!"
                    + " Conviene aumentar el tamaño.");
    }

    public CasaRural buscar(String clave)
    {
        CasaRural pr;
        int posicion = direccion(clave);
        pr = tabla[posicion];

        if (pr != null)
            if (!pr.esAlta())
                pr = null;

        return pr;
    }

    public void eliminar(String clave)
    {
        int posicion = direccion(clave);

        if (tabla[posicion] != null)
            tabla[posicion].setAlta(false);
    }

}
