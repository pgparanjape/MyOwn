package PB_2015_2.FB.Actions;

import java.util.ArrayList;
import java.util.HashMap;

import resources.PB_2015_2.FB.Actions.ProjectActionsHelper;
import PB_2015_2.Common.Const;
import PB_2015_2.Common.Utils;

import com.rational.test.ft.object.interfaces.GuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.GuiTestObject;
import com.rational.test.ft.object.interfaces.SelectGuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.TextGuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.TextSelectGuiSubitemTestObject;
import com.rational.test.ft.vp.ITestDataElement;
import com.rational.test.ft.vp.ITestDataElementList;
import com.rational.test.ft.vp.ITestDataList;
import com.rational.test.ft.vp.ITestDataProperties;
import com.rational.test.ft.vp.ITestDataTable;

public class ProjectActions extends ProjectActionsHelper {

	private ITestDataTable myTable;
	private String indentCode = Const.BLANK;
	public String searchProjectNo = Const.BLANK;
	private String returnMessage = Const.SUCCESSMSG;
	public String searchProjectTemplate = Const.BLANK;
	
	public ProjectActions() {}

	public ProjectActions(String indentCode) {
		this.indentCode = indentCode;
	}
	
	public void openProjectScreenOnly(){
		sleep(2);
		pMenuBar().click(atPath("Projects"));
		sleep(1);
		pMenuBar().click(atPath("Projects->Edit"));
		sleep(2);
		Utils.waitForScreenToLoad("Projects select", 30);
	}
	
	public String searchProject() {
		if (!Utils.waitForScreenToLoad("Projects select", 5)){
			openProjectScreenOnly();
		}
		Utils.getGuiTestObjByToolTip("Clear All Filters [Alt+Z]").click();
		sleep(2);
		if (!indentCode.isEmpty()) {
			TextGuiSubitemTestObject indentCodeTextField = (TextGuiSubitemTestObject) Utils.getObj("name", ".proDBTextFieldObjectPanelLocationId1");
			indentCodeTextField.click(atPoint(30, 10));
			indentCodeTextField.setText(indentCode);
			sleep(1);
		}
		if (!searchProjectNo.isEmpty()) {
			TextGuiSubitemTestObject projectNo = getTextFieldByName(".proDBTextFieldProjectPanelProjectNumber");
			projectNo.click(atPoint(43, 11));
			projectNo.setText(searchProjectNo);
			sleep(1);
		}
		if (!searchProjectTemplate.isEmpty()) {
			template().click();
			sleep(1);
			template().click(atText(searchProjectTemplate));
			sleep(1);
		}
		
		Utils.getGuiTestObjByName(".jButtonSelect").click(); 
		sleep(10);
		if (information().exists()) {
			yes2().click();
			sleep(5);
		}
		Utils.waitForScreenToClose("Dauer:", 30);
		sleep(5);
		if (noDataFoundErrorDialog().exists()) {
			returnMessage = ((String) _htmlNoDataRecordFoundHtml().getProperty(
					"text")).trim();
			ok().click();
			sleep(10);
		}
		if (returnMessage.equalsIgnoreCase("More than 200 records found.")) {
			return Const.SUCCESSMSG;
		} else {
			return returnMessage;
		}
	}
	
	public String[] getValueFromProjectList(int whichRow, String[] whichColumns) {
		sleep(2);
		String[] columnValues = new String [whichColumns.length];
		GuiTestObject projectListTable = (GuiTestObject) Utils.getObj(".class","com.tmobile.itnetdev.fixedbuild.nevbz.presentation.framework.NeVbzTable$Table");
		myTable = (ITestDataTable) projectListTable.getTestData("contents");
		for (int i = 0; i < whichColumns.length; i++) {
			for (int j = 0; j < myTable.getColumnCount(); j++) {
				String columnName = ((String) myTable.getColumnHeader(j)).toLowerCase();
				if (columnName.contains(whichColumns[i].toLowerCase())) {
					columnValues[i] = (String) myTable.getCell(whichRow, j);
				}
			}
		}
		return columnValues;
	}

