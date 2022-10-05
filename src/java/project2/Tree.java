package project2;

import java.util.Objects;

class Tree implements Comparable<Tree> {
    private static final String MANHATTAN_BOROUGH = "Manhattan";
    
    private int treeID;
    private String status;
    private String health;
    private String latinName;
    private String commonName;
    private int zipCode;
    private String borough = MANHATTAN_BOROUGH;
    private double x;
    private double y;
    
    public int getTreeID() {
        return treeID;
    }

    public void setTreeID(int treeID) {
        if (treeID < 0) {
            throw new IllegalArgumentException(
                    "Value is out of range. A non-negative number is required. Argument name: treeID.");
        } else {
            this.treeID = treeID;
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if (status == null || status.equals("") || status.equalsIgnoreCase("Alive") || status.equalsIgnoreCase("Dead")
                || status.equalsIgnoreCase("Stump")) {
            this.status = status;
        } else {
            throw new IllegalArgumentException(
                    "A valid status value is required. Allowed values: null or empty, Alive, Dead, and Stump. Argument name: status.");
        }
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        if (health == null || health.equals("") || health.equalsIgnoreCase("Good") || health.equalsIgnoreCase("Fair")
                || health.equalsIgnoreCase("Poor")) {
            this.health = health;
        } else {
            throw new IllegalArgumentException(
                    "A valid health value is required. Allowed values: null or empty, Good, Fair, and Poor. Argument name: health.");
        }
    }

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

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        if (zipCode >= 0 && zipCode <= 99999) {
            this.zipCode = zipCode;
        } else {
            throw new IllegalArgumentException(
                    "Value is out of range. Zip codes must be between 00000 and 99999, inclusive. Argument name: zipCode.");
        }
    }

    public String getBorough() {
        return borough;
    }

    public void setBorough(String borough) {
        for (String item : getBoroughs()) {
            if (item.equalsIgnoreCase(borough)) {
                this.borough = item;

                return;
            }
        }

        throw new IllegalArgumentException(
                "A valid borough name is required. Allowed values: Manhattan, Bronx, Brooklyn, Queens, Staten Island. Argument name: borough.");
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Tree(int treeID, TreeSpecies species) {
        setTreeID(treeID);

        if (species == null) {
            throw new IllegalArgumentException("Value cannot be null. Argument name: species.");
        } else {
            setLatinName(species.getLatinName());
            setCommonName(species.getCommonName());
        }
    }

    @Override
    public int compareTo(Tree o) {
        Tree other = (Tree)o;
        int result = commonName.compareToIgnoreCase(other.commonName);

        if (result == 0)
        {
            Comparable<Integer> comparable = treeID;

            result = comparable.compareTo(other.treeID);
        }

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Tree)) {
            return false;
        } else {
            Tree other = (Tree) obj;

            return treeID == other.treeID && latinName.equalsIgnoreCase(other.latinName)
                    && commonName.equalsIgnoreCase(other.commonName);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(treeID, latinName, commonName);
    }

    @Override
    public String toString() {
        return String.format("%s (%s) #%d", commonName, latinName, treeID);
    }

    public static String[] getBoroughs() {
        return new String[] { MANHATTAN_BOROUGH, "Bronx", "Brooklyn", "Queens", "Staten Island" };
    }
}
