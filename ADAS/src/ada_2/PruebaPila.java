package ada_2;

import java.util.Scanner;

/**
 *
 * @author A15001169
 */
public class PruebaPila
{

    public static void main(String[] args)
    {

        String expresion = new Scanner(System.in).nextLine();

        try
        {

            System.out.println("La expresión" + (new PruebaPila().estaBalanceado(expresion) ? "" : " no") + " está balanceada.");

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

            } catch (PilaLlenaException ex)
            {
                throw new ExpresionNoEvaluableException();
            }

        return pila.isEmpty();

    }

}
