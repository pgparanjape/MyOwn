package PB_2015_2.FB.Actions;

import java.util.HashMap;

public interface FB {
	
	public String search(HashMap<String, String> searchCriteria);
	public int getSearchListCount();
	public String openFromSearchList(String whichRow);
	public String createNew(HashMap<String, String> createCriteria);
}
