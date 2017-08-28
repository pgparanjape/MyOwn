package PB_2015_2.FB.Actions;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;

import PB_2015_2.Common.Const;
import PB_2015_2.Common.Utils;
import PB_2015_2.FB.ObjRepo.FBRepo;
import PB_2015_2.Verification.Verify;

import com.rational.test.ft.object.interfaces.GuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.GuiTestObject;
import com.rational.test.ft.object.interfaces.TextGuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.TextSelectGuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.ToggleGUITestObject;
import com.rational.test.ft.vp.ITestData;
import com.rational.test.ft.vp.ITestDataTable;

public class UDSVActions extends FBRepo implements FB{
	
	public String search(HashMap<String, String> searchCriteria) {
		if (Utils.waitForScreenToLoad("UDSV", 10)) {
			Utils.closeAll();
		}
		pMenuBar().click(atPath("VBZ"));
		sleep(1);
		pMenuBar().click(atPath("VBZ->Edit  UDSV"));
		Utils.waitForScreenToLoad("UDSV", 10);
		if (searchCriteria.get("Region_A") != null) {
			TextSelectGuiSubitemTestObject regionAComboBox = (TextSelectGuiSubitemTestObject) Utils.getObj("name", ".cmbNiederlassungA");
			regionAComboBox.click();
			sleep(1);
			regionAComboBox.click(atText(searchCriteria.get("Region_A")));
			sleep(1);
		}
		if (searchCriteria.get("Region_B") != null) {
			TextSelectGuiSubitemTestObject regionBComboBox = (TextSelectGuiSubitemTestObject) Utils.getObj("name", ".cmbNiederlassungB");
			regionBComboBox.click();
			sleep(1);
			regionBComboBox.click(atText(searchCriteria.get("Region_B")));
			sleep(1);
		}
		if (searchCriteria.get("IndentCode_A") != null) {
			getTextFieldByName("ProDBTextField", ".edStoKngA" ).setText(searchCriteria.get("IndentCode_A"));
			sleep(1);
		}
		if (searchCriteria.get("IndentCode_B") != null) {
			((TextGuiSubitemTestObject) Utils.getObj("name", ".edStoKngB")).setText(searchCriteria.get("IndentCode_B"));
			sleep(1);
		}
		if (searchCriteria.get("SiteName_A") != null) {
			getTextFieldByName("ProDBTextField", ".edSTNameNEA" ).setText(searchCriteria.get("SiteName_A"));
			sleep(1);
		}
		if (searchCriteria.get("SiteName_B") != null) {
			((TextGuiSubitemTestObject) Utils.getObj("name", ".edSTNameNEB")).setText(searchCriteria.get("SiteName_B"));
			sleep(1);
		}
		if (searchCriteria.get("NEType_A") != null) {
			Utils.getListBoxByName(".cmbBTypNEA" ).click();
			sleep(1);
			Utils.getListBoxByName(".cmbBTypNEA" ).click(atText(searchCriteria.get("NEType_A")));
			sleep(1);
		}
		if (searchCriteria.get("NEType_B") != null) {
			Utils.getListBoxByName(".cmbBTypNEB" ).click();
			sleep(1);
			Utils.getListBoxByName(".cmbBTypNEB" ).click(atText(searchCriteria.get("NEType_B")));
			sleep(1);
		}
		if (searchCriteria.get("NE_B") != null) {
			getTextFieldByName("ProDBTextField", ".edNE_IDNEB" ).setText(searchCriteria.get("NE_B"));
			sleep(1);
		}
		if (searchCriteria.get("NE_A") != null) {
			getTextFieldByName("ProDBTextField", ".edNE_IDNEA" ).setText(searchCriteria.get("NE_A"));
			sleep(1);
		}
		if (searchCriteria.get("AGName_A") != null) {
			getTextFieldByName("ProDBTextField", ".edBGNameA" ).setText(searchCriteria.get("AGName_A"));
			sleep(1);
		}
		if (searchCriteria.get("AGName_B") != null) {
			getTextFieldByName("ProDBTextField", ".edBGNameB" ).setText(searchCriteria.get("AGName_B"));
			sleep(1);
		}
		if (searchCriteria.get("Address1_A") != null) {
			getTextFieldByName("ProDBTextField", ".edAdresse1A" ).setText(searchCriteria.get("Address1_A"));
			sleep(1);
		}
		if (searchCriteria.get("Address1_B") != null) {
			getTextFieldByName("ProDBTextField", ".edAdresse1B" ).setText(searchCriteria.get("Address1_B"));
			sleep(1);
		}
		TextGuiSubitemTestObject dateField = (TextGuiSubitemTestObject)Utils.getObj(
				".class",
				"com.tmobile.itnetdev.fixedbuild.common.gui.components.ProDateField",
				".priorLabel", "Date:");
		if (searchCriteria.get("date") != null) {
			dateField.setText(searchCriteria.get("date"));
			sleep(1);
		}else{
			dateField.setText("");
			sleep(1);
		}
		if (searchCriteria.get("comment") != null) {
			getTextFieldByName("ProDBTextField", ".edBemerkung" ).setText(searchCriteria.get("comment"));
			sleep(1);
		}if (searchCriteria.get("description") != null) {
			getTextFieldByName("ProDBTextField", ".edBezeichnung" ).setText(searchCriteria.get("description"));
			sleep(1);
		}if (searchCriteria.get("capacity") != null && Utils.setAppVersion().contains("2015.2")) {
			TextSelectGuiSubitemTestObject capacityListBox = (TextSelectGuiSubitemTestObject) Utils
					.getObj("name", ".cmbKapazitaet");
			if (capacityListBox != null){
			capacityListBox.setText(searchCriteria.get("capacity"));
			sleep(1);
			_10GBitS().click(atCell(atRow("headerValues",searchCriteria.get("capacity") ), atColumn("headerValues")));
			}
		}
		if (searchCriteria.get("udsvID") != null) {
			try {
				getTextFieldByName("ProDBTextField", ".edUdsvID" ).setText(searchCriteria.get("udsvID"));
				sleep(1);
			} catch (Exception e) {}
		}
		clickSearchButton();
		sleep(15);
		Utils.waitForScreenToClose("Dauer", 300);
		if (SysInfoDialog().exists()){
			((GuiTestObject) Utils.getObj("accessibleContext.accessibleName",
					"OK")).click();
			sleep(2);
//			return "No Data Found";
		}else if (enterConsiderationDatePlanning().exists()){
			Utils.getGuiTestObjByName(".btAbbrechen").click();
			sleep(2);
		}
//		ITestDataTable myTable = (ITestDataTable) getListTable(".tblUDSV").getTestData("contents");
		ITestDataTable myTable = (ITestDataTable) Utils.getGuiTestObjByName(".tblUDSV").getTestData("contents");
		if (myTable.getRowCount() >=1){
			return Const.SUCCESSMSG;
		}else{
			return Const.NOTSUCCESS;
		}
	}

