package telran.performance;

public class JoinStringsStandardImpl implements JoinStrings {

	@Override
	public String join(String[] strings, String delimiter) {
		
		return String.join(delimiter, strings);
	}

}