	public void closeEditProjScreen() {
		sleep(2);
		editProjCloseButton().click();
	}
	
	public void closeListScreen() {
		sleep(2);
		listDialogCloseBTN().click();
	}

	public void openProjFromProjList(int whichRow) {
		Utils.waitForScreenToLoad("Projects select", 30);
		GuiSubitemTestObject projectListTable = (GuiSubitemTestObject)Utils.getObj(".class","com.tmobile.itnetdev.fixedbuild.nevbz.presentation.framework.NeVbzTable$Table");
		projectListTable.click(atCell(atRow(atIndex(whichRow)), atColumn(atIndex(1))));
		sleep(1);
		editAltF().click();
		sleep(10);
		Utils.waitForScreenToLoad("Project  -  ", 10);
//		administerNetworkElements().click(atPoint(605,16));
		sleep(1);
	}
	
	private TextGuiSubitemTestObject getTextFieldByName(String textFieldName) {
		TestObject[] textFields = find(atDescendant("name", textFieldName));
		for (int i = 0; i < textFields.length; i++) {
			TextGuiSubitemTestObject textField = (TextGuiSubitemTestObject) textFields[i];
			try {
				if (textField.isShowing() && textField.exists()) {
					sleep(2);
					return textField;
				}
			} catch (Exception e) {}
		}
		return null;
	}

