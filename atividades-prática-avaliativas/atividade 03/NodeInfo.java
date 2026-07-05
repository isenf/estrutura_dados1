/**
 * classe que guarda um nó, posições x e y e representação string
 * 
 * @author Dante Fabro
 */
public class NodeInfo {
    ExpressionTerm term;
    int x; // numero de nodos visitados antes no percurso inorder
    int y; // profundidade
    String value;


    public NodeInfo(ExpressionTerm t, int x, int y){
        this.x = x;
        this.y = y;
        this.term = t;
        this.value = t.toString();
    }

    /** retorna a posição x */
    public int getX(){
        return this.x;
    }

    /** retorna a posição y */
    public int getY(){
        return this.y;
    }

    /** retorna o valor do termo */
    public String getValue(){
        return this.value;
    }

    /** mofica o valor de x */
    public void setX(int newX){
        this.x = newX;
    }
    
    /** modifica o valor de y */
    public void setY(int newY){
        this.y = newY;
    }

}
