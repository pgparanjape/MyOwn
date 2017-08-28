package PB_2015_2.FB.Actions;

import java.util.HashMap;

import PB_2015_2.Common.Const;
import PB_2015_2.Common.Utils;
import PB_2015_2.FB.ObjRepo.FBRepo;

import com.rational.test.ft.object.interfaces.GuiTestObject;

public class NEGroupActions extends FBRepo {

	public boolean visible = true;

	public String createNewNEGroup(HashMap<String, String> neGroupCreateCriteria, String neGroupName) {
		sleep(2);
		if (neGroupCreateCriteria.get("Category") != null) {
			NewNEGroupCategoryListBox().click();
			NewNEGroupCategoryListBox().click(atText(neGroupCreateCriteria.get("Category")));
			sleep(2);
		}
		if (neGroupCreateCriteria.get("Type") != null) {
			NewNEGroupTypeListBox().click();
			NewNEGroupTypeListBox().click(atText(neGroupCreateCriteria.get("Type")));
			sleep(2);
		}
		name().setText(neGroupName);
		sleep(2);
		if (neGroupCreateCriteria.get("SiteAmount") != null) {
			NewNEGroupSiteAmount().click();
			NewNEGroupSiteAmount().click(atText(neGroupCreateCriteria.get("SiteAmount")));
			sleep(2);
		}
		if (neGroupCreateCriteria.get("StartDate") != null) {
			NewNEGroupStartDate().setText(neGroupCreateCriteria.get("StartDate"));
			sleep(2);
		}
		if (neGroupCreateCriteria.get("EndDate") != null) {
			NewNEGroupEndDate().setText(neGroupCreateCriteria.get("EndDate"));
			sleep(2);
		}
		if (visible) {
			NewNEGroupInUse().clickToState(SELECTED);
		}else{
			NewNEGroupInUse().clickToState(NOT_SELECTED);
		}
		sleep(2);
		if (getSaveButtonStatus()) {
			clickSaveButton();
			sleep(5);
			if (error().exists()){
				sleep(1);
				String errorText = (String)ErrorText().getProperty("text");
				((GuiTestObject) Utils.getObj(
						"accessibleContext.accessibleName", "OK")).click();
				sleep(1);
				return Utils.removeHtml(errorText);
			}
			return Const.SUCCESSMSG;
		} else {
			return "Save Button Disabled";
		}
	}
	
	public void openSearchGroup() {
		sleep(2);
		pMenuBar().click(atPath("NE-Groups"));
		sleep(1);
		pMenuBar().click(atPath("NE-Groups->NE-Gruppen"));
		sleep(2);
		Utils.waitForScreenToLoad("Select Groups [NE-Gruppen]", 30);
	}
	
	/*public void clickNewButton() {
		sleep(1);
		super.clickNewButton();
		sleep(2);
	}*/

	public String getNodeID() {
		sleep(2);
		String NodeID_text = (String)NewNEGroupNodeID().getProperty("text");
		sleep(2);
		return NodeID_text;
	}
	
	public String getNeGroupName() {
		sleep(2);
		String NodeID_text = (String)name().getProperty("text");
		sleep(2);
		return NodeID_text;
	}

	public String checkVisibility(String neGroupName) {
		sleep(2);
		pMenuBar().click(atPath("Network Elements"));
		sleep(1);
		pMenuBar().click(atPath("Network Elements->Edit"));
		Utils.waitForScreenToLoad("Select Network Elements", 30);

		/*
		 * TODO : need to add the code for 2016 onwards, as the edit NE mask has
		 * beed changed. unable to find detailButton() in 2016 onwards
		 */
		
		detailButton().click();
		sleep(10);
		selectGroups().click();
		name2().click(atPoint(77,9));
		sleep(1);
		name2().setText(neGroupName);
		sleep(1);
		clickSearchButton();
		sleep(5);
		if (SysInfoDialog().exists()){
			SysInfoDialogOk().click();
			sleep(2);
			closeAll();
			return Const.NOTSUCCESS;
		}else{
			Utils.waitForScreenToLoad("Select Network Elements", 30);
			String NEGroup_text = (String)neGroup().getProperty("text");
			if (NEGroup_text.equalsIgnoreCase(neGroupName)){
				closeAll();
				return Const.SUCCESSMSG;
			}else{
				closeAll();
				return Const.NOTSUCCESS;
			}
		}
	}
}
