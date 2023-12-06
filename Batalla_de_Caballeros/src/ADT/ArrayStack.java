package ADT;

public class ArrayStack implements iStack{

	private int tope;
	private int size;
	private Object[] array;
	
	public ArrayStack(int capacidad) {
		this.size = capacidad;
		this.tope = -1;
		this.array = new Object[capacidad];
	}
	
	
	@Override
	public void push(Object o) throws FullStackException{
		if(this.tope+1 > this.size-1)
			throw new FullStackException("Pila LLena");
		this.tope++;
		this.array[this.tope]=o;
	}
	
	public String printStack() throws EmptyStackException {
		if(isEmpty())
			throw new EmptyStackException("Pila Vacía");
		
		String salida = "";
		
		for (int i = 0; i < this.array.length; i++){
		    salida += this.array[i]+ "\n";
		}
		
		return salida;
	}

	@Override
	public Object top() throws EmptyStackException{
		// TODO Auto-generated method stub
		if(isEmpty())
			throw new EmptyStackException("Pila Vacía");
		return this.array[this.tope];
	}

	@Override
	public Object pop() throws EmptyStackException{
		// TODO Auto-generated method stub
		if(isEmpty())
			throw new EmptyStackException("Pila Vacía");
		return this.array[this.tope--];
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return this.tope < 0;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return this.tope+1;
	}

}
