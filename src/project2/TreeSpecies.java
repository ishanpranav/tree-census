package project2;

import java.util.Objects;

/**
 * Represents a single tree species with a common and Latin name.
 */
public class TreeSpecies {
    private String latinName;
    private String commonName;

    /**
     * Initializes a new instance of the {@link TreeSpecies} class.
     * 
     * @param commonName the common name.
     * @param latinName  the scientific name.
     */
    public TreeSpecies(String commonName, String latinName) {
        if (commonName == null) {
            throw new IllegalArgumentException("Value cannot be null. Argument name: commonName.");
        } else if (latinName == null) {
            throw new IllegalArgumentException("Value cannot be null. Argument name: latinName.");
        } else {
            this.commonName = commonName;
            this.latinName = latinName;
        }
    }

    /**
     * Gets the scientific (Latin) name of the tree's species. This property
     * represents the {@code spc_latin} field in the dataset.
     * 
     * @return The scientific name, a non-null string.
     */
    public String getLatinName() {
        return latinName;
    }

    /**
     * Gets the common (English) name of the tree's species. This property
     * represents the {@code spc_common} field in the dataset.
     * 
     * @return The common name, a non-null string.
     */
    public String getCommonName() {
        return commonName;
    }

    /** {@inheritDoc} */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof TreeSpecies)) {
            return false;
        } else {
            final TreeSpecies other = (TreeSpecies) obj;

            return commonName.equalsIgnoreCase(other.commonName) && latinName.equalsIgnoreCase(other.latinName);
        }
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        // Hash upper-case strings to preserve case-insensitivity

        return Objects.hash(latinName.toUpperCase(), commonName.toUpperCase());
    }
}
