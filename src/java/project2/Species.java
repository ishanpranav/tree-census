package project2;

/**
 * Defines methods for accessing the scientific (Latin) and common (English)
 * names of a species.
 */
public interface Species {
    /**
     * Gets the scientific (Latin) name of the tree's species. This property
     * represents the {@code spc_latin} field in the dataset.
     * 
     * @return The scientific name, a non-null string.
     */
    String getLatinName();

    /**
     * Gets the common (English) name of the tree's species. This property
     * represents the {@code spc_common} field in the dataset.
     * 
     * @return The common name, a non-null string.
     */
    String getCommonName();
}
