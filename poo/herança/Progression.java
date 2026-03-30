/**
 * classe de progressão numérica
 */

public class Progression{
    /** primeiro valor da progressão */
    public long first;
    /** valor atual da progressão */
    public long cur;

    /** construtor default */
    Progression(){
        cur = first = 0;
    }

    /** reinicializa a progressão co o valor inicial 
     * 
     * @return valor inicial
     */
    protected long firstValue(){
        cur = first;
        return cur;
    }

    /** avança a progressão para o próximo valor
     * 
     * @return próximo valor da progressão
     */
    protected long nextValue(){
        return ++cur;                   // próximo valor default
    }

    /** imprime os primeiros n valores da progressão
     * 
     * @param n número de valores a serem impressos
     */
    public void printProgression(int n){
        System.out.print(firstValue());

        for(int i = 2; i <= n; i++)
            System.out.print(" " + nextValue());

        System.out.println();           // termina  a linha
    }

}