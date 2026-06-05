/** implementa os exercícios de recursividade
 * 
 * @author Dante Fabro
 */
public class RecursionExercises{

    /** encontra o valor máximo em um arranjo
     * 
     * @param A arranjo de inteiros
     * @return valor máximo encontrado 
    */
    public static int findMax(int[] A){
        return findMax(A, A.length, A[A.length-1]);
    }

    /** método recursivo privado que encontra o valor máximo de um arranjo
     * 
     * @param A arranjo de inteiros
     * @param n quantidade de elementos do arranjo
     * @param max valor máximo encontrado 
     */
    private static int findMax(int[] A, int n, int max){
        if(n == 0){
            return max;
        }
        if(A[n-1] > max) max = A[n-1];
        return findMax(A, n - 1, max);
    }

    /** reorganiza um arranjo para que os números pares apareçam antes de todos os números ímpares
     * 
     * @param A arranjo a ser reorganizado
     */
    public static void evenFirst(int[] A){
        evenFirst(A, 0, A.length-1);
    }

    /** método recursivo que reorganiza um arranjo para que os números pares apareçam antes de todos os números ímpares
     * 
     * @param A arranjo a ser reorganizado
     * @param i índice do primeiro elemento
     * @param j índice do último elemento
     */
    private static void evenFirst(int[] A, int i, int j){
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

    /** verifica se uma string é um palíndromo ou não
     * 
     * @param s string a ser verificada
     * @return true se é um palíndromo, false caso contrário
     */
    public static boolean isPalindrome(String s){
        return isPalindrome(s, 0, s.length()-1);
    }

    /** método recursivo para verificar se uma determinada string é um palíndromo ou não
     * 
     * @param s string a ser verificada
     * @param i índice inicial da string
     * @param j índice final da string
     * 
     * @return true se é um palíndromo, false caso contrário
     */
    private static boolean isPalindrome(String s, int i, int j){
        if(i < j){
            return (s.charAt(i) == s.charAt(j)) && isPalindrome(s, i + 1, j - 1);
        } 

        return true;
    }

    /** calcula o k-ésimo elemento na sequência de Fibonacci usando recursão binária
     * 
     * livro - trecho de código 3.35
     * 
     * @param k inteiro não negativo k que representa a posição na sequência
     * @return k-ésimo elemento da sequência
     */
    public static int BinaryFib(int k){
        if(k <= 1) return k;
        else return BinaryFib(k - 1) + BinaryFib(k - 2);
    }

    /** calcula o par (k, k-1) na sequência de Fibonacci utilizando recursão linear
     * 
     *  livro - trecho de código 3.36
     * 
     * @param k inteiro não negativo k que representa a posição na sequência
     * @return par (k, k-1)
     */
    public static int[] LinearFibonacci(int k){
        if(k <= 1){
            return new int[]{k, 0};
        }

        int[] pair = LinearFibonacci(k-1);
        return new int[]{pair[0] + pair[1], pair[0]};

    }

    /** imprime um arranjo de inteiros
     * 
     * @param A vetor a ser impresso
    */
    public static void printArray(int[] A){
        System.out.print("{");
        printArray(A, 0);
        System.out.print("}");
    }

    /** método recursivo que imprime um arranjo
     * 
     * @param A vetor a ser impresso
     * @param i índice do primeiro elemento do vetor
     */
    private static void printArray(int[] A, int i){
        System.out.print(A[i]);

        if(i < A.length - 1){
            System.out.print(", ");
            printArray(A, i + 1);
        }
    }

    public static void fillMatrix(int[][] M, int[] a, int i, int j){
        if(i >= M.length) return;

        if(j < M.length){
            M[i][j] = a[i * M.length + j];
            fillMatrix(M, a, i, j + 1);
            return;
        }

        fillMatrix(M, a, i + 1, 0);
    }

    public static int calcProduct(int m, int n){
        if(n == 0) return 0;
        return m + calcProduct(m, n-1);
    }

    public static double convStrInt(String S, int n, int i){
        if(i >= n-1) return S.charAt(i) - '0';
        return (Math.pow(10, n-1-i) * ((int) S.charAt(i) - '0')) + convStrInt(S, n, i+1);
    }

    public static int[] findMinMax(int[] A){
        return findMinMax(A, new int[]{A[0], A[0]}, 1);
    }

    private static int[] findMinMax(int[] A, int[] minMax, int i){
        if(i == A.length) return minMax;

        // para menor valor
        if(A[i] < minMax[0]) minMax[0] = A[i];

        // para maior valor
        if(A[i] > minMax[1]) minMax[1] = A[i];

        return findMinMax(A, minMax, i+1);
    }
    public static void printMatrix(int [][] M){
        printMatrix(M, 0, 0);
    }

    private static void printMatrix(int[][] M, int i, int j){
        if(i == M.length) return;
        if(j < M[i].length){
            System.out.print(M[i][j]);

            if(j < M[i].length-1) System.out.print(", ");
            
            printMatrix(M, i, j+1);
            return;
        }
        System.out.println();
        printMatrix(M, i+1, 0);
    }

    // testa os algoritmos recursivos
    public static void main(String[] args){
        int[] A = {12, 6, 7, 5, 2, 4, 9, 0, 1, 11};
        String s = "racecar";
        int k = 7;

        System.out.print("Arranjo: ");
        printArray(A);
        System.out.println("\nMaior valor do arranjo: " + findMax(A));
        System.out.println("\nColocar os números pares primeiro: ");
        evenFirst(A);
        printArray(A);

        System.out.println("\n\nPalavra: " + s + "\nÉ um palíndromo? " + isPalindrome(s));

        System.out.println("\n" + k + "º número da sequência de Fibonacci: " + BinaryFib(k));
        k+=2;
        int[] fibPair = LinearFibonacci(k);
        System.out.println("\n" + (k-1) + "º e " + k + "º elementos da sequencia de Fibonacci: " + fibPair[1] + " e " + fibPair[0]);

        int[] aux = findMinMax(A);
        System.out.println("\nMenor e maior elemento: " + aux[0] + " e " + aux[1]);

        int[][] M = new int[3][3];
        A = new int[]{0, 1, 2, 3, 5, 5, 6, 7, 0};

        System.out.println("\nTransforma o arranjo em uma matriz nxn: ");
        printArray(A);
        System.out.println();
        fillMatrix(M, A, 0, 0);
        printMatrix(M, 0, 0);

        int m = 2, n = 3;
        System.out.println("\nCalcula o produto de " + m + " e " + n + 
                            " utilizando recursão e adição e subtração\nProduto: " + calcProduct(m, n));

        s = "13098";
        System.out.println("\nConverte a string numérica " + s + " em um número: " + (int) convStrInt(s, s.length(), 0));
    }
}
