package PB_2015_2.FB.Actions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import PB_2015_2.Common.Const;
import PB_2015_2.Common.Utils;
import PB_2015_2.FB.ObjRepo.FBRepo;

import com.rational.test.ft.object.interfaces.GuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.GuiTestObject;
import com.rational.test.ft.object.interfaces.TextGuiSubitemTestObject;
import com.rational.test.ft.vp.ITestDataTable;

public class OrderActions extends FBRepo implements FB{

	@Override
	public String search(HashMap<String, String> searchCriteria) {
		
		if (Utils.waitForScreenToLoad("Cell-Orders select", 2)) {
			Utils.closeAll();
		}
		pMenuBar().click(atPath("Orders"));
		sleep(1);
		pMenuBar().click(atPath("Orders->Cell-Orders"));
		Utils.waitForScreenToLoad("Cell-Orders select", 10);
		if (searchCriteria.get("IndentCode") != null) {
			((TextGuiSubitemTestObject) Utils.getObj("name",
					".proDBTextFieldKng")).setText(searchCriteria
					.get("IndentCode"));
			sleep(1);
		}
		((GuiTestObject)Utils.getObj("name", ".jButtonSelect")).click();
		
		return null;
	}
	
	public int getSearchListCount() {
		Utils.waitForScreenToLoad("Cell-Orders select", 10);
		GuiSubitemTestObject neListTable = (GuiSubitemTestObject) Utils
				.getObj(".class",
						"com.tmobile.itnetdev.fixedbuild.nevbz.presentation.framework.NeVbzTable$Table");
		ITestDataTable myTable = (ITestDataTable) neListTable
				.getTestData("contents");
		return myTable.getRowCount();
	}

	@Override
	public String openFromSearchList(String whichRow) {
		Utils.waitForScreenToLoad("Cell-Orders select", 10);
		GuiSubitemTestObject orderListTable = (GuiSubitemTestObject) Utils
				.getObj(".class",
						"com.tmobile.itnetdev.fixedbuild.nevbz.presentation.framework.NeVbzTable$Table");
		ITestDataTable myTable = (ITestDataTable) orderListTable
				.getTestData("contents");
		int rowNo = 0;
		try {
			rowNo = Integer.parseInt(whichRow);
		} catch (Exception ex) {
			for (int row = 0; row < myTable.getRowCount(); row++) {
				for (int col = 0; col < myTable.getColumnCount(); col++) {
					try {
						String temp = (String) myTable.getCell(row, col);
						if (temp.equalsIgnoreCase(whichRow)) {
							rowNo = row;
						}
					} catch (Exception e) {
					}
				}
			}
		}

		orderListTable
				.click(atCell(atRow(atIndex(rowNo)), atColumn(atIndex(0))));
		sleep(2);
		((GuiTestObject) Utils.getObj("name", ".jButtonEdit")).click();
		sleep(2);
		Utils.waitForScreenToLoad("edit cell-order", 60);
		return Const.SUCCESSMSG;
	}

	public ArrayList<String> getOrderParamValue(String whichParam,
			String whichValue) {
		int coulumn;
		if (whichValue.equalsIgnoreCase("target")) {
			coulumn = 2;
		} else {
			coulumn = 1;
		}

		ArrayList<String> returnArrayList = new ArrayList<String>();
		Utils.waitForScreenToLoad("edit cell-order", 10);
		GuiSubitemTestObject paramTable = ((GuiSubitemTestObject) Utils.getObj(
				"name", ".tableParameter"));
		ITestDataTable myTable = (ITestDataTable) paramTable
				.getTestData("contents");
		String[] whichParamArray = whichParam.split(":");
		for (int i = 0; i < whichParamArray.length; i++) {
			for (int row = 0; row < myTable.getRowCount(); row++) {
				try {
					String temp = (String) myTable.getCell(row, 0);
					// System.out.println(temp);
					if (temp.equalsIgnoreCase(whichParamArray[i])) {
						try {
							returnArrayList.add((String) myTable.getCell(row,
									coulumn));
						} catch (ClassCastException e) {
							SimpleDateFormat ft = new SimpleDateFormat(
									"ddMMyyyy");
							returnArrayList.add(ft.format(myTable.getCell(row,
									coulumn)));
						}
					}
				} catch (Exception e) {
				}
			}
		}
		return returnArrayList;
	}
	
	public ArrayList<String> getColumnValuesSearchList(int whichColumn) {
		ArrayList<String> retArrayList = new ArrayList<String>();
		Utils.waitForScreenToLoad("Cell-Orders select", 10);
		GuiSubitemTestObject orderListTable = (GuiSubitemTestObject) Utils
				.getObj(".class",
						"com.tmobile.itnetdev.fixedbuild.nevbz.presentation.framework.NeVbzTable$Table");
		ITestDataTable myTable = (ITestDataTable) orderListTable
				.getTestData("contents");
		for (int row = 0; row < myTable.getRowCount(); row++) {
			try {
				retArrayList.add((String) myTable.getCell(row, whichColumn));
			} catch (ClassCastException e) {
				SimpleDateFormat ft = new SimpleDateFormat("ddMMyyyy");
				retArrayList.add(ft.format(myTable.getCell(row, whichColumn)));
			} catch (Exception e) {
			}
		}
		sleep(2);
		return retArrayList;
	}

	@Override
	public String createNew(HashMap<String, String> createCriteria) {
		return Const.NOTSUCCESS;
	}
}
