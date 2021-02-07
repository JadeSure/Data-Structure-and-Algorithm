
class Node<T> {

	private T value;
	Node leftNode;
	Node rightNode;
	Node parent;

	public Node() {
		this.value = null;
		this.leftNode = null;
		this.rightNode = null;
		this.parent = null;
	}

	public Node(T value) {
		this.value = value;
		this.leftNode = null;
		this.rightNode = null;

	}

	public void setLeftNode(Node node) {

		leftNode = node;

	}

	public Node search(T nodeValue) {

		Node target = null;

		if (this.value.toString().equals(nodeValue.toString())) {

			return this;
		} else {
			if (leftNode != null) {
				target = leftNode.search(nodeValue);
			}

			if (target != null) {
				return target;
			}

			if (rightNode != null) {
				target = rightNode.search(nodeValue);
			}
		}

		return target;

	}

	public void setRightNode(Node node) {

		rightNode = node;

	}

	public void setParent(Node parent2) {
		parent = parent2;

	}

	public Node getLeftNode() {
		return leftNode;
	}

	public Node getParent() {
		return parent;
	}

	public Node getRightNode() {
		return rightNode;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

}
