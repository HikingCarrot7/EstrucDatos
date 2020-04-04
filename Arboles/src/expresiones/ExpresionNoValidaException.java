package expresiones;

/**
 *
 * @author Nicolás
 */
public class ExpresionNoValidaException extends RuntimeException
{

    public ExpresionNoValidaException()
    {
        this("La expresión no es válida");
    }

    public ExpresionNoValidaException(String msg)
    {
        super(msg);
    }
}
