public class Main{
    public static void main(String[] args){
        DList<Contact> agenda = new DList<>();
        DNode<Contact> c1 = new DNode<>(new Contact("123.456.789-00", "Dante", 20, "98-76543210", "teste@exemple.com"), null, null);
        DNode<Contact> c2 = new DNode<>(new Contact("123.456.789-00", "Ante", 119, "98-76543210", "teste@exemple.com"), null, null);
        DNode<Contact> c4 = new DNode<>(new Contact("123.456.789-00", "Nte", 19, "98-76543210", "teste@exemple.com"), null, null);
        DNode<Contact> c3 = new DNode<>(new Contact("123.456.789-00", "Te", 109, "98-76543210", "teste@exemple.com"), null, null);

        agenda.addFirst(c4);
        agenda.addFirst(c3);
        agenda.addFirst(c2);
        agenda.addFirst(c1);

        DList.sortByAge(agenda);
        System.out.println(agenda);
        System.out.println(agenda.size());

        DList.printInv(agenda);
    }
}