	public String createNewProject(HashMap<String, String> createCriteria) {
		if (createCriteria.containsKey("fromNEScreen")) {
			GuiSubitemTestObject tab = (GuiSubitemTestObject) Utils.getObj("name",".sheetConfig");
			tab.click(atText("Projects"));
			sleep(10);
			Utils.getGuiTestObjByToolTip("New [Alt+N]").click();
		}else if (createCriteria.containsKey("fromNEScreenHWPurchases")) {
			GuiSubitemTestObject tab = (GuiSubitemTestObject) Utils.getObj("name",".sheetConfig");
			tab.click(atText("HW Purchases"));
			sleep(10);
			Utils.getGuiTestObjByToolTip("New").click();
		}else{
			openProjectScreenOnly();
			Utils.getGuiTestObjByToolTip("New").click();
			
		}
		Utils.waitForScreenToLoad("Create Project", 60);
		Utils.getGuiTestObjByName(".detailButtonProjectTemplate" ).click();
		Utils.waitForScreenToLoad("project templates choice", 30);
		sleep(2);
		if (createCriteria.containsKey("template")) {
			((TextGuiSubitemTestObject) Utils.getObj("name",
					".TextFieldProjectTemplateName")).setText(createCriteria.get("template"));
		}
		Utils.getGuiTestObjByName(".ButtonSelect").click();
		sleep(10);
		if (ProjectTemplateList().exists()&& Utils.getDialogTitles().contains("project templates choice")){
			if (noDataFoundErrorDialog().exists()) {
				String errorMsgTxt = (String) _htmlNoDataRecordFoundHtml()
						.getProperty("text");
				if (errorMsgTxt
						.equalsIgnoreCase("More than 200 records found.")) {
					ok().click();
					sleep(2);
				}
			}
			ProjectTemplateList().click(atCell(atRow(atIndex(0)), atColumn(atIndex(0))));
			sleep(2);
			Utils.getGuiTestObjByName(".ButtonChoose").click();
			sleep(2);
		}else if (noDataFoundErrorDialog().exists()){
				String errorMsgTxt = (String)_htmlNoDataRecordFoundHtml().getProperty("text");
				ok().click();
				return Utils.removeHtml(errorMsgTxt);
		}
		if (createCriteria.containsKey("package")) {
			((GuiTestObject) Utils.getObj("name",".detailButtonProjectPackage")).click();
			Utils.waitForScreenToLoad("project packages choice", 30);
			((TextGuiSubitemTestObject) Utils.getObj("name",".proDBTextFieldName")).setText(createCriteria.get("package"));
			Utils.getGuiTestObjByName(".jButtonSelect").click();
			sleep(10);
			if (noDataFoundErrorDialog().exists()) {
				ok().click();
			}
			if (btsAnbindungsprojekt_BLN().exists()) {
				btsAnbindungsprojekt_BLN().click(atCell(atRow(atIndex(0)), atColumn(atIndex(0))));
				((GuiTestObject) Utils.getObj("toolTipText","Choose [Alt+C]")).click();
			}
		}
		sleep(2);
		if (!createCriteria.containsKey("fromNEScreenHWPurchases")
				&& !createCriteria.containsKey("fromNEScreen")) {
			TextSelectGuiSubitemTestObject projectObject = (TextSelectGuiSubitemTestObject) Utils
					.getObj("name", ".jComboBoxObject");
			projectObject.click();
			sleep(1);
			if (createCriteria.containsKey("object")) {
				projectObject.click(atText(createCriteria.get("object")));
			} else {
				projectObject.click(atText("Network Elements"));
			}
			sleep(2);
			// Addition of Object ID depending on the Template
			Utils.getGuiTestObjByName(".detailButtonObjectId").click();
			if (!createCriteria.containsKey("object")
					|| createCriteria.get("object").equalsIgnoreCase(
							"Network Elements")) { // By default selecting NE
				Utils.waitForScreenToLoad("choose network element", 30);
				if (createCriteria.get("NERegion") != null) {
					TextSelectGuiSubitemTestObject neRegions = (TextSelectGuiSubitemTestObject) Utils
							.getObj("name", ".comboNiederlassung");
					neRegions.click();
					sleep(1);
					neRegions.click(atText(createCriteria.get("NERegion")));
				}
				if (createCriteria.containsKey("IndentCode")) {
					((TextGuiSubitemTestObject) Utils.getObj("name",
							".edStoKennung")).setText(createCriteria
							.get("IndentCode"));
					sleep(1);
				}
				if (createCriteria.containsKey("NEID")) {
					((TextGuiSubitemTestObject) Utils.getObj("name", ".edNEID"))
							.setText(createCriteria.get("NEID"));
					sleep(1);
				}
				Utils.getGuiTestObjByToolTip("Select [Alt+S]").click();
				sleep(10);
				if (information().exists()){
					yes2().click();
					sleep(5);
				}
				Utils.waitForScreenToClose(Const.DURATION, 60);
				/*for (int i = 0; i < 12 && durationWaitDialog().exists(); i++) {
					sleep(5);
				}*/
				GuiSubitemTestObject neListTable = (GuiSubitemTestObject) Utils
						.getObj("name", ".tabSelektion");
				neListTable.click(
						atCell(atRow(atIndex(0)), atColumn("NE_Typ")),
						atPoint(55, 10));
				Utils.getGuiTestObjByName(".btAuswaehlen").click();
				sleep(2);
			} else if (createCriteria.get("object").equalsIgnoreCase("Lines")) {
				Utils.waitForScreenToLoad("choose Lines", 30);
				TextGuiSubitemTestObject indentCode = ((TextGuiSubitemTestObject) Utils
						.getObj("name", ".edStandortNrA"));
				sleep(1);
				if (createCriteria.containsKey("IndentCode")) {
					indentCode.setText(createCriteria.get("IndentCode"));
				} else {
					indentCode.setText(Const.INDENTCODE);
				}
				Utils.getGuiTestObjByToolTip("Select [Alt+S]").click();
				sleep(10);
				for (int i = 0; i < 6 && durationWaitDialog().exists(); i++) {
					sleep(10);
				}
				GuiSubitemTestObject neListTable = (GuiSubitemTestObject) Utils
						.getObj(".class",
								"com.tmobile.itnetdev.fixedbuild.common.gui.components.table.ProTable");
				if (neListTable != null) {
					neListTable.click(atCell(atRow(atIndex(0)),
							atColumn(atIndex(0))));
					Utils.getGuiTestObjByToolTip("Choose [Alt+C]").click();
					sleep(2);
				}
			} else if (createCriteria.get("object").equalsIgnoreCase(
					"Microwaves")) {
				Utils.waitForScreenToLoad("Choose Microwave", 60);
				Utils.getTextGuiSubitemByName(".edName").setText(
						createCriteria.get("Name"));
				sleep(1);
				Utils.getGuiTestObjByToolTip("Select [Alt+S]").click();
				sleep(10);
				Utils.waitForScreenToClose(Const.DURATION, 60);
				if (noDataFoundErrorDialog().exists())
					ok2().click();
				GuiSubitemTestObject microwaveListTable = (GuiSubitemTestObject) Utils
						.getObj(".class",
								"com.tmobile.itnetdev.fixedbuild.common.gui.components.table.ProTable");
				if (microwaveListTable != null) {
					microwaveListTable.click(atCell(atRow(atIndex(0)),
							atColumn(atIndex(0))));
					Utils.getGuiTestObjByToolTip("Choose [Alt+C]").click();
					sleep(2);
				}
			} else if (createCriteria.get("object").equalsIgnoreCase(
					"External Services")) {
				Utils.waitForScreenToLoad("Select External Services", 60);
				if (createCriteria.containsKey("ExternalServiceID")) {
					Utils.getTextGuiSubitemByName(".extServiceId").setText(
							createCriteria.get("ExternalServiceID"));
				}
				if (createCriteria.containsKey("SiteAltIndentCode")) {
					indentCode().setText(
							createCriteria.get("SiteAltIndentCode"));
				}
				if (createCriteria.containsKey("ProjectIndentCode")) {
					indentCode2().setText(
							createCriteria.get("ProjectIndentCode"));
				}

				Utils.getGuiTestObjByToolTip("Select [Alt+S]").click();
				sleep(5);
				Utils.waitForScreenToClose(Const.DURATION, 60);
				sleep(5);
				if (information().exists()) {
					ok().click();
					sleep(2);
				}
				if (ExternalServiceTypeListTable().exists()) {
					ExternalServiceTypeListTable().doubleClick(
							atCell(atRow(atIndex(0)), atColumn(atIndex(0))));
					sleep(2);
				}
			}
		}
		// addition of Region
		if (createCriteria.containsKey("Region")) {
			pListOfValuesLOVBasicArrowButt().click();
			sleep(2);
			jTable3().click(
					atCell(atRow("Region", createCriteria.get("Region")),
							atColumn("Region")), atPoint(31, 4));
			sleep(2);
		}
		
//		addition of budget Type
		if (createCriteria.containsKey("BudgetType")) {
			pListOfValuesLOVBasicArrowButt2().click();
			sleep(1);
			jTable2().click(atCell(atRow(createCriteria.get("BudgetType")), atColumn("Long text")),atPoint(60,14));
			sleep(2);
		}
		Utils.getGuiTestObjByName(".jButtonEdit").click();
		if (createCriteria.containsKey("fromNEScreenHWPurchases")) {
			sleep(20);
			return Const.SUCCESSMSG;
		} else if (Utils.waitForScreenToLoad("project  -  ", 120)) {
			return projectNo2().getText().toString();
		} else {
			return Const.NOTSUCCESS;
		}
	}

