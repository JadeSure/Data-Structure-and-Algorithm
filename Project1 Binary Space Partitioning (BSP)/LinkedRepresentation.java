import java.io.PrintWriter;

/**
 * Linked Tree Representation implementation for the {@link BSPTree} interface.
 * <p>
 * Your task is to complete the implementation of this class. You may add
 * methods, but ensure your modified class compiles and runs.
 *
 * @author Jeffrey Chan, 2016.
 * @author Yongli Ren, 2019.
 */
public class LinkedRepresentation<T> implements BSPTree<T> {

	private Node root;

	public LinkedRepresentation() {

		this.root = new Node<T>();

	} // end of LinkedRepresentation()

	@Override
	public void setRootNode(T nodeLabel) {

		this.root = new Node<T>(nodeLabel);
	} // end of setRootNode()

	@Override
	public void splitNode(T srcLabel, T leftChild, T rightChild) {

		Node parent = root.search(srcLabel);
		if (parent != null) {

			if (parent.getLeftNode() == null && parent.getRightNode() == null) {
				Node left = new Node(leftChild);
				left.setParent(parent);
				parent.setLeftNode(left);

				Node right = new Node(rightChild);
				right.setParent(parent);
				parent.setRightNode(right);
			}

		}

	} // end of splitNode

	@Override
	public boolean findNode(T nodeLabel) {
		if (root.search(nodeLabel) != null)
			return true;
		return false;
	} // end of findNode

	@Override
	public String findParent(T nodeLabel) {

		Node node = root.search(nodeLabel);

		if (node.getParent() != null) {
			return node.getValue().toString() + " " + node.getParent().getValue().toString();
		} else if (node != null) {
			return node.getValue().toString();

		} else
			return "The value dosen't excist";

	} // end of findParent

	@Override
	public String findChildren(T nodeLabel) {
		Node parent = root.search(nodeLabel);

		if (parent.getLeftNode() != null && parent.getRightNode() != null) {
			return parent.getValue().toString() + " " + parent.getLeftNode().getValue().toString() + " "
					+ parent.getRightNode().getValue().toString();
		} else if (parent != null) {
			return parent.getValue().toString();
		} else
			return null;
	} // end of findParent

	@Override
	public void printInPreorder(PrintWriter writer) {
		printInPreorder(writer, root);
	} // end of printInPreorder

	public void printInPreorder(PrintWriter writer, Node node) {
		if (node == null) {
			return;
		}
		// the node
		writer.print(node.getValue() + " ");

		// first recur on left subtree
		printInPreorder(writer, node.getLeftNode());

		// then recur on right subtree
		printInPreorder(writer, node.getRightNode());
	}

	@Override
	public void printInInorder(PrintWriter writer) {
		printInInorder(writer, root);
	} // end of printInInorder

	public void printInInorder(PrintWriter writer, Node node) {
		if (node == null) {
			return;
		}
		// first recur on left subtree
		printInInorder(writer, node.getLeftNode());

		// the node
		writer.print(node.getValue() + " ");

		// then recur on right subtree
		printInInorder(writer, node.getRightNode());

	}

	@Override
	public void printInPostorder(PrintWriter writer) {
		printInPostorder(writer, root);
	}// end of printInPostorder

	public void printInPostorder(PrintWriter writer, Node node) {
		if (node == null)
			return;

		// first recur on left subtree
		printInPostorder(writer, node.getLeftNode());

		// then recur on right subtree
		printInPostorder(writer, node.getRightNode());

		// the node
		writer.print(node.getValue() + " ");

	}

} // end of class LinkedRepresentation
