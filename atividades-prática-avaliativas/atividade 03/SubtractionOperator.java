/**
 * Representa o operador subtração
 * 
 * Livro - Trecho de Código 7.30 (inspiração)
 */
public class SubtractionOperator extends ExpressionOperator {
    public Integer getValue(){
        return firstOperand - secondOperand;
    }

    public String toString(){
        return "-";
    }
}
