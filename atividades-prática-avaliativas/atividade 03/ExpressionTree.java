/**
 * 
 * @author Dante Fabro
 */
public class ExpressionTree{
    protected ExpressionTerm root;
    protected String expression;
    public static final String OPERATORS = "+-*/";

    // construtores
    public ExpressionTree(){
        this.root = null;
        this.expression = "";
    }

    public ExpressionTree(String e){
        this.expression = e.replaceAll("\\s+", "");
        this.root = null;
    }

    // métodos de verificação

    /** verifica se um caractere é um operador ou não */
    public boolean isOperator(char c){
        return OPERATORS.indexOf(c) != -1;
    }

    private boolean hasOperator(String exp){
        for(char c: exp.toCharArray()){
            if(isOperator(c)) return true;
        }

        return false;
    }

    private boolean hasValidCharacters(String exp){
        for(char c: exp.toCharArray()){
            if(!Character.isDigit(c) && !isOperator(c) && !ParenMatch.isOpening(c) && !ParenMatch.isClosing(c)){
                System.out.println("invalido -> " + c);
                return false;

            } 
        }

        return true;
    }

    /** verifica a posição dos operadores em uma árvore */
    private boolean hasValidOperators(String exp){
        for(int i = 0; i < exp.length() - 1; i++){
            char c = exp.charAt(i);
            char next = exp.charAt(i+1);

            if(isOperator(c) && isOperator(next)){
                System.out.println("erro: dois operadores consecultivos");
                return false;
            }

            if(ParenMatch.isOpening(c) && isOperator(next)){
                System.out.println("erro: parenteses seguido de número");
                return false;
            }

            if(ParenMatch.isClosing(next) && isOperator(c)){
                System.out.println("erro: número seguido de parenteses");
                return false;
            }

    
        }

        return true;
    }

    private boolean hasBalancedParen(String expression){
        return ParenMatch.validate(expression);
    }

    /** verifica uma expressão */
    private boolean verifyExpression(String expression){
        expression = expression.replaceAll("\\s+", "");

        if(expression == null || expression.isEmpty()){
            System.out.println("erro: expressão vazia");
            return false;
        }

        if(!hasBalancedParen(expression)){
            System.out.println("erro: parenteses desbalanceados");
            return false;
        }

        if(!hasValidCharacters(expression)){
            System.out.println("erro: caracteres inválidos na expressão");
            return false;
        }

        if(!hasValidOperators(expression)){
            return false;
        }

        return true;

    }

    // métodos auxiliares
    /** encontra o operador principal considerando os parenteses */
    public int findMainOperator(String e){
        int count = 0, index = -1;

        for(int i = 0; i < e.length(); i++){
            char c = e.charAt(i);

            if(ParenMatch.isOpening(c)) count++;
            else if(ParenMatch.isClosing(c)) count--;
            else if(count == 0 && isOperator(c)){
                return i;
            }
        }
        return -1;
    }

    // métodos para construir a árvore
    /** 
     * tenta construir a árvore
     */
    public void build() throws IllegalArgumentException{
        if(!verifyExpression(expression)){
            throw new IllegalArgumentException("expressão inválida");
        }

        this.root = buildRecursive(expression.replaceAll("\\s+", ""));
    }

    /** */
    private ExpressionTerm buildRecursive(String expression){

        if(!expression.startsWith("(")){
            return new ExpressionVariable(Double.parseDouble(expression));
        }

        expression = expression.substring(1, expression.length()-1);
        int operatorIdx = findMainOperator(expression);

        if(operatorIdx == -1){
            return new ExpressionVariable(Double.parseDouble(expression));
        }

        String leftExp = expression.substring(0, operatorIdx).replaceAll("\\s+", "");
        String rightExp = expression.substring(operatorIdx + 1).replaceAll("\\s+", "");
        Character operatorVal = expression.charAt(operatorIdx);

        ExpressionOperator operator = createOperator(operatorVal);

        ExpressionTerm left = buildRecursive(leftExp);
        ExpressionTerm right = buildRecursive(rightExp);

        operator.setOperands(left, right);

        return operator;

    }

    /** retorna um ExpressionOperator com base no caractere do operador */
    private ExpressionOperator createOperator(char operator){
        switch (operator) {
            case '+':
                return new AddittionOperator();
            
            case '-':
                return new SubtractionOperator();

            case '*':
                return new MultiplicationOperator();

            case '/':
                return new DivisionOperator();
        
            default:
                throw new IllegalArgumentException("operador inválido");
        }
    }

    /** método que retorna o resultado da expressão aritmética */
    public Double evaluate(){
        return root.getValue();
    }

    /** realza o percurso inorder para retornar a expressão 
     * meio inutil kkkkk
     */
    public String stringExpression(ExpressionTerm term){
        if(term == null) return "";

        if(term instanceof ExpressionVariable) return term.toString();

        String s = "";
        ExpressionOperator operator = (ExpressionOperator) term;
        s += "(" + stringExpression(operator.firstOperand);
        s += operator.toString();
        s += stringExpression(operator.secondOperand) + ")";

        return s;
    }
    

    // testes dos métodos
    public static void main(String[] args){
        String strExp = "((5--1)*(2*(6/2)))";

        ExpressionTree exp = new ExpressionTree(strExp);
        exp.build();
        System.out.println(exp.evaluate());
        System.out.println(exp.verifyExpression(exp.expression));
        System.out.println(exp.stringExpression(exp.root));
    }

}