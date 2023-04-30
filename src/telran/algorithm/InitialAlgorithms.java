 package telran.algorithm;

public class InitialAlgorithms {
	public static void sortShortPositive(short[] array) {
		int[] helper = new int [Short.MAX_VALUE]; 
		//helper[index] => count of occurrences for number index in array
		//helper[1000] = 2 => number 1000 occurs twice in the source array
		//helper[2] = 0 => 
		for(int i = 0; i < array.length; i++) {  //2, 2, 2  => 0, 0, 3
			helper[array[i]]++;  
			}
		int ind = 0; 
		for(int i = 0; i < helper.length; i++) {
			for(int j = 0; j < helper[i]; j++) {
				array[ind++] =  (short) i;
			}
		}
	}
	
	public static boolean isSum2(short[] array, short sum) {
		int[]helper = new int [sum + 1];
		for(int i = 0; i <= sum; i++) {
			helper[array[i]]++;
		}
		boolean flag = false;
		short index = 0;
		while((index <= sum - index) && flag == false ) {
			if((helper[index] != 0) && (helper[sum - index] != 0) ||
					helper[index] > 1 || helper[sum - index] > 1) {
				index++;
				flag = true;
			}
		}
		return flag;
	}
		
		
	public static short getMaxPositiveWithNegativeReflect(short[] array) {
		//returns maximal positive number, for which there is the negative image
		//If there are not such numbers at all the method returns -1 
		//TODO
		return -1;
	}
}
