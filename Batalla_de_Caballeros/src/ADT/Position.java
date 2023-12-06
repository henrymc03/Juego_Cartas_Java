package ADT;

public class Position {
	
	private NodeTree nodo;

	//Constructor default
	public Position() {
		this.nodo = null;
	}

	//Constructor sobrecargado
	public Position(NodeTree nodo) {
		this.nodo = nodo;
	}

	public NodeTree getNodo() {
		return nodo;
	}

	public void setNodo(NodeTree nodo) {
		this.nodo = nodo;
	}
	
	

}
