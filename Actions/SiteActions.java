package PB_2015_2.FB.Actions;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import PB_2015_2.Common.Const;
import PB_2015_2.Common.Utils;
import PB_2015_2.FB.ObjRepo.FBRepo;
import PB_2015_2.Verification.Verify;

import com.rational.test.ft.object.interfaces.GuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.GuiTestObject;
import com.rational.test.ft.object.interfaces.TextGuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.TextSelectGuiSubitemTestObject;
import com.rational.test.ft.vp.ITestDataTable;

public class SiteActions extends FBRepo {
	
	public static String externalServiceInitiateOrdering = null;
	private ITestDataTable myTable;
	String newSiteArchiveNo = "";
	
	public void openSearchSite() {
		sleep(2);
		pMenuBar().click(atPath("Sites"));
		sleep(1);
		pMenuBar().click(atPath("Sites->Edit"));
		sleep(2);
		Utils.waitForScreenToLoad("search areas / sites select", 10);
	}
	public void searchAndOpen(String whichField, String whatValue) {
		if (!Utils.getDialogTitles().contains("search areas / sites select")){
			openSearchSite();
		}
		sleep(2);
		if (whichField.equalsIgnoreCase("IndentCode")) {
			TextGuiSubitemTestObject indentCodeTextField = (TextGuiSubitemTestObject) Utils.getObj("name",".proDBTextFieldStoKng");
			indentCodeTextField.click(atPoint(38, 10));
			sleep(1);
			indentCodeTextField.setText(whatValue);
		}
		if (whichField.equalsIgnoreCase("ExtIndentCode")) {
			((TextGuiSubitemTestObject) Utils.getObj("name",".edDFMG_ID")).setText(whatValue);
		}
		if (whichField.equalsIgnoreCase("SiteName")) {
			((TextGuiSubitemTestObject) Utils.getObj("name",".proDBTextFieldStoName")).setText(whatValue);
		}
		if (whichField.equalsIgnoreCase("VPSZ")) {
			((TextGuiSubitemTestObject) Utils.getObj("name",".edVPSZ")).setText(whatValue);
		}
		sleep(1);
		clickSearchButton();
		sleep(2);
		
		for (int i = 0; i < 2
				&& (!editDialog().exists() || durationPopup().exists()); i++) {
			sleep(10);
		}
		for (int i = 0; i < 10 && durationPopup().exists(); i++) {
			sleep(10);
		}
		if (systemInformationPopup().exists()) {
			((GuiTestObject)Utils.getObj("accessibleContext.accessibleName", "OK")).click();
//			systemInformationPopupOkButton().click();
			sleep(1);
			searchSiteScreenDialog().click(atPoint(357,15));
		}
		if (!editDialog().exists() && searchSiteScreenDialog().exists()){
			sleep(2);
			searchSiteScreenDialog().click(atPoint(358,12));
			sleep(2);
			GuiSubitemTestObject seachListTable = (GuiSubitemTestObject) Utils.getObj(".class","com.tmobile.itnetdev.fixedbuild.nevbz.presentation.framework.NeVbzTable$Table");
			seachListTable.click(atCell(atRow(atIndex(0)), atColumn(atIndex(1))),atPoint(43, 4));
			sleep(1);
			Utils.getGuiTestObjByName(".jButtonEdit").click();
			sleep(2);
			editDialog().click(atPoint(347,13));
			sleep(2);
		}
		sleep(2);
	}
	
	public void search(String whichField, String whatValue) {
		sleep(2);
		if (whichField.equalsIgnoreCase("IndentCode")) {
			TextGuiSubitemTestObject indentCodeTextField = (TextGuiSubitemTestObject) Utils
					.getObj("name", ".proDBTextFieldStoKng");
			indentCodeTextField.click(atPoint(38, 10));
			sleep(1);
			indentCodeTextField.setText(whatValue);
		}
		if (whichField.equalsIgnoreCase("ExtIndentCode")) {
			((TextGuiSubitemTestObject) Utils.getObj("name", ".edDFMG_ID"))
					.setText(whatValue);
		}
		if (whichField.equalsIgnoreCase("SiteName")) {
			((TextGuiSubitemTestObject) Utils.getObj("name",
					".proDBTextFieldStoName")).setText(whatValue);
		}
		if (whichField.equalsIgnoreCase("VPSZ")) {
			((TextGuiSubitemTestObject) Utils.getObj("name", ".edVPSZ"))
					.setText(whatValue);
		}
		sleep(1);
		clickSearchButton();
		sleep(5);
		if (information().exists()) {
			yes().click();
			sleep(2);
		}
		for (int i = 0; i < 2
				&& (!editDialog().exists() || durationPopup().exists()); i++) {
			sleep(10);
		}
		for (int i = 0; i < 10 && durationPopup().exists(); i++) {
			sleep(10);
		}
		if (SysInfoDialog().exists()) {
			((GuiTestObject) Utils.getObj("accessibleContext.accessibleName",
					"OK")).click();
			// systemInformationPopupOkButton().click();
			sleep(1);
			Utils.waitForScreenToLoad("Search Areas / Sites select", 10);
		}
		if (systemInformationPopup().exists()) {
			((GuiTestObject) Utils.getObj("accessibleContext.accessibleName",
					"OK")).click();
			// systemInformationPopupOkButton().click();
			Utils.waitForScreenToLoad("Search Areas / Sites select", 10);
		}

		if (editDialog().exists() && !searchSiteScreenDialog().exists()) {
			sleep(2);
			Utils.getGuiTestObjByName(".btAuswahl").click();
			sleep(2);
			Utils.waitForScreenToLoad("Search Areas / Sites select", 10);
		}
		if (Utils.getDialogTitles().contains("search areas / site -")) {
			sleep(2);
			Utils.getGuiTestObjByName(".btAuswahl").click();
			sleep(2);
			Utils.waitForScreenToLoad("Search Areas / Sites select", 10);
		}
	}

