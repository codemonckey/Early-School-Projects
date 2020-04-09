package campingsystem;

import static org.junit.Assert.*;

import org.junit.Test;

public class MyLinkedListTest {

	//test normal linked list
	@Test
	public void testMyLinkedList() {
		MyLinkedList l = new MyLinkedList();
		l.insertBefore(0, "lol");

		String q = l.removeTop();
		assertEquals("lol", q);
	}


	//test Size
	@Test
	public void testSize1() {
		MyLinkedList l = new MyLinkedList();
		assertEquals(l.size(), 0);
	}

	//test Size
	@Test
	public void testSize2() {
		MyLinkedList l = new MyLinkedList();
		l.insertAfter(0, "yuh");
		assertEquals(l.size(), 1);
	}

	//test remove function normally
	@Test
	public void testRemoveTop1() {
		MyLinkedList l = new MyLinkedList();

		l.insertAfter(0, "a");
		l.insertAfter(0, "b");
		l.insertBefore(0, "c");
		String q = l.removeTop();
		assertEquals("c", q);
	}

	//test remove throw error
	@Test (expected = RuntimeException.class)
	public void testRemoveTop2() {
		MyLinkedList l = new MyLinkedList();
		l.removeTop();
	}

	//test delete for deleting one item
	@Test
	public void testDelAt1() {
		MyLinkedList l = new MyLinkedList();

		l.insertBefore(0, "a");
		l.insertAfter(0, "b");
		l.insertAfter(0, "c");
		l.delAt(1);
		assertEquals(2, l.size());
	}

	//test to delete an empty list
	@Test (expected = IllegalArgumentException.class)
	public void testDelAt2() {
		MyLinkedList l = new MyLinkedList();
		l.delAt(0);
	}

	//test to delete an empty list
	@Test (expected = IllegalArgumentException.class)
	public void testDelAt3() {
		MyLinkedList l = new MyLinkedList();
		l.delAt(-4);
	}
	//delete something out of the index
	@Test (expected = IllegalArgumentException.class)
	public void testDelAt4() {
		MyLinkedList l = new MyLinkedList();
		l.insertBefore(0,"a");
		l.delAt(1);
	}

	//delete all items at index 0
	@Test 
	public void testDelAt5() {
		MyLinkedList l = new MyLinkedList();

		l.insertBefore(0, "a");
		l.insertBefore(0, "b");
		l.insertBefore(0, "c");
		l.delAt(0);
		l.delAt(0);
		l.delAt(0);
		assertEquals(0, l.size());
	}

	//delete all item at max index's
	public void testDelAt6() {
		MyLinkedList l = new MyLinkedList();

		l.insertBefore(0, "a");
		l.insertBefore(0, "b");
		l.insertBefore(0, "c");
		l.insertBefore(0, "c");
		l.insertBefore(0, "c");
		l.delAt(5);
		l.delAt(3);
		l.delAt(2);
		assertEquals(2, l.size());
	}

	//test inserting out of bounds index
	@Test (expected = IllegalArgumentException.class)
	public void testInsertBefore1() {
		MyLinkedList l = new MyLinkedList();

		l.insertBefore(1, "a");

	}

	//test inserting a negative index
	@Test (expected = IllegalArgumentException.class)
	public void testInsertBefore2() {
		MyLinkedList l = new MyLinkedList();

		l.insertBefore(-4, "a");

	}

	//test inserting two items for size at base index
	@Test 
	public void testInsertBefore3() {
		MyLinkedList l = new MyLinkedList();

		l.insertBefore(0, "a");
		l.insertBefore(1, "b");
		l.insertBefore(2, "c");

		assertEquals(3, l.size());


	}

	//inserting after out of bounds
	@Test (expected = IllegalArgumentException.class)
	public void testInsertAfter1() {
		MyLinkedList l = new MyLinkedList();

		l.insertAfter(1, "a");

	}

	//inserting after along index
	@Test 
	public void testInsertAfter2() {
		MyLinkedList l = new MyLinkedList();

		l.insertAfter(0, "a");
		l.insertAfter(0, "b");
		l.insertAfter(1, "c");
		l.insertAfter(2, "c");
		assertEquals(4, l.size());


	}

	//inserting after negative index
	@Test (expected = IllegalArgumentException.class)
	public void testInsertAfter3() {
		MyLinkedList l = new MyLinkedList();

		l.insertAfter(-34, "a");

	}

}
