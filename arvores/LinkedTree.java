import java.util.Iterator;

/**
 * A linked class for a tree where nodes have an arbitrary number of children.
 *
 * @author Luca Vismara, Roberto Tamassia, Michael Goodrich, Eric Zamore
 */
public class LinkedTree<E> implements Tree<E> {

    protected TreePosition<E> root; // reference to the root
    protected int size;		  // number of nodes

    /**
     * Creates an empty tree.
     */
    public LinkedTree() {
        root = null;  // start with an empty tree
        size = 0;
    }

    /**
     * Returns the number of nodes in the tree.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns whether the tree is empty.
     */
    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     * Returns whether a node is internal.
     */
    @Override
    public boolean isInternal(Position<E> v) throws IllegalArgumentException {
        return !isExternal(v);
    }

    /**
     * Returns whether a node is external.
     */
    @Override
    public boolean isExternal(Position<E> v) throws IllegalArgumentException {
        TreePosition<E> vv = checkPosition(v);	// auxiliary method
        return (vv.getChildren() == null) || vv.getChildren().isEmpty();
    }

    /**
     * Returns whether a node is the root.
     */
    @Override
    public boolean isRoot(Position<E> v) throws IllegalArgumentException {
        checkPosition(v);
        return (v == root());
    }

    /**
     * Returns the root of the tree.
     */
    @Override
    public Position<E> root() throws IllegalArgumentException {
        if (root == null) {
            throw new IllegalArgumentException("The tree is empty");
        }
        return root;
    }

    /**
     * Returns the parent of a node.
     */
    @Override
    public Position<E> parent(Position<E> v)
            throws IllegalArgumentException {
        TreePosition<E> vv = checkPosition(v);
        Position<E> parentPos = vv.getParent();
        if (parentPos == null) {
            throw new IllegalArgumentException("No parent");
        }
        return parentPos;
    }

    /**
     * Returns an iterable collection of the children of a node.
     */
    @Override
    public Iterable<Position<E>> children(Position<E> v)
            throws IllegalArgumentException {
        TreePosition<E> vv = checkPosition(v);
        if (isExternal(v)) {
            throw new IllegalArgumentException("External nodes have no children");
        }
        return vv.getChildren();
    }

    /** adds a new node as a child to an existent parent node 
     * 
     * @param element element to be storaged in the new node
     * @param parent the existing parent node
     * 
     * @return the newly created TreePosition
     */
    public Position<E> addChild(E element, Position parent){
        TreePosition<E> parentPos = checkPosition(parent);
        TreePosition<E> child = createNode(element, parentPos, null);

        PositionList<Position<E>> children = parentPos.getChildren();

        if(children == null){
            children = new NodePositionList<>();
        }

        children.addLast(child);
        parentPos.setChildren(children);
        
        return child;
    }

    /** adds multiple children to a given existing parent node and returns the newly created children
     * 
     * @param elements the array of elements to be added as a child
     * @param parent the existing parent node
     * 
     * @return the PositionList of the new children
     */
    public PositionList<Position<E>> addChildren(E[] elements, Position<E> parent){
        PositionList<Position<E>> newChildren = new NodePositionList<>();

        for(E e: elements){
            Position<E> newChild = addChild(e, parent);
            newChildren.addLast(newChild);
        }

        return newChildren;
    }

    /**
     * Returns an iterable collection of the tree nodes.
     */
    @Override
    public Iterable<Position<E>> positions() {
        PositionList<Position<E>> positions = new NodePositionList<Position<E>>();
        if (size != 0) {
            preorderPositions(root(), positions);  // assign positions in preorder
        }
        return positions;
    }

    /**
     * Returns an iterator of the elements stored at the nodes
     */
    @Override
    public Iterator<E> iterator() {
        Iterable<Position<E>> positions = positions();
        PositionList<E> elements = new NodePositionList<E>();
        for (Position<E> pos : positions) {
            elements.addLast(pos.getElement());
        }
        return elements.iterator();  // An iterator of elements
    }

    /**
     * Replaces the element at a node.
     */
    @Override
    public E replace(Position<E> v, E o)
            throws IllegalArgumentException {
        TreePosition<E> vv = checkPosition(v);
        E temp = v.getElement();
        vv.setElement(o);
        return temp;
    }

    // Additional update methods

    /**
     * Adds a root node to an empty tree
     */
    public Position<E> addRoot(E e) throws IllegalArgumentException {
        if (!isEmpty()) {
            throw new IllegalArgumentException("Tree already has a root");
        }
        size = 1;
        root = createNode(e, null, null);
        return root;
    }

    /**
     * Swap the elements at two nodes
     */
    public void swapElements(Position<E> v, Position<E> w)
            throws IllegalArgumentException {
        TreePosition<E> vv = checkPosition(v);
        TreePosition<E> ww = checkPosition(w);
        E temp = w.getElement();
        ww.setElement(v.getElement());
        vv.setElement(temp);
    }
    // Auxiliary methods

    /**
     * If v is a good tree node, cast to TreePosition, else throw exception
     */
    protected TreePosition<E> checkPosition(Position<E> v)
            throws IllegalArgumentException {
        if (v == null || !(v instanceof TreePosition)) {
            throw new IllegalArgumentException("The position is invalid");
        }
        return (TreePosition<E>) v;
    }

    /**
     * Creates a new tree node
     */
    protected TreePosition<E> createNode(E element, TreePosition<E> parent,
            PositionList<Position<E>> children) {
        return new TreeNode<E>(element, parent, children);
    }

