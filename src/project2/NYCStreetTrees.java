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
                throw new IllegalArgumentException(
                        "Usage error: The program expects a path to a CSV data set as a command-line argument.");
            } else {
                final String path = args[0];
                final NYCStreetTrees program = new NYCStreetTrees(path);

                try (Scanner scanner = new Scanner(System.in)) {
                    String line = "";

                    final String terminateString = "quit";

                    while (!line.equalsIgnoreCase(terminateString)) {
                        System.out.println("Enter the tree species to learn more about it (\"quit\" to stop):");

                        line = scanner.nextLine();

                        if (line.length() > 0 && !line.equalsIgnoreCase(terminateString)) {
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
                                System.out.println("There are no records of " + line + " on NYC streets");
                                System.out.println();
                            }
                        }
                    }
                }
            }
        } catch (IllegalArgumentException illegalArgumentException) {
            // Catch exception that the program throws missing command-line arguments

            System.err.println(illegalArgumentException.getLocalizedMessage());
        } catch (IOException ioException) {
            // Catch exception that the program throws when the file could not be read

            System.err.println(ioException.getLocalizedMessage());
        } catch (Exception exception) {
            // Catch other exceptions thrown by the program
            System.err.println("An unexpected error occurred.");
        }
    }

    private static void printPopularity(String borough, int frequency, int total) {
        System.out.printf("\t%-14s:%21s%9.2f%%\n", borough, String.format("%,d(%,d)", frequency, total),
                100d * frequency / total);
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

                    TreeSpecies species = new TreeSpecies(fields.get(9), fields.get(8));

                    // Retrieve an tree species or construct a new one (avoid duplicates)

                    final int index = this.species.indexOf(species);

                    if (index == -1) {
                        this.species.add(species);
                    } else {
                        species = this.species.get(index);
                    }

                    final String idField = fields.get(0);

                    if (idField.length() > 0) {
                        final Tree tree = new Tree(Integer.parseInt(idField), species);

                        tree.setStatus(fields.get(6));
                        tree.setHealth(fields.get(7));

                        final String zipCodeField = fields.get(25);
                        
                        int zipCode;

                        if (zipCodeField.length() == 0) {
                            zipCode = 0;
                        } else {
                            zipCode = Integer.parseInt(zipCodeField);
                        }

                        tree.setZipcode(zipCode);
                        tree.setBoroname(fields.get(29));

                        final String xField = fields.get(39);
                        
                        double x;

                        if (xField.length() == 0) {
                            x = Double.parseDouble(xField);
                        } else {
                            x = 0;
                        }

                        tree.setX_sp(x);

                        final String yField = fields.get(40);

                        double y;

                        if (yField.length() == 0) {
                            y = Double.parseDouble(yField);
                        } else {
                            y = 0;
                        }

                        tree.setY_sp(y);

                        trees.add(tree);
                    }
                }
            }
        } catch (IOException ioException) {
            throw new IOException("Runtime error: An error occurred while attempting to access " + path + ".");
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
        // Reset the frequency of the given species in New York City

        frequency = 0;

        // Reset the frequency of the given species in each borough

        Arrays.fill(frequencies, 0);

        // Reset the matching species names

        speciesNames.clear();

        final TreeSpeciesList byCommonName = species.getByCommonName(keyword);
        final TreeSpeciesList byLatinName = species.getByLatinName(keyword);

        if (byCommonName == null && byLatinName == null) {
            return false;
        } else {
            final TreeSpeciesList bySpecies = new TreeSpeciesList();

            if (byCommonName != null) {
                for (TreeSpecies item : byCommonName) {
                    final String commonName = item.getCommonName();

                    // Record the matching species common name and TreeSpecies instance if it has
                    // not yet been found

                    if (!speciesNames.contains(commonName)) {
                        speciesNames.add(commonName);
                    }

                    if (!bySpecies.contains(item)) {
                        bySpecies.add(item);
                    }
                }
            }

            if (byLatinName != null) {
                for (TreeSpecies item : byLatinName) {
                    final String latinName = item.getLatinName();

                    // Record the matching species Latin name and TreeSpecies instance if it has
                    // not yet been found

                    if (!speciesNames.contains(latinName)) {
                        speciesNames.add(latinName);
                    }

                    if (!bySpecies.contains(item)) {
                        bySpecies.add(item);
                    }
                }
            }

            for (TreeSpecies item : bySpecies) {
                final String latinName = item.getLatinName();

                // Use each matching species's Latin name to get its frequency in New York City
                // and include this in the total for all matching species

                frequency += trees.getCountByLatinName(latinName);

                // For each species and each borough, add the frequency to the total for all
                // matching species in that borough

                for (int i = 0; i < BOROUGHS.length; i++) {
                    frequencies[i] += trees.getCountByLatinNameBorough(latinName, BOROUGHS[i]);
                }
            }

            speciesNames.sort(null);

            return true;
        }
    }
}
