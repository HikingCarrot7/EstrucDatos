package util;

public class DequeEmptyException extends RuntimeException
{

    private static final long serialVersionUID = 8992910757665510364L;

    public DequeEmptyException()
    {
        super("La cola está vacía.");
    }

    public DequeEmptyException(String mensaje)
    {
        super(mensaje);
    }

}