	public ArrayList<String> getSiteListValues(String whichColumn) {

		Utils.waitForScreenToLoad("Search Areas / Sites select", 10);
		sleep(2);
		/*String readbleColumnNames[] = { "NE-Type", "SiteName", "IndentCode",
				"Master SiteName", "VPSZ", "NE-ID", "Node-ID", "Configuration",
				"Rollout Plan", "Start Date", "End Date", "Locked", "Working",
				"Project-No", "ProjectType", "Template", "Region", "SubArea",
				"Owner", "SiteGroup", "NE-Name", "Status", "Parameter", "Value" };*/

		sleep(2);
		GuiTestObject seachListTable = (GuiTestObject) Utils.getObj(".class","com.tmobile.itnetdev.fixedbuild.nevbz.presentation.framework.NeVbzTable$Table");
		myTable = (ITestDataTable) seachListTable.getTestData("contents");
		ArrayList<String> siteListArray = new ArrayList<>();
		int whichHeader = 0;
		try {
			whichHeader = Integer.parseInt(whichColumn);
		} catch (Exception ex) {
			for (int i = 0; i < myTable.getColumnCount(); i++) {
				String columnHeader = (String) myTable.getColumnHeader(i);
				System.out.println(columnHeader);
				if (columnHeader.equalsIgnoreCase("SiteName")) {
					whichHeader = i;
				}
			}
		}

		for (int row = 0; row < myTable.getRowCount(); row++) {
			siteListArray.add((String) myTable.getCell(row, whichHeader));
		}
		return siteListArray;
	}

	public void openSiteFromSiteList(int whichRow) {
		sleep(2);
		Utils.waitForScreenToLoad("Search Areas / Sites select", 10);
		GuiSubitemTestObject seachListTable = (GuiSubitemTestObject)Utils.getObj(".class","com.tmobile.itnetdev.fixedbuild.nevbz.presentation.framework.NeVbzTable$Table");
		seachListTable.click(atCell(atRow(atIndex(whichRow)), atColumn(atIndex(1))));
		sleep(1);
		Utils.getGuiTestObjByName(".jButtonEdit").click();
		sleep(2);
		editDialog().click(atPoint(347,13));
		sleep(2);
	}

	public void closeEditSiteScreen() {
		sleep(2);
		Utils.getGuiTestObjByName(".jButtonClose ").click();
		sleep(2);
	}

	public String createNewSite(HashMap<String, String> siteCreateCriteria) {
		Utils.waitForScreenToLoad("search areas / sites select", 10);
		sleep(2);
		Utils.getGuiTestObjByName(".acButtonNew").click();
		sleep(2);
		Utils.waitForScreenToLoad("search area / site create", 30);

		for (Map.Entry<String, String> mapEntry : siteCreateCriteria.entrySet()) {
			String key = mapEntry.getKey().toLowerCase();
			String value = mapEntry.getValue();
			if (key.contains("regionshortname")) {
				sleep(1);
				TextSelectGuiSubitemTestObject regionShortName = ((TextSelectGuiSubitemTestObject) Utils
						.getObj("name", ".neVbzComboBoxBranch")); 
				regionShortName.click();
				regionShortName.click(atText(value));
			}
			if (key.contains("sitename")) {
				sleep(1);
				((TextGuiSubitemTestObject) Utils.getObj("name",".proDBTextFieldLocName")).setText(value);
				sleep(2);
				mainFrame().inputKeys("{TAB}");
				sleep(3);
			}
			if (key.contains("indentname")) {
				sleep(1);
				((TextGuiSubitemTestObject) Utils.getObj("name",
						".proDBTextFieldSearchCircle")).setText(value);
			}
			if (key.contains("extindentcode")) {
				sleep(1);
				((TextGuiSubitemTestObject) Utils.getObj("name", ".edDFMG_ID"))
						.setText(value);
			}
			if (key.contains("vpsz")) {
				sleep(1);
				vpszOnNewSiteTextField().setText(value);
			}
			if(key.contains("indentcodenew")){
				sleep(1);
				indentCodeOnNewSiteTextField().setText("");
				sleep(1);
				indentCodeOnNewSiteTextField().setText(value);
			}
		}
		return saveSite();
	}

