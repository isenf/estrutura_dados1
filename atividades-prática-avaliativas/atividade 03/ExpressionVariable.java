/**
 * Representa uma variável em uma expressão aritmética
 * 
 * Livro - Trecho de Código 7.30
 */
public class ExpressionVariable extends ExpressionTerm{
    protected Double var;

    // construtor
    public ExpressionVariable(Double x){
        var = x;
    }

    /** atribui um valor à variável */
    public void setVariable(Double x){
        this.var = x;
    }

    @Override
    public Double getValue(){
        return this.var;
    }

    @Override
    public String toString(){
        return this.var.toString();
    }
}
