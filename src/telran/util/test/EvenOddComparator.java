package telran.util.test;

import java.util.Comparator;

public class EvenOddComparator implements Comparator<Integer> {
	@Override
	public int compare(Integer el1, Integer el2) {
		
		if(el1 % 2 == 0 && el2 %2 == 0) {
			return el1 - el2;
		}
		if(el1 % 2 != 0 && el2 %2 != 0) {
			return el2 - el1;
		}
		if(el1 % 2 != 0 && el2 % 2 == 0) {
			return 1;
		}
		if(el1 % 2 == 0 && el2 % 2 != 0) {
			return -1;
		}
		return 0;
	}
}