	/**
	 * @return
	 */
	private String saveSite() {
		if (saveOnNewSiteButton().exists()){
			saveOnNewSiteButton().click();
			sleep(20);
			String ChangesSaved_text = (String)changesSaved().getProperty("text");
			newSiteArchiveNo = ((TextGuiSubitemTestObject) Utils.getObj("name",
					".proDBTextFieldArchiveNo")).getText();
			for (int i = 0; i < 5
					&& !ChangesSaved_text
							.equalsIgnoreCase(Const.CHANGESSAVED); i++) {
				sleep(2);
				ChangesSaved_text = (String)changesSaved().getProperty("text");
			}
			sleep(2);
			if (ChangesSaved_text.equalsIgnoreCase(Const.CHANGESSAVED)||Const.CHANGESSAVED.startsWith(ChangesSaved_text)) {
				return Const.SUCCESSMSG;
			} else {
				return Const.NOTSUCCESS;
			}
		}else{
			return Const.NOTSUCCESS;
		}
	}

	public String editSiteMasterData(String whichField, String value) {
		sleep(2);
		if (whichField.toLowerCase().contains("extindentcode")) {
			((TextGuiSubitemTestObject) Utils.getObj("name", ".edDFMG_ID"))
					.setText(value);
			sleep(1);
		} else {
			return Const.NOTSUCCESS;
		}
		return saveSite();

	}
	public String addSiteAlternative(HashMap<String, String> saCreateCriteria, boolean useDefault) {
		String retMsg = Const.SUCCESSMSG;
		sleep(2);
		editDialog().click(atPoint(336,13));
		((GuiSubitemTestObject)Utils.getObj("name",".jTabbedPane")).click(atText("Alternatives"));
		sleep(5);
		Utils.getGuiTestObjByName(".acButtonNewAlternative").click();
		Utils.waitForScreenToLoad("Site Alternative Create", 60);
		sleep(1);
		if (saCreateCriteria.get("name") != null) {
			siteAltNameTextField().setText(saCreateCriteria.get("name"));
			sleep(1);
		}else if (useDefault) {
			siteAltNameTextField().setText("SiteAlt-"+Utils.getRandomNoStr(99));
			sleep(1);
		}
		if (saCreateCriteria.get("city") != null) {
			siteAltStreetTextField().setText(saCreateCriteria.get("city"));
			sleep(1);
		}else if (useDefault) {
			siteAltStreetTextField().setText("City");
			sleep(1);
		}
		if (saCreateCriteria.get("zip") != null) {
			siteAltZipTextField().setText(saCreateCriteria.get("zip"));
			sleep(1);
		}else if (useDefault) {
			siteAltZipTextField().setText("123");
			sleep(1);
		}
		if (saCreateCriteria.get("country") != null) {
			siteAltCountryListBox().click();
			sleep(1);
			siteAltCountryListBox().click(
					atText(saCreateCriteria.get("country")));
			sleep(1);
		} else if (useDefault) {
			siteAltCountryListBox().click();
			sleep(1);
			siteAltCountryListBox().click(atText("Deutschland"));
			sleep(1);
		}
		siteAltSaveButton().click();
		sleep(10);
		for (int i = 0; i < 10 && !siteAltSaveButton(ANY, DISABLED).exists(); i++) {
			sleep(2);
		}
		if (SysInfoDialog().exists()) {
			sleep(1);
			retMsg = (String) errorText().getProperty("text");
			sleep(1);
			SysInfoDialogOk().click();
			sleep(1);
		}
		sleep(2);
		GuiTestObject abc = (GuiTestObject)Utils.getObj("toolTipText","Close [Alt+L]", ".classIndex","0");
		abc.click();
		sleep(1);
		if (!retMsg.equalsIgnoreCase(Const.SUCCESSMSG)) {
			sleep(2);
			no().click();
			sleep(2);
		}
		return Utils.removeHtml(retMsg);
	}
	public String delete() {
		sleep(2);
		editDialog().click(atPoint(336, 13));
		sleep(1);
		Utils.getGuiTestObjByName(".acButtonDelete").click();
		sleep(4);
		DeleteConfirmationPopUp().click(atPoint(128, 11));
		sleep(1);
		yes().click();
		sleep(10);
		if (SysInfoDialog().exists()){
			sleep(1);
			String DataNotPossibleToDelete_text = (String)dataNotPossibleToDelete().getProperty("text");
			String dependentDataExists_text = (String)dependentDataExists().getProperty("text");
			SysInfoDialogOk().click();
			return DataNotPossibleToDelete_text+ dependentDataExists_text;
		}else if (ErrorMessagePopUp().exists()) {
			String errorMessage = (String) errorLabel().getProperty("text");
			ok().click();
			return errorMessage;
		} else if (systemInformationPopup().exists()){
			systemInformationPopup().click(atPoint(270, 10));
			String errorMessage = (String) errorLabel().getProperty("text");
			ok().click();
			return errorMessage;
		}else{
			return Const.SUCCESSMSG;
		}
	}