	public void closeAll() {
		Utils.closeAll();
	}

	public String deleteProject() {
		sleep(2);
		Utils.waitForScreenToLoad("project  -  ", 60);
		sleep(1);
		Utils.getGuiTestObjByName(".jButtonDelete").click();
		sleep(5);
		Utils.waitForScreenToLoad("Perform action", 20);
		sleep(2);
		yes().click();
		sleep(2);
		Utils.waitForScreenToClose("project  -  ", 60);
		return Const.SUCCESSMSG;
	}

	public String addParam(String paramGrpName, String paramName) {
		Utils.waitForScreenToLoad("Project ", 30);
		((GuiSubitemTestObject) Utils.getObj("name", ".jTabbedPaneProject"))
				.click(atText("Parameter"));
		sleep(2);
		Utils.getGuiTestObjByToolTip("New [Alt+N]").click();
		Utils.waitForScreenToLoad("Parameters Choice", 30);
		sleep(1);
		if (!paramGrpName.isEmpty()){
		parameterGroup().click();
		sleep(1);
		parameterGroup().click(atText(paramGrpName));
		sleep(1);
		}
		((TextGuiSubitemTestObject) Utils.getObj(".priorLabel", "Name:",
				".classIndex", "0")).setText(paramName);
		sleep(1);
		selectAltS().click();
		sleep(30);
		if (noDataFoundErrorDialog().exists()) {
			returnMessage = ((String) _htmlNoDataRecordFoundHtml().getProperty(
					"text")).trim();
			sleep(2);
			ok().click();
			sleep(2);
			closeAltL().click();
			return Utils.removeHtml(returnMessage);
		}
		if (_7995G6G().exists()) {
			_7995G6G().click(atCell(atRow(atIndex(0)), atColumn("headerName")));
			sleep(1);
			Utils.getGuiTestObjByName(".ButtonChoose").click();
			Utils.waitForScreenToClose("Parameters Choice", 30);
		}
		return saveProject();
	}
	
