package PB_2015_2.FB.Actions;

import java.util.HashMap;

import PB_2015_2.Common.Const;
import PB_2015_2.Common.Utils;
import PB_2015_2.FB.ObjRepo.FBRepo;
import PB_2015_2.Verification.Verify;

import com.rational.test.ft.object.interfaces.GuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.GuiTestObject;
import com.rational.test.ft.object.interfaces.TextGuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.TextSelectGuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.ToggleGUITestObject;
import com.rational.test.ft.vp.ITestDataTable;

public class EnsembleActions extends FBRepo {
	
	ITestDataTable myTable;
	
	public void openSearchEnsemble() {
		sleep(2);
		pMenuBar().click(atPath("Administration"));
		sleep(1);
		pMenuBar().click(atPath("Administration->Network Elements->Ensemble"));
		sleep(2);
		selectEnsembles().click();
	}

	public void createNewEnsemble(String ensembleName) {
		sleep(2);
		Utils.waitForScreenToLoad("Administer Ensemble", 10);
		newEnsembleNameTextField().setText(ensembleName);
		sleep(1);
		newEnsembleDescriptionTextField().setText(ensembleName+"_desc");
		sleep(1);
		clickSaveButton();
		sleep(4);
	}

	public void clickNewButton() {
		sleep(1);
		super.clickNewButton();
		sleep(2);
	}
	
	public void addConfig(String[] configurations) {
		sleep(2);
		for (int i = 0; i < configurations.length; i++) {
			sleep(1);
			addConfig().click();
			Utils.waitForScreenToLoad("configurations / assembly groups choice", 20);
			((TextGuiSubitemTestObject)Utils.getObj("name",".proDBTextFieldName")).setText(configurations[i]);
			sleep(2);
			clickSearchButton();
			sleep(10);
			if (Utils.getDialogTitles().contains("system information")){
				((GuiTestObject) Utils.getObj(
						"accessibleContext.accessibleName", "OK")).click();
				Verify.failDueToUnexpectedError("Unable to add given Configuration "
						+ configurations[i]);
				sleep(2);
				closeAltL().click();
			}else if (agsnt().exists()){
				agsnt().click(atCell(atRow(atIndex(0)),atColumn("headerName")),atPoint(50,3));
				sleep(2);
				clickChooseButton();
				Utils.waitForScreenToClose("configurations / assembly groups choice", 240);
			}
		}
	}
	
	public void closeAll() {
		super.closeAll();
	}

	public void attach(String[] attachConfig) throws Exception{
			String[] configA = attachConfig[0].split("::");
			String[] configB = attachConfig[1].split("::");
			sleep(2);
			ensembleConfigATable().click(
					atCell(atRow("headerName", configA[0]),atColumn("headerName")));
			sleep(5);
			ensembleConfigATreeTable().click(
					atCell(atRow(atIndex(Integer.parseInt(configA[1]))), atColumn("Name")));
			sleep(5);
			ensembleConfigBTable().click(
					atCell(atRow("headerName", configB[0]), atColumn("headerName")));
			sleep(5);
			ensembleConfigBTreeTable().click(
					atCell(atRow(atIndex(Integer.parseInt(configB[1]))), atColumn("Name")));
			sleep(5);
			attach().click();
			sleep(5);
			clickSaveButton();
			sleep(5);
			String ChangesSaved_text = (String)changesSaved().getProperty("text");
			Verify.strVerification(ChangesSaved_text, "Changes saved!",
					Const.EXACTMATCH, "Configuration Attach");
		
//		TODO need to check the (1) in the  ensembleConfigATreeTable adn ensembleConfigBTreeTable
		
	}