	public void removeSiteAlternative(String siteAltName) {
		sleep(10);
		((GuiSubitemTestObject) Utils.getObj("name", ".jTabbedPane"))
				.click(atText("Alternatives"));
		sleep(1);
		siteAltListTable().click(
				atCell(atRow("headerAlteName", siteAltName),
						atColumn("headerAlteName")));
		sleep(1);
		Utils.getGuiTestObjByName(".jButtonEditAlternative").click();
		sleep(5);
		Utils.waitForScreenToLoad("Site Alternative",60);
		siteAltDeleteButton().waitForExistence(300.00,20.00);
		sleep(2);
		siteAltDeleteButton().click();
		sleep(2);
		DeleteConfirmationPopUp().click(atPoint(153, 13));
		sleep(1);
		yes().click();
		sleep(2);
		Utils.waitForScreenToClose("Site Alternative",60);
	}
	
	public String addParam(String paramGrpName, String paramName) {
		Utils.waitForScreenToLoad("search area / site", 30);
		((GuiSubitemTestObject)Utils.getObj("name",".jTabbedPane")).click(atText("Parameters"));
		sleep(2);
		clickNewButton();
		Utils.waitForScreenToLoad("Parameters Choice", 30);
		sleep(1);
		parameterGroup().click();
		parameterGroup().click(atText(paramGrpName));
		sleep(1);
		((TextGuiSubitemTestObject) Utils.getObj(".priorLabel", "Name:",
				".classIndex", "0")).setText(paramName);
		sleep(1);
		clickSearchButton();
		sleep(10);
		if (_7995G6G().exists()) {
			_7995G6G().click(atCell(atRow(atIndex(0)), atColumn("headerName")));
			sleep(1);
			clickChooseButton();
			Utils.waitForScreenToClose("Parameters Choice", 30);
		}
		if (SysInfoDialog().exists()) {
			String errorMessage = (String) _htmlNoDataRecordFoundHtml2()
					.getProperty("text");
			sleep(2);
			SysInfoDialogOk().click();
			sleep(2);
			closeAltL().click();
			return Utils.removeHtml(errorMessage);
		}
		return saveSite();
	}

	public ArrayList<String> getParameterValue(String whichParam) {
		ArrayList<String> retArrayList = new ArrayList<String>();
		Utils.waitForScreenToLoad("search area / site", 30);
		((GuiSubitemTestObject)Utils.getObj("name",".jTabbedPane")).click(atText("Parameters"));
		sleep(2);
		GuiSubitemTestObject paramTable = (GuiSubitemTestObject) Utils
				.getObj(".class",
						"com.tmobile.itnetdev.fixedbuild.nevbz.presentation.framework.NeVbzTable$Table");
		ITestDataTable myTable = (ITestDataTable) paramTable
				.getTestData("contents");
		for (int row1 = 0; row1 < myTable.getRowCount(); row1++) {
			String temp = (String) myTable.getCell(row1, 1);
			if (temp.equalsIgnoreCase(whichParam)) {
				for (int col = 0; col < myTable.getColumnCount(); col++) {
					try {
						String paramValue = (String) myTable.getCell(row1, col);
						retArrayList.add(paramValue);
					} catch (Exception e) {}
				}
				break;
			}
		}
		return retArrayList;
	}

	public String updateParameterValue(String whichParam, String whatValue) {
		Utils.waitForScreenToLoad("search area / site", 30);
		((GuiSubitemTestObject)Utils.getObj("name",".jTabbedPane")).click(atText("Parameters"));	
		sleep(2);
		GuiSubitemTestObject paramTable = (GuiSubitemTestObject) Utils
				.getObj(".class",
						"com.tmobile.itnetdev.fixedbuild.nevbz.presentation.framework.NeVbzTable$Table");
		ITestDataTable myTable = (ITestDataTable) paramTable.getTestData("contents");
		for (int row = 0; row < myTable.getRowCount(); row++) {
			String temp = (String) myTable.getCell(row, 1);
			if (temp.equalsIgnoreCase(whichParam)) {
				paramTable.doubleClick(atCell(atRow(atIndex(row)),
						atColumn(atIndex(3))));
				sleep(5);
//				pTextArea2().setText(whatValue);
				mainFrame().inputKeys(whatValue);
				sleep(5);
				paramTable.click(atCell(atRow(atIndex(row)),atColumn(atIndex(0))));
				sleep(1);
				paramTable.click(atCell(atRow(atIndex(row)),atColumn(atIndex(0))));
				sleep(5);
				break;
			}
		}
		return saveSite();
	}
	
	public void checkSiteAltColumnEditable() {
		Utils.waitForScreenToLoad("search area / site", 30);
		((GuiSubitemTestObject)Utils.getObj("name",".jTabbedPane")).click(atText("Alternatives"));	
		sleep(5);
		for (int i = 0; i <= 4; i++) {
			siteAltListTable().doubleClick(atCell(atRow(atIndex(0)), atColumn(atIndex(i))));
			sleep(2);
			if (Utils.waitForScreenToLoad("Site Alternative", 60)) {
				Utils.getGuiTestObjByToolTip("Close [Alt+L]").click();
				sleep(2);
			} else {
				Verify.failDueToUnexpectedError("Site Alt Column " + i+ " is editable");
			}
		}
		
		for (int i = 5; i <= 9; i++) {
			siteAltListTable().doubleClick(atCell(atRow(atIndex(0)), atColumn(atIndex(i))));
			sleep(2);
			if (Utils.waitForScreenToLoad("Site Alternative", 25)) {
				Verify.failDueToUnexpectedError("Site Alt Column " + i+ " is non editable");
				Utils.getGuiTestObjByToolTip("Close [Alt+L]").click();
				sleep(2);
			} 
		}
	}

