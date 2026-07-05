import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * classe que representa uma árvore binária própria de uma expressão aritmética
 * 
 * @author Dante Fabro
 */
public class ExpressionTree{
    protected ExpressionTerm root;
    protected String expression;
    public static final String OPERATORS = "+-*/";

    private int count = 0;
    private int spaces = 2;     // número de espaços

    // construtores

    // construtor default
    public ExpressionTree(){
        this.root = null;
        this.expression = "";
    }

    public ExpressionTree(String e){
        this.expression = e.replaceAll("\\s+", "");
        this.root = null;
    }

    // setters
    public void setExpression(String exp){
        this.expression = exp;
    }

    // métodos de verificação
    /** verifica se a árvore está vazia ou não
     * 
     * @return true se está vazia, else caso contrário
     */
    public boolean isEmpty(){
        return this.root == null;
    }

    /** verifica se um caractere é um operador ou não 
     * 
     * @return true se é um operador, else caso contrário
    */
    public boolean isOperator(char c){
        return OPERATORS.indexOf(c) != -1;
    }

    /** verifica se uma expressão dada tem caracteres válidos ou não
     * 
     * @param exp expressão a ser analisada
     * @return true se todos os caracteres são válidos. false caso contrário
     */
    private boolean hasValidCharacters(String exp){
        for(char c: exp.toCharArray()){
            if(!Character.isDigit(c) && !isOperator(c) && !ParenMatch.isOpening(c) && !ParenMatch.isClosing(c) && c != '.'){
                System.out.println("caractere inválido -> " + c);
                return false;

            } 
        }

        return true;
    }

    /** verifica a posição dos operadores em uma árvore
     * 
     * @return true se os operadores estão em posições válidas, false caso contrário
    */
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

    /** verifica se uma expressão tem os parenteses balanceados
     * 
     * @param exp expressão a ser verificada
     * @return true se tem os parenteses balanceados, false caso contrário
     */
    private boolean hasBalancedParen(String exp){
        return ParenMatch.validate(exp);
    }

    /** verifica se uma expressão é valida
     * 
     * @param exp expressão a ser verificada
     * @return true se a expressão é válida, false caso contrário
     */
    private boolean verifyExpression(String exp){
        exp = exp.replaceAll("\\s+", "");

        if(exp == null || exp.isEmpty()){
            System.out.println("erro: expressão vazia");
            return false;
        }

        if(!hasBalancedParen(exp)){
            System.out.println("erro: parenteses desbalanceados");
            return false;
        }

        if(!hasValidCharacters(exp)){
            System.out.println("erro: caracteres inválidos na expressão");
            return false;
        }

        if(!hasValidOperators(exp)){
            return false;
        }

        return true;

    }

    // métodos para construir a árvore

    /** 
     * tenta construir a árvore da expressão se a expressão é válida
     * 
     * @return true se a árvore foi construida com sucesso
     * @throws IllegalArgumentException mensagem de erro informando que expressão é inválida
     */
    public boolean build() throws IllegalArgumentException{
        if(!verifyExpression(this.expression)){
            throw new IllegalArgumentException("expressão inválida");
        }

        this.root = buildRecursive(this.expression.replaceAll("\\s+", ""));
        return true;
    }

    /** constrói a árvore recursivamente
     * 
     * @param exp expressão a ser considerada para construir a árvore
     * @return nodo (ExpressionTerm) raíz da árvore (ou subárvore)
     */
    private ExpressionTerm buildRecursive(String exp){

        if(!exp.startsWith("(")){ // caso base
            return new ExpressionVariable(Double.parseDouble(exp));
        }

        exp = exp.substring(1, exp.length()-1);
        int operatorIdx = findMainOperator(exp);

        if(operatorIdx == -1){  // é um número, caso base
            return new ExpressionVariable(Double.parseDouble(exp));
        }

        String leftExp = exp.substring(0, operatorIdx).replaceAll("\\s+", "");
        String rightExp = exp.substring(operatorIdx + 1).replaceAll("\\s+", "");
        Character operatorVal = exp.charAt(operatorIdx);

        ExpressionOperator operator = createOperator(operatorVal);

        // casos recursivos
        ExpressionTerm left = buildRecursive(leftExp);
        ExpressionTerm right = buildRecursive(rightExp);

        operator.setOperands(left, right);

        return operator;

    }

    /** cria e retorna um ExpressionOperator com base no caractere dado
     * 
     * @param operator caractere do operador
     * @return nodo (ExpressionOperator) com base no operador
     */
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

