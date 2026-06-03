import java.util.Random;

/** classe executável
 * 
 * @author Dante Fabro
 */
public class Main{
    public static void main(String[] args){
        int numTasks = 5;
        Random rd = new Random(186);
        int quantum = 5;
        RoundRobin rr = new RoundRobin(quantum);
        long startTime = System.currentTimeMillis();
        int totalExecutions = 0;


        for(int i = 0; i < numTasks; i++){
            rr.addTask(new Task(i + 1, rd.nextInt(0, 100), 0));
        }

        while(!rr.hasTasks()){
            rr.executeQuantum();
            totalExecutions++;
        }

        rr.printReport();
        long endTime = System.currentTimeMillis();
        System.out.println("Total de execuções: " + totalExecutions);
        System.out.println("Tempo de execução: " + (endTime - startTime) + " ms");
    }
}