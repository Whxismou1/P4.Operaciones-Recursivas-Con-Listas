package ule.ed.recursivelist;

import java.util.NoSuchElementException;

public class LinkedEDList<T> implements EDList<T> {

	// referencia al primer de la lista
	private Node<T> front;

	private class Node<T> {

		Node(T element) {
			this.elem = element;
			this.next = null;
		}

		T elem;

		Node<T> next;
	}

	// isEmpty
	@Override
	public boolean isEmpty() {
		// TODO
		return front == null;
	}

	// size
	@Override
	public int size() {
		int size = sizeWithRec(front);

		return size;
	}

	private int sizeWithRec(Node<T> node) {
		if (node == null) {
			return 0;
		} else {
			return 1 + sizeWithRec(node.next);
		}
	}

	// addLast
	@Override
	public void addLast(T elem) {
		// TODO RECURSIVAMENTE
		this.checkNull(elem);
		Node<T> newNode = new Node<T>(elem);
		if (front == null) {
			front = newNode;
		} else {
			addLastWithRec(front, newNode);
		}
	}

	private void addLastWithRec(Node<T> node, Node<T> newNode) {
		// TODO Auto-generated method stub
		if (node.next == null) {
			node.next = newNode;
		} else {
			addLastWithRec(node.next, newNode);
		}
	}

	// addPos
		@Override
		public void addPos(T elem, int position) {
			// TODO RECURSIVAMENTE
			this.checkNull(elem);
			if(position <= 0) {
				throw new IllegalArgumentException();
			} else if(position == 1) {
				Node<T> node = new Node<T>(elem); 
				node.next = front;
				front = node;
			}else if(position > size()) {
				this.addLast(elem);
			}else {
				int actualPos = 1;
				Node<T>newNode = new Node<T>(elem);
				addPosWithRec(front, newNode, position, actualPos);
			}
		}
	
		private void addPosWithRec(Node<T> node, Node<T> newNode,  int position, int actualPos) {
			// TODO Auto-generated method stub
			if(node != null){
				if(actualPos == position-1) {
					newNode.next = node.next;
					node.next = newNode;
				}else {
					addPosWithRec(node.next, newNode, position, actualPos+1);
				}
			}
		}

	// getElemPos
	@Override
	public T getElemPos(int position) {
		// TODO RECURSIVAMENTE
		if(position <1 || position > size()) {
			throw new IllegalArgumentException();
		}
		
		int actualPos = 1;
		
		T elem = null;
		return getElemPosWithRec(front, elem, position, actualPos);
	}

	private T getElemPosWithRec(Node<T> node, T elem, int position, int actualPos) {
		// TODO Auto-generated method stub
		if(node != null) {
			if(position == actualPos) {
				elem = node.elem;
			}else {
				elem = getElemPosWithRec(node.next, elem, position, actualPos+1);
			}
		}
		return elem;
	
	}

	// getPosFirst
	@Override
	public int getPosFirst(T elem) {
		// TODO RECURSIVAMENTE
		this.checkNull(elem);
		int pos = 1;
		return getPosFirstWithRec(front, elem, pos);
	}

	private int getPosFirstWithRec(Node<T> node, T elem, int pos) {
		// TODO Auto-generated method stub
		if(node == null) {
			throw new NoSuchElementException("ERROR: El elemento no está en la lista");
		}else {
			if(node.elem.equals(elem)) {
				return pos;
			}
			return getPosFirstWithRec(node.next, elem, pos+1);
		}
	}

	// getPosLast
	@Override
	public int getPosLast(T elem) {
		// TODO RECURSIVAMENTE
		this.checkNull(elem);
		int actualPos = 1;
		
		int elemPos = getPosLastWithRec(front, elem, actualPos);
		if(elemPos == 0) {
			throw new NoSuchElementException("ERROR: El elemento no está en la lista");
		}else {
			return elemPos;
		}
	}

	private int getPosLastWithRec(Node<T> node, T elem, int pos) {
		// TODO Auto-generated method stub
		if(node == null) {
			return 0;
		}else if(node.elem.equals(elem) && (getPosLastWithRec(node.next, elem, pos+1)==0)) {
			return pos;
		}else {
			return getPosLastWithRec(node.next, elem, pos+1);
		}
	}

	// remoeLast
	@Override
	public T removelast() throws EmptyCollectionException {
		// TODO RECURSIVAMENTE
		this.checkIsEmpty();
		T elem = null;
		if(front.next == null) {
			elem = front.elem;
			front = null;
			return elem;
		}else {
			return removelastWithRec(front, elem);
		}
	}

	private T removelastWithRec(Node<T> node, T elem) {
		// TODO Auto-generated method stub
		if(node != null) {
			if(node.next.next == null) {
				elem = node.next.elem;
				node.next = null;
			}else {
				return removelastWithRec(node.next, elem);
			}
		}
		return elem;
	}

