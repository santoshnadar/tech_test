package com.test.basic;

import java.util.Arrays;
import java.util.List;

public enum Currency {

	SGP(Arrays.asList("Sun","Sat")), AED(Arrays.asList("Sat","Fri")), SAR(Arrays.asList("Sat","Fri"));
	
	/**
	 * Weekend days for the currency. 
	 * 
	 * Order of Weekend days are reversed so that index can used to calculate next working day.
	 * 
	 * E.g. Arrays.asList("Sun","Sat")
	 * Next working day from "Sun" is Index=0 + 1 day
	 * Next working day from "Sat" is Index=1 + 1 day
	 * 
	 * This will make the calculation easier
	 */
	private List<String> weekEndDays;

	public List<String> getWeekEndDays() {
		return weekEndDays;
	}

	public void setWeekEndDays(List<String> weekEndDays) {
		this.weekEndDays = weekEndDays;
	}

	private Currency(List<String> weekEndDays) {
		this.weekEndDays = weekEndDays;
	}

}
