import java.util.Random;

/**
 * @author Dante Fabro
 */
public class Main{
    public static void main(String[] args){
        int numTasks = 10;
        Random rd = new Random(186);
        int quantum = 5;
        RoundRobin rr = new RoundRobin(quantum);
        long startTime = System.currentTimeMillis();
        int totalExecutions = 0;

        //System.out.println("Tempo inicial: " + startTime);

        for(int i = 0; i < numTasks; i++){
            Task t = new Task(i + 1, rd.nextInt(0, 100), 0);
            rr.addTask(t);
            //System.out.println(t);
        }

        while(!rr.hasTasks()){
            rr.executeQuantum();
            totalExecutions++;
        }

        rr.printReport();
        long endTime = System.currentTimeMillis();
        System.out.println("Total de execuções: " + totalExecutions);
        //System.out.println("Tempo final: " + endTime);
        System.out.println("Tempo de execução: " + (endTime - startTime) + " ms");
    }
}