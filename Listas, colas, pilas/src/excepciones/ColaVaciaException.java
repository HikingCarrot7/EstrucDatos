package excepciones;

/**
 *
 * @author A15001169
 */
public class ColaVaciaException extends RuntimeException
{

    public ColaVaciaException()
    {
        super("La cola está vacía.");
    }

}