	public String isSiteAltChosen(String siteAltName) {
		Utils.waitForScreenToLoad("search area / site", 30);
		((GuiSubitemTestObject) Utils.getObj("name", ".jTabbedPane")).click(atText("Alternatives"));
		sleep(5);
		ITestDataTable myTable;
		myTable = (ITestDataTable) siteAltListTable().getTestData("contents");
		for (int row = 0; row < myTable.getRowCount(); row++) {
			String temp = myTable.getCell(row, 0).toString();
			if (temp.equalsIgnoreCase(siteAltName)) {
				return myTable.getCell(row, 5).toString();
			}
		}
		return "Unable to find given Site Alternative";
	}

	public String updateSiteAltChosenFlag(String siteAltName, boolean value) {
		Utils.waitForScreenToLoad("search area / site", 30);
		((GuiSubitemTestObject) Utils.getObj("name", ".jTabbedPane")).click(atText("Alternatives"));
		sleep(5);
		ITestDataTable myTable;
		myTable = (ITestDataTable) siteAltListTable().getTestData("contents");
		for (int row = 0; row < myTable.getRowCount(); row++) {
			String temp = myTable.getCell(row, 0).toString();
			if (temp.equalsIgnoreCase(siteAltName)) {
				if (value){
					if (Boolean.parseBoolean(myTable.getCell(row, 5).toString())){
						return "Chosen flag already TRUE";
					}else {
						siteAltListTable().click(atCell(atRow(row),atColumn(5)));
						sleep(2);
						return saveSite();
					}
				}else{
					if (Boolean.parseBoolean(myTable.getCell(row, 5).toString())){
						siteAltListTable().click(atCell(atRow(row),atColumn(5)));
						sleep(2);
						return saveSite();
					}else {
						return "Chosen flag already FALSE";					
					}
				}
			}
		}
		return "Unable to find given Site Alternative";
	}

	public String updateSiteAltRankingValue(String siteAltName, String value) {
		Utils.waitForScreenToLoad("search area / site", 30);
		((GuiSubitemTestObject) Utils.getObj("name", ".jTabbedPane")).click(atText("Alternatives"));
		sleep(5);
		ITestDataTable myTable;
		myTable = (ITestDataTable) siteAltListTable().getTestData("contents");
		for (int row = 0; row < myTable.getRowCount(); row++) {
			String temp = myTable.getCell(row, 0).toString();
			if (temp.equalsIgnoreCase(siteAltName)) {
				siteAltListTable().click(atCell(atRow(row), atColumn(9)));
				sleep(2);
				siteAltListTable().doubleClick(atCell(atRow(row), atColumn(9)));
				sleep(2);
				mainFrame().inputKeys("{BACKSPACE}");
				sleep(2);
				mainFrame().inputKeys(value);
				sleep(2);
				siteAltListTable().click(atCell(atRow(row), atColumn(10)));
				return saveSite();
			}
		}
		return "Unable to find given Site Alternative";
	}

	public String openSiteAltFromSiteScreen(String siteAltName) {
		Utils.waitForScreenToLoad("search area / site", 30);
		((GuiSubitemTestObject) Utils.getObj("name", ".jTabbedPane")).click(atText("Alternatives"));
		sleep(5);
		ITestDataTable myTable = (ITestDataTable) siteAltListTable()
				.getTestData("contents");
		for (int row = 0; row < myTable.getRowCount(); row++) {
			String temp = myTable.getCell(row, 0).toString();
			if (temp.equalsIgnoreCase(siteAltName)) {
				siteAltListTable().click(atCell(atRow(row), atColumn(0)));
				sleep(2);
				Utils.getGuiTestObjByName(".jButtonEditAlternative").click();
				sleep(2);
				if (Utils.waitForScreenToLoad("Site Alternative", 60)) {
					return Const.SUCCESSMSG;
				} else {
					return Const.NOTSUCCESS;
				}
			}
		}
		return "Unable to find given Site Alternative";
	}

	public String getSiteAltScreenStatusLovList() {
		return SiteAltStatusComboBox().getProperty(".itemText").toString();
	}

	public String getSiteAltStatusLovList(String siteAltName) {
		Utils.waitForScreenToLoad("search area / site", 30);
		((GuiSubitemTestObject) Utils.getObj("name", ".jTabbedPane")).click(atText("Alternatives"));
		sleep(5);
		ITestDataTable myTable;
		myTable = (ITestDataTable) siteAltListTable().getTestData("contents");
		for (int row = 0; row < myTable.getRowCount(); row++) {
			String temp = myTable.getCell(row, 0).toString();
			if (temp.equalsIgnoreCase(siteAltName)) {
				siteAltListTable().click(atCell(atRow(row), atColumn(6)));
				sleep(1);
				return SiteAltListStatusComboBox().getProperty(".itemText").toString();
			}
		}
		return "Unable to find given Site Alternative";
	}

