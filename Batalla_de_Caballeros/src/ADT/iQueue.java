package ADT;

public interface iQueue {
	public void enqueue(Object o) throws FullQueueException;
	public Object front() throws EmptyQueueException;
	public Object dequeue() throws EmptyQueueException;
	public boolean isEmpty();
	public int size();
}
