import java.util.Iterator;
/**
 * A simple iterator class for lists. The elements of a list are returned by
 * this iterator. No copy of the list is made, so any changes to the list are
 * reflected in the iterator.
 *
 * @author Michael Goodrich, Eric Zamore, Roberto Tamassia
 */
public class ElementIterator<E> implements Iterator<E> {

    protected PositionList<E> list; // the underlying list
    protected Position<E> cursor; // the next position

    /**
     * Creates an element iterator over the given list.
     */
    public ElementIterator(PositionList<E> L) {
        list = L;
        cursor = (list.isEmpty()) ? null : list.first();
    }

    /**
     * Returns whether the iterator has a next object.
     */
    @Override
    public boolean hasNext() {
        return (cursor != null);
    }

    /**
     * Returns the next object in the iterator.
     */
    @Override
    public E next() throws IllegalArgumentException {
        if (cursor == null) {
            throw new IllegalArgumentException("No next element");
        }
        E toReturn = cursor.getElement();
        cursor = (cursor == list.last()) ? null : list.next(cursor);
        return toReturn;
    }

    /**
     * Throws an {@link UnsupportedOperationException} in all cases, because
     * removal is not a supported operation in this iterator.
     */
    @Override
    public void remove() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("remove");
    }
//begin# Iterator
}
//end# Iterator
