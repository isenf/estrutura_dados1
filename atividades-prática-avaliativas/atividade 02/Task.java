/** 
 * 
 * @author Dante Fabro
 */
public class Task{
    // procurar um identificador melhor para as variáveis
    protected int id; // identificador da tarefa
    protected int exeTime;  // tempo de execução da tarefa
    protected final int time; // ======================================================= falta um getter disso daqui ========================================
    protected int untilComplete;    // quantidade de vezes que ela foi a frente da fila

    public Task(int i, int e, int u){
        this.id = i;
        this.exeTime = e;
        this.time = e;
        this.untilComplete = u;
    }

    public int getId(){
        return this.id;
    }

    public int getExeTime(){
        return this.exeTime;
    }

    public int getUntilComplete(){
        return this.untilComplete;
    }

    public void setId(int newId){
        this.id = newId;
    }

    public void setExeTime(int newTime){
        this.exeTime = newTime;
    }

    public void setUntilComplete(int newValue){
        this.untilComplete = newValue;
    }

    @Override // melhorar aqui depois
    public String toString(){
        return "Tarefa " + getId() + ":\nTempo de execução: " + this.time + "\nAté completar: " + getUntilComplete();

    }

}