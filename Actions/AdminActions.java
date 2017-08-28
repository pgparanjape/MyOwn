package PB_2015_2.FB.Actions;

import java.util.Enumeration;
import java.util.HashMap;

import resources.PB_2015_2.FB.Actions.AdminActionsHelper;
import PB_2015_2.Common.Const;
import PB_2015_2.Common.Utils;
import PB_2015_2.FB.Common.Configuration;
import PB_2015_2.Verification.Verify;

import com.rational.test.ft.object.interfaces.*;
import com.rational.test.ft.vp.*;

public class AdminActions extends AdminActionsHelper {
	
	private ITestDataTable myTable;
	
	public boolean configVisibleFlag = true;
	public boolean configBatchSave, configInstantiated = false ;
	private String configurationBaseType = "";
	public String restriction = "";
	
	public AdminActions(String configurationBaseType) {
		this.configurationBaseType = configurationBaseType;
	}

	public AdminActions() {
	}

	public String createNewCatalog(String whichCatlog, String newCatlogName, boolean suppressError) {
		String returnMessage = "" ;
		sleep(2);
		pMenuBar().click(atPath("Administration"));
		sleep(1);
		pMenuBar().click(atPath("Administration->Catalogs"));
		sleep(2);
		Utils.waitForScreenToLoad("Select Catalog Types", 20);
		selectCatalogTypes().click(atPoint(254,11));
		sleep(2);
		usageReason().click(atCell(atRow("headerName", whichCatlog),atColumn("headerName")));
		sleep(2);
		Utils.getGuiTestObjByToolTip("Edit [Alt+F]").click();
//		editAltF().click();
		sleep(15);
		myTable = (ITestDataTable)neVbzTableTable().getTestData("contents");
		boolean commonflag = false;
		for (int row = 0; row < myTable.getRowCount(); row++) {
			String name = myTable.getCell(row, 0).toString();
			if (name.equalsIgnoreCase(newCatlogName)){
				if(!suppressError){
					returnMessage = "Given "+newCatlogName +" present in " + whichCatlog;
				}
				commonflag = true;
				break;
			}
		}
		neVbzTableTable().unregister();
		if (!commonflag){
			sleep(1);
			newAltN().click();
			sleep(1);
			neVbzDBTextField().setText(newCatlogName);
			sleep(1);
			administerCatalogType().inputKeys("{TAB}");
			sleep(4);
			myTable = (ITestDataTable)neVbzTableTable().getTestData("contents");
			int newRowNo = myTable.getRowCount()-1;
			neVbzTableTable().click(atCell(atRow(atIndex(newRowNo)), 
					atColumn("headerDescription")), atPoint(63,6));
			neVbzTableTable().doubleClick(atCell(atRow(atIndex(newRowNo)), 
					atColumn("headerDescription")), atPoint(63,6));
			neVbzDBTextField().setText(newCatlogName);
			sleep(1);
			administerCatalogType().inputKeys("{TAB}");
			neVbzTableTable().doubleClick(atCell(atRow(atIndex(newRowNo)), 
					atColumn("headerSort")), atPoint(13,8));
			neVbzTableTable().doubleClick(atCell(atRow(atIndex(newRowNo)), 
					atColumn("headerSort")), atPoint(13,8));
			administerCatalogType().inputKeys("1{TAB}");
			sleep(1);
			saveAltA().click();
			sleep(2);
			returnMessage = Const.SUCCESSMSG;
		}
		closeAltL().click();
		sleep(2);
		return returnMessage;
	}

	String extConfName = "";
	public String createNewConfigWithExtension(String configName, String extConfName, String baseType, String configStartDate) {
		sleep(2);
		this.extConfName = extConfName;
		String msg = createNewConfig(configName, baseType, configStartDate);
		this.extConfName = extConfName;
		return msg;
	}
	
	public String createNewConfig(String configName, String baseType, String configStartDate) {
		openNewConfigDialogOnly();
		proDBTextField().setText(configName);
		sleep(1);
		baseType().click();
		sleep(1);
		baseType().click(atText(baseType));
		sleep(10);
		if (!extConfName.isEmpty()) {
			sleep(2);
			pRadioButton().click();
			sleep(2);
			detailButton().click();
			sleep(5);
			configurationsAssemblyGroupsCh().click(atPoint(568, 9));
			sleep(1);
			TestObject[] nameTextBox = find(atDescendant("accessibleContext.accessibleName", "Name:", "name", ".proDBTextFieldName"));
			for (int i = 0; i < nameTextBox.length; i++) {
				GuiTestObject abc = (GuiTestObject) nameTextBox[i];
				if (abc.isEnabled() && abc.isShowing() && abc.exists()) {
					try {
						abc.click();
						sleep(2);
						((TextGuiSubitemTestObject) abc).setText(extConfName);
						break;
					} catch (Exception e) {}
				}
			}
			sleep(1);
			GuiTestObject selectButton = (GuiTestObject) Utils.getObj("toolTipText", "Select [Alt+S]", "name", ".jButtonSelect");
			selectButton.click();
			sleep(5);
			if (systemInformation().exists()) {
				systemInformation().click(atPoint(136, 16));
				sleep(1);
				ok3().click();
				sleep(1);
				Utils.closeAll();
				return "extnsion of Configuration does not exits";
			}
		}
		start().setText(configStartDate);
		sleep(1);
		create().click();
		sleep(5);
		if (jDialog().exists()){
			ok().click();
			sleep(1);
			proDialog().click(atPoint(244,14));
			sleep(1);
			Utils.closeAll();
			return "Configuration already present";
		}
		
		/*for (int i= 0; i<20 && !administerNetworkElements().exists(); i++ ){
			sleep(10);
		}*/
		if (Utils.waitForScreenToLoad("Configuration / Assembly Group -", 200)){
			sleep(2);
			String nameText = (String) name().getProperty("text");
			sleep(1);
			if(configVisibleFlag){
				configTreeTable().click(atCell(atRow(atIndex(0)), atColumn("Visible")),atPoint(12,7));
			}
			sleep(1);
			configTreeTable().click(atCell(atRow(atIndex(0)), atColumn("Instantiated")),atPoint(13,8));
			sleep(1);
			if (!restriction.isEmpty()){
				restriction().click();
				sleep(1);
				_4105_UsageRestrictions().click(atCell(atRow("headerValues",restriction),atColumn("headerValues")), atPoint(27,4));
				sleep(1);
			}
			parameter().click(atText("Role Rights"));
			sleep(5);
			application().click();
			sleep(1);
			application().click(atText("/f&/b"));
			sleep(5);
			myTable = (ITestDataTable)n().getTestData("contents");
			n().click(atCell(atRow(atIndex(0)),atColumn("insDeleteRight")), atPoint(94,6));
			sleep(1);
			n().click(SHIFT_LEFT, atCell(atRow(atIndex(myTable.getRowCount()-1)),atColumn("insDeleteRight")), atPoint(94,6));
			insertDelete().click();
			sleep(2);
			insertDelete().click();
			sleep(2);
			saveConfiguration(false);
			Utils.closeAll();
			if (nameText.equals(configName)) {
				return Const.SUCCESSMSG;
			} else {
				return "Some thing went wrong, configuration name is not as per given ";
			}
		} else {
			return "Unable to save Configuration";
		}
	}

