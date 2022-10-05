package project2;

import java.util.Objects;

class TreeSpecies {
    private String latinName;
    private String commonName;

    public String getLatinName() {
        return latinName;
    }

    public void setLatinName(String latinName) {
        if (latinName == null) {
            throw new IllegalArgumentException("Value cannot be null. Argument name: latinName.");
        } else {
            this.latinName = latinName;
        }
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        if (commonName == null) {
            throw new IllegalArgumentException("Value cannot be null. Argument name: commonName.");
        } else {
            this.commonName = commonName;
        }
    }

    public TreeSpecies(String commonName, String latinName) {
        setCommonName(commonName);
        setLatinName(latinName);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof TreeSpecies)) {
            return false;
        } else {
            TreeSpecies other = (TreeSpecies) obj;

            return commonName.equalsIgnoreCase(other.commonName) && latinName.equalsIgnoreCase(other.latinName);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(latinName.toUpperCase(), commonName.toUpperCase());
    }
}
