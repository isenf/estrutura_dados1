/**
 * nodo de com tipo genérico
 */
public class NodeG<T>{
    protected T element;
    protected NodeG<T> next;

    /** construtor */
    public NodeG(T s, NodeG<T> n){
        element = s;
        next = n;
    }

    public NodeG(){
        this(null, null);
    }

    /** retorna o elemento do nodo */
    public T getElement(){
        return element;
    }

    /** retorna o próximo elemento */
    public NodeG<T> getNext(){
        return next;
    }

    /** define o elemento do nodo */
    public void setElement(T newValue){
        element = newValue;
    }

    /** define o próximo elemento */
    public void setNext(NodeG<T> newNext){
        next = newNext;
    }
}