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
}
