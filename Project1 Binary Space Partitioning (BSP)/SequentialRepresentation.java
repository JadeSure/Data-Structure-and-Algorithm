import java.io.PrintWriter;
import java.util.Arrays;

/**
 * Sequential Tree Representation implementation for the {@link BSPTree}
 * interface.
 * <p>
 * Your task is to complete the implementation of this class. You may add
 * methods, but ensure your modified class compiles and runs.
 *
 * @author Jeffrey Chan, 2016.
 * @author Yongli Ren, 2019.
 */
public class SequentialRepresentation<T> implements BSPTree<T> {
	
	MyArray array;

	/**
	 * Constructs empty graph.
	 */
	public SequentialRepresentation() {
		
		array = new MyArray<T>();
	} // end of SequentialRepresentation()

	@Override
	public void setRootNode(T nodeLabel) {
		// root = new Node(nodeLabel);
		array.add(nodeLabel);
	} // end of setRootNode()

	@Override
	public void splitNode(T srcLabel, T leftChild, T rightChild) {
		// this value is not exist
		if (!findNode(srcLabel)) {
			return;
		}
		// set the left and right value for the source label
		int leftIndex = array.search(srcLabel) * 2 + 1;
		int rightIndex = leftIndex + 1;

		array.insert(leftIndex, leftChild);
		array.insert(rightIndex, rightChild);
	
	} // end of splitNode

	@Override
	public boolean findNode(T nodeLabel) {
		// do not have this value
		if (array.search(nodeLabel) == -1) {
			return false;
		}
		return true;
	} // end of findNode

	@Override
	public String findParent(T nodeLabel) {
		// this value is not exist
		if (!findNode(nodeLabel)) {
			return "Do not have this value";
		}
		// root node does not have parent node
		if (array.search(nodeLabel) == 0) {
			String result =  nodeLabel.toString();
			return result;
		}

		int parentIndex = (array.search(nodeLabel) - 1) / 2;
		String result =  nodeLabel + " " + array.get(parentIndex);
		return result;
	} // end of findParent

	@Override
	public String findChildren(T nodeLabel) {
		// this value is not exist
		if (!findNode(nodeLabel)) {
			return "Do not have this value";
		}
		// this node does not have children
		if ((array.search(nodeLabel) * 2 + 2) > array.size() - 1) {
			return nodeLabel.toString();
		}
		int leftChildNode = array.search(nodeLabel) * 2 + 1;
		String result = nodeLabel + " " + array.get(leftChildNode) + " " + array.get(leftChildNode + 1);
		return result;
	} // end of findParent

	// to be finished
	@Override
	public void printInPreorder(PrintWriter writer) {
		printInPreorder(writer, 0);
	} // end of printInPreorder

	public void printInPreorder(PrintWriter writer, int index) {
		if (array.size() == 0 || array == null) {
			return;
		}

		writer.print(array.get(index)+" ");

		// handle left node
		if (2 * index + 1 < array.size()) {
			printInPreorder(writer, 2 * index + 1);
		}
		// handle right node
		if (2 * index + 2 < array.size()) {
			printInPreorder(writer, 2 * index + 2);
		}
	}

	@Override
	public void printInInorder(PrintWriter writer) {
		printInInorder(writer, 0);
	} // end of printInInorder

	
	public void printInInorder(PrintWriter writer, int index) {
		if (array.size() == 0 || array == null) {
			return;
		}

		// handle left node
		if (2 * index + 1 < array.size()) {
			printInInorder(writer, 2 * index + 1);
		}

		writer.print(array.get(index)+" ");
		// handle right node
		if (2 * index + 2 < array.size()) {
			printInInorder(writer, 2 * index + 2);
		}
	}

	@Override
	public void printInPostorder(PrintWriter writer) {
		printInPostorder(writer, 0);
	} // end of printInPostorder

	public void printInPostorder(PrintWriter writer, int index) {
		if (array.size() == 0 || array == null) {
			return;
		}

		// handle left node
		if (2 * index + 1 < array.size()) {
			printInPostorder(writer, 2 * index + 1);
		}

		
		// handle right node
		if (2 * index + 2 < array.size()) {
			printInPostorder(writer, 2 * index + 2);
		}
		
		writer.print(array.get(index)+" ");
	}
	// need to be delete later
	@Override
	public String toString() {
		return array.show();
	}

	// need to be delete later

} // end of class SequentialRepresentation