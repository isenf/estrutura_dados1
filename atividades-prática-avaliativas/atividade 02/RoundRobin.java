/**
 * @author Dante Fabro
 */
public class RoundRobin{
    // melhorar esses identificadores aqui
    public final int QUANTUM;
    protected NodeQueue<Task> queueExec;
    protected NodeQueue<Task> queueEnded;

    public RoundRobin(){
        this(5);
    }

    public RoundRobin(int q){
        this.QUANTUM = q;
        queueExec = new NodeQueue<>();
        queueEnded = new NodeQueue<>();
    }

    public void addTask(Task task){
        queueExec.enqueue(task);
    }

    // nome temporário, voltar aqui depois
    public void removeTask(){
        Task elem = queueExec.front();
        if(elem.getExeTime() - QUANTUM >= 0){
            elem.setExeTime(elem.getExeTime() - QUANTUM);
            queueExec.enqueue(queueExec.dequeue());
            queueExec.front().setUntilComplete(queueExec.front().untilComplete + 1);
        } else{
            queueEnded.enqueue(queueExec.dequeue());
        }
    }

    // MUDAR O NOME DEPOIS AAAAAAAA
    public boolean noTask(){
        return queueExec.isEmpty();
    }

    public void printReport(){
        System.out.println(this.queueEnded);
    }

    public static void main(String[] args) {
        
    }
}