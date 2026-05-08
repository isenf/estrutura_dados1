/** subclasse que herda os métodos da classe DList e usa o Contact */
public class ContactList extends DList<Contact>{

        /** ordena a lista de contatos pelo nome */
    public void sortByName(){
        if(size() <= 1) return;
    
        DNode<Contact> cur = getFirst();
        DNode<Contact> ins, pivot;

        while(getLast() != cur){
            pivot = cur.getNext();
            remove(pivot);
            ins = cur;

            while(hasPrev(ins) && ins.getElement().getName().compareTo(pivot.getElement().getName()) > 0){
                ins = ins.getPrev();
            }

            addAfter(ins, pivot);

            if(ins == cur){
                cur = cur.getNext();
            }

        }
    }

    /** ordena a lista de contatos pela idade em ordem crescente */
    public void sortByAge(){
        if(this.size <= 1) return;

        DNode<Contact> cur = getFirst();
        DNode<Contact> pivot, ins;

        while(cur != getLast()){
            pivot = cur.getNext();
            remove(pivot);
            ins = cur;

            while(hasPrev(ins) && ins.getElement().getAge() > pivot.getElement().getAge()){
                ins = ins.getPrev();
            }

            addAfter(ins, pivot);

            if(ins == cur) cur = cur.getNext();

        }

    }

    /** consulta na lista de contatos usando o nome
     * 
     * @param n nome a ser consultado 
     */
    public DList<Contact> findByName(String n){
        DList<Contact> L = new DList<>();
        DNode<Contact> cur = (DNode<Contact>) this.getFirst();

        while(cur != this.tail){
            if(cur.getElement().getName().equals(n)){
                L.addLast(cur);
            }

            cur = cur.getNext();
        }

        return L;

    }

    /** imprime do cabeçalho até a cauda */
    public void print(){
        DNode<Contact> cur = getFirst();

        while(cur != tail){
            System.out.println(cur.getElement());
            cur = cur.getNext();
        }

    }

    /** percorre a lista e imprime da cauda até o cabeçalho */
    public void printInv(){
        DNode<Contact> cur = getLast();

        while(cur != this.header){
            System.out.println(cur.getElement());
            cur = cur.getPrev();
        }

    }

    /** recebe um nome de contato e modifica para um novo nome */
    public void modifyName(String name, String newName){
        DNode<Contact> cur = getFirst();
        while(cur != getLast()){
            if(cur.getElement().getName().equals(name)){
                cur.getElement().setName(newName);
            }
            cur = cur.getNext();
        }
    }

    /** remove um nodo se ele tem o mesmo cpf informado */
    public boolean removeByCpf(String cpf){
        DNode<Contact> cur = getFirst();
        while(cur != getLast()){
            if(cur.getElement().getCPF().compareTo(cpf) == 0){
                remove(cur);
                return true;
            }
            cur = cur.getNext();
        }
        return false;
    }

    /** imprime a lista de contatos */
    @Override
    public String toString(){
        String s = "" + getFirst();
        DNode<Contact> aux = getFirst().getNext();

        while(aux != this.tail){
            s += aux.getElement();
            aux = aux.getNext();

            if(aux != this.tail) s += ",";
            
        }

        return s;
    }
}