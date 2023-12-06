package ADT;

public interface iStack {

	public void push(Object o) throws FullStackException;
	public Object top() throws EmptyStackException;
	public Object pop() throws EmptyStackException;
	public boolean isEmpty();
	public int size();
}
