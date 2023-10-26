package telran.performance;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

import telran.strings.*;

public class JoinStringsPerformanceAppl {

	private static final int N_STRINGS = 1000;
	private static final int N_RUNS = 1000;
	private static final String BASE_PACKAGE = "telran.performance.";
	
    //FIXME rewrite the code by applying class reflection
	// to get rid of JoinStrings implementations
	
	public static void main(String[] args) {
		
		for(String className: args) {
			
			try {
				Class<JoinStrings> clazz = (Class<JoinStrings>) Class.forName(BASE_PACKAGE + className);
				Constructor<JoinStrings> constructor = clazz.getConstructor();
				JoinStrings joinStrings = constructor.newInstance();
				JoinStringsPerformanceTest test = new JoinStringsPerformanceTest(clazz.getSimpleName(), N_RUNS,
						getStrings(), joinStrings);
				test.run();
			} catch (ClassNotFoundException e) {
				System.out.println(e.getMessage() + " not found");
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	private static String[] getStrings() {
		String[] res = new String[N_STRINGS];
		Arrays.fill(res, "string");
		return res;
	}

}