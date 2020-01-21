package ada_3;

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

}
