import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.swing.JFrame;

import com.toedter.calendar.JCalendar;

import Easter.CalculateEasterGerman;
import Easter.CalculateEasterGreek;
import HighlightEvaluator.HighlightEvaluatorCommon;
import HighlightEvaluator.HighlightEvaluatorGerman;
import HighlightEvaluator.HighlightEvaluatorGreece;

public class GUI {

	private JFrame frmDsa;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frmDsa.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDsa = new JFrame();
		frmDsa.setTitle("Calendar with Greek And German Vacation Days");
		frmDsa.setBounds(100, 100, 551, 419);
		Date date = null;
		frmDsa.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		HighlightEvaluatorGreece evaluatorGR = new HighlightEvaluatorGreece();
		HighlightEvaluatorGerman evaluatorDE = new HighlightEvaluatorGerman();
		HighlightEvaluatorCommon evaluatorCM = new HighlightEvaluatorCommon();

		String[] GreekUrlaub = { "XXXX-03-25 00:00:00.0", "XXXX-10-26 00:00:00.0", "XXXX-10-28 00:00:00.0", };
		String[] GermanUrlaub = { "XXXX-10-03 00:00:00.0", "XXXX-11-01 00:00:00.0", "XXXX-08-08 00:00:00.0",
				"XXXX-12-24 00:00:00.0", "XXXX-12-31 00:00:00.0" };
		String[] CommonUrlaub = { "XXXX-01-01 00:00:00.0", "XXXX-05-01 00:00:00.0", "XXXX-01-06 00:00:00.0",
				"XXXX-08-15 00:00:00.0", "XXXX-12-25 00:00:00.0", "XXXX-12-26 00:00:00.0" };

		JCalendar calendar = new JCalendar();
		calendar.setWeekdayForeground(Color.BLUE);

		for (int year = 1999; year < 2100; year++) {

			// Pasxa Elladas
			evaluatorGR = CalculateEasterGreek.FindEaster(year, evaluatorGR);
			calendar.getDayChooser().addDateEvaluator(evaluatorGR);

			// Kathara Deutera Elladas
			evaluatorGR = CalculateEasterGreek.FindTheVacationDayBeforeEaster(year, evaluatorGR, 48);
			calendar.getDayChooser().addDateEvaluator(evaluatorGR);

			// Megali Paraskeyi Elladas
			evaluatorGR = CalculateEasterGreek.FindTheVacationDayBeforeEaster(year, evaluatorGR, 2);
			calendar.getDayChooser().addDateEvaluator(evaluatorGR);

			// Megali Savvato Elladas
			evaluatorGR = CalculateEasterGreek.FindTheVacationDayBeforeEaster(year, evaluatorGR, 1);
			calendar.getDayChooser().addDateEvaluator(evaluatorGR);

			// LamproDeutera Elladas
			evaluatorGR = CalculateEasterGreek.FindTheVacationDayAfterEaster(year, evaluatorGR, 1);
			calendar.getDayChooser().addDateEvaluator(evaluatorGR);

			// Agiou Pneumatos Elladas
			evaluatorGR = CalculateEasterGreek.FindTheVacationDayAfterEaster(year, evaluatorGR, 50);
			calendar.getDayChooser().addDateEvaluator(evaluatorGR);

			// Pasxa Germanias
			evaluatorDE = CalculateEasterGerman.FindEaster(year, evaluatorDE);
			calendar.getDayChooser().addDateEvaluator(evaluatorDE);

			// MegaliParaskeyi Germanias
			evaluatorDE = CalculateEasterGerman.FindTheVacationDayBeforeEaster(year, evaluatorDE, 2);
			calendar.getDayChooser().addDateEvaluator(evaluatorDE);

			// MegaloSavvato Germanias
			evaluatorDE = CalculateEasterGerman.FindTheVacationDayBeforeEaster(year, evaluatorDE, 1);
			calendar.getDayChooser().addDateEvaluator(evaluatorDE);

			// LamproDeutera Germanias
			evaluatorDE = CalculateEasterGerman.FindTheVacationDayAfterEaster(year, evaluatorDE, 1);
			calendar.getDayChooser().addDateEvaluator(evaluatorDE);

			// Ascension of Christ Germanias
			evaluatorDE = CalculateEasterGerman.FindTheVacationDayAfterEaster(year, evaluatorDE, 39);
			calendar.getDayChooser().addDateEvaluator(evaluatorDE);

			// Corpus Christi Germanias
			evaluatorDE = CalculateEasterGerman.FindTheVacationDayAfterEaster(year, evaluatorDE, 60);
			calendar.getDayChooser().addDateEvaluator(evaluatorDE);

			// Corpus Christi Germanias
			evaluatorDE = CalculateEasterGerman.FindTheVacationDayAfterEaster(year, evaluatorDE, 50);
			calendar.getDayChooser().addDateEvaluator(evaluatorDE);

			for (String BothDates : CommonUrlaub) {
				try {

					BothDates = BothDates.replaceAll("XXXX", String.valueOf(year));
					date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(BothDates);
					evaluatorCM.add(date);
					evaluatorCM.getSpecialBackroundColor();
					calendar.getDayChooser().addDateEvaluator(evaluatorCM);
				} catch (Exception e) {

				}
			}
			for (String BothDates : GreekUrlaub) {
				try {
					BothDates = BothDates.replaceAll("XXXX", String.valueOf(year));
					date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(BothDates);
					evaluatorGR.add(date);
					evaluatorGR.getSpecialBackroundColor();
					calendar.getDayChooser().addDateEvaluator(evaluatorGR);
				} catch (Exception e) {

				}
			}
			for (String BothDates : GermanUrlaub) {
				try {
					BothDates = BothDates.replaceAll("XXXX", String.valueOf(year));
					date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(BothDates);
					evaluatorDE.add(date);
					evaluatorDE.getSpecialBackroundColor();
					calendar.getDayChooser().addDateEvaluator(evaluatorDE);
				} catch (Exception e) {

				}
			}

		}

		frmDsa.getContentPane().add(calendar, BorderLayout.CENTER);
	}

	public Date convertToDateViaInstant(LocalDateTime dateToConvert) {
		return java.util.Date.from(dateToConvert.atZone(ZoneId.systemDefault()).toInstant());
	}
}
