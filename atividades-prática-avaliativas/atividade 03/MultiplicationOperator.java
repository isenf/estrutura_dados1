/**
 * Representa o operador multiplicação
 * 
 * Livro - Trecho de Código 7.30 (inspiração)
 */
public class MultiplicationOperator extends ExpressionOperator {
    public Integer getValue(){
        return firstOperand * secondOperand;
    }

    public String toString(){
        return "*";
    }
}
