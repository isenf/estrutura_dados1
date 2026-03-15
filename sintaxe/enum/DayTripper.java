public class DayTripper{
    public enum Day {MON, TUE, WED, THU, FRI, SAT, SUN};

    public static void main(String[] args){
        Day d = Day.MON;
        System.out.println("Inicialmente d é " + d + " com valor ordinal " + d.ordinal());
        d = Day.WED;
        System.out.println("Agora é " + d.name() + " com valor ordinal " + d.ordinal());

        Day t = Day.valueOf("WED");
        System.out.println("d e t são os mesmos? " + (d == t));
    }
}