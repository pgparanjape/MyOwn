public class RegularExpressionTest {

	public static void main(String[] args) {

		String testString = "PegaBase /fixed & /build 2016.1.1.0 (TSLPXM2)";
		System.out.println(testString.substring(testString.indexOf("(")+1, testString.indexOf(")")));
		System.out.println(testString);
		String[] groups = testString.split("\\((.*?)\\)");
		for (int i = 0; i < groups.length; i++) {
			System.out.println(groups[i]);
		}
	}

}
