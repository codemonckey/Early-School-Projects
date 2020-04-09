package campingsystem;

public class MyLinkedList {
	private Node top;
	public int size;

	/***********************************************************************
	 * Basic Constructer
	 **********************************************************************/
	public  MyLinkedList() {
		top = null;
		size = 0;
	}

	/***********************************************************************
	 * This method gets the size of the list
	 * @return the size of the list
	 **********************************************************************/
	public int size() {
		if(top == null)
			return 0;

		Node temp = top;
		int count=1;
		while(temp.getNext() != null) {
			temp = temp.getNext();
			count++;
		}
		return count;
	}
	/********************************************************************
	 * Removes the top element of the list
	 * 
	 * @return returns the element that was removed.
	 * 
	 * @throws RuntimeStringxception if top == null, that is,
	 * 			 there is no list.
	 ******************************************************************/

	public String removeTop () {
		if(top == null)
			throw new RuntimeException();


		Node temp = top;
		top = top.getNext();
		size--;
		return temp.getData();
	}


	/*******************************************************************
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
	 ******************************************************************/
	public void delAt(int index) {
		if(index<0 || index >= size)
			throw new IllegalArgumentException();
		if(size == 1) {
			top = null;
			size--;
			return;
		}
		Node temp = top;
		for(int x=0; x<index-1; x++)
			temp = temp.getNext();
		temp.setNext(temp.getNext().getNext());
		size--;
	}
	/*******************************************************************
	 * 
	 * Inserts a node before a specific index.  When the list is empty
	 * that is, top = null, then the index must be 0. After the first
	 * element is added, index must be:  0 <= index < size of list  
	 * 
	 * @param index a specific index into the list.
	 * 
	 * @throws IllegalArgumentStringxception if index < 0 or 
	 * 			index >= size of the list

	 ******************************************************************/

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

	/******************************************************************
	 * 
	 * Inserts a node after a specific index.  When the list is empty
	 * that is, top = null, then the index must be 0. After the first
	 * element is added, index must be:  0 <= index < size of list  
	 * 
	 * @param index a specific index into the list.
	 * 
	 * @throws IllegalArgumentStringxception if index < 0 or 
	 * 			index >= size of the list

	 *****************************************************************/

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
}
