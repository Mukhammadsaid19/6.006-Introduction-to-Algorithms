public class BST {
    private int key;
    private BST left;
    private BST right;
    private BST parent;

    public BST(int key){
        this.key = key;
        this.left = null;
        this.right = null;
        this.parent = null;
    }

    public static BST insert(BST node, int value) {
        if (node == null) {
            return new BST(value);
        } else {
            BST temp;

            if (value < node.key) {
                temp = insert(node.left, value);
                node.left = temp;
            } else {
                temp = insert(node.right, value);
                node.right = temp;
            }
            temp.parent = node;

            return node;
        }
    }

    public static void insert(BST node, int ...values) {
        for (int value : values) {
            insert(node, value);
        }
    }

    public static BST search(BST node, int key) {
        if (node == null || key == node.key) {
            return node;
        }
        if (key < node.key) {
            return search(node.left, key);
        } else {
            return search(node.right, key);
        }
    }

    public static BST minimum(BST node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public static BST maximum(BST node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    public static BST successor(BST node) {
        if (node.right != null) {
            return minimum(node.right);
        }
        BST temp = node.parent;
        while (temp != null && node == temp.right){
            node = temp;
            temp = temp.parent;
        }

        return temp;
    }

    public static BST predecessor(BST node) {
        if (node.left != null) {
            return maximum(node.left);
        }
        BST temp = node.parent;
        while (temp != null && node == temp.left){
            node = temp;
            temp = temp.parent;
        }

        return temp;
    }


    public static void inOrderTreeWalk(BST node) {
        if (node != null) {
            inOrderTreeWalk(node.left);
            System.out.println(node.key);
            inOrderTreeWalk(node.right);
        }
    }

    public static void preOrderTreeWalk(BST node) {
        if (node != null){
            System.out.println(node.key);
            preOrderTreeWalk(node.left);
            preOrderTreeWalk(node.right);
        }
    }

    public static void postOrderTreeWalk(BST node) {
        if (node != null){
            postOrderTreeWalk(node.left);
            postOrderTreeWalk(node.right);
            System.out.println(node.key);
        }
    }

    @Override
    public String toString() { return String.valueOf(this.key); }


    public static void main(String[] args) {
        BST mainTree = new BST(5);
        insert(mainTree, 2, 10, 3, 4, 1, 3, 5, 4, 3);
        System.out.println(successor(minimum(mainTree)));
    }
}
