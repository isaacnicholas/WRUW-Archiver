import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import com.opencsv.CSVWriter;

public class Webscraper {
	public static void get() throws IOException {
		URL url;
		BufferedReader br;
		InputStream is = null;
		String line;
		try {
			url = new URL("http://wruw.org/scheduler");
			is = url.openStream();
			br = new BufferedReader(new InputStreamReader(is));
			if ((line = br.readLine()) != null) {
				CSVWriter writer = new CSVWriter(new FileWriter("shows.csv"));
				String[] array;
				array = new String[5];
				array[0] = line;
				array[1] = br.readLine();
				array[2] = br.readLine();
				array[3] = br.readLine();
				array[4] = br.readLine();
				writer.writeNext(array);
				while ((line = br.readLine()) != null) {
					array = new String[5];
					array[0] = line;
					array[1] = br.readLine();
					array[2] = br.readLine();
					array[3] = br.readLine();
					array[4] = br.readLine();
					writer.writeNext(array);
				}
				writer.close();
			}
		} catch (IOException e) {
		}
	}
}