	public ArrayList<String> getParameterValue(String whichParam) {
		ArrayList<String> retArrayList = new ArrayList<String>();
		Utils.waitForScreenToLoad("Project ", 30);
		((GuiSubitemTestObject) Utils.getObj("name", ".jTabbedPaneProject"))
				.click(atText("Parameter"));
		sleep(2);
		GuiSubitemTestObject paramTable = (GuiSubitemTestObject) Utils
				.getObj(".class",
						"com.tmobile.itnetdev.fixedbuild.nevbz.presentation.project.ProjectParamPanel$1");
		ITestDataTable myTable = (ITestDataTable) paramTable
				.getTestData("contents");
		for (int row1 = 0; row1 < myTable.getRowCount(); row1++) {
			String temp = (String) myTable.getCell(row1, 1);
			if (temp.equalsIgnoreCase(whichParam)) {
				for (int col = 0; col < myTable.getColumnCount(); col++) {
					try {
						String paramValue = (String) myTable.getCell(row1, col);
						retArrayList.add(paramValue);
					} catch (Exception e) {
					}
				}
				break;
			}
		}
		return retArrayList;
	}
	
	public String updateParameterValue(String whichParam, String whatValue) {
		Utils.waitForScreenToLoad("Project ", 30);
		((GuiSubitemTestObject) Utils.getObj("name", ".jTabbedPaneProject")).click(atText("Parameter"));
		sleep(2);
		GuiSubitemTestObject paramTable = (GuiSubitemTestObject) Utils
				.getObj(".class",
						"com.tmobile.itnetdev.fixedbuild.nevbz.presentation.project.ProjectParamPanel$1");
		ITestDataTable myTable = (ITestDataTable) paramTable.getTestData("contents");
		String[] keys = whichParam.split(":");
		String[] values = whatValue.split(":");
		for (int i = 0; i < keys.length; i++) {
			for (int row = 0; row < myTable.getRowCount(); row++) {
				String temp = (String) myTable.getCell(row, 1);
				if (temp.equalsIgnoreCase(keys[i])) {
					paramTable.doubleClick(atCell(atRow(atIndex(row)),atColumn(atIndex(3))));
					sleep(5);
					if (neTableComboBox().exists()) {
						neTableComboBox().click();
						sleep(2);
						_2().click(atCell(atRow("headerValues", values[i]),atColumn("headerValues")));
						sleep(2);
					} else {
						mainFrame().inputKeys(values[i]);
						sleep(5);
						mainFrame().inputKeys("{TAB}");
						sleep(5);
					}
					paramTable.click(atCell(atRow(atIndex(row)),atColumn(atIndex(0))));
					sleep(1);
					paramTable.click(atCell(atRow(atIndex(row)),atColumn(atIndex(0))));
					sleep(5);
					break;
				}
			}
		}
		return saveProject();
	}

