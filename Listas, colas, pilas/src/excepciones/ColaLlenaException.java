package excepciones;

/**
 *
 * @author A15001169
 */
public class ColaLlenaException extends RuntimeException
{

    public ColaLlenaException()
    {
        super("La cola está llena.");
    }

}
