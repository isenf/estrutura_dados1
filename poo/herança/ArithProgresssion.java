/**
 * progressão aritmética
 */
class ArithProgression extends Progression{
    /** incremento */
    protected long inc;                 // variáveis first e cur são herdadas

    /** construtor default inicializa com incremento 1 */
    ArithProgression(){
        this(1);
    }

    /** construtor paramétrico fornece o incremento */
    ArithProgression(long increment){
        inc = increment;
    }

    /** avança para a próxima progressão acrescentando o incremento no valor atual
     * 
     * @return próximo valor da progressão
     */
    protected long nextValue(){
        cur += inc;
        return cur;
    }
    

}