	public String saveProject() {
		if (saveAltR().exists()) {
			saveAltR().click();
			sleep(20);
			String ChangesSaved_text = (String) changesSaved().getProperty("text");
			ChangesSaved_text = Utils.removeHtml(ChangesSaved_text);
			for (int i = 0; i < 60
					&& !Const.CHANGESSAVED.contains(ChangesSaved_text); i = i + 10) {
				sleep(10);
				ChangesSaved_text = Utils.removeHtml((String) changesSaved()
						.getProperty("text"));
			}
			sleep(2);
			if (Const.CHANGESSAVED.contains(ChangesSaved_text)) {
				return Const.SUCCESSMSG;
			} else {
				return Const.NOTSUCCESS;
			}
		} else {
			return Const.NOTSUCCESS;
		}
	}

	public String updateMilestoneDate(String milestoneName, String value, String whichDate) {
		Utils.waitForScreenToLoad("project  -  ", 10);
		GuiSubitemTestObject milestoneTreeTable = ((GuiSubitemTestObject) Utils
				.getObj(".class",
						"com.tmobile.itnetdev.fixedbuild.nevbz.presentation.framework.NeVbzTreeTable$Table"));
		myTable = (ITestDataTable) milestoneTreeTable.getTestData("contents");
		for (int row = 0; row < myTable.getRowCount(); row++) {
			String temp = myTable.getCell(row, 1).toString();
			if (temp.trim().equalsIgnoreCase(milestoneName)) {
				milestoneTreeTable.click(atCell(atRow(atIndex(row)),atColumn(atIndex(1))));
				sleep(2);
				break;
			}
		}
		((GuiSubitemTestObject) Utils.getObj("name", ".jTabbedPaneProject")).click(atText("Milestone"));
		sleep(5);
		
		if (whichDate.equalsIgnoreCase("Plan Date"))
		((TextGuiSubitemTestObject) Utils.getObj("name",".proDateFieldMilestonePlan")).setText(value);
		else if (whichDate.equalsIgnoreCase("Real Date"))
			((TextGuiSubitemTestObject) Utils.getObj("name",".proDateFieldMilestoneActual")).setText(value);
		else if(whichDate.equalsIgnoreCase("Target Date"))
			((TextGuiSubitemTestObject) Utils.getObj("name",".proDateFieldMilestoneDebit")).setText(value);
		sleep(2);
		mainFrame().inputKeys("{TAB}");
		sleep(5);
		if (error().exists()) {
			error().click(atPoint(129, 13));
			sleep(1);
			String error = ((GuiTestObject) Utils.getObj("name",
					"OptionPane.label", ".class", "javax.swing.JLabel"))
					.getProperty("text").toString();
			sleep(1);
			((GuiTestObject) Utils.getObj("name", "OptionPane.button",
					"accessibleContext.accessibleName", "OK")).click();
//			ok2().click();
			sleep(1);
			return Utils.removeHtml(error);
		}
		((TextGuiSubitemTestObject) Utils.getObj("name",".proDateFieldMilestoneDebit")).click();
		sleep(1);
		return saveProject();
	}

