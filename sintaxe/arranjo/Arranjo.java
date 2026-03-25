public class Arranjo {

    /**
     * Soma todos os valores de um arranjo de inteiros
     */
    public static int sum(int[] a) {
        int total = 0;
        for (int i = 0; i < a.length; i++) // observe-se o uso da variável length
        {
            total += a[i];
        }
        return total;
    }

    /**
     * Conta o numero de vezes que um inteiro k aparece em um arranjo a
     */
    public static int findCount(int[] a, int k) {
        int count = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] == k) {
                count++;
            }
        }
        return count;
    }

    public void printArray(double[] input){
        for(int i = 0; i < input.length; i++){
            System.out.println(input[i]);
        }
    }

    public static void printArray(int[] input){
        for(int i = 0; i < input.length; i++){
            System.out.println(input[i]);
        }
    }

    public static void main(String[] args){
        int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29};
        double[] a = new double[10];

        for(int i = 0; i < a.length; i++){
            a[i] = Math.random();
        }

        printArray(primes);
        
    }

}