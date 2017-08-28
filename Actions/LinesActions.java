package PB_2015_2.FB.Actions;

import java.util.ArrayList;
import java.util.HashMap;

import PB_2015_2.Common.Const;
import PB_2015_2.Common.Utils;
import PB_2015_2.FB.ObjRepo.FBRepo;

import com.rational.test.ft.object.interfaces.*;
import com.rational.test.ft.vp.ITestDataTable;

public class LinesActions extends FBRepo{

	public ArrayList<String> createNewLineFromMaintainUDSV(HashMap<String, String> createCriteria) {
		ArrayList<String> returnArrayList = new ArrayList<String>();
		sleep(5);
		GuiSubitemTestObject udsvListTable = (GuiSubitemTestObject) Utils.getGuiTestObjByName(".tblUDSV");
		ITestDataTable myTable = (ITestDataTable) udsvListTable.getTestData("contents");
		all: for (int row = 0; row < myTable.getRowCount(); row++) {
			for (int col = 1; col < myTable.getColumnCount(); col++) {
				try {
					String temp = (String) myTable.getCell(row, col);
					if (temp.equalsIgnoreCase(createCriteria.get("UDSVSearch"))){
						udsvListTable.click(atCell(atRow(atIndex(row)), atColumn(atIndex(0))));
						sleep(2);
						break all;
					}
				} catch (Exception e) {	}
			}
		}
		((GuiTestObject) Utils
				.getObj(".class",
						"com.tmobile.itnetdev.fixedbuild.common.gui.components.ProButton",
						"accessibleContext.accessibleName", "Lines")).click();
		sleep(5);
		ArrayList<String> dialogsTitleText = Utils.getDialogTitles();
		for (int i = 0; i < 30 && !SysInfoDialog().exists(); i=i+10) {
			if (!dialogsTitleText.contains("Select Lines")) {
				if (!dialogsTitleText.contains("Edit Lines")) {
					sleep(10);
					dialogsTitleText = Utils.getDialogTitles();
				} else {
					break;
				}
			}else{
				break;
			}
		}
		if (SysInfoDialog().exists()) {
			SysInfoDialog().click(atPoint(141, 15));
			sleep(2);
			String _NoDataRecordFoundHtml = (String) _htmlNoDataRecordFoundHtml2()
					.getProperty("text");
			returnArrayList.add(Utils.removeHtml(_NoDataRecordFoundHtml));
			sleep(2);
			SysInfoDialog().click(atPoint(140, 12));
			sleep(2);
			((GuiTestObject) Utils.getObj("accessibleContext.accessibleName",
					"OK")).click();
			createLine(createCriteria);
			returnArrayList.add("Lines");
//			String temp = editDialog("Edit Lines").getDescriptiveName();
			returnArrayList.add("Edit Lines");
		} else if (editDialog("Edit Lines") != null) {
			String temp = editDialog("Edit Lines").getDescriptiveName();
			returnArrayList.add(temp);
			sleep(2);
			cloneLineAltM().click();
			sleep(2);
			DeleteConfirmationPopUp().click(atPoint(142, 13));
			sleep(1);
			yes().click();
			sleep(5);
			if (createCriteria.get("descriptionLine") != null) {
				sleep(1);
				TextGuiSubitemTestObject description = (TextGuiSubitemTestObject)Utils.getObj(
						".class",
						"com.tmobile.itnetdev.fixedbuild.common.gui.components.ProDBTextField",
						"accessibleContext.accessibleName", "Description*:");
				description.setText(createCriteria.get("descriptionLine"));
			}
			if (getSaveButtonStatus()) {
				clickSaveButton();
				String ChangesSaved_text = (String)changesSaved().getProperty("text");
				for (int i = 0; i < 10
						&& !ChangesSaved_text
								.equalsIgnoreCase(Const.CHANGESSAVED); i++) {
					sleep(1);
				}
			}
		}else if (editProDialog(" Select Lines ") != null){
			String temp = editProDialog("Select Lines").getDescriptiveName();
			returnArrayList.add(temp);
			clickNewButton();
			sleep(2);
			createLine(createCriteria);
			returnArrayList.add("Lines");
			returnArrayList.add("Edit Lines");
		}
		return returnArrayList;
	}

	public String createLine(HashMap<String, String> createCriteria) {
		Utils.waitForScreenToLoad("Lines", 20);
		sleep(2);
		if (createCriteria.get("classLine") != null) {
			proComboBox2().click();
			sleep(1);
			proComboBox2().click(atText(createCriteria.get("classLine")));
		}
		if (createCriteria.get("typeLine") != null) {
			sleep(1);
			type().click();
			sleep(1);
			type().click(atText(createCriteria.get("typeLine")));
		}
		if (createCriteria.get("descriptionLine") != null) {
			sleep(1);
			description4().setText(createCriteria.get("descriptionLine"));
		}
		if (createCriteria.get("classConnection") != null) {
			sleep(1);
			proComboBox1().click();
			sleep(2);
			proComboBox1().click(atText(createCriteria.get("classConnection")));
		}
		if (createCriteria.get("startValueConnection") != null) {
			sleep(1);
			proNumTextField().setText(createCriteria.get("startValueConnection"));
		}
		if (createCriteria.get("quantityConnection") != null) {
			sleep(1);
			quantity().setText(createCriteria.get("quantityConnection"));
		}
		if (createCriteria.get("startDateConnection") != null) {
			sleep(1);
			start2().setText(createCriteria.get("startDateConnection"));
		}
		if (createCriteria.get("endDateConnection") != null) {
			sleep(1);
			end2().setText(createCriteria.get("endDateConnection"));
		}
		sleep(1);
		((GuiTestObject) Utils
				.getObj(".class",
						"com.tmobile.itnetdev.fixedbuild.common.gui.components.ProButton",
						"accessibleContext.accessibleName", "Create")).click();
		sleep(20);
		return Const.SUCCESSMSG;
	}

