package com.ubs.opsit.interviews.impl;

import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.ubs.opsit.interviews.TimeConverter;


public class BerlinClockTimeConverterTest {

	@Rule
	public final ExpectedException thrown = ExpectedException.none();

	@Test
	public void convertEmpty() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("Incorrect time provided, aTime = ");

		final TimeConverter tc = new BerlinClockTimeConverter();
		tc.convertTime(StringUtils.EMPTY);
	}

	@Test
	public void convertIncorrectTime() {
		final String inputTime = "23:59:60";

		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("Incorrect time provided, aTime = " + inputTime);

		final TimeConverter tc = new BerlinClockTimeConverter();
		tc.convertTime(inputTime);
	}

	@Test
	public void convertTime() {
		final TimeConverter tc = new BerlinClockTimeConverter();

		String inputTime = "00:00:00";
		String expected = "Y\nOOOO\nOOOO\nOOOOOOOOOOO\nOOOO";
		Assert.assertEquals(expected, tc.convertTime(inputTime));

		inputTime = "13:32:58";
		expected = "Y\nRROO\nRRRO\nYYRYYROOOOO\nYYOO";
		Assert.assertEquals(expected, tc.convertTime(inputTime));

		inputTime = "23:59:59";
		expected = "O\nRRRR\nRRRO\nYYRYYRYYRYY\nYYYY";
		Assert.assertEquals(expected, tc.convertTime(inputTime));
	}

}
