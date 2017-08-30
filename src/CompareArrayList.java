import java.util.ArrayList;
import java.util.Arrays;

public class CompareArrayList {

	public static void main(String[] args) {
		// Create ArrayLists
		String[] A1 = {

		"A:test_admin,H:pparanja,C:q4de3qsy802,I:229461,S:",
				"A:test_admin,H:pparanja,C:q4de3qsy802,I:229461,S:",
				"A:test_admin,H:pparanja,C:q4de3qsy802,I:229461,S:",
				"A:test_admin,H:pparanja,C:q4de3qsy802,I:229461,S:",
				"A:test_admin,H:pparanja,C:q4de3qsy802,I:229461,S:"

		};
		ArrayList<String> a1 = new ArrayList(Arrays.asList(A1));
		String[] A2 = { "A:test_admin,H:pparanja,C:q4de3qsy802,I:229461,S:",
				"A:test_admin,H:pparanja,C:q4de3qsy802,I:229461,S:",
				"A:test_admin,H:pparanja,C:q4de3qsy802,I:229461,S:",
				"A:test_admin,H:pparanja,C:q4de3qsy802,I:229461,S:",
				"A:test_admin,H:pparanja,C:q4de3qsy802,I:229461,S:",
				"A:test_admin,H:pparanja,C:q4de3qsy802,I:229461,S:Cell",
				"A:test_admin,H:pparanja,C:q4de3qsy802,I:229461,S:" };
		ArrayList<String> a2 = new ArrayList(Arrays.asList(A2));
		// Check ArrayLists
//		System.out.println("a1 = " + a1);
//		System.out.println("a2 = " + a2);
		// Find difference
		for (String s : a1)
			a2.remove(s);
		// Check difference
		System.out.println("a1 = " + a1);
		System.out.println("a2 = " + a2);
	}

}


/*Collection<String> listOne = new ArrayList<String>(Arrays.asList(
		"A:test_admin,H:pparanja,C:q4de3qsy802,I:229461,S:",
		"A:test_admin,H:pparanja,C:q4de3qsy802,I:229461,S:",
		"A:test_admin,H:pparanja,C:q4de3qsy802,I:229461,S:",
		"A:test_admin,H:pparanja,C:q4de3qsy802,I:229461,S:",
		"A:test_admin,H:pparanja,C:q4de3qsy802,I:229461,S:",
		"A:test_admin,H:pparanja,C:q4de3qsy802,I:229461,S:Cell",
		"A:test_admin,H:pparanja,C:q4de3qsy802,I:229461,S:"));
Collection<String> listTwo = new ArrayList<String>(Arrays.asList(
		"A:test_admin,H:pparanja,C:q4de3qsy802,I:229461,S:",
		"A:test_admin,H:pparanja,C:q4de3qsy802,I:229461,S:",
		"A:test_admin,H:pparanja,C:q4de3qsy802,I:229461,S:",
		"A:test_admin,H:pparanja,C:q4de3qsy802,I:229461,S:",
		"A:test_admin,H:pparanja,C:q4de3qsy802,I:229461,S:"
		));

List<String> sourceList = new ArrayList<String>(listOne);
List<String> destinationList = new ArrayList<String>(listTwo);

sourceList.removeAll(listTwo);
destinationList.removeAll(listOne);

System.out.println(sourceList);
System.out.println(destinationList);
*/