import java.util.Scanner;
import java.util.regex.*;

public class Main{
    public static Scanner s = new Scanner(System.in);

    public static void menu(){
        System.out.println("\n<----- Operações da Agenda ----->");
        System.out.println("1. Adicionar novo contato no final");
        System.out.println("2. Adicionar novo contato ordenado pelo nome");
        System.out.println("3. Ordenar pelo nome");
        System.out.println("4. Ordenar pela idade");
        System.out.println("5. Alterar pelo nome");
        System.out.println("6. Consultar pelo nome");
        System.out.println("7. Excluir pelo CPF");
        System.out.println("8. Imprimir (cabeça -> cauda)");
        System.out.println("9. Imprimir (cauda -> cabeça)");
        System.out.println("10. Carregar um arquivo com contatos");

        System.out.println("0. Salvar e sair\n");
    }

    /** lê e retorna uma string com base no regex informado */
    public static String readData(String regex){
        Pattern p = Pattern.compile(regex);
        Matcher m;
        String data;

        do{
            System.out.print("-> ");
            data = s.nextLine();
            m = p.matcher(data);
        } while(!m.matches());

        return data;
    }

    /** lê e retorna um nome */
    public static String readName(){
        System.out.println("Nome do contato");
        String n = readData("^[A-Za-zÀ-ÖØ-ö\s]+$");
        return n;
    }

    /** lê e retorna um CPF */
    public static String readCpf(){
        System.err.println("CPF: ");
        String n = readData("^(\\d{11}|\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2})$");
        return n;
    }

    /** lê e retorna um telefone */
    public static String readPhone(){
        return "";
    }

    /** lê e retorna um email */
    public static String readEmail(){
        return "";
    }

    /** lê e retorna uma idade */
    public static int readAge(){
        int age = -1;
        System.out.println("Idade:");

        do{
            System.out.print("-> ");
            age = (int) s.nextInt();
            System.out.println("entrada: "+age);
        } while(age < 0 || age > 100);

        return age;
    }

    /** usa os métodos de leitura e retorna um contato */
    public static Contact readContact(){
        Contact c = new Contact();

        c.setName(readName());
        c.setAge(readAge());
        c.setCPF(readCpf());
        

        return c;
    }

    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        DList<Contact> contacts = new DList<>();
        Arquivo file = new Arquivo("contact.txt");
        int option;

        do{
            menu();
            System.out.print("\n-> ");

            option = s.nextInt();
            s.nextLine();
            if(option == 0) break;

            switch(option){
                case 1: {
                    DNode<Contact> temp = new DNode<>(readContact(), null, null);
                    contacts.addLast(temp);
                    System.err.println("O contato " + temp.getElement() + " foi adicionado no final!");
                    break;
                }
                    
                case 2: {
                    DNode<Contact> temp = new DNode<>(readContact(), null, null);
                    contacts.insertSorted(temp);
                    System.err.println("O contato " + temp.getElement() + " foi adicionado ordenado pelo nome!");
                    break;
                }

                case 3:
                    DList.sortByName(contacts);
                    break;
            
                case 4:
                    DList.sortByAge(contacts);
                    break;

                case 5:

                    break;
                    
                case 6:

                    break;
                case 7:

                    break;
                case 8:
                    DList.print(contacts);
                    break;
                case 9:
                    DList.printInv(contacts);
                    break;
                case 10:

                    Contact c;
                    file.criarEntrada();
                    do { 
                        c = file.lerContato();
                        if(c != null) contacts.insertSorted(new DNode(c, null, null));
                    } while(c != null);

                    System.out.println("Lista de contatos carregada!");
                    break;
                default:
                    file.criarSaida();
                    System.out.println("Opção inválida!");
            }

            
        } while(option != 0);



    }
}