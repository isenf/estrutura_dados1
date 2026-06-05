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
        //doOperation();

        if(task.getExeTime() - QUANTUM > 0){
            task.setExeTime(task.getExeTime() - QUANTUM);
            task.setExecutions(task.getExecutions() + 1);
            taskQueue.enqueue(taskQueue.dequeue());
        } else{
            task.setExecutions(task.getExecutions() + 1);
            task.setExeTime(0);
            completeQueue.enqueue(taskQueue.dequeue());
        }
    }

    /** verifica se tem tarefas ou não
     * 
     * @return true se tem tarefas, false caso contrário 
     */
    public boolean hasTasks(){
        return !taskQueue.isEmpty();
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
            int wasted = (aux.getElement().getExecutions() * QUANTUM) - aux.getElement().getFullTime();

            System.out.println("Tarefa " + aux.getElement().getId());
            System.out.println("Tempo total da tarefa: " + aux.getElement().getFullTime());
            System.out.println("Tempo desperdiçado: " + wasted);
            System.out.println("Quantidade de execuções: " + aux.getElement().getExecutions());
            System.out.println();

            loss += wasted;
            aux = aux.getNext();
        }

        System.out.println("Quantidade de tempo desperdiçado: " + loss);
        System.out.println("Maior tempo gasto em uma tarefa: " + completeQueue.tail.getElement().getFullTime());
        System.out.println("Menor tempo gasto em uma tarefa: " + completeQueue.head.getElement().getFullTime());
    }

    /** simula o processamento de uma tarefa executando operação matemática aleatória */
    public void doOperation(){
        int index = (int)(Math.random() * 5); 
        double num = Math.random() * 1000;
        
        switch(index){
            case 0: for(int i = 0; i < 100; i++) Math.log(num);
                break;

            case 1: for(int i = 0; i < 500; i++) Math.exp(num);
                break;
            
            case 2: for(int i = 0; i < 1000; i++) Math.pow(num, 2);
                break;

            case 3: Math.sin(num);
                break;
            
            case 4: Math.hypot(num, 10);
                break;
        }
    }
}