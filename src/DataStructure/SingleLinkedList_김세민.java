package DataStructure;

import java.util.NoSuchElementException;

/**
 * 단일 연결리스트 구현
 *
 * @author semin.kim
 */

public class SingleLinkedList_김세민 {

	static class Node<E> {
		E data;
		Node<E> next;
		
		// 생성자
		public Node(E data, Node<E> next) {
			this.data = data;
			this.next = next;
		}
		
		public Node(E data) {
			this.data = data;
			this.next = null;
		}

		public E getData() {
			return data;
		}

		public void setData(E data) {
			this.data = data;
		}

		public Node<E> getNext() {
			return next;
		}

		public void setNext(Node<E> next) {
			this.next = next;
		}

		@Override
		public String toString() {
			return "Node [data=" + data + ", next=" + next + "]";
		}
		
		
	}
	
	static class LinkedList<E> {
		
		private Node<E> head;
		
		private Node<E> tail;
		
		private int size = 0;
		
		public void addFirst(E data) {
			Node<E> newNode = new Node<E>(data);
			
			newNode.next = head;
			head = newNode;
			size++;
			
			if(head == null) {
				tail = head;
			}
		}
		
		public void addLast(E data) {
			Node<E> newNode = new Node<E>(data);
			
			if(size == 0) {
				addFirst(data);
				return;
			}
			
			tail.next = newNode;
			tail = newNode;
			size++;
		}
		
		public void add(int index, E data) {
			if(index > size || index < 0) {
				throw new IndexOutOfBoundsException();
			}
			
			if(index == 0) {
				addFirst(data);
				return;
			}
			if(index == size) {
				addLast(data);
				return;
			}
			
			Node<E> prevNode = search(index - 1);
			Node<E> nextNode = prevNode.next;
			Node<E> newNode = new Node<E>(data);
			
			prevNode.next = newNode;
			newNode.next = nextNode;
			size++;
		}
		
		public E removeFirst() {
			Node<E> headNode = head;
			
			if(headNode == null) {
				throw new NoSuchElementException();
			}
			
			E element = headNode.data;
			
			Node<E> nextNode = head.next;
			
			head.data = null;
			head.next = null;
			
			head = nextNode;
			size--;
			
			if(size == 0) {
				tail = null;
			}
			return element;
		}
		
		public E remove(int index) {
			if(index == 0) {
				return removeFirst();
			}
			
			if(index >= size || index < 0) {
				throw new IndexOutOfBoundsException();
			}
			
			Node<E> prevNode = search(index - 1);
			Node<E> removeNode = prevNode.next;
			Node<E> nextNode = removeNode.next;
			
			E element = removeNode.data;
			
			prevNode.next = nextNode;
			
			if(prevNode.next == null) {
				tail = prevNode;	
			}
			
			removeNode.next = null;
			removeNode.data = null;
			size--;
			
			return element;
		}
		
		public boolean remove(Object object) {
			Node<E> prevNode = head;
			boolean hasValue = false;
			Node<E> x = head;
			
			for(; x != null; x = x.next) {
				if(object.equals(x.data)) {
					hasValue = true;
					break;
				}
				prevNode = x;
			}
			
			if(x == null) {
				return false;
			}
			
			if(x.equals(head)) {
				removeFirst();
				return true;
			} else {
				prevNode.next = x.next;
				
				if(prevNode.next == null) {
					tail = prevNode;
				}
				x.data = null;
				x.next = null;
				size--;
				return true;
			}
		}
		
		public Node<E> search(int index){
			if(index < 0 || index >= size) {
				throw new IndexOutOfBoundsException();
			}
			
			Node<E> x = head;
			
			for(int i = 0; i < index; i++) {
				x = x.next;
			}
			return x;
		}
		
		public E get(int index) {
			return search(index).data;
		}
		
		public void set(int index, E data) {
			Node<E> replaceNode = search(index);
			replaceNode.data = data;
		}
		
		public int indexOf(Object object) {
			int index = 0;
			
			for(Node<E> x = head; x != null; x = x.next) {
				if(object.equals(x.data)) {
					return index;
				}
				index++;
			}
			
			return -1;
		}
		
		public boolean contains(Object item) {
			return indexOf(item) >= 0;
		}
		
		public int size() {
			return size;
		}
		
		public boolean isEmpty() {
			return size == 0;
		}
		
		public void clear() {
			for(Node<E> x = head; x != null;) {
				Node<E> nextNode = x.next;	
				x.data = null;
				x.next = null;
				x = nextNode;
			}
			head = tail = null;
			size = 0;
		}
	}
	
	
}
