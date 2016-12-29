package nl.tinkoczy.villa.util;

import static java.time.temporal.TemporalAdjusters.firstDayOfYear;
import static java.time.temporal.TemporalAdjusters.lastDayOfYear;

import java.time.LocalDate;

public final class DatumUtil {

	private DatumUtil() {
		// static
	}

	/**
	 * @return the first day of the current year. For 2016 it will return
	 *         2016-01-01
	 */
	public static LocalDate getFistDayOfYear() {
		return getFistDayOfYear(LocalDate.now());
	}

	/**
	 * @return the last day of the current year. For 2016 it will return
	 *         2016-12-31
	 */
	public static LocalDate getLastDayOfYear() {
		return getLastDayOfYear(LocalDate.now());
	}

	/**
	 * @param localDate
	 * @return the first day of the date passed in the parameter. For 2016-04-25
	 *         it will return 2016-01-01
	 */
	public static LocalDate getFistDayOfYear(final LocalDate localDate) {
		return localDate.with(firstDayOfYear());
	}

	/**
	 * @param localDate
	 * @return the last day of the date passed in the parameter. For 2016-04-25
	 *         it will return 2016-12-31
	 */
	public static LocalDate getLastDayOfYear(final LocalDate localDate) {
		return localDate.with(lastDayOfYear());
	}

}
