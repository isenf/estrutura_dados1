/**
 * nodo de uma lista simplesmente encadeada com tipo genérico
 */
public class Node<T>{
    protected T element;
    protected Node<T> next;

    /** construtor */
    public Node(T s, Node<T> n){
        element = s;
        next = n;
    }

    /** retorna o elemento do nodo */
    public T getElement(){
        return element;
    }

    /** retorna o próximo elemento */
    public Node<T> getNext(){
        return next;
    }

    /** define o elemento do nodo */
    public void setElement(T newValue){
        element = newValue;
    }

    /** define o próximo elemento */
    public void setNext(Node<T> newNext){
        next = newNext;
    }
}