/** Nodo de uma lista duplamente encadeada de strings */
public class DNode <T> {

    protected T element; // String armazenada pelo nodo
    protected DNode<T> next, prev; // Ponteiros para o nodo seguinte e o anterior 

    /**
     * Construtor que cria um nodo com os campos fornecidos
     */

    public DNode(T e, DNode<T> p, DNode<T> n) {
        element = e;
        prev = p;
        next = n;
    }

    /**
     * Retorna o elemento deste nodo
     */
    public T getElement() {
        return element;
    }

    /**
     * Retorna o nodo anterior a este
     */
    public DNode<T> getPrev() {
        return prev;
    }

    /**
     * Retorna o nodo seguinte a este
     */
    public DNode<T> getNext() {
        return next;
    }

    /**
     * Atribui o elemento deste nodo
     */
    public void setElement(T newElem) {
        element = newElem;
    }

    /**
     * Atribui o nodo anterior deste nodo
     */
    public void setPrev(DNode<T> newPrev) {
        prev = newPrev;
    }

    /**
     * Atribui o nodo seguinte a este nodo
     */
    public void setNext(DNode<T> newNext) {
        next = newNext;
    }
}
