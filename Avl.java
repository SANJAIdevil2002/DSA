// time complexity O(log n)
// because the height of an AVL tree is logarithmic in relation to the number of nodes it contains.
// The AVL Tree is a type of Binary Search Tree named after two Soviet inventors Georgy Adelson-Velsky and Evgenii Landis who invented the AVL Tree in 1962.

// AVL trees are self-balancing, which means that the tree height is kept to a minimum so that a very fast runtime is guaranteed for searching, inserting and deleting nodes, with time complexity 
// O(logn).
public class Avl {

    
    static class AVLTree {
        // TreeNode class: Represents each node in the AVL tree
        // - data: The value stored (char in this case)
        // - left/right: Child pointers
        // - height: Height of the subtree rooted at this node
        class TreeNode {
            char data;
            TreeNode left, right;
            int height;

            TreeNode(char d) {
                data = d;
                height = 1; // Leaf nodes have height 1
            }
        }

        TreeNode root; // Root of the AVL tree

        // Helper method: Returns the height of a node (0 if null)
        // Height is the longest path from the node to a leaf
        int height(TreeNode N) {
            if (N == null) return 0;
            return N.height;
        }

        // Computes the balance factor: height(right) - height(left)
        // Balance factor must be -1, 0, or 1 for the tree to be balanced
        int getBalance(TreeNode N) {
            if (N == null) return 0;
            return height(N.right) - height(N.left);
        }

        // Right Rotation: Fixes left-heavy imbalance
        // Rotates the subtree right around node y
        TreeNode rightRotate(TreeNode y) {
            System.out.println("Rotate right on node " + y.data);
            TreeNode x = y.left;
            TreeNode T2 = x.right;

            x.right = y;
            y.left = T2;

            y.height = Math.max(height(y.left), height(y.right)) + 1;
            x.height = Math.max(height(x.left), height(x.right)) + 1;

            return x;
        }

        // Left Rotation: Fixes right-heavy imbalance
        // Rotates the subtree left around node x
        TreeNode leftRotate(TreeNode x) {
            System.out.println("Rotate left on node " + x.data);
            TreeNode y = x.right;
            TreeNode T2 = y.left;

            y.left = x;
            x.right = T2;

            x.height = Math.max(height(x.left), height(x.right)) + 1;
            y.height = Math.max(height(y.left), height(y.right)) + 1;

            return y;
        }

        // Insert a new node into the AVL tree
        // Maintains BST property and AVL balance through rotations
        TreeNode insert(TreeNode root, char data) {
            if (root == null) return new TreeNode(data);

            if (data < root.data) {
                root.left = insert(root.left, data);
            } else if (data > root.data) {
                root.right = insert(root.right, data);
            } else {
                return root; // Duplicate data is not allowed
            }

            root.height = 1 + Math.max(height(root.left), height(root.right));

            int balance = getBalance(root);

            // Left Heavy Left Case: Single right rotation
            if (balance < -1 && getBalance(root.left) <= 0) {
                return rightRotate(root);
            }
// Balance factor values

// 0: The node is in balance.
// more than 0: The node is "right heavy".
// less than 0: The node is "left heavy".
// If the balance factor is less than -1, or more than 1, 
// for one or more nodes in the tree,
//  the tree is considered not in balance, 
//  and a rotation operation is needed to restore balance.
            // Left Heavy Right Case: Left-right double rotation
            if (balance < -1 && getBalance(root.left) > 0) {
                root.left = leftRotate(root.left);
                return rightRotate(root);
            }

            // Right Heavy Right Case: Single left rotation
            if (balance > 1 && getBalance(root.right) >= 0) {
                return leftRotate(root);
            }

            // Right Heavy Left Case: Right-left double rotation
            if (balance > 1 && getBalance(root.right) < 0) {
                root.right = rightRotate(root.right);
                return leftRotate(root);
            }

            return root;
        }

        // In-order traversal: Visits left subtree, root, right subtree
        // For BSTs, this produces sorted output
        void inOrderTraversal(TreeNode node) {
            if (node != null) {
                inOrderTraversal(node.left);
                System.out.print(node.data + ", ");
                inOrderTraversal(node.right);
            }
        }
    }

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        // Insert characters into the AVL tree
        // Rotations will occur to maintain balance
        char[] letters = {'C', 'B', 'E', 'A', 'D', 'H', 'G', 'F'};
        for (char letter : letters) {
            tree.root = tree.insert(tree.root, letter);
        }
        // Print in-order traversal (sorted order)
        tree.inOrderTraversal(tree.root);
    }
}

//Java

