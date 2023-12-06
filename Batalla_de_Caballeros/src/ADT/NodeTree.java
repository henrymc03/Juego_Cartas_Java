package ADT;

public class NodeTree {

	private Object element;
	public NodeTree parent, left, right;
	
	//Constructor default
	public NodeTree() {
		this.element = this.parent = this.left = this.right = null;
	}

	//Constructor sobrecargado
	public NodeTree(Object element) {
		
		this.element = element;
		this.parent = this.left = this.right = null;
	}

	public Object getElement() {
		return element;
	}

	public void setElement(Object element) {
		this.element = element;
	}

	public NodeTree getParent() {
		return parent;
	}

	public void setParent(NodeTree parent) {
		this.parent = parent;
	}

	public NodeTree getLeft() {
		return left;
	}

	public void setLeft(NodeTree left) {
		this.left = left;
	}

	public NodeTree getRight() {
		return right;
	}

	public void setRight(NodeTree right) {
		this.right = right;
	}
	
	
}
