/**
 * Class implementing a node of a binary tree by storing references to
 * an element, a parent node, a left node, and a right node.
 *
 *  @author Luca Vismara, Roberto Tamassia, Michael Goodrich
 */
public class TreeNode<E> implements TreePosition<E> {

    private E element;  // element stored at this node
    private TreePosition<E> parent;  // adjacent node
    private PositionList<Position<E>> children;  // children nodes

    /**
     * Default constructor
     */
    public TreeNode() {
    }

    /**
     * Main constructor
     */
    public TreeNode(E element, TreePosition<E> parent,
            PositionList<Position<E>> children) {
        setElement(element);
        setParent(parent);
        setChildren(children);
    }

    /**
     * Returns the element stored at this position
     */
    @Override
    public E getElement() {
        return element;
    }

    /**
     * Sets the element stored at this position
     */
    @Override
    public void setElement(E o) {
        element = o;
    }

    /**
     * Returns the children of this position
     */
    @Override
    public PositionList<Position<E>> getChildren() {
        return children;
    }

    /**
     * Sets the right child of this position
     */
    @Override
    public void setChildren(PositionList<Position<E>> c) {
        children = c;
    }

    /**
     * Returns the parent of this position
     */
    @Override
    public TreePosition<E> getParent() {
        return parent;
    }

    /**
     * Sets the parent of this position
     */
    @Override
    public void setParent(TreePosition<E> v) {
        parent = v;
    }
}