	public void updateGroupType(String groupTypeName, String value) {
		sleep(2);
		pMenuBar().click(atPath("Administration"));
		sleep(1);
		pMenuBar().click(
				atPath("Administration->Network Elements->Group Types"));
		sleep(5);
		selectGroupTypes().click();
		sleep(1);
		name2().click(atPoint(35, 11));
		name2().setText(groupTypeName);
		sleep(2);
		selectAltS().click();
		sleep(2);
		String[] valuArr = value.split("@");
		if (valuArr[0].equalsIgnoreCase("NE-Type")) {
			if (valuArr.length >1 ) {
				neType().click();
				sleep(1);
				neType().click(atText(valuArr[1]));
			} else {
				neType().click();
				sleep(1);
				neType().click(atIndex(0));
			}
		}
		sleep(10);
		if (saveAltA3(ANY, MAY_EXIT).exists())
			saveAltA3(ANY, MAY_EXIT).click();
		sleep(5);
	}

	public void closeAll() {
		Utils.closeAll();
	}

	public void openSearchCatGroup() {
		sleep(2);
		pMenuBar().click(atPath("Administration"));
		sleep(1);
		pMenuBar().click(atPath("Administration->Network Elements->Group Categories"));
		sleep(2);
		selectGroupCategories().click(atPoint(255,20));
	}

	public void clickNewButton() {
		TestObject[] newButtons = find(atDescendant("toolTipText","New [Alt+N]"));
		GuiTestObject btn = (GuiTestObject) newButtons[0];
		if (btn.isEnabled() && btn.isShowing() && btn.exists()) {
			btn.click();
			sleep(2);
		}
	}
	
	private void clickSelect() {
		((GuiTestObject)Utils.getObj("toolTipText","Select [Alt+S]")).click();
	}

	public String createNewNECatGroup(
			HashMap<String, String> neCatGroupCreateCriteria,
			String neCatGroupName) {

		sleep(2);
		administerNetworkElements().click(atPoint(166, 8));
		sleep(2);
		categoryName().setText(neCatGroupName);
		sleep(1);
		description().setText(neCatGroupName + "_Desc");
		if (neCatGroupCreateCriteria.get("Application") != null) {
			application2().click();
			sleep(1);
			application2().click(atText(neCatGroupCreateCriteria.get("Application")));
			// application2().click(atText("/f&/b"));
			sleep(2);
		}
		if (neCatGroupCreateCriteria.get("StartDate") != null) {
			start2().setText(neCatGroupCreateCriteria.get("StartDate"));
			sleep(2);
		}
		if (neCatGroupCreateCriteria.get("EndDate") != null) {
			end().setText(neCatGroupCreateCriteria.get("EndDate"));
			sleep(2);
		}
		if (neCatGroupCreateCriteria.get("Hidden") != null) {
			if (neCatGroupCreateCriteria.get("Hidden").equalsIgnoreCase("TRUE")) {
				hiddenTrue().clickToState(SELECTED);
				sleep(2);
			} else {
				hiddenTrue().clickToState(NOT_SELECTED);
				sleep(2);
			}
		}
		saveAltA3().click();
		sleep(5);
		if (error().exists()) {
			error().click(atPoint(141, 11));
			String errorText = (String) errorText().getProperty("text");
			sleep(2);
			ok2().click();
			return errorText.replaceAll("<html>", "").replaceAll("</html>", "");
		}
		return Const.SUCCESSMSG;
	}

	public String getNeCatGroupName() {
		sleep(2);
		String neCatGroupNametext = (String)categoryName().getProperty("text");
		sleep(2);
		return neCatGroupNametext;
	}

	public String[] getAllCatGroupListItems() {
		Utils.closeAll();
		sleep(2);
		pMenuBar().click(atPath("NE-Groups"));
		sleep(1);
		pMenuBar().click(atPath("NE-Groups->NE-Gruppen"));
		sleep(2);
		newAltN3().click();
		sleep(2);
		return getListBoxItems(category());
	}
	
	public void openConfiguration(String configurationName) {
		sleep(2);
		pMenuBar().click(atPath("Administration"));
		sleep(1);
		pMenuBar().click(atPath("Administration->Network Elements->Configurations / Assembly Groups"));
		Utils.waitForScreenToLoad("Configurations / Assembly Groups select", 30);
		sleep(1);
//		name3().setText(configurationName);
		((TextGuiSubitemTestObject) Utils.getObj("name", ".proDBTextFieldName")).setText(configurationName);
		sleep(1);
		TextSelectGuiSubitemTestObject baseType2 = (TextSelectGuiSubitemTestObject) Utils.getObj("name", ".neVbzComboBoxBasicType");
		if (configurationBaseType != ""){
			baseType2.click();
			sleep(1);
			baseType2.click(atText(configurationBaseType));
			sleep(2);
		}
		sleep(5);
		Utils.getGuiTestObjByName(".jButtonSelect").click();
		sleep(10);
		try {
			myTable = (ITestDataTable)configurationListTable().getTestData("contents");
			if (myTable.getRowCount()>1){
			configurationListTable().click(atCell(atRow(atIndex(0)),atColumn("headerName")),atPoint(39,10));
			sleep(1);
			Utils.getGuiTestObjByName(".jButtonEdit").click();
			sleep(5);
			}
		} catch (Exception e) {}
		Utils.waitForScreenToLoad("Configuration / Assembly Group - ", 30);
		sleep(1);
	}
	
	/**
	 * Method provides the value of Label For Component list box value
	 */
	public String[] getConfigurationLabelValues() {
		sleep(2);
		administerNetworkElements().click(atPoint(529, 10));
		sleep(1);
		parameter().click(atText("Type / Node-ID / Default Values"));
		sleep(2);
		proComboBox().waitForExistence();
		return getListBoxItems(proComboBox());
	}
	
	public String[] getComponentTreeLabelValues() {
		sleep(2);
		administerNetworkElements().click(atPoint(529, 10));
		sleep(1);
		Enumeration<?> myenu = configTreeTable().getTestDataTypes().keys();
		myTable = (ITestDataTable) configTreeTable().getTestData("contents");
		String[] retArray = new String[myTable.getRowCount()];
		while (myenu.hasMoreElements()) {
			String string = myenu.nextElement().toString();
			ITestData itestData = configTreeTable().getTestData(string);			
			if (itestData instanceof ITestDataTable) {
				ITestDataTable iTableData = (ITestDataTable) configTreeTable().getTestData(string);
				for (int row = 0; row < iTableData.getRowCount(); row++) {
					try{
						retArray[row] = (String) iTableData.getCell(row, 1);
					}catch (Exception e){}
				}
			}
		}
		return retArray;
	}

