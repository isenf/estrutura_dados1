import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author Dante Fabro
 */
public class ExpressionTree{
    protected ExpressionTerm root;
    protected String expression;
    public static final String OPERATORS = "+-*/";

    private int count = 0;
    private int spaces = 2;

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

    public boolean isEmpty(){
        return this.root == null;
    }

    /** verifica se um caractere é um operador ou não */
    public boolean isOperator(char c){
        return OPERATORS.indexOf(c) != -1;
    }

    private boolean hasValidCharacters(String exp){
        for(char c: exp.toCharArray()){
            if(!Character.isDigit(c) && !isOperator(c) && !ParenMatch.isOpening(c) && !ParenMatch.isClosing(c) && c != '.'){
                System.out.println("inválido -> " + c);
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
    public boolean build() throws IllegalArgumentException{
        if(!verifyExpression(expression)){
            throw new IllegalArgumentException("expressão inválida");
        }

        this.root = buildRecursive(expression.replaceAll("\\s+", ""));
        return true;
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

    // métodos para desenhar a árvore
    
    public void draw(){
        if(root == null) return;

        List<NodeInfo> nodes = new ArrayList<>();
        calcPositions(root, nodes, 0);

        int maxX = 0, maxY = 0;

        for(NodeInfo node: nodes){
            if(node.getX() > maxX) maxX = node.getX();
            if(node.getY() > maxY) maxY = node.getY();
        }

        char[][] grid = new char[(maxY+1)*2 -1][(maxX+1) * this.spaces + 2];

        for(char[] row: grid) Arrays.fill(row, ' ');
        for(NodeInfo node: nodes){
            String val = (node.term instanceof ExpressionOperator) ? node.term.toString() : formatNum(node.term.getValue());
            int x = node.getX() * this.spaces;
            int y = node.getY() * 2;

            for(int i = 0; (i < val.length()) && (x + i < grid[y].length); i++){
                grid[y][x+i] = val.charAt(i);
            }
        }

        drawConnections(root, nodes, grid);

        System.out.println();
        for(char[] row: grid){
            System.out.println(new String(row));
        }



    }

    private void calcPositions(ExpressionTerm term, List<NodeInfo> nodes, int depth){
        if(term == null) return;

        if(term instanceof ExpressionVariable){
            nodes.add(new NodeInfo(term, count++, depth));
            // System.out.println(": " + term.toString());
        } else{
            ExpressionOperator op = (ExpressionOperator) term;
            calcPositions(op.getLeftOperand(), nodes, depth+1);
            // System.out.println(": " + term.toString());
            nodes.add(new NodeInfo(op, count++, depth));
            calcPositions(op.getRightOperand(), nodes, depth+1);
        }
    }

    private void drawConnections(ExpressionTerm term, List<NodeInfo> nodes, char[][] grid){
        if(term instanceof ExpressionVariable) return;

        ExpressionOperator op = (ExpressionOperator) term;
        NodeInfo parent = findNodeInfo(term, nodes);

        int px = parent.getX() * this.spaces;
        int py = parent.getY() * 2;

        drawChildConnections(op.getLeftOperand(), nodes, grid, px, py, '/');
        drawChildConnections(op.getRightOperand(), nodes, grid, px, py, '\\');

    }

    
    private void drawChildConnections(ExpressionTerm child, List<NodeInfo> nodes, char[][] grid, 
                                      int px, int py, char symbol){
        if(child == null) return;

        NodeInfo childInfo = findNodeInfo(child, nodes);
        int val = symbol == '/' ? this.spaces/2  : -this.spaces/2 ;

        int cx = childInfo.getX() * this.spaces  + val;
        int cy = childInfo.getY() * 2 -1;

        if(cy < grid.length && cx < grid[cy].length){
            grid[cy][cx] = symbol;
        }

        drawConnections(child, nodes, grid);

    }

    /** busca pelas informações do nodo em uma lista de informações de informações */
    private NodeInfo findNodeInfo(ExpressionTerm term, List<NodeInfo> nodes){
        for(NodeInfo node: nodes){
            if(node.term == term) return node;
        }

        return null;
    }

    /** formata um número */
    private String formatNum(double num){
        if(num == (long) num){
            return String.valueOf((long) num);
        } else{
            return String.format("%.1f", num);
        }
    }

    // testes dos métodos
    public static void main(String[] args){
        String strExp = "((5-1)*(2*(6/2.1)))";

        ExpressionTree exp = new ExpressionTree(strExp);
        exp.build();
        System.out.println(exp.evaluate());
        System.out.println(exp.verifyExpression(exp.expression));

        exp.draw();
        // System.out.println(exp.height(exp.root));
    }

}