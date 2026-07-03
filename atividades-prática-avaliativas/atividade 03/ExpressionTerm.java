/**
 * Classe que representa um termo em uma expressão aritmética
 * 
 * Livro - Trecho de Código 7.30 
 */
public abstract class ExpressionTerm{
    /** retorna o valor numérico do termo
     * se é uma instância ExpressionVariable retorna o próprio número
     * se é uma instância ExpressionOperator a expressão é avaliada e retorna o resuktado
     * 
     * @return valor Double armazenado
     */
    public abstract Double getValue();

    /** retorna a representação String do termo
     * se é uma instância ExpressionVariable retorna o número como uma String
     * se é uma instância ExpressionOperator retorna o símbolo do operador aritmético
     * 
     * @return representação String do termo
     */
    public abstract String toString();
    
}