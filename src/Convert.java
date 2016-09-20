import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;

public class Convert {
	public static void convert(String showname, String url) throws IOException {
		if (showname.length() > 30) {
			showname = showname.substring(0, 29);
		}
		Calendar cal = Calendar.getInstance();
		ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c",
				"lame -b 56 --tt \"" + showname + "\" --ta \"WRUW-FM 91.1\" --tl \"" + cal.get(Calendar.YEAR) + "/"
						+ cal.get(Calendar.MONTH) + "/" + cal.get(Calendar.DAY_OF_MONTH) + "\" " + url
						+ ".wav \"C:\\BetaArchives\\56\\" + url + ".mp3\"");
		builder.redirectErrorStream(true);
		Process p = builder.start();
		BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line;
		while (true) {
			line = r.readLine();
			if (line == null) {
				break;
			}
			System.out.println(line);
		}
		builder = new ProcessBuilder("cmd.exe", "/c",
				"lame -b 128 --tt \"" + showname + "\" --ta \"WRUW-FM 91.1\" --tl \"" + cal.get(Calendar.YEAR) + "/"
						+ cal.get(Calendar.MONTH) + "/" + cal.get(Calendar.DAY_OF_MONTH) + "\" " + url
						+ ".wav \"C:\\BetaArchives\\128\\" + url + ".mp3\"");
		builder.redirectErrorStream(true);
		p = builder.start();
		r = new BufferedReader(new InputStreamReader(p.getInputStream()));
		while (true) {
			line = r.readLine();
			if (line == null) {
				break;
			}
			System.out.println(line);
		}
		builder = new ProcessBuilder("cmd.exe", "/c",
				"lame -b 256 --tt \"" + showname + "\" --ta \"WRUW-FM 91.1\" --tl \"" + cal.get(Calendar.YEAR) + "/"
						+ cal.get(Calendar.MONTH) + "/" + cal.get(Calendar.DAY_OF_MONTH) + "\" " + url
						+ ".wav \"C:\\BetaArchives\\256\\" + url + ".mp3\"");
		builder.redirectErrorStream(true);
		p = builder.start();
		r = new BufferedReader(new InputStreamReader(p.getInputStream()));
		while (true) {
			line = r.readLine();
			if (line == null) {
				break;
			}
			System.out.println(line);
		}
		/*builder = new ProcessBuilder("cmd.exe", "/c", "filemover.bat");
		File dir = new File("C:\\bat");
		builder.redirectErrorStream(true);
		builder.directory(dir);
		p = builder.start();
		r = new BufferedReader(new InputStreamReader(p.getInputStream()));
		while (true) {
			line = r.readLine();
			if (line == null) {
				break;
			}
			System.out.println(line);
		}*/
	}
}
