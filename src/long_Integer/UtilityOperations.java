package long_Integer;

/**
 * Support operations for the linked list
 * @author Dakota Buell
 *
 */
public class UtilityOperations {

	/**
	 * Returns the digits that acceded the the digit count of 4;
	 * @param t : The integer to be check
	 * @return The overflow digits of the integer.
	 */
	public static int overFlow(int t) {
		if(t<9999){
			return 0;
		}
		else{
			return t/10000;
		}
    }

	/**
	 * Gets the digits that are not the first 4 in the integer.
	 * @param t : The integer being checked
	 * @return The digits that are not the first 4
	 */
    public static int underFlow(int t) {
      if(t<9999){
    	  return 0;
      }
      else{
    	  int underflow=0;
    	  int pt =0;
    	  for(int x =0; t>9999;x++){
    		  pt=t%10;
    		  underflow += pt*(Math.pow(10, x));
    		  t= t/10;
    	  }
    	  return underflow;
      }
    }

    /**
     * Gets the number of digits in the integer
     * @param t : The integer to be counted
     * @return The number of digits in the integer
     */
    public static int digits(int t) {
      int count =0;
      while (t>0){
    	  count++;
    	  t= t/10;
      }
      return count;
    }
}
