
package LEVEL3;

import java.util.Scanner;

public class BinarySearchTree {

    // Node structure
    static class Node {
        int data;
        Node left, right;

        Node(int value) {
            data = value;
            left = null;
            right = null;
        }
    }

    static Node root = null;
    static Scanner sc = new Scanner(System.in);

    // Insert a node
    static Node insert(Node root, int value) {

        if (root == null) {
            return new Node(value);
        }

        if (value < root.data) {
            root.left = insert(root.left, value);
        } else if (value > root.data) {
            root.right = insert(root.right, value);
        } else {
            System.out.println("Duplicate values are not allowed!");
        }

        return root;
    }

    // Search for a value
    static boolean search(Node root, int value) {

        if (root == null) {
            return false;
        }

        if (root.data == value) {
            return true;
        }

        if (value < root.data) {
            return search(root.left, value);
        }

        return search(root.right, value);
    }

    // Find minimum value in a subtree
    static Node findMin(Node root) {

        while (root.left != null) {
            root = root.left;
        }

        return root;
    }

    // Delete a node
    static Node delete(Node root, int value) {

        if (root == null) {
            return null;
        }

        if (value < root.data) {
            root.left = delete(root.left, value);
        } else if (value > root.data) {
            root.right = delete(root.right, value);
        } else {

            // Case 1: No child
            if (root.left == null && root.right == null) {
                return null;
            }

            // Case 2: One child
            if (root.left == null) {
                return root.right;
            }

            if (root.right == null) {
                return root.left;
            }

            // Case 3: Two children
            Node successor = findMin(root.right);

            root.data = successor.data;

            root.right = delete(root.right, successor.data);
        }

        return root;
    }

    // Inorder traversal: Left, Root, Right
    static void inorder(Node root) {

        if (root != null) {
            inorder(root.left);
            System.out.print(root.data + " ");
            inorder(root.right);
        }
    }

    // Preorder traversal: Root, Left, Right
    static void preorder(Node root) {

        if (root != null) {
            System.out.print(root.data + " ");
            preorder(root.left);
            preorder(root.right);
        }
    }

    // Postorder traversal: Left, Right, Root
    static void postorder(Node root) {

        if (root != null) {
            postorder(root.left);
            postorder(root.right);
            System.out.print(root.data + " ");
        }
    }

    // Main method
    public static void main(String[] args) {

        while (true) {

            System.out.println("\n===== BINARY SEARCH TREE =====");
            System.out.println("1. Insert Node");
            System.out.println("2. Search Node");
            System.out.println("3. Delete Node");
            System.out.println("4. Inorder Traversal");
            System.out.println("5. Preorder Traversal");
            System.out.println("6. Postorder Traversal");
            System.out.println("7. Exit");

            System.out.print("Enter your choice: ");

            if (!sc.hasNextInt()) {
                System.out.println("Please enter a valid number!");
                sc.nextLine();
                continue;
            }

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter value to insert: ");

                    if (sc.hasNextInt()) {
                        int value = sc.nextInt();
                        root = insert(root, value);
                        System.out.println("Node inserted successfully!");
                    } else {
                        System.out.println("Invalid input!");
                        sc.nextLine();
                    }
                    break;

                case 2:
                    System.out.print("Enter value to search: ");

                    if (sc.hasNextInt()) {
                        int value = sc.nextInt();

                        if (search(root, value)) {
                            System.out.println("Value found!");
                        } else {
                            System.out.println("Value not found!");
                        }
                    } else {
                        System.out.println("Invalid input!");
                        sc.nextLine();
                    }
                    break;

                case 3:
                    System.out.print("Enter value to delete: ");

                    if (sc.hasNextInt()) {
                        int value = sc.nextInt();

                        if (search(root, value)) {
                            root = delete(root, value);
                            System.out.println("Node deleted successfully!");
                        } else {
                            System.out.println("Value not found!");
                        }
                    } else {
                        System.out.println("Invalid input!");
                        sc.nextLine();
                    }
                    break;

                case 4:
                    System.out.print("Inorder Traversal: ");
                    inorder(root);
                    System.out.println();
                    break;

                case 5:
                    System.out.print("Preorder Traversal: ");
                    preorder(root);
                    System.out.println();
                    break;

                case 6:
                    System.out.print("Postorder Traversal: ");
                    postorder(root);
                    System.out.println();
                    break;

                case 7:
                    System.out.println("Exiting Binary Search Tree!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}
