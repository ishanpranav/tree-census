package project2;

import java.util.Objects;

/**
 * Represents a tree in New York City.
 * 
 * @author Ishan Pranav
 */
public class Tree implements Comparable<Tree> {
    private static final String MANHATTAN_BOROUGH = "Manhattan";

    /**
     * Gets a collection of valid borough names.
     * 
     * @return The valid borough names.
     */
    public static String[] getBoroughs() {
        return new String[] { MANHATTAN_BOROUGH, "Bronx", "Brooklyn", "Queens", "Staten Island" };
    }

    private final int treeID;
    private final TreeSpecies species;
    
    private String status;
    private String health;
    private int zipCode;
    private String borough = MANHATTAN_BOROUGH;
    private double x;
    private double y;

    /**
     * Initializes a new instance of the {@link Tree} class.
     * 
     * @param treeID  the tree identifier, a non-negative integer. This argument
     *                represents the {@code tree_id} field in the dataset.
     * @param species the tree species. This argument represents the
     *                {@code spc_latin} and {@code spc_common} fields in the
     *                dataset.
     * @throws IllegalArgumentException if treeID is negative.
     * @throws IllegalArgumentException if species is {@code null}.
     */
    public Tree(int treeID, TreeSpecies species) throws IllegalArgumentException {
        if (treeID < 0) {
            throw new IllegalArgumentException(
                    "Value is out of range. A non-negative number is required. Argument name: treeID.");
        } else if (species == null) {
            throw new IllegalArgumentException("Value cannot be null. Argument name: species.");
        } else {
            this.treeID = treeID;
            this.species = species;
        }
    }

    /**
     * Gets the tree identifier. This property represents the {@code tree_id} field
     * in the dataset.
     * 
     * @return A non-negative integer identifier.
     */
    public int getTree_id() {
        return treeID;
    }

    /**
     * Gets the status of the tree. This property represents the {@code status}
     * field in the dataset.
     * 
     * @return {@code "Alive"}, {@code "Dead"}, {@code "Stump"}, {@code null}, or an
     *         empty string to represent the status. Status values are
     *         case-insensitive.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status of the tree. This property represents the {@code status}
     * field in the dataset.
     * 
     * @param status {@code "Alive"}, {@code "Dead"}, {@code "Stump"}, {@code null},
     *               or an empty string to represent the status. Status values are
     *               case-insensitive.
     * @throws IllegalArgumentException if status is not a valid status.
     */
    public void setStatus(String status) {
        if (status == null || status.equals("") || status.equalsIgnoreCase("Alive") || status.equalsIgnoreCase("Dead")
                || status.equalsIgnoreCase("Stump")) {
            this.status = status;
        } else {
            throw new IllegalArgumentException(
                    "A valid status value is required. Allowed values: null or empty, Alive, Dead, and Stump. Argument name: status.");
        }
    }

    /**
     * Gets the health of the tree. This property represents the {@code health}
     * field in the dataset.
     * 
     * @return {@code "Good"}, {@code "Fair"}, {@code "Poor"}, {@code null}, or an
     *         empty string to represent the health. Health descriptions are
     *         case-insensitive.
     */
    public String getHealth() {
        return health;
    }

    /**
     * Sets the health of the tree. This property represents the {@code health}
     * field in the dataset.
     * 
     * @param health {@code "Good"}, {@code "Fair"}, {@code "Poor"}, {@code null},
     *               or an empty string to represent the health. Health descriptions
     *               are case-insensitive.
     * @throws IllegalArgumentException if health is not a valid health description.
     */
    public void setHealth(String health) {
        if (health == null || health.equals("") || health.equalsIgnoreCase("Good") || health.equalsIgnoreCase("Fair")
                || health.equalsIgnoreCase("Poor")) {
            this.health = health;
        } else {
            throw new IllegalArgumentException(
                    "A valid health value is required. Allowed values: null or empty, Good, Fair, and Poor. Argument name: health.");
        }
    }

    /**
     * Gets the scientific (Latin) name of the tree's species. This property
     * represents the {@code spc_latin} field in the dataset.
     * 
     * @return The scientific name, a non-null string.
     */
    public String getSpc_latin() {
        return species.getLatinName();
    }