	public String start(String startDate) {
		Utils.waitForScreenToLoad("project  -  ", 10);
		GuiSubitemTestObject milestoneTreeTable = ((GuiSubitemTestObject) Utils
				.getObj(".class",
						"com.tmobile.itnetdev.fixedbuild.nevbz.presentation.framework.NeVbzTreeTable$Table"));
		milestoneTreeTable.click(atCell(atRow(atIndex(0)), atColumn(atIndex(1))));
		sleep(2);
		((GuiSubitemTestObject) Utils.getObj("name", ".jTabbedPaneProject")).click(atText("Project Dates"));
		sleep(2);
		GuiTestObject startButton = Utils.getGuiTestObjByName(".jButtonProjectDatesStart");
		if (startButton != null) {
			startButton.click();
			sleep(5);
			((TextGuiSubitemTestObject) Utils.getObj("name", ".jTextFieldDate")).setText(startDate);
			sleep(1);
			Utils.getGuiTestObjByName(".jButtonStartDate").click();
			sleep(3);
			return saveProject();
		} else {
			return "project is already started";
		}
	}

	public String getMilestoneDate(String milestoneName, String whichDate) {
		Utils.waitForScreenToLoad("project  -  ", 10);
		String returnStr = Const.NOTSUCCESS;
		GuiSubitemTestObject milestoneTreeTable = ((GuiSubitemTestObject) Utils
				.getObj(".class",
						"com.tmobile.itnetdev.fixedbuild.nevbz.presentation.framework.NeVbzTreeTable$Table"));
		myTable = (ITestDataTable) milestoneTreeTable.getTestData("contents");
		for (int row = 0; row < myTable.getRowCount(); row++) {
			String temp = myTable.getCell(row, 1).toString();
			if (temp.trim().equalsIgnoreCase(milestoneName)) {
				milestoneTreeTable.click(atCell(atRow(atIndex(row)),atColumn(atIndex(1))));
				sleep(2);
				break;
			}
		}
		((GuiSubitemTestObject) Utils.getObj("name", ".jTabbedPaneProject")).click(atText("Milestone"));
		sleep(2);

		if (whichDate.equalsIgnoreCase("Plan Date"))
			returnStr = ((TextGuiSubitemTestObject) Utils.getObj("name",
					".proDateFieldMilestonePlan")).getText().toString();
		else if (whichDate.equalsIgnoreCase("Real Date"))
			returnStr = ((TextGuiSubitemTestObject) Utils.getObj("name",
					".proDateFieldMilestoneActual")).getText().toString();
		sleep(2);
		return returnStr;
	}

	public String assignTemplateSequence(String preOrSuccessor, String projectNo, String milestoneName) {
		Utils.waitForScreenToLoad("project  -  ", 10);
		((GuiSubitemTestObject) Utils.getObj("name", ".jTabbedPaneProject")).click(atText("Sequence"));
		sleep(2);
		Utils.getGuiTestObjByName(".jButtonSequenceConcatenate").click();
		Utils.waitForScreenToLoad("Projects Choice", 10);
		Utils.getTextGuiSubitemByName(".proDBTextFieldProjectPanelProjectNumber").setText(projectNo);
		sleep(1);
		Utils.getTextGuiSubitemByName(".proDBTextFieldObjectPanelLocationId1").setText("");
		sleep(1);
		Utils.getTextGuiSubitemByName(".proDBTextFieldObjectPanelLoctaion1").setText("");
		sleep(1);
		Utils.getGuiTestObjByName(".jButtonSelect").click(); 
		sleep(5);
		SelectGuiSubitemTestObject milestoneTable = ((SelectGuiSubitemTestObject) Utils
				.getObj("name",".neVbzListSequenceMSInProjectCheckBox"));
		milestoneTable.click(atText(milestoneName));
		sleep(2);
		if (preOrSuccessor.equalsIgnoreCase("Predecessor"))
			Utils.getGuiTestObjByName(".jButtonSequenceAddPredecessor").click();
		else
			Utils.getGuiTestObjByName(".jButtonSequenceAddSuccessor").click();
		sleep(2);
		return saveProject();
	}

