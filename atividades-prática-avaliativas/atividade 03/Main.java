import java.util.Scanner;

/**
 * classe executável
 * 
 * @author Dante Fabro
 */
public class Main{
    private static Scanner sc = new Scanner(System.in);
    private static ExpressionTree expTree = new ExpressionTree();

    /** lê e retorna um inteiro válido */
    public static int readInt(){
        while(!sc.hasNextInt()){
            System.out.print("-> ");
            sc.next();
        }

        int val = sc.nextInt();
        sc.nextLine();

        return val;
    }

    public static String readString(){
        String val = sc.nextLine().trim();  
        if(val.isEmpty()){
            System.out.print("-> ");
            return readString();
        }

        return val;
    }

    /** imprime um menu de operações */
    public static void menu(){
        System.out.println("\n\n----- Selecione uma opção -----");
        System.out.println("1. Adicionar nova expressão aritmética");
        System.out.println("2. Resolver uma expressão existente");
        System.out.println("3. Imprimir representação da árvore");
        System.out.println("4. Percurso preorder");
        System.out.println("5. Percurso inorder");
        System.out.println("6. Percurso postorder");
        System.out.println("7. EulerTour");
        System.out.println("0. Sair");
    }

    /** adiciona a expressão e tenta construir a árvore */
    public static ExpressionTree addExpression(ExpressionTree expTree, String exp){
        expTree.setExpression(exp);

        try{
            if(expTree.build()){
                return expTree;
            }

            return null;
        } catch(IllegalArgumentException e){
            return null;
        }

    }

    /** resolve a expressão */
    public static void solveExpression(){
        if(expTree.isEmpty()){
            System.out.println("erro: a árvore não foi montada");
            return;
        }
        System.out.println(expTree.expression + " = " + expTree.evaluate());
    }

    /** imprime a representação de árvore */
    public static void printTree(){
        if(expTree.isEmpty()){
            System.out.println("erro: a árvore não foi montada");
            return;
        }

        expTree.draw();
    }

    /** imprime a representação do percurso preorder */
    public static void showPreorder(){
        if(expTree.isEmpty()){
            System.out.println("erro: a árvore não foi montada");
            return;
        }

        System.out.println(expTree.stringPreorder(expTree.root));
    }

    /** imprime a representação do percurso inorder */
    public static void showInorder(){
        if(expTree.isEmpty()){
            System.out.println("erro: a árvore não foi montada");
            return;
        }

        System.out.println(expTree.stringInorder(expTree.root));
    }

    /** imprime a representação do percurso postorder */
    public static void showPostorder(){
        if(expTree.isEmpty()){
            System.out.println("erro: a árvore não foi montada");
            return;
        }

        System.out.println(expTree.stringPostorder(expTree.root));
    }

    /** imprime a representação do euler tour */
    public static void showEulerTour(){
        if(expTree.isEmpty()){
            System.out.println("erro: a árvore não foi montada");
            return;
        }

        System.out.println(expTree.stringEulerTour(expTree.root));
    }

    public static void main(String[] args) {
        int option = -1;

        do{
            menu();
            System.out.print("-> ");
            option = readInt();

            switch (option) {
                case 0:
                    System.out.println("\nSaindo...");
                    break;

                case 1:
                    System.out.print("Insira a expressão, ex:((5+4)*(3-2))\n-> ");
                    String exp = readString();

                    ExpressionTree res = addExpression(new ExpressionTree(), exp);
                    if(res != null) expTree = res;
                    break;
                
                case 2:
                    solveExpression();
                    break;
            
                case 3:
                    printTree();
                    break;

                case 4:
                    showPreorder();
                    break;

                case 5:
                    showInorder();
                    break;

                case 6:
                    showPostorder();
                    break;
                
                case 7:
                    showEulerTour();
                    break;
                    
                default:
                    System.out.println("Opção inválida! Tente novamente");
                    break;
            }

        } while(option != 0);

    }
}