public class Scores{
    public static final int maxEntries = 10; // qtde de registros que serão armazenados
    protected int numEntries; // numero real de registros
    protected GameEntry[] entries; // arranjo de registros (nome e score)

    /**
     * construtor default
     */
    public Scores(){
        entries = new GameEntry[maxEntries];
        numEntries = 0;
    }

    /**
     * retorna uma representação string da lista de scores
     */
    public String toString(){
        String s = "[";

        for(int i = 0; i < numEntries; i++){
            if(i > 0){
                s += ", ";
            }
            s += entries[i];
        }

        return s + "]";
    }

    /**
     * tenta inserir um novo score na coleção (se ele for grande o suficiente)
     */
    public void add(GameEntry e){
        int newScore = e.getScore();

        if(numEntries == maxEntries){
            if(newScore <= entries[numEntries - 1].getScore()){
                return;
            }
        } else{
            numEntries++;
        }

        int i = numEntries - 1;

        for(; (i >= 1) && (newScore > entries[i-1].getScore()); i--)
            entries[i] = entries[i-1];

        entries[i] = e;
    }

    /**
     * remove e retorna o score armazenado no indice i
     */
    public GameEntry remove(int i) throws IndexOutOfBoundsException{
        if(i < 0 || i >= numEntries){
            throw new IndexOutOfBoundsException("invalid index: " + i);
        }

        GameEntry temp = entries[i];

        for(int j = i; j < numEntries - 1; j++)
            entries[j] = entries[j+1];

        entries[numEntries-1] = null;
        numEntries--;
        return temp;

    }

    public static void main(String[] args){
        
    }
}