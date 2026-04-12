/* 
1) Implemente as operações de inserir e remover considerando a cabeça de uma lista simplesmente encadeada.

3) Implemente a ordenação por inserção em uma lista simplesmente encadeada.
*/

import java.util.Random;

/**
 * lista encadeada simples com tipo genérico
 */
public class SLinkedList<T extends Comparable<T>>{
    protected Node<T> head;
    protected long size;

    /** construtor */
    public SLinkedList(){
        head = null;
        size = 0;
    }

    /** adiciona um elemento na primeira posição
     * 
     * @param v nodo a ser adicionado
     */
    public void addFirst(Node<T> v){
        v.setNext(head);
        head = v;
        size++;
    }

    /**
     * remove o primeiro elemento
     */
    public void removeFirst(){
        if(head == null){
            System.out.println("Lista vazia :(");
            return;
        }

        Node<T> temp = head;
        head = head.getNext();
        temp.setNext(null);
        size--;
    }

    /**
     * algoritmo de inserção ordenada com lista simplesmente encadeada
     */
    public void insertionSort(){
        if(head == null || head.getNext() == null) return;

        Node<T> newList = null, cur = head;

        while(cur != null){
            Node<T> next = cur.getNext();

            if(newList == null || cur.getElement().compareTo(newList.getElement()) < 0){ // adiciona na primeira posição
                cur.setNext(newList);
                newList = cur;
            } else{
                Node<T> temp = newList, tempNext = temp.getNext();

                while(temp.getNext() != null && cur.getElement().compareTo(tempNext.getElement()) > 0){ // procura a posição
                    temp = temp.getNext();
                    tempNext = temp.getNext();
                }

                cur.setNext(temp.getNext());
                temp.setNext(cur);
            }

            cur = next;
        }

        head = newList;
    }

    /**
     * retorna uma string que representa a lista simplesmente encadeada
     */
    @Override
    public String toString(){
        String s = "";
        Node<T> cur = head;

        while(cur != null){
            s += cur.getElement() + " -->";
            cur = cur.getNext();
        }

        s += "NULL";

        return s;
    }

    // testa o insertion sort com números aleatórios
    public static void main(String[] args){
        Random rand = new Random(67);
        SLinkedList<Integer> lista = new SLinkedList<>();

        for(int i = 0; i < 10; i++){
            lista.addFirst(new Node<Integer>(rand.nextInt(1000), null));
            System.out.printf("iteração %d: \t", i+1);
            System.out.println(lista);
            lista.insertionSort();
            System.out.printf("ordenação: \t");
            System.out.println(lista + "\n");
        }

        System.out.println("lista após todas as iterações:\n" + lista); 

    }
}