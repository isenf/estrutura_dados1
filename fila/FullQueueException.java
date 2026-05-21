/**
 * Runtime exception thrown when one tries to perform operation front
 * or dequeue on an empty queue.
 * @author Natasha Gelfand
 * @author Michael T. Goodrich
 * @author Roberto Tamassia
 */

public class FullQueueException extends RuntimeException {  
    public FullQueueException(String err) {
        super(err);
    }
}