public class LinkedSplayBinaryTree{

	  private Node root;
	  public LinkedSplayBinaryTree(){
		  root = null;
	  }
	  //---------------- nested Node class ----------------
	  /** Nested  class for a binary tree node. */
	  protected class Node  {
	    private NodeElement element;  // an element stored at this node
	    private Node parent;     	// a reference to the parent node (if any)
	    private Node left;       	// a reference to the left child (if any)
	    private Node right;      	// a reference to the right child (if any)

	    /**
	     * Constructs a node with the given element and neighbors.
	     *
	     * @param e  the element to be stored
	     * @param above       reference to a parent node
	     * @param leftChild   reference to a left child node
	     * @param rightChild  reference to a right child node
	     */
	    public Node(NodeElement e, Node above, Node leftChild, Node rightChild) {
	      element = e;
	      parent = above;
	      left = leftChild;
	      right = rightChild;
	    }

	    // accessor methods
	    public NodeElement getElement() { return element; }
	    public Node getParent() { return parent; }
	    public Node getLeft() { return left; }
	    public Node getRight() { return right; }

	    // update methods
	    public void setElement(NodeElement e) { element = e; }
	    public void setParent(Node parentNode) { parent = parentNode; }
	    public void setLeft(Node leftChild) { left = leftChild; }
	    public void setRight(Node rightChild) { right = rightChild; }
	  } //----------- end of nested Node class -----------

	  /** Factory function to create a new node storing element e. */
	  protected Node createNode(NodeElement e, Node parent,
	                                  Node left, Node right) {
	    return new Node(e, parent, left, right);
	  }
	  
	  public void insertNewNode(NodeElement newNode) {	
			Node node =createNode(newNode,null,null,null); // create a new node with input data
			Node tempNode = null;
			Node traverseNode = this.root;

			//start traversing the tree from the root
			while (traverseNode != null) {
				tempNode = traverseNode;
				if (node.getElement().getZipCode() < traverseNode.getElement().getZipCode()) {
					traverseNode = traverseNode.left;
				} else {
					traverseNode = traverseNode.right;
				}
			}

			
			node.parent = tempNode;
			if (tempNode == null) {
				root = node;
			} else if (node.getElement().getZipCode() < tempNode.getElement().getZipCode()) {
				tempNode.left = node;
			} else {
				tempNode.right = node;
			}

			// splay node to make it the root node
			splay(node);
		}
	  
	  //Zig to rotate right
	  public void rotateRight(Node node) {
		  
		  Node newParent = node.left ;
		  node.left = newParent.right;
		  if(newParent.right != null)
			  newParent.right.parent = node;
		  newParent.parent = node.parent;
		  if(node.parent == null)
			  this.root = newParent;
		  else if(node == node.parent.right)
			  node.parent.right = newParent;
		  else
			  node.parent.left = newParent;
		  
		  newParent.right = node;
		  node.parent  = newParent;
		  
		  
	  }
	  
	  //Zag to rotate left
	  public void rotateLeft(Node node) {
		  
		  Node newParent = node.right;
		  node.right = newParent.left;
			if (newParent.left != null) {
				newParent.left.parent = node;
			}
			newParent.parent = node.parent;
			if (node.parent == null) {
				this.root = newParent;
			} else if (node == node.parent.left) {
				node.parent.left = newParent;
			} else {
				node.parent.right = newParent;
			}
			newParent.left = node;
			node.parent = newParent;
		  
	  }
	  
	  /**
	   * This method makes node the root of the tree
	   * @param node
	   */
	  public void splay(Node node) {
		 while(node.parent != null) {
			 if(node.parent.parent == null) {
				 if(node == node.parent.left) {
					 //zig rotation if the node is left child of the parent
					 rotateRight(node.parent);
				 }
				 else {
					 //zag rotation if the node is right child of the parent
					 rotateLeft(node.parent);
				 }	 
			 }
			 else if(node == node.parent.left && node.parent == node.parent.parent.left) {
				 /**
				  * zig-zig rotation when the node's parent is the left child of its parent
				  * and the node itself is the left child of its parent
				  */
				 rotateRight(node.parent.parent);
				 rotateRight(node.parent);
			 }
			 else if(node == node.parent.right && node.parent == node.parent.parent.right) {
				 /**
				  * zag-zag rotation when the node's parent is the right child of its parent
				  * and the node  is the right child of its parent
				  */
				 rotateLeft(node.parent.parent);
				 rotateLeft(node.parent);
			 }
			 else if(node == node.parent.right && node.parent == node.parent.parent.left) {
				 /**
				  * zag-zig rotation when the node's parent is the left child of its parent
				  * and the node  is the right child of its parent
				  */
				 rotateLeft(node.parent);
				 rotateRight(node.parent);
			 }
			 else {
				 /**
				  * zig-zag rotation when the node's parent is the right child of its parent
				  * and the node is the left child of its parent
				  */
				 rotateRight(node.parent);
				 rotateLeft(node.parent);
			 }
		 }
	  }
	  
	  public Node search(int zipCode) {
		 
		 Node node = searchTreeHelper(root,zipCode);
		 if(node != null)
			 splay(node);
		 return node;
	  }
	  
	  public Node searchTreeHelper(Node node, int zipCode) {
		  //If node matches the zipCode we are searching for return the node
		  if(node == null || node.getElement().getZipCode() == zipCode)
			  return node;
		  //If zipCode to be searched is less than the current node traverse the left subtree
		  if(zipCode < node.getElement().getZipCode())
			  return searchTreeHelper(node.getLeft(), zipCode);
		  
		  return searchTreeHelper(node.getRight(),zipCode);
	  }
	  
	  private void printHelper(Node currPtr, String indent, boolean last) {
			// print the tree structure on the screen
		   	if (currPtr != null) {
			   System.out.print(indent);
			   if (last) {
			      System.out.print("R----");
			      indent += "     ";
			   } else {
			      System.out.print("L-----");
			      indent += "|    ";
			   }

			   System.out.println("Zip:"+currPtr.getElement().getZipCode()+" Count :"+currPtr.getElement().getListCrimeIncidents().size());

			   printHelper(currPtr.left, indent, false);
			   printHelper(currPtr.right, indent, true);
			}
		}
	  
	  public void printTree() {
			printHelper(this.root, "", true);
		}
	  
	  
}
