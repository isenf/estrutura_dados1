/** Lista duplamente encadeada com nodos do tipo DNode com tipo genérico */
public class DList<T extends Comparable<T>> {

    protected int size; // quantidade de elementos
    protected DNode<T> header, tail; //apontadores para o inicio e fim da lista

    /**
     * Construtor que cria uma lista vazia
     */
    public DList() {
        size = 0;
        header = new DNode<T>(null, null, null); // cria o cabecalho 
        tail = new DNode<T>(null, null, null); //cria o final 
        header.setNext(tail); // faz o cabecalho e o final apontarem um para o outro
        tail.setPrev(header);
    }

    /**
     * Retorna o número de elementos na lista
     */
    public int size() {
        return size;
    }

    /**
     * Informa se a lista está vazia
     */
    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     * Retorna o primeiro nodo da lista
     */
    public DNode<T> getFirst() throws IllegalStateException {
        if (isEmpty()) {
            throw new IllegalStateException("List is empty");
        }
        return header.getNext();
    }

    /**
     * Retorna o último nodo da lista
     */
    public DNode<T> getLast() throws IllegalStateException {
        if (isEmpty()) {
            throw new IllegalStateException("List is empty");
        }
        return tail.getPrev();
    }

    /**
     * Retorna o nodo que antecede um dado nodo v. Gera erro se v é o cabecalho
     */
    public DNode<T> getPrev(DNode<T> v) throws IllegalArgumentException {
        if (v == header) {
            throw new IllegalArgumentException("Cannot move back past the header of the list");
        }
        return v.getPrev();
    }

    /**
     * Retorna o nodo que segue um dado nodo v. Gera erro se v é o final
     */
    public DNode<T> getNext(DNode<T> v) throws IllegalArgumentException {
        if (v == tail) {
            throw new IllegalArgumentException("Cannot move forward past the trailer of the list");
        }
        return v.getNext();
    }

    /**
     * Insere um dado nodo z antes de um dado nodo v. Gera um erro se v é o
     * cabecalho
     */
    public void addBefore(DNode<T> v, DNode<T> z) throws IllegalArgumentException {
        DNode<T> u = getPrev(v); // Deve lancar uma IllegalArgumentException
        z.setPrev(u);
        z.setNext(v);
        v.setPrev(z);
        u.setNext(z);
        size++;
    }

    /**
     * Insere um dado nodo z depois de uma dado nodo v. Gera um erro se v é o
     * final
     */
    public void addAfter(DNode<T> v, DNode<T> z) {
        DNode<T> w = getNext(v); // Deve lançar uma IllegalArgumentException 
        z.setPrev(v);
        z.setNext(w);
        w.setPrev(z);
        v.setNext(z);
        size++;
    }

    /**
     * Insere o nodo fornecido no início da lista
     */
    public void addFirst(DNode<T> v) {
        addAfter(header, v);
    }

    /**
     * Insere o nodo fornecido no fim da lista
     */
    public void addLast(DNode<T> v) {
        addBefore(tail, v);
    }

    /**
     * Remove um dado nodo v da lista. Gera um erro se v é o cabeçalho ou o
     * final
     */
    public void remove(DNode<T> v) {
        DNode<T> u = getPrev(v); // Deve lançar uma IllegalArgumentException 
        DNode<T> w = getNext(v); // Deve lançar uma IllegalArgumentException 

        // Desconecta o nodo da lista 
        w.setPrev(u);
        u.setNext(w);
        v.setPrev(null);
        v.setNext(null);
        size--;
    }


    /** remove um objeto do tipo T
     * 
     * @param s objeto a ser removido
     */
    public void remove(T s){
        if(s == null) return;

        DNode<T> cur = this.header.getNext(), next;

        while(cur != tail){
            T element = cur.getElement();
            next = cur.getNext();

            if(element != null && element.compareTo(s) == 0){
                remove(cur);
            }

            cur = next;
        }
        
    }

    /* 1) Implemente um método que recebe duas listas duplamente encadeadas, L e M, com sentinelas de cabeça, 
    e devolva uma única lista K que contém todos os nodos de L seguidos por todos os nodos de M. */

    /** une duas listas duplamente encadeadas
     * 
     * @param L primeira lista
     * @param M segunda lista 
     */
    public DList<T> concatenate(DList<T> L, DList<T> M){
        if(M == null) return L;
        if(L == null) return M;

        DList<T> K = new DList<>();
        DNode <T> cur = L.header.getNext();

        while(cur != L.tail && cur.getNext() != null){
            DNode<T> temp = new DNode<>(cur.getElement(), null, null);
            K.addLast(temp);
            cur = cur.getNext();
        }
        
        cur = M.header.getNext();
        while(cur != M.tail && cur.getNext() != null){
            DNode<T> temp = new DNode<>(cur.getElement(), null, null);
            K.addLast(temp);
            cur = cur.getNext();
        }

        return K;
    }

    /**
     * Indica se o nodo indicado possui um antecessor
     */
    public boolean hasPrev(DNode<T> v) {
        return v != header;
    }

    /**
     * Indica se o nodo indicado possui um sucessor
     */
    public boolean hasNext(DNode<T> v) {
        return v != tail;
    }

    /**
     * Retorna uma representação string da lista
     */
    public String toString() {
        String s = "[";
        DNode<T> v = header.getNext();
        while (v != tail) {
            s += v.getElement();
            v = v.getNext();
            if (v != tail) {
                s += ", ";
            }
        }
        s += "]";
        return s;
    }

    public static void main(String[] args) {
        DList<String> lista = new DList<>();

        lista.addFirst(new DNode("AAA", null, null));
        lista.addFirst(new DNode("BBB", null, null));
        lista.addFirst(new DNode("CCC", null, null));
        lista.addFirst(new DNode("DDD", null, null));
        lista.addFirst(new DNode("CCC", null, null));
        lista.addFirst(new DNode("CCC", null, null));
        lista.addFirst(new DNode("EEE", null, null));
        
        System.out.println(lista);
        System.out.println(lista.concatenate(lista, lista));

    }
    
}
