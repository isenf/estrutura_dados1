/**
 * Representa o operador divisão
 * 
 * Livro - Trecho de Código 7.30 (inspiração)
 */
public class DivisionOperator extends ExpressionOperator {
    @Override
    public Double getValue(){
        return (firstOperand.getValue() / secondOperand.getValue());
    }

    @Override
    public String toString(){
        return "/";
    }
}
