/**
 * Representa o operador multiplicação
 * 
 * Livro - Trecho de Código 7.30 (inspiração)
 */
public class MultiplicationOperator extends ExpressionOperator {
    public Double getValue(){
        return (firstOperand.getValue() * secondOperand.getValue());
    }

    public String toString(){
        return "*";
    }
}
