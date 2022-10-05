package project2;

import java.util.ArrayList;
import java.util.Collection;

class TreeSpeciesList extends ArrayList<TreeSpecies> {
    public TreeSpeciesList() {
    }

    private TreeSpeciesList(Collection<? extends TreeSpecies> items) {
        super(items);
    }

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

    public TreeSpeciesList getByLatinName(String keyword)
    {
        if (keyword == null)
        {
            throw new IllegalArgumentException("Value cannot be null. Argument name: keyword.");
        }
        else
        {
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