	public String[] createNewNEusingEnsemble(
			HashMap<String, String> neCreateCriteria, String ensembleNENameStartText) {
		sleep(2);
		Utils.waitForScreenToLoad("Create Network Element", 10);
		sleep(2);
		((GuiSubitemTestObject) Utils
				.getObj(".class",
						"com.tmobile.itnetdev.fixedbuild.nevbz.presentation.networkelement.NENeu$CreateNETabbedPane"))
				.click(atText("Ensemble"));
		sleep(5);
		TextSelectGuiSubitemTestObject createNewEnsRegComboBox = (TextSelectGuiSubitemTestObject)Utils.getObj("name",".comboNiederlassung");
		if (neCreateCriteria.get("Region") != null) {
			createNewEnsRegComboBox.click();
			sleep(1);
			createNewEnsRegComboBox.click(
					atText(neCreateCriteria.get("Region")));
		} else {
			createNewEnsRegComboBox.click();
			sleep(1);
			createNewEnsRegComboBox.click(atText(Const.REGION_PREFIX_KLN));
		}
		TextGuiSubitemTestObject createNewEnsSiteNameTextField = ((TextGuiSubitemTestObject)Utils.getObj("name",".edStandortName"));
		if (neCreateCriteria.get("SiteName") != null) {
			createNewEnsSiteNameTextField.setText(
					neCreateCriteria.get("SiteName"));
		} else {
			createNewEnsSiteNameTextField.setText(Const.SITE);
		}
		sleep(2);
		Utils.getTextGuiSubitemByName(".edKonfiguration").setText(
				neCreateCriteria.get("EnsembleName"));
		sleep(2);
		Utils.getGuiTestObjByName(".btKonfiguration").click();
		sleep(5);
		clickSearchButton();
		sleep(5);
		if (ensemblesListTable().exists()&& ensemblesListTable().isShowing()){
			try {
				ensemblesListTable().click(atCell(atRow(atIndex(0)), atColumn("headerName")),atPoint(52, 8));
				sleep(1);
				clickChooseButton();
				sleep(5);
			} catch (Exception e) {}
		}
		TextGuiSubitemTestObject CreateNewEnsRolloutPlanTextField = (TextGuiSubitemTestObject) Utils
				.getObj(".class",
						"com.tmobile.itnetdev.fixedbuild.common.gui.components.ProTextField",
						"name", ".edRolloutPlan");
		
		if (neCreateCriteria.get("RollOutPlan") != null) {
			CreateNewEnsRolloutPlanTextField.setText(neCreateCriteria.get("RollOutPlan"));
		} else {
			CreateNewEnsRolloutPlanTextField.setText(Const.rollOutPlanDate);
		}
		if (neCreateCriteria.get("Owner") != null) {
			TextSelectGuiSubitemTestObject createNewEnsOwnerListBox = ((TextSelectGuiSubitemTestObject)Utils.getObj("name",".comboBesitzer"));
			createNewEnsOwnerListBox.click();
			createNewEnsOwnerListBox.click(atText(neCreateCriteria.get("Owner")));
		}
		if (neCreateCriteria.get("Floating") != null) {
			ToggleGUITestObject createNewEnsNEWOTimeCheckBox = ((ToggleGUITestObject)Utils.getObj("name",".cbSwimm")); 
			boolean flag = (boolean) createNewEnsNEWOTimeCheckBox.getProperty("selected");
			if (neCreateCriteria.get("Floating").equalsIgnoreCase("true") && !flag) {
				createNewEnsNEWOTimeCheckBox.clickToState(SELECTED);
				sleep(1);
			} else {
				if (flag) {
					createNewEnsNEWOTimeCheckBox.clickToState(SELECTED);
				}
				sleep(1);
				((TextGuiSubitemTestObject)Utils.getObj("name",".edStartDate")).setText(neCreateCriteria.get("StartDate"));
				sleep(1);
				((TextGuiSubitemTestObject)Utils.getObj("name",".edEndDate")).setText(neCreateCriteria.get("EndDate"));
			}
		}
		sleep(2);
		myTable = (ITestDataTable) createNewEnsNEListTable().getTestData("contents");
		String ensembleNEList[] = new String [myTable.getRowCount()];
		int randomNo = Utils.getRandomNo(999);
		for (int row = 0; row < myTable.getRowCount(); row++) {
			sleep(1);
			createNewEnsNEListTable().click(atCell(atRow(atIndex(row)), atColumn("headerNeId")),atPoint(32, 10));
			sleep(1);
			createNewEnsNEListTable().click(atCell(atRow(atIndex(row)), atColumn("headerNeId")),atPoint(32, 10));
			String neID = ensembleNENameStartText + randomNo + row;
			ensembleNEList[row] = neID + "@" + myTable.getCell(row, 1) + "@"
					+ myTable.getCell(row, 2);
			createNEDialog().inputKeys(neID);
			sleep(1);
			createNEDialog().inputKeys("{TAB}");
		}
		sleep(2);
		Utils.getGuiTestObjByName(".btBearbeiten").click();
		sleep(2);
		if (Utils.waitForScreenToLoad("Edit Network Element", 180)){
			return ensembleNEList;
		}else{
			return null;
		}
	}

