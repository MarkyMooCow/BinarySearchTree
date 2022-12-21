package org.example;

class BST_Class {
    // Node class that defines BST node
    public class MyNode {
        int key;

        MyNode west, east;

        public MyNode(int data){
            key = data;
            west = east = null;
        }
    }
    // BST root node
    MyNode root;

    // Constructor for BST => Initial Empty Tree
    BST_Class() { root = null; }

    // Delete! Delete! A node from BST!
    void deleteKey(int key) { root = delete_recursive(root, key); }

    // Recursive Delete! Delete! function
    MyNode delete_recursive(MyNode root, int key) {
        // For empty tree
        if (root == null) return root;

        // navigate the tree like monkey
        if (key < root.key)      // Navigate west subtree
            root.west = delete_recursive(root.west, key);
        else if (key > root.key) // Navigate east subtree
            root.east = delete_recursive(root.east, key);

        else {
            // Node has only one child
            if (root.west == null)
                return root.east;
            else if (root.east == null)
                return root.west;

            // node has two children
            // get inorder successor (the minimum value is in the east subtree)
            root.key = minValue(root.east);

            // Delete! Delete! the inorder successor
            root.east = delete_recursive(root.east, root.key);
        }
        return root;
    }

    int minValue(MyNode root) {
        // originally minval = root
        int minval = root.key;
        // find minval
        while (root.west != null) {
            minval = root.west.key;
            root = root.west;
        }
        return minval;
    }

    // insert a node into the BST
    void insert(int key) { root = insert_recursive(root, key); }

    // recursive insert function
    MyNode insert_recursive(MyNode root, int key) {
        // tree's empty
        if (root == null) {
            root = new MyNode(key);
            return root;
        }
        // navigate the tree
        if (key < root.key) // insert into the west subtree
            root.west = insert_recursive(root.west, key);
        else if (key > root.key) // insert into the east subtree
            root.east = insert_recursive(root.east, key);
        // return pointer
        return root;
    }

    // method for inorder navigation of BST
    void inorder() { inorder_recursive(root); }

    // recursively navigate the BST
    void inorder_recursive(MyNode root) {
        if (root != null) {
            inorder_recursive(root.west);
            System.out.print(root.key + " ");
            inorder_recursive(root.east);
        }
    }

    boolean search(int key) {
        root = search_recursive(root, key);
        if (root != null)
            return true;
        else
            return false;
    }

    // recursive insert function
    MyNode search_recursive(MyNode root, int key) {
        // Base Cases: root is null or key is present at root
        if (root==null || root.key==key)
            return root;
        // value is greater than roots key
        if (root.key > key)
            return search_recursive(root.west, key);
        // val is less than roots key
        return search_recursive(root.east, key);
    }
}
class Main{
    public static void main(String[] args) {
        // Let's finally create a BST object!
        BST_Class cool_bst = new BST_Class();

        cool_bst.insert(5);
        cool_bst.insert(12);
        cool_bst.insert(2);
        cool_bst.insert(20);
        cool_bst.insert(3);

        // Print the BST
        System.out.println("The BST Created with input data(Left-root-right):");
        cool_bst.inorder();

        // deleting node
        System.out.println("\nThe BST after deleting 12:");
        cool_bst.deleteKey(12);
        cool_bst.inorder();

        //searching for a key in the BST
        boolean ret_val = cool_bst.search (20);
        System.out.println("\nHas key 20 been found in the BST? " + ret_val );

        boolean ret_val2 = cool_bst.search (12);
        System.out.println("\nHas key 12 been found in the BST? " + ret_val2 );
    }
}
