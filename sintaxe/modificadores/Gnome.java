import java.util.Scanner;

public class Gnome{

    public String name;
    public int age;
    public Gnome gnomeBuddy;
    public boolean magical = false;
    protected double height = 2.4;
    public static final int MAX_HEIGHT = 3;

    // construtores
    Gnome(String nm, int ag, Gnome bud, double hgt){
        name = nm;
        age = ag;
        gnomeBuddy = bud;
        height = hgt;
    }

    // construtor default
    Gnome(){
        name = "Cell";
        age = 67;
        gnomeBuddy = null;
        height = 2.0;
    }

    // métodos
    public static void makeKing(Gnome h){
        h.name = "King " + h.getRealName();
        h.magical = true;
    }

    public void makeMeKing(){
        name = "King " + name;
        magical = true;
    }

    public boolean isMagical(){
        return magical;
    }

    public void setHeight(double newHeight){
        if(newHeight <= 3){
            height = newHeight;
        } else{
            height = MAX_HEIGHT;
        }
    }

    public String getName(){
        return "é segredo! shh";
    }

    public String getRealName(){
        return name;
    }

    public void renameGnome(String s){
        name = s;
    }

    public void displayInfo(){
        System.out.printf("Nome: %s%n", name);
        System.out.printf("Idade: %d anos %n", age);
        System.out.printf("Altura: %.2f%n", height);
        System.out.printf("Mágico: %b%n", magical);
        System.out.printf("Amigo: %s%n", gnomeBuddy != null ? gnomeBuddy.getRealName() : "Nenhum");
    }

    public static void main(String[] args){
        Gnome g = new Gnome();
        Scanner scanner = new Scanner(System.in);

        System.out.println("** Criação de um novo Gnome **\n");

        System.out.print("Nome: ");
        g.renameGnome(scanner.nextLine());

        System.out.print("Idade: ");
        g.age = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Altura: ");
        double height = scanner.nextDouble();
        g.setHeight(height);
        scanner.nextLine();

        System.out.print("Mágico: ");
        g.magical = scanner.nextBoolean();

        Gnome friend = new Gnome();
        g.gnomeBuddy = friend;

        System.out.println("\n\n** Informações do Gnome **\n");

        g.displayInfo();
        scanner.close();

    }

}