package mazeGenerator;

public class Tree {

	private Tree parent = null;

	public Tree() {

	}

	// get the root of this tree util the parent to null
	// If a tree didn't join any tree, return this current tree instance
	public Tree root() {
		return parent != null ? parent.root() : this;
	}

	// check does current tree have the same root of insert tree
	// check whether they are in the same tree (root)
	public boolean connected(Tree tree) {
		return this.root() == tree.root();
	}

	
	// Connect to the tree and set the parent to this tree's root
	public void connect(Tree tree) {
		tree.root().setParent(this);
	}

	
	// Set the parent of the object instance
	public void setParent(Tree parent) {
		this.parent = parent;
	}
}
