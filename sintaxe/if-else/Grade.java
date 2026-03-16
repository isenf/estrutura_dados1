import java.util.Scanner;

public class Grade{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        float n1, n2, nf;

        System.out.print("Nota 1: ");
        n1 = scanner.nextFloat();

        System.out.print("Nota 2: ");
        n2 = scanner.nextFloat();

        nf = (n1 + n2)/2;

        if(nf < 6.0){
            System.out.println("\n\nReprovado com nota " + nf);
        } else if(nf < 8.0){
            System.out.println("\n\nAprovado com nota " + nf);
        } else{
            System.out.println("\n\nExcelente desempenho! Aprovado com nota " + nf);
        }

        scanner.close();
    }
}