    /**
     * Gets the common (English) name of the tree's species. This property
     * represents the {@code spc_common} field in the dataset.
     * 
     * @return The common name, a non-null string.
     */
    public String getSpc_common() {
        return species.getCommonName();
    }

    /**
     * Gets the postal (ZIP) code in which the tree is located. This property
     * represents the {@code zipcode} field in the dataset.
     * 
     * @return The postcode, an integer between 00000 and 99999.
     */
    public int getZipcode() {
        return zipCode;
    }

    /**
     * Sets the postal (ZIP) code in which the tree is located. This property
     * represents the {@code zipcode} field in the dataset.
     * 
     * @param zipCode the postcode, an integer between 00000 and 99999.
     * @throws IllegalArgumentException if zipCode is less than 0 or greater than
     *                                  99999.
     */
    public void setZipcode(int zipCode) {
        if (zipCode >= 0 && zipCode <= 99999) {
            this.zipCode = zipCode;
        } else {
            throw new IllegalArgumentException(
                    "Value is out of range. Zip codes must be between 00000 and 99999, inclusive. Argument name: zipCode.");
        }
    }

    /**
     * Gets the borough name in which the tree is located. This property represents
     * the {@code boroname} field in the dataset.
     * 
     * @return {@code "Manhattan"}, {@code "Bronx"}, {@code "Brooklyn"},
     *         {@code "Queens"}, or {@code "Staten Island"} to represent the
     *         borough. Borough names are case-insensitive.
     */
    public String getBoroname() {
        return borough;
    }

    /**
     * Sets the borough name in which the tree is located. This property represents
     * the {@code boroname} field in the dataset.
     * 
     * @param borough {@code "Manhattan"}, {@code "Bronx"}, {@code "Brooklyn"},
     *                {@code "Queens"}, or {@code "Staten Island"} to represent the
     *                borough. Borough names are case-insensitive.
     * @throws IllegalArgumentException if borough is not a valid borough name.
     */
    public void setBoroname(String borough) {
        for (String item : getBoroughs()) {
            if (item.equalsIgnoreCase(borough)) {
                this.borough = item;

                return;
            }
        }

        throw new IllegalArgumentException(
                "A valid borough name is required. Allowed values: Manhattan, Bronx, Brooklyn, Queens, Staten Island. Argument name: borough.");
    }

    /**
     * Gets the x-coordinate of the tree. This property represents the {@code x_sp}
     * field in the dataset.
     * 
     * @return The x-coordinate.
     */
    public double getX_sp() {
        return x;
    }

    /**
     * Sets the x-coordinate of the tree. This property represents the {@code x_sp}
     * field in the dataset.
     * 
     * @param x the x-coordinate.
     */
    public void setX_sp(double x) {
        this.x = x;
    }

    /**
     * Gets the y-coordinate of the tree. This property represents the {@code y_sp}
     * field in the dataset.
     * 
     * @return the y-coordinate.
     */
    public double getY_sp() {
        return y;
    }

    /**
     * Sets the y-coordinate of the tree. This property represents the {@code y_sp}
     * field in the dataset.
     * 
     * @param y the y-coordinate.
     */
    public void setY_sp(double y) {
        this.y = y;
    }

    /** {@inheritDoc} */
    public int compareTo(Tree o) {
        Tree other = (Tree) o;
        int result = getSpc_common().compareToIgnoreCase(other.getSpc_common());

        if (result == 0) {
            Comparable<Integer> comparable = treeID;

            result = comparable.compareTo(other.treeID);
        }

        return result;
    }

    /** {@inheritDoc} */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Tree)) {
            return false;
        } else {
            Tree other = (Tree) obj;

            return treeID == other.treeID && getSpc_latin().equalsIgnoreCase(other.getSpc_latin())
                    && getSpc_common().equalsIgnoreCase(other.getSpc_common());
        }
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        return Objects.hash(treeID, getSpc_latin(), getSpc_common());
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return String.format("%s (%s) #%d", getSpc_common(), getSpc_latin(), treeID);
    }
}
