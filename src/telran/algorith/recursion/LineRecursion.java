package telran.algorith.recursion;

public class LineRecursion {

	public static long factorial(int n) {
		long res = 1;
		if( n < 0) {
			throw new IllegalArgumentException("Cannot be negative value");
		}
		if(n > 1) {
			res = n * factorial(n - 1);
		}
		return res;
	}
	public static long pow (int a, int b) {
		//a - any number
		//b - any positive number or zero
//		if(b < 0) {
//			throw new IllegalArgumentException("Pow cannot be negative value");
//		}
//		long res = 1;
//		if(b > 0) {
//			res = a * pow(a, b - 1); //a^b = a * a^(b - 1)
//		}
//		return res;
		//	TODO HW #18
		//Limitations: 
		// 1. no cycles
		// 2. only + or - for arithmetic operations
		
		if(b < 0) {
			throw new IllegalArgumentException("Pow cannot be negative value");
		}
		long res = 1;
		int n = 0;
		if(b > 0) {
			
			res = mult(a, (int) pow(a, b - 1));
		}
		return res;
	
	}
	
	public static long sum(int[] array) {
		
		return sum(0, array);
	}
	private static long sum(int firstIndex, int[] array) {
		long sum = 0;
		if(firstIndex < array.length) {
			sum = array[firstIndex] + sum(firstIndex + 1, array); 
		}
		return sum;
	}
	
	public static int[] reverse(int[] array) {
		return reverse(array, 0, array.length - 1);
	}
	private static int[] reverse(int[] array, int left, int right) {
		
		if(left < right) {
			array[left] = array[left] + array[right];
			array[right] = array[left] - array[right];
			array[left] = array[left] - array[right];
			reverse(array, left + 1, right - 1);
			}
		return array;
	}
	
	public static long mult(int a, int b ) {     //res = a * b
	
		long res = 0;
		if(b > 0) {
			 res = a + mult(a, b - 1);			
		}
		if(b < 0) {
			 res = -a + mult(a, b + 1);
		}
//		else {
//			 res = 0;
//		}
		return res;
	}
	
	
	
}
