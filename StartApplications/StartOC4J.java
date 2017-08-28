package PB_2015_2.StartApplications;

import java.awt.Rectangle;
import java.util.ArrayList;

import resources.PB_2015_2.StartApplications.StartOC4JHelper;
import PB_2015_2.Common.Const;
import PB_2015_2.Common.Utils;
import PB_2015_2.Main.ScriptHandler;
import PB_2015_2.Verification.Verify;

import com.rational.test.ft.object.interfaces.GuiTestObject;
import com.rational.test.ft.object.interfaces.RootTestObject;
import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.TextGuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.rational.test.ft.script.RunException;
import com.rational.test.ft.value.RegularExpression;

public class StartOC4J extends StartOC4JHelper {

	String appName = "FB_2016.1_TMA_Batch";
//	String appName = "FB_2016.2_Batch";
//	String appName = "FB_2016.2_Batch_TSLPXM14";
	
	RootTestObject root = RootTestObject.getRootTestObject();
	RegularExpression mainFrameClassRE = new RegularExpression(
			".*mainframe.MainFrame", false);
	RegularExpression mainFrameCaptionTextRE = new RegularExpression(
			"PegaBase.*", false);

	public void testMain(Object[] args) throws RunException{

		if (args.length > 0) {
			appName = args[0].toString();
		}

		if (checkAndKill()) {
			Const.pte = startApp(appName);
			sleep(5);
			TopLevelTestObject loginDialog = (TopLevelTestObject) Utils
					.getObj(".class",
							"com.tmobile.itnetdev.pegabase.application.gui.view.DialogLogin");

			for (int i = 0; i < 10 && loginDialog == null; i++) {
				sleep(10);
				loginDialog = (TopLevelTestObject) Utils
						.getObj(".class",
								"com.tmobile.itnetdev.pegabase.application.gui.view.DialogLogin");
			}
			((TextGuiSubitemTestObject) Utils.getObj(".class",
					"com.tmobile.itnetdev.guicomponents.components.PTextField",
					".priorLabel", "Password")).setText(Global.User.account);
			sleep(1);
			((TextGuiSubitemTestObject) Utils
					.getObj(".class",
							"com.tmobile.itnetdev.guicomponents.components.PPasswordField",
							"toolTipText", "Password"))
					.setText(Global.User.password);
			sleep(1);
			Utils.getGuiTestObjByToolTip("Login [Alt+L]").click();
			sleep(10);
			TestObject[] dialog = root.find(atDescendant(".captionText",
					mainFrameCaptionTextRE, ".class", mainFrameClassRE));
			
			ArrayList<String> allDialogs = Utils.getDialogTitles();
			if (allDialogs.size() >= 2 || allDialogs.contains("error")) {
				System.out.println("Login Error Found : " + allDialogs);
				if (allDialogs.contains("error")) {
					String errorMessage = (String) ((GuiTestObject) Utils
							.getObj(".class", "javax.swing.JLabel"))
							.getProperty("text");
					Verify.failDueToUnexpectedError(errorMessage
							+ "  Error found while Login "
							+ "  CLOSING EXECUTION ... ");
					sleep(2);
					ScriptHandler.vpDetails
							.add("Unable to Open the application due to "+errorMessage);
					ScriptHandler.vpDetailsAssertation.add(false);
					throw new RunException("System Exit");
				}
				checkAndKill();
				
			} else {
				for (int i = 0; i < 10 && dialog.length == 0; i++) {
					sleep(10);
//					System.out.println("Sleep : " + i);
					dialog = root
							.find(atDescendant(".captionText",
									mainFrameCaptionTextRE, ".class",
									mainFrameClassRE));
				}
			}
			if (dialog.length > 0) {
				if (dialog[0].getDescriptiveName().contains(
						appName.split("_")[1])) {
//					System.out.println("Application Opened Properly");
					maximizeScreen();
				}
			} else {
				System.out
						.println("Unable to open application.. please check it once.. ");
			}
		}
	}

	private void maximizeScreen() {
		TopLevelTestObject mainFrame = (TopLevelTestObject) Utils
				.getObj(".class",
						"com.tmobile.itnetdev.pegabase.application.gui.view.mainframe.MainFrame");
		Utils.closeAll();
		try {
			Rectangle rect = mainFrame.getScreenRectangle();
//			System.out.println(rect.width + "   " + rect.height);
			if (rect.width < 1296) {
				mainFrame.click(atPoint(677, 18));
				sleep(1);
				mainFrame.doubleClick(atPoint(677, 18));
				sleep(5);
			}
		} catch (Exception e) {
			e.printStackTrace();
			mainFrame.inputKeys("{ESC}");
			sleep(1);
		}
	}
	
	private boolean checkAndKill(){
		boolean openApplication = true;
		String whichApp = "";
		
		TestObject[] dialog = root.find(atDescendant(".captionText",
				mainFrameCaptionTextRE, ".class", mainFrameClassRE));

		for (int i = 0; i < dialog.length; i++) {
			if (appName.startsWith("R"))
				whichApp = "/radio";
			else
				whichApp = "/fixed & /build";
			if (dialog[i].getDescriptiveName().contains(appName.split("_")[1])) {
				if (dialog[i].getDescriptiveName().contains(whichApp)) {
					Const.pte = dialog[i].getProcess();
					root.enableForTesting(dialog[i].getProcess().getProcessId());
					Utils.setAppVersion();
					maximizeScreen();
					callScript("PB_2015_2.UnexpectedWindow.UnexpectedWindow");
					openApplication = false;
				} else {
					dialog[i].getProcess().kill();
					sleep(2);
				}
			} else {
				dialog[i].getProcess().kill();
				sleep(2);
			}
		}

		TestObject[] loginDialogs = root
				.find(atDescendant(".class",
						"com.tmobile.itnetdev.pegabase.application.gui.view.DialogLogin"));
		for (int i = 0; i < loginDialogs.length; i++) {
			loginDialogs[i].getProcess().kill();
			sleep(2);
		}
		return openApplication;
	}
}
