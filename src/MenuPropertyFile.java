import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class MenuPropertyFile {

	public static void main(String[] args) {

		try {
			File keywordMapPropertyFile = new File("./menu.properties");
			FileInputStream fileInput = new FileInputStream(keywordMapPropertyFile);
			Properties properties = new Properties();
			properties.load(fileInput);
			fileInput.close();
			 Properties props = new Properties();
             props.setProperty("/fixed & /build_1", "Projects...;");
             props.store(System.out, null);
             
			for(String key : properties.stringPropertyNames()) {
				  String value = properties.getProperty(key);
				  System.out.println(key + "   "+value);
				  if (value != null) {
						String myValues[] = value.split(";");
						String menus[] = myValues[0].split(",");
						String menuString = menus[0];
						for (int i = 1; i < menus.length; i++) {
							menuString = menuString  +"->"+ menus[i].trim();
						}
						System.out.println(key.split("_")[0] + " = " + menuString+"	"+myValues[1]);
					}
				}
			/*String value = properties.
			if (value != null) {
				String myValues[] = value.split(";");
				String reqParam[] = myValues[0].split(",");
				for (int i = 0; i < reqParam.length; i++) {
					reqParam[i] = reqParam[i].trim();
				}
			}*/

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
