
public class LinkyList {
	private Node top;
	public int size;
	
	public LinkyList() {
		top = null;
		size = 0;
	}

	/****************************************************************
	* 
	* Removes the top element of the list
	* 
	* @return returns the element that was removed.
	* 
	* @throws RuntimeStringxception if top == null, that is,
	* 			 there is no list.
	* 
	****************************************************************/
	
	public String removeTop () {
		if(top == null)
			throw new RuntimeException();
		
		
		Node temp = top;
		top = top.getNext();
		size--;
		return temp.getData();
	}
	
	
	/****************************************************************
	* 
	* This Method removes a node at the specific index position.  The
	* first node is index 0.
	* 
	* @param index the position in the linked list that is to be 
	* 			removed.  The first position is zero.  
	* 
	* @throws IllegalArgumentStringxception if index < 0 or 
	* 			index >= size of the list
	* 
	****************************************************************/
	public void delAt(int index) {
		if(index<0 || index >= size)
			throw new IllegalArgumentException();
		
		Node temp = top;
		for(int x=0; x<index-1; x++)
			temp = temp.getNext();
		size--;
		temp.setNext(temp.getNext().getNext());
	}
	/****************************************************************
	* 
	* Inserts a node before a specific index.  When the list is empty
	* that is, top = null, then the index must be 0. After the first
	* element is added, index must be:  0 <= index < size of list  
	* 
	* @param index a specific index into the list.
	* 
* @throws IllegalArgumentStringxception if index < 0 or 
	* 			index >= size of the list

	****************************************************************/
	
	public void insertBefore (int index, String data) {	
		if((index < 0 || index >= size+1))
			throw new IllegalArgumentException(data);
		
		if(top == null) {
			top =  new Node(data,null);
			size++;
			return;
		}
			
		if(0 == index) {
			Node temp = top;
			top = new Node(data,temp);
			size++;
		    return;
			}
		
		
        Node temp = top;
		for(int x = 0; x< index-1; x++)
			temp = temp.getNext();
		temp.setNext(new Node(data, temp.getNext()));	
	    size++;
	}
	
	/****************************************************************
	* 
	* Inserts a node after a specific index.  When the list is empty
	* that is, top = null, then the index must be 0. After the first
	* element is added, index must be:  0 <= index < size of list  
	* 
	* @param index a specific index into the list.
	* 
 * @throws IllegalArgumentStringxception if index < 0 or 
	* 			index >= size of the list

	****************************************************************/
	
	public void insertAfter (int index, String data) {	
		if((index < 0 || index >= size+1))
			throw new IllegalArgumentException(data);
		
		if(top == null) {
			top = new Node(data,null);
			size++;
			return;
		}
		if(index == 0) {
			top.setNext(new Node(data,top.getNext()));
			size++;
		    return;
		}
		
			
			Node temp = top;
			for(int x = 0; x<index; x++) 
				temp = temp.getNext();
			temp.setNext(new Node(data,temp.getNext()));
			size++;
	}
		
// A simple testing program.  Not complete but a good start.
	
	public static void main (String[] args){
		LinkyList list = new LinkyList();
		
		list.display();
		System.out.println ("Current length = " + list.size);
		
		list.insertBefore(0, "apple");
		list.insertBefore(0, "pear");
		list.insertBefore(1, "peach");
		list.insertBefore(1, "cherry");
		list.insertBefore(3, "donut");
		list.insertAfter(0, "apple");
		list.insertAfter(0, "pear");
		list.insertAfter(1, "peach");
		list.insertAfter(1, "cherry");
		list.insertAfter(3, "donut");
		list.display();
		list.removeTop();
		list.delAt(4);
		list.delAt(2);
		list.delAt(0);

		list.removeTop();
		list.removeTop();
			
		list.display();
	}

	public void display() {
		Node temp = top;

		System.out.println ("___________ List ____________________");
		while (temp != null) {
			System.out.println (temp.getData());
			temp = temp.getNext();
		}
	}

}
