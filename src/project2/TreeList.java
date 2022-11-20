package project2;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Represents a collection of trees.
 * 
 * @author Ishan Pranav
 */
public class TreeList implements Iterable<Tree> {

    /**
     * Provides a node for the linked list.
     * 
     * @author Ishan Pranav
     */
    private class TreeListNode {
        private final Tree value;

        private TreeListNode next;

        /**
         * Initializes a new instance of the {@link TreeListNode} class.
         * 
         * @param value The node data.
         */
        public TreeListNode(Tree value) {
            this.value = value;
        }
    }

    /**
     * Provides an iterator for the linked list.
     */
    private class TreeListIterator implements Iterator<Tree> {
        private final int expectedVersion = version;

        private TreeListNode current = head;

        /**
         * Initializes a new instance of the {@link TreeListIterator} class.
         */
        public TreeListIterator() {
        }

        /** {@inheritDoc} */
        @Override
        public boolean hasNext() {
            return current != null;
        }

        /**
         * Returns the next element in the iteration.
         * 
         * @throws ConcurrentModificationException if the list has been modified
         *                                         concurrently with the iteration
         * @throws NoSuchElementException          if the iteration has no more elements
         */
        @Override
        public Tree next() {
            if (version != expectedVersion) {
                throw new ConcurrentModificationException("Collection was modified during iteration.");
            }

            if (current == null) {
                throw new NoSuchElementException("Collection has no more elements.");
            }

            final Tree result = current.value;

            current = current.next;

            return result;
        }
    }

    private int count;
    private int version;
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
     * @throws IllegalArgumentException if tree is {@code null}.
     */
    public void add(Tree tree) {
        if (tree == null) {
            throw new IllegalArgumentException("Value cannot be null. Argument name: tree.");
        }
        
        final TreeListNode node = new TreeListNode(tree);

        if (tail == null) {
            // If the linked list is empty, the new node is both the first and the last
            // node

            head = node;
            tail = node;
        } else {
            // If the linked list is not empty, the new node follows the existing last node,
            // and is thus the new last node

            tail.next = node;
            tail = node;
        }

        count++;
        version++;
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
        final StringBuilder result = new StringBuilder("[");
        final Iterator<Tree> treeListIterator = iterator();

        boolean hasNext = treeListIterator.hasNext();

        // If there are any elements, iteratively append them within the brackets

        while (hasNext) {
            Tree current = treeListIterator.next();

            result.append(current);

            hasNext = treeListIterator.hasNext();

            // If there are more elements, add a trailing comma

            if (hasNext) {
                result.append(", ");
            }
        }

        return result.append("]").toString();
    }
}
