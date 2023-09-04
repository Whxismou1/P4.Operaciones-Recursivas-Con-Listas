package ule.ed.recursivelist;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.*;


public class LinkedEDListTest {
	private LinkedEDList<String> lista;
	
	@Before
	public void test() {
		 lista= new LinkedEDList<String>();
	}

	@Test
	public void testSize() {
		assertEquals(0,lista.size());
		lista.addLast("A");
		Assert.assertEquals("(A )", lista.toString());
		assertEquals(1,lista.size());
		lista.addLast("B");
		assertEquals(2,lista.size());
		lista.addLast("B");
		lista.addLast("C");
		lista.addLast("D");
		lista.addLast("E");
		Assert.assertEquals("(A B B C D E )", lista.toString());
		assertEquals(6, lista.size());
		
	}
	
	@Test
	public void test_AddLast() {
		lista.addLast("2");
		Assert.assertFalse(lista.isEmpty());
		Assert.assertEquals("(2 )", lista.toString());
		lista.addLast("3");
		Assert.assertEquals("(2 3 )", lista.toString());
		lista.addLast("7");
		Assert.assertEquals("(2 3 7 )", lista.toString());
	}
	
	@Test(expected=NoSuchElementException.class)
	public void test_RemoveLastElem_Vacia() throws EmptyCollectionException{
		lista.removeLastElem("A");
	}

