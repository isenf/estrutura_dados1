public class LTTest{
    public static void main(String[] args) {
        LinkedTree<String> arvoregenerica = new LinkedTree();
        Position<String> raiz = arvoregenerica.addRoot("Vendas");
        
        PositionList<Position<String>> depth_1 = arvoregenerica.addChildren(new String[]{"Nacional", "Internacional"}, raiz);
        PositionList<Position<String>> depth_2 = arvoregenerica.addChildren(new String[]{"Canadá", "América do Sul", "Ultramar"}, depth_1.last().getElement());
        arvoregenerica.addChildren(new String[]{"África", "Europa", "Ásia", "Austrália"}, depth_2.last().getElement());
        //arvoregenerica.preorderPositions(raiz, new NodePositionList<>());

        System.out.println(LinkedTree.parentheticPreorder(arvoregenerica, raiz, 0));
    }
}