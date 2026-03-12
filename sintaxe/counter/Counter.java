public class Counter{
    protected int count;

    Counter(){
        count = 0;
    }

    public int getCount(){
        return count;
    }

    public int incrementCounter(){
        return count++;
    }

    public int decrementCounter(){
        return count--;
    }

    public static void main(String[] args){
        Counter contador = new Counter();

        contador.incrementCounter();
        System.out.println(contador.getCount());
    }
}