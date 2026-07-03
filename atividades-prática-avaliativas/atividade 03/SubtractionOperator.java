/**
 * Representa o operador subtração
 * 
 * Livro - Trecho de Código 7.30 (inspiração)
 */
public class SubtractionOperator extends ExpressionOperator {
    public Double getValue(){
        return (firstOperand.getValue() - secondOperand.getValue());
    }

    public String toString(){
        return "-";
    }
}
