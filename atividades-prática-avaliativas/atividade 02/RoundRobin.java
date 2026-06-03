/** implementação de um escalonador Round-Robin utilizando filas 
 * 
 * @author Dante Fabro
 */
public class RoundRobin{
    public final int QUANTUM;
    protected NodeQueue<Task> taskQueue;
    protected NodeQueue<Task> completeQueue;

    // construtores
    public RoundRobin(){
        this(5);
    }

    public RoundRobin(int q){
        this.QUANTUM = q;
        taskQueue = new NodeQueue<>();
        completeQueue = new NodeQueue<>();
    }

    /** adiciona uma tarefa na fila de tarefas
     * 
     * @param task tarefa a ser adicionada
     */
    public void addTask(Task task){
        taskQueue.enqueue(task);
    }

    /**
     * executa um quantum: retira a primeira tarefa e decrementa o tempo de execução em um quantum
     */
    public void executeQuantum(){
        Task task = taskQueue.front();

        if(task.getExeTime() - QUANTUM >= 0){
            task.setExeTime(task.getExeTime() - QUANTUM);
            taskQueue.front().setExecutions(task.getExecutions() + 1);
            taskQueue.enqueue(taskQueue.dequeue());
        } else{
            taskQueue.front().setExecutions(task.getExecutions() + 1);
            completeQueue.enqueue(taskQueue.dequeue());
        }
    }

    /** verifica se tem tarefas ou não
     * 
     * @return true se tem tarefas, false caso contrário 
     */
    public boolean hasTasks(){
        return taskQueue.isEmpty();
    }

    /** 
     * imprime o relatório 
     */
    public void printReport(){
        int loss = 0;

        System.out.println("Fatia de tempo (1 quantum): " + QUANTUM);

        System.out.println("\n\nTarefas em ordem de finalização:\n");

        NodeG<Task> aux = completeQueue.head;
        while(aux != null){
            System.out.println("Tarefa " + aux.getElement().getId());
            System.out.println("Tempo total da tarefa: " + aux.getElement().getFullTime());
            System.out.println("Quantidade de execuções: " + aux.getElement().getExecutions());
            System.out.println();
            loss += aux.getElement().getExeTime();
            aux = aux.getNext();
        }

        System.out.println("Quantidade de tempo desperdiçado: " + loss);
    }
    
}