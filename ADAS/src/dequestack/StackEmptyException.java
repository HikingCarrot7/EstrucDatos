package dequestack;

public class StackEmptyException extends RuntimeException
{

    private static final long serialVersionUID = 7271455034964572430L;

    public StackEmptyException()
    {
        super("La pila está vacía.");
    }
}
