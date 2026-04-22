/*
2) Escreva uma classe que mantenha os dez maiores escores para uma aplicação de jogo, implementando 
os métodos add e remove da Seção 3.11, mas usando uma lista simplesmente encadeada em vez de um arranjo.

4) Implementa entrada de dados que permita ao usuário incluir (ordenadamente) e excluir elementos pelo 
nome em uma lista simplesmente encadeada até que decida sair da aplicação. Após a inclusão ou exclusão de 
elementos, imprimir a lista atualizada para o usuário.
*/

// considera-se Node como tupo genérico e GameEntry.java no mesmo diretório
import java.util.Arrays;
import java.util.Scanner;

public class ScoresSLinkedList{

    protected Node<GameEntry> head;
    protected int size;
    public static final int MAX_SIZE = 10;

    /** construtor */
    public ScoresSLinkedList(){
        head = null;
        size = 0;
    }

    /** retorna se está vazio ou não */
    public boolean isNull(){
        return head == null;
    }

    /** adiciona e ordena o elemento
     * 
     * @param e elemento a ser inserido
     */
    public void add(Node<GameEntry> e){

        if(head == null){   // lista vazia
            e.setNext(head);
            head = e;
            size++;
            return;
        }

        int newScore = e.element.getScore();

        if(newScore > head.getElement().getScore()){    // novo escore > que o primeiro
            Node<GameEntry> newNode = e;
            newNode.setNext(head);
            head = newNode;
            size++;

            if(size > MAX_SIZE){
                removeLast();
            }

            return;
        }

        Node<GameEntry> cur = head, prev = null;

        while(cur != null && newScore <= cur.getElement().getScore()){
            prev = cur;
            cur = cur.getNext();
        }
        
        Node<GameEntry> newNode = e;
        prev.setNext(newNode);
        newNode.setNext(cur);
        size++;

        if(size > MAX_SIZE){
            removeLast();
        }

    }

    /** remove o nodo na posição i e retorna o elemento removido
     *
     * @param i posição do elemento que se deseja remover
     * @return retorna o objeto GameEntry removido
     */
    public GameEntry remove(int i) throws IndexOutOfBoundsException{
        if(i < 0 || i >= size){
            throw new IndexOutOfBoundsException("indice inválido: " + i);
        }

        if(i == 0){
            Node<GameEntry> e = head;
            head = head.getNext();
            size--;

            return e.getElement();
        }

        Node<GameEntry> prev = head;
        for(int j = 1; j < i; j++){
            prev = prev.getNext();
        }

        Node<GameEntry> cur = prev.getNext();
        GameEntry e = cur.getElement();
        prev.setNext(cur.getNext());
        cur = null;
        size--;

        return e;
    }

    /** remove a primeira ocorrência do nodo com name s
     * 
     * @param s nome do jogador a remover
     * @return retorna o objeto GameEntry removido ou null
     */
    public GameEntry remove(String s){
        if(head == null) return null;

        GameEntry removed = null;

        if(head.getElement().getName().equals(s)){
            removed = head.getElement();
            head = head.getNext();
            size--;
            return removed;
        }

        Node<GameEntry> cur = head;
        while(cur.getNext() != null && !cur.getNext().getElement().getName().equals(s)){
            cur = cur.getNext();
        }

        if(cur.getNext() != null){
            removed = cur.getNext().getElement();
            cur.setNext(cur.getNext().getNext());
            size--;
            return removed;
        } 

        return removed;

    }

    /**
     * método para remover o último elemento
     */
    private void removeLast(){
        if(head == null){
            return;
        }

        if(head.getNext() == null){
            head = null;
            size--;
            return;
        }

        Node<GameEntry> cur = head;
        while(cur.getNext().getNext() != null){
            cur = cur.getNext();
        }

        cur.setNext(null);
        size--;
        
    }

    /**
     * retorna uma string que representa o conjunto de escores armazenados na lista encadeada
     */
    @Override
    public String toString(){
        if(head == null) return "NULL";

        String s = "\n" + head.getElement().toString() + "--> ";
        Node<GameEntry> cur = head.getNext();

        for(int i = 1; i < size; i++){
            s += cur.getElement().toString() + "--> ";
            cur = cur.getNext();
        }

        s += "NULL";
        return s;
    }

    // para testar a classe
    public static void main(String[] args){

        ScoresSLinkedList scores = new ScoresSLinkedList();
        Scanner scanner = new Scanner(System.in);
        int option;
        
        do{
            System.err.print("\n\n* -- OPÇÕES -- *\n1 - Inserir nova entrada (nome e score)\n2 - Deletar a primeira ocorrência de um jogador\n0 - Sair da aplicação\n-> ");
            option = scanner.nextInt();
            scanner.nextLine();

            while(!Arrays.asList(0, 1, 2).contains(option)){
                System.out.print("Opção inválida! Tente novamente\n-> ");
                option = scanner.nextInt();
            }

            String name;
            switch(option){
                case 1:
                    System.out.print("Nome do jogador: ");
                    name = scanner.nextLine();

                    System.err.print("Score: ");
                    int score = scanner.nextInt();

                    scores.add(new Node<>(new GameEntry(name, score), null));
                    
                    break;
                
                case 2:
                    System.out.print("Nome do jogador: ");
                    name = scanner.nextLine();

                    GameEntry player = scores.remove(name);

                    if(player == null && scores.isNull()) System.out.println("lista vazia!\n");
                    else if(player == null) System.out.printf("jogador %s não encontrado!\n", name);
                    
                    break;                    
            }

            System.out.println(scores);
        } while(option != 0);

        System.out.println("Fim do programa");

    }
}

