package exploracioncuadratica;

/**
 *
 * @author HikingCarrot7
 */
public class CasaRural
{

    private String codigo;
    private String poblacion;
    private String direccion;
    private int numHabitacion;
    private double precio;
    private boolean esAlta;

    public CasaRural(String codigo, String poblacion, String direccion, int numHabitacion, double precio)
    {
        this.codigo = codigo;
        this.poblacion = poblacion;
        this.direccion = direccion;
        this.numHabitacion = numHabitacion;
        this.precio = precio;
        this.esAlta = true;
    }

    public boolean esAlta()
    {
        return esAlta;
    }

    public void setAlta(boolean esAlta)
    {
        this.esAlta = esAlta;
    }

    public String getCodigo()
    {
        return codigo;
    }

    public void muestra()
    {
        System.out.println("\nCasa Rural " + codigo);
        System.out.println("Población: " + poblacion);
        System.out.println("Dirección: " + direccion);
        System.out.println("Precio por día: " + precio);
    }

}
