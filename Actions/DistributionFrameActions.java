package PB_2015_2.FB.Actions;

import java.util.HashMap;
import java.util.Map;

import PB_2015_2.Common.Const;
import PB_2015_2.Common.Utils;
import PB_2015_2.FB.ObjRepo.FBRepo;

import com.rational.test.ft.object.interfaces.GuiTestObject;
import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.TextGuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.TextSelectGuiSubitemTestObject;
import com.rational.test.ft.vp.ITestDataTable;
import com.rational.test.ft.vp.ITestDataTree;
import com.rational.test.ft.vp.ITestDataTreeNode;
import com.rational.test.ft.vp.ITestDataTreeNodes;

public class DistributionFrameActions extends FBRepo {
	
	public void openSearchDialog() {
		sleep(2);
		pMenuBar().click(atPath("Projection"));
		sleep(1);
		pMenuBar().click(atPath("Projection->Distribution Frames"));
		sleep(5);
		Utils.waitForScreenToLoad("Distribution Frame", 20);
	}
	
	public void closeAll() {
		super.closeAll();
	}

	public void removeDate() {
		sleep(2);
		Utils.waitForScreenToLoad("Distribution Frame", 20);
		sleep(1);
		TestObject[] dates = find(atDescendant(
				".class",
				"com.tmobile.itnetdev.fixedbuild.common.gui.components.ProDateField",
				"accessibleContext.accessibleName", "Date:"));
		TextGuiSubitemTestObject date = (TextGuiSubitemTestObject) dates[0];
		date.setText("");
		sleep(1);
		configAssemblyGroupsDialog().inputKeys("{TAB}");
		sleep(2);
	}
/**
 * Perform the provided actions on SelectDistribution Dialog
 * supported actions "Close Window", "ALT+F4", "ESC", "ENTER"
 * @param action
 * @return true/false
 */
	public boolean selectDialogAction(String action) {
		sleep(2);
		Utils.waitForScreenToLoad("Distribution Frame", 20);
		if (action.equalsIgnoreCase("Close Window")) {
			sleep(1);
			configAssemblyGroupsDialog().close();
		} else if (action.equalsIgnoreCase("ALT+F4")) {
			sleep(1);
			// configurationsAssemblyGroupsCh().inputKeys("{ALT} {F4}");
			configAssemblyGroupsDialog().inputKeys("%{F4}");
		} else if (action.equalsIgnoreCase("ESC")) {
			sleep(1);
			configAssemblyGroupsDialog().inputKeys("{ESCAPE}");
		} else if (action.equalsIgnoreCase("ENTER")) {
			sleep(1);
			configAssemblyGroupsDialog().inputKeys("{Num~}");
			sleep(5);
			if (SysInfoDialog().exists()) {
				sleep(1);
				if (yes().exists())
					yes().click();
				sleep(2);
			}
			Utils.waitForScreenToClose("Dauer", 100);
			sleep(2);
			if (SysInfoDialog().exists()) {
				sleep(1);
				if (SysInfoDialogOk().exists())
					SysInfoDialogOk(ANY,MAY_EXIT).click();
			}
			sleep(5);
			ITestDataTable myTable = (ITestDataTable) Utils
					.getGuiTestObjByName(".tabSelektion").getTestData(
							"contents");
			if (myTable.getRowCount() > 0) {
				return true;
			} else {
				return false;
			}
		}
		sleep(2);
		if (configAssemblyGroupsDialog().exists()) {
			closeAll();
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Search the DF depending on the parameters
	 * @param dfSearchCriteria
	 * @return
	 */
	public String search(HashMap<String, String> dfSearchCriteria) {
		sleep(2);
		Utils.waitForScreenToLoad("Distribution Frame", 20);
		TestObject[] regions = find(atDescendant(
				".class",
				"com.tmobile.itnetdev.fixedbuild.nevbz.presentation.framework.NevbzFilterableComboBox",
				"name", ".cmbNiederlassung"));
		TextSelectGuiSubitemTestObject region = (TextSelectGuiSubitemTestObject) regions[0];
		
		TestObject[] dates = find(atDescendant(
				".class",
				"com.tmobile.itnetdev.fixedbuild.common.gui.components.ProDateField",
				"accessibleContext.accessibleName", "Date:"));
		TextGuiSubitemTestObject date = (TextGuiSubitemTestObject) dates[0];
		
		for (Map.Entry<String, String> mapEntry : dfSearchCriteria.entrySet()) {
			String key = mapEntry.getKey().toLowerCase();
			String value = mapEntry.getValue();
			
			if (key.contains("region")) {
				region.click();
				sleep(1);
				region.click(atText(value));
				sleep(1);
			}
			if (key.equals("date")) {
				date.setText(value);
				sleep(1);
			}
			sleep(1);
		}
		clickSearchButton();
		sleep(5);
		Utils.waitForScreenToClose("Dauer", 500);
		sleep(2);
		if (SysInfoDialog().exists()) {
			sleep(1);
			SysInfoDialogOk(ANY,MAY_EXIT).click();
			sleep(2);
		}
		return Const.SUCCESSMSG;
		
	}
	
	/**
	 * click new button
	 */
	public void clickNewButton() {
		sleep(1);
		super.clickNewButton();
		sleep(2);
	}

	/**
	 * Create new DF
	 * @param dfCreateCriteria
	 * @return
	 */
	public String createNewDF(HashMap<String, String> dfCreateCriteria) {
		Utils.waitForScreenToLoad("Distribution Frame", 10);
		if (dfCreateCriteria.containsKey("region")) {
			TextSelectGuiSubitemTestObject region = ((TextSelectGuiSubitemTestObject) Utils
					.getObj("name", ".cmbNiederlassung"));
			region.click();
			sleep(1);
			region.click(atText(dfCreateCriteria.get("region")));
			sleep(1);
		}
		if (dfCreateCriteria.containsKey("siteName")) {
			sleep(1);
			getTextFieldByName("ProDBTextField", ".edStandortname").setText(dfCreateCriteria.get("siteName"));
		}
		if (dfCreateCriteria.containsKey("indentCode")) {
			sleep(1);
			getTextFieldByName("ProDBTextField", ".edStandortNr").setText(dfCreateCriteria.get("indentCode"));
		}
		if (dfCreateCriteria.containsKey("roomNo")) {
			TextSelectGuiSubitemTestObject roomNo = ((TextSelectGuiSubitemTestObject) Utils
					.getObj("name", ".cmbRaumNr"));
			roomNo.click();
			sleep(1);
			roomNo.click(atText(dfCreateCriteria.get("roomNo")));
			sleep(1);
		}
		if (dfCreateCriteria.containsKey("roomNoDDF")) {
			TextSelectGuiSubitemTestObject roomNoDDF = ((TextSelectGuiSubitemTestObject) Utils
					.getObj("name", ".cmbRaumNrEVt"));
			roomNoDDF.click();
			sleep(1);
			roomNoDDF.click(atText(dfCreateCriteria.get("roomNoDDF")));
			sleep(1);
		}
		if (dfCreateCriteria.containsKey("row")) {
			sleep(1);
			getTextFieldByName("ProNumTextField", ".edReihe").setText(dfCreateCriteria.get("row"));
		}
		sleep(2);
		((GuiTestObject)Utils.getObj("name", ".btBearbeiten")).click();
		Utils.waitForElement(newPlate(), 15);
		if (editDialog().exists() && newPlate().exists()) {
			return Const.SUCCESSMSG;
		} else {
			return Const.NOTSUCCESS;
		}
	}

	/**
	 * 
	 * @param dfCriteria
	 * @return
	 */
	public String addNewPlate(HashMap<String, String> dfCriteria) {
		sleep(2);
		editDialog().click(atPoint(449,8));
		sleep(2);
		newPlate().click();
		sleep(2);
		if (dfCriteria.containsKey("bay")) {
			sleep(1);
			((TextGuiSubitemTestObject) Utils.getObj("name", ".edBucht"))
					.setText(dfCriteria.get("bay"));
		}
		if (dfCriteria.containsKey("placeNoFrom")) {
			sleep(1);
			((TextGuiSubitemTestObject) Utils.getObj("name", ".edPlatzNrVon"))
					.setText(dfCriteria.get("placeNoFrom"));
			sleep(1);
			((TextGuiSubitemTestObject) Utils.getObj("name", ".edPlatzNrBis"))
					.setText(dfCriteria.get("placeNoTo"));
			sleep(1);
		}
		if (dfCriteria.containsKey("plateType")) {
			sleep(1);
			detailButton2().click();
			configAssemblyGroupsDialog().click(atPoint(256,15));
			clickSearchButton();
			plateTypesTable().click(atCell(atRow("name",dfCriteria.get("plateType") ),atColumn("name")),atPoint(46,9));
			clickChooseButton();
		}else{
			sleep(1);
			detailButton2().click();
			configAssemblyGroupsDialog().click(atPoint(256,15));
			clickSearchButton();
			plateTypesTable().click(atCell(atRow(atIndex(0)),atColumn("name")),atPoint(46,9));
			clickChooseButton();
		}
		
		clickSaveButton();
		sleep(2);
		String temp = (String) changesSaved2().getProperty("text");
		for (int i = 0; i < 12 && !temp.equalsIgnoreCase(Const.CHANGESSAVED); i++) {
			sleep(5);
			temp = (String) changesSaved2().getProperty("text");
		}
		sleep(2);
		((GuiTestObject)Utils.getObj("name", ".btSchliessen")).click();
		sleep(2);
		editDialog().click(atPoint(449,8));
		if (temp.equalsIgnoreCase(Const.CHANGESSAVED)){
			return Const.SUCCESSMSG; 
		}else {
			return Const.NOTSUCCESS;
		}
		
	}

	public String verifyComments() {
		
		//Declare variables for tree
		ITestDataTree cdTree;
		ITestDataTreeNodes cdTreeNodes;
		ITestDataTreeNode[] cdTreeNode;

		//Variables to hold tree data
		cdTree = (ITestDataTree)proTree().getTestData("tree");
		cdTreeNodes = cdTree.getTreeNodes();
		cdTreeNode = cdTreeNodes.getRootNodes();
		//Print out total number of nodes
		System.out.println ("Tree Total Node Count: " + cdTreeNodes.getNodeCount());
		System.out.println ("Tree Root Node Count : " + cdTreeNodes.getRootNodeCount());

		//Iterate through tree branches; this is a recursive method.
		
		proTree().click(atList(atRow(4))); 

		for (int i = 0;i<cdTreeNode.length;++i)
		showTree(cdTreeNode[i], 0);
		for (int i = 0; i < cdTreeNodes.getNodeCount(); i++) {
			String[] tempArray = myArray[i].split("@");
			int tempInt = Integer.parseInt(tempArray[1]);
			for (int j = 0; j < tempInt; j++) {
				
			}
			
		}
		return "";
		}
	String[] myArray = new String [50];
	int counter = 0;
	// String of tabs used to indent tree view
		final String tabs = "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t";
	void showTree(ITestDataTreeNode node, int indent) {
		// Determine number of tabs to use - to properly indent tree
		int tabCount = (indent < tabs.length() ? indent : tabs.length());

		System.out.println
		(tabs.substring(0, tabCount) + node.getNode() + " ("+ node.getChildCount() + "children)");
		myArray[counter] = node.getNode().toString() + "@"+tabCount; 
		counter = counter +1;
		
		// Determine if node has children; recursively call this same
		// method to print out child nodes.
		ITestDataTreeNode[] children = node.getChildren();
		int childCount = (children != null ? children.length : 0);
		for (int i = 0; i < childCount; ++i){
			showTree(children[i], indent + 1);
		}
	}
}
