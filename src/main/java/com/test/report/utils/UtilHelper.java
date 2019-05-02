package com.test.report.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;

import com.test.basic.Currency;

public class UtilHelper {
	public static final SimpleDateFormat DDMMMYYYY = new SimpleDateFormat("dd MMM yyyy");
	public static final SimpleDateFormat EE = new SimpleDateFormat("EE");

	/**
	 * Get next working day for given date based on currency specific weekends.
	 */
	public static Date getNextWorkingDay(Date date, Currency currency) {
		String day = EE.format(date);
		int additionalDays = currency.getWeekEndDays().indexOf(day) + 1;
		return DateUtils.addDays(date, additionalDays);
	}
}
