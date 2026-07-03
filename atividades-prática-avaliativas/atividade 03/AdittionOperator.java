/**
 * Representa o operador soma
 * 
 * Livro - Trecho de Código 7.30
 */
public class AdittionOperator extends ExpressionOperator {
    public Integer getValue(){
        return firstOperand + secondOperand;
    }

    public String toString(){
        return "+";
    }
}