    /** calcula o resultado da expressão aritmética
     * 
     * @return resultado (Double) da expressão aritmética
     */
    public Double evaluate(){
        return root.getValue();
    }

    // métodos para desenhar a árvore
    
    /** desenha a árvore da expressão */
    public void draw(){
        if(root == null) return;

        List<NodeInfo> nodes = new ArrayList<>();   // para usar a lista dinÂmica do java
        calcPositions(root, nodes, 0);

        int maxX = 0, maxY = 0;

        for(NodeInfo node: nodes){
            if(node.getX() > maxX) maxX = node.getX();
            if(node.getY() > maxY) maxY = node.getY();
        }

        char[][] grid = new char[(maxY+1)*2 -1][(maxX+1) * this.spaces + 2];

        for(char[] row: grid) Arrays.fill(row, ' ');    // preenche com espaços vazios
        for(NodeInfo node: nodes){
            String val = (node.term instanceof ExpressionOperator)
                          ? node.term.toString() : formatNum(node.term.getValue()); // para formatar número
            int x = node.getX() * this.spaces;
            int y = node.getY() * 2; // elementos em indice par

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

    /** calcula as posições x e y de cada nodo da árvore pelo percurso inorder (interfixado)
     * 
     * @param term termo que as posições serão calculadas
     * @param nodes lista de NodeInfo para armazenar as informações calculadas
     * @param depth profundidade do nodo (posição y)
     */
    private void calcPositions(ExpressionTerm term, List<NodeInfo> nodes, int depth){
        if(term == null) return;

        if(term instanceof ExpressionVariable){
            nodes.add(new NodeInfo(term, count++, depth));
            // System.out.println(": " + term.toString());
        } 
        else{
            ExpressionOperator op = (ExpressionOperator) term;
            calcPositions(op.getLeftOperand(), nodes, depth+1);
            // System.out.println(": " + term.toString());
            nodes.add(new NodeInfo(op, count++, depth));
            calcPositions(op.getRightOperand(), nodes, depth+1);
        }
    }

    /** desenha as conexões entre os nodos da árvore
     * 
     * @param term termo "pai" a ser considerado
     * @param nodes lista de NodeInfo que armazena informações de posições
     * @param grid matrix de caracteres da árvore
     */
    private void drawConnections(ExpressionTerm term, List<NodeInfo> nodes, char[][] grid){
        if(term instanceof ExpressionVariable) return;

        ExpressionOperator op = (ExpressionOperator) term;
        NodeInfo parent = findNodeInfo(term, nodes);

        int px = parent.getX() * this.spaces;
        int py = parent.getY() * 2;

        // considera os filhos para desenhar as conexões
        drawChildConnections(op.getLeftOperand(), nodes, grid, px, py, '/');
        drawChildConnections(op.getRightOperand(), nodes, grid, px, py, '\\');

    }

    /** recebe as informações do pai e desenha as conexões considerando o filho
     * 
     * @param child filho a ser considerado
     * @param nodes lista de NodeInfo que armazena informações de posições
     * @param grid matrix de caracteres da árvore
     * @param px posição x do pai
     * @param py posição y do pai
     * @param symbol simbolo da conexão (/ ou \)
     */
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

    // métodos auxiliares

    /** encontra o índice operador principal considerando os parenteses 
     * 
     * @param exp expressão para buscar operador
     * @return índice do operador principal, retorna -1 se a expressão não tem nenhum operador
    */
    public int findMainOperator(String exp){
        int count = 0;

        for(int i = 0; i < exp.length(); i++){
            char c = exp.charAt(i);

            if(ParenMatch.isOpening(c)) count++;
            else if(ParenMatch.isClosing(c)) count--;
            else if(count == 0 && isOperator(c)){
                return i;
            }
        }
        return -1;
    }

    /** busca pelas informações de um termo em uma lista de informações
     * 
     * @param term termo a ser buscado
     * @param nodes lista de NodeInfo
     * @return objeto NodeInfo que corresponde ao termo em questão, retorna null se não encontra
     */
    private NodeInfo findNodeInfo(ExpressionTerm term, List<NodeInfo> nodes){
        for(NodeInfo node: nodes){
            if(node.term == term) return node;
        }

        return null;
    }

    /** formata um número considerando se é inteiro ou decimal
     * 
     * @param num número a ser formatado
     * @return string com o número
     */
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
    }

}