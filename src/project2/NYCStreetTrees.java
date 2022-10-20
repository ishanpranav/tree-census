package project2;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Provides the controller for the New York City Street Tree Census parser
 * application and contains the main entry point for the program.
 * 
 * @author Ishan Pranav
 */
public class NYCStreetTrees {
    private static final String[] BOROUGHS = Tree.getBoroughs();

    /**
     * Provides the main entry point for the application.
     * 
     * @param args the command-line arguments to the program.
     */
    public static void main(String[] args) {
        // This try-catch block only catches exceptions thrown within the program
        // itself; exceptions thrown from library functions should be caught elsewhere

        try {
            if (args.length == 0) {
                // "If the program is run without any arguments, the program should display an
                // error message and terminate. It should not prompt the user for the name of
                // the file."

                throw new IllegalArgumentException(
                        "Usage error: The program expects a path to a CSV data set as a command-line argument.");
            } else {
                final String path = args[0];
                final NYCStreetTrees program;

                try {
                    program = new NYCStreetTrees(path);
                } catch (IOException ioException) {
                    throw new IOException("Error: the file " + path + " cannot be opened.");
                }

                try (Scanner scanner = new Scanner(System.in)) {
                    String line = "";

                    final String terminateString = "quit";

                    // Request next tree species until "quit" is entered

                    while (!line.equalsIgnoreCase(terminateString)) {
                        System.out.println("Enter the tree species to learn more about it (\"quit\" to stop):");

                        line = scanner.nextLine();

                        if (line.length() > 0 && !line.equalsIgnoreCase(terminateString)) {
                            // Populate totals, frequencies, and sorted species names for each borough

                            if (program.summarize(line)) {
                                System.out.println("All matching species:");

                                for (String speciesName : program.speciesNames) {
                                    System.out.print('\t');
                                    System.out.println(speciesName);
                                }

                                System.out.println();
                                System.out.println("Popularity in the city:");

                                printPopularity("NYC", program.frequency, program.trees.getTotalNumberOfTrees());

                                for (int i = 0; i < BOROUGHS.length; i++) {
                                    printPopularity(BOROUGHS[i], program.frequencies[i], program.totals[i]);
                                }

                                System.out.println();
                            } else {
                                System.out.println();
                                System.out.println("Tere are no records of " + line + " on NYC streets");
                                System.out.println();
                            }
                        }
                    }
                }
            }
        } catch (IllegalArgumentException illegalArgumentException) {
            // Usage error: The program expects a path to a CSV data set as a command-line
            // argument.

            System.err.println(illegalArgumentException.getLocalizedMessage());
        } catch (IOException ioException) {
            // Error: the file data/missing.csv cannot be opened.

            System.err.println(ioException.getLocalizedMessage());
        } catch (Exception exception) {
            // Other runtime exceptions

            System.err.println("An unexpected error occurred.");
        }
    }

    /**
     * Prints a formatted row displaying statistics about the tree.
     * 
     * @param borough   The borough name.
     * @param frequency The number of matching trees in that borough.
     * @param total     The total number of trees in that borough.
     */
    private static void printPopularity(String borough, int frequency, int total) {
        final double percentage;

        if (total == 0) {
            percentage = 0;
        } else {
            percentage = 100d * frequency / total;
        }

        // Two format strings are required for perfect formatting: the parenthetical
        // fraction is a string in its own column

        System.out.printf("\t%-14s:%21s%9.2f%%\n", borough, String.format("%,d(%,d)", frequency, total), percentage);
    }

    private final int[] totals = new int[BOROUGHS.length];
    private final int[] frequencies = new int[BOROUGHS.length];
    private final TreeList trees = new TreeList();
    private final TreeSpeciesList species = new TreeSpeciesList();
    private final ArrayList<String> speciesNames = new ArrayList<String>();

    private int frequency = 0;