	public String getMilestoneDate(String milestoneName, String whichDate,
			String preOrSuccessor, String preOrSuccessorMileStone) {
		Utils.waitForScreenToLoad("project  -  ", 10);
		String returnStr = Const.NOTSUCCESS;
		GuiSubitemTestObject milestoneTreeTable = ((GuiSubitemTestObject) Utils
				.getObj(".class",
						"com.tmobile.itnetdev.fixedbuild.nevbz.presentation.framework.NeVbzTreeTable$Table"));
		myTable = (ITestDataTable) milestoneTreeTable.getTestData("contents");
		for (int row = 0; row < myTable.getRowCount(); row++) {
			String temp = myTable.getCell(row, 1).toString();
			if (temp.trim().equalsIgnoreCase(milestoneName)) {
				milestoneTreeTable.click(atCell(atRow(atIndex(row)),atColumn(atIndex(1))));
				sleep(2);
				break;
			}
		}
		((GuiSubitemTestObject) Utils.getObj("name", ".jTabbedPaneProject")).click(atText("Milestone"));
		sleep(2);
		int column = 0;
		if (whichDate.equalsIgnoreCase("Plan Date")) {
			column = 1;
		} else if (whichDate.equalsIgnoreCase("Target Date")) {
			column = 2;
		}
		if (preOrSuccessor.equalsIgnoreCase("Predecessor")) {
			myTable = (ITestDataTable) _20102001().getTestData("contents");
			for (int row = 0; row < myTable.getRowCount(); row++) {
				try {
					String temp = myTable.getCell(row, 0).toString();
					if (temp.trim().equalsIgnoreCase(preOrSuccessorMileStone)) {
						returnStr = myTable.getCell(row, column).toString();
						sleep(2);
						break;
					}
				} catch (Exception e) {}
			}
		} else {
			myTable = (ITestDataTable) _20102010().getTestData("contents");
			for (int row = 0; row < myTable.getRowCount(); row++) {
				try {
					String temp = myTable.getCell(row, 0).toString();
					if (temp.trim().equalsIgnoreCase(preOrSuccessorMileStone)) {
						returnStr = myTable.getCell(row, column).toString();
						sleep(2);
						break;
					}
				} catch (Exception e) {}
			}
		}
		return returnStr;
	}

	public String personAssignmentForRoles(String role, String person) {
		Utils.waitForScreenToLoad("project  -  ", 10);
		((GuiSubitemTestObject) Utils.getObj("name", ".jTabbedPaneProject"))
				.click(atText("Roles/Persons"));
		sleep(5);
		ITestDataTable myTable;
		boolean roleExits = false;
		myTable = (ITestDataTable) gu().getTestData("contents");
		for (int row = 0; row < myTable.getRowCount(); row++) {
			String temp = (String) myTable.getCell(row, 0);
			if (temp != null && temp.equalsIgnoreCase(role)) {
				roleExits = true;
				gu().click(atCell(atRow(atIndex(row)),atColumn("tableRolesAndPersonsRole")));
				sleep(5);
				break;
			}
		}
		if (roleExits) {
			boolean found = false;
			ITestDataList nameList = (ITestDataList) _neVbzListRolesAndPersonsBranc()
					.getTestData("list");
			ITestDataElementList nameListElements = nameList.getElements();
			int listElemCount = nameList.getElementCount();
			ITestDataElement nameListElement;
			for (int i = 0; i < listElemCount; i++) {
				nameListElement = nameListElements.getElement(i);
				if (nameListElement.getElement().toString()
						.equalsIgnoreCase(person)) {
					_neVbzListRolesAndPersonsBranc().click(atText(person));
					sleep(2);
					addPerson().click();
					sleep(2);
					found = true;
					break;
				}
			}
			if (found) {
				return saveProject();
			} else {
				System.out.println("Given Person '" + person + "' does not exists please check");
				saveProject();
				return "Given Person '" + person + "' does not exists please check";
			}
		} else {
			System.out.println("Given Role '" + role + "' does not exists please check");
			return "Given Role '" + role + "' does not exists please check";
		}
	}
}
