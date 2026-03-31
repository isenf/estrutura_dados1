import java.util.Scanner;
import java.util.regex.*;

public class Patterns{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        Pattern p1 = Pattern.compile("abc", Pattern.CASE_INSENSITIVE);
        Matcher matcher;

        do{
            String input = scanner.nextLine();
            matcher = p1.matcher(input);
        }while(!matcher.matches());
    }
}

