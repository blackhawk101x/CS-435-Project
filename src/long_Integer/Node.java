package long_Integer;

/**
 * The node class to store and maintain the node data
 * @author Dakota Buell
 *
 */
public abstract class Node {

	private int data;
	
	/**
	 * Overload Constructor for the class
	 * @param data : The value to set the the data in the node
	 */
    public Node(int data){
        this.data= data;
    }
    
    /**
     * retrieves the data of the node
     * @return the integer data stored in the node
     */
    public int getData(){
        return data;
    }
}
