import java.util.Scanner;
import java.util.regex.*;

/** classe executável da lista de contatos */
public class Main{

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
    public static String readData(Scanner sc, String regex){
        Pattern p = Pattern.compile(regex);
        Matcher m;
        String data;

        do{
            System.out.print("-> ");
            data = sc.nextLine();
            m = p.matcher(data);
        } while(!m.matches());

        return data;
    }

    /** lê e retorna um nome */
    public static String readName(Scanner sc){
        System.out.println("nome do contato");
        String n = readData(sc, "^[\\p{L} '\\-]+$");

        return n;
    }

    /** lê e retorna um CPF */
    public static String readCpf(Scanner sc){
        System.out.println("CPF: (formato: 000.000.000-00)");
        String n = readData(sc, "^(\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2})$");

        return n;
    }

    /** lê e retorna um telefone */
    public static String readPhone(Scanner sc){
        System.out.println("telefome: (formato: 00 0000-0000)");
        String n = readData(sc, "^(\\d{2}) \\d{4,5}-?\\d{4}$");

        return n;
    }

    /** lê e retorna um email */
    public static String readEmail(Scanner sc){
        System.out.println("email:");
        String n = readData(sc, "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");

        return n;
    }

    /** lê e retorna uma idade */
    public static int readAge(Scanner sc){
        int age = -1;
        System.out.println("idade:");

        do{
            System.out.print("-> ");
            try{
                age = sc.nextInt();
                
            } catch (Exception InputMismatchException){
                System.out.println("erro: apenas números permitidos");
                sc.nextLine();  
            }
        } while(age < 0 || age > 100);

        sc.nextLine();
        return age;
    }

    /** usa os métodos de leitura e retorna um contato */
    public static Contact readContact(Scanner sc){

        String name = readName(sc);
        int age = readAge(sc);
        String CPF = readCpf(sc);
        String phone = readPhone(sc);
        String email = readEmail(sc);

        Contact c = new Contact(CPF, name, age, phone, email);
        return c;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        ContactList contacts = new ContactList();
        int option;

        do{
            menu();
            System.out.print("\n-> ");

            option = sc.nextInt();
            sc.nextLine();
            if(option == 0){
                if(contacts.size() == 0){
                    System.out.println("Agenda vazia, não há dados para serem salvos.");
                    break;
                }

                System.out.print("Nome do arquivo para salvar: ");
                String nameFile = sc.nextLine();
                Arquivo fileWrite = new Arquivo(nameFile);

                try{
                    fileWrite.criarSaida();
                    DNode<Contact> c = contacts.getFirst();

                    while(c != null){
                        if(c.getElement() != null) fileWrite.escreverContato(c.getElement());
                        c = c.getNext();
                    }

                    fileWrite.close();
                } catch(Exception e){
                    System.out.println("erro ao salvar o arquivo: " + e.getMessage());
                }

                break;
            }

            switch(option){
                case 1: {
                    DNode<Contact> temp = new DNode<>(readContact(sc), null, null);
                    contacts.addLast(temp);
                    System.out.println("O contato\n" + temp.getElement() + "\nfoi adicionado no final!");
                    break;
                }
                    
                case 2: {
                    DNode<Contact> temp = new DNode<>(readContact(sc), null, null);
                    contacts.insertSorted(temp);
                    System.out.println("O contato\n" + temp.getElement() + "\nfoi adicionado ordenado pelo nome!");
                    break;
                }

                case 3:
                    contacts.sortByName();
                    break;
            
                case 4:
                    contacts.sortByAge();
                    break;

                case 5:
                    if(contacts.size() == 0){
                        System.out.println("Lista vazia!");
                        break;
                    }
                    String name = readName(sc);
                    System.out.print("Novo ");
                    String newName = readName(sc);
                    contacts.modifyName(name, newName);

                    break;
                    
                case 6:
                    if(contacts.size() == 0){
                        System.out.println("Lista vazia!");
                        break;
                    }
                    DList<Contact> temp = contacts.findByName(readName(sc));
                    System.out.println(temp);
                    break;

                case 7:
                    if(contacts.size() == 0){
                        System.out.println("Lista vazia!");
                        break;
                    }                    String cpf = readCpf(sc);
                    System.out.println(contacts.removeByCpf(cpf) ? "O contato foi removido!" : "Contato não encontrado...");

                    break;

                case 8:
                    contacts.print();
                    break;

                case 9:
                    contacts.printInv();
                    break;

                case 10:{
                    System.out.print("Nome do arquivo para carregar: ");

                    String fileName = sc.nextLine();
                    Arquivo fileRead = new Arquivo(fileName);

                    try {
                        fileRead.criarEntrada();
                        Contact c;
                        int count = 0;
                        do{ 
                            c = fileRead.lerContato();

                            if(c != null){
                                contacts.insertSorted(new DNode(c, null, null));
                                count++;
                            }
                        } while(c != null);

                        System.out.println(count + " contatos foram carregado do arquivo " + fileName);
                        fileRead.close();
                    } catch(Exception e){
                        System.out.println("erro ao carregar o arquivo: " + e.getMessage());
                    }
                    break;
                }
                default:
                    System.out.println("Opção inválida!");
            }
            
        } while(option != 0);

        sc.close();

    }
}