	/**
	 * This method provides all the listbox items in Array
	 * @param whichListBox
	 * @return
	 */
	private String[] getListBoxItems(TextSelectGuiSubitemTestObject whichListBox) {
		ITestDataElement nameListElement;
		ITestDataList catList = (ITestDataList) whichListBox.getTestData("list");
		ITestDataElementList nameListElements = catList.getElements();
		String[] retArray = new String[catList.getElementCount()];
		for (int i = 0; i < catList.getElementCount(); i++) {
			nameListElement = nameListElements.getElement(i);
			retArray[i] = (String) nameListElement.getElement();
		}
		return retArray;
	}

	/**
	 * selects the row (Root, component) from the tree view
	 * @param whichRow
	 */
	public void selectComponentFromTreeTable(String whichRow) {
		sleep(2);
		Utils.waitForScreenToLoad("Configuration / Assembly Group - ", 20);
		sleep(1);
		try {
			if (whichRow.contains(":")){
				String[] rowNos = whichRow.split(":");
				for (int i = 0; i < rowNos.length; i++) {
					sleep(2);
					configTreeTable().click(CTRL_LEFT, atCell(atRow(atIndex(Integer.parseInt(rowNos[i]))), atColumn("Label")));
					sleep(2);
				}
			}else{
			int rowNo = Integer.parseInt(whichRow);
			configTreeTable().click(atCell(atRow(atIndex(rowNo)), atColumn("Label")),atPoint(13, 9));
			}
			
		} catch (Exception ex) {
			Enumeration<?> myenu = configTreeTable().getTestDataTypes().keys();
			int rowNo = 0;
			outer: while (myenu.hasMoreElements()) {
				String string = myenu.nextElement().toString();
				ITestData itestData = configTreeTable().getTestData(string);
				if (itestData instanceof ITestDataTable) {
					ITestDataTable iTableData = (ITestDataTable) configTreeTable().getTestData(string);
					for (int row = 0; row < iTableData.getRowCount(); row++) {
						for (int col = 0; col < iTableData.getColumnCount(); col++) {
							try {
								if (((String) iTableData.getCell(row, col)).startsWith(whichRow)) {
									rowNo = row;
									break outer;
								}
							} catch (Exception e) {}
						}
					}
				}
			}
			configTreeTable().click(atCell(atRow(atIndex(rowNo)), atColumn("Label")));
		}
		sleep(2);
	}
	
	public String[] getConfigComponentTreeColumnValues(int whichColumn) {
		sleep(2);
		Enumeration<?> myenu = configTreeTable().getTestDataTypes().keys();
		myTable = (ITestDataTable) configTreeTable().getTestData("contents");
		String[] retArray = new String[myTable.getRowCount()];
		while (myenu.hasMoreElements()) {
			String string = myenu.nextElement().toString();
			ITestData itestData = configTreeTable().getTestData(string);			
			if (itestData instanceof ITestDataTable) {
				ITestDataTable iTableData = (ITestDataTable) configTreeTable().getTestData(string);
				for (int row = 0; row < iTableData.getRowCount(); row++) {
					try{
						retArray[row] = (String) iTableData.getCell(row, whichColumn);
					}catch (Exception e){}
				}
			}
		}
		return retArray;
	}
	
	public String updateConfigComponentInstantiatedValue(String whichComponent, boolean value) {
		Enumeration<?> myenu = configTreeTable().getTestDataTypes().keys();
		int rowNo = 0;
		String currentValue = "";
		outer: while (myenu.hasMoreElements()) {
			String string = myenu.nextElement().toString();
			ITestData itestData = configTreeTable().getTestData(string);
			if (itestData instanceof ITestDataTable) {
				ITestDataTable iTableData = (ITestDataTable) configTreeTable().getTestData(string);
				for (int row = 0; row < iTableData.getRowCount(); row++) {
					for (int col = 0; col < iTableData.getColumnCount(); col++) {
						try {
							String temp= ((String) iTableData.getCell(row, col));
							if (temp.startsWith(whichComponent)) {
								rowNo = row;
								currentValue = iTableData.getCell(row, 4).toString();
								break outer;
							}
						} catch (Exception e) {}
					}
				}
			}
		}
		if (!currentValue.equalsIgnoreCase(String.valueOf(value))){
			sleep(2);
			configTreeTable().click(atCell(atRow(atIndex(rowNo)), atColumn("Instantiated")),atPoint(13, 9));
			sleep(2);
		}
		return saveConfiguration(true);
	}

	/***
	 * Insert the Assembly group to Configuration
	 * you can add the assembly name like "ALL", "ETH", "a%"
	 * @param assemblyGroupName
	 */
	public String addAssemblyGroupToConfiguration(String assemblyGroupName) {
		sleep(2);
		administerNetworkElements().click(atPoint(592, 6));
		String[] configCompList = getConfigComponentTreeColumnValues(3);
		((GuiTestObject)Utils.getObj("name", ".btBGInsert")).click();
//		insert().click();
		Utils.waitForScreenToLoad("Configurations / Assembly Groups Choice", 30);
		if (assemblyGroupName.equalsIgnoreCase("ALL")) {
			((GuiTestObject) Utils.getObj("name", ".jButtonSelect")).click();
			sleep(2);
			myTable = (ITestDataTable) assembleyGroupListTable().getTestData("contents");
			assembleyGroupListTable().click(SHIFT_LEFT,atCell(atRow(atIndex(1)), atColumn("headerName")),atPoint(73, 7));
			sleep(1);
			assembleyGroupListTable().click(SHIFT_LEFT,atCell(atRow(atIndex(myTable.getRowCount()-1)),atColumn("headerName")), atPoint(73, 7));
			sleep(1);
			choose().click();
			sleep(2);

		} else {
			name3().setText(assemblyGroupName);
			sleep(1);
			((GuiTestObject) Utils.getObj("name", ".jButtonSelect")).click();
			sleep(2);
			myTable = (ITestDataTable) assembleyGroupListTable().getTestData("contents");
			if (myTable.getRowCount()>1){
				assembleyGroupListTable().click(SHIFT_LEFT,atCell(atRow(atIndex(0)), atColumn("headerName")),atPoint(73, 7));
				sleep(2);
				choose().click();
			}
		}
		sleep(2);
		myTable = (ITestDataTable) assembleyGroupListTable().getTestData("contents");
		for (int i = 0; i < myTable.getRowCount() && assemblyGroup().exists() ; i++) {
			assemblyGroup().click(atPoint(160, 12));
			sleep(1);
			proCheckBox().clickToState(SELECTED);
			sleep(1);
			insert2().click();
			sleep(4);
		}
		sleep(5);
		if(configInstantiated){
			String[] configCompListNew = getConfigComponentTreeColumnValues(3);
			for (int i = configCompList.length; i < configCompListNew.length; i++) {
			configTreeTable().click(atCell(atRow(atIndex(i)), atColumn(atIndex(0))));
			sleep(2);
			configTreeTable().click(atCell(atRow(atIndex(i)), atColumn("Instantiated")),atPoint(13,8));
			sleep(1);
			}
		}
		return saveConfiguration(true);
	}