	public void closeEditSiteAltScreen() {
		sleep(2);
		Utils.getGuiTestObjByToolTip("Close [Alt+L]").click();
		sleep(2);
	}

	public String addRoom(String roomNo) {
		Utils.waitForScreenToLoad("search areas / sites select", 10);
		((GuiSubitemTestObject) Utils.getObj("name", ".jTabbedPane")).click(atText("Rooms"));
		sleep(5);
		newAltN().click();
		Utils.waitForScreenToLoad("Equipment Room", 10);
		roomNo().setText(roomNo);
		sleep(2);
		saveAltA().click();
		sleep(10);
		if (systemInformationPopup().exists()){
			String errorMessage = (String) _htmlNoDataRecordFoundHtml().getProperty("text");
			sleep(2);
			ok3().click();
			sleep(2);
			return Utils.removeHtml(errorMessage);
		}
		return Const.SUCCESSMSG;
	}

	public String createNewExternalService(HashMap<String, String> criteria) {
		sleep(2);
		pMenuBar().click(atPath("Sites"));
		sleep(1);
		pMenuBar().click(atPath("Sites->External Services"));
		sleep(2);
		Utils.waitForScreenToLoad("Select External Services", 30);
		Utils.getGuiTestObjByToolTip("New [Alt+N]").click();
		Utils.waitForScreenToLoad("Create External Service", 30);
		sleep(2);
		if (criteria.containsKey("ExtTypeName")) {
			NewMicrowaveSiteNameADetailButton().click();
			extTypeName().setText(criteria.get("ExtTypeName"));
			Utils.getGuiTestObjByToolTip("Select [Alt+S]").click();
//			selectAltS().click();
			sleep(5);
			if (Utils.getDialogTitles().contains("system information")) {
				yes3().click();
				sleep(2);
				Utils.getGuiTestObjByToolTip("Close [Alt+L]").click();
				return "ExtTypeName data not found";
			}
			if (Utils.waitForScreenToClose("Choose External Service Types", 10)) {
				GuiSubitemTestObject externalServicesListTable = (GuiSubitemTestObject) Utils
						.getObj(".class",
								"com.tmobile.itnetdev.fixedbuild.common.gui.components.table.ProTable");
				externalServicesListTable.doubleClick(atCell(atRow(atIndex(0)),
						atColumn("name")));
				Utils.waitForScreenToClose("Choose External Service Types", 10);
			}
			if (criteria.containsKey("Object")) {
				object().click();
				sleep(1);
				object().click(atText(criteria.get("Object")));
				sleep(5);
				if (criteria.containsKey("ObjectID")) {
					NewMicrowaveSiteNameBDetailButton().click();
					if (criteria.get("Object").equalsIgnoreCase("Project")) {
						Utils.waitForScreenToLoad("Projects Choice", 30);
						projectNo().setText(criteria.get("ObjectID"));
						Utils.getGuiTestObjByName(".jButtonSelect").click();
//						selectAltS2().click();
						Utils.waitForScreenToClose(Const.DURATION, 60);

						if (Utils.getDialogTitles().contains(
								"system information")) {
							yes3().click();
						}
						if (information().exists()) {
							yes2().click();
							Utils.waitForScreenToClose(Const.DURATION, 60);
							sleep(2);
						}
						if (Utils.getDialogTitles().contains(
								"system information")) {
							yes3().click();
							sleep(5);
						}
						if (Utils.getDialogTitles().contains("projects choice")) {
							myTable = (ITestDataTable) siteMSTFF_LindeggG01()
									.getTestData("contents");
							if (myTable.getRowCount() > 0) {
								siteMSTFF_LindeggG01().doubleClick(
										atCell(atRow(atIndex(0)),
												atColumn("tableObject")));
								Utils.waitForScreenToClose("projects choice", 30);
							} else {
								Utils.getGuiTestObjByToolTip("Close [Alt+L]")
										.click();
								return "project data not found..";
							}
							sleep(5);
						}
					} else {
						Utils.waitForScreenToLoad(
								"site alternatives / sites select", 30);
						alternativeName().setText(criteria.get("ObjectID"));
						selectAltS3().click();
						Utils.waitForScreenToClose(Const.DURATION, 60);
						if (jDialog().exists()) {
							yes3().click();
							sleep(2);
						}
						if (siteNameTableNewMicrowave().exists()) {
							myTable = (ITestDataTable) siteNameTableNewMicrowave()
									.getTestData("contents");

							if (myTable.getRowCount() > 0) {
								siteNameTableNewMicrowave().doubleClick(
										atCell(atRow(atIndex(0)),
												atColumn("headerLocName")));
							} else {
								Utils.getGuiTestObjByToolTip("Close [Alt+L]")
										.click();
								return "site alternatives data not found..";
							}
						}
					}

				} else {
					Utils.getGuiTestObjByToolTip("Close [Alt+L]").click();
					return "ExtTypeName mandatory parameter not defined..";
				}

			} else {
				Utils.getGuiTestObjByToolTip("Close [Alt+L]").click();
				return "ExtTypeName mandatory parameter not defined..";
			}

		} else {
			Utils.getGuiTestObjByToolTip("Close [Alt+L]").click();
			return "ExtTypeName mandatory parameter not defined..";
		}
		create2().click();
		if (Utils.waitForScreenToLoad("Edit External Service", 30)) {
			return Const.SUCCESSMSG;
		} else {
			return Const.NOTSUCCESS;
		}
	}

