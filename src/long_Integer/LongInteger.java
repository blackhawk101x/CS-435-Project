package long_Integer;

import java.util.ArrayList;

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
	    	
	    	s = s.replace("\n", ""); // removes all the newline characters
	    	
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
	     * @return true if the this long integer is less than the given long integer
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
	    	return this.list.before(p);
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
	    
	    /**
	     * !!!!! DEPRECATED !!!!!
	     * Converts any long integer to its tens complement. Only works for for positive integers
	     * @param i
	     * @return
	     */
	    private LongInteger tensComplement(LongInteger i){
	    	LongInteger newNum = new LongInteger();
	    	
	    	Node a =i.getLast();
	    	boolean firstFound = false;
	    	while(a!=null){
	    		
	    		if(!firstFound && a.getData()!=0){ // if the first digit have not been found and it is not zero
	    			
	    			newNum.list.insertFirst((int) (Math.pow(10, UtilityOperations.digits(a.getData()) )-a.getData()));
	    			firstFound=true;
	    		}
	    		else if(firstFound){ // if the first set of digits has been found
	    			if(i.isFirst(a)){
	    				if(a.getData()>999){
	    					newNum.list.insertFirst(9999-a.getData());
	    				}
	    				else if(a.getData()>99){
	    					newNum.list.insertFirst(999-a.getData());
	    				}
	    				else if(a.getData()>9){
	    					newNum.list.insertFirst(99-a.getData());
	    				}
	    				else{
	    					newNum.list.insertFirst(9-a.getData());
	    				}
	    			}
	    			
	    		}
	    		else{ // if the data is a zero and the first set of digits has not been found
	    			newNum.list.insertFirst(0);
	    		}
	    		a = i.getPrevious(a);
	    	}	
	    	return newNum;
	    }
	    
	    /**
	     * !!!!! DEPRECATED !!!!!
	     * Performs tens complement math. Only works on two positive numbers
	     * @param i
	     * @return
	     */
	    @SuppressWarnings("unused")
		private LongInteger tensComplementAdd(LongInteger i){
	    	LongInteger newNum = new LongInteger();
	    	LongInteger tens = this.tensComplement(i);
	    	
	    	Node a = this.getLast();
	    	Node b = tens.getLast();
	    	
	    	int overflow =0;
	    	while(a!=null || b!=null){
	    		
	    		if(b==null && a!=null){
	    			newNum.list.insertFirst(a.getData()+overflow);
	    			overflow=0;
	    		}
	    		else if(b!=null && a==null){
	    			newNum.list.insertFirst(b.getData()+overflow);
	    			overflow =0;
	    		}
	    		else{
	    			
	    			
	    			int num = UtilityOperations.underFlow(a.getData()+b.getData()+overflow);
	    			
	    			if(tens.isFirst(b)&& i.lessThan(this)){
	    				if(UtilityOperations.digits(i.getFirst().getData())%4==0){
	    					overflow= -1;
	    				}
	    				else{
	    					num-= Math.pow(10, UtilityOperations.digits(i.getFirst().getData()));
	    					overflow =0;
	    				}
	    			}

	    			newNum.list.insertFirst(num);

	    		}
		
	    		// moving up the list to the next node
    			if( a!=null){
    				a = this.getPrevious(a);
    			}
    			if(b!=null){
    				b = tens.getPrevious(b);
    			}
	    		
	    	}
	    	return newNum;
	    }
	    
	    /**
	     * Added this long integer to the specified long integer and returns a new long integer 
	     * @param i : The long integer to add to this long integer
	     * @return A new long integer representing the sum of the two long integers this and i 
	     */
	    public LongInteger add(LongInteger i) {
	    	LongInteger newNum =  null;
	    	
	    	if(this.getSign()==i.getSign()){ // if the signs are equal
	    		
	    		newNum = new LongInteger();
	    		
	    		// setting the sigh of the new number
	    		newNum.setSign(this.getSign());
	    		
	    		Node a = this.getLast();
	    		Node b = i.getLast();
	    		int sum =0;
	    		
	    		while(a!=null || b!=null){
	    			if(a!=null){
	    				sum+=a.getData();
	    			}
	    			if(b!=null){
	    				sum+=b.getData();
	    			}
	    			
	    			newNum.list.insertFirst(UtilityOperations.underFlow(sum));
	    			sum = UtilityOperations.overFlow(sum);
	    			
	    			// moving up the list to the next node
	    			if( a!=null){
	    				a = this.getPrevious(a);
	    			}
	    			if(b!=null){
	    				b = i.getPrevious(b);
	    			}
	    		}
	    		
	    		// adds any remaining numbers to the heads
	    		if(sum!=0){
	    			newNum.list.insertFirst(sum);
	    		}
	    		
	    	}
	    	else{
	    		// if this is negative and i is positive
	    		if(this.getSign() && !i.getSign()){
	    			this.setSign(false);
	    			newNum = i.subtract(this);
	    			this.setSign(true);
	    		}
	    		else{ // if this is positive and i is negative
	    			i.setSign(false);
	    			newNum=this.subtract(i);
	    			i.setSign(true);
	    		}
	    	}
	    	return newNum;
	    }
	    
	    /**
	     * Removes any leading zeros that may have been added during subtractions
	     * @param i : The long integer to clean of zeros
	     * @return A proper long integer
	     */
	    private LongInteger sanitize(LongInteger i){
	    	
	    	if(i.getFirst().getData()!=0){
	    		return i;
	    	}
	    	else{
	    		LongInteger newNum = new LongInteger();
	    		newNum.setSign(i.getSign());
	    		Node a = i.getNext(i.getFirst());
	    		boolean firstFound = false;
	    		while(a!=null){
	    			
	    			if(firstFound){
	    				newNum.list.insertLast(a.getData());
	    			}
	    			else{
	    				if(a.getData()!=0){
	    					newNum.list.insertLast(a.getData());
	    					firstFound=true;
	    				}
	    			}
	    			
	    			a= i.getNext(a);
	    		}
	    		return newNum;
	    	}
	    }
	    
	    /**
	     * Performs subtraction of i on this.
	     * @param i : The long integer that should be subtracted from this
	     * @return The difference between this and i.
	     */
	    public LongInteger subtract(LongInteger i) {
	    	LongInteger newNum = new LongInteger();
	    	
	    	// if i is negative
	    	if(i.getSign()){
	    		i.setSign(false);
	    		newNum = this.add(i);
	    		i.setSign(true);
	    	}
	    	else if(this.getSign() && !i.getSign()){ // if this is negative 
	    		this.setSign(false);
	    		newNum = this.add(i);
	    		newNum.setSign(true);
	    		this.setSign(true);
	    	}
	    	else if(this.equalTo(i)){
	    		newNum.list.insertFirst(0);
	    	}
	    	else{
	    		
	    		Node a  =null;
	    		Node b = null;
	    		
	    		// which ever one is larger is assigned to a, the smaller one is assigned to b
	    		if(this.lessThan(i)){
	    			// if i is bigger than this
	    			a= i.getLast();
	    			b= this.getLast();
	    			newNum.setSign(true);
	    		}
	    		else{
	    			// if this is larger than i
	    			a= this.getLast();
	    			b= i.getLast();
	    			newNum.setSign(false);
	    		}
	    		
	    		int borrow = 0;
	    		int sum = 0;
	    		
	    		// a will always be the larger of the two
	    		while(a!=null){
	    			
	    			if(b!=null){
	    				if(a.getData()+borrow<b.getData()){
	    					newNum.list.insertFirst((10000+(a.getData()+borrow))-b.getData());
	    					borrow =-1;
	    				}
	    				else{
	    					newNum.list.insertFirst((a.getData()+borrow)-b.getData());
	    					borrow = 0;
	    				}
	    			}
	    			else{
	    				newNum.list.insertFirst(a.getData()+borrow);
	    				borrow =0;
	    			}
	    			
	    			// The sign was set in the beginning to negative if i was larger than this. Repeated access to a boolean is better than repeated
	    			// calling of this.lessthan
	    			if(newNum.getSign()){
	    				a= i.getPrevious(a);
	    				if(b!=null){
	    					b= this.getPrevious(b);
	    				}
	    				
	    			}
	    			else{
	    				a= this.getPrevious(a);
	    				if(b!=null){
	    					b= i.getPrevious(b);
	    				}
	    			}
	    			
	    		}
	    		
	    		newNum = sanitize(newNum); // cleans up any leading zeros
	    	}
	    	
	    	
	    	return newNum;
	    }

	    /**
	     * Performs multiplication on the long integer with another long integer
	     * @param i : The long integer to multiply by
	     * @return A product of the this long integer and long integer i
	     */
	    public LongInteger multiply(LongInteger i) {
	    	
	    	// if either number is equal to zero, return a zero
	    	if(this.equalTo(new LongInteger("0")) || i.equalTo(new LongInteger("0"))){
	    		return new LongInteger("0");
	    	}
	    	
	    	LongInteger Product = new LongInteger("0");
	    	
	    	Node b = i.getLast();
	    	int indentMultiplier =0;    	
	    	
	    	while(b!=null){
	    		LongInteger tmp = new LongInteger();
		    	
		    	// getting the proper indentation for the product
		    	for(int x=0;x<indentMultiplier;x++){
		    		tmp.list.insertFirst(0);
		    	}
		    	
		    	Node a  = this.getLast();
		    	int overflow=0;
		    	while(a!=null || overflow !=0){
		    	
		    		if(a!=null){
		    			overflow += a.getData()*b.getData();
		    			tmp.list.insertFirst(UtilityOperations.underFlow(overflow));
		    			overflow = UtilityOperations.overFlow(overflow);
		    			a = this.getPrevious(a);
		    		}
		    		else{
		    			tmp.list.insertFirst(UtilityOperations.underFlow(overflow));
		    			overflow = UtilityOperations.overFlow(overflow);
		    		}
		    	}
		    	
		    	Product = Product.add(tmp);
	    		
	    		indentMultiplier++;
	    		b = i.getPrevious(b);
	    	}
	    	
	    	// setting the sign of the new long integer
	    	Product.setSign(this.getSign() != i.getSign());
	    	
	    	return Product;
	    }

	    /**
	     * Raises this to the power of p
	     * @param p : The power to raise this by
	     * @return A long integer which is the the power of this raised to p
	     */
	    public LongInteger power(int p) {
	    	
	    	if(p==0){ // if p is 0, return a long integer 1
	    		return new LongInteger("1");
	    	}
	    	else if(p==1){ // if p is 1, return a copy of itself
	    		return this.clone();
	    	}
	    	
	    	LongInteger num1 =this.power(p/2);
    		LongInteger num2 = this.power(p/2);
   		
	    	if(p%2==0){ // if the p is even
	    		return num1.multiply(num2);
	    	}
	    	else{ // if p is odd
	    		return this.multiply(num1.multiply(num2));
	    	}
	    }

	    public LongInteger divide(LongInteger i) {
	    	return null;
	    }
	    
	    protected LongInteger clone(){
	    	LongInteger newNum = new LongInteger();
	    	
	    	newNum.setSign(this.getSign());
	    	for(Node a = this.getFirst();a!=null;a=this.getNext(a)){
	    		newNum.list.insertLast(a.getData());
	    	}
	    	return newNum;
	    }
	    
}
