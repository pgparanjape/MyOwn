package PB_2015_2.UnexpectedWindow;

import java.io.File;

import javax.imageio.ImageIO;

import resources.PB_2015_2.UnexpectedWindow.UnexpectedWindowHelper;
import Global.Paths;
import PB_2015_2.Common.Utils;
import PB_2015_2.Verification.Verify;

import com.rational.test.ft.object.interfaces.GuiTestObject;
import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.TextGuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.TopLevelTestObject;

public class UnexpectedWindow extends UnexpectedWindowHelper {

	public void testMain(Object[] args) {

		TestObject[] informationButtons = find(atDescendant("accessibleContext.accessibleName", "Information", "name","OptionPane.button"));
		TestObject[] radioClose = find(atDescendant(".class", "com.tmobile.itnetdev.guicomponents.plaf.PInternalFrameTitlePane$NoFocusButton"));
		TestObject[] saveChangesNoButtons = find(atDescendant("accessibleContext.accessibleName", "No", "name","OptionPane.button"));
		TestObject[] loginDialogs = find(atDescendant("class", "com.tmobile.itnetdev.pegabase.application.gui.view.DialogLogin"));

		try {
			ImageIO.write(getRootTestObject().getScreenSnapshot(), "png",
					new File(Paths.screenGrabFolderPath + args[0].toString()
							+ ".png"));
		} catch (Exception e) {}

		for (int i = 0; i < loginDialogs.length; i++) {
			TopLevelTestObject loginDialog = (TopLevelTestObject) loginDialogs[i];
			loginDialog.close();
			sleep(2);
		}

		try {
			for (int i = 0; i < saveChangesNoButtons.length; i++) {
				GuiTestObject abc = (GuiTestObject) saveChangesNoButtons[i];
				abc.click();
			}
			for (int i = 0; i < radioClose.length; i++) {
				GuiTestObject abc = (GuiTestObject) radioClose[i + 2];
				abc.click();
			}
			TopLevelTestObject errorMessage = (TopLevelTestObject) Utils
					.getObj(".class",
							"com.tmobile.itnetdev.framework.gui.view.swing.ErrorDialog");
			if (errorMessage != null) {
				TestObject[] textAreas = find(atDescendant(".class",
						"com.tmobile.itnetdev.guicomponents.components.PTextArea"));
				for (int j = 0; j < textAreas.length; j++) {
					TextGuiSubitemTestObject textArea = (TextGuiSubitemTestObject) textAreas[j];
					if (textArea.exists() && textArea.isShowing()) {
						Verify.failDueToUnexpectedError(textArea.getText());
						break;
					}
				}
				sleep(2);
				ok2().click();
				sleep(2);
			}

			for (int i = 0; i < informationButtons.length; i++) {
				GuiTestObject informationButton = (GuiTestObject) informationButtons[i];
				if (informationButton.isEnabled()&& informationButton.isShowing()) {
					informationButton.click();
					sleep(2);
					TestObject[] textAreas = find(atDescendant(".class", "com.tmobile.itnetdev.guicomponents.components.PTextArea"));
					for (int j = 0; j < textAreas.length; j++) {
						TextGuiSubitemTestObject textArea = (TextGuiSubitemTestObject) textAreas[j];
						if (textArea.exists() && textArea.isShowing()){
							Verify.failDueToUnexpectedError(textArea.getText());
							break;
						}
					}
					sleep(2);
					ok2().click();
					sleep(2);
					GuiTestObject cancelBtn = (GuiTestObject) Utils.getObj(
							"accessibleContext.accessibleName", "Cancel");
					if (cancelBtn != null)
						cancelBtn.click();
					sleep(2);
				}
				sleep(2);
			}
			sleep(2);
			TestObject[] closeWithoutToolTip = find (atDescendant("iconDescription", "sexit.gif"));
			TestObject[] closeButtons = find(atDescendant("toolTipText", "Close [Alt+L]"));
			TestObject[] okButtons = find(atDescendant("accessibleContext.accessibleName", "OK", "name","OptionPane.button"));
			sleep(2);

			if (okButtons.length >= 1) {
				for (int i = 0; i < okButtons.length; i++) {
					if (((GuiTestObject) okButtons[i]).isEnabled()
							&& ((GuiTestObject) okButtons[i]).isShowing()) {
						((GuiTestObject) okButtons[i]).click();
						sleep(2);
					}
					sleep(2);
				}
			}

			for (int i = 0; i < closeButtons.length; i++) {
				GuiTestObject abc = (GuiTestObject) closeButtons[i];
				if (abc.isEnabled() && abc.isShowing() && abc.exists()) {
					abc.click();
					TestObject[] tobs3 = find(atDescendant("accessibleContext.accessibleName", "No", "name","OptionPane.button"));
					if (tobs3.length >= 1) {
						System.out.println("tobs3.length   " + tobs3.length);
						((GuiTestObject) tobs3[0]).click();
					}
					sleep(2);
				}
			}

			for (int i = 0; i < closeWithoutToolTip.length; i++) {
				GuiTestObject abc = (GuiTestObject) closeWithoutToolTip[i];
				if (abc.isEnabled() && abc.isShowing() && abc.exists()) {
					abc.click();
					sleep(2);
					closeWithoutToolTip = find (atDescendant("iconDescription", "sexit.gif"));
					if (closeWithoutToolTip.length >= 1) {
						((GuiTestObject) closeWithoutToolTip[0]).click();
					}
					sleep(2);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error while closing the open windows please check the logs.. ");
		}
	}
}
