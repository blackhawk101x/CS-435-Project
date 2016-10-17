package long_Integer;

/**
 * The Double Linked List class which allows for the construction of two way linked lists
 * @author Dakota Buell
 *
 */
public class DLLNode extends Node{

    private DLLNode prev;
    private DLLNode next;

    /**
     * Constructor which will set the data and the two nodes to null
     * @param newData : Sets the data in the node
     */
    DLLNode(int newData) {
        super(newData);
        this.setNext(null);
        this.setPrev(null);
        
    }
    
    /**
     * Sets the previous node
     * @param n : The node to be set too
     */
    public void setPrev(DLLNode n) {
        this.prev = n;
    }
    
    /**
     * Gets the previous node
     * @return The previous node
     */
    public DLLNode getPrev() {
        return this.prev;
    }
    
    /**
     * Sets the next node
     * @param n : The node to be set too
     */
    public void setNext(DLLNode n) {
        this.next = n;
    }
    
    /**
     * Gets the next node
     * @return The next node
     */
    public DLLNode getNext() {
        return this.next;
    }
}
