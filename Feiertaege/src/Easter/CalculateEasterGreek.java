package Easter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import HighlightEvaluator.HighlightEvaluatorGreece;

public class CalculateEasterGreek {

	@SuppressWarnings("deprecation")
	public static String getOrthodoxEaster(int myear) {
		Calendar dof = Calendar.getInstance();
		String month;
		String day;
		String result;
		int r1 = myear % 4;
		int r2 = myear % 7;
		int r3 = myear % 19;
		int r4 = (19 * r3 + 15) % 30;
		int r5 = (2 * r1 + 4 * r2 + 6 * r4 + 6) % 7;
		int mdays = r5 + r4 + 13;

		if (mdays > 39) {
			mdays = mdays - 39;
			dof.set(myear, 4, mdays);
		} else if (mdays > 9) {
			mdays = mdays - 9;
			dof.set(myear, 3, mdays);
		} else {
			mdays = mdays + 22;
			dof.set(myear, 2, mdays);
		}
		month = String.valueOf(dof.getTime().getMonth() + 1);
		day = String.valueOf(dof.getTime().getDate());

		if (!month.startsWith("1")) {

			month = "0" + month;
		}
		if (!(day.startsWith("1") || day.startsWith("2") || day.startsWith("3"))) {
			day = "0" + day;
		}
		result = month + "-" + day;
		return result;
	}

	public static String getEasterSundayDate(int year) {
		int a = year % 19, b = year / 100, c = year % 100, d = b / 4, e = b % 4, g = (8 * b + 13) / 25,
				h = (19 * a + b - d - g + 15) % 30, j = c / 4, k = c % 4, m = (a + 11 * h) / 319,
				r = (2 * e + 2 * j - k - h + m + 32) % 7, n = (h - m + r + 90) / 25, p = (h - m + r + n + 19) % 32;

		String result;
		switch (n) {
		case 1:
			result = "01-";
			break;
		case 2:
			result = "02-";
			break;
		case 3:
			result = "03-";
			break;
		case 4:
			result = "04-";
			break;
		case 5:
			result = "05-";
			break;
		case 6:
			result = "06-";
			break;
		case 7:
			result = "07-";
			break;
		case 8:
			result = "08-";
			break;
		case 9:
			result = "09-";
			break;
		case 10:
			result = "10-";
			break;
		case 11:
			result = "11-";
			break;
		case 12:
			result = "12-";
			break;
		default:
			result = "error";
		}

		String date = String.valueOf(p);
		if (!(date.startsWith("1") || date.startsWith("2") || date.startsWith("3"))) {
			date = "0" + date;
		}
		return result + date;
	}

	public static HighlightEvaluatorGreece FindEaster(int pYear, HighlightEvaluatorGreece pEvaluator) {

		String dateEaster = CalculateEasterGreek.getOrthodoxEaster(pYear);
		String[] splitString = dateEaster.split("-");

		LocalDateTime Easter = LocalDateTime.of(pYear, Integer.valueOf(splitString[0]), Integer.valueOf(splitString[1]),
				00, 00);

		Date date = convertToDateViaInstant(Easter);

		pEvaluator.add(date);

		pEvaluator.getSpecialBackroundColor();
		return pEvaluator;
	}

	public static HighlightEvaluatorGreece FindTheVacationDayBeforeEaster(int pYear,
			HighlightEvaluatorGreece pEvaluator, int pDays) {

		String dateEaster = CalculateEasterGreek.getOrthodoxEaster(pYear);
		String[] splitString = dateEaster.split("-");

		LocalDateTime Easter = LocalDateTime.of(pYear, Integer.valueOf(splitString[0]), Integer.valueOf(splitString[1]),
				00, 00);

		LocalDateTime VacationBeforeEaster = Easter.minusDays(pDays);
		try {
			Date date = convertToDateViaInstant(VacationBeforeEaster);
			pEvaluator.add(date);
			pEvaluator.getSpecialBackroundColor();

		} catch (Exception e) {
			System.out.println(pYear);
		}
		return pEvaluator;
	}

	public static HighlightEvaluatorGreece FindTheVacationDayAfterEaster(int pYear, HighlightEvaluatorGreece pEvaluator,
			int pDays) {

		String dateEaster = CalculateEasterGreek.getOrthodoxEaster(pYear);
		String[] splitString = dateEaster.split("-");

		LocalDateTime Easter = LocalDateTime.of(pYear, Integer.valueOf(splitString[0]), Integer.valueOf(splitString[1]),
				00, 00);

		LocalDateTime VacationBeforeEaster = Easter.plusDays(pDays);
		try {
			Date date = convertToDateViaInstant(VacationBeforeEaster);
			pEvaluator.add(date);
			pEvaluator.getSpecialBackroundColor();

		} catch (Exception e) {
			System.out.println(pYear);
		}
		return pEvaluator;
	}

	public static Date convertToDateViaInstant(LocalDateTime dateToConvert) {
		return java.util.Date.from(dateToConvert.atZone(ZoneId.systemDefault()).toInstant());
	}

}