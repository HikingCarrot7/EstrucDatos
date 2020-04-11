package arbolesavl;

/**
 *
 * @author Nicolás
 */
public class PrubaArbolAVL
{

    public static void main(String[] args)
    {
        ArbolAVL<Integer> arbol = new ArbolAVL<>();
        arbol.insertar(34);
        arbol.insertar(23);
        arbol.insertar(83);
        arbol.insertar(1);
        arbol.insertar(100);
        arbol.insertar(3);
        arbol.insertar(45);
        arbol.insertar(1000);
        arbol.inorder();

    }

}
