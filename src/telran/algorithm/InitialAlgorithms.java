 package telran.algorithm;

import java.util.Comparator;

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
  		int helperSize = sum < 0 ? Short.MAX_VALUE + 1 : sum + 1;
		boolean[] helper = new boolean[helperSize];
		boolean res = false;
		int index = 0;
		while (index < array.length && !res) {
			short value = array[index];
			short secondValue = (short) (sum - value);
			if (secondValue >= 0) {
				if (helper[secondValue]) {
					res = true;
				} else {
					helper[value] = true;
				}
			}
			index++;
		}
		return res;
	}
		
	public static short getMaxPositiveWithNegativeReflect(short[] array) {
		//returns maximal positive number, for which there is the negative image
		//If there are not such numbers at all the method returns -1 
		short res = -1;
		byte[] helper = new byte [Short.MAX_VALUE];
		short candidate = -1;
		for (int i = 0; i < array.length; i++) {
			candidate = (short) Math.abs(array[i]);
			if (array[i] < 0) {
				res = getRes(res, helper, candidate, 1);
			} else {
				res = getRes(res, helper, candidate, -1);
			}			
		}
		return res;
	}

	private static short getRes(short res, byte[] helper, short candidate, int sign) {
		if(helper[candidate] == 1 * sign && candidate > res) {
			res = candidate;
		} else if (helper[candidate] == 0) {
			helper[candidate] = (byte) (-1 * sign);
		}
		return res;
	}
	
	public static void bubbleSort(short[] array) {
		// sorting with bubble sort algorithm
		int size = array.length;
		boolean flUnsorted = false;
		do {
			size--;
			flUnsorted = false;
			for (int i = 0; i < size; i++) {
				if (array[i] > array[i + 1]) {
					short tmp = array[i];
					array[i] = array[i + 1];
					array[i + 1] = tmp;
					flUnsorted = true;
				}
			}
		} while (flUnsorted);
	} 
	
	public static <T> int binarySearch(T [] array, T key, Comparator<T> comp) {
		int leftIndex = 0;
		int rightIndex = array.length - 1;
		int middleIndex = rightIndex / 2;
		int compRes = 0;
		while(leftIndex <= rightIndex && 
				(compRes = comp.compare(key, array[middleIndex])) != 0) {
			if (compRes > 0) {
				leftIndex = middleIndex + 1;
			} else {
				rightIndex = middleIndex - 1; 
			}
			middleIndex = (leftIndex + rightIndex) / 2; 
		}
		return leftIndex > rightIndex ? -1 : middleIndex;
	}
	
	public static <T> int binarySearchUpdated(T [] array, T key, Comparator<T> comp) {
		int left = 0;
		int right = array.length - 1;
		int middle = right / 2;
		while(right >= left && comp.compare(key, array[left]) != 0) {
			if(comp.compare(key, array[middle]) <= 0) {
				right = middle - 1; 
			}
			else {
				left = middle + 1;
			}
			middle = (left + right) / 2;
		}

			return left < array.length && comp.compare(key, array[left]) == 0 ? 
					left : -(left + 1); 
	}
		
}
		
   