	/**
	 * @return
	 */
	private String saveConfiguration(boolean dialog) {
		sleep(2);
		if (saveAltA2().exists()){
			saveAltA2().click();
			sleep(10);
			if (dialog) {
				for (int i = 0; i < 120; i = i + 10) {
					if (jDialog3().exists())
						break;
					sleep(10);
				}
				if (jDialog3().exists()) {
					jDialog3().click(atPoint(150, 16));
					sleep(2);
					if (configBatchSave)
						batch().click();
					else
						immediately().click();
					sleep(60);
				}
			}
			String changesSaved = changesSaved().getProperty("text").toString();
			if (!Const.CHANGESSAVED.contains(changesSaved)) {
				sleep(120);
				changesSaved = changesSaved().getProperty("text").toString();
				if (Const.CHANGESSAVED.contains(changesSaved)) {
					return Const.SUCCESSMSG;
				}else {
					return Const.NOTSUCCESS;
				}
			}else {
				return Const.SUCCESSMSG;
			}
		}else {
			return "Save Button is Disabled";
		}
	}
	
	/**
	 * update the label for the configuration
	 */
	public String updateConfigurationLabel(String rootPublicLabel) {
		sleep(2);
		administerNetworkElements().click(atPoint(529, 10));
		sleep(1);
		parameter().click(atText("Type / Node-ID / Default Values"));
		sleep(2);
		proComboBox().waitForExistence();
		sleep(2);
		proComboBox().setText(rootPublicLabel);
		sleep(1);
		return saveConfiguration(false);
	}
	
	protected String clickSaveButton() {
		GuiTestObject saveButton = Utils.getGuiTestObjByToolTip("Save [Alt+A]");
		saveButton.click();
		sleep(2);
		for (int i = 0; i < 120; i = i + 5) {
			saveButton = Utils.getGuiTestObjByToolTip("Save [Alt+A]");
			if (saveButton != null && saveButton.isEnabled()) {
				sleep(5);
			} else {
				break;
			}
		}
		saveButton = Utils.getGuiTestObjByToolTip("Save [Alt+A]");
		if (saveButton == null) {
			return Const.SUCCESSMSG;
		} else if (!saveButton.isEnabled()){
			return Const.SUCCESSMSG;
		}else {
			return Const.NOTSUCCESS;
		}
	}

	public String addPredecessor(String whichPredecessor, String whatComponents) {
		sleep(2);
		predecessor().click();
		sleep(5);
		configurationsAssemblyGroupsCh().click(atPoint(542,15));
		sleep(2);
		name3().click(atPoint(53,6));
		sleep(1);
		name3().setText(whichPredecessor);
		sleep(5);
		((GuiTestObject) Utils.getObj("name", ".jButtonSelect")).click();
//		selectAltS2().click();
		sleep(5);
		administerPredecessor().click(atPoint(546,11));
		sleep(2);
		attach().click();
		sleep(2);
		
		ITestDataTable configurationLeftSide, configurationRightSide;
		TestObject[] configurationTables = find(atDescendant(".class", "com.tmobile.itnetdev.fixedbuild.common.gui.components.treetable.ProTreeTable"));
		
		GuiSubitemTestObject left = (GuiSubitemTestObject) configurationTables[0];
		GuiSubitemTestObject right = (GuiSubitemTestObject) configurationTables[1];
		
		configurationLeftSide = (ITestDataTable)left.getTestData("contents");
		configurationRightSide = (ITestDataTable)right.getTestData("contents");
		
//		"Abis - 1:Abis - 1@@Abis - 2:Abis - 2@@Abis - 3:Abis - 3"
		String[] attachments = whatComponents.split("@@");
		for (int i = 0; i < attachments.length; i++) {
			String[] attachment = attachments[i].split(":");
			for (int row = 0; row < configurationLeftSide.getRowCount(); row++) {
				String temp = (String) configurationLeftSide.getCell(row,0);
				if (temp.equalsIgnoreCase(attachment[0])) {
					left.click(atCell(atRow(atIndex(row)), atColumn("Name")),atPoint(66, 4));
					break;
				}
			}
			sleep(2);
			for (int row = 0; row < configurationRightSide.getRowCount(); row++) {
				String temp = (String) configurationRightSide.getCell(row,0);
				if (temp.equalsIgnoreCase(attachment[1])) {
					right.click(atCell(atRow(atIndex(row)), atColumn("Name")),atPoint(66, 4));
					break ;
				}
			}
			sleep(2);
			attach().click();
			sleep(2);
		}
		clickSaveButton();
		sleep(10);
		closeAltL5().click();
		sleep(10);
		return Const.SUCCESSMSG;
	}

	public String updateConfigurationConfigBase(boolean checkBoxStatus) {
		sleep(2);
		administerNetworkElements().click(atPoint(529, 10));
		sleep(2);

		if (checkBoxStatus) {
			proCheckBox2().clickToState(SELECTED);
		} else {
			proCheckBox2().clickToState(NOT_SELECTED);
		}
		sleep(2);
		clickSaveButton();
		sleep(15);
		String ChangesSaved_text = (String) changesSaved().getProperty("text");
		if (ChangesSaved_text.equalsIgnoreCase(Const.CHANGESSAVED)){
			return Const.SUCCESSMSG;
		}else{
			return Const.NOTSUCCESS;
		}
	}

	public void newConfigDialogWithCancelAction(String action) {
		openNewConfigDialogOnly();
		if (action.equalsIgnoreCase("Close Window")){
			proDialog().click();
			sleep(1);
			proDialog().close();
		}else if (action.equalsIgnoreCase("ALT+F4")){
			proDialog().click();
			sleep(1);
			proDialog().inputKeys("%{F4}");
		}else if (action.equalsIgnoreCase("ESC")){
			proDialog().click();
			sleep(1);
			proDialog().inputKeys("{ESCAPE}");
		}
		sleep(3);
		if (Utils.getDialogTitles().contains("Create Configuration")){
			Verify.failDueToUnexpectedError("closing of application failed with action " + action);
			proDialog().close();
			sleep(2);
		}
	}

	public IFtVerificationPoint getVP(String vpName) {
		if (vpName.equalsIgnoreCase("CreateNewConfigAssemblyGrp_5250VP"))
			return CreateNewConfigAssemblyGrp_5250VPVP();
		else
			return CreateNewConfigAssemblyGrp_5250VPVP();
	}

