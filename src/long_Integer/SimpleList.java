package long_Integer;

/**
 * 
 * @author Dakota Buell
 *
 * @param <N>
 */
public interface SimpleList <N extends Node>{
	
	/**
	 * Inserts a new node at the beginning of the list.
	 * @param data : The data to be in the first node.
	 */
	public void insertFirst(int data);
	
	/**
	 * Inserts a new node at the end of the list
	 * @param data : The data to be in the last node.
	 */
    public void insertLast(int data);
    
    /**
     * Gets the first node in the list
     * @return The header DLLNode
     */
    public N first();
    
    /**
     * Gets the last node in the list
     * @return The tail DLLNode
     */
    public N last();
    
    /**
     * Checks to see if the node is the first in the list
     * @param n : The node to be checked
     * @return True if the node is the first in the list, otherwise false
     */
    public boolean isFirst(N n);
    
    /**
     * Checks to see if the node is the last in the list
     * @param n : The node to be checked
     * @return True if the node is last in the list, otherwise false
     */
    public boolean isLast(N n);
    
    /**
     * Gets the node before the node passed to the function
     * @param n : The current Node
     * @return The node before the node passed to the function
     */
    public N before(N n);
    
    /**
     * Gets the node after the node passed to the function
     * @param n : The  current Node
     * @return The Node after the node passed to the function
     */
    public N after(N n);
    
    /**
     * Checks to see if the list is empty
     * @return true if it is empty, otherwise false
     */
    public boolean isEmpty();
    
    /**
     * Gets the size of the list
     * @return The number of nodes in the list
     */
    public int size();

}
