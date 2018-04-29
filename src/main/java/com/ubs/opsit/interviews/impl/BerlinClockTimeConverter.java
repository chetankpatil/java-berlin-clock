package com.ubs.opsit.interviews.impl;

import java.util.Collections;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import org.codehaus.plexus.util.StringUtils;

import com.ubs.opsit.interviews.TimeConverter;

public class BerlinClockTimeConverter implements TimeConverter {

	@Override
	public String convertTime(final String aTime) {
		if (StringUtils.isEmpty(aTime) || !(Pattern.matches("([01]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]", aTime))) {
			throw new IllegalArgumentException("Incorrect time provided, aTime = " + aTime);
		}

		final int[] units = Stream.of(aTime.split(":")).mapToInt(Integer::parseInt).toArray();

		final StringBuilder result = new StringBuilder();
		result.append(getSeconds(units[2])).append("\n");
		result.append(getHours(units[0])).append("\n");
		result.append(getMinutes(units[1]));

		return result.toString();
	}

	private String getHours(final int hours) {
		return new StringBuilder(String.join("", Collections.nCopies(hours / 5, "R"))).append(String.join("", Collections.nCopies(4 - (hours / 5), "O"))).append("\n")
				.append(String.join("", Collections.nCopies(hours % 5, "R"))).append(String.join("", Collections.nCopies(4 - (hours % 5), "O"))).toString();
	}

	private String getMinutes(final int minutes) {
		final String row1 = new StringBuilder(String.join("", Collections.nCopies(minutes / 5, "Y"))).append(String.join("", Collections.nCopies(11 - (minutes / 5), "O")))
				.append("\n")
				.toString().replaceAll("YYY", "YYR");
		final StringBuilder row2 = new StringBuilder(row1).append(String.join("", Collections.nCopies(minutes % 5, "Y")))
				.append(String.join("", Collections.nCopies(4 - (minutes % 5), "O")));
		return row2.toString();
	}

	private String getSeconds(final int seconds) {
		return (seconds % 2) == 0 ? "Y" : "O";
	}

}
