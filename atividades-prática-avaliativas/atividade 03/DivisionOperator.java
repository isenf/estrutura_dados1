/**
 * Representa o operador divisão
 * 
 * Livro - Trecho de Código 7.30 (inspiração)
 */
public class DivisionOperator extends ExpressionOperator {
    public Integer getValue(){
        return firstOperand / secondOperand;
    }

    public String toString(){
        return "/";
    }
}
