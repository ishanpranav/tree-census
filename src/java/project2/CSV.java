package project2;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

class CSV implements Closeable, Iterator<List<String>> {
	private Scanner input;

	public CSV(Scanner input) {
		this.input = input;
	}

	@Override
	public void close() throws IOException {
		input.close();
	}

	@Override
	public boolean hasNext() {
		return input.hasNextLine();
	}

	@Override
	public List<String> next() {
		ArrayList<String> results = new ArrayList<String>();
		StringBuilder fieldBuilder = new StringBuilder();
		boolean inQuotes = false;
		boolean inField = false;
		String line = input.nextLine();

		for (int i = 0; i < line.length(); i++) {
			char nextChar = line.charAt(i);

			if (nextChar == '"') {
				if (inQuotes) {
					inQuotes = false;
					inField = false;
				} else {
					inQuotes = true;
					inField = true;
				}
			} else if (Character.isWhitespace(nextChar)) {
				if (inQuotes || inField) {
					fieldBuilder.append(nextChar);
				} else {
					continue;
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