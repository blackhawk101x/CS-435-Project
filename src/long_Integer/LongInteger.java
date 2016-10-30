package long_Integer;

/**
 * 
 * @author Dakota Buell
 *
 */
public class LongInteger {

	 	private SimpleList list = new DLLSimpleList();
	    private boolean isNegative = false;

	    public LongInteger() {
	    }

	    /**
	     * Overload constructor which populates the list
	     * @param s
	     */
	    public LongInteger(String s) {
	    	int num=-1;
	    	// checks to see if the number is negative
	    	if(s.startsWith("-")){
	    		this.setSign(true);
	    		s =s.substring(1, s.length());
	    	}
	    	else{
	    		this.setSign(false);
	    	}
	    	
	    	/*
	    	 * The Simple list is being populated from the back to the front. The last 4 chars in the string are converted to an integer and added to
	    	 * the front of the string. 
	    	 */
	    	while(s.length()>0){
	    		
	    		if(s.length()>4){
	    			num = Integer.parseInt(s.substring(s.length()-4, s.length())); // converting the last 4 digits to an integer
	    			s= s.substring(0,s.length()-4); // removing the last 4 digits from the string
	    		}
	    		else{
	    			num=Integer.parseInt(s); // converting the string to an integer
	    			s = ""; // setting the string to empty
	    		}	
	    		this.list.insertFirst(num); // inserting the number to the beginning of the linked list
	    	}
	    } 

	    /**
	     * Outputs the contains of the list to the console
	     */
	    public void output() {
	    	
	    	String num = (this.isNegative)? "-":"";
	    	
	    	/*
	    	 * The first node data may not have 4 digits in its data. the simplest path is to print out the first node before the for loop so an
	    	 * Additional condition is not necessary
	    	 */
	    	num+=(this.list.first().getData());
	    	
	    	Node pt = this.getNext(this.list.first());
	    	while(pt!=null){
	    		if(pt.getData()>999){
	    			num+=(pt.getData());
	    		}
	    		else if(pt.getData()>99){
	    			num+=("0"+pt.getData());
	    		}
	    		else if(pt.getData()>9){
	    			num+=("00"+pt.getData());
	    		}
	    		else if(pt.getData()>0){
	    			num+=("000"+pt.getData());
	    		}
	    		else{
	    			num+=("0000");
	    		}
	    		pt= this.list.after(pt);
	    	}
	    	System.out.println(num);
	    }

	    /**
	     * Gets the sign of the number
	     * @return True if the number is negative, otherwise false
	     */
	    public boolean getSign() {
	    	return this.isNegative;
	    }

	    /**
	     * Sets the sign of the number
	     * @param isNegative : True of the number is negative, false if it is positive
	     */
	    public void setSign(boolean isNegative) {
	    	this.isNegative = isNegative;
	    }

	    /**
	     * Gets the number of digits in the number
	     * @return the integer count of the digits in the string
	     */
	    public int getDigitCount() {
	    	
	    	// gets the number of nodes and subtracts 1 for the headed. THe idea is that all the nodes after the head are going to contain
	    	// at least 4 digits because of the design of the list.
	    	int count = (this.list.size()-1)*4;
	    	
	    	// computes the size of the head node
	    	int headData = this.list.first().getData();
	    	while(headData>0){
	    		count++;
	    		headData =headData/10;
	    	}

	    	return count;
	    }

	    /**
	     * Checks to see if the LongIntegers are equal to one another
	     * @param i : The long integer to compare to
	     * @return True if the long integers are equal
	     */
	    public boolean equalTo(LongInteger i) {
	    	boolean equalTo =true;
	    	if(this.getSign()!=i.getSign()){
	    		equalTo= false;
	    	}
	    	else if(i.getDigitCount()!=this.getDigitCount()){
	    		equalTo= false;
	    	}
	    	else{
	    		Node curr1 = this.getFirst();
	    		Node curr2 = i.getFirst();
	    		while(!this.isLast(curr1) && !this.isLast(curr2)){
	    			if(curr1.getData()!=curr2.getData()){
	    				equalTo= false;
	    				break;
	    			}
	    			curr1 = this.getNext(curr1);
	    			curr2 = i.getNext(curr2);
	    		}
	    	}
	    	return equalTo;
	    }