    /**
     * Creates a list storing the the nodes in the subtree of a node, ordered
     * according to the preorder traversal of the subtree.
     */
    protected void preorderPositions(Position<E> v, PositionList<Position<E>> pos)
            throws IllegalArgumentException {
        pos.addLast(v);
        System.out.println(v.getElement());

        if(isExternal(v)) return;
        for (Position<E> w : children(v)) {
            preorderPositions(w, pos);	// recurse on each child
        }
    }

    /** computa a profundidade de um nodo v em uma árvore T 
     * trecho de código 7.3
     */
    public static<E> int depth(Tree<E> T, Position<E> v){
        if(T.isRoot(v)) return 0;

        return 1 + depth(T, T.parent(v));
    }

    /** computa a altura de uma árvore
     * 
     * trecho de código 7.5 
     */
    public static <E> int height1(Tree<E> T){        
        int h = 0;
        for(Position<E> v: T.positions()){
            h = Math.max(h, depth(T, v));
        }

        return h;
    }

    /** computa a altura de uma árvore
     * 
     * trecho de código 7.7
     */
    public static <E> int height2(Tree<E> T, Position<E> v){
        if(T.isExternal(v)) return 0;

        int h = 0;
        for(Position<E> w: T.children(v))
            h = Math.max(h, height2(T, w));

        return 1 + h;
    }

    /** retorna uma string com o percurso preorder dos elementos na subárvore do nodo v de T */
    public static <E> String toStringPreorder(Tree<E> T, Position<E> v){
        String s = v.getElement().toString();

        if(T.isExternal(v)) return s;
        for(Position<E> w: T.children(v))
            s += ", " + toStringPreorder(T, w);
        
        return s;
    }

    public static <E> String toStringPostorder(Tree<E> T, Position<E> v){
        String s = "";

        if(T.isInternal(v)){
            for(Position<E> w: T.children(v))
                s += toStringPostorder(T, w) + ", ";
        }
        
        s += v.getElement();
        return s;
    }

    /** retorna uma string com uma representação entre parenteses de uma árvore usando percurso preorder
     * 
     * @param T árvore a ser percorrida
     * @param v nodo considerado como raiz da subárvore a ser representada
     * @param depth profundidade do nodo v da árvore
     * 
     * @return string que representa a subárvore enraizada em v
     */
    public static<E> String parentheticPreorder(Tree<E> T, Position<E> v, int depth){
        String s = v.getElement().toString();
        String indent = "  ".repeat(depth);
        if(T.isExternal(v)) return s;

        TreePosition<E> vv = (TreePosition) v;
        boolean firstTime = true;
        for(Position<E> u: vv.getChildren()){
            if(firstTime){
                firstTime = false;
                s += " (\n";
            }
            
                s += indent + parentheticPreorder(T, u, depth+1) + "\n";
        }

        s += indent + ")";

        return s;
    }

    /** procura o ancestral comum mais baixo (Lowest Common Ancestor -LCA) de dois nodos de uma árvore, chamando um método protegido recursivo
     * 
     * @param T árvore que contém os nodos v e w
     * @param v primeiro nodo
     * @param w segundo nodo
     * 
     * @return posição do ancestral comum mais baixo entre v e w
     */
    public static <E> Position<E> findAncestor(Tree<E> T, Position<E> v, Position<E> w){
        return findAncestor(T, v, w, depth(T, v), depth(T, w));
    }

    /** método protegido e recursivo que procura o ancestral comum mais baixo (Lowest Common Ancestor -LCA) de dois nodos de uma árvore
     * 
     * @param T árvore que contém os nodos v e w
     * @param v primeiro nodo
     * @param w segundo nodo
     * @param depth_v profundidade do primeiro nodo
     * @param depth_w profundidade do segundo nodo
     * 
     * @return posição do ancestral comum mais baixo entre v e w
     */
    protected static <E> Position<E> findAncestor(Tree<E> T, Position<E> v, Position<E> w, int depth_v, int depth_w){
        if(depth_v > depth_w) return findAncestor(T, T.parent(v), w, depth_v - 1, depth_w);
        else if(depth_v < depth_w) return findAncestor(T, v, T.parent(w), depth_v, depth_w-1);

        if(v != w) return findAncestor(T, T.parent(v), T.parent(w), depth_v-1, depth_w-1);
        return v;
    }

    /** procura o ancestral comum mais baixo (Lowest Common Ancestor -LCA) de dois nodos de uma árvore, chamando um método protegido iterativo
     * 
     * @param T árvore que contém os nodos v e w
     * @param v primeiro nodo
     * @param w segundo nodo
     * 
     * @return posição do ancestral comum mais baixo entre v e w
     */
    public static <E> Position<E> findAncestorIterative(Tree<E> T, Position<E> v, Position<E> w){
        return findAncestorIterative(T, v, w, depth(T, v), depth(T, w));
    }

    /** método protegido e iterativo que procura o ancestral comum mais baixo (Lowest Common Ancestor -LCA) de dois nodos de uma árvore
     * 
     * @param T árvore que contém os nodos v e w
     * @param v primeiro nodo
     * @param w segundo nodo
     * @param depth_v profundidade do primeiro nodo
     * @param depth_w profundidade do segundo nodo
     * 
     * @return posição do ancestral comum mais baixo entre v e w
     */
    protected static <E> Position<E> findAncestorIterative(Tree<E> T, Position<E> v, Position<E> w, int depth_v, int depth_w){
        while(depth_v > depth_w){
            v = T.parent(v);
            depth_v--;
        }

        while(depth_w > depth_v){
            w = T.parent(w);
            depth_w--;
        }

        while(v != w){
            v = T.parent(v); depth_v--;
            w = T.parent(w); depth_w--;
        }

        return v;
    }
}
