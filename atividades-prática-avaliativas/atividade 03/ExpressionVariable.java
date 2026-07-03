/**
 * Representa uma variável em uma expressão aritmética
 * 
 * Livro - Trecho de Código 7.30
 */
public class ExpressionVariable extends ExpressionTerm{
    protected Integer var;

    // construtor
    public ExpressionVariable(Integer x){
        var = x;
    }

    public void setVariable(Integer x){
        this.var = x;
    }

    public Integer getValue(){
        return this.var;
    }

    public String toString(){
        return this.var.toString();
    }
}
