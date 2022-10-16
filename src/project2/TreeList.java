package project2;

import java.util.Iterator;

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
        private Tree value;
        private TreeListNode next;

        /**
         * Initializes a new instance of the {@link TreeListNode} class.
         * 
         * @param value The node data.
         */
        public TreeListNode(Tree value) {
            this.value = value;
        }

        /**
         * Gets the next node in the linked list.
         * 
         * @return A reference to the next node.
         */
        public TreeListNode getNext() {
            return next;
        }

        /**
         * Gets the node data.
         * 
         * @return A {@link Tree} instance.
         */
        public Tree getValue() {
            return value;
        }
    }

    /**
     * Provides an iterator for the linked list.
     */
    private class TreeListIterator implements Iterator<Tree> {
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

        /** {@inheritDoc} */
        @Override
        public Tree next() {
            final Tree result = current.getValue();

            current = current.getNext();

            return result;
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
     * @throws IllegalArgumentException if tree is {@code null}.
     */
    public void add(Tree tree) {
        if (tree == null) {
            throw new IllegalArgumentException("Value cannot be null. Argument name: tree.");
        } else {
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
            if (tree.getSpc_common().equalsIgnoreCase(speciesName)) {
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
            if (tree.getSpc_Latin().equalsIgnoreCase(speciesName)) {
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
            if (tree.getBoroname().equalsIgnoreCase(boroName)) {
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
            if (tree.getBoroname().equalsIgnoreCase(boroName) && tree.getSpc_common().equalsIgnoreCase(speciesName)) {
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
            if (tree.getBoroname().equalsIgnoreCase(boroName) && tree.getSpc_Latin().equalsIgnoreCase(speciesName)) {
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
