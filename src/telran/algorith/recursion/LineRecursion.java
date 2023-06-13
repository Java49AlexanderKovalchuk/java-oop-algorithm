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
				
		if(b < 0) {
			throw new IllegalArgumentException("Pow cannot be negative value");
		}
		long res = 1;
		
		if(b > 0) {
			
			res = multiply(a, pow(a, b - 1));
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
	
	public static int multiply(int a, long b ) {     //res = a * b
	
		int res = 0;
		if(b != 0) {
			res = b < 0 ? multiply(-a, -b) : a + multiply(a, b - 1);
		}

		return res;
	}
	
	public static long square (int x) {
		long res = 0;
		if(x != 0) {
			res = x < 0 ? square(-x) : x + x - 1 + square(x - 1); 
		}
		return res;
	}
	
	public static boolean isSubstring(String string, String substr) {
		
		boolean res = false;
		if(string.length() >= substr.length()) {
			res = isEqual(string, substr) ? true : isSubstring(string.substring(1), substr);
		}
		
		return res; 
	
	}
	private static boolean isEqual(String string, String substr) {
		boolean res = false;
		if(substr.length() == 0) {
			res = true;
		}
		else if(string.charAt(0) == substr.charAt(0)) {
			res = isEqual(string.substring(1), substr.substring(1));
		}
		return res;
	}
	
}
