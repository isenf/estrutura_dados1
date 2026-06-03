/**
 * @author Dante Fabro
 */
public class RoundRobin{
    public final int QUANTUM;
    protected NodeQueue<Task> taskQueue;
    protected NodeQueue<Task> completeQueue;

    public RoundRobin(){
        this(5);
    }

    public RoundRobin(int q){
        this.QUANTUM = q;
        taskQueue = new NodeQueue<>();
        completeQueue = new NodeQueue<>();
    }

    public void addTask(Task task){
        taskQueue.enqueue(task);
    }

    public void executeQuantum(){
        Task task = taskQueue.front();

        if(task.getExeTime() - QUANTUM >= 0){
            task.setExeTime(task.getExeTime() - QUANTUM);
            taskQueue.front().setExecutions(task.executions + 1);
            taskQueue.enqueue(taskQueue.dequeue());
        } else{
            taskQueue.front().setExecutions(task.executions + 1);
            completeQueue.enqueue(taskQueue.dequeue());
        }
    }

    public boolean hasTasks(){
        return taskQueue.isEmpty();
    }

    public void printReport(){
        System.out.println(this.completeQueue);
    }
    
}