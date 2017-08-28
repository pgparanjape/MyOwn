package PB_2015_2.UnexpectedWindow;

import resources.PB_2015_2.UnexpectedWindow.CloseMeHelper;
import PB_2015_2.Common.Const;

import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.value.RegularExpression;

public class CloseMe extends CloseMeHelper {
	public void testMain(Object[] args) {
		sleep(2);
		try {
			if (quitApplicationPegabaseAltQ().exists()) {
				
				pMenuBar2().click(atPath("File"));
				sleep(1);
				pMenuBar2().click(atPath("File->Exit"));
				sleep(5);
				// jDialog().waitForExistence();
				if (yes2().exists()) {
					yes2(ANY, MAY_EXIT).click();
				}
				sleep(3);

			} else if (projectsAltA().exists()) {
				try {
					sleep(1);
					pMenuBar().click(atPath("Windows"));
					sleep(1);
					pMenuBar().click(atPath("Windows->Close All"));
					sleep(1);
					pMenuBar().click(atPath("System"));
					sleep(1);
					pMenuBar().click(atPath("System->Quit"));
					sleep(5);
					// pOptionPane().waitForExistence();
					if (yes().exists()) {
						yes(ANY, MAY_EXIT).click();
					}
					sleep(3);
				} catch (Exception e) {
					pMenuBar().click(atPath("System"));
					pMenuBar().click(atPath("System->Quit"));
					sleep(5);
					// pOptionPane().waitForExistence();
					if (yes().exists()) {
						yes(ANY, MAY_EXIT).click();
					}
					sleep(3);
				}
			}
		} catch (Exception e) {
			try {
				Const.pte.kill();
				sleep(5);
			} catch (Exception e1) {
				System.out.println("PTE. kill error");
				RegularExpression mainFrameClassRE = new RegularExpression(
						".*mainframe.MainFrame", false);
				TestObject[] dialog = find(atDescendant(".class", mainFrameClassRE));
				
				for (int i = 0; i < dialog.length; i++) {
					dialog[i].getProcess().kill();
				}
			}
		
		}
	}
}
