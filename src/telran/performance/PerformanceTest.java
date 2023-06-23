package telran.performance;

public abstract class PerformanceTest {
	
	private String testName;
	private int nRuns;
	public PerformanceTest(String testName, int nRuns) {
		this.testName = testName;
		this.nRuns = nRuns;
	}
	
	protected abstract void runTest();
	
	public void run() {
		long timeBeforeTest, timeTest = 0;
		timeBeforeTest = System.currentTimeMillis();
		for(int i = 0; i < nRuns; i++) {
			runTest();
		}
		timeTest = System.currentTimeMillis() - timeBeforeTest;
		
		System.out.print("Iterations numbers: " + this.nRuns + "; ");
		System.out.print(" Test: " + this.testName + "; ");
		System.out.print(" Test duration: " + timeTest + " ms");
		System.out.println();
	}
	
}