	public ArrayList<String> getUDSVSearchListValues(String whichColumn) {
		Utils.waitForScreenToLoad("UDSV", 10);
		GuiTestObject seachListTable = (GuiTestObject) Utils.getObj("name",".tblUDSV");
		ITestDataTable myTable = (ITestDataTable) seachListTable.getTestData("contents");
		ArrayList<String> siteListArray = new ArrayList<>();
		int whichHeader = 0;
		try {
			whichHeader = Integer.parseInt(whichColumn);
		} catch (Exception ex) {

			for (int i = 0; i < myTable.getColumnCount(); i++) {
				String columnHeader = (String) myTable.getColumnHeader(i);
				System.out.println(columnHeader);
				if (columnHeader.equalsIgnoreCase(whichColumn)) {
					whichHeader = i;
				}
			}
		}

		for (int row = 0; row < myTable.getRowCount(); row++) {
			siteListArray.add((String) myTable.getCell(row, whichHeader));
		}
		return siteListArray;
	}
	
	public void openFromSearchList(int whichRow) {
		Utils.waitForScreenToLoad("UDSV", 10);
		GuiSubitemTestObject udsvListTable = (GuiSubitemTestObject) Utils.getGuiTestObjByName(".tblUDSV");
		udsvListTable.click(atCell(atRow(atIndex(whichRow)), atColumn(atIndex(0))));
		sleep(1);
		Utils.getGuiTestObjByName(".btBearbeiten").click();
		sleep(5);
		if (Utils.waitForScreenToLoad(
				"Enter Consideration Date / Planning Date:", 60)) {
			Utils.getGuiTestObjByName(".btOK").click();
			sleep(1);
		}
		Utils.waitForScreenToLoad("Maintain UDSV", 60);
	}
	
	public String openFromSearchList(String searchCriteria) {
		sleep(2);
		GuiSubitemTestObject udsvListTable = (GuiSubitemTestObject) Utils.getGuiTestObjByName(".tblUDSV");
		ITestDataTable myTable = (ITestDataTable) udsvListTable.getTestData("contents");
		if (myTable.getRowCount() == 0){
			sleep(50);
		}
		all: for (int row = 0; row < myTable.getRowCount(); row++) {
			for (int col = 1; col < myTable.getColumnCount(); col++) {
				try {
					String temp = (String) myTable.getCell(row, col);
					if (temp != null && temp.equalsIgnoreCase(searchCriteria)){
						udsvListTable.click(atCell(atRow(atIndex(row)), atColumn(atIndex(0))),atPoint(43, 4));
						sleep(2);
						Utils.getGuiTestObjByName(".btBearbeiten").click();
						sleep(15);
						Utils.waitForScreenToLoad("Create/Edit UDSV", 60);
						configAssemblyGroupsDialog().click(atPoint(548,17));
						break all;
					}
				} catch (Exception e) {	}
			}
		}
		return Const.SUCCESSMSG;
	}

