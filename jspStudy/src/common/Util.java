package common;

import java.util.Calendar;

public class Util {
	public int[] getDateTime() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int min = cal.get(Calendar.MINUTE);
		int sec = cal.get(Calendar.SECOND);
		
		int[] result = new int[6];
		result[0] = year;
		result[1] = month;
		result[2] = day;
		result[3] = hour;
		result[4] = min;
		result[5] = sec;
		return result;
	}

	public int numberCheck(String pageNumStr, int defaultNumber) {
		String defaultPageNumber = String.valueOf(defaultNumber);
		
		if (pageNumStr == null || pageNumStr.trim().equals("")) {
			pageNumStr = defaultPageNumber;
		}
		
		try {
			Double.parseDouble(pageNumStr);
			return Integer.parseInt(pageNumStr);
		} catch (Exception e) {
			return Integer.parseInt(defaultPageNumber);
		}
	}

	public String list_gubunCheck(String list_gubun) {
		if (list_gubun == null || list_gubun.trim().equals("")) {
			list_gubun = "all";
		}
		
		list_gubun = list_gubun.trim();
		if (list_gubun.equals("all")) {} 
		else if (list_gubun.equals("ing")) {} 
		else if (list_gubun.equals("end")) {} 
		else { list_gubun = "all"; }
		
		return list_gubun;
	}

	public String[] searchCheck(String search_option, String search_data, String search_date_start, String search_date_end, String search_date_check) {
		String[] result = new String[5];
		
		if (search_option == null || search_option.trim().equals("")) {
			search_option = "";
		}
		search_option = search_option.trim();
		
		if (search_option.equals("")) {}
		else if (search_option.equals("question")) {}
		else { search_option = ""; }
		
		if (search_data == null || search_data.trim().equals("")) {
			search_data = "";
		}
		search_data = search_data.trim();
		
		if (search_option.equals("") || search_data.equals("")) {
			search_option = "";
			search_data = "";
		}
		
		if (search_date_start == null || search_date_start.trim().equals("")) {
			search_date_start = "";
		}
		search_date_start = search_date_start.trim();
		
		if (search_date_end == null || search_date_end.trim().equals("")) {
			search_date_end = "";
		}
		search_date_end = search_date_end.trim();
		
		if (search_date_start.equals("") || search_date_end.equals("")) {
			search_date_check = "";
		}
		
		result[0] = search_option;
		result[1] = search_data;
		result[2] = search_date_start;
		result[3] = search_date_end;
		result[4] = search_date_check;
		
		return result;
	}
}
