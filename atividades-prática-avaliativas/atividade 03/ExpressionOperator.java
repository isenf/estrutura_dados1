/**
 * Classe abstrata que repesenta um operador em uma expressão aritmética
 * 
 * Livro - Trecho de Código 7.30 (inspiração)
 */
public abstract class ExpressionOperator extends ExpressionTerm {
    protected ExpressionTerm firstOperand, secondOperand;

    /** atribui um valor aos operandos de um operador 
     * 
     * @param left operador da esquerda
     * @param right operador da direita
    */
    public void setOperands(ExpressionTerm left, ExpressionTerm right){
        this.firstOperand = left;
        this.secondOperand = right;
    }

    /** retorna o operador da esquerda
     * 
     * @return operador da esquerda
     */
    public ExpressionTerm getLeftOperand(){
        return this.firstOperand;
    }

    /** retorna o operador da direita
     * 
     * @return operador da direita
     */
    public ExpressionTerm getRightOperand(){
        return this.secondOperand;
    }

    // métodos abstratos
    public abstract Double getValue();

    public abstract String toString();
}

