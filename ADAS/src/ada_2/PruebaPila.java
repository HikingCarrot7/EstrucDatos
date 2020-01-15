package ada_2;

import java.io.FileNotFoundException;

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

            System.out.println(new Fichero().llavesValidas());
            new Fichero().contarPalabras();

        } catch (ExpresionNoEvaluableException | FileNotFoundException ex)
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
