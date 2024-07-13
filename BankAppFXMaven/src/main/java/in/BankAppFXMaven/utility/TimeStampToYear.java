package in.BankAppFXMaven.utility;

import java.time.LocalDate;

/**
 * This class extracts the year from a TimeStamp for GUI display
 */
public class TimeStampToYear {

	/**
	 * @param timestamp to transform to String
	 * @return String of timestamp
	 */
	public static String timeStampToYearString(java.sql.Timestamp timestamp) {
		LocalDate date = timestamp.toLocalDateTime().toLocalDate();
		return date.toString();
	}
	
	/**
	 * @param timestamp
	 * @param days to add to timestamp
	 * @return LocalDate of timestamp
	 */
	public static LocalDate addDaysToDate(java.sql.Timestamp timestamp, long days) {
		LocalDate date = timestamp.toLocalDateTime().toLocalDate().plusDays(days);
		return date;
	}
}