	public void openNewConfigDialogOnly() {
		sleep(2);
		pMenuBar().click(atPath("Administration"));
		sleep(1);
		pMenuBar().click(atPath("Administration->Network Elements->Configurations / Assembly Groups"));
		sleep(1);
		Utils.waitForScreenToLoad("Configurations / Assembly Groups Select", 30);
		sleep(1);
		Utils.getGuiTestObjByName(".jButtonNewConfiguration").click();
		Utils.waitForScreenToLoad("Create Configuration", 30);
	}

	public String configRight(String whichRole, String whichConfig,String neType ,String access) {
		sleep(2);
		pMenuBar().click(atPath("Administration"));
		sleep(1);
		pMenuBar().click(atPath("Administration->User Management->Roles"));
		sleep(1);
		Utils.waitForScreenToLoad("Roles Select", 30);
		((TextGuiSubitemTestObject) Utils.getObj("name", ".jTextFieldName")).setText(whichRole);
		sleep(1);
		((GuiTestObject) Utils.getObj("name", ".jButtonSelect")).click();
		sleep(15);
		if (jDialog2().exists()) {
			jDialog2().click(atPoint(188, 15));
			sleep(1);
			ok4().click();
			sleep(1);
			admin().click(atCell(atRow(atIndex(0)),atColumn("headerRole")));
			sleep(1);
			Utils.getGuiTestObjByName("Edit [Alt+F]").click();
			sleep(1);
		}
		Utils.waitForScreenToLoad("Role  -  ", 30);
		((GuiSubitemTestObject) Utils
				.getObj(".class",
						"com.tmobile.itnetdev.fixedbuild.common.gui.components.ProTabbedPane"))
				.click(atText("Configuration Rights"));
		sleep(2);
		for (int i = 0; i < 60 && dauer000002().exists();) {
			sleep(5);
			i = i + 5;
		}
		sleep(2);
		TextSelectGuiSubitemTestObject neTypeDropDown = ((TextSelectGuiSubitemTestObject) Utils
				.getObj(".class",
						"com.tmobile.itnetdev.fixedbuild.common.gui.components.ProComboBox",
						".priorLabel", "NE-Type:"));
		if (neType != "" ){
			neTypeDropDown.click();
			sleep(1);
			neTypeDropDown.click(atText(neType));
		}
		sleep(5);
		GuiSubitemTestObject configList = ((GuiSubitemTestObject) Utils
				.getObj(".class",
						"com.tmobile.itnetdev.fixedbuild.common.gui.components.table.ProTable",
						".classIndex", "0"));
		myTable = (ITestDataTable) configList.getTestData("contents");
		for (int row = 0; row < myTable.getRowCount(); row++) {
			String temp = myTable.getCell(row, 0).toString();
			if (temp.equalsIgnoreCase(whichConfig)) {
				if (access.equalsIgnoreCase("GRANT")) {
					temp = myTable.getCell(row, 5).toString();
					if (temp.equalsIgnoreCase("FALSE")) {
						configList.click(atCell(atRow(atIndex(row)),atColumn(atIndex(5))));
						sleep(2);
						break;
					}
					temp = myTable.getCell(row, 4).toString();
					if (temp.equalsIgnoreCase("FALSE")) {
						configList.click(atCell(atRow(atIndex(row)),atColumn(atIndex(4))));
						sleep(2);
					}
					
				} else {
					temp = myTable.getCell(row, 4).toString();
					if (temp.equalsIgnoreCase("TRUE")) {
						configList.click(atCell(atRow(atIndex(row)),atColumn(atIndex(4))));
						sleep(2);
						break;
					}
					temp = myTable.getCell(row, 5).toString();
					if (temp.equalsIgnoreCase("TRUE")) {
						configList.click(atCell(atRow(atIndex(row)),atColumn(atIndex(5))));
						sleep(2);
					}
				}
			}
		}
		sleep(2);
		if (saveAltA2().exists()){
			saveAltA2().click();
			sleep(30);
			String changesSaved = ((GuiTestObject) Utils
					.getObj(".class",
							"com.tmobile.itnetdev.fixedbuild.common.gui.components.ProLabel",
							".classIndex", "0")).getProperty("text").toString();
			if (!Const.CHANGESSAVED.contains(changesSaved)) {
				sleep(60);
				changesSaved = ((GuiTestObject) Utils
						.getObj(".class",
								"com.tmobile.itnetdev.fixedbuild.common.gui.components.ProLabel",
								".classIndex", "0")).getProperty("text").toString();
				if (Const.CHANGESSAVED.contains(changesSaved)) {
					return Const.SUCCESSMSG;
				}else {
					return Const.NOTSUCCESS;
				}
			}else {
				return Const.SUCCESSMSG;
			}
		}else {
			return "Save Button is Disabled";
		}
	}

	public void reConnect() {
		sleep(2);
		pMenuBar().click(atPath("System"));
		sleep(1);
		pMenuBar().click(atPath("System->Connect"));
		sleep(5);
		yes().click();
		sleep(5);
		relogin().waitForExistence();
		password().setText(Global.User.account);
		sleep(1);
		password2().setText(Global.User.password);
		sleep(1);
		login().click();
		sleep(1);
		for (int i = 0; i < 120 && pleaseWait().exists();i = i + 10) {
			sleep(10);
		}
	}

	public String createNewParamGroup(String grpName, String whichObjects) {
		sleep(2);
		pMenuBar().click(atPath("Administration"));
		sleep(1);
		pMenuBar().click(atPath("Administration->Parameters->Parameter Groups"));
		Utils.waitForScreenToLoad("parameter groups", 60);
		clickNewButton();
		sleep(5);
		neVbzDBTextField2().setText(grpName);
		sleep(1);
		mainFrame().inputKeys("{TAB}");
		sleep(2);
		GuiSubitemTestObject table = (GuiSubitemTestObject) Utils
				.getObj(".class",
						"com.tmobile.itnetdev.fixedbuild.nevbz.presentation.framework.NeVbzTable$Table");
		myTable = (ITestDataTable) table.getTestData("contents");
		if (whichObjects.equalsIgnoreCase("All")) {
			for (int col = 2; col < myTable.getColumnCount(); col++) {
				table.click(atCell(atRow(atIndex(myTable.getRowCount() - 1)),
						atColumn(col)));
				sleep(1);
			}
		} else {
			String[] paramObjs = whichObjects.split(":");
			for (int i = 0; i < paramObjs.length; i++) {
				table.click(atCell(atRow(atIndex(myTable.getRowCount() - 1)),
						atColumn(paramObjs[i])));
				sleep(1);
			}
		}
		return clickSaveButton();
	}

