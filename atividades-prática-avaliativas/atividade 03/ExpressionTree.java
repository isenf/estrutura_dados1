/**
 * @author Dante Fabro
 */
public class ExpressionTree{
    private LinkedBinaryTree<String> tree;
    private Position<String> root;
    private String expression;
    public static final String OPERATORS = "+-*/";

    // construtores
    public ExpressionTree(){
        this.tree = new LinkedBinaryTree<>();
        this.root = null;
        this.expression = "";
    }

    public ExpressionTree(String e){
        this.tree = new LinkedBinaryTree<>();
        this.expression = e.replace("\\s+", "");
        this.root = null;
    }

    // métodos de verificação

    /** verifica se um caractere é um operador ou não */
    public boolean isOperator(char c){
        return OPERATORS.indexOf(c) != -1;
    }

    /** verifica se uma string pode ser convertida para número */
    public boolean isNumber(String s){
        try {
            Double.parseDouble(s);
            return true;
        } catch(Exception e){
            return false;
        }
        
    }

    private boolean hasOperator(String s){
        for(char c: s.toCharArray()){
            if(isOperator(c)) return true;
        }

        return false;
    }

    private boolean isValidExpression(String e){
        String exp = e.replace("\\s+", "");

        if(exp == null || e.isEmpty()) return false;
        
        if(!ParenMatch.validate(exp)) return false;

        while(exp.startsWith("(") && exp.endsWith(")")){
            exp = exp.substring(1, exp.length() -1);
        }

        if(isNumber(exp)) return true;

        if(!hasOperator(exp) || !validateOperators(exp)) return false;

        return true;
    }

    private boolean validateOperators(String e){
        for(int i = 0; i < e.length() -1; i++){
            char c = e.charAt(i);

            if(!isOperator(c)) continue;

            if(i == 0 || i == e.length()-1) return false;
            if(!(isNumber(String.valueOf(e.charAt(i-1))) || e.charAt(i-1) ==  ')')) return false;
            if(!(isNumber(String.valueOf(e.charAt(i+1))) || e.charAt(i+1) ==  '(')) return false;
        }

        return true;
    }




    // métodos auxiliares
    /** encontra o operador principal considerando os parenteses */
    public int findMainOperator(String e){
        int count = 0, index = -1;

        for(int i = 0; i < e.length(); i++){
            char c = e.charAt(i);

            if(count == 0 && isOperator(c)){
                index = i;
                break;
            }
            if(ParenMatch.isOpening(c)) count++;
            else if(ParenMatch.isClosing(c)) count--;
        }

        return index;
    }

    // métodos para construir a árvore
    /** tenta construir a árvore
     * 
     * @return true se é possível construir a árvore, false caso contrário
     */
    public boolean build(){
        if(this.expression == null || this.expression.isEmpty()) return false;
        if(!isValidExpression(this.expression)) return false;

        String exp = this.expression.replace("\\s+", "");

        try{
            this.root = buildRecursive(exp, null, true);
        } catch(Exception e){
            System.out.println("AAAAAAAAAAAA" + e);
            return false;
        }

        return true;
    }

    private Position<String> buildRecursive(String expression, Position<String> parent, boolean isLeft){
        if(expression.startsWith("(") && expression.endsWith(")")) expression = expression.substring(1, expression.length()-1);

        int operatorIdx = findMainOperator(expression);

        if(isNumber(expression) || operatorIdx == -1){   // caso base -> número (é nó folha ou raiz, se árvore contém apenas o número)
            if(parent == null){
                return tree.addRoot(expression);
            } 
            else{
                if(isLeft)
                    return tree.insertLeft(parent, expression);
                
                return tree.insertRight(parent, expression);
            }

        }

        String leftExp = expression.substring(0, operatorIdx).replaceAll("\\s+", "");
        String rightExp = expression.substring(operatorIdx + 1).replaceAll("\\s+", "");
        String operator = String.valueOf(expression.charAt(operatorIdx));

        Position<String> node;

        if(parent == null){
            node = tree.addRoot(operator);
        } 
        else{
            if(isLeft)
                node = tree.insertLeft(parent, operator);
            else
                node = tree.insertRight(parent, operator);
        }

        buildRecursive(leftExp, node, true);
        buildRecursive(rightExp, node, false);
        return node;

    }


    // testes dos métodos
    public static void main(String[] args){
        ExpressionTree exp = new ExpressionTree("(5-1)+(2*(5+1))");
        System.out.println(exp.isOperator('+'));
        System.out.println(exp.isNumber("6.7"));
        System.out.println(exp.build());

        
    }

}