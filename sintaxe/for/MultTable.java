import java.util.Scanner;


public class MultTable{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite um número para a tabuada: ");
        float num = scanner.nextFloat();

        for(int i = 1; i <= 10; i++){
            System.out.printf("%.2f x %d = %.2f\n", num, i, (num * i));
        }
    }
}