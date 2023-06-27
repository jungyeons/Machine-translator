import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SLex {
	private Scanner scan;

	public SLex() {

	}

	public void initialize(String fileName) {
		try {
			scan = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			System.out.println("파일이 없습니다 !!!");
		}
	}

	public void finalize() {
		scan.close();
	}

	public String getToken() {
		if (scan.hasNext()) {
			return scan.next().trim();
		}
		return null;
	}

	public String[] getTokens() {
		if (scan.hasNext()) {
			String line = scan.nextLine();
			line = line.trim();
			String tokens[] = line.split("[ \t]");

			return tokens;
		}
		return null;
	}

}
