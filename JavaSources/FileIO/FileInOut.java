package JavaSources.FileIO;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * <p>‹berschrift: Java Version of Win2KSec Tool</p>
 * <p>Beschreibung: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Organisation: T-Systems Service Integration, ITC Security</p>
 * @author unbekannt
 * @version 1.0
 */


public class FileInOut extends Workflows.SQL.io.FileIO {
	FileWriter output;
	PrintWriter out;

	//////////////////////////////////////////////////////////////////////////////////////////////////
	//      CONSTRUCTOR: initializing of file descriptor
	public FileInOut(String fullFileName) {
		super(fullFileName);
		try {
			output = new FileWriter(datei);
			out = new PrintWriter(output);
		} catch (Exception e) {}
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////
	//      APPEND DATA IN A FILE
	public boolean append_data(String data) throws IOException {
		try {
			out.append(data);
		}catch (Exception e) {
			System.out.println("\n FileIO-Exception: Fehler beim Schreiben in die Datei");
		}
		return true;
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////
	//      WRITE TEXT INTO A FILE
	public boolean write_text(String data) throws IOException {
		boolean success = true;
		try {
			success = this.write_data_to_file(data);
		} catch (Exception e) {
			System.out.println("\n FileIO-Exception: Fehler beim Schreiben in die Datei");
		}
		return success;
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////
	//      READ TEXT OUT OF A FILE
	public String read_text(int buff_size) throws IOException {
		String text = "";

		try {
			text = super.readFile(buff_size, super.datei);
		}catch (Exception e) {
			System.out.println("\n FileIO-Exception: Fehler beim Lesen aus der Datei");
		}
		return text;
	}

	///////////////////////////////////////////////////////////////////////////////////////////
	public void finishObject() {
		try {
			// Schlieﬂen File
			out.close();
			output.close();
		}catch (IOException e) {}
	}
}