	@Test(expected=NullPointerException.class)
	public void test_addLast_ElementoNulo() {
			lista.addLast(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void addPosException() {
		lista.addPos("A", 0);
	}
	
	@Test
	public void linkedtestAddPos_Varios() {
		lista.addPos("2",1);
		Assert.assertFalse(lista.isEmpty());
		Assert.assertEquals("(2 )", lista.toString());
		lista.addPos("3",1);
		Assert.assertEquals("(3 2 )", lista.toString());
		lista.addPos("7",2);
		Assert.assertEquals("(3 7 2 )", lista.toString());
		lista.addPos("10",3);
		Assert.assertEquals("(3 7 10 2 )", lista.toString());
		lista.addPos("5",5);
		Assert.assertEquals("(3 7 10 2 5 )", lista.toString());
		
	}
	
	
	@Test
	public void testReverse() {
		Assert.assertEquals("()", lista.reverse().toString());
		lista.addLast("A");
		lista.addLast("B");
		lista.addLast("C");
		lista.addLast("D");
		lista.addLast("E");
		Assert.assertEquals("(A B C D E )", lista.toString());
		Assert.assertEquals("(E D C B A )", lista.reverse().toString());
		
	}
	
	@Test
	public void testLengthEqualsN() {
		lista.addLast("A");
		lista.addLast("B");
		lista.addLast("C");
		lista.addLast("D");
		lista.addLast("E");
		Assert.assertEquals("(A B C D E )", lista.toString());
		
		assertFalse(lista.lengthEqualsTo(0));
		assertFalse(lista.lengthEqualsTo(2));
		assertFalse(lista.lengthEqualsTo(8));
		assertTrue(lista.lengthEqualsTo(5));
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetElemPosException() {
		lista.getElemPos(1);
	}
	
	@Test
	public void testGetElemPos() {
		lista.addLast("Z");
		lista.addLast("C");
		lista.addLast("A");
		lista.addLast("D");
		lista.addLast("Z");
		assertEquals("(Z C A D Z )", lista.toString());
		assertEquals("C", lista.getElemPos(2));	
		assertEquals("Z", lista.getElemPos(5));	
		assertEquals("Z", lista.getElemPos(1));	
	}
	
	@Test
	public void testRemoveLast() throws EmptyCollectionException {
		lista.addLast("Z");
		lista.addLast("C");
		lista.addLast("A");
		lista.addLast("D");
		lista.addLast("Z");
		assertEquals("(Z C A D Z )", lista.toString());
		assertEquals("Z", lista.removelast());
		assertEquals("(Z C A D )", lista.toString());
		
		assertEquals("D", lista.removelast());
		assertEquals("(Z C A )", lista.toString());

		assertEquals("A", lista.removelast());
		assertEquals("(Z C )", lista.toString());

		assertEquals("C", lista.removelast());
		assertEquals("(Z )", lista.toString());

		assertEquals("Z", lista.removelast());
		assertEquals("()", lista.toString());
	}
	
	
	@Test(expected = NoSuchElementException.class)
	public void testGetPosFirstException() {	
		lista.addLast("Z");
		lista.addLast("C");
		lista.addLast("A");
		lista.addLast("D");
		lista.addLast("W");
		assertEquals("(Z C A D W )", lista.toString());
		lista.getPosFirst("X");
	}
	
	@Test
	public void testGetPosFirst() {		
		lista.addLast("Z");
		lista.addLast("C");
		lista.addLast("A");
		lista.addLast("D");
		lista.addLast("W");
		assertEquals("(Z C A D W )", lista.toString());
		assertEquals(3, lista.getPosFirst("A"));
		assertEquals(1, lista.getPosFirst("Z"));
		assertEquals(5, lista.getPosFirst("W"));
	}

	
	@Test(expected = NoSuchElementException.class)
	public void testGetPosLastException() {
		lista.addLast("Z");
		lista.addLast("X");
		lista.addLast("Z");
		lista.addLast("A");
		lista.addLast("B");
		Assert.assertEquals("(Z X Z A B )", lista.toString());
		lista.getPosLast("Y");
	}
	
	@Test
	public void testGetPosLast() {
		lista.addLast("Z");
		lista.addLast("X");
		lista.addLast("Z");
		lista.addLast("A");
		lista.addLast("B");
		Assert.assertEquals("(Z X Z A B )", lista.toString());
		
		assertEquals(3, lista.getPosLast("Z"));
		assertEquals(5, lista.getPosLast("B"));
		assertEquals(2, lista.getPosLast("X"));
		
	}
	
	@Test
	public void testRemoveOdd() throws EmptyCollectionException {
		lista.addLast("A");
		lista.addLast("B");
		lista.addLast("C");
		lista.addLast("D");
		lista.addLast("E");
		Assert.assertEquals("(A B C D E )", lista.toString());
		assertEquals(3, lista.removeOddElements());
		Assert.assertEquals("(B D )", lista.toString());
		
		assertEquals(1, lista.removeOddElements());
		Assert.assertEquals("(D )", lista.toString());
		assertEquals(1, lista.removeOddElements());
		Assert.assertEquals("()", lista.toString());
		
	}

	@Test(expected = IllegalArgumentException.class)
	public void testToSringExceptFromUntilReverseWithRecException() {
		lista.addLast("A");
		lista.addLast("B");
		lista.addLast("C");
		lista.addLast("D");
		lista.addLast("E");
		Assert.assertEquals("(A B C D E )", lista.toString());
		lista.toSringExceptFromUntilReverse(0, 2);
	}
	
	@Test
	public void testToSringExceptFromUntilReverseWithRec() {
		lista.addLast("A");
		lista.addLast("B");
		lista.addLast("C");
		lista.addLast("D");
		lista.addLast("E");
		Assert.assertEquals("(A B C D E )", lista.toString());
		
		assertEquals("(E D )", lista.toSringExceptFromUntilReverse(3, 1));
		assertEquals("(E A )", lista.toSringExceptFromUntilReverse(4, 2));
		assertEquals("(A )", lista.toSringExceptFromUntilReverse(5, 2));
		
	}
	
	@Test
	public void testRemoveConsec() throws EmptyCollectionException {
		lista.addLast("A");
		lista.addLast("A");
		lista.addLast("B");
		lista.addLast("A");
		lista.addLast("A");
		lista.addLast("A");
		lista.addLast("B");
		assertEquals("(A A B A A A B )", lista.toString());
		Assert.assertEquals(3, lista.removeConsecDuplicates());
		assertEquals("(A B A B )", lista.toString());
	}
	
	@Test(expected= NoSuchElementException.class)
	public void testRemoveLastElemException() throws EmptyCollectionException {
		lista.addLast("Z");
		lista.addLast("C");
		lista.addLast("A");
		lista.addLast("D");
		lista.addLast("W");
		assertEquals("(Z C A D W )", lista.toString());
		lista.removeLastElem("X");
	}
	
	
	@Test
	public void testRemoveLastElem() throws EmptyCollectionException {
		lista.addLast("Z");
		lista.addLast("C");
		lista.addLast("A");
		lista.addLast("D");
		lista.addLast("W");
		assertEquals("(Z C A D W )", lista.toString());
		assertEquals("W", lista.removeLastElem("W"));
		assertEquals("(Z C A D )", lista.toString());
		lista.addLast("W");
		lista.addPos("A", 1);
		assertEquals("(A Z C A D W )", lista.toString());
		
		assertEquals("A", lista.removeLastElem("A"));
		assertEquals("(A Z C D W )", lista.toString());
		
		assertEquals("A", lista.removeLastElem("A"));
		assertEquals("(Z C D W )", lista.toString());
		
	}
	
	@Test(expected = EmptyCollectionException.class)
	public void testEmpty() throws EmptyCollectionException {
		lista.checkIsEmpty();
	}
	
	
}
