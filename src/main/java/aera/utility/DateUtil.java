package aera.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author Arul Christo C T
 *
 */
public class DateUtil {
	/**
	 * This is an utility method to calculate the date from current date based on the
	 * input integer parameter
	 * 
	 * @param currentDate
	 *            - current date from the application
	 * @param noOfDays-
	 *            no of days to be added to the current date
	 * @return currentDate - updated date wrt noOfDays parameter
	 * @throws ParseException
	 */
	public String dateCalculation(String currentDate, int noOfDays) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Calendar c = Calendar.getInstance();
		c.setTime(sdf.parse(currentDate));
		c.add(Calendar.DATE, noOfDays); // number of days to add
		currentDate = sdf.format(c.getTime()); // currentDate is now the new date
		return currentDate;
	}
}
