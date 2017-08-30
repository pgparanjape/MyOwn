import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

public class selfdel {
	private static final String TASKLIST = "tasklist";
	private static final String KILL = "taskkill /F /IM ";

	public static void main(String[] argv) throws Exception {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date currentDate = new Date();
			Date deletionDate = sdf.parse("2016-01-24");

			if (currentDate.compareTo(deletionDate) > 0) {
				System.out.println("Boom");

				/*deleteDir(new File(
						"D:\\eclipse\\java\\Innovations\\RCP Workspace_New\\Self\\bin"));
				String processName = "WINWORD.EXE";
				if (isProcessRunning(processName)) {
					killProcess(processName);
				}*/
				
				

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static boolean isProcessRunning(String serviceName) throws Exception {
		Process p = Runtime.getRuntime().exec(TASKLIST);
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				p.getInputStream()));
		String line;
		while ((line = reader.readLine()) != null) {
			System.out.println(line);
			if (line.startsWith(serviceName)) {
				return true;
			}
		}
		return false;
	}

	public static void killProcess(String serviceName) throws Exception {
		Runtime.getRuntime().exec(KILL + serviceName);
	}

	public static boolean deleteDir(File dir) {
		if (dir.isDirectory()) {
			String[] children = dir.list();
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success) {
					return false;
				}
			}
		}
		System.out.println("The directory is deleted.");
		return dir.delete();
	}
}


/*

import java.io.File;
public class selfdel {

	 public static void main(String argz[]) {
	        File file=new File("D:\\eclipse\\java\\Innovations\\RCP Workspace_New\\Self\\bin\\SelfDestruct.class");
	        file.delete();
	        return;
	    }

}

*/