	public String addNewParameter(HashMap<String, String> paramCreateCriteria) {
		pMenuBar().click(atPath("Administration"));
		sleep(1);
		pMenuBar().click(
				atPath("Administration->Parameters->Parameter Definition"));
		Utils.waitForScreenToLoad("Parameters Select", 30);
		clickNewButton();
		Utils.waitForScreenToLoad("Parameters Create", 30);
		((TextGuiSubitemTestObject) Utils.getObj(".priorLabel", "Name*:",
				".classIndex", "1")).setText(paramCreateCriteria
				.get("paramname"));
		sleep(1);

		TextSelectGuiSubitemTestObject parameterGroup = (TextSelectGuiSubitemTestObject) Utils
				.getObj(".priorLabel", "Parameter Group*:", ".classIndex", "0");
		parameterGroup.click();
		sleep(1);
		parameterGroup.click(atText(paramCreateCriteria.get("paramgroup")));

		TextSelectGuiSubitemTestObject dataType = (TextSelectGuiSubitemTestObject) Utils
				.getObj(".priorLabel", "Data Type*:", ".classIndex", "2");
		dataType.click();
		sleep(1);
		dataType.click(atText(paramCreateCriteria.get("paramdatatype")));
		sleep(1);
		pTextArea().setText(paramCreateCriteria.get("paramdefaultvalue"));
		sleep(1);
		if (paramCreateCriteria.get("paramvalid") != null) {
			if (paramCreateCriteria.get("paramvalid").equalsIgnoreCase("true")) {
				validTrue().clickToState(SELECTED);
			} else {
				validTrue().clickToState(NOT_SELECTED);
			}
			sleep(1);
		}
		String msg = clickSaveButton();
		if (msg.equalsIgnoreCase(Const.NOTSUCCESS)) {
			if (error().exists()) {
				String error = (String) networkElementGroup2426_7651Wi()
						.getProperty("text");
				ok2().click();
				sleep(1);
				Utils.closeAll();
				return Utils.removeHtml(error);
			} else {
				return Const.NOTSUCCESS;
			}

		} else {
			return Const.SUCCESSMSG;
		}
	}

	public String addParamToConfig(String paramGrpName, String paramName) {
		Utils.waitForScreenToLoad("Configuration / Assembly Group - ", 30);
		((GuiTestObject) Utils.getObj("toolTipText",
				"Insert Parameter [Alt+G]", ".classIndex", "5")).click();
		Utils.waitForScreenToLoad("Parameters Choice", 30);
		parameterGroup().click();
		parameterGroup().click(atText(paramGrpName));
		sleep(1);
		((TextGuiSubitemTestObject) Utils.getObj(".priorLabel", "Name:",
				".classIndex", "0")).setText(paramName);
		sleep(1);
		clickSelect();
		sleep(2);
		((GuiSubitemTestObject) Utils
				.getObj(".class",
						"com.tmobile.itnetdev.fixedbuild.common.gui.components.ProTabbedPane"))
				.click(atText("Parameter"));
		sleep(5);
		try {
			_html4969_GRP_ALLHtml().click(
					atCell(atRow("headerGroup", paramGrpName, "headerName",
							paramName), atColumn("headerGroup")));
			sleep(1);
			return clickSaveButton();
		} catch (Exception e) {
			return Const.NOTSUCCESS;
		}
	}
	
