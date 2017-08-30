package JavaSources.Logging;

import Global.Paths;
import JavaSources.FileIO.FileInOut;
import Workflows.SQL.io.FileIO;

public class Logger {

	String fullname = Paths.LogFullFileName;
	FileInOut fio = null;

	static String currentLog = "";
	static boolean firstRun = true;

	final static int BUFF_SIZE_FILE_READER = 100000;

	// Konstruktor für Radio!
	public Logger(String path) {
		fullname = path;
	}

	// Konstruktor für FB!
	public Logger() {
	}

	public void log(String text) {
		// System.out.print(text);
		if (!newFileLog(text)) {
			System.err.print("File Error!");
			System.exit(-1);
		}
	}

	private boolean newFileLog(String increment) {
		FileIO fin = new FileIO(fullname);
		if (firstRun && fin.fileExists()) {
			try {
				currentLog = fin.readFile(BUFF_SIZE_FILE_READER, fin.getFile());
				firstRun = false;
				fin.close();
			} catch (Exception e) {
				fin.close();
				return false;
			}
		}

		currentLog = currentLog + increment;
		fio = new FileInOut(fullname);

		try {
			fio.write_text(currentLog);
		} catch (Exception e) {
			fio.finishObject();
			return false;
		}
		fio.finishObject();
		return true;
	}
}
