package project2;

import java.util.Iterator;

class TreeList implements Iterable<Tree> {
    private int count;
    private TreeListNode head;
    private TreeListNode tail;

    public TreeList() {
    }

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

    public int getTotalNumberOfTrees() {
        return count;
    }

    public int getCountByCommonName(String speciesName) {
        int result = 0;

        for (Tree tree : this) {
            if (tree.getCommonName().equalsIgnoreCase(speciesName)) {
                result++;
            }
        }

        return result;
    }

    public int getCountByLatinName(String speciesName) {
        int result = 0;

        for (Tree tree : this) {
            if (tree.getLatinName().equalsIgnoreCase(speciesName)) {
                result++;
            }
        }

        return result;
    }

    public int getCountByBorough(String boroName) {
        int result = 0;

        for (Tree tree : this) {
            if (tree.getBorough().equalsIgnoreCase(boroName)) {
                result++;
            }
        }

        return result;
    }

    public int getCountByCommonNameBorough(String speciesName, String boroName) {
        int result = 0;

        for (Tree tree : this) {
            if (tree.getBorough().equalsIgnoreCase(boroName) && tree.getCommonName().equalsIgnoreCase(speciesName)) {
                result++;
            }
        }

        return result;
    }

    public int getCountByLatinNameBorough(String speciesName, String boroName) {
        int result = 0;

        for (Tree tree : this) {
            if (tree.getBorough().equalsIgnoreCase(boroName) && tree.getLatinName().equalsIgnoreCase(speciesName)) {
                result++;
            }
        }

        return result;
    }

    @Override
    public Iterator<Tree> iterator() {
        return new TreeListIterator();
    }

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

    private class TreeListNode {
        private Tree value;
        private TreeListNode next;

        public TreeListNode getNext() {
            return next;
        }

        public Tree getValue() {
            return value;
        }

        public TreeListNode(Tree value) {
            this.value = value;
        }
    }

    private class TreeListIterator implements Iterator<Tree> {
        private TreeListNode current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Tree next() {
            Tree result = current.getValue();

            current = current.getNext();

            return result;
        }
    }
}
