/**
 * Implementation of the stack ADT by means of a singly linked list.
 *
 * @author Natasha Gelfand
 * @author Roberto Tamassia
 * @see Node
 */

public class NodeStack<T> implements Stack<T> {

    protected NodeG<T> top;	// reference to the head node
    protected int size;		// number of elements in the stack

    /**
     * Creates an empty stack.
     */
    public NodeStack() {	// constructs an empty stack
        top = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty(){
        return top == null;
    }

    @Override
    public void push(T elem) {
        NodeG<T> v = new NodeG<>(elem, top);	// create and link-in a new node
        top = v;
        size++;
    }

    @Override
    public T top() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException("Stack is empty.");
        }
        return top.getElement();
    }

    @Override
    public T pop() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException("Stack is empty.");
        }
        T temp = top.getElement();
        top = top.getNext();	// link-out the former top node
        size--;
        return temp;
    }

    /**
     * Returns a string representation of the stack as a list of elements, with
     * the top element at the end: [ ... , prev, top ]. This method runs in O(n)
     * time, where n is the size of the stack.
     *
     * @return textual representation of the stack.
     */
    @Override
    public String toString() {
        String s;
        NodeG<T> cur = null;
        s = "[";
        int n = size();
        if (n > 0) {
            cur = top;
            s += cur.getElement();
        }
        if (n > 1) {
            for (int i = 1; i <= n - 1; i++) {
                cur = cur.getNext();
                s += ", " + cur.getElement();
            }
        }
        s += "]";
        return s;
    }

    /**
     * Prints information about an operation and the stack.
     *
     * @param op operation performed
     * @param element element returned by the operation
     * @return information about the operation performed, the element returned
     * by the operation and the content of the stack after the operation.
     */
    public static void status(Stack S, String op, Object element) {
        System.out.println("---------------------------------");
        System.out.println(op);
        System.out.println("Returned: " + element);
        String emptyStatus;
        if (S.isEmpty()) {
            emptyStatus = "empty";
        } else {
            emptyStatus = "not empty";
        }
        System.out.println("size = " + S.size() + ", " + emptyStatus);
        System.out.println("Stack: " + S);
    }

    /**
     * Test program that performs a series of operations on on a stack and
     * prints the operation performed, the returned element and the content of
     * the stack after each operation.
     */
    public static void main(String[] args) {
        Object o;
        Stack<Integer> A = new NodeStack<>();
        status(A, "New empty stack", null);
        A.push(5);
        status(A, "push(5)", null);
        A.push(3);
        status(A, "push(3)", null);
        A.push(7);
        status(A, "push(7)", null);
        o = A.pop();
        status(A, "pop()", o);
        A.push(9);
        status(A, "push(9)", null);
        o = A.pop();
        status(A, "pop()", o);
        o = A.top();
        status(A, "top()", o);
    }
}
