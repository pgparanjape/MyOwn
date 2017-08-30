package JavaSources.importfiles;

import JavaSources.FileIO.FileInOut;


/**
 * <p>Überschrift: Java Version of Win2KSec Tool</p>
 * <p>Beschreibung: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Organisation: T-Systems Service Integration, ITC Security</p>
 * @author unbekannt
 * @version 1.0
 */


public class      ImportComponentsInNeGroup		
  {
	String				path						= Global.Paths.ImportPath;
	String				fileName					= Global.Paths.ImportFileNameNeGroup;
	FileInOut			fio							= null;

	// Konstruktor 
	public ImportComponentsInNeGroup()	{}
	
	public boolean			createImportFile(String text)		{
		
		fio				= new FileInOut(path + "\\" + fileName);

		try	{
			fio.write_text(text);
		} 
		catch(Exception e)	{
			fio.finishObject();
			return	false;
		}
		fio.finishObject();
		
		return true;
	}
  }
