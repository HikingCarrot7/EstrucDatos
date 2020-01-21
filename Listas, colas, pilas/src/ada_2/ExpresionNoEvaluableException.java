package ada_2;

/**
 *
 * @author A15001169
 */
public class ExpresionNoEvaluableException extends RuntimeException
{

    public ExpresionNoEvaluableException()
    {
        super("La expresión no se puede evaluar.");
    }

}