	// removeLastElem
	@Override
	public T removeLastElem(T elem) throws NoSuchElementException {
		// TODO RECURSIVAMENTE
		this.checkNull(elem);
        if(isEmpty()){
            throw new NoSuchElementException();
        }
		T rem = removeLastElemWithRec(front, elem);
		
		if(rem == null) {
			if(front.elem.equals(elem)) {
				rem = front.elem;
				front = front.next;
				return rem;
			}else {
				throw new NoSuchElementException("ERROR: El elemento no está en la lista!");
			}
		}else {
			return rem;
		}

	}
	

	private T removeLastElemWithRec(Node<T> node, T elem) {
		// TODO Auto-generated method stub
		T resElem = null;
		if(node == null) {
			return null;
		}else {
			resElem = removeLastElemWithRec(node.next, elem);
			if(resElem == null) {
				if(node.next != null && node.next.elem.equals(elem)) {
					resElem = node.next.elem;
					node.next = node.next.next;
					return resElem;
				}else {
					return resElem;
				}
			}else {
				return resElem;
			}
		
		}

	}

	// reverse
	@Override
	public EDList<T> reverse() {
		// TODO RECURSIVAMENTE
		LinkedEDList<T> reverseList = new LinkedEDList<>();
		if(front == null) {
			return reverseList;
		}else {
			return reverseWithRec(reverseList,front);			
		}
	}

	private EDList<T> reverseWithRec(LinkedEDList<T> reverseList, Node<T> node) {
		// TODO Auto-generated method stub
		if(node != null) {
			reverseWithRec(reverseList, node.next);
			reverseList.addLast(node.elem);
		}			
			
		return reverseList;
		
	}

	// removeOdd
	@Override
	public int removeOddElements() {
		// TODO RECURSIVAMENTE
		try {
			this.checkIsEmpty();
		} catch (EmptyCollectionException e) {
			// TODO Auto-generated catch block
		}
		int nElem = 0;
		return removeOddElementsWithRec(front, nElem);			
		
	}

	private int removeOddElementsWithRec(Node<T> node, int nElem) {
		// TODO Auto-generated method stub
		if(node == null) {
			return nElem;
		}
		else if(nElem == 0) {
			front = node.next;
			return removeOddElementsWithRec(front, nElem+1);
		}else if(node.next == null) {
			node.next = node.next;
			return removeOddElementsWithRec(node.next, nElem);
		}else {
			node.next = node.next.next;
			return removeOddElementsWithRec(node.next, nElem+1);
		}
	}

	// removeConsec
	@Override
	public int removeConsecDuplicates()  {
		// TODO RECURSIVAMENTE
		try {
			this.checkIsEmpty();
		} catch (EmptyCollectionException e) {
		}
		int nElem = 0;
		return removeConsecDuplicatesWithRec(front, nElem);
	}

	private int removeConsecDuplicatesWithRec(Node<T> node, int numElem) {
		// TODO Auto-generated method stub
		if(node == null) {
			return numElem;			
		}else {
			if(node.next != null && node.elem.equals(node.next.elem)) {
				node.next = node.next.next;
				return removeConsecDuplicatesWithRec(node, numElem+1);
			}else {
				return removeConsecDuplicatesWithRec(node.next, numElem);
			}
		}
	}

	// toString>FromUntil
	@Override
	public String toSringExceptFromUntilReverse(int from, int until) {
		// TODO RECURSIVAMENTE
		if(from <= 0 || until <= 0 || from < until) {
			throw new IllegalArgumentException();
		}
		
		return "("+ toSringExceptFromUntilReverseWithRec(front, from, until, 1) + ")";
	}

	private String toSringExceptFromUntilReverseWithRec(Node<T> node, int from, int until, int posElem) {
		// TODO Auto-generated method stub
		String lis = "";
		if(node == null) {
			return "";
		}else {
			lis = toSringExceptFromUntilReverseWithRec(node.next, from, until, posElem+1);
			if(posElem > from || posElem < until) {
				lis += node.elem.toString() + " ";
			}
		}

		return lis;
	}

	// LengthEqualsTo
	@Override
	public boolean lengthEqualsTo(int n) {
		// TODO RECURSIVAMENTE
		int aux = 0;
		return lengthequalsToWithRec(front, n, aux);
	}

	private boolean lengthequalsToWithRec(Node<T> node, int n, int aux) {
		// TODO Auto-generated method stub
		if(aux == n) {
			return node == null;
		}else if(node == null) {
			return false;
		}else{
			return lengthequalsToWithRec(node.next, n, aux+1);
		}
	}

	// ToString
	@Override
	public String toString() {
		String sc = toStringWithRec(front);
		return "(" + sc + ")";
	}

	private String toStringWithRec(Node<T> node) {
		String sc = "";
		if (node == null) {
			return sc;
		} else {
			sc = sc + node.elem.toString() + " " + toStringWithRec(node.next);
		}

		return sc;
	}

	private void checkNull(T elem) {
		if (elem == null) {
			throw new NullPointerException("ERROR: El elemento no puede ser nulo");
		}
	}

	public void checkIsEmpty() throws EmptyCollectionException {
		if (isEmpty()) {
			throw new EmptyCollectionException("ERROR: La lista está vacía");
		}
	}
	
	
	
}