	public int getExtServiceID() {
		if (Utils.waitForScreenToLoad("Edit External Service", 10)) {
			return Integer.parseInt(externalServiceIDInternal().getText());
		}
		return 0;
	}

	public String addSiteAltToExternalService(String siteAltName) {

		if (Utils.waitForScreenToLoad("Edit External Service", 10)) {
			if (detailButton3().isEnabled()) {
				detailButton3().click();
				sleep(2);
				if (Utils.waitForScreenToLoad(
						"site alternatives / sites select", 30)) {
					alternativeName().setText(siteAltName);
					selectAltS3().click();
					Utils.waitForScreenToClose(Const.DURATION, 60);
					if (jDialog().exists()) {
						yes3().click();
						sleep(2);
					}
					if (siteNameTableNewMicrowave().exists()) {
						myTable = (ITestDataTable) siteNameTableNewMicrowave()
								.getTestData("contents");

						if (myTable.getRowCount() > 0) {
							siteNameTableNewMicrowave().doubleClick(
									atCell(atRow(atIndex(0)),
											atColumn("headerLocName")));
						} else {
							Utils.getGuiTestObjByToolTip("Close [Alt+L]").click();
							return "site alternatives data not found..";
						}
					}
				} else {
					String errorText = (String) _htmlNoDataRecordFoundHtml2()
							.getProperty("text");
					yes3().click();
					sleep(2);
					return Utils.removeHtml(errorText);
				}
				return Const.SUCCESSMSG;
			}else {
				return "Site Alternative can't be possibe: " + Const.NOTSUCCESS;
			}
		} else
			return Const.NOTSUCCESS;
	}

	public String updateExternalServiceAmout(String amount) {
		if (Utils.waitForScreenToLoad("Edit External Service", 10)) {
			amount().setText(amount);
			sleep(2);
			if (saveOnNewSiteButton().isEnabled()) {
				saveOnNewSiteButton().click();
				sleep(10);
				return Const.SUCCESSMSG;
			} else
				return "Unable to update Amount "+Const.NOTSUCCESS;
		} else {
			return Const.NOTSUCCESS;
		}
	}

	public int searchExternalService(HashMap<String, String> criteria) {
		if (!Utils.getDialogTitles().contains("select external services")) {
			sleep(2);
			pMenuBar().click(atPath("Sites"));
			sleep(1);
			pMenuBar().click(atPath("Sites->External Services"));
			sleep(2);
			Utils.waitForScreenToLoad("Select External Services", 30);
		}
		if (criteria.containsKey("ExternalServiceID")) {
			Utils.getTextGuiSubitemByName(".extServiceId").setText(
					criteria.get("ExternalServiceID"));
		}
		if (criteria.containsKey("SiteAltIndentCode")) {
			indentCode().setText(criteria.get("SiteAltIndentCode"));
		}
		if (criteria.containsKey("ProjectNo")) {
			Utils.getTextGuiSubitemByName(".projectNo").setText(
					criteria.get("ProjectNo"));
//			projectNo3().setText(criteria.get("ProjectNo"));
		}

		Utils.getGuiTestObjByToolTip("Select [Alt+S]").click();
		sleep(5);
		Utils.waitForScreenToClose(Const.DURATION, 60);
		sleep(5);
		if (systemInformation().exists()) {
			ok().click();
			sleep(2);
		}
		if (Utils.getDialogTitles().contains("edit external service")) {
			Utils.getGuiTestObjByName(".btAuswahl").click();
			sleep(10);
		}
		ITestDataTable myTable = (ITestDataTable) ExternalServiceTypeListTable()
				.getTestData("contents");
		return myTable.getRowCount();
	}

	/**
	 * 
	 * @param whichColumn
	 *            /extserviceId, extTypeName, serviceType, SapInterface,
	 *            accountNumber, description, projectNo, extendedIndentCode,
	 *            indentName, parameter, value, orderDate, orderStatus,
	 *            dateCreated, deliveryDate, amount, totalCost
	 * @return
	 */
	public ArrayList<String> getExternalServiceListValues(String whichColumn) {
		sleep(2);
		myTable = (ITestDataTable) ExternalServiceTypeListTable().getTestData(
				"contents");
		ArrayList<String> neListArray = new ArrayList<>();
		int whichHeader = 0;
		try {
			whichHeader = Integer.parseInt(whichColumn);
		} catch (Exception ex) {
			for (int i = 0; i < myTable.getColumnCount(); i++) {
				String columnHeader = (String) myTable.getColumnHeader(i);
				if (columnHeader.equalsIgnoreCase(whichColumn)) {
					whichHeader = i;
					break;
				}
			}
		}
		for (int row = 0; row < myTable.getRowCount(); row++) {
			neListArray.add((String) myTable.getCell(row, whichHeader));
		}
		return neListArray;
	}