	public String createNew(HashMap<String, String> createCriteria) {
		if (Utils.waitForScreenToLoad("UDSV", 20)){
			closeAll();
		}
		sleep(2);
		pMenuBar().click(atPath("VBZ"));
		sleep(1);
		pMenuBar().click(atPath("VBZ->Edit  UDSV"));
		Utils.waitForScreenToLoad("UDSV", 20);
		if (createCriteria.get("IndentCode_A") != null) {
			getTextFieldByName("ProDBTextField", ".edStoKngA" ).setText(createCriteria.get("IndentCode_A"));
			sleep(1);
		}
		if (createCriteria.get("IndentCode_B") != null) {
			getTextFieldByName("ProDBTextField", ".edStoKngB" ).setText(createCriteria.get("IndentCode_B"));
			sleep(1);
		}
		if (createCriteria.get("NEType_B") != null) {
			Utils.getListBoxByName(".cmbBTypNEB" ).click();
			sleep(1);
			Utils.getListBoxByName(".cmbBTypNEB" ).click(atText(createCriteria.get("NEType_B")));
			sleep(1);
		}
		if (createCriteria.get("NEType_A") != null) {
			Utils.getListBoxByName(".cmbBTypNEA" ).click();
			sleep(1);
			Utils.getListBoxByName(".cmbBTypNEA" ).click(atText(createCriteria.get("NEType_A")));
			sleep(1);
		}
		if (createCriteria.get("NE_A") != null) {
			getTextFieldByName("ProDBTextField", ".edNE_IDNEA" ).setText(createCriteria.get("NE_A"));
			sleep(1);
		}
		TextGuiSubitemTestObject dateField = (TextGuiSubitemTestObject) Utils
				.getObj(".class",
						"com.tmobile.itnetdev.fixedbuild.common.gui.components.ProDateField",
						".priorLabel", "Date:");
		dateField.setText("");
		sleep(2);
		clickNewButton();
		sleep(10);
		if(information().exists()){
			sleep(2);
			yes2().click();
			sleep(2);
		}
		Utils.waitForScreenToClose("Dauer", 300);
		sleep(2);
		if (SysInfoDialog().exists()){
			SysInfoDialog().click(atPoint(196,17));
			sleep(1);
			SysInfoDialogOk().click();
		}
		Utils.waitForScreenToLoad("Choose to create UDSV", 20);
		if (createCriteria.get("ChooseToCreateNE_A") != null) {
			GuiSubitemTestObject table = (GuiSubitemTestObject) Utils.getGuiTestObjByName(".table1");
			if (createCriteria.get("ChooseToCreateNE_A").matches("[0-9]*")){
				table.click(atCell(atRow(atIndex(Integer.parseInt(createCriteria.get("ChooseToCreateNE_A")))), 
						atColumn("location.location")), atPoint(36,8));
			}else{
				table.click(atCell(atRow("neId",createCriteria.get("ChooseToCreateNE_A")),atColumn("configuration")), atPoint(36,8));
			}
			sleep(1);
		}
		if (createCriteria.get("ChooseToCreateNE_B") != null) {
			GuiSubitemTestObject table = (GuiSubitemTestObject) Utils.getGuiTestObjByName(".table2");
			if (createCriteria.get("ChooseToCreateNE_B").matches("[0-9]*")){
				table.click(atCell(atRow(atIndex(Integer.parseInt(createCriteria.get("ChooseToCreateNE_B")))), 
						atColumn("location.location")), atPoint(36,8));
			}else{
				table.click(atCell(atRow("neID",createCriteria.get("ChooseToCreateNE_B")),atColumn("configuration")), atPoint(36,8));
			}
			sleep(1);
		}
		sleep(1);
		((GuiTestObject)Utils.getObj("name", ".btErstellen")).click();
		Utils.waitForScreenToLoad("Create/Edit UDSV", 60);
		description().waitForExistence();
		if (createCriteria.containsKey("comment"))
			getTextFieldByName("ProTextArea", ".edBemerkung").setText(createCriteria.get("comment"));
		if (createCriteria.containsKey("description"))
			description().setText(createCriteria.get("description"));
		if (createCriteria.containsKey("udsvType")) {
			TextSelectGuiSubitemTestObject udsvType = ((TextSelectGuiSubitemTestObject)Utils.getObj("name",".cmbUdsvType"));
			udsvType.click();
			sleep(1);
//			Ethernet.IP
			udsvType.click(atText(createCriteria.get("udsvType")));
			sleep(2);
		}
		if (createCriteria.containsKey("capacity")){
			capacity().click();
			sleep(1);
//			"10 MBit/s"
			_10MBitS().click(atCell(atRow("headerValues", createCriteria.get("capacity")),atColumn("headerValues")),atPoint(23,2));
			sleep(2);
		}
		sleep(5);
		if (createCriteria.containsKey("Attach_NE_A"))
			attach("NE_A",createCriteria.get("Attach_NE_A") );
		if (createCriteria.containsKey("Attach_NE_B"))
			attach("NE_B",createCriteria.get("Attach_NE_B") );
		clickSaveButton();
		sleep(20);
		if (getSaveButtonStatus()) {
			sleep(60);
			if (getSaveButtonStatus())
				return Const.NOTSUCCESS;
			else
				return Const.SUCCESSMSG;
		}
		return Const.SUCCESSMSG;
	}
	
/**
 * provides the Tree Lable Valus for NE 
 * @return
 */
	public String[] getComponentTreeLabelValues(String whichNE) {
		
		ITestDataTable myTable;
		GuiSubitemTestObject whichTree;
		if (whichNE.equalsIgnoreCase("NE_A")){
			whichTree = rootPrivate();
		}else{
			whichTree = _treeNE2();
		}
		sleep(2);
		configAssemblyGroupsDialog().click(atPoint(529, 10));
		sleep(1);
		
		Enumeration <?> myenu = whichTree.getTestDataTypes().keys();
		myTable = (ITestDataTable) whichTree.getTestData("contents");
		String[] retArray = new String[myTable.getRowCount()];
		while (myenu.hasMoreElements()) {
			String string = (String) myenu.nextElement();
			ITestData itestData = whichTree.getTestData(string);			
			if (itestData instanceof ITestDataTable) {
				ITestDataTable iTableData = (ITestDataTable) whichTree.getTestData(string);
				for (int row = 0; row < iTableData.getRowCount(); row++) {
					try{
						retArray[row] = (String) iTableData.getCell(row, 1);
					}catch (Exception e){}
				}
			}
		}
		return retArray;
		
	}

