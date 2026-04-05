/**
 * progressão geométrica
 */

class GeomProgression extends Progression{
    /** base */
    protected long base;

    /** construtor default inicializa o valor base com 2 */
    public GeomProgression(){
        this(2);
    }

    /** construtor paramétrico fornece o valor base
     * 
     * @param b é o valor base da progressão
     */
    public GeomProgression(long b){
        base = b;
        first = 1;
        cur = first;
    }

    /** avança a progressão multiplicando a base pelo valor corrente
     * 
     * @return próximo valor da progressão
     */
    @Override
    protected long nextValue(){
        cur *= base;
        return cur;
    }

}