	public String addNewProjTemplate(HashMap<String, String> createCriteria) {
		sleep(2);
		pMenuBar().click(atPath("Administration"));
		sleep(1);
		pMenuBar().click(atPath("Administration->Projects->Templates"));
		Utils.waitForScreenToLoad("Project Templates Select", 10);
		clickNewButton();
		Utils.waitForScreenToLoad("Create Project Templates", 10);
		if (createCriteria.get("TemplateName")!= null){
			projectTemplateName().setText(createCriteria.get("TemplateName"));
			sleep(1);
		}else{
			return "Please provide TemplateName";
		}
		if (createCriteria.get("TemplateType")!= null){
			projectTemplateType().click();
			sleep(1);
			projectTemplateType().click(atText(createCriteria.get("TemplateType")));
			sleep(1);
		}else{
			projectTemplateType().click();
			sleep(1);
			projectTemplateType().click(atText("LINE"));
			sleep(1);
		}
		Utils.getGuiTestObjByToolTip("Create a New Object [Alt+E]").click();
		sleep(10);
		if (Utils.waitForScreenToLoad("Error", 60)) {
			((GuiTestObject) Utils.getObj("accessibleContext.accessibleName",
					"OK", "name", "OptionPane.button")).click();
		}
		if (error().exists()) {
			((GuiTestObject) Utils.getObj("accessibleContext.accessibleName",
					"OK", "name", "OptionPane.button")).click();
		}else if (systemInformation().exists()){
			String errormsg = Utils.removeHtml(_htmlNoDataRecordFoundHtml().getProperty("text").toString());
			ok3().click();
			Verify.failDueToUnexpectedError(errormsg);
			Utils.closeAll();
			return errormsg;
		}
		
		Utils.waitForScreenToLoad("Project Template  -  ", 20);
		
//		Add new MileStones
		if (createCriteria.get("MileStones") != null) {
			roles().click(atText("Milestones"));
			sleep(10);
			String[] milestones = createCriteria.get("MileStones").split(":");
			for (int i = 0; i < milestones.length; i++) {
				addMilestone().click();
				Utils.waitForScreenToLoad("Milestones Choice", 20);
				((TextGuiSubitemTestObject) Utils.getObj("name",
						".proDBTextFieldName")).setText(milestones[i]);
				sleep(1);
				((GuiTestObject) Utils.getObj("name", ".jButtonSelect"))
						.click();
				sleep(10);
				if (milestones[i].contains("%") || jDialog().exists()) {
					sleep(10);
				} else {
					Utils.waitForScreenToClose("Milestones Choice", 120);
				}
				if (jDialog().exists()) {
					String message = (String) _htmlNoDataRecordFoundHtml2()
							.getProperty("text");
					System.out.println(message);
					ok().click();
					sleep(5);
					if (message.contains("No data record found!")) {
						Verify.failDueToUnexpectedError(milestones[i]
								+ " Given Milestone is not found");
						closeAltL().click();
					}
				}
				if (assembleyGroupListTable().exists()) {
					assembleyGroupListTable().click(
							atCell(atRow(atIndex(0)), atColumn("tableName")));
					sleep(1);
					((GuiTestObject) Utils.getObj("name", ".jButtonChoose"))
							.click();
					sleep(10);
				}
				clickSaveButton();
			}
		}
		
//		add new Rules
		if (createCriteria.get("RuleLHSMilestone") != null) {
			sleep(2);
			roles().click(atText("Milestones"));
			sleep(10);
			GuiSubitemTestObject milestoneTable = ((GuiSubitemTestObject) Utils
					.getObj(".class",
							"com.tmobile.itnetdev.fixedbuild.common.gui.components.treetable.ProTreeTable"));
			myTable = (ITestDataTable) milestoneTable.getTestData("contents");
			for (int row = 0; row < myTable.getRowCount(); row++) {
				String temp = myTable.getCell(row, 0).toString();
				if (temp.trim().equalsIgnoreCase(createCriteria.get("RuleLHSMilestone"))) {
					milestoneTable.click(atCell(atRow(atIndex(row)),atColumn(atIndex(0))));
					sleep(5);
					break;
				}
			}
			sleep(2);
			milestoneRule().click();
			Utils.waitForScreenToLoad("Rule Editor -", 20);
			TextSelectGuiSubitemTestObject ruleParameter = ((TextSelectGuiSubitemTestObject) Utils
					.getObj(".priorLabel", "Parameter/Constant:"));
			TextSelectGuiSubitemTestObject ruleOperation = ((TextSelectGuiSubitemTestObject) Utils
					.getObj("accessibleContext.accessibleName",
							"Operation:", ".class",
							"com.tmobile.itnetdev.fixedbuild.common.gui.components.ProComboBox"));
			
			if (createCriteria.get("SpecialCase") != null){
				// this is exception for adding long rule condition required in TC#5122
				if (createCriteria.get("RuleLHSOperation") != null) {
					ruleOperation.click();
					sleep(1);
					ruleOperation.click(atText(createCriteria.get("RuleLHSOperation")));
					sleep(1);
					value().click();
					sleep(1);
					value().click(atText("Real Date"));
				}
				sleep(2);
				Utils.getGuiTestObjByToolTip("Add [Alt+I]").click();
				sleep(2);
				((GuiSubitemTestObject) Utils.getObj("name",".sheetConfig" )).click(atText("Rule Text"));
				sleep(1);
				Utils.getGuiTestObjByToolTip("Clear Rule Editor [Alt+C]").click();
				sleep(1);
				((TextGuiSubitemTestObject) Utils
						.getObj(".class",
								"com.tmobile.itnetdev.fixedbuild.common.gui.components.ProTextArea"))
						.setText(createCriteria.get("RuleText"));
				sleep(2);
			}else if (createCriteria.get("RuleText") != null) {
				((GuiSubitemTestObject) Utils.getObj("name",".sheetConfig" )).click(atText("Rule Text"));
				sleep(2);
				((TextGuiSubitemTestObject) Utils
						.getObj(".class",
								"com.tmobile.itnetdev.fixedbuild.common.gui.components.ProTextArea"))
						.setText(createCriteria.get("RuleText"));
				sleep(2);
			} else {
				if (createCriteria.get("RuleLHSParameter") != null) {
					ruleParameter.click();
					sleep(1);
					ruleParameter.click(atText(createCriteria.get("RuleLHSParameter")));
				} else {
					ruleParameter.click();
					sleep(1);
					ruleParameter.click(atText("Plan Date"));
				}
				if (createCriteria.get("RuleLHSOperation") != null) {
					ruleOperation.click();
					sleep(1);
					ruleOperation.click(atText(createCriteria.get("RuleLHSOperation")));
					sleep(1);
				} else {
					ruleOperation.click();
					sleep(1);
					ruleOperation.click(atText("IS NOT NULL"));
					sleep(1);
				}
				((GuiTestObject) Utils.getObj("toolTipText", "Add [Alt+I]")).click();
				sleep(1);
			}
			if (createCriteria.get("RuleName") != null) {
				((TextGuiSubitemTestObject) Utils.getObj(".priorLabel",
						"Name*:")).setText(createCriteria.get("RuleName"));
				sleep(1);
				((TextGuiSubitemTestObject) Utils.getObj(".priorLabel","Description:"))
						.setText(createCriteria.get("RuleName"));
			} else {
				((TextGuiSubitemTestObject) Utils.getObj(".priorLabel",
						"Name*:")).setText("RuleName");
				sleep(1);
				((TextGuiSubitemTestObject) Utils.getObj(".priorLabel",
						"Description:")).setText("RuleDesc");
			}
			sleep(1);
			((GuiTestObject) Utils.getObj("toolTipText", "Save [Alt+A]")).click();
			sleep(10);
			((GuiTestObject) Utils.getObj("toolTipText", "Close [Alt+L]")).click();
			/*milestoneTable = ((GuiSubitemTestObject) Utils
					.getObj(".class",
							"com.tmobile.itnetdev.fixedbuild.common.gui.components.treetable.ProTreeTable"));
			myTable = (ITestDataTable) milestoneTable.getTestData("contents");
			for (int row = 0; row < myTable.getRowCount(); row++) {
				String temp = myTable.getCell(row, 1).toString();
				System.out.println(temp);
			}*/
		} 
		if (createCriteria.containsKey("PlausiType")) {
			plausiType().click();
			sleep(2);
			plausiType().click(atText(createCriteria.get("PlausiType")));
			sleep(5);
			clickSaveButton();
		}
		if (createCriteria.containsKey("Roles")) {
			roles().click(atText("Roles"));
			sleep(10);
			availableRoles().click(atText(createCriteria.get("Roles")));
			sleep(2);
			addRole().click();
			clickSaveButton();
		}
//		NOT Required to add new Template
		/*administerNetworkElements().click(atPoint(516,6));
		insertTemplate().click();
		configurationsAssemblyGroupsCh().click(atPoint(323,18));
		selectAltS3().click();
		type().click();
		type().setState(Action.vScroll(352));
		type().click(atText("LINE"));
		selectAltS3().click();
		assembleyGroupListTable().click(
				atCell(atRow("tableName", "Line Ordering", "tableType", "LINE",
						"tableDescription", "Line Ordering"),
						atColumn("tableName")), atPoint(90, 11));
		choose2().click();
		jDialog3().click(atPoint(245,15));
		yes2().click();*/
		return Const.SUCCESSMSG;
	}

