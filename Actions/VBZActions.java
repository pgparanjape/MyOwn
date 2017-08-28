package PB_2015_2.FB.Actions;

import java.util.HashMap;

import PB_2015_2.Common.Const;
import PB_2015_2.Common.Utils;
import PB_2015_2.FB.ObjRepo.FBRepo;

import com.rational.test.ft.object.interfaces.GuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.GuiTestObject;
import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.TextGuiSubitemTestObject;
import com.rational.test.ft.vp.ITestDataTable;

public class VBZActions extends FBRepo implements FB{

	@Override
	public String search(HashMap<String, String> searchCriteria) {
		openSelectVBZDialog();
		enterSearchCriteria(searchCriteria);
		clickSearchButton();
		sleep(15);
		Utils.waitForScreenToClose("Dauer", 300);
		if (SysInfoDialog().exists()){
			String message = (String)_htmlNoDataRecordFoundHtml2().getProperty("text");
			((GuiTestObject) Utils.getObj("accessibleContext.accessibleName",
					"OK")).click();
			sleep(2);
			return Utils.removeHtml(message);
		}else if (enterConsiderationDatePlanning().exists()){
			Utils.getGuiTestObjByName(".btAbbrechen").click();
			sleep(2);
		}
		ITestDataTable myTable = (ITestDataTable)Utils.getObj(".class",				
				"com.tmobile.itnetdev.fixedbuild.common.gui.components.table.ProTable").getTestData("contents");
		if (myTable.getRowCount() >1){
			return Const.SUCCESSMSG;
		}else{
			return Const.NOTSUCCESS;
		}
	}

	public String openFromSearchList(String whichRow) {
		Utils.waitForScreenToLoad("Select VBZ", 10);
		GuiSubitemTestObject vbzListTable = (GuiSubitemTestObject)Utils.getObj(".class",				
				"com.tmobile.itnetdev.fixedbuild.common.gui.components.table.ProTable");
		vbzListTable.click(atCell(atRow(atIndex(Integer.parseInt(whichRow))), atColumn(atIndex(0))));
		sleep(1);
		Utils.getGuiTestObjByToolTip("Edit [Alt+F]");
//		editAltF2_DELETE().click();
		Utils.waitForScreenToLoad("Enter Consideration Date", 10);
		if (enterConsiderationDatePlanning().exists()){
			Utils.getGuiTestObjByName(".btOK");
			sleep(1);
		}
		Utils.waitForScreenToLoad("Traffic Relation", 20);
		return Const.SUCCESSMSG;
	}

	public int getSearchListCount() {
		GuiSubitemTestObject neListTable = (GuiSubitemTestObject) Utils
				.getObj(".class",
						"com.tmobile.itnetdev.fixedbuild.common.gui.components.table.ProTable");
		ITestDataTable myTable = (ITestDataTable) neListTable
				.getTestData("contents");
		return myTable.getRowCount();
	}

	@Override
	public String createNew(HashMap<String, String> createCriteria) {
		if (!Utils.waitForScreenToLoad("Select VBZ", 10))
			openSelectVBZDialog();
		enterSearchCriteria(createCriteria);
		clickNewButton();
		Utils.waitForScreenToClose("Dauer", 300);
		if (SysInfoDialog().exists())
			SysInfoDialogOk().click();
		Utils.waitForScreenToLoad("Create New VBZ", 10);
		if (createCriteria.get("NE_A") != null) {
			NEA_CreateVBZTable().click(
					atCell(atRow("neId", createCriteria.get("NE_A")),
							atColumn("neId")));
		} else {
			NEA_CreateVBZTable().click(
					atCell(atRow(atIndex(0)), atColumn("neId")));
		}
		sleep(2);
		if (createCriteria.get("NE_B") != null) {
			NEB_CreateVBZTable().click(
					atCell(atRow("neId", createCriteria.get("NE_B")),
							atColumn("neId")));
		} else {
			NEB_CreateVBZTable().click(
					atCell(atRow(atIndex(0)), atColumn("neId")));
		}
		sleep(2);
		Utils.getGuiTestObjByToolTip("Create [Alt+C]").click();
		sleep(5);
		Utils.waitForScreenToLoad("Enter Consideration Date", 200);
		if (Utils.getDialogTitles().contains("An error occured!")
				|| Utils.getDialogTitles().contains("VBZ")) {
			GuiTestObject informationButton = (GuiTestObject) Utils.getObj(
					"accessibleContext.accessibleName", "Information", "name",
					"OptionPane.button");
			informationButton.click();
			sleep(2);
			TextGuiSubitemTestObject textArea = (TextGuiSubitemTestObject) Utils
					.getObj(".class",
							"com.tmobile.itnetdev.guicomponents.components.PTextArea");
			if (textArea != null) {
				sleep(2);
				String message = textArea.getText();
				sleep(2);
				SysInfoDialogOk().click();
				sleep(2);
				GuiTestObject cancelBtn = (GuiTestObject) Utils.getObj(
						"accessibleContext.accessibleName", "Cancel");
				cancelBtn.click();
				sleep(2);
				return message;
			}
			return "An error occured!";
		}
		if (enterConsiderationDatePlanning().exists()) {
			sleep(1);
			if (createCriteria.get("ConsiderationDate") != null) {
				start().setText(createCriteria.get("ConsiderationDate"));
				sleep(1);
			}
			Utils.getGuiTestObjByName(".btOK").click();
			sleep(1);
		}
		if (Utils.waitForScreenToLoad("Traffic Relation", 20))
			return Const.SUCCESSMSG;
		else
			return Const.NOTSUCCESS;
	}

