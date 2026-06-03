/** 
 * 
 * @author Dante Fabro
 */
public class Task{
    protected int id; // identificador da tarefa
    protected int exeTime;  // tempo de execução da tarefa
    protected final int fullTime; // o tempo inicial da tarefa
    protected int executions;    // quantidade de vezes que foi executada

    public Task(int i, int e, int u){
        this.id = i;
        this.exeTime = e;
        this.fullTime = e;
        this.executions = u;
    }

    public int getId(){
        return this.id;
    }

    public int getExeTime(){
        return this.exeTime;
    }

    public int getExecutions(){
        return this.executions;
    }

    public int getFullTime(){
        return this.fullTime;
    }

    public void setId(int newId){
        this.id = newId;
    }

    public void setExeTime(int newTime){
        this.exeTime = newTime;
    }

    public void setExecutions(int newValue){
        this.executions = newValue;
    }

    @Override // melhorar aqui depois
    public String toString(){
        return "Tarefa " + getId() + ":\nTempo total: " + this.fullTime + "\nExecuções: " + getExecutions();

    }

}