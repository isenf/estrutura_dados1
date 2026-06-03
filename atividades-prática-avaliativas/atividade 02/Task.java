/** classe que representa uma tarefa
 * 
 * @author Dante Fabro
 */
public class Task{
    protected int id; // identificador da tarefa
    protected int exeTime;  // tempo de execução da tarefa
    protected final int fullTime; // o tempo inicial da tarefa
    protected int executions;    // quantidade de vezes que foi executada

    // construtor
    public Task(int i, int e, int u){
        this.id = i;
        this.exeTime = e;
        this.fullTime = e;
        this.executions = u;
    }

    public Task(){
        this(0, 0, 0);
    }

    /** retorna o identificador da tarefa */
    public int getId(){
        return this.id;
    }

    /** retorna o tempo de execução restante da tarefa */
    public int getExeTime(){
        return this.exeTime;
    }

    /** retorna a quantidade de vezes que a tarefa foi executada */
    public int getExecutions(){
        return this.executions;
    }

    /** retorna o tempo inicial da tarefa */
    public int getFullTime(){
        return this.fullTime;
    }

    /** modifica o identificador */
    public void setId(int newId){
        this.id = newId;
    }

    /** modifica o tempo restante de execução */
    public void setExeTime(int newTime){
        this.exeTime = newTime;
    }

    /** modifica a quantidade de vezes que foi executada */
    public void setExecutions(int newValue){
        this.executions = newValue;
    }

    @Override // melhorar aqui depois
    public String toString(){
        return "Tarefa " + getId() + ":\nTempo total: " + this.fullTime + "\nExecuções: " + getExecutions();

    }

}