	/**
	 * Add the parameter to line
	 * @param paramGrpName
	 * @param paramName
	 * @return success/notsuccess
	 */
	public String addParam(String paramGrpName, String paramName) {
		Utils.waitForScreenToLoad("Edit Lines", 30);
		((GuiSubitemTestObject) Utils
				.getObj(".class",
						"com.tmobile.itnetdev.fixedbuild.common.gui.components.ProTabbedPane",
						".classIndex", "0")).click(atText("Parameter"));
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
		return saveLine();
	}

	private String saveLine() {
		siteAltSaveButton().click();
		sleep(10);
		String changesSaved_text = (String) changesSaved().getProperty("text");
		for (int i = 0; i < 60
				&& !changesSaved_text.contains(Const.CHANGESSAVED); i = i + 10) {
			sleep(10);
			changesSaved_text = (String) changesSaved().getProperty("text");
		}
		if (changesSaved_text.contains(Const.CHANGESSAVED))
			return Const.SUCCESSMSG;
		else
			return Const.NOTSUCCESS;
	}

	public ArrayList<String> getParameterValue(String whichParam) {
		ArrayList<String> retArrayList = new ArrayList<String>();
		Utils.waitForScreenToLoad("Edit Lines", 30);
		((GuiSubitemTestObject) Utils
				.getObj(".class",
						"com.tmobile.itnetdev.fixedbuild.common.gui.components.ProTabbedPane",
						".classIndex", "0")).click(atText("Parameter"));
		sleep(2);
		GuiSubitemTestObject paramTable = (GuiSubitemTestObject) Utils
				.getObj(".class",
						"com.tmobile.itnetdev.fixedbuild.projektierung.LTGParamPanel$1");
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
		Utils.waitForScreenToLoad("Edit Lines", 30);
		((GuiSubitemTestObject) Utils
				.getObj(".class",
						"com.tmobile.itnetdev.fixedbuild.common.gui.components.ProTabbedPane",
						".classIndex", "0")).click(atText("Parameter"));
		sleep(2);
		GuiSubitemTestObject paramTable = (GuiSubitemTestObject) Utils
				.getObj(".class",
						"com.tmobile.itnetdev.fixedbuild.projektierung.LTGParamPanel$1");
		ITestDataTable myTable = (ITestDataTable) paramTable.getTestData("contents");
		for (int row = 0; row < myTable.getRowCount(); row++) {
			String temp = (String) myTable.getCell(row, 1);
			if (temp.equalsIgnoreCase(whichParam)) {
				paramTable.doubleClick(atCell(atRow(atIndex(row)),
						atColumn(atIndex(3))));
				sleep(5);
				mainFrame().inputKeys(whatValue);
				sleep(5);
				paramTable.click(atCell(atRow(atIndex(row)),atColumn(atIndex(0))));
				sleep(1);
				paramTable.click(atCell(atRow(atIndex(row)),atColumn(atIndex(0))));
				sleep(5);
				break;
			}
		}
		return saveLine();
	}

	public String search(HashMap<String, String> criteria) {
		pMenuBar().click(atPath("Projection"));
		sleep(1);
		pMenuBar().click(atPath("Projection->Lines"));
		sleep(2);
		Utils.waitForScreenToLoad("Select Lines", 20);
		if (criteria.get("IndentCode_A") != null) {
			Utils.getTextGuiSubitemByName(".edStandortNrA").setText(
					criteria.get("IndentCode_A"));
			sleep(1);
		}
		if (criteria.get("IndentCode_B") != null) {
			Utils.getTextGuiSubitemByName(".edStandortNrB").setText(
					criteria.get("IndentCode_B"));
			sleep(1);
		}
		if (criteria.get("descriptionLine") != null) {
			Utils.getTextGuiSubitemByName(".edBezeichnung").setText(
					criteria.get("descriptionLine"));
			sleep(1);
		}
		if (criteria.get("commentLine") != null) {
			Utils.getTextGuiSubitemByName(".edBemerkung").setText(
					criteria.get("commentLine"));
			sleep(1);
		}
		if (criteria.get("date") != null) {
			Utils.getTextGuiSubitemByName(".edDate").setText(
					criteria.get("date"));
			sleep(1);
		}
		Utils.getGuiTestObjByToolTip("Select [Alt+S]").click();
		sleep(5);
		if (information().exists())
			yes2().click();
		sleep(2);
		Utils.waitForScreenToClose("Dauer", 120);
		if (SysInfoDialog().exists()) {
			String text = (String) _htmlNoDataRecordFoundHtml2().getProperty("text");
			text = Utils.removeHtml(text);
			if (text.contains("200")) {
				SysInfoDialogOk().click();
				sleep(2);
			} else {
				return text;
			}
		}
		return Const.SUCCESSMSG;
	}

	public String openFromSearchList(int rowNo) {
		GuiSubitemTestObject seachListTable = (GuiSubitemTestObject) Utils
				.getObj(".class",
						"com.tmobile.itnetdev.fixedbuild.common.gui.components.table.ProTable");
		int rowCount = ((Integer) seachListTable.getProperty("rowCount"))
				.intValue();
		if (rowCount > 0) {
			seachListTable.click(atCell(atRow(atIndex(rowNo)),
					atColumn(atIndex(1))));
			sleep(1);
			Utils.getGuiTestObjByToolTip("Edit [Alt+F]").click();
			Utils.waitForScreenToLoad("Edit Lines", 30);
			sleep(2);
			return Const.SUCCESSMSG;
		} else {
			return "No result in the search Table";
		}
	}
}
