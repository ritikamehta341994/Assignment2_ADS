
public class TreeEncapsulation {
	
	private BinarySearchTree bTree;
	private LinkedSplayBinaryTree sTree;
	public TreeEncapsulation(BinarySearchTree btree, LinkedSplayBinaryTree stree) {
		super();
		this.bTree = btree;
		this.sTree = stree;
	}
	public BinarySearchTree getBtree() {
		return bTree;
	}
	public void setBtree(BinarySearchTree btree) {
		this.bTree = btree;
	}
	public LinkedSplayBinaryTree getStree() {
		return sTree;
	}
	public void setStree(LinkedSplayBinaryTree stree) {
		this.sTree = stree;
	}
	

}
