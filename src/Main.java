import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.opencsv.CSVReader;

public class Main {
	public static void main(String[] args) throws InterruptedException, ExecutionException, IOException {
		while (true) {
			// First, check website for updates
			/*
			ExecutorService executor = Executors.newSingleThreadExecutor();
			Future<String> future = executor.submit(new Task());

			try {
				System.out.println("Started..");
				System.out.println(future.get(30, TimeUnit.SECONDS));
				System.out.println("Finished!");
			} catch (TimeoutException e) {
				future.cancel(true);
				System.out.println("Terminated!");
			}
			*/
			// Now, find the next show
			Calendar now = Calendar.getInstance();
			now.add(Calendar.MINUTE, 3);
			int dayofweek = now.get(Calendar.DAY_OF_WEEK);
			int hour = now.get	(Calendar.HOUR_OF_DAY);
			int minute = now.get(Calendar.MINUTE);
			String[] line;
			boolean found = false;
			String[] firstline;
			CSVReader reader = new CSVReader(new FileReader("shows.csv"));
			firstline = reader.readNext();
			while ((line = reader.readNext()) != null) {
				if (dayofweek < daytoint(line[2])) {
					found = true;
					break;
				} else if (dayofweek == daytoint(line[2])) {
					if (hour < Integer.parseInt(line[3].substring(0, 2))) {
						found = true;
						break;
					} else if (hour == Integer.parseInt(line[3].substring(0, 2))) {
						if (minute < Integer.parseInt(line[3].substring(3, 5))) {
							found = true;
							break;
						}
					}
				}
			}
			if (!found) {
				line = firstline;
			}
			System.out.println(line);
			final String[] finalline=line;
			reader.close();
			int showlength = Integer.parseInt(line[4].substring(0, 2)) - Integer.parseInt(line[3].substring(0, 2));
			if (showlength <= 0) {
				showlength = showlength + 24;
			}
			showlength = showlength * 60;
			showlength = showlength + Integer.parseInt(line[4].substring(3, 5));
			showlength = showlength - Integer.parseInt(line[3].substring(3, 5));
			showlength = showlength + 6;
			long longlength = (long) showlength * 60 * 1000;
			System.out.println(longlength);
			Calendar start = Calendar.getInstance();
			start.set(Calendar.DAY_OF_WEEK, daytoint(line[2]));
			start.set(Calendar.HOUR_OF_DAY, Integer.parseInt(line[3].substring(0, 2)));
			start.set(Calendar.MINUTE, Integer.parseInt(line[3].substring(3, 5)));
			start.set(Calendar.MILLISECOND, 0);
			start.set(Calendar.SECOND, 0);
			start.add(Calendar.MINUTE, -3);
			long startlong = start.getTimeInMillis();
			Calendar newnow = Calendar.getInstance();
			long nowlong = newnow.getTimeInMillis();
			System.out.println(start.toString());
			System.out.println(newnow.toString());
			TimeUnit.MILLISECONDS.sleep(startlong - nowlong);
			Thread record = new Thread(new Runnable() {
				public void run() {
						Recorder.record(finalline[0], longlength, finalline[1]);
				}
			});
			record.start();
		}
	}

	public static int daytoint(String day) {
		if (day.equals("Sun")) {
			return Calendar.SUNDAY;
		} else if (day.equals("Mon")) {
			return Calendar.MONDAY;
		} else if (day.equals("Tue")) {
			return Calendar.TUESDAY;
		} else if (day.equals("Wed")) {
			return Calendar.WEDNESDAY;
		} else if (day.equals("Thu")) {
			return Calendar.THURSDAY;
		} else if (day.equals("Fri")) {
			return Calendar.FRIDAY;
		} else {
			return Calendar.SATURDAY;
		}

	}
}

class Task implements Callable<String> {
	@Override
	public String call() throws Exception {
		Webscraper.get();
		return "Ready!";
	}
}
