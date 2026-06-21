import java.util.Iterator;

/**
 * An implementation of the BinaryTree interface by means of a linked structure.
 * This class serves as a superclass for the
 * BinarySearchTree implementation. This design decision was made to emphasize
 * the conceptual relationship that a BinarySearchTree is a specialized
 * LinkedBinaryTree. An unwanted side-effect of this is that the
 * {@link #size() size} method returns the number of total nodes whereas the
 * {@link BinarySearchTree#size() size} method in the
 * {@link BinarySearchTree BinarySearchTree} class returns the number of
 * internal nodes only. For this reason, the the {@link #size
 * size} variable instead of the {@link #size() size} method is used within this
 * class.
 *
 * @author Luca Vismara, Roberto Tamassia, Michael Goodrich, Eric Zamore
 * @see BinaryTree
 */
public class LinkedBinaryTree<E> implements BinaryTree<E> {

    protected BTPosition<E> root;	// reference to the root
    protected int size;		// number of nodes

    /**
     * Creates an empty binary tree.
     */
    public LinkedBinaryTree() {
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
    public boolean isInternal(Position<E> v) throws IllegalStateException {
        checkPosition(v);		// auxiliary method
        return (hasLeft(v) || hasRight(v));
    }

    /**
     * Returns whether a node is external.
     */
    @Override
    public boolean isExternal(Position<E> v) throws IllegalStateException {
        return !isInternal(v);
    }

    /**
     * Returns whether a node is the root.
     */
    @Override
    public boolean isRoot(Position<E> v) throws IllegalStateException {
        checkPosition(v);
        return (v == root());
    }

    /**
     * Returns whether a node has a left child.
     */
    @Override
    public boolean hasLeft(Position<E> v) throws IllegalStateException {
        BTPosition<E> vv = checkPosition(v);
        return (vv.getLeft() != null);
    }

    /**
     * Returns whether a node has a right child.
     */
    @Override
    public boolean hasRight(Position<E> v) throws IllegalStateException {
        BTPosition<E> vv = checkPosition(v);
        return (vv.getRight() != null);
    }

    /**
     * Returns the root of the tree.
     */
    @Override
    public Position<E> root() throws IllegalStateException {
        if (root == null) {
            throw new IllegalStateException("The tree is empty");
        }
        return root;
    }

    /**
     * Returns the left child of a node.
     */
    @Override
    public Position<E> left(Position<E> v)
            throws IllegalStateException {
        BTPosition<E> vv = checkPosition(v);
        Position<E> leftPos = vv.getLeft();
        if (leftPos == null) {
            throw new IllegalStateException("No left child");
        }
        return leftPos;
    }

    /**
     * Returns the right child of a node.
     */
    @Override
    public Position<E> right(Position<E> v)
            throws IllegalStateException {
        BTPosition<E> vv = checkPosition(v);
        Position<E> rightPos = vv.getRight();
        if (rightPos == null) {
            throw new IllegalStateException("No right child");
        }
        return rightPos;
    }

    /**
     * Returns the parent of a node.
     */
    @Override
    public Position<E> parent(Position<E> v)
            throws IllegalStateException {
        BTPosition<E> vv = checkPosition(v);
        Position<E> parentPos = vv.getParent();
        if (parentPos == null) {
            throw new IllegalStateException("No parent");
        }
        return parentPos;
    }

    /**
     * Returns an iterable collection of the children of a node.
     */
    @Override
    public Iterable<Position<E>> children(Position<E> v)
            throws IllegalStateException {
        PositionList<Position<E>> children = new NodePositionList<Position<E>>();
        if (hasLeft(v)) {
            children.addLast(left(v));
        }
        if (hasRight(v)) {
            children.addLast(right(v));
        }
        return children;
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
            throws IllegalStateException {
        BTPosition<E> vv = checkPosition(v);
        E temp = v.getElement();
        vv.setElement(o);
        return temp;
    }

    // Additional accessor method

    /**
     * Return the sibling of a node
     */
    public Position<E> sibling(Position<E> v)
            throws IllegalStateException {
        BTPosition<E> vv = checkPosition(v);
        BTPosition<E> parentPos = vv.getParent();
        if (parentPos != null) {
            BTPosition<E> sibPos;
            BTPosition<E> leftPos = parentPos.getLeft();
            if (leftPos == vv) {
                sibPos = parentPos.getRight();
            } else {
                sibPos = parentPos.getLeft();
            }
            if (sibPos != null) {
                return sibPos;
            }
        }
        throw new IllegalStateException("No sibling");
    }
    // Additional update methods

    /**
     * Adds a root node to an empty tree
     */
    public Position<E> addRoot(E e) throws IllegalStateException {
        if (!isEmpty()) {
            throw new IllegalStateException("Tree already has a root");
        }
        size = 1;
        root = createNode(e, null, null, null);
        return root;
    }

    /**
     * Inserts a left child at a given node.
     */
    public Position<E> insertLeft(Position<E> v, E e)
            throws IllegalStateException {
        BTPosition<E> vv = checkPosition(v);
        Position<E> leftPos = vv.getLeft();
        if (leftPos != null) {
            throw new IllegalStateException("Node already has a left child");
        }
        BTPosition<E> ww = createNode(e, vv, null, null);
        vv.setLeft(ww);
        size++;
        return ww;
    }
//end#fragment LinkedBinaryTree3

    /**
     * Inserts a right child at a given node.
     */
    public Position<E> insertRight(Position<E> v, E e)
            throws IllegalStateException {
        BTPosition<E> vv = checkPosition(v);
        Position<E> rightPos = vv.getRight();
        if (rightPos != null) {
            throw new IllegalStateException("Node already has a right child");
        }
        BTPosition<E> w = createNode(e, vv, null, null);
        vv.setRight(w);
        size++;
        return w;
    }
//begin#fragment LinkedBinaryTree4

    /**
     * Removes a node with zero or one child.
     */
    public E remove(Position<E> v)
            throws IllegalStateException {
        BTPosition<E> vv = checkPosition(v);
        BTPosition<E> leftPos = vv.getLeft();
        BTPosition<E> rightPos = vv.getRight();
        if (leftPos != null && rightPos != null) {
            throw new IllegalStateException("Cannot remove node with two children");
        }
        BTPosition<E> ww; 	// the only child of v, if any
        if (leftPos != null) {
            ww = leftPos;
        } else if (rightPos != null) {
            ww = rightPos;
        } else // v is a leaf
        {
            ww = null;
        }
        if (vv == root) { 	// v is the root
            if (ww != null) {
                ww.setParent(null);
            }
            root = ww;
        } else { 		// v is not the root
            BTPosition<E> uu = vv.getParent();
            if (vv == uu.getLeft()) {
                uu.setLeft(ww);
            } else {
                uu.setRight(ww);
            }
            if (ww != null) {
                ww.setParent(uu);
            }
        }
        size--;
        return v.getElement();
    }

    /**
     * Attaches two trees to be subtrees of an external node.
     */
    public void attach(Position<E> v, BinaryTree<E> T1, BinaryTree<E> T2)
            throws IllegalStateException {
        BTPosition<E> vv = checkPosition(v);
        if (isInternal(v)) {
            throw new IllegalStateException("Cannot attach from internal node");
        }
        int newSize = size + T1.size() + T2.size();
        if (!T1.isEmpty()) {
            BTPosition<E> r1 = checkPosition(T1.root());
            vv.setLeft(r1);
            r1.setParent(vv);		// T1 should be invalidated
        }
        if (!T2.isEmpty()) {
            BTPosition<E> r2 = checkPosition(T2.root());
            vv.setRight(r2);
            r2.setParent(vv);		// T2 should be invalidated
        }
        size = newSize;
    }

    /**
     * Swap the elements at two nodes
     */
    public void swapElements(Position<E> v, Position<E> w)
            throws IllegalStateException {
        BTPosition<E> vv = checkPosition(v);
        BTPosition<E> ww = checkPosition(w);
        E temp = w.getElement();
        ww.setElement(v.getElement());
        vv.setElement(temp);
    }

    /**
     * Expand an external node into an internal node with two external node
     * children
     */
    public void expandExternal(Position<E> v, E l, E r)
            throws IllegalStateException {
        if (!isExternal(v)) {
            throw new IllegalStateException("Node is not external");
        }
        insertLeft(v, l);
        insertRight(v, r);
    }

    /**
     * Remove an external node v and replace its parent with v's sibling
     */
    public void removeAboveExternal(Position<E> v)
            throws IllegalStateException {
        if (!isExternal(v)) {
            throw new IllegalStateException("Node is not external");
        }
        if (isRoot(v)) {
            remove(v);
        } else {
            Position<E> u = parent(v);
            remove(v);
            remove(u);
        }
    }
    // Auxiliary methods

    /**
     * If v is a good binary tree node, cast to BTPosition, else throw exception
     */
    protected BTPosition<E> checkPosition(Position<E> v)
            throws IllegalStateException {
        if (v == null || !(v instanceof BTPosition)) {
            throw new IllegalStateException("The position is invalid");
        }
        return (BTPosition<E>) v;
    }

    /**
     * Creates a new binary tree node
     */
    protected BTPosition<E> createNode(E element, BTPosition<E> parent,
            BTPosition<E> left, BTPosition<E> right) {
        return new BTNode<E>(element, parent, left, right);
    }

    /**
     * Creates a list storing the the nodes in the subtree of a node, ordered
     * according to the preorder traversal of the subtree.
     */
    protected void preorderPositions(Position<E> v, PositionList<Position<E>> pos)
            throws IllegalStateException {
        pos.addLast(v);
        if (hasLeft(v)) {
            preorderPositions(left(v), pos);	// recurse on left child
        }
        if (hasRight(v)) {
            preorderPositions(right(v), pos);	// recurse on right child
        }
    }

    /**
     * Creates a list storing the the nodes in the subtree of a node, ordered
     * according to the inorder traversal of the subtree.
     */
    protected void inorderPositions(Position<E> v, PositionList<Position<E>> pos)
            throws IllegalStateException {
        if (hasLeft(v)) {
            inorderPositions(left(v), pos);  // recurse on left child
        }
        pos.addLast(v);
        if (hasRight(v)) {
            inorderPositions(right(v), pos); // recurse on right child
        }
    }
}
