/**
 * progressão baseada fibonnaci
 */
class FibonacciProgression extends Progression{
    /** valor anterior */
    long prev;

    /** construtor default inicializa os dois primeiros valores como sendo 0 e 1 */
    public FibonacciProgression(){
        this(0, 1);
    }

    /** construtor paramétrico fornece o primeiro e o segundo valor
     * 
     * @param val1 primeiro valor
     * @param val2 segundo valor
     */
    public FibonacciProgression(long val1, long val2){
        first = val1;
        prev = val2 - val1; // valor fictício que antecede o primeiro
    }

    /** avança a progressão somando o valor anterior no valor atual
     * 
     * @return próximo valor da progressão
     */
    @Override
    protected long nextValue(){
        long temp = prev;
        prev = cur;
        cur += temp;
        return cur;
    }

    

    

}