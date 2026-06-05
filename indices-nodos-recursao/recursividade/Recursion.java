/** implementações de algoritmos recursivos baseados em pseudocódigos do livro */
public class Recursion{

    /** implementação recursiva da função fatorial
     * 
     * trecho de código 3.29 - livro
     * @param n valor inteiro não negativo do fatorial a ser calculado
     * @return valor fatorial de n (n!)
     */
    public static int recursiveFactorial(int n){
        if(n == 0) return 1;    // caso base

        return n * recursiveFactorial(n-1);     // caso recursivo
    }

    /** calcula a soma dos primeiros n elementos de A utilizando recursão linear
     * 
     * trecho de código 3.32 - livro
     * @param A arranjo de inteiros
     * @param n quantidade de elementos 
     * @return somatório dos primeiros n elementos
     */
    public static int linearSum(int[] A, int n){
        if(n == 1) return A[0];

        return A[n-1] + linearSum(A, n - 1);

    }

    /** inverte os elementos de um arranjo utilizando recursão linear
     * 
     * @param A arranjo a ser invertido
     * @param i índice inicial
     * @param j índice final 
     */
    public static void reverseArray(int[] A, int i, int j){
        if(i < j){
            int temp = A[i];
            A[i] = A[j];
            A[j] = temp;

            reverseArray(A, i + 1, j - 1);
        }
    }

    /** soma os elementos de um arranjo utilizando recursão binária 
     * 
     * trecho de código 3.34 - livro
     * 
     * @param A arranjo a ser somado
     * @param i índice inicial
     * @param n quantidade de elementos a serem somados
     * 
     * @return somatória de elementos do arranjo
     */
    public static int binarySum(int[] A, int i, int n){
        if(n == 1) return A[i];

        return  binarySum(A, i, Math.ceilDiv(n, 2)) + 
                binarySum(A, i + Math.ceilDiv(n, 2), Math.floorDiv(n, 2));
    }

    public static void printArray(int[] A){
        System.out.print("{");
        for(int i = 0; i < A.length; i++){
            System.out.print(A[i]);

            if(i != A.length - 1) System.out.print(", ");
        }

        System.out.println("}");
    }

    public static void main(String[] args){
        int[] A = {2, 3, 5, 7, 11, 13, 17, 19, 23};
        int k = 7;

        System.out.println(k + "!: " + recursiveFactorial(k));

        System.out.println();
        printArray(A);
        System.out.println("Soma com recursão linear: " + linearSum(A, A.length));

        reverseArray(A, 0, A.length-1);
        System.out.println("\nArranjo invertido: ");
        printArray(A);

        System.out.println("\nSoma com recursão binária: " + binarySum(A, 0, A.length));

    }
}