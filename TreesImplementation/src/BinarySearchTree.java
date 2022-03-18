public class BinarySearchTree {
	
	/* Class containing left
    and right child of current node
  * and key value*/
 class Node {
     NodeElement key;
     Node left, right;

     public Node(NodeElement item)
     {
         key = item;
         left = right = null;
     }
 }

 // Root of BST
 Node root;

 // Constructor
 BinarySearchTree() { root = null; }

 BinarySearchTree(NodeElement value) { root = new Node(value); }

 // This method mainly calls insertRec()
 void insert(NodeElement key) { root = insertRec(root, key); }

 /* A recursive function to
    insert a new key in BST */
 Node insertRec(Node root, NodeElement key)
 {

     /* If the tree is empty,
        return a new node */
     if (root == null) {
         root = new Node(key);
         return root;
     }

     /* Otherwise, recur down the tree */
     if (key.getZipCode() < root.key.getZipCode())
         root.left = insertRec(root.left, key);
     else if (key.getZipCode() > root.key.getZipCode())
         root.right = insertRec(root.right, key);

     /* return the (unchanged) node pointer */
     return root;
 }

 // This method mainly calls InorderRec()
 void inorder() { inorderRec(root); }

 // A utility function to
 // do inorder traversal of BST
 void inorderRec(Node root)
 {
     if (root != null) {
         inorderRec(root.left);
         System.out.println(root.key.getZipCode()+" "+root.key.getListCrimeIncidents().size());
         inorderRec(root.right);
     }
 }
 
 public Node search(Node root, int key)
 {
     // Base Cases: root is null or key is present at root
     if (root==null || root.key.getZipCode()==key)
         return root;
  
     // Key is greater than root's key
     if (root.key.getZipCode() < key)
        return search(root.right, key);
  
     // Key is smaller than root's key
     return search(root.left, key);
 }
 
//This method mainly calls deleteRec()
void deleteKey(int key) { root = deleteRec(root, key); }

/* A recursive function to
  delete an existing key in BST
 */
Node deleteRec(Node root, int key)
{
    /* Base Case: If the tree is empty */
    if (root == null)
        return root;

    /* Otherwise, recur down the tree */
    if (key < root.key.getZipCode())
        root.left = deleteRec(root.left, key);
    else if (key > root.key.getZipCode())
        root.right = deleteRec(root.right, key);

    // if key is same as root's
    // key, then This is the
    // node to be deleted
    else {
        // node with only one child or no child
        if (root.left == null)
            return root.right;
        else if (root.right == null)
            return root.left;

        // node with two children: Get the inorder
        // successor (smallest in the right subtree)
        root.key.setZipCode(minValue(root.right));

        // Delete the inorder successor
        root.right = deleteRec(root.right, root.key.getZipCode());
    }

    return root;
 }

int minValue(Node root)
{
    int minv = root.key.getZipCode();
    while (root.left != null)
    {
        minv = root.left.key.getZipCode();
        root = root.left;
    }
    return minv;
}
}
