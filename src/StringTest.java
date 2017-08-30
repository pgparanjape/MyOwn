import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class StringTest {

	public static void main(String[] args) {
		
		String str = "String destined for clipboard";

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Clipboard clipboard = toolkit.getSystemClipboard();
		StringSelection strSel = new StringSelection(str);
		clipboard.setContents(strSel, null);
		
		
		
//		pegabase /fixed & /build login error, pegabase /fixed & /build login
		/*String test = "pegabase /fixed & /build login error";
		System.out.println(test);
		System.out.println(test.contains("pegabase /fixed & /build login error"));
		ArrayList<String> allDialogs = new ArrayList<String>();
		allDialogs.add("pegabase /fixed & /build login error");
		allDialogs.add("pegabase /fixed & /build login");
		System.out.println(allDialogs.contains("pegabase //fixed & //build login error"));
		*/
		
		/*int arr[][] = { { 1, 2, 3 }, { 2, 4, 5 }, { 4, 4, 5 } };

		// printing 2D array
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}*/
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		date.setDate(date.getDate()+15);
		System.out.println(date);
		System.out.println(dateFormat.format(date));
		
		String expectedCsvData[][] = new String[][] { { "55467317", "kreditor",
			"Z3", "0001", "EC3", "0001", " ", " ", "Machbarkeitsprüfung",
			"97000", "A2/G-W/01", "55467317",
			"AE - K1A/V1 (ein Streckenpaket mit max. 2 Gegenstellen",
			"inkl. kleiner Doku)", "1", "LE", "lieferdatum", "713.4", "K",
			"30089", " ", "69450159", "0001", "A2/G-W/01", "STGU_Hart",
			"Rennweg", "97-99", "1030", "Wien", "AT", " ", "72102207", "713.4",
			"554674/2017", "554673/2017" } };
		
		for (int i = 0; i < expectedCsvData.length; i++) {
			for (int j = 0; j < expectedCsvData[i].length; j++) {
				System.out.println(i + "  " + j + " "+expectedCsvData[i][j]);
			}
		}
		String aaa = "554674/2017";
		System.out.println(aaa.replaceFirst("/20", ""));
		Arrays.deepEquals(expectedCsvData, expectedCsvData);
	}

}
