package project2;

import java.util.ArrayList;

/**
 * Represents a collection of tree species.
 */
public class TreeSpeciesList extends ArrayList<TreeSpecies> {
    /**
     * Initializes a new instance of the {@link TreeSpeciesList} class.
     */
    public TreeSpeciesList() {
    }

    /**
     * Constructs a new collection of tree species containing the species whose
     * common names contain a given keyword as a substring.
     * 
     * @param keyword The substring present in all the common names of the species
     *                in the output.
     * @return The collection of matching tree species.
     * @throws IllegalArgumentException if keyword is null.
     */
    public TreeSpeciesList getByCommonName(String keyword) {
        if (keyword == null) {
            throw new IllegalArgumentException("Value cannot be null. Argument name: keyword.");
        } else {
            TreeSpeciesList results = new TreeSpeciesList();

            keyword = keyword.toUpperCase();

            for (TreeSpecies species : this) {
                if (species.getCommonName().toUpperCase().contains(keyword)) {
                    results.add(species);
                }
            }

            if (results.size() == 0) {
                return null;
            } else {
                return results;
            }
        }
    }

    /**
     * Constructs a new collection of tree species containing the species whose
     * Latin names contain a given keyword as a substring.
     * 
     * @param keyword The substring present in all the Latin names of the species in
     *                the output.
     * @return The collection of matching tree species.
     * @throws IllegalArgumentException if keyword is null.
     */
    public TreeSpeciesList getByLatinName(String keyword) {
        if (keyword == null) {
            throw new IllegalArgumentException("Value cannot be null. Argument name: keyword.");
        } else {
            final TreeSpeciesList results = new TreeSpeciesList();
            
            keyword = keyword.toUpperCase();

            for (TreeSpecies species : this) {
                if (species.getLatinName().toUpperCase().contains(keyword)) {
                    results.add(species);
                }
            }

            if (results.size() == 0) {
                return null;
            } else {
                return results;
            }
        }
    }
}