	public String updateExternalServiceOrdStatus(String orderStatus) {
		if (Utils.waitForScreenToLoad("Edit External Service", 10)) {
			sleep(2);
			orderStatus2().click();
			sleep(2);
			orderStatus2().click(atText(orderStatus));
			sleep(2);
			saveOnNewSiteButton().click();
			sleep(10);
			if (saveOnNewSiteButton().isEnabled()) {
				return Const.NOTSUCCESS;
			}
			return Const.SUCCESSMSG;
		} else {
			return Const.NOTSUCCESS;
		}
	}

	public String[][] externalServiceInitiateOrdering(String fileName, String screenName) {
		sleep(2);
		if (screenName.equalsIgnoreCase("ExternalServiceListScreen")) {
			ITestDataTable myTable = (ITestDataTable) ExternalServiceTypeListTable()
					.getTestData("contents");
			ExternalServiceTypeListTable().click(
					SHIFT_LEFT,
					atCell(atRow(atIndex(myTable.getRowCount()-1)),
							atColumn("extserviceId")));
			sleep(2);
			initiateOrdering().click();
		} else {
			order().click();
		}
		sleep(10);
		if (systemInformation().exists()) {
			String noHWPurchasesAreAssigned = (String) _htmlNoDataRecordFoundHtml2()
					.getProperty("text");
			sleep(1);
			ok().click();
			sleep(1);
			System.out.println(noHWPurchasesAreAssigned);
			String retArray[][] = new String[][] { { Utils
					.removeHtml(noHWPurchasesAreAssigned) } };
			return retArray;
		}
		if (Utils.waitForScreenToLoad("Initiate Ordering", 60)) {
			if (information3().exists() && proceed().exists()) {
				information3().click();
				sleep(5);
				externalServiceInitiateOrdering = (String) pTextArea().getProperty("text");
				ok4().click();
				sleep(2);
				proceed().click();
				sleep(5);
			}
			GuiTestObject executeBTN = Utils.getGuiTestObjByToolTip("Execute [Alt+E]");
			if (executeBTN != null) {
				execute().click();
				sleep(10);
				if (Utils.getDialogTitles().contains("check results")) {
					displayCSVData().click();
					for (int i = 0; i < 6 && !hwPurchaseCSVData().exists(); i++) {
						sleep(10);
					}
					if (hwPurchaseCSVData().exists()) {
						ITestDataTable myTable;
						myTable = (ITestDataTable) MicrowaveCSVDataTable()
								.getTestData("contents");
						String csvData[][] = new String[myTable.getRowCount()][myTable
								.getColumnCount()];
						for (int row = 0; row < myTable.getRowCount(); row++) {
							for (int col = 0; col < myTable.getColumnCount(); col++) {
								try {
									csvData[row][col] = (String) myTable
											.getCell(row, col);
								} catch (Exception e) {
									csvData[row][col] = "";
								}
							}
						}
						sleep(2);
						// Saving Data
						Utils.getGuiTestObjByToolTip("Ok [Alt+O]").click();
						sleep(10);
						Clipboard clipboard = Toolkit.getDefaultToolkit()
								.getSystemClipboard();
						clipboard.setContents(new StringSelection(fileName),
								null);

						// Window: java.exe: Export Microwaves
						exportHWPurchaseswindow().click(CAPTION);
						sleep(5);
						comboBoxcomboBox2().click(atPoint(29, 11));
						sleep(5);
						comboBoxcomboBox2().click(RIGHT, atPoint(122, 10));
						sleep(2);
						contextMenu().click(atPath("Paste"));
						sleep(2);
						savebutton().click();
						sleep(10);
						return csvData;
					} else {
						Verify.failDueToUnexpectedError("CSVData view dialog is not opened");
						return null;
					}
				}
			}
		}
		if (information2().exists()) {
			information2().click();
			sleep(5);
			String errorText = (String) pTextArea().getProperty("text");
			ok4().click();
			sleep(2);
			ok5().click();
			sleep(2);
			Verify.failDueToUnexpectedError(errorText);
			String retArray[][] = new String[][] { { errorText } };
			return retArray;
		}
		return null;
	}

	public HashMap<String, String> getExternalServiceAttributes() {
		sleep(2);
		HashMap<String, String> attributes = new HashMap<String, String>();

		attributes.put("ServiceType", serviceType(ANY, DISABLED).getText());
		attributes.put("ExtTypeName", extTypeName2().getText());
		attributes.put("AccountNo", accountNo().getText());
		attributes.put("SapInterface", sapInterface().getText());
		attributes.put("Description", description2().getText());
		attributes.put("ExtServiceIDInternal", externalServiceIDInternal()
				.getText());
		attributes.put("ProjectNo", projectNo2().getText());
		attributes.put("ProjectIndentCode", indentCode2().getText());
		attributes.put("ProjectSiteName", siteName().getText());
		attributes.put("SiteAltIndentCode", indentCode3().getText());
		attributes.put("SiteAltSiteName", siteName4().getText());
		return attributes;
	}
}
