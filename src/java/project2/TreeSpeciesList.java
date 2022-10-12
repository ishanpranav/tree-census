package project2;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Represents a collection of tree species.
 */
public class TreeSpeciesList extends ArrayList<TreeSpecies> {
    /**
     * Initializes a new instance of the {@link ArrayList} class.
     */
    public TreeSpeciesList() {
    }

    /**
     * Initializes a new instance of the {@link ArrayList} class containing the
     * elements of the specified collection, in the order that they are returned by
     * the collection's iterator.
     * 
     * @param items the collection whose elements are to be placed into this list.
     * @throws NullPointerException if the specified collection is null.
     */
    private TreeSpeciesList(Collection<? extends TreeSpecies> items) {
        super(items);
    }

    /**
     * Returns a new collection of tree species containing the species whose common
     * names contain a given keyword as a substring.
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
            ArrayList<TreeSpecies> results = new ArrayList<TreeSpecies>();

            for (TreeSpecies species : this) {
                if (species.getCommonName().toUpperCase().contains(keyword.toUpperCase())) {
                    results.add(species);
                }
            }

            if (results.size() == 0) {
                return null;
            } else {
                return new TreeSpeciesList(results);
            }
        }
    }

    /**
     * Returns a new collection of tree species containing the species whose Latin
     * names contain a given keyword as a substring.
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
            ArrayList<TreeSpecies> results = new ArrayList<TreeSpecies>();

            for (TreeSpecies species : this) {
                if (species.getLatinName().toUpperCase().contains(keyword.toUpperCase())) {
                    results.add(species);
                }
            }

            if (results.size() == 0) {
                return null;
            } else {
                return new TreeSpeciesList(results);
            }
        }
    }
}