    /**
     * Initializes a new instance of the {@link NYCStreetTrees} class.
     * 
     * @param path The path to the comma-delimited (*.csv) dataset.
     * @throws IOException if an error occurs while attempting to access the
     *                     dataset.
     */
    private NYCStreetTrees(String path) throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream(path)) {
            try (CSV csv = new CSV(fileInputStream)) {

                // Skip the header row

                if (csv.hasNext()) {
                    csv.next();
                }

                while (csv.hasNext()) {
                    final List<String> fields = csv.next();

                    // "Any row that contains the tree id and common species name is a valid row.
                    // Any rows that are missing either of these two values should be silently
                    // ignored by the program. Any other column values can be empty."

                    final String idField = fields.get(0);
                    final String commonName = fields.get(9);

                    if (idField.length() > 0 && commonName.length() > 0) {

                        TreeSpecies species = new TreeSpecies(commonName, fields.get(8));

                        // Avoid duplicate species

                        if (!this.species.contains(species)) {
                            this.species.add(species);
                        }

                        final Tree tree = new Tree(Integer.parseInt(idField), species);

                        tree.setStatus(fields.get(6));
                        tree.setHealth(fields.get(7));

                        // Assign non-nullable fields if a non-empty value exists in the CSV file

                        final String zipCodeField = fields.get(25);

                        if (zipCodeField.length() > 0) {
                            tree.setZipcode(Integer.parseInt(zipCodeField));
                        }

                        final String borough = fields.get(29);

                        if (borough.length() > 0) {
                            tree.setBoroname(borough);
                        }

                        final String xField = fields.get(39);

                        if (xField.length() > 0) {
                            tree.setX_sp(Double.parseDouble(xField));
                        }

                        final String yField = fields.get(40);

                        if (yField.length() > 0) {
                            tree.setY_sp(Double.parseDouble(yField));
                        }

                        trees.add(tree);
                    }
                }
            }
        }

        // Calculate the total number of trees in each borough

        for (int i = 0; i < BOROUGHS.length; i++) {
            totals[i] = trees.getCountByBorough(BOROUGHS[i]);
        }
    }

    /**
     * Populates internal data structures that record matches and frequencies of
     * tree species based on the given keyword.
     * 
     * @param keyword The species keyword.
     * @return {@code true} if there is at least one tree speices that matches the
     *         given
     *         keyword in the dataset; otherwise, {@code false}.
     */
    private boolean summarize(String keyword) {
        // Reset the frequency of the matching species in NYC

        frequency = 0;

        // Reset the frequency of the given species in each borough

        Arrays.fill(frequencies, 0);

        // Reset the matching species names

        speciesNames.clear();

        final TreeSpeciesList byCommonName = species.getByCommonName(keyword);
        final TreeSpeciesList byLatinName = species.getByLatinName(keyword);

        if (byCommonName == null && byLatinName == null) {
            // No matching species exists

            return false;
        } else {
            // Combine matches into a list of distinct species

            final TreeSpeciesList bySpecies = new TreeSpeciesList();

            if (byCommonName != null) {
                for (TreeSpecies item : byCommonName) {
                    final String commonName = item.getCommonName();

                    // Avoid duplicate species names

                    if (!speciesNames.contains(commonName)) {
                        speciesNames.add(commonName);
                    }

                    // Avoid duplicate species (no double counting)

                    if (!bySpecies.contains(item)) {
                        bySpecies.add(item);
                    }
                }
            }

            if (byLatinName != null) {
                for (TreeSpecies item : byLatinName) {
                    final String latinName = item.getLatinName();

                    // Avoid duplicate species names

                    if (!speciesNames.contains(latinName)) {
                        speciesNames.add(latinName);
                    }

                    // Avoid duplicate species (no double counting)

                    if (!bySpecies.contains(item)) {
                        bySpecies.add(item);
                    }
                }
            }

            for (TreeSpecies item : bySpecies) {
                final String commonName = item.getCommonName();

                // Get the total count of the unique species matches in NYC

                frequency += trees.getCountByCommonName(commonName);

                // Get the total counts of the unique species matches in eadch borough

                for (int i = 0; i < BOROUGHS.length; i++) {
                    frequencies[i] += trees.getCountByCommonNameBorough(commonName, BOROUGHS[i]);
                }
            }

            speciesNames.sort(null);

            return true;
        }
    }
}
