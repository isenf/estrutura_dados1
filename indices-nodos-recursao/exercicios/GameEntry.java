public class GameEntry{
    protected String name;
    protected int score;

    /**
     * construtor que cria um registro do jogo
     */
    public GameEntry(String n, int s){
        name = n;
        score = s;
    }

    /**
     * retorna o campo nome
     */
    public String getName(){
        return name;
    }

    /**
     * retorna o campo score
     */
    public int getScore(){
        return score;
    }

    /**
     * retorna a string com a representação desse registro
     */
    @Override
    public String toString(){
        return "(" + name + ", " + score + ")";
    }
}