	public void enterSearchCriteria(HashMap<String, String> searchCriteria){
		if (searchCriteria.get("IndentCode_A") != null) {
			getTextFieldByName("ProDBTextField", ".edSTNrNEA" ).setText(searchCriteria.get("IndentCode_A"));
			sleep(1);
		}
		if (searchCriteria.get("IndentCode_B") != null) {
			((TextGuiSubitemTestObject) Utils.getObj("name", ".edSTNrNEB")).setText(searchCriteria.get("IndentCode_B"));
			sleep(1);
		}
		if (searchCriteria.get("NEType_B") != null) {
			Utils.getListBoxByName(".cmbBTypNEB" ).click();
			sleep(1);
			Utils.getListBoxByName(".cmbBTypNEB" ).click(atText(searchCriteria.get("NEType_B")));
			sleep(1);
		}
		if (searchCriteria.get("NEType_A") != null) {
			Utils.getListBoxByName(".cmbBTypNEA" ).click();
			sleep(1);
			Utils.getListBoxByName(".cmbBTypNEA" ).click(atText(searchCriteria.get("NEType_A")));
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
		if (searchCriteria.get("NodeID_A") != null) {
			getTextFieldByName("ProDBTextField", ".edNodeIdA" ).setText(searchCriteria.get("NodeID_A"));
			sleep(1);
		}
		if (searchCriteria.get("NodeID_B") != null) {
			getTextFieldByName("ProDBTextField", ".edNodeIdB" ).setText(searchCriteria.get("NodeID_B"));
			sleep(1);
		}
		TextGuiSubitemTestObject dateField = (TextGuiSubitemTestObject) Utils.getObj("name",".edSelectedDate");
		if (searchCriteria.get("date") != null) {
			dateField.setText(searchCriteria.get("date"));
			sleep(1);
		}else{
			dateField.setText("");
			sleep(1);
		}
	}

	public void openSelectVBZDialog() {
		sleep(2);
		pMenuBar().click(atPath("VBZ"));
		sleep(1);
		pMenuBar().click(atPath("VBZ->Edit  VBZ"));
		Utils.waitForScreenToLoad("Select VBZ", 10);
	}

	public String delete() {
		Utils.waitForScreenToLoad("Traffic Relation", 10);
		Utils.getGuiTestObjByToolTip("Delete [Alt+Z]").click();
		Utils.waitForScreenToLoad("VBZ", 10);
		yes().click();
		sleep(5);
		if (error().exists()) {
			sleep(1);
			String errorText = (String) ErrorText().getProperty("text");
			((GuiTestObject) Utils.getObj("accessibleContext.accessibleName",
					"OK")).click();
			sleep(2);
			if (Utils.removeHtml(errorText).equalsIgnoreCase(
					"No data record found!"))
				return Const.SUCCESSMSG;
			else
				return Const.NOTSUCCESS;

		}
		return Const.SUCCESSMSG;
	}
}
