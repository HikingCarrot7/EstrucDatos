package ada_2;

/**
 *
 * @author A15001169
 */
public class PruebaPila
{

    public static void main(String[] args)
    {

        try
        {

            System.out.println(new PruebaPila().estaBalanceado("()()()"));

        } catch (ExpresionNoEvaluableException ex)
        {
            System.out.println(ex.getMessage());
        }

    }

    public boolean estaBalanceado(String cadena)
    {

        String[] signos = cadena.split("");
        Pila<String> pila = new Pila();

        for (String signo : signos)
            try
            {

                if (signo.equals("("))
                    pila.push("(");

                else if (signo.equals(")"))
                    pila.pop();

            } catch (PilaVaciaException ex)
            {
                return false;

            } catch (PilaLLenaException ex)
            {
                throw new ExpresionNoEvaluableException();
            }

        return pila.isEmpty();

    }

}
