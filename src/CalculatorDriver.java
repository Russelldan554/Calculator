/**
 * driver class for calculatorGui holds methods to do calculation of addition subtraction multiplication division
 * and returns the result
 * @author Daniel Russell
 *
 */
public class CalculatorDriver {

	private String total;
	private double floatTotal;
	
	/**
	 * initializes total and floatTotal
	 */
	public CalculatorDriver(){
		total = " ";
		floatTotal = 0;
	}
	
	/**
	 * adds the two numbers sent and returns sum
	 * @param one first number to add
	 * @param two second number to add
	 * @return total of addition
	 */
	public String add(double one, double two){
		floatTotal = one + two;
		total = String.valueOf(floatTotal);
		return total;
	}
	
	/**
	 * subtracts the two numbers sent and returns sum
	 * @param one first number to subtract
	 * @param two second number to subtract
	 * @return total after subtraction
	 */
	public String subtract(double one, double two){
		floatTotal = one - two;
		total = String.valueOf(floatTotal);
		return total;
	}
	
	/**
	 * multiplies the two numbers sent and returns sum
	 * @param one
	 * @param two
	 * @return total of multiplication
	 */
	public String multiply(double one, double two){
		floatTotal = one * two;
		total = String.valueOf(floatTotal);
		return total;
	}
	
	/**
	 * divides the two numbers sent to it
	 * @param one numerator
	 * @param two denominator
	 * @return total of division
	 */
	public String divide(double one, double two){
		if(two !=0){
			floatTotal = one / two;
			total = String.valueOf(floatTotal);
			return total;
		} else
			return "Error cannot divide by zero";
	}
}
