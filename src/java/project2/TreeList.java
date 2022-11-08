package project2;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

/**
 * Represents a collection of trees.
 * 
 * @author Ishan Pranav
 */
public class TreeList implements Iterable<Tree> {
    private class TreeListNode {
        private Tree value;
        private TreeListNode next;

        public TreeListNode(Tree value) {
            this.value = value;
        }

        public TreeListNode getNext() {
            return next;
        }

        public Tree getValue() {
            return value;
        }
    }

    private class TreeListIterator implements Iterator<Tree> {
        private final int expectedCount = count;

        private TreeListNode current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Tree next() {
            if (count != expectedCount) {
                throw new ConcurrentModificationException();
            } else {
                Tree result = current.getValue();

                current = current.getNext();

                return result;
            }
        }
    }

    private int count;
    private TreeListNode head;
    private TreeListNode tail;

    /**
     * Initializes a new instance of the {@link TreeList} class.
     */
    public TreeList() {
    }

    /**
     * Adds a tree to the collection.
     * 
     * @param tree the tree to add.
     * @throws IllegalArgumentException if tree is null.
     */
    public void add(Tree tree) {
        if (tree == null) {
            throw new IllegalArgumentException("Value cannot be null. Argument name: tree.");
        } else {
            TreeListNode node = new TreeListNode(tree);

            if (tail == null) {
                head = node;
                tail = node;
            } else {
                tail.next = node;
                tail = node;
            }

            count++;
        }
    }

    /**
     * Gets the total number of trees in the collection.
     * 
     * @return The number of trees.
     */
    public int getTotalNumberOfTrees() {
        return count;
    }

    /**
     * Gets the number of trees whose species' common names are the same as the
     * given species name using a case-insensitive comparison.
     * 
     * @param speciesName the common name
     * @return The number of trees with the given common name.
     */
    public int getCountByCommonName(String speciesName) {
        int result = 0;

        for (Tree tree : this) {
            if (tree.getCommonName().equalsIgnoreCase(speciesName)) {
                result++;
            }
        }

        return result;
    }

    /**
     * Gets the number of trees whose species' scientific names are the same as the
     * given species name using a case-insensitive comparison.
     * 
     * @param speciesName the scientific name.
     * @return The number of trees with the given scientific name.
     */
    public int getCountByLatinName(String speciesName) {
        int result = 0;

        for (Tree tree : this) {
            if (tree.getLatinName().equalsIgnoreCase(speciesName)) {
                result++;
            }
        }

        return result;
    }

    /**
     * Gets the number of trees located in the given borough using a
     * case-insensitive comparison.
     * 
     * @param boroName the borough name.
     * @return The number of trees in the given borough.
     */
    public int getCountByBorough(String boroName) {
        int result = 0;

        for (Tree tree : this) {
            if (tree.getBorough().equalsIgnoreCase(boroName)) {
                result++;
            }
        }

        return result;
    }

    /**
     * Gets the number of trees whose species' common names are the same as the
     * given species name and which are located in the given borough. All
     * comparisons are case-insensitive.
     * 
     * @param speciesName the common name.
     * @param boroName    the borough name.
     * @return The number of trees with the given common name in the given borough.
     */
    public int getCountByCommonNameBorough(String speciesName, String boroName) {
        int result = 0;

        for (Tree tree : this) {
            if (tree.getBorough().equalsIgnoreCase(boroName) && tree.getCommonName().equalsIgnoreCase(speciesName)) {
                result++;
            }
        }

        return result;
    }

    /**
     * Gets the number of trees whose species' scientific names are the same as the
     * given species name and which are located in the given borough. All
     * comparisons are case-insensitive.
     * 
     * @param speciesName the scientific name.
     * @param boroName    the borough name.
     * @return The number of trees with the given scientific name in the given
     *         borough.
     */
    public int getCountByLatinNameBorough(String speciesName, String boroName) {
        int result = 0;

        for (Tree tree : this) {
            if (tree.getBorough().equalsIgnoreCase(boroName) && tree.getLatinName().equalsIgnoreCase(speciesName)) {
                result++;
            }
        }

        return result;
    }

    /** {@inheritDoc} */
    @Override
    public Iterator<Tree> iterator() {
        return new TreeListIterator();
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        Iterator<Tree> treeListIterator = iterator();
        boolean hasNext = treeListIterator.hasNext();

        while (hasNext) {
            Tree current = treeListIterator.next();

            result.append(current);

            hasNext = treeListIterator.hasNext();

            if (hasNext) {
                result.append(", ");
            }
        }

        return result.append("]").toString();
    }
}
