package expresiones;

import arbolbusquedabinaria.ArbolBinario;
import arbolbusquedabinaria.NodoABB;
import static expresiones.Conversor.deInfijoAPostfijo;
import static expresiones.Operador.ESPACIO;
import static expresiones.Operador.esOperador;
import static expresiones.Validador.esExpresionInfija;
import util.LinkedStack;
import util.PilaVaciaException;
import util.Stack;

/**
 *
 * @author Nicolás
 */
public class ArbolExpresiones extends ArbolBinario<String>
{

    public ArbolExpresiones(String expresion)
    {
        construirArbolExpresiones(expresion);
    }

    private void construirArbolExpresiones(String expresion) throws ExpresionNoValidaException
    {
        if (!esExpresionInfija(expresion))
            throw new ExpresionNoValidaException();

        Stack<NodoABB<String>> pila = new LinkedStack<>();
        String expresionPostfija = deInfijoAPostfijo(expresion);
        String[] tokens = expresionPostfija.split(ESPACIO);

        for (String token : tokens)
        {
            NodoABB<String> nodo = new NodoABB<>(token);

            if (esOperador(token))
            {
                nodo.setDer(pila.pop());
                nodo.setIzq(pila.pop());
            }

            pila.push(nodo);
        }

        try
        {
            setRaiz(pila.pop());

        } catch (PilaVaciaException e)
        {
            throw new ExpresionNoValidaException();
        }

        System.out.println("La expresión está bien construida.");
    }

}
