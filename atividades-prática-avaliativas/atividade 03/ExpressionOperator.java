/**
 * Classe que repesenta um operador em uma expressão aritmética
 * 
 * Livro - Trecho de Código 7.30 (inspiração)
 */
public class ExpressionOperator extends ExpressionTerm {
    protected ExpressionTerm firstOperand, secondOperand;

    public void setOperands(ExpressionTerm x, ExpressionTerm y){
        this.firstOperand = x;
        this.secondOperand = y;
    }
}

