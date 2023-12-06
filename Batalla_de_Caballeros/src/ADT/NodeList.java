package ADT;

public class NodeList {

	public NodeList next;
	private Object element;
	
	public NodeList(Object element) {
		// Constructor default
		this.element = element;
		this.next = null;
	}
	
	public NodeList(Object element, NodeList next) {
		// Constructor sobrecargado
		this.element = element;
		this.next = next;
	}
	
	public Object getElement() {
		return element;
	}
	
	public void setElement(Object element) {
		this.element = element;
	}
	
	public NodeList getNext() {
		return next;
	}
	
	public void setNext(NodeList next) {
		this.next = next;
	}
	
}
