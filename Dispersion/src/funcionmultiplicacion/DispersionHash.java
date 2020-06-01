package funcionmultiplicacion;

/**
 *
 * @author HikingCarrot7
 */
public class DispersionHash
{

    private static final int M = 1024;
    private static final double R = 0.618034;

    public long transformaClave(String clave)
    {
        long d = 0;

        for (int j = 0; j < Math.min(clave.length(), 10); j++)
            d = d * 27 + clave.charAt(j);

        if (d < 0)
            d = -d;

        return d;
    }

    public int dispersion(long x)
    {
        double t;
        int v;

        t = R * x - Math.floor(R * x); // parte decimal
        v = (int) (M * t);

        return v;
    }

}