	public String updateProjTemplateRule(
			HashMap<String, String> createCriteria, boolean removeOldOnes) {
		Utils.waitForScreenToLoad("Project Template  -  ", 10);
		if (createCriteria.get("RuleLHSMilestone") != null) {
			GuiSubitemTestObject milestoneTable = ((GuiSubitemTestObject) Utils
					.getObj(".class",
							"com.tmobile.itnetdev.fixedbuild.common.gui.components.treetable.ProTreeTable"));
			myTable = (ITestDataTable) milestoneTable.getTestData("contents");
			for (int row = 0; row < myTable.getRowCount(); row++) {
				String temp = myTable.getCell(row, 0).toString();
				if (temp.trim().equalsIgnoreCase(createCriteria.get("RuleLHSMilestone"))) {
					milestoneTable.click(atCell(atRow(atIndex(row)),atColumn(atIndex(0))));
					sleep(5);
					break;
				}
			}
			milestoneRule().click();
			Utils.waitForScreenToLoad("Rule Editor -", 20);
			GuiTestObject clearButton = ((GuiTestObject) Utils.getObj(
					"toolTipText", "Clear Rule Editor [Alt+C]"));
			if (createCriteria.get("RuleText") != null) {
				((GuiSubitemTestObject) Utils.getObj("name",".sheetConfig" )).click(atText("Rule Text"));
				sleep(2);
				((TextGuiSubitemTestObject) Utils
						.getObj(".class",
								"com.tmobile.itnetdev.fixedbuild.common.gui.components.ProTextArea"))
						.setText(createCriteria.get("RuleText"));
				/*_htmlMileStoneVeri8210_Hochbau().setText(
						createCriteria.get("RuleText"));*/
			} else {
				TextSelectGuiSubitemTestObject ruleParameter = ((TextSelectGuiSubitemTestObject) Utils
						.getObj(".priorLabel", "Parameter/Constant:"));
				TextSelectGuiSubitemTestObject ruleOperation = ((TextSelectGuiSubitemTestObject) Utils
						.getObj("accessibleContext.accessibleName",
								"Operation:", ".class",
								"com.tmobile.itnetdev.fixedbuild.common.gui.components.ProComboBox"));
				if (createCriteria.get("RuleLHSParameter") != null) {
					ruleParameter.click();
					sleep(1);
					ruleParameter.click(atText(createCriteria.get("RuleLHSParameter")));
				} else {
					ruleParameter.click();
					sleep(1);
					ruleParameter.click(atText("Plan Date"));
				}
				sleep(5);
				if (removeOldOnes && clearButton != null) {
					clearButton.click();
					sleep(2);
				}
				if (createCriteria.get("RuleLHSOperation") != null) {
					ruleOperation.click();
					sleep(1);
					ruleOperation.click(atText(createCriteria.get("RuleLHSOperation")));
					sleep(1);
				} else {
					ruleOperation.click();
					sleep(1);
					ruleOperation.click(atText("IS NOT NULL"));
					sleep(1);
				}
				((GuiTestObject) Utils.getObj("toolTipText", "Add [Alt+I]")).click();
				sleep(1);
			}
			if (createCriteria.get("RuleName") != null) {
				((TextGuiSubitemTestObject) Utils.getObj(".priorLabel",
						"Name*:")).setText(createCriteria.get("RuleName"));
				sleep(1);
				((TextGuiSubitemTestObject) Utils.getObj(".priorLabel","Description:"))
						.setText(createCriteria.get("RuleName"));
			} 
			sleep(1);
			((GuiTestObject) Utils.getObj("toolTipText", "Save [Alt+A]")).click();
			sleep(10);
			((GuiTestObject) Utils.getObj("toolTipText", "Close [Alt+L]")).click();
			return Const.SUCCESSMSG;
		} else {
			return "Please provide milestone name as \"RuleLHSMilestone\", \"ruleLHSMilestone\"";
		}
	}

	public String searchAndOpenProjTemplateByName(String projectTemplateName) {
		sleep(2);
		pMenuBar().click(atPath("Administration"));
		sleep(1);
		pMenuBar().click(atPath("Administration->Projects->Templates"));
		Utils.waitForScreenToLoad("Project Templates Select", 10);
		((TextGuiSubitemTestObject) Utils.getObj("name",
				".TextFieldProjectTemplateName")).setText(projectTemplateName);
		sleep(1);
		clickSelect();
		sleep(10);
		if (Utils.getDialogTitles().contains(
				("Project Templates Select").toLowerCase())) {
			GuiSubitemTestObject projTemplateTable = ((GuiSubitemTestObject) Utils
					.getObj(".class",
							"com.tmobile.itnetdev.fixedbuild.nevbz.presentation.framework.NeVbzTable$Table"));
			projTemplateTable.click(atCell(atRow(atIndex(0)),
					atColumn(atIndex(0))));
			sleep(1);
			((GuiTestObject) Utils.getObj("name", ".ButtonEdit")).click();
		}
		if (Utils.waitForScreenToLoad("Project Template  -  ", 10)){
			return Const.SUCCESSMSG;
		}else{
			return "Unable to Open Project Template screen";
		}
	}

	public String selectMileStoneFromProjTempTreeTable(String ruleLHSMilestone) {
		sleep(2);
		GuiSubitemTestObject milestoneTable = ((GuiSubitemTestObject) Utils
				.getObj(".class",
						"com.tmobile.itnetdev.fixedbuild.common.gui.components.treetable.ProTreeTable"));
		myTable = (ITestDataTable) milestoneTable.getTestData("contents");
		for (int row = 0; row < myTable.getRowCount(); row++) {
			String temp = myTable.getCell(row, 0).toString();
			if (temp != null && temp.trim().equalsIgnoreCase(ruleLHSMilestone)) {
				milestoneTable.click(atCell(atRow(atIndex(row)),atColumn(atIndex(0))));
				sleep(5);
				return Const.SUCCESSMSG;
			}
		}
		return "Given Milestone '" + ruleLHSMilestone + "' not found";
	}

	public String assignRoleToPersonWcreditorNumber(String personName,
			String whichRole, String creditorNo) {
		Utils.closeAll();
		sleep(2);
		pMenuBar().click(atPath("Administration"));
		sleep(1);
		pMenuBar().click(atPath("Administration->Persons"));
		sleep(2);
		Utils.waitForScreenToLoad("Persons Select", 60);
		Utils.getTextGuiSubitemByName(".proDBTextFieldName")
				.setText(personName);
		sleep(1);
		Utils.getGuiTestObjByToolTip("Select [Alt+S]").click();
		if (!Utils.waitForScreenToLoad("person  -  ", 60)) {
//		_htmlTest_adminHtml().click(atCell(atRow("headerName", "test_admin","headerFirstName", "PEGA","headerPLZ", "12345"),atColumn("headerName")));
			_htmlTest_adminHtml().click(
					atCell(atRow(atIndex(0)), atColumn("headerName")));
			sleep(2);
			Utils.getGuiTestObjByName(".jButtonEdit").click();
			Utils.waitForScreenToLoad("person  -  ", 60);
		}

		boolean found = false;
		ITestDataList nameList = (ITestDataList) available().getTestData("list");
		ITestDataElementList nameListElements = nameList.getElements();

		int listElemCount = nameList.getElementCount();
		ITestDataElement nameListElement;
		for (int i = 0; i < listElemCount; i++) {
			nameListElement = nameListElements.getElement(i);
			if (nameListElement.getElement().toString()
					.equalsIgnoreCase(whichRole)) {
				available().click(atText(whichRole));
				sleep(2);
				assignRole().click();
				sleep(2);
				found = true;
				break;
			}
		}
		creditorNo().setText(creditorNo);
		sleep(2);
		saveConfiguration(false);
		if (!found){
			System.out.println("Given Role '"+whichRole+"' already assigned or not present");
			return "Given Role '"+whichRole+"' already assigned or not present";
		}else {
			return Const.SUCCESSMSG;
		}
	}
}
