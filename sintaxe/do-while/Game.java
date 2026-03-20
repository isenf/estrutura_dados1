import java.util.Random;
import java.util.Scanner;

public class Game{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int secretNum = random.nextInt(20) + 1;
        int guess, count = 0;

        System.out.println("Jogo da Adivinhação\nTente adivinhar o número entre 1 e 20!\n");

        do{
            System.out.print("\nDigite seu guess: ");
            guess = scanner.nextInt();
            count++;

            if(guess < secretNum){
                System.out.println("O número secreto é maior");
            } else if(guess > secretNum){
                System.out.println("O número secreto é menor");
            }

        } while(guess != secretNum);

        System.out.println("Yippe! Você acertou o número secreto em " + count + " tentativas!");
        System.out.println("Número secreto: " + secretNum);

        scanner.close();

    }
}

