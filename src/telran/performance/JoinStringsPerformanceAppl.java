package telran.performance;

public class JoinStringsPerformanceAppl {
	
	private static final int N_STRINGS = 1000;
    private static final int N_RUNS = 100;
	
    public static void main (String[] args) {
		
    	String[] strings = getStrings();
		JoinStringsPerformanceTest testBuilder = 
				new JoinStringsPerformanceTest("Test_Builder", N_RUNS, strings, new JoinStringsBuilderImpl());
		JoinStringsPerformanceTest testString = 
				new JoinStringsPerformanceTest("Test_String", N_RUNS, strings, new JoinStringsImpl());
		testBuilder.run();
		testString.run();
	
    }

	private static String[] getStrings() {
		String[] res = new String[N_STRINGS];
		for(int i = 0; i < N_STRINGS; i++) {
			res[i] = "Hello";
		}
		return res;
	}
	
	
}
