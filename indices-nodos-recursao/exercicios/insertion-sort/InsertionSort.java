public class InsertionSort{

    public static void insertionSort(char[] a){
        int n = a.length;

        for(int i = 1; i < n; i++){
            char cur = a[i];
            int j = i - 1;

            while((j >= 0) && (a[j] > cur)){
                a[j+1] = a[j--];
            }

            a[j+1] = cur;

        }
    }

    public static void printArr(char[] a){
        System.out.print("[");
        for(int i = 0; i < a.length; i++){
            if(i > 0){
                System.out.print(", ");
            }
            System.out.print(a[i]);
        }

        System.out.println("]");
    }


    public static void main(String[] args){
        char[] letras = {'Q', 'D','A', 'C', 'W', 'P', 'F', 'N'};

        printArr(letras);
        insertionSort(letras);
        System.out.println("array ordenado:");
        printArr(letras);

    }
}