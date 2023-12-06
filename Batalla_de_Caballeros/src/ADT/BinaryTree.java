package ADT;

public class BinaryTree {

	private int size;
	private NodeTree root;
	
	//Constructor default
	public BinaryTree() {
		this.size = 0;
		this.root = null;
	}

	//Constructor sobrecargado
	public BinaryTree(NodeTree root) {
		this.size = 1;
		this.root = root;
	}
	
	public void insertLeft(Position p,Object o) {
		
		if(p.getNodo().getLeft()==null) {
			NodeTree nuevo = new NodeTree(o);
			nuevo.setParent(p.getNodo());
			p.getNodo().setLeft(nuevo);
			this.size++;
		}else
			System.out.println("La posicion ya tiene un nodo a la izquierda");
	}
	
	public void insertRight(Position p,Object o) {
		
		if(p.getNodo().getRight()==null) {
			NodeTree nuevo = new NodeTree(o);
			nuevo.setParent(p.getNodo());
			p.getNodo().setRight(nuevo);
			this.size++;
		}else
			System.out.println("La posicion ya tiene un nodo a la derecha");
	
	}
	
	public void remove(Position p) {
		if(p.getNodo().getLeft() == null || p.getNodo().getRight() == null) {
			NodeTree parent = p.getNodo().getParent();
			
			//Valida que hijo era la posicion si el hijo  izquierdo o el derecho
			if(parent.getLeft() == p.getNodo()) {
				parent.setLeft(null);
			}else if(parent.getRight() == p.getNodo())
				parent.setRight(null);
			
			
			p.getNodo().setParent(null);
			this.size--;
			System.out.println("Removido con exito");
			
		}else
			System.out.println("No se permite eliminar un nodo con 2 hijos");
	} 

	
	
	public boolean search1(Object o) {
	    boolean a = false;	
	    try {
				//root;
		
			if(o.equals(root.getLeft().getElement())) {
              a=true;
			}else if(o.equals(root.getRight().getElement())) {
				a=true;
			}else {
			a=false;
			}
	
		} catch (Exception e) {
			System.out.println("f");// TODO: handle exception
		}
		return a;
	}
	
	
	public Position search(Object o) {
		NodeTree aux = root;
			if (aux.getElement() != o) {    
				if(o.equals(aux.getLeft().getElement())) {
					aux= aux.getLeft();	           
				}else if(o.equals(aux.getRight().getElement())) {
					aux= aux.getRight();				
				}else 
				return null;
	        }
		Position nodoPadre = new Position(aux);
		return nodoPadre;
	}

	 public boolean contains(Object element)  {
	        if(isEmpty()){     
	        	
	        }
	        return contains(root, element);
	    }
	    
	    private boolean contains(NodeTree node, Object element){
	        if(node==null) return false;
	        else
	            if(node.getElement().equals(element)){
	                return true; 
	            }else
	                return contains(node.left, element)|| contains(node.right, element);
	    }
	
	public int size() {
		
		return this.size;
	}
	
	public boolean isEmpty() {
		return this.root == null;
	}
	
	
	public Position root() {
		Position raiz = new Position(this.root);
		return raiz;
	} 
	
	public Position parent(Position p) {
		NodeTree aux = p.getNodo().getParent();
		Position nodoPadre = new Position(aux);
		
		return nodoPadre;
	}
	
	public Position[] children(Position p) {
		
		Position[] arregloHijos = new Position[2];
		
		if(p.getNodo().getLeft()!=null&&p.getNodo().getRight()!=null) {
			Position hijoIzquierdo = new Position(p.getNodo().getLeft());
			Position hijoDerecho = new Position(p.getNodo().getRight());
			
			arregloHijos[0] = hijoIzquierdo;
			arregloHijos[1] = hijoDerecho;
		}else
			System.out.println("El nodo elegido no tiene algun hijo");
		
		return arregloHijos;
	}
	
	public boolean isInternal(Position p) {
		return p.getNodo().getLeft() != null || p.getNodo().getRight() != null;
	} 
	
	public boolean isExternal(Position p) {
		return p.getNodo().getLeft() == null && p.getNodo().getRight() == null;
	} 
	
	public boolean isRoot(Position p) {
		return p.getNodo() == this.root;
	}
	
	public Object replace(Position p, Object o) {
		
		Object salida = p.getNodo().getElement();
		p.getNodo().setElement(o);
		
		return salida;
	}
	
	
	private void inOrder(NodeTree raiz) {
        
		if (raiz != null) {    
        	inOrder(raiz.getLeft());
            System.out.print(raiz.getElement());
            inOrder(raiz.getRight());
        }
    }

    public void inOrder() {
    	System.out.print("El recorrido en entreorden del árbol es: ");
    	inOrder(this.root);
    }
 
    private NodeTree insert(NodeTree node, Object element){
        if(node==null){
        node = new NodeTree(element);
        }else
            if(node.right==null){
           node.right = insert(node.right, element);
            }else
                    if(node.getLeft()==null){
                        node.left = insert(node.left, element);
                }else{ 
                    int ramdom = 1 + (int) Math.floor(Math.random() * 99);
                    if(ramdom%2==0){
                        node.left = insert(node.left, element);
                    }else 
                        node.right = insert(node.right, element);
                }
        return node;
    }
    public void insert(Object element) {
        root = insert(root, element);
       
    }
    
    
    
    public void clear() {
        this.root = null;
    }
  
}
