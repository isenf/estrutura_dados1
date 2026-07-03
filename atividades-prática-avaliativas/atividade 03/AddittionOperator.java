/**
 * Representa o operador soma
 * 
 * Livro - Trecho de Código 7.30
 */
public class AddittionOperator extends ExpressionOperator {
    @Override
    public Double getValue(){
        return (firstOperand.getValue() + secondOperand.getValue());
    }

    @Override
    public String toString(){
        return "+";
    }
}
