package excepciones;

/**
 *
 * @author HikingC7
 */
public class ListaVaciaException extends RuntimeException
{

    public ListaVaciaException()
    {
        super("La lista está vacía.");
    }

    public ListaVaciaException(String mensaje)
    {
        super(mensaje);
    }

}
