import java.util.Scanner;

public class DaysWeek{
    public enum Day {MON, TUE, WED, THU, FRI, SAT, SUN};

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.print("\nInforme a abreviação do dia da semana: ");
        String input = scanner.nextLine().toUpperCase();

        try{
            Day day = Day.valueOf(input);

            switch(day){
                case MON:
                    System.out.println("\nInício da Semana");
                    break;
                case TUE:
                    System.out.println("\nA semana está passando");
                    break;
                case WED:
                    System.out.println("\nMeio da semana");
                    break;
                case THU:
                case FRI:
                    System.out.println("\nA semana está acabando");
                    break;
                default:
                    System.out.println("\nFinal de semana");


            }
        } 
        catch(Exception e){
            System.out.print("Entrada inválida!");
        }

        scanner.close();
    }
}
