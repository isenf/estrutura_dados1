/** Lista encadeada circular com nodos do tipo Node que armazenam tipos genéricos */
public class CircleList<T extends Comparable<T>> {

    protected Node<T> cursor;	// o cursor corrente 
    protected int size;	// a quantidade de nodos da lista

    /**
     * Construtor que cria uma lista vazia
     */
    public CircleList() {
        cursor = null;
        size = 0;
    }

    /**
     * Retorna o tamanho corrente
     */
    public int size() {
        return size;
    }

    /**
     * Retorna o cursor
     */
    public Node<T> getCursor() {
        return cursor;
    }

    /**
     * retorna o tamanho
     */
    public int getSize(){
        return size;
    }

    /**
     * Move o cursor adiante
     */
    public void advance() {
        cursor = cursor.getNext();
    }

    /**
     * Acrescenta um nodo depois do cursor
     */
    public void add(Node<T> newNode) {
        if (cursor == null) {	// a lista está vazia 
            newNode.setNext(newNode);
            cursor = newNode;
        } else {
            newNode.setNext(cursor.getNext());
            cursor.setNext(newNode);
        }
        size++;
    }

    /**
     * Remove o nodo que segue o cursor
     */
    public Node<T> remove() {
        Node<T> oldNode = cursor.getNext(); // o nodo sendo removido 
        if (oldNode == cursor) {
            cursor = null; // a lista se torna vazia 
        } else {
            cursor.setNext(oldNode.getNext()); // desconecta o nodo antigo
            oldNode.setNext(null);
        }
        size--;
        return oldNode;
    }

    /* 3) Escreva um método que receba duas listas circulares, L e M como parâmetros de entrada, isto é, 
    duas listas de nodos de forma que cada nodo possui uma referência next não nula. Implemente um algoritmo 
    para verificar se L e M são na verdade a mesma lista de nodos, mas elas podem ter diferentes (cursores) 
    pontos de partida.*/

    /** verifica se duas listas circulares são iguais ou não.
     *  
     * @return true, se são iguais. false caso contrário.
     */
    public static <T extends Comparable<T>> boolean isEqual(CircleList<T> L, CircleList<T> M){
        if(M.getSize() != L.getSize()) return false;

        for(int i = 0; i < L.getSize(); i++){
            boolean equal = true;
            
            for(int j = 0; j < L.getSize(); j++){
                if(L.getCursor().getElement().compareTo(M.getCursor().getElement()) != 0){
                    equal = false;
                    break;
                }
                M.advance();
                L.advance();
            }
        
            if(equal) return true;
            L.advance();
        }
        return false;
    }

    /**
     * Retorna uma representação string da lista, iniciando pelo cursor
     */
    public String toString() {
        if (cursor == null) {
            return "[ ]";
        }
        String s = "[..." + cursor.getElement();
        Node<T> oldCursor = cursor;
        for (advance(); oldCursor != cursor; advance()) {
            s += ", " + cursor.getElement();
        }
        return s + "...]";
    }

    public static void main(String[] args) {
        java.util.Random rand = new java.util.Random(67);
        CircleList<Integer> l1 = new CircleList<>();
        CircleList<Integer> l2 = new CircleList<>();

        for(int i = 0; i < 10; i++){
            int num = rand.nextInt(100);
            l1.add(new Node<>(num, null));
            //num = rand.nextInt(100);      // para listas diferentes
            l2.add(new Node<>(num, null));
        }

        l1.advance();
        l1.advance();
        l1.advance();
        l1.advance();

        //l1.add(new Node<>(rand.nextInt(100), null));  // para tamanhos diferentes
        System.out.println("l1: " + l1);
        System.out.println("l2: " + l2);

        System.out.printf("\nl1 e l2 são iguais? %b", isEqual(l1, l2));
    }

}