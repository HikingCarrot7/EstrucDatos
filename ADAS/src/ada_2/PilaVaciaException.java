package ada_2;

/**
 *
 * @author A15001169
 */
public class PilaVaciaException extends RuntimeException
{

    public PilaVaciaException()
    {
        super("La pila está vacía.");
    }

}