	public void attach(String whichNE, String whichComponent) {
		sleep(2);
		configAssemblyGroupsDialog().click(atPoint(586,15));
		sleep(1);
		GuiSubitemTestObject whichTree;
		GuiTestObject detachButton;
		if (whichNE.equalsIgnoreCase("NE_A")) {
			whichTree = rootPrivate();
			assemblies().click(atText("Assemblies"));
			detachButton = detachUDSV_NEA();
		}else{
			whichTree = _treeNE2();
			assemblies2().click(atText("Assemblies"));
			detachButton = detachUDSV_NEB();
		}
		ITestDataTable myTable = (ITestDataTable) whichTree.getTestData("contents");
		if (!whichComponent.isEmpty()){
			String[] components = whichComponent.split(":");
			for (int i = 0; i < components.length; i++) {
				for (int row = 0; row < myTable.getRowCount(); row++) {
					try {
						String temp = (String)myTable.getCell(row, 0);
						if (temp.equalsIgnoreCase(components[i])){
							whichTree.doubleClick(atCell(atRow(atIndex(row)), atColumn("Name")),atPoint(59, 8));
							sleep(60);
							if (systemInformationPopup().exists()){
								sleep(2);
								ok3().click();
								sleep(2);
							}
							myTable = (ITestDataTable) whichTree.getTestData("contents");
							break;
						}
					} catch (Exception e) {}
				}
			}
		}else{
			for (int row = 1; row < myTable.getRowCount(); row++) {
				try {
					whichTree.doubleClick(atCell(atRow(atIndex(row)), atColumn("Name")),atPoint(59, 8));
					sleep(60);
					if (systemInformationPopup().exists()){
						ok3().click();
						sleep(5);
					}
					if (detachButton.isEnabled()){
						break;
					}
					myTable = (ITestDataTable) whichTree.getTestData("contents");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		if (!detachButton.isEnabled()){
			Verify.failDueToUnexpectedError(whichNE+ " UDSV attachment is failed.");
		}
//		Assembly Group successfully assigned!
//		assemblyGroupSuccessfullyAssig().performTest(AssemblyGroupSuccessfullyAssig_standardVP());
	}

	public HashMap <String, String>  getAllAttributesEditUDSVDetailsScrren() {
		sleep(2);
		HashMap<String, String> attributes = new HashMap<String, String>();
		configAssemblyGroupsDialog().click(atPoint(548,17));
		
//		UDSV attributes
		attributes.put("udsvType", ((TextSelectGuiSubitemTestObject) Utils
				.getObj("name", ".cmbUdsvType")).getSelectedText());
		attributes.put("CIC",getTextFieldByName("ProDBTextField", ".edCIC").getText());
		attributes.put("capacity",capacity().getSelectedText());
		attributes.put("start",getTextFieldByName("ProDateField", ".edBegin").getText());
		attributes.put("end",getTextFieldByName("ProDateField", ".edEnd").getText());
		attributes.put("editor",((TextGuiSubitemTestObject)Utils.getObj("name",".edBearbeiter")).getText());
		try {
			attributes.put("responsibility",
					((TextSelectGuiSubitemTestObject) Utils.getObj("name",
							".cmbZust")).getSelectedText());	
		} catch (Exception e) {
			attributes.put("responsibility","None");
		}
		attributes.put("description",getTextFieldByName("ProDBTextField", ".edBezeichnung").getText());
		attributes.put("comment",getTextFieldByName("ProTextArea", ".edBemerkung").getText());
		
//		Order Data Tab Attributes
		udsvParam().click(atText("Order Data"));
		sleep(2);
		attributes.put("OrderDataProviderID", ((TextGuiSubitemTestObject) Utils
				.getObj("name", ".edProviderId")).getText());
		attributes.put("OrderDataDsvcfvid",((TextGuiSubitemTestObject)Utils.getObj("name",".edCfvId")).getText());
		try {
			attributes.put("OrderDataProvider",
					((TextSelectGuiSubitemTestObject) Utils.getObj("name",
							".cmbProvider")).getSelectedText());
			attributes.put("OrderDataContractType",contractType().getSelectedText());
		} catch (Exception e) {
			attributes.put("OrderDataProvider","");
			attributes.put("OrderDataContractType","");
		}
		
//		UDSV Ref. Tab Attributes
		udsvParam().click(atText("UDSV Ref."));
		sleep(2);
		try {
			GuiTestObject myObj = (GuiTestObject) Utils
					.getObj(".class",
							"com.tmobile.itnetdev.fixedbuild.common.gui.components.treetable.ProTreeTable",
							"name", ".tree");
			ITestDataTable table = (ITestDataTable) myObj
					.getTestData("contents");
			String udsvRef = Integer.toString(table.getRowCount());
			attributes.put("UDSVRef", udsvRef);
		} catch (Exception e) {
			attributes.put("UDSVRef", "");
		}
		sleep(2);
		
//		Lines/Microwaves Tab Attributes
		udsvParam().click(atText("Lines/Microwaves"));
		sleep(2);
		try {
			attributes.put("LinesMicrowaves",Integer.toString(((ITestDataTable) Utils.getGuiTestObjByName(".tbl").getTestData("contents")).getRowCount()));
		} catch (Exception e) {
			attributes.put("LinesMicrowaves","");
		}
		sleep(2);
		udsvParam().click(atText("Order Data"));
		sleep(2);

//		NE_A attributes
		attributes.put("NEType_A",getTextFieldByName("ProDBTextField", ".edNEBTypA").getText());
		attributes.put("IndentCode_A",getTextFieldByName("ProTextField", ".edStoKngA").getText());
		attributes.put("NodeID_A",getTextFieldByName("ProDBTextField", ".edNodeIdA").getText());
		attributes.put("AssemblyGrp_A",((TextGuiSubitemTestObject) Utils.getObj("name",".edBgrpA")).getText());
		assemblies().click(atText("UDSVs"));
		sleep(2);
		attributes.put("UDSVCount_A",Integer.toString(((ITestDataTable) udsvNEATable().getTestData("contents")).getRowCount()));
		sleep(2);

//		NE_B attributes
		attributes.put("NEType_B",getTextFieldByName("ProDBTextField", ".edNEBTypB").getText());
		attributes.put("IndentCode_B",getTextFieldByName("ProTextField", ".edStoKngB").getText());
		attributes.put("NodeID_B",getTextFieldByName("ProDBTextField", ".edNodeIdB").getText());
		attributes.put("AssemblyGrp_B",((TextGuiSubitemTestObject)Utils.getObj("name",".edBgrpB")).getText());
//		attributes.put("AssemblyGrp_B",assemblyGroup2().getText());
		sleep(2);
		assemblies2().click(atText("UDSVs"));
		attributes.put("UDSVCount_B",Integer.toString(((ITestDataTable) udsvNEBTable().getTestData("contents")).getRowCount()));
		sleep(2);
		return attributes;
	}
	
	public void addBookMark(String bookMarkName, String pubOrPrivate) {
		sleep(2);
		((GuiTestObject) Utils.getObj("name", ".btBookmark")).click();
		Utils.waitForScreenToLoad("Save Bookmark", 10);
		((TextGuiSubitemTestObject) Utils.getObj("name", ".nameTextField")).setText(bookMarkName);
		sleep(1);
		if (pubOrPrivate.equalsIgnoreCase("public")) {
			((ToggleGUITestObject) Utils
					.getObj(".class",
							"com.tmobile.itnetdev.fixedbuild.common.gui.components.ProCheckBox"))
					.clickToState(SELECTED);
			sleep(1);
		}
		Utils.getGuiTestObjByName(".saveButton.").click();
		sleep(5);
	}

	public String addNewLine(String lineDescription) {
		
		Utils.waitForScreenToLoad("Create/Edit UDSV", 10);
		udsvParam().click(atText("Lines/Microwaves"));
		sleep(2);
		int initialRowCount = ((ITestDataTable) Utils.getGuiTestObjByName(".tbl").getTestData("contents")).getRowCount();
		Utils.getGuiTestObjByName(".proButtonLeitungAuswahl").click();
		Utils.waitForScreenToLoad("Select Lilnes", 10);
		((TextGuiSubitemTestObject) Utils.getObj("name",".edBezeichnung")).setText(lineDescription);
		sleep(1);
		((TextGuiSubitemTestObject) Utils.getObj("name",".edDate")).setText("");
		sleep(1);
		Utils.getGuiTestObjByToolTip("Select [Alt+S]").click();
		sleep(5);
		if (!Utils.waitForScreenToLoad("Edit Lines", 20)){
			GuiSubitemTestObject listTable = (GuiSubitemTestObject) Utils.getObj(".class","com.tmobile.itnetdev.fixedbuild.common.gui.components.table.ProTable");
			ITestDataTable myTable = (ITestDataTable) listTable.getTestData("contents");
			if (myTable.getRowCount() == 0) {
				Verify.failDueToUnexpectedError("Provided Line is not in the search List");
				return "Provided Line is not in the search List";
			}

			for (int row = 0; row < myTable.getRowCount(); row++) {
				try {
					String temp = (String) myTable.getCell(row, 2);
					if (temp.equalsIgnoreCase(lineDescription)) {
						listTable.click(atCell(atRow(atIndex(row)), atColumn(atIndex(0))));
						Utils.getGuiTestObjByToolTip("Select [Alt + S]").click();
						break;
					}
				} catch (Exception e) {}
			}
			if (!Utils.waitForScreenToLoad("Edit Lines", 5) && lineDescription == ""){
				listTable.click(atCell(atRow(atIndex(0)), atColumn(atIndex(0))));
				Utils.getGuiTestObjByToolTip("Select [Alt + S]").click();
			}
			sleep(5);
			Utils.waitForScreenToLoad("Edit Lines", 20);
		}
		clickChooseButton();
		sleep(5);
		int newRowCount = ((ITestDataTable) Utils.getGuiTestObjByName(".tbl").getTestData("contents")).getRowCount();
		if (newRowCount == initialRowCount + 1) {
			return Const.SUCCESSMSG;
		} else {
			return Const.NOTSUCCESS;
		}
	}

	public ArrayList<String> deleteUsingDeleteBTN() {
		sleep(2);
		ArrayList<String> returnArrayList = new ArrayList<String>();
		Utils.getGuiTestObjByToolTip("Delete [Alt+D]").click();
		Utils.waitForScreenToLoad("Perform action", 10);
		((GuiTestObject) Utils
				.getObj("accessibleContext.accessibleName", "Yes")).click();
		sleep(10);
		if (conflictsAndWarningsWhenMoving().exists()) {
			returnArrayList.add("Conflicts and Warnings when moving UDSVs");
			ITestDataTable myTable = (ITestDataTable) udsvLineInfoTable().getTestData("contents");
			for (int row = 0; row < myTable.getRowCount(); row++) {
				returnArrayList.add(myTable.getCell(row, 3).toString());
			}
			Utils.getGuiTestObjByToolTip("OK [Alt+K]").click();
		}
		sleep(2);
		if (SysInfoDialog().exists()) {
			SysInfoDialogOk().click();
			sleep(2);
		}
		return returnArrayList;
	}

	public boolean verifyAddress1(String address1Value, String condition, String whichNE) {
		ArrayList<String> address1ListArray = new ArrayList<>();
		int counter = 0;
		Utils.waitForScreenToLoad("UDSV", 10);
		((GuiSubitemTestObject) Utils.getGuiTestObjByName(".tblUDSV")).click(atCell(
				atRow(atIndex(0)), atColumn(atIndex(0))));
		Utils.getGuiTestObjByName(".btBearbeiten").click();
		boolean repeat = false;
		boolean retFlag = false;
		int repeatCounter = 0;
		do {
			sleep(10);
			Utils.waitForElement(enterConsiderationDatePlanning(), 60);
			if (enterConsiderationDatePlanning().exists()) {
				Utils.getGuiTestObjByName(".btOK").click();
				sleep(1);
			}
			Utils.waitForScreenToLoad("Maintain UDSV", 15);
			GuiSubitemTestObject udsvListTable = (GuiSubitemTestObject) Utils.getGuiTestObjByName(".tblUDSV");
			ITestDataTable myTable = (ITestDataTable) udsvListTable.getTestData("contents");
			int column = 2;
			if (whichNE.equalsIgnoreCase("NEB")){
				column = 4;
			}
			for (int row = 0; row < myTable.getRowCount(); row++) {
				address1ListArray.add((String) myTable.getCell(row, column));
			}
			Iterator<String> itr = address1ListArray.iterator();
			while (itr.hasNext()) {
				try {
					String temp = itr.next().toString();
					if (condition.equalsIgnoreCase(Const.EXACTMATCH)) {
						if (temp.equals(address1Value)) {
							counter++;
						}
					} else if (condition.equalsIgnoreCase(Const.REGX)) {
						if (temp.toLowerCase().matches(
								address1Value.toLowerCase())) {
							counter++;
						}
					}
				} catch (Exception e) {}
			}
			
			if (counter >= 1) {
				retFlag = true;
				address1ListArray.clear();
			} else {
				retFlag = false;
				break;
			}
			if (nextRecord().isEnabled()){
				nextRecord().click();
				repeat = true;
			}else{
				repeat = false;
			}
			repeatCounter++;
		} while (repeat && repeatCounter <10);
		return retFlag;
	}
	
	public boolean verifyAddress1Both(String address1ValueNEA, String address1ValueNEB, String condition) {
		ArrayList<String> address1ListArrayNEA = new ArrayList<>();
		ArrayList<String> address1ListArrayNEB = new ArrayList<>();
		int counter = 0;
		Utils.waitForScreenToLoad("UDSV", 10);
		((GuiSubitemTestObject) Utils.getGuiTestObjByName(".tblUDSV")).click(atCell(
				atRow(atIndex(0)), atColumn(atIndex(0))));
		Utils.getGuiTestObjByName(".btBearbeiten").click();
		boolean repeat = false;
		boolean retFlag = false;
		int repeatCounter = 0;
		do {
			sleep(10);
			Utils.waitForElement(enterConsiderationDatePlanning(), 60);
			if (enterConsiderationDatePlanning().exists()) {
				Utils.getGuiTestObjByName(".btOK").click();
				sleep(1);
			}
			Utils.waitForScreenToLoad("Maintain UDSV", 15);
			GuiSubitemTestObject udsvListTable = (GuiSubitemTestObject) Utils.getGuiTestObjByName(".tblUDSV");
			ITestDataTable myTable = (ITestDataTable) udsvListTable.getTestData("contents");
			int columnNEA = 2;
			int	columnNEB = 4;
			for (int row = 0; row < myTable.getRowCount(); row++) {
				address1ListArrayNEA.add((String) myTable.getCell(row, columnNEA));
			}
			for (int row = 0; row < myTable.getRowCount(); row++) {
				address1ListArrayNEB.add((String) myTable.getCell(row, columnNEB));
			}
			Iterator<String> itrNEA = address1ListArrayNEA.iterator();
			while (itrNEA.hasNext()) {
				try {
					String temp = itrNEA.next().toString();
					if (condition.equalsIgnoreCase(Const.EXACTMATCH)) {
						if (temp.equals(address1ValueNEA)) {
							counter++;
						}
					} else if (condition.equalsIgnoreCase(Const.REGX)) {
						if (temp.toLowerCase().matches(
								address1ValueNEA.toLowerCase())) {
							counter++;
						}
					}
				} catch (Exception e) {}
			}
			if (counter >= 1) {
				retFlag = true;
				address1ListArrayNEA.clear();
			} else {
				retFlag = false;
				break;
			}
			counter = 0;
			Iterator<String> itrNEB = address1ListArrayNEB.iterator();
			while (itrNEB.hasNext()) {
				try {
					String temp = itrNEB.next().toString();
					if (condition.equalsIgnoreCase(Const.EXACTMATCH)) {
						if (temp.equals(address1ValueNEB)) {
							counter++;
						}
					} else if (condition.equalsIgnoreCase(Const.REGX)) {
						if (temp.toLowerCase().matches(
								address1ValueNEB.toLowerCase())) {
							counter++;
						}
					}
				} catch (Exception e) {}
			}
			
			if (counter >= 1) {
				retFlag = true;
				address1ListArrayNEA.clear();
			} else {
				retFlag = false;
				break;
			}
			if (nextRecord().isEnabled()){
				nextRecord().click();
				repeat = true;
			}else{
				repeat = false;
			}
			repeatCounter++;
		} while (repeat && repeatCounter <10);
		return retFlag;
	}

	public int getSearchListCount() {
		Utils.waitForScreenToLoad("UDSV", 10);
		int count = 0;
		try {
			count = Integer.parseInt(_NumberOfFound().getProperty("text").toString().replaceAll("[^0-9]", ""));
		} catch (NumberFormatException e) {
			count = -1;
		}
		return count; 
	}

	public String[] getIpAddressCheckBoxStatus() {

		String[] retArray = new String[4];
		Utils.waitForScreenToLoad("Create/Edit UDSV", 10);
		assemblies().click(atText("IP-Addresses"));
		sleep(2);
		retArray[0] = hideUsedIPAddresses().getState().toString();
		retArray[1] = showAllIPAddressesOfSelectedNE().getState().toString();
		assemblies2().click(atText("IP-Addresses"));
		sleep(2);
		retArray[2] = hideUsedIPAddresses2().getState().toString();
		retArray[3] = showAllIPAddressesOfSelectedNE2().getState().toString();

		return retArray;
	}

	public void verifyIpAddressAssembliesTab_5131() {
		Utils.waitForScreenToLoad("Create/Edit UDSV", 10);

		assemblies().click(atText("Assemblies"));
		ITestDataTable myTable;
		myTable = (ITestDataTable) rootPrivate().getTestData("contents");
		String labelColumn = (String)myTable.getColumnHeader(1);
		Verify.strVerification(labelColumn, "Label", Const.EXACTMATCH, "Veri: Label column should be in Assemblies tab");
		
		// Verification of IpAddressTab for NE-A
		assemblies().click(atText("IP-Addresses"));
		sleep(3);
		hideUsedIPAddresses().clickToState(NOT_SELECTED);
		sleep(3);
		showAllIPAddressesOfSelectedNE().clickToState(SELECTED);
		sleep(3);

		myTable = (ITestDataTable) _ipAddressTable().getTestData("contents");
		int totalRowCount = myTable.getRowCount();
		String ipAddString = null;

		// Attach and detach with hideUsedIPAddresses checkbox functionality 
		for (int i = 0; i < totalRowCount; i++) {
			_ipAddressTable().click(
					atCell(atRow(atIndex(i)), atColumn("ipName")));
			sleep(5);
			if (attachUDSV_IPAdd_NEA().exists()) {
				ipAddString = (String) myTable.getCell(i, 3);
				attachUDSV_IPAdd_NEA().click();
				sleep(10);
				hideUsedIPAddresses().clickToState(SELECTED);
				sleep(5);
				myTable = (ITestDataTable) _ipAddressTable().getTestData("contents");
				int totalRowCountAfterHideUsed = myTable.getRowCount();
				Verify.intVerification(totalRowCount,
						totalRowCountAfterHideUsed, ">",
						"Veri1: After selecting hideUsedIPAddresses and attach total row counts should be less");
				break;
			}
		}

		if (ipAddString != null) {
			hideUsedIPAddresses().clickToState(NOT_SELECTED);
			sleep(5);
			_ipAddressTable().click(
					atCell(atRow("ipAdresse", ipAddString),
							atColumn("ipAdresse")));
			sleep(5);
			if (detachUDSV_IPAdd_NEA().exists()) {
				detachUDSV_IPAdd_NEA().click();
				sleep(10);
				hideUsedIPAddresses().clickToState(SELECTED);
				sleep(5);
				myTable = (ITestDataTable) _ipAddressTable().getTestData("contents");
				int totalRowCountAfterHideUsed = myTable.getRowCount();
				Verify.intVerification(totalRowCount,
						totalRowCountAfterHideUsed, "=",
						"Veri2: After selecting hideUsedIPAddresses and detach total row counts should same");
			}
		}
		hideUsedIPAddresses().clickToState(NOT_SELECTED);
		sleep(3);
		showAllIPAddressesOfSelectedNE().clickToState(NOT_SELECTED);
		sleep(3);
		myTable = (ITestDataTable) _ipAddressTable().getTestData("contents");
		Verify.intVerification(1, myTable.getRowCount(), "=",
				"Veri3: After deselecting showAllIPAddressesOfSelectedNE");
	}
	
	public String updateUDSVType(String udsvTypeValue) {
		Utils.waitForScreenToLoad("Create/Edit UDSV", 10);
		TextSelectGuiSubitemTestObject udsvType = ((TextSelectGuiSubitemTestObject) Utils
				.getObj("name", ".cmbUdsvType"));
		
		String udsvTypeValueOriginal = udsvType.getSelectedText();
		udsvType.click();
		sleep(1);
		// Ethernet.IP
		udsvType.click(atText(udsvTypeValue));
		sleep(2);
		clickSaveButton();
		sleep(20);
		if (systemInformationPopup().exists()) {
			String errorMsg = (String) _htmlNoDataRecordFoundHtml().getProperty("text");
			sleep(1);
			ok3().click();
			sleep(2);
			udsvType.click();
			sleep(1);
			// Ethernet.IP
			udsvType.click(atText(udsvTypeValueOriginal));
			sleep(2);
			clickSaveButton();
			sleep(20);
			return Utils.removeHtml(errorMsg);
		}
		return Const.SUCCESSMSG;
	}

	public String addNewMicrowave() {
		Utils.waitForScreenToLoad("Create/Edit UDSV", 10);
		udsvParam().click(atText("Lines/Microwaves"));
		sleep(2);
		int initialRowCount = ((ITestDataTable) Utils.getGuiTestObjByName(
				".tbl").getTestData("contents")).getRowCount();
		Utils.getGuiTestObjByName(".proButtonLeitungAuswahl").click();
		Utils.waitForScreenToLoad("Choose option", 10);
		Utils.getGuiTestObjByToolTip("OK [Alt+O]").click();
		Utils.waitForScreenToLoad("Choose Microwave", 10);
		Utils.getGuiTestObjByToolTip("Select [Alt+S]").click();
		sleep(2);
		
		Utils.waitForScreenToClose(Const.DURATION, 60);
		if (SysInfoDialog().exists()) {
			((GuiTestObject) Utils.getObj("accessibleContext.accessibleName",
					"OK")).click();
			sleep(2);
			Utils.getGuiTestObjByToolTip("Close [Alt+L]").click();
			 return "No Data Found";
		}

		if (Utils.waitForScreenToLoad("Choose Microwave", 10)) {
			GuiSubitemTestObject microwaveListTable = (GuiSubitemTestObject) Utils
					.getObj(".class",
							"com.tmobile.itnetdev.fixedbuild.common.gui.components.table.ProTable");
			microwaveListTable.click(atCell(atRow(atIndex(0)),
					atColumn(atIndex(0))));
			sleep(1);
			Utils.getGuiTestObjByToolTip("Choose [Alt+C]").click();
			Utils.waitForScreenToClose("Choose Microwave", 10);
		}

		int newRowCount = ((ITestDataTable) Utils.getGuiTestObjByName(".tbl")
				.getTestData("contents")).getRowCount();
		if (newRowCount == initialRowCount + 1) {
			return Const.SUCCESSMSG;
		} else {
			return Const.NOTSUCCESS;
		}
	}
}
