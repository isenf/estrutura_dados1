

public class ArrayQueue<T> implements Queue<T>{

    protected int f;                            // primeiro elemento
    protected int r;                            // próximo elemento a ser inserido
    protected int capacity;                     // capacidade da lista
    public static final int CAPACITY = 1000;    // capacidade default
    protected T Q[];                            // arranjo que representa uma fila

    // construtores
    public ArrayQueue(){
        this(CAPACITY);
    }

    public ArrayQueue(int c){
        capacity = c + 1;
        Q = (T[]) new Object[capacity];
    }

    @Override
    public int size(){
        return (capacity - f + r) % capacity;
    }

    @Override
    public boolean isEmpty(){
        return f == r;
    }

    @Override
    public T front() throws EmptyQueueException{
        if(isEmpty()){
            throw new EmptyQueueException("fila está vazia!");
        }

        return Q[f];
    }

    @Override
    public void enqueue(T element) throws FullQueueException{
        if(size() == capacity - 1){
            throw new FullQueueException("fila está cheia");
        }

        Q[r] = element;
        r = (r + 1) % capacity;
    }

    @Override
    public T dequeue() throws EmptyQueueException{
        if(isEmpty()){
            throw new EmptyQueueException("fila está vazia!");
        }

        T temp = Q[f];
        Q[f] = null;
        f = (f + 1) % capacity;
        return temp;
    }

    /** retorna uma string que representa uma fila */
    @Override
    public String toString(){
        String s = "[";

        for(int i = 0; i < size(); i++){
            s += Q[(f + i) % capacity];
            if(i != size() - 1) s += ", ";
        }

        s += "]";

        return s;
    }

    // testa a implementação
    public static void main(String[] args){
        Queue<Integer> Q = new ArrayQueue<>(5);

        System.out.println("tamanho: " + Q.size());
        Q.enqueue(7);
        Q.enqueue(2);
        Q.enqueue(10);
        Q.enqueue(8);
        Q.enqueue(5);
        //Q.enqueue(0);
        System.out.println(Q);
        System.out.println("tamanho: " + Q.size());
        Q.dequeue();
        System.out.println(Q);
        System.out.println("primeiro elemento: " + Q.front());
        System.out.println("tamanho: " + Q.size());

    }

}