	public String createNewNEusingEnsemble(
			HashMap<String, String> neCreateCriteria, String[] ensembleNENames) {
		sleep(2);
		Utils.waitForScreenToLoad("Create Network Element", 10);
		sleep(2);
		((GuiSubitemTestObject) Utils
				.getObj(".class",
						"com.tmobile.itnetdev.fixedbuild.nevbz.presentation.networkelement.NENeu$CreateNETabbedPane"))
				.click(atText("Ensemble"));
		sleep(5);
		TextSelectGuiSubitemTestObject createNewEnsRegComboBox = (TextSelectGuiSubitemTestObject)Utils.getObj("name",".comboNiederlassung");
		TextGuiSubitemTestObject createNewEnsSiteNameTextField = (TextGuiSubitemTestObject)Utils.getObj("name",".edStandortName");
		TextGuiSubitemTestObject createNewEnsEnsembleTextField = (TextGuiSubitemTestObject)Utils.getObj("name",".edKonfiguration");
		GuiTestObject createNewEnsEnsebleDetailButton = (GuiTestObject)Utils.getObj("name",".btKonfiguration");
		if (neCreateCriteria.get("Region") != null) {
			createNewEnsRegComboBox.click();
			sleep(1);
			createNewEnsRegComboBox.click(
					atText(neCreateCriteria.get("Region")));
			sleep(10);
		} else {
			createNewEnsRegComboBox.click();
			sleep(1);
			createNewEnsRegComboBox.click(atText(Const.REGION_PREFIX_KLN));
		}
		if (neCreateCriteria.get("SiteName") != null) {
			createNewEnsSiteNameTextField.setText(
					neCreateCriteria.get("SiteName"));
		} else {
			createNewEnsSiteNameTextField.setText(Const.SITE);
		}
		sleep(2);
		createNewEnsEnsembleTextField.setText(neCreateCriteria
				.get("EnsembleName"));
		sleep(2);
		createNewEnsEnsebleDetailButton.click();
		sleep(5);
		clickSearchButton();
		sleep(5);
//		GuiSubitemTestObject ensemblesListTable = (GuiSubitemTestObject)getObjByClass("com.tmobile.itnetdev.fixedbuild.common.gui.components.table.ProTable");
		if (ensemblesListTable().exists()&& ensemblesListTable().isShowing()){
			try {
				ensemblesListTable().click(atCell(atRow(atIndex(0)), atColumn("headerName")),atPoint(52, 8));
				sleep(1);
				clickChooseButton();
				sleep(5);
			} catch (Exception e) {}
		}
		TextGuiSubitemTestObject CreateNewEnsRolloutPlanTextField = (TextGuiSubitemTestObject) Utils.getObj(
				".class",
				"com.tmobile.itnetdev.fixedbuild.common.gui.components.ProTextField",
				"name", ".edRolloutPlan");
		if (neCreateCriteria.get("RollOutPlan") != null) {
			CreateNewEnsRolloutPlanTextField.setText(neCreateCriteria.get("RollOutPlan"));
		} else {
			CreateNewEnsRolloutPlanTextField.setText(Const.rollOutPlanDate);
		}
		if (neCreateCriteria.get("Owner") != null) {
			TextSelectGuiSubitemTestObject createNewEnsOwnerListBox = ((TextSelectGuiSubitemTestObject)Utils.getObj("name",".comboBesitzer"));
			createNewEnsOwnerListBox.click();
			createNewEnsOwnerListBox.click(atText(neCreateCriteria.get("Owner")));
		}
		if (neCreateCriteria.get("Floating") != null) {
			ToggleGUITestObject createNewEnsNEWOTimeCheckBox = ((ToggleGUITestObject)Utils.getObj("name",".cbSwimm"));
			boolean flag = (boolean) createNewEnsNEWOTimeCheckBox.getProperty("selected");
			if (neCreateCriteria.get("Floating").equalsIgnoreCase("true") && !flag) {
				createNewEnsNEWOTimeCheckBox.clickToState(SELECTED);
				sleep(1);
			} else {
				if (flag) {
					createNewEnsNEWOTimeCheckBox.clickToState(SELECTED);
				}
				sleep(1);
				((TextGuiSubitemTestObject)Utils.getObj("name",".edStartDate")).setText(neCreateCriteria.get("StartDate"));
//				createNewEnsStartTextField().setText(neCreateCriteria.get("StartDate"));
				sleep(1);
				((TextGuiSubitemTestObject)Utils.getObj("name",".edEndDate")).setText(neCreateCriteria.get("EndDate"));
//				createNewEnsEndTestField().setText(neCreateCriteria.get("EndDate"));
			}
		}
		sleep(2);
		myTable = (ITestDataTable) createNewEnsNEListTable().getTestData("contents");
		for (int row = 0; row < myTable.getRowCount(); row++) {
			sleep(1);
			createNewEnsNEListTable().click(atCell(atRow(atIndex(row)), atColumn("headerNeId")),atPoint(32, 10));
			sleep(1);
			createNewEnsNEListTable().click(atCell(atRow(atIndex(row)), atColumn("headerNeId")),atPoint(32, 10));
			createNEDialog().inputKeys(ensembleNENames[row]);
			sleep(1);
			createNEDialog().inputKeys("{TAB}");
		}
		sleep(2);
		((GuiTestObject)Utils.getObj("name", ".btBearbeiten")).click();
		sleep(10);
		if (Utils.waitForScreenToLoad("Edit Network Element", 90)) {
			return Const.SUCCESSMSG;
		} else if (systemInformationPopup().exists()) {
			sleep(2);
			String errorText = (String) _htmlNoDataRecordFoundHtml().getProperty("text");
			sleep(2);
			((GuiTestObject) Utils.getObj("accessibleContext.accessibleName","OK")).click();
			closeAll();
			sleep(2);
			return Utils.removeHtml(errorText);
		} else {
			return "Something Wrong.. Edit Network Element Screen not loaded after waiting 90 Sec..";
		}
	}
}
