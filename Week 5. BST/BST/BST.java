package BST;

import java.util.NoSuchElementException;

public class BST<Key extends Comparable<Key>> {
    private Node root;

    private class Node {
        private Key key;
        public Node left, right, parent;

        public Node(Key key){
            this.key = key;
            this.left = this.right = this.parent = null;
        }

        @Override
        public String toString() { return String.valueOf(this.key); }
    }


    public BST() {
        this.root = null;
    }

    public void insert(Node z) {
        Node y = null;
        Node x = root;
        while (x != null){
            y = x;
            int comparedToValue = z.key.compareTo(x.key);
            if (comparedToValue < 0){
                x = x.left;
            } else {
                x = x.right;
            }
        }

        z.parent = y;
        if (y == null) {
            root = z; // BST was empty
        } else if (z.key.compareTo(y.key) < 0) {
            y.left = z;
        } else {
            y.right = z;
        }
    }

    public void insert(Key key) {
        insert(new Node(key));
    }

    public void insert(Key ...keys) {
        for (Key key : keys) {
            insert(key);
        }
    }

    public boolean contains(Key key){
        return search(key).key != null;
    }

    public Node search(Key key) {
        return search(root, key);
    }

    public Node search(Node x, Key key) {
        while (x != null && key != x.key) {
            int comparedToValue = key.compareTo(x.key);
            if (comparedToValue < 0) {
                x = x.left;
            } else {
                x = x.right;
            }
        }
        return x;
    }

    public Key minimum() {
        if (root == null) {
            throw new IllegalArgumentException("ERROR: minimum() is called in an empty BST");
        }
        return minimum(root).key;
    }

    private Node minimum(Node x) {
        while (x.left != null) {
            x = x.left;
        }
        return x;
    }

    public Key maximum() {
        if (root == null) {
            throw new IllegalArgumentException("ERROR: maximum() is called in an empty BST");
        }
        return maximum(root).key;
    }

    public Node maximum(Node x) {
        while (x.right != null) {
            x = x.right;
        }
        return x;
    }

    public Node successor(Node x) {
        if (x.right != null){
            return minimum(x.right);
        }
        Node y = x.parent;
        while (y != null && x == y.right){
            x = y;
            y = y.parent;
        }

        return y;
    }

    public Node successor(Key key) {
        Node temp = search(key);
        return successor(temp);
    }

    public void delete(Key key) {
        Node toBeDeletedNode = search(key);

    }

    public void transplant(Node u, Node v){
        if (u.parent == null) { // u is a root of BST
            root = v;
        } else if (u == u.parent.left) {
            u.parent.left = v;
        } else {
            u.parent.right = v;
        }
        if (v != null){
            v.parent = u.parent;
        }
    }

    public void delete(Node z) {
        if (z.left == null){
            transplant(z, z.right);
        } else if (z.right == null) {
            transplant(z, z.left);
        } else {
            Node y = minimum(z.right);
            if (y.parent != z) {
                transplant(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }
            transplant(z, y);
            y.left = z.left;
            y.left.parent = y;
        }
    }

    public void inOrderTreeWalk() {
        inOrderTreeWalk(root);
    }

    private void inOrderTreeWalk(Node root) {
        if (root != null) {
            inOrderTreeWalk(root.left);
            System.out.println(root.key);
            inOrderTreeWalk(root.right);
        }
    }

//    public static void preOrderTreeWalk(Node root) {
//        if (root != null) {
//            System.out.println(root.key);
//            preOrderTreeWalk(root.left);
//            preOrderTreeWalk(root.right);
//        }
//    }
//
//    public static void postOrderTreeWalk(Node root) {
//        if (root != null) {
//            postOrderTreeWalk(root.left);
//            postOrderTreeWalk(root.right);
//            System.out.println(root.key);
//        }
//    }


    public static void main(String[] args) {
        BST<Integer> tree = new BST<Integer>();
        tree.insert(8, 6, 10, 5, 7, 8, 9, 12);
        System.out.println(tree.successor(6));
    }
}
