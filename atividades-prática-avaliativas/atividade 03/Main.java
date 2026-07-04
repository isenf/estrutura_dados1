import java.util.Scanner;
/**
 * classe executável
 * 
 * @author Dante Fabro
 */
public class Main{
    private static Scanner sc = new Scanner(System.in);
    private static ExpressionTree expTree = new ExpressionTree();

    /** imprime um menu de operações */
    public static void menu(){
        System.out.println("\n----- Selecione uma opção -----");
        System.out.println("1. Adicionar nova expressão aritmética");
        System.out.println("2. Resolver uma expressão existente");
        System.out.println("3. Imprimir representação da árvore");
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
        System.out.println(expTree.expression + " = " + expTree.evaluate());
    }

    public static void printTree(){

    }

    public static void main(String[] args) {
        int option = -1;

        do{
            menu();
            System.out.print("-> ");
            option = sc.nextInt();
            if(option == 0) break;

            switch (option) {
                case 1:
                    System.out.print("Insira a expressão \n-> ");
                    sc.nextLine();
                    String exp = sc.nextLine();

                    if(exp.isEmpty() || exp == null){
                        System.out.println("erro: expressão vazia!");
                        break;
                    }

                    ExpressionTree res = addExpression(new ExpressionTree(), exp);
                    if(res != null) expTree = res;

                    break;
                
                case 2:
                    if(expTree.isEmpty()){
                        System.out.println("erro: a árvore não foi montada");
                        break;
                    }
                    
                    solveExpression();
                    break;
            
                case 3:
                    if(expTree.isEmpty()){
                        System.out.println("erro: a árvore não foi montada");
                        break;
                    }

                    System.out.println(expTree.treeExpression());
                    break;
                    
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        } while(option != 0);

    }
}