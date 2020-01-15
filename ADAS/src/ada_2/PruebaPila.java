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

            System.out.println(new PruebaPila().estaBalanceado("()"));

        } catch (ExpresionNoEvaluableException ex)
        {
            System.out.println(ex.getMessage());
        }

    }

    public boolean estaBalanceado(String cadena)
    {

        int contador = 0;
        String[] signos = cadena.split("");
        Pila<String> pila = new Pila();

        while (contador < signos.length)
        {

            try
            {

                if (signos[contador].equals("("))
                    pila.push("(");
                else if (signos[contador].equals(")"))
                    pila.pop();

            } catch (PilaVaciaException ex)
            {
                return false;

            } catch (PilaLLenaException ex)
            {
                throw new ExpresionNoEvaluableException();
            }

            contador++;

        }

        return pila.isEmpty();

    }

}
