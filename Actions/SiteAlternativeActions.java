package PB_2015_2.FB.Actions;

import java.util.ArrayList;
import java.util.HashMap;

import PB_2015_2.Common.Const;
import PB_2015_2.Common.Utils;
import PB_2015_2.FB.ObjRepo.FBRepo;

import com.rational.test.ft.object.interfaces.TextGuiSubitemTestObject;
import com.rational.test.ft.vp.ITestDataTable;

public class SiteAlternativeActions extends FBRepo {
	
	private ITestDataTable myTable;
	public boolean onlyOneResult = false;

	public void openSiteAltSearchScreen() {
		sleep(2);
		pMenuBar().click(atPath("Sites"));
		sleep(1);
		pMenuBar().click(atPath("Sites->Site Alternatives"));
		sleep(2);
		Utils.waitForScreenToLoad("site alternatives / sites select", 50);
	}

	public void search(String whichField, String whatValue) {
		sleep(2);
		if (whichField.equalsIgnoreCase("AlternativeName")) {
			TextGuiSubitemTestObject alternativeName = (TextGuiSubitemTestObject) Utils.getObj(
					".priorLabel", "Alternative Name:", "name",
					".edsiteAlternative");
			alternativeName.click(atPoint(38, 10));
			sleep(1);
			alternativeName.setText(whatValue);
			sleep(1);
		}
		if (whichField.equalsIgnoreCase("City")) {
			TextGuiSubitemTestObject city = (TextGuiSubitemTestObject) Utils.getObj(
					".priorLabel", "City:", "name", ".proDBTextFieldCity");
			city.click(atPoint(38, 10));
			sleep(1);
			city.setText(whatValue);
			sleep(1);
		}
		sleep(1);
		clickSearchButton();
		sleep(2);
		Utils.waitForScreenToClose("Dauer", 30);
		sleep(5);
		if (SysInfoDialog().exists()) {
			SysInfoDialogOk().click();
			sleep(1);
			searchSiteScreenDialog().click(atPoint(357,15));
		}
		if (onlyOneResult){
			Utils.waitForScreenToLoad("Site Alternative  -  ", 120);
		}else if (editDialog().exists() && !searchSiteScreenDialog().exists()){
			editDialog().click(atPoint(347,13));
			sleep(2);
			Utils.getGuiTestObjByName(".btAuswahl").click();
			sleep(2);
			searchSiteScreenDialog().click(atPoint(357,15));
		}
	}
	
	public ArrayList<String> getSiteListValues(String whichColumn) {
		sleep(2);
		myTable = (ITestDataTable) SiteAltListTable().getTestData("contents");
		ArrayList<String> siteListArray = new ArrayList<>();
		int whichHeader = 0;
		try {
			whichHeader = Integer.parseInt(whichColumn);
		} catch (Exception ex) {
			for (int i = 0; i < myTable.getColumnCount(); i++) {
				String columnHeader = (String) myTable.getColumnHeader(i);
				if (columnHeader.contains(whichColumn)) {
					whichHeader = i;
				}
			}
		}
		for (int row = 0; row < myTable.getRowCount(); row++) {
			siteListArray.add((String) myTable.getCell(row, whichHeader));
		}
		return siteListArray;

	}

	public String getProperty(String whichField, String propertyName) {
		String returnString = "";
		TextGuiSubitemTestObject alternativeName = (TextGuiSubitemTestObject) Utils.getObj(
				".priorLabel", "Alternative Name:", "name",
				".edsiteAlternative");
		if (whichField.equalsIgnoreCase("AlternativeName")) {
			returnString = alternativeName.getProperty("maxLength")
					.toString();
		}
		if (whichField.equalsIgnoreCase("City")) {
			TextGuiSubitemTestObject city = (TextGuiSubitemTestObject) Utils.getObj(
					".priorLabel", "City:", "name", ".proDBTextFieldCity");
			returnString = city.getProperty("maxLength").toString();
		}
		return returnString;
	}

	public String createNewsiteAlternative(
			HashMap<String, String> saCreateCriteria) {
		String retString = Const.SUCCESSMSG;
		Utils.waitForScreenToLoad("Site Alternative  -  ", 60);
		clickNewButton();
		Utils.waitForScreenToLoad("Site Alternative Create", 60);
		sleep(1);
		if (saCreateCriteria.get("name") != null) {
			siteAltNameTextField().setText(saCreateCriteria.get("name"));
			sleep(1);
		}
		if (saCreateCriteria.get("city") != null) {
			siteAltStreetTextField().setText(saCreateCriteria.get("city"));
			sleep(1);
		}
		if (saCreateCriteria.get("zip") != null) {
			siteAltZipTextField().setText(saCreateCriteria.get("zip"));
			sleep(1);
		}
		if (saCreateCriteria.get("country") != null) {
			siteAltCountryListBox().click();
			siteAltCountryListBox().click(
					atText(saCreateCriteria.get("country")));
			sleep(1);
		}
		siteAltSaveButton().click();
		sleep(10);
		if (SysInfoDialog().exists()) {
			sleep(1);
			retString = (String) errorText().getProperty("text");
			sleep(1);
			SysInfoDialogOk().click();
			sleep(1);
			closeAll();
		}
		return Utils.removeHtml(retString);
	}

}
