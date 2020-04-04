package expresiones;

import static expresiones.Operador.DIVISION;
import static expresiones.Operador.ESPACIO;
import static expresiones.Operador.EXPONENTE;
import static expresiones.Operador.MULTIPLICACION;
import static expresiones.Operador.RESTA;
import static expresiones.Operador.SUMA;
import static expresiones.Operador.esOperador;
import static expresiones.Operador.esParentesisDerecho;
import static expresiones.Operador.esParentesisIzquierdo;
import util.LinkedStack;
import util.PilaVaciaException;
import util.Stack;

/**
 *
 * @author Nicolás
 */
public class Validador
{

    public static boolean esExpresionInfija(String expresion)
    {
        if (expresion.endsWith(RESTA)
                || expresion.endsWith(SUMA)
                || expresion.endsWith(MULTIPLICACION)
                || expresion.endsWith(DIVISION)
                || expresion.endsWith(EXPONENTE))
            return false;
        if (expresion.startsWith(SUMA)
                || expresion.startsWith(RESTA)
                || expresion.startsWith(MULTIPLICACION)
                || expresion.startsWith(DIVISION)
                || expresion.endsWith(EXPONENTE))
            return false;

        if (!operadoresCorrectos(expresion))
            return false;

        return parentesisCorrectos(expresion);
    }

    private static boolean operadoresCorrectos(String expresion)
    {
        String[] tokens = expresion.replaceAll("\\(", "").replaceAll("\\)", "").replaceAll(ESPACIO, "").split("");

        for (int i = 0; i < tokens.length - 1; i++)
            if (esOperador(tokens[i]) && esOperador(tokens[i + 1]))
                return false;

        return true;
    }

    private static boolean parentesisCorrectos(String expresion)
    {
        Stack<String> pila = new LinkedStack<>();
        String[] tokens = expresion.split("");

        for (String token : tokens)
            if (esParentesisIzquierdo(token))
                pila.push(token);

            else if (esParentesisDerecho(token))
                try
                {
                    pila.pop();

                } catch (PilaVaciaException ex)
                {
                    return false;
                }

        return pila.isEmpty();
    }

}
