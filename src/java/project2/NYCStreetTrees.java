package project2;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class NYCStreetTrees {
    private static final String[] BOROUGHS = Tree.getBoroughs();

    private final int[] totals = new int[BOROUGHS.length];
    private final int[] frequencies = new int[BOROUGHS.length];
    private final TreeList trees = new TreeList();
    private final TreeSpeciesList species = new TreeSpeciesList();
    private final ArrayList<String> speciesNames = new ArrayList<String>();

    private int frequency = 0;

    private NYCStreetTrees(String path) throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream(path)) {
            try (Scanner scanner = new Scanner(fileInputStream)) {
                try (CSV csv = new CSV(scanner)) {
                    if (csv.hasNext()) {
                        csv.next();
                    }

                    while (csv.hasNext()) {
                        List<String> fields = csv.next();
                        TreeSpecies species = new TreeSpecies(fields.get(9), fields.get(8));
                        int index = this.species.indexOf(species);

                        if (index == -1) {
                            this.species.add(species);
                        } else {
                            species = this.species.get(index);
                        }

                        Tree tree = new Tree(Integer.parseInt(fields.get(0)), species);

                        tree.setStatus(fields.get(6));
                        tree.setHealth(fields.get(7));
                        tree.setZipCode(Integer.parseInt(fields.get(25)));
                        tree.setBorough(fields.get(29));
                        tree.setX(Double.parseDouble(fields.get(39)));
                        tree.setY(Double.parseDouble(fields.get(40)));

                        trees.add(tree);
                    }
                }
            }
        } catch (IOException ioException) {
            throw new IOException("Runtime error: An error occurred while attempting to access " + path + ".");
        }

        for (int i = 0; i < BOROUGHS.length; i++) {
            totals[i] = trees.getCountByBorough(BOROUGHS[i]);
        }
    }

    private boolean summarize(String keyword) {
        frequency = 0;

        Arrays.fill(frequencies, 0);

        speciesNames.clear();

        TreeSpeciesList byCommonName = species.getByCommonName(keyword);
        TreeSpeciesList byLatinName = species.getByLatinName(keyword);

        if (byCommonName == null && byLatinName == null) {
            return false;
        } else {
            TreeSpeciesList bySpecies = new TreeSpeciesList();
            
            if (byCommonName != null) {
                for (TreeSpecies item : byCommonName) {
                    String commonName = item.getCommonName();

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
                    String latinName = item.getLatinName();

                    if (!speciesNames.contains(latinName)) {
                        speciesNames.add(latinName);
                    }

                    if (!bySpecies.contains(item)) {
                        bySpecies.add(item);
                    }
                }
            }

            for (TreeSpecies item : bySpecies) {
                String latinName = item.getLatinName();

                frequency += trees.getCountByLatinName(latinName);

                for (int i = 0; i < BOROUGHS.length; i++) {
                    frequencies[i] += trees.getCountByLatinNameBorough(latinName, BOROUGHS[i]);
                }
            }

            speciesNames.sort(null);

            return true;
        }
    }

    public static void main(String[] args) {
        try {
            if (args.length == 0) {
                throw new IllegalArgumentException(
                        "Usage error: The program expects a path to a CSV data set as a command-line argument.");
            } else {
                String path = args[0];
                NYCStreetTrees program = new NYCStreetTrees(path);

                try (Scanner scanner = new Scanner(System.in)) {
                    while (true) {
                        System.out.println("Enter the tree species to learn more about it (\"quit\" to stop):");

                        String line = scanner.nextLine();

                        if (line.equalsIgnoreCase("quit")) {
                            break;
                        } else if (line.length() > 0) {
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
                                System.out.println("There are no records of " + line + " on NYC streets.");
                            }
                        }
                    }
                }
            }
        } catch (IllegalArgumentException illegalArgumentException) {
            System.err.println(illegalArgumentException.getLocalizedMessage());
        } catch (IOException ioException) {
            System.err.println(ioException.getLocalizedMessage());
        } catch (Exception exception) {
            System.err.println("An unexpected error occurred.");

            throw exception;
        }
    }

    private static void printPopularity(String borough, int frequency, int total) {
        String fraction = String.format("%,d(%,d)", frequency, total);

        System.out.printf("\t%-14s:%21s%9.2f%%\n", borough, fraction, 100d * frequency / total);
    }
}