	    /**
	     * Checks to see if the long int is less than the one given
	     * @param i : the long integer to compare against
	     * @return true if the this long integer is less than the goven long integer
	     */
	    public boolean lessThan(LongInteger i) {
	    	boolean lessThan = false;
	    	
	    	// first step is to check the signs
	    	if(this.getSign() && !i.getSign()){ // if this long int is negative and i is not
	    		lessThan = true;
	    	}
	    	else if(!this.getSign() && i.getSign()){ // if this long int is positive and i is not
	    		lessThan = false;
	    	}
	    	else{ // if the signs are equal
	    		// second step is to check the number of digits
	    		
	    		// if the signs are negative.
	    		// because the signs are negative, the logic is inverted
	    		if(this.getSign()){
	    			if(this.getDigitCount()>i.getDigitCount()){ // if this long int has fewer digits than i
			    		lessThan = true;    		
			    	}
	    			else if(this.getDigitCount()<i.getDigitCount()){ // if this long int has more digits than i
	    				lessThan =false;
	    			}
	    			else{ // if the digit count is equal
	    				Node curr1 = this.getFirst();
	    	    		Node curr2 = i.getFirst();
	    	    		while(!this.isLast(curr1) && !this.isLast(curr2)){
    	    				 //Because of the negative signs, the larger of the the two is smaller
    	    				if(curr1.getData()>curr2.getData()){
    	    					lessThan=true;
    	    					break;
    	    				}
	    	    			curr1 = this.getNext(curr1);
	    	    			curr2 = i.getNext(curr2);
	    	    		}
	    			}
	    		}
	    		else{ // if the signs are positive
	    			if(this.getDigitCount()<i.getDigitCount()){ // if this long int has fewer digits than i
			    		lessThan = true;    		
			    	}
	    			else if(this.getDigitCount()>i.getDigitCount()){ // if this long in has more digits than i
	    				lessThan = false;
	    			}
	    			else{ // if they have the same number of digits
	    				Node curr1 = this.getFirst();
	    	    		Node curr2 = i.getFirst();
	    	    		while(!this.isLast(curr1) && !this.isLast(curr2)){
    	    				if(curr1.getData()<curr2.getData()){
    	    					lessThan = true;
    	    					break;
    	    				}
	    	    			curr1 = this.getNext(curr1);
	    	    			curr2 = i.getNext(curr2);
	    	    		}
	    			}
	    			
	    		}	
	    	}
	    	return lessThan;
	    	
	    }

	    public boolean greaterThan(LongInteger i) {
	    	boolean greaterThan = false;
	    	
	    	// first step is to check the signs
	    	if(this.getSign() && !i.getSign()){ // if this long int is negative and i is not
	    		greaterThan = false;
	    	}
	    	else if(!this.getSign() && i.getSign()){ // if this long int is positive and i is not
	    		greaterThan = true;
	    	}
	    	else{ // if the signs are equal
	    		// second step is to check the number of digits
	    		
	    		// if the signs are negative.
	    		// because the signs are negative, the logic is inverted
	    		if(this.getSign()){
	    			if(this.getDigitCount()>i.getDigitCount()){ // if this long int has fewer digits than i
			    		greaterThan = false;    		
			    	}
	    			else if(this.getDigitCount()<i.getDigitCount()){ // if this long int has more digits than i
	    				greaterThan =true;
	    			}
	    			else{ // if the digit count is equal
	    				Node curr1 = this.getFirst();
	    	    		Node curr2 = i.getFirst();
	    	    		while(!this.isLast(curr1) && !this.isLast(curr2)){
    	    				 //Because of the negative signs, the larger of the the two is smaller
    	    				if(curr1.getData()>curr2.getData()){
    	    					greaterThan=false;
    	    					break;
    	    				}
	    	    			curr1 = this.getNext(curr1);
	    	    			curr2 = i.getNext(curr2);
	    	    		}
	    			}
	    		}
	    		else{ // if the signs are positive
	    			if(this.getDigitCount()<i.getDigitCount()){ // if this long int has fewer digits than i
			    		greaterThan = false;    		
			    	}
	    			else if(this.getDigitCount()>i.getDigitCount()){ // if this long in has more digits than i
	    				greaterThan = true;
	    			}
	    			else{ // if they have the same number of digits
	    				Node curr1 = this.getFirst();
	    	    		Node curr2 = i.getFirst();
	    	    		while(!this.isLast(curr1) && !this.isLast(curr2)){
    	    				if(curr1.getData()<curr2.getData()){
    	    					greaterThan = false;
    	    					break;
    	    				}
	    	    			curr1 = this.getNext(curr1);
	    	    			curr2 = i.getNext(curr2);
	    	    		}
	    			}
	    			
	    		}	
	    	}
	    	
	    	
	    	return greaterThan;
	    }

	    /**
	     * Gets the next node in the list
	     * @param p : The current node in the list
	     * @return The next node in the list
	     */
	    public Node getNext(Node p) {
	    	return this.list.after(p);
	    	//return null;
	    }

	    /**
	     * Gets the previous node in the list
	     * @param p : The current Node
	     * @return The previous node in the list
	     */
	    public Node getPrevious(Node p) {
	    	return this.list.after(p);
	    }

	    /**
	     * Gets the first node in the list
	     * @return The first node in the list
	     */
	    public Node getFirst() {
	    	return this.list.first();
	    }

	    /**
	     * Gets the last node in the list
	     * @return The last node in the list
	     */
	    public Node getLast() {
	    	return this.list.last();
	    }

	    /**
	     * Checks to see if the current node is the last in the list
	     * @param p : The node to be checked
	     * @return True if the node is last, otherwise false
	     */
	    public boolean isLast(Node p) {
	    	return this.list.isLast(p);
	    }

	    /**
	     * Checks to see if the current node is the first in the list
	     * @param p : The node to be checked
	     * @return True if the node is first, otherwise false
	     */
	    public boolean isFirst(Node p) {
	    	return this.list.isFirst(p);
	    }

	    public LongInteger add(LongInteger i) {
	    	return null;
	    }

	    public LongInteger subtract(LongInteger i) {
	    	return null;
	    }

	    public LongInteger multiply(LongInteger i) {
	    	return null;
	    }

	    public LongInteger power(int p) {
	    	return null;
	    }

	    public LongInteger divide(LongInteger i) {
	    	return null;
	    }
}
