package telran.algorithm;

public class MemoryService {
	
	public static int getMaxAvailableSize() {
//		int res = Integer.MAX_VALUE;
//		boolean running = true;
//		while (running) {
//			try {
//				byte[] array = new byte[res];	
//				running = false;
//			} catch(OutOfMemoryError e) {
//				res--;
//			}
//		}
//		return res;
		int left = 0;
		long right = Integer.MAX_VALUE;
		int middle = (int) (right / 2);
		while(left <= right) {
			try {
			byte[] array = new byte[middle];
				left = middle + 1;
			}
			catch(OutOfMemoryError e) {
				right = middle - 1;
			}
			middle = (int)((left + right) / 2);			
		}
		return (int) right;
		//NOTE: VM argument Xmx600m Xms200m t = 1.303s
		//      VM argument Xmx600m Xms50m t = 1.328s
		//      VM argument Xmx600m Xms10m t = 1.321s
		//      VM argument Xmx1200m Xms200m t = 2.547s
		//      VM argument Xmx1200m Xms50m t = 2.680s
		//      VM argument Xmx1200m Xms10m t = 2.578s
		
		//default VM arguments t = 4.772s;	
	}
}
