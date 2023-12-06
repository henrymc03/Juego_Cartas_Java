package ADT;

import Domain.Jugador;

public class SimpleList {

	private NodeList head, tail;
	private int size;
	
	public SimpleList() {
		// Constructor default
		this.head = null;
		this.tail = null;
		this.size = 0;
	}
	
	//Setters y Getters
	public NodeList getHead() {
		return head;
	}
	
	public NodeList getTail() {
		return tail;
	}
	
	public void setHead(NodeList head) {
		this.head = head;
	}
	
	public void setTail(NodeList tail) {
		this.tail = tail;
	}
	
	public boolean isEmpty(){
        return this.head == null;
    }
	
    public int getSize(){
        return this.size;
    }
    
    public void addFirst(NodeList nuevo){
    	
        if (isEmpty()) {
            this.head = nuevo;
            this.tail = nuevo;
        } else{
            nuevo.setNext(this.head);
            this.head = nuevo;
        }
        this.size++;
    }
    
    public void addLast(NodeList nuevo){
    	
        if (isEmpty()) {
            this.head = nuevo;
            this.tail = nuevo;
        } else{
            nuevo.setNext(null);
            this.tail.setNext(nuevo);
            this.tail = nuevo;
        }
        this.size++;
    }
    
    public boolean search(NodeList referencia){
        
    	NodeList aux = this.head;
        boolean bandera = false;
        
        while(aux != null && bandera != true){
            
        	if (referencia == aux)
                bandera = true;
            else
                aux = aux.getNext();
        }
        return bandera;
    }
    
    public Object remove(NodeList referencia){
    	Object salida = null;
        
    	if (search(referencia)) {
        	
            if (this.head == referencia) {
            	salida = this.head.getElement();
                this.head = this.head.getNext();
            	
            }else{
                NodeList aux = this.head;
                
                while(aux != referencia){
                	salida = aux.getNext().getElement();
                	aux = aux.getNext();
                }
                
                NodeList aux2 = this.head;
                
                while(aux2.getNext() != referencia){
                	aux2 = aux2.getNext();
                }
                
                NodeList siguiente = aux2.getNext().getNext();
                aux2.setNext(siguiente);  
            }
            
            this.size--;
            
        }else
        	System.out.println("No se encontro el nodo");
        	
        return salida;
    }
    
    public void addInSortedList(Object element) {
        NodeList newNode=new NodeList(element);
       if(isEmpty()){
            head=newNode;
        }else{
           if(greater(head.getElement(),element)){
               newNode.next=head;
               head=newNode;
           }else{
               NodeList prev=head;
               NodeList aux=head.next;
               boolean added=false;
               while(aux!=null&&added){
                   if(lees(element, aux.getElement())){
                       prev.next=newNode;
                       newNode.next=aux;
                       added=true;
                   }
                   prev=aux;
                   aux=aux.next;
               }
               if(!added){
                   prev.next=newNode;
               }
           }
       }
        
    }
    
    private String instanceOf(Object a, Object b) {
  
        if (a instanceof Jugador && b instanceof Jugador) {
            return "Jugador";
        }
      
        return "unknown"; //desconocido
    }
    
    public void clear() {
        this.head = null;
    }

    private  boolean greater(Object a, Object b) {
        switch (instanceOf(a, b)) {
            case "Jugador":
            	Jugador s3 = (Jugador) a;
            	Jugador s4 = (Jugador) b;
                return s3.getMonedas()<s4.getMonedas();
        }
        return false; //en cualquier otro caso
    }

    private  boolean lees(Object a, Object b) {
        switch (instanceOf(a, b)) {
            case "Jugador":
            	Jugador x = (Jugador) a;
            	Jugador y = (Jugador) b;
                return x.getMonedas() > y.getMonedas();
        }
        return false;
    }
    
    public String printList(){
    	String exit="";
        if (!isEmpty()) {
        	
            NodeList aux = this.head;
            
            while(aux != null){
            	exit+=(aux.getElement()+ "\n");
                aux = aux.getNext();
            }
        }else
        	System.out.println("Lista vacia");
    
    return exit;
    }
    

}
