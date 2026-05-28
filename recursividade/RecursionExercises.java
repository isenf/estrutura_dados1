/** implementa os exercícios de recursividade
 * 
 * @author Dante Fabro
 */
public class RecursionExercises{

    /** verifica se uma determinada string é um palíndromo ou não
     * 
     * @param s string a ser verificada
     * @param i índice inicial da string
     * @param j índice final da string
     * 
     * @return true se é um palíndromo, false caso contrário
     * 
     */
    public static boolean isPalindrome(String s, int i, int j){
        if(i < j){
            return (s.charAt(i) == s.charAt(j)) && isPalindrome(s, i + 1, j - 1);
        } 

        return true;
    }

    /** reorganiza um arranjo para que os números pares apareçam antes de todos os números ímpares
     * 
     * @param A arranjo a ser reorganizado
     * @param i índice do primeiro elemento
     * @param j índice do último elemento
     */
    public static void evenFirst(int[] A, int i, int j){
        if(A[i] % 2 == 0 && i < j){
            evenFirst(A, i + 1, j);
        }
        else if(A[i] % 2 != 0 && i < j){
            int temp = A[i];
            A[i] = A[j];
            A[j] = temp;
            evenFirst(A, i, j - 1);
        }
    }

    /**
     * imprime um vetor
     * 
     * @param A vetor a ser impresso
     * @param i índice do primeiro elemento do vetor
     */
    public static void printArray(int[] A, int i){
        System.out.print(A[i]);

        if(i < A.length - 1){
            System.out.print(", ");
            printArray(A, i + 1);
        }
    }

    // testa os algoritmos recursivos
    public static void main(String[] args){
        int[] A = {6, 7, 5, 2, 4, 9, 0, 1};
        String s = "racecar";

        System.out.print("Arranjo: ");
        printArray(A, 0);
        System.out.println("\nColocar os números pares primeiro: ");
        evenFirst(A, 0, A.length - 1);
        printArray(A, 0);

        System.out.println("Palavra: " + s + "\nÉ um palíndromo? " + isPalindrome(s, 0, s.length() - 1));

    }
}
