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

            System.out.println(new PruebaPila().estaBalanceado("((((((((((((()"));

        } catch (ExpresionNoEvaluableException ex)
        {
            System.out.println(ex.getMessage());
        }

    }

    public boolean estaBalanceado(String cadena)
    {

        int contador = 0;
        String[] signos = cadena.split("");

        if (!expresionValida(signos))
            throw new ExpresionNoEvaluableException();

        Pila<String> pila = new Pila(cadena.length());

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
            }

            contador++;

        }

        return pila.isEmpty();

    }

    private boolean expresionValida(String[] parentesis)
    {

        if (parentesis[0].equals(")"))
            throw new PilaVaciaException();

        int contador = 0;

        for (String signo : parentesis)
            if (signo.equals("("))
                contador++;
            else if (signo.equals(")"))
            {

                if (contador > 10)
                    return false;

                contador = 0;

            }

        return true;

    }

}
