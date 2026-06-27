public class LTTest{
    public static void main(String[] args) {
        LinkedTree<String> arvoregenerica = new LinkedTree();
        Position<String> raiz = arvoregenerica.addRoot("Eletronics R'Us");
        
        PositionList<Position<String>> depth_1 = arvoregenerica.addChildren(new String[]{"P&D", "Vendas", "Compras", "Manufatura"}, raiz);
        Position<String> vendas = depth_1.next(depth_1.first()).getElement();
        PositionList<Position<String>> manufatura_c = arvoregenerica.addChildren(new String[]{"TV", "CD", "Tuner"}, depth_1.last().getElement());
        PositionList<Position<String>> vendas_c = arvoregenerica.addChildren(new String[]{"Nacional", "Internacional"}, vendas);
        PositionList<Position<String>> internacional_c = arvoregenerica.addChildren(new String[]{"Canadá", "América do Sul", "Ultramar"}, vendas_c.last().getElement());
        PositionList<Position<String>> ultramar_c = arvoregenerica.addChildren(new String[]{"África", "Europa", "Ásia", "Austrália"}, internacional_c.last().getElement());

        System.out.println(LinkedTree.parentheticPreorder(arvoregenerica, raiz, 0));

        Position<String> v = vendas_c.first().getElement();
        Position<String> w = ultramar_c.first().getElement();
        Position<String> ancestral = LinkedTree.findAncestor(arvoregenerica, v, w);
        System.out.println("\n\nAncestral comum mais baixo entre " + v.getElement() + " e " + w.getElement() + ": "+ ancestral.getElement());

        int height = LinkedTree.height2(arvoregenerica, raiz);
        System.out.println("Altura em relação à raiz: " + height);

        System.out.println("Preorder:\n" + LinkedTree.toStringPreorder(arvoregenerica, raiz));
        System.out.println("Postorder:\n" + LinkedTree.toStringPostorder(arvoregenerica, raiz));
    }
}