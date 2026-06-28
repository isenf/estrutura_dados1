/**
 * An interface for a binary tree, where each node can have zero, one,
 * or two children.
 *
 * @author Michael Goodrich
 */
public interface BinaryTree<E> extends Tree<E> {

    /**
     * Returns the left child of a node.
     */
    public Position<E> left(Position<E> v) throws IllegalArgumentException;

    /**
     * Returns the right child of a node.
     */
    public Position<E> right(Position<E> v)
            throws IllegalArgumentException;

    /**
     * Returns whether a node has a left child.
     */
    public boolean hasLeft(Position<E> v) throws IllegalArgumentException;

    /**
     * Returns whether a node has a right child.
     */
    public boolean hasRight(Position<E> v) throws IllegalArgumentException;
}
