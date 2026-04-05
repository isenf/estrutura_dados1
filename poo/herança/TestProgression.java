/**
 * programa teste para as classes de progressão
 */
class TestProgression{
    public static void main(String[] args){
        Progression prog;
        prog = new ArithProgression();
        prog.printProgression(10);

        prog = new ArithProgression(5);
        prog.printProgression(10);

        prog = new GeomProgression();
        prog.printProgression(10);

        prog = new GeomProgression(3);
        prog.printProgression(10);

        prog = new FibonacciProgression();
        prog.printProgression(10);

        prog = new FibonacciProgression(10, 12);
        prog.printProgression(10);
    }
}