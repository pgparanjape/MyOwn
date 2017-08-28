package PB_2015_2.FB.Actions;

import java.util.HashMap;

import resources.PB_2015_2.FB.Actions.ExternalServiceTypesActionsHelper;

import PB_2015_2.Common.Const;
import PB_2015_2.Common.Utils;
import PB_2015_2.Verification.Verify;

import com.rational.test.ft.*;
import com.rational.test.ft.object.interfaces.*;
import com.rational.test.ft.object.interfaces.SAP.*;
import com.rational.test.ft.object.interfaces.siebel.*;
import com.rational.test.ft.script.*;
import com.rational.test.ft.value.*;
import com.rational.test.ft.vp.*;

public class ExternalServiceTypesActions extends
		ExternalServiceTypesActionsHelper {
	
	public void testMain(Object[] args) {
	
		configurationsAssemblyGroupsCh().performTest(SelectExternalServiceTypes_standardVP());
		
	}

	public String createNew(HashMap<String, String> criteria) {
		sleep(2);
		pMenuBar().click(atPath("Administration"));
		sleep(2);
		pMenuBar().click(
				atPath("Administration->Sites->External Service Types"));
		Utils.waitForScreenToLoad("Select External Service Type", 20);
		Utils.getGuiTestObjByToolTip("New [Alt+N]").click();
		Utils.waitForScreenToLoad("Create External Service Type", 20);
		if (criteria.containsKey("ServiceType")) {
			serviceType().click();
			sleep(1);
			serviceType().click(atText(criteria.get("ServiceType")));// default will be"Bauabruf (Construction)"
			sleep(2);
		}
		extTypeName().setText(criteria.get("Name"));
		sleep(1);
		description().setText(criteria.get("Description"));
		sleep(1);
		Utils.getGuiTestObjByToolTip("Create [Alt+C]").click();
		Utils.waitForScreenToLoad("Edit External Service Type", 20);
		if (criteria.containsKey("Usuable")
				&& criteria.get("Usuable").equalsIgnoreCase("false")) {
			proCheckBox().clickToState(NOT_SELECTED);
			sleep(2);
			return save();
		}
		return Const.SUCCESSMSG;
	}

	public int search(HashMap<String, String> criteria) {
		sleep(2);
		pMenuBar().click(atPath("Administration"));
		sleep(2);
		pMenuBar().click(
				atPath("Administration->Sites->External Service Types"));
		Utils.waitForScreenToLoad("Select External Service Type", 20);

		if (criteria.containsKey("Name")) {
			extTypeName2().setText(criteria.get("Name"));
		}

		Utils.getGuiTestObjByToolTip("Select [Alt+S]").click();
		sleep(15);
		if (systemInformation().exists()) {
			String errorText = (String) _htmlNoDataRecordFoundHtml()
					.getProperty("text");
			ok().click();
			sleep(2);
			Verify.failDueToUnexpectedError(Utils.removeHtml(errorText));
		}
		if (Utils.getDialogTitles().contains("edit external service types")) {
			Utils.getGuiTestObjByName(".btAuswahl").click();
			sleep(10);
		}
		ITestDataTable myTable = (ITestDataTable) name()
				.getTestData("contents");
		return myTable.getRowCount();
	}

	public String openFromListScreen(int i) {
		sleep(2);
		ITestDataTable myTable = (ITestDataTable) name()
				.getTestData("contents");
		if (myTable.getRowCount() < i) {
			return "Given row number does not exists, please check once";
		}
		name().doubleClick(atCell(atRow(atIndex(i)), atColumn("name")));
		Utils.waitForScreenToLoad("edit external service types", 30);
		return Const.SUCCESSMSG;
	}

	public String addParameter(String parameterName) {
		sleep(2);
		Utils.getGuiTestObjByToolTip("New [Alt+C]").click();
		Utils.waitForScreenToLoad("Parameters Choice", 20);
		int parameterRowNo = 0;
		try {
			parameterRowNo = Integer.parseInt(parameterName);
		} catch (NumberFormatException e) {
			parameterRowNo = -1;
		}
		if (parameterName.equalsIgnoreCase("All")) {
			Utils.getGuiTestObjByToolTip("Select [Alt+S]").click();
			sleep(10);
			addAll().waitForExistence(60, 10);
			addAll().click();
			Utils.waitForScreenToClose("Parameters Choice", 20);
		} else if (parameterRowNo >= 0) {
			selectAltS().click();
			parametersSearchListTable().doubleClick(
					atCell(atRow(atIndex(parameterRowNo)),
							atColumn("headerName")));

			Utils.waitForScreenToClose("Parameters Choice", 20);

		} else {
			nameText().setText(parameterName);
			selectAltS().click();
			if (Utils.waitForScreenToClose("Parameters Choice", 20)) {
				parametersSearchListTable().doubleClick(
						atCell(atRow(atIndex(0)), atColumn("headerName")));
			}
		}

		return save();
	}

	private String save() {
		saveAltA().click();
		sleep(15);
		if (saveAltA().isEnabled()) {
			Verify.failDueToUnexpectedError("After Saving External Service Types, button is disabled");
			return "After Saving External Service Types, button is disabled";
		}
		return Const.SUCCESSMSG;
	}

	public String deleteParameter(String parameterName) {
		sleep(2);
		ITestDataTable myTable;
		myTable = (ITestDataTable) parametersListTable()
				.getTestData("contents");

		if (parameterName.equalsIgnoreCase("All")) {
			parametersListTable().click(
					SHIFT_LEFT,
					atCell(atRow(myTable.getRowCount() - 1),
							atColumn("parameterType.parameterTypeName")));
		} else {
			for (int row = 0; row < myTable.getRowCount(); row++) {
				String temp = (String) myTable.getCell(row, 1);
				if (temp.equalsIgnoreCase(parameterName)) {
					parametersListTable()
							.click(atCell(atRow(row),
									atColumn("parameterType.parameterTypeName")));
				}
			}
		}
		sleep(2);
		deleteAltD().click();
		Utils.waitForScreenToLoad("Perform action", 10);
		yes().click();
		return save();
	}

	public void delete() {
		sleep(2);
		deleteAltX().click();
		yes().waitForExistence();
		yes().click();
		sleep(10);
		Utils.closeAll();
	}

	public String edit(HashMap<String, String> criteria) {
		if (criteria.containsKey("Name")) {
			extTypeName3().setText(criteria.get("Name"));
			sleep(1);
		}
		if (criteria.containsKey("Description")) {
			description2().setText(criteria.get("Description"));
			sleep(1);
		}
		if (criteria.containsKey("AccountNo")) {
			accountNo().setText(criteria.get("AccountNo"));
			sleep(1);
		}
		return save();
	}
}
