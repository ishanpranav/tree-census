package project2;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * Provides an iterator for a comma-delimited data source, such as a
 * comma-separated values (*.csv) file. The iterator returns lists of strings
 * representing the fields in each successive line.
 * 
 * This class's {@code next()} method is based on Joanna Klukowska's
 * implementation in CSV.java. This implementation uses lazy iteration to
 * minimize memory usage.
 * 
 * @author Joanna Klukowska
 * @author Ishan Pranav
 */
public class CSV implements Closeable, Iterator<List<String>> {
	private final Scanner scanner;

	/**
	 * Initializes a new instance of the {@link CSV} class.
	 * 
	 * @param input the input stream.
	 */
	public CSV(InputStream input) {
		this.scanner = new Scanner(input);
	}

	/** {@inheritDoc} */
	@Override
	public void close() throws IOException {
		scanner.close();
	}

	/** {@inheritDoc} */
	@Override
	public boolean hasNext() {
		return scanner.hasNextLine();
	}

	/** {@inheritDoc} */
	@Override
	public List<String> next() {
		final ArrayList<String> results = new ArrayList<String>();
		
		boolean inQuotes = false;
		boolean inField = false;
		String line = scanner.nextLine();
		StringBuilder fieldBuilder = new StringBuilder();

		for (int i = 0; i < line.length(); i++) {
			char nextChar = line.charAt(i);

			if (nextChar == '"') {
				boolean toggledValue = !inQuotes;

				inQuotes = toggledValue;
				inField = toggledValue;
			} else if (Character.isWhitespace(nextChar)) {
				if (inQuotes || inField) {
					fieldBuilder.append(nextChar);
				}
			} else if (nextChar == ',') {
				if (inQuotes) {
					fieldBuilder.append(nextChar);
				} else {
					results.add(fieldBuilder.toString());

					fieldBuilder = new StringBuilder();
					inField = false;
				}
			} else {
				fieldBuilder.append(nextChar);

				inField = true;
			}
		}

		if (fieldBuilder.length() > 0) {
			results.add(fieldBuilder.toString().trim());
		}

		return results;
	}
}
