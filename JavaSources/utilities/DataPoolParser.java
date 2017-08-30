package JavaSources.utilities;

public class DataPoolParser {

	public static String readBundleNumberA_B(String textCode) {
		return textCode.substring(0, posOfSemicolon(textCode));
	}

	public static String readBundleNumberB_A(String textCode) {
		return textCode.substring(posOfSemicolon(textCode) + 1);
	}

	private static int posOfSemicolon(String textCode) {
		int i;

		for (i = 0; i < textCode.length(); i++) {
			if (textCode.charAt(i) == ';')
				break;
		}

		return i;
	}

}
