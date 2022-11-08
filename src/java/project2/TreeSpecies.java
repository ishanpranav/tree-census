package project2;

import java.util.Objects;

/**
 * Represents a tree species.
 * 
 * @author Ishan Pranav
 */
public class TreeSpecies implements Species {
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

    /** {@inheritDoc} */
    public String getLatinName() {
        return latinName;
    }

    /** {@inheritDoc} */
    public String getCommonName() {
        return commonName;
    }

    /** {@inheritDoc} */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof TreeSpecies)) {
            return false;
        } else {
            TreeSpecies other = (TreeSpecies) obj;

            return commonName.equalsIgnoreCase(other.commonName) && latinName.equalsIgnoreCase(other.latinName);
        }
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        return Objects.hash(latinName.toUpperCase(), commonName.toUpperCase());
    }
}
