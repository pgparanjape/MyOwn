package PB_2015_2.FB.Actions;

import resources.PB_2015_2.FB.Actions.SystemActionsHelper;
import PB_2015_2.Common.Const;
import PB_2015_2.Common.Utils;

import com.rational.test.ft.object.interfaces.GuiSubitemTestObject;
import com.rational.test.ft.vp.ITestDataTable;

public class SystemActions extends SystemActionsHelper {

	String planningDate = "";

	public void testMain(Object[] args) {
	}

	public String getPlanningDate() {
		sleep(2);
		pMenuBar().click(atPath("System"));
		sleep(1);
		pMenuBar().click(atPath("System->Planning Date"));
		sleep(2);
		ChangePlanningDateDialog().click(atPoint(202, 13));
		sleep(2);
		planningDate = planningDate().getText();
		cancelAltN().click();
		sleep(2);
		return planningDate.replace(".", "").trim();
	}

	public String getPlanningDateAsItIs() {
		if (planningDate.isEmpty()) {
			getPlanningDate();
		}
		return planningDate;
	}
	
	public String openBookMark(String bookMarkName, String pubOrPrivate){
		pMenuBar().click(atPath("System"));
		sleep(1);
		pMenuBar().click(atPath("System->Bookmark Administration"));
		sleep(1);
		Utils.waitForScreenToLoad("Bookmark Administration", 10);
		String path  = "Bookmarks->"+pubOrPrivate+"->"+bookMarkName;
		proTree().click(atPath(path));
		sleep(2);
		open().click();
		return Const.SUCCESSMSG;
	}

	public String updateSysPref(String tabname, String attribute, String value,
			String inputValue) {

		if (!Utils.waitForScreenToLoad("Preferences", 5)) {
			pMenuBar().click(atPath("System"));
			pMenuBar().click(atPath("System->Preferences"));
			Utils.waitForScreenToLoad("Preferences", 20);
		}
		// .tabs : Fixed & Build,Radio,Common,Sort Order Region,System
		// Settings,Colors,Character Setting
		((GuiSubitemTestObject) Utils
				.getObj(".class",
						"com.tmobile.itnetdev.fixedbuild.common.gui.components.ProTabbedPane"))
				.click(atText(tabname));
		sleep(5);
		GuiSubitemTestObject attributeTable = ((GuiSubitemTestObject) Utils
				.getObj(".class",
						"com.tmobile.itnetdev.fixedbuild.common.gui.components.table.ProTable"));
		ITestDataTable myTable;
		myTable = (ITestDataTable) attributeTable.getTestData("contents");

		for (int row = 0; row < myTable.getRowCount(); row++) {
			String temp = myTable.getCell(row, 0).toString();
			if (temp.equalsIgnoreCase(attribute)) {
				if (inputValue.equalsIgnoreCase("checkbox")) {
					temp = myTable.getCell(row, 1).toString();
					if ((temp.equalsIgnoreCase("N") && value
							.equalsIgnoreCase("true"))
							|| (temp.equalsIgnoreCase("Y") && value
									.equalsIgnoreCase("false"))) {
						attributeTable.click(atCell(atRow(atIndex(row)),
								atColumn(atIndex(1))));
						sleep(2);
					} else
						return "checkbox already " + value;

				} else {
					attributeTable.doubleClick(atCell(atRow(atIndex(row)),
							atColumn(atIndex(1))));
					sleep(2);
					proTextField().setText(value);
					sleep(2);
					attributeTable.click();
				}
			}

		}
		return savePref();
	}

	public String savePref() {
		if (saveAltA().exists()) {
			saveAltA().click();
			sleep(30);
			String changesSavedText = (String) _ChangesHaveBeenSaved()
					.getProperty("text");
			if (changesSavedText.equalsIgnoreCase(" Changes have been saved!"))
				return Const.SUCCESSMSG;
			else {
				return Const.NOTSUCCESS;
			}
		}
		return "Save button is diabled";
	}
}
