import java.util.Scanner;

public class Password{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String password = "ab12345";


        System.out.print("Informe a senha: ");
        String guess = scanner.nextLine();

        int count = 1;

        while(!guess.equals(password)){
            System.out.print("\nSenha errada, tente novamente: ");
            guess = scanner.nextLine();

            count++;
        }

        System.out.printf("Você acertou a senha com %d tentativas", count);
    }
}