package project1;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestStopWatch {

/**
 *
 * The following are simple random JUnit test cases... After talking with your 
 * instructor, create many, many more that shows that your code 
 * is functioning correctly.
 *
 */	
	
	//@Test
	public void testConstructor() {
		StopWatch s = new StopWatch (5,10,300);
		assertEquals(s.toString(),"5:10:300");
		
		s = new StopWatch("20:10:8");
		assertEquals(s.toString(),"20:10:008");
		
		s = new StopWatch("20:8");
		assertEquals(s.toString(),"0:20:008");
		
		s = new StopWatch("8");
		assertEquals(s.toString(),"0:00:008");

	}
	
	// There can only be one test here
	// no more lines of code after "new StopWatch("-2");"
	@Test (expected = IllegalArgumentException.class)
	public void testNegSingleInput() {
		new StopWatch("-2");
		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testNegDouble1Input() {
		new StopWatch("-59:-23");
		
	}
	
	// To the students of CIS163: is this a good test?
	@Test 
	public void testAlotInput() {
		for (int m = 0; m < 50; m++)
			for (int s = 0; s < 60; s++)
				for (int ms = 0; ms < 1000; ms++) {
					String st = m + ":" + s + ":" + ms;
					StopWatch d = new StopWatch(st);
					assertEquals(m, d.getMinutes());
					assertEquals(s, d.getSeconds());
					assertEquals(ms, d.getMilliseconds());					
				}
	}
	
	 
	@Test (expected = IllegalArgumentException.class)
	public void testNegDouble2aInput() {
		new StopWatch("2:-2");
		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testNegDouble2bInput() {
		new StopWatch("-2:7");
		
	}

	@Test (expected = IllegalArgumentException.class)
	public void testAlphaInput() {
		new StopWatch("a");

	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testNegDouble2cInput() {
		new StopWatch("-2:-7");
		
	}

	@Test
	public void testAddMethod () {
		StopWatch s1 = new StopWatch (5,59,300);
		s1.add(2000);
		assertEquals (s1.toString(),"6:01:300");
		
		s1 = new StopWatch (5,59,300);
		StopWatch s2 = new StopWatch (2,2,300);
		s1.add(s2);
		System.out.println (s1);
		assertEquals (s1.toString(),"8:01:600");
	
		for (int i = 0; i < 15000; i++)
			s1.inc();
		System.out.println (s1);
		assertEquals (s1.toString(),"8:16:600");
	}
	
	
	 @Test (expected = IllegalArgumentException.class)
	    public void testContuctor() {
		 new StopWatch("2,-3,-3");
		 
	    }

	@Test 
	public void testEqual () {
		StopWatch s1 = new StopWatch (5,59,300);
		StopWatch s2 = new StopWatch (6,01,200);
		StopWatch s3 = new StopWatch (5,50,200);
		StopWatch s4 = new StopWatch (5,59,300);

		assertFalse(s1.equals(s2));
		assertTrue (s1.equals(s4));
		
		assertTrue (s2.compareTo(s1) > 0);
		assertTrue (s3.compareTo(s1) < 0);
		assertTrue (s1.compareTo(s4) == 0);
	
	}
	@Test 
	public void testCompareTo () {
		StopWatch s1 = new StopWatch (5,59,300);
		StopWatch s2 = new StopWatch (6,01,200);
		StopWatch s3 = new StopWatch (5,50,200);
		StopWatch s4 = new StopWatch (5,59,300);

		assertFalse(s1.equals(s2));
		assertTrue (s1.equals(s4));
		
		assertTrue (s2.compareTo(s1) > 0);
		assertTrue (s3.compareTo(s1) < 0);
		assertTrue (s1.compareTo(s4) == 0);
	
	}
		
	@Test 
	public void testLoadSave () {
		StopWatch s1 = new StopWatch (5,59,300);
		StopWatch s2 = new StopWatch (5,59,300);

		s1.save("PIZZA");
		s1 = new StopWatch ();  // resets to zero

		s1.load("PIZZA");
		assertTrue (s1.equals(s2));
	}
		
	@Test 
	public void testMutate () {
		StopWatch s1 = new StopWatch (5,59,300);
		StopWatch s2 = new StopWatch (5,59,300);
		
		StopWatch.suspend(true);
		s1.add(1000);
		assertTrue (s1.equals(s2));	
		StopWatch.suspend(false);
		}
	
	//equals to testing true
	@Test 
	public void equalsTest() {
		StopWatch s1 = new StopWatch (5,59,300);
		StopWatch s2 = new StopWatch (5,59,300);
		
		assertEquals(s1, s2);
	}
	
	
	
	@Test
	public void testConstructorKK() {
		
		StopWatch s = new StopWatch("25:8");
		assertEquals(s.toString(),"0:25:008");

		s = new StopWatch(7);
		assertEquals(s.toString(),"0:00:007");

		s = new StopWatch(7,10);
		assertEquals(s.toString(),"0:07:010");
		
		s = new StopWatch(20,50,400);
		assertEquals(s.toString(),"20:50:400");
		
		s = new StopWatch("678");
		assertEquals(s.toString(),"0:00:678");
		
		s = new StopWatch("90000:59:999");
		assertEquals(s.toString(),"90000:59:999");

		s = new StopWatch("0:00:000");
		assertEquals(s.toString(),"0:00:000");
		
	}	


	@Test (expected = IllegalArgumentException.class)
	public void testSignInputKK() {
		new StopWatch("-:+");
		
	}

	@Test (expected = IllegalArgumentException.class)
	public void testAllDots() {
		new StopWatch(".................................");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test4SignInputKK() {
		new StopWatch("-:+:/:*");
		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test3SignInputKK() {
		new StopWatch("-:+:/");
		
	}

	@Test (expected = IllegalArgumentException.class)
	public void testAlotNegInputKK() {
		for (int m = 0; m > -100000; m--)
			for (int s = 0; s > -1000; s--)
				for (int ms = 0; ms > -60000; ms--) {
					String st = m + ":" + s + ":" + ms;
					StopWatch d = new StopWatch(st);
					assertEquals(m, d.getMinutes());
					assertEquals(s, d.getSeconds());
					assertEquals(ms, d.getMilliseconds());					
				}
	}

	
		@Test (expected = IllegalArgumentException.class)
	public void test3AlphaCharKK() {
		new StopWatch("a,v,e");
	}

	@Test (expected = IllegalArgumentException.class)
	public void testRandom() {
		new StopWatch("h8u49a008asnfa0p498auhs0gdsads");
	}

	@Test (expected = IllegalArgumentException.class)
	public void testNothingKK() {
		new StopWatch(" , , ");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test2AlphaCharKK() {
		new StopWatch("v,e");
	}

	@Test (expected = IllegalArgumentException.class)
	public void testNumDashKK() {
		new StopWatch("2-,1-");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test1Alpha2numberKK() {
		new StopWatch("0,1,e");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test2Neg1AlphaKK() {
		new StopWatch("-34534,-905943,e");
	}

	@Test (expected = IllegalArgumentException.class)
	public void test3WierdInputKK() {
		new StopWatch("â:+:™+:€");	
	}

	@Test (expected = IllegalArgumentException.class)
	public void testdecimals() {
		new StopWatch(".02:.25");
		
	}

	@Test (expected = IllegalArgumentException.class)
	public void test3WierdInput2KK() {
		new StopWatch("^:|:1");	
	}

	@Test (expected = IllegalArgumentException.class)
	public void test1LargeumKK() {
		new StopWatch("9000000000000000000000000000");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test1LargeNegNumKK() {
		new StopWatch("-80000000000000000000000000000000000000000000000000000");
	}

	public void testAddMethodkk () {

	StopWatch s3kk = new StopWatch (10,50,300);
	s3kk.add(10000);
	assertEquals (s3kk.toString(),"11:00:300");

	}

	//save and load a bunch
    @Test
    public void loadandSave1ID() {
    	StopWatch s1 = new StopWatch(12);
    	s1.save("lol");
    	s1.add(10);
    	s1.save("lol1");
    	s1.add(10);
    	s1.save("lol2");
    	s1.add(10);
    	s1.save("lol3");
    	s1.load("lol");
    	assertEquals(s1.getMilliseconds(), 12);
    	s1.load("lol1");
    	assertEquals(s1.getMilliseconds(), 22);
    	s1.load("lol2");
    	assertEquals(s1.getMilliseconds(), 32);
    	s1.load("lol3");
    	assertEquals(s1.getMilliseconds(), 42);
    	
    }

    @Test
	public void testCompareToNegE1MS() {
		StopWatch s1 = new StopWatch(0,10,0);
		StopWatch s2 = new StopWatch(1,0,0);
		
		assertEquals(s1.compareTo(s2),-1);
	}
	
	@Test
	public void testCompareToNegE2MS() {
		StopWatch s1 = new StopWatch(0,0,678);
		StopWatch s2 = new StopWatch(1,0,0);
		
		assertEquals(s1.compareTo(s2),-1);
	}
	
	@Test
	public void testCompareToNegE3MS() {
		StopWatch s1 = new StopWatch(0,0,678);
		StopWatch s2 = new StopWatch(0,12,0);
		
		assertEquals(s1.compareTo(s2),-1);
	}
	
	@Test
	public void testCompareToNegE4MS() {
		StopWatch s1 = new StopWatch(1,2,645);
		StopWatch s2 = new StopWatch(1,2,999);
		
		assertEquals(s1.compareTo(s2),-1);
	}
	
	@Test
	public void testCompareToNegE5MS() {
		StopWatch s1 = new StopWatch(0,17,236);
		StopWatch s2 = new StopWatch(0,17,638);
		
		assertEquals(s1.compareTo(s2),-1);
	}

	@Test
	public void testCompareToNegE6MS() {
		StopWatch s1 = new StopWatch(0,2,0);
		StopWatch s2 = new StopWatch(0,56,0);
		
		assertEquals(s1.compareTo(s2),-1);
	}
	
	@Test
	public void testCompareToEqE1MS() {
		StopWatch s1 = new StopWatch(555,46,345);
		StopWatch s2 = new StopWatch(555,46,345);
		
		assertEquals(s1.compareTo(s2),0);
	}
	
	@Test
	public void testCompareToEqE2MS() {
		StopWatch s1 = new StopWatch(26,345);
		StopWatch s2 = new StopWatch(26,345);
		
		assertEquals(s1.compareTo(s2),0);
	}
	
	@Test
	public void testCompareToEqE3MS() {
		StopWatch s1 = new StopWatch(746);
		StopWatch s2 = new StopWatch(746);
		
		assertEquals(s1.compareTo(s2),0);
	}
	
	@Test
	public void testCompareToEqE4MS() {
		StopWatch s1 = new StopWatch(34,0,746);
		StopWatch s2 = new StopWatch(34,0,746);
		
		assertEquals(s1.compareTo(s2),0);
	}
	
	@Test
	public void testCompareToEqE5MS() {
		StopWatch s1 = new StopWatch(69,0,0);
		StopWatch s2 = new StopWatch(69,0,0);
		
		assertEquals(s1.compareTo(s2),0);
	}
	
	@Test
	public void testCompareToEqE6MS() {
		StopWatch s1 = new StopWatch(689,57,0);
		StopWatch s2 = new StopWatch(689,57,0);
		
		assertEquals(s1.compareTo(s2),0);
	}
	
	@Test
	public void testCompareToEqE7MS() {
		StopWatch s1 = new StopWatch(9,0);
		StopWatch s2 = new StopWatch(9,0);
		
		assertEquals(s1.compareTo(s2),0);
	}
	
	@Test
	public void testCompareToPosE1MS() {
		StopWatch s1 = new StopWatch(0,0,670);
		StopWatch s2 = new StopWatch(0,0,140);
		
		assertEquals(s1.compareTo(s2),1);
	}
	
	@Test
	public void testCompareToPosE2MS() {
		StopWatch s1 = new StopWatch(0,14,140);
		StopWatch s2 = new StopWatch(0,7,140);
		
		assertEquals(s1.compareTo(s2),1);
	}
	
	@Test
	public void testCompareToPosE3MS() {
		StopWatch s1 = new StopWatch(20000,7,140);
		StopWatch s2 = new StopWatch(10000,7,140);
		
		assertEquals(s1.compareTo(s2),1);
	}
	
	@Test
	public void testCompareToPosE5MS() {
		StopWatch s1 = new StopWatch(14000,0,0);
		StopWatch s2 = new StopWatch(400,0,0);
		
		assertEquals(s1.compareTo(s2),1);
	}
	
	@Test
	public void testCompareToPosE6MS() {
		StopWatch s1 = new StopWatch(0,57,0);
		StopWatch s2 = new StopWatch(0,56,0);
		
		assertEquals(s1.compareTo(s2),1);
	}
	
	@Test
	public void testAddMSE1MS() {
		StopWatch s = new StopWatch(0);
		s.add(30);
		
		assertEquals(s.getMilliseconds(), 30);
	}
	
	@Test
	public void testAddMSE2MS() {
		StopWatch s = new StopWatch(0);
		s.add(1007);
		
		assertEquals(s.getMilliseconds(), 7);
		assertEquals(s.getSeconds(), 1);
	}
	
	@Test
	public void testAddMSE3MS() {
		StopWatch s = new StopWatch(0);
		s.add(62010);
		
		assertEquals(s.getMilliseconds(), 10);
		assertEquals(s.getSeconds(), 2);
		assertEquals(s.getMinutes(), 1);
	}
	
	@Test
	public void testAddMSE4MS() {
		StopWatch s = new StopWatch(0);
		s.add(62010);
		
		assertEquals(s.getMilliseconds(), 10);
		assertEquals(s.getSeconds(), 2);
		assertEquals(s.getMinutes(), 1);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testAddMSTestNegMS() {
		StopWatch s = new StopWatch(10);
		s.add(-10);
	}
	
	@Test 
	public void testAddSWE1MS() {
		StopWatch s1 = new StopWatch(10,10,100);
		StopWatch s2 = new StopWatch(11,21,10);
		
		s1.add(s2);
		
		assertEquals(s1.getMinutes(), 21);
		assertEquals(s1.getSeconds(), 31);
		assertEquals(s1.getMilliseconds(), 110);		
	}
	
	@Test 
	public void testAddSWE2MS() {
		StopWatch s1 = new StopWatch(10,10,100);
		StopWatch s2 = new StopWatch(0,21,10);
		
		s1.add(s2);
		
		assertEquals(s1.getMinutes(), 10);
		assertEquals(s1.getSeconds(), 31);
		assertEquals(s1.getMilliseconds(), 110);		
	}
	
	@Test 
	public void testAddSWE3MS() {
		StopWatch s1 = new StopWatch(11);
		StopWatch s2 = new StopWatch(999);
		
		s1.add(s2);

		assertEquals(s1.getSeconds(), 1);
		assertEquals(s1.getMilliseconds(), 10);		
	}
	
	@Test 
	public void testAddSWE4MS() {
		StopWatch s1 = new StopWatch(59,11);
		StopWatch s2 = new StopWatch(8,999);
		
		s1.add(s2);
		
		assertEquals(s1.getMinutes(), 1);
		assertEquals(s1.getSeconds(), 8);
		assertEquals(s1.getMilliseconds(), 10);		
	}
	
	@Test 
	public void testIncE1MS() {
		StopWatch s = new StopWatch(0);
		s.inc();
		
		assertEquals(s.getMilliseconds(),1);
	}
	
	@Test 
	public void testIncE2MS() {
		StopWatch s = new StopWatch(999);
		s.inc();
		
		assertEquals(s.getSeconds(),1);
		assertEquals(s.getMilliseconds(),0);
	}
	
	@Test 
	public void testIncE3MS() {
		StopWatch s = new StopWatch(59,999);
		s.inc();
		
		assertEquals(s.getMinutes(),1);
		assertEquals(s.getSeconds(),0);
		assertEquals(s.getMilliseconds(),0);;
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testSubMSE1MS() {
		StopWatch s = new StopWatch(9);
		s.sub(10);
	}
	
	@Test
	public void testSubMSE2MS() {
		StopWatch s = new StopWatch(10);
		s.sub(10);
		
		assertEquals(s.getMilliseconds(),0);
	}
	
	@Test
	public void testSubMSE3MS() {
		StopWatch s = new StopWatch(11);
		s.sub(10);
		
		assertEquals(s.getMilliseconds(),1);
	}
	
	@Test
	public void testSubMSE4MS() {
		StopWatch s = new StopWatch(1,0);
		s.sub(1000);
		
		assertEquals(s.getMilliseconds(),0);
		assertEquals(s.getSeconds(),0);
	}
	
	@Test
	public void testSubMSE5MS() {
		StopWatch s = new StopWatch(10,0,0);
		s.sub(60000);
		
		assertEquals(s.getMilliseconds(),0);
		assertEquals(s.getSeconds(),0);
		assertEquals(s.getMinutes(),9);
	}
	
	@Test
	public void testSubMSE6MS() {
		StopWatch s = new StopWatch(10,10,10);
		s.sub(61001);
		
		assertEquals(s.getMilliseconds(),9);
		assertEquals(s.getSeconds(),9);
		assertEquals(s.getMinutes(),9);
	}
	
	@Test
	public void testSubSWE1MS() {
		StopWatch s1 = new StopWatch(10);
		StopWatch s2 = new StopWatch(9);
		
		s1.sub(s2);
		
		assertEquals(s1.getMilliseconds(),1);
	}
	
	@Test
	public void testSubSWE2MS() {
		StopWatch s1 = new StopWatch(10,0);
		StopWatch s2 = new StopWatch(9,0);
		
		s1.sub(s2);
		
		assertEquals(s1.getSeconds(),1);
	}
	
	@Test
	public void testSubSWE3MS() {
		StopWatch s1 = new StopWatch(10,0,0);
		StopWatch s2 = new StopWatch(9,0,0);
		
		s1.sub(s2);
		
		assertEquals(s1.getMinutes(),1);
	}
	
	@Test
	public void testSubSWE4MS() {
		StopWatch s1 = new StopWatch(10,10,888);
		StopWatch s2 = new StopWatch(9,8,788);
		
		s1.sub(s2);
		
		assertEquals(s1.getMilliseconds(),100);
		assertEquals(s1.getSeconds(),2);
		assertEquals(s1.getMinutes(),1);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testSubSWE5MS() {
		StopWatch s1 = new StopWatch(10,10,888);
		StopWatch s2 = new StopWatch(10,11,788);
		
		s1.sub(s2);		
	}
	
	//testing subtraction method working
		@Test
		public void testSubID1( ) {
			StopWatch s1 = new StopWatch(999, 59, 999);
			StopWatch s2 = new StopWatch(999, 59, 999);
			
			s1.sub(s2);
			
			assertEquals(s1.getMilliseconds(), 0);
			assertEquals(s1.getMinutes(), 0);
			assertEquals(s1.getSeconds(), 0);
			
		}
		//testing subtraction method invalidly
		@Test (expected = IllegalArgumentException.class)
		public void testSubID2( ) {
			StopWatch s1 = new StopWatch(5, 5, 555);
			StopWatch s2 = new StopWatch(6, 6, 665);
			
			s1.sub(s2);		
		}

		//Testing millisecond setter methods for negative
	     @Test (expected = IllegalArgumentException.class)
	     public void setttertestID() {
	    	 StopWatch s1 = new StopWatch(10, 10, 10);         
	         s1.setMilliseconds(-2);
	     }
	     
	     //Testing minutes setter methods for negative
	     @Test (expected = IllegalArgumentException.class)
	     public void setttertest1ID() {
	    	 StopWatch s1 = new StopWatch(10, 10, 10);         
	         s1.setMinutes(-2);
	     }
	     
	     //Testing seconds setter methods for negative
	     @Test (expected = IllegalArgumentException.class)
	     public void setttertest2ID() {
	    	 StopWatch s1 = new StopWatch(10, 10, 10);         
	         s1.setSeconds(-2);
	     }
	     
	   //Testing setter methods of big numbers to be reduced
	     @Test 
	     public void settertes3ID() {
	    	 StopWatch s1 = new StopWatch(10, 10, 10);
	    	 s1.setMilliseconds(1000000);
	    	 s1.setMinutes(1000000);
	     }
	     
	   //Testing weird numbers
	     @Test
	     public void settertest4ID( ) {
	    	 StopWatch s1 = new StopWatch(10,10,1);
	    	 s1.setSeconds(11111111);
	    	 s1.setMinutes(9999);
	    	 s1.setMilliseconds(9000);
	    	 
	    	 
	     }
	   //equals to testing for false
	 	@Test
	 	public void equalsTestID() {
	 	   StopWatch s1 = new StopWatch (12, 0, 200);
	 	   StopWatch s2 = new StopWatch  (12, 44, 999);
	 	   
	 	  assertFalse(s1.equals(s2));
	 	}
	 	
	 	//equals to testing false
	 	@Test
	 	public void equalsTest1ID() {
	 	   StopWatch s1 = new StopWatch (12, 33, 999);
	 	   StopWatch s2 = new StopWatch  (12, 44, 999);
	 	   
	 	  assertFalse(s1.equals(s2));
	 	}
	 	
	 	//equals to testing false
	 	@Test
	 	public void equalsTest2ID() {
	 	   StopWatch s1 = new StopWatch (12, 44, 999);
	 	   StopWatch s2 = new StopWatch  (11, 44, 999);
	 	   
	 	  assertFalse(s1.equals(s2));
	 	}
	 	
	 	//equals to testing, 
	 	@Test
	 	public void equalsTest3ID() {
	 	   StopWatch s1 = new StopWatch (12, 44, 201);
	 	   StopWatch s2 = new StopWatch  (12, 44, 200);
	 	   
	 	  assertFalse(s1.equals(s2));
	 	}
	 	
	 	//compares stopwatches
	 	@Test
	 	public void compareToTestID() {
	 	StopWatch s1 = new StopWatch (12, 0, 200);
	 	StopWatch s2 = new StopWatch (12, 0, 200);
	 	StopWatch s3 = new StopWatch (14, 1, 200);	
	 	
	 	assertEquals(1,  s3.compareTo(s1));
	 	assertEquals(-1, s1.compareTo(s3));
	 	assertEquals(0, s1.compareTo(s2));
	 	}
	 	
	 	//tests the dec will stop at 0
	 	@Test
	 	public void decTestCaseID() {
	 		StopWatch s1 = new StopWatch (0, 0 ,200);
	 		for(int x = 0; x<9000000; x++)
	 			s1.dec();
	 		assertTrue(s1.getMilliseconds() == 0);
	 		
	 		
	 	}
	 	
	 	//tests the dec a lot of times
	 	@Test
	 	public void decTestCase1ID() {
	 		StopWatch s1 = new StopWatch (1, 0);
	 		for(int x = 0; x<999; x++)
	 			s1.dec();
	 		assertTrue(s1.getMilliseconds() == 1);
	 	}
	 	
	 	//tests the dec when greater than milliseconds for translator
	 	@Test
	 	public void decTestCase2ID() {
	 		StopWatch s1 = new StopWatch (1, 40 ,0);
	 		for(int x = 0; x<99999; x++)
	 			s1.dec();
	 		assertTrue(s1.getMilliseconds() == 1);
	 	}
	 	
	 	//tests to String for multiple values
	 	@Test
	 	public void toStringID( ) {
	 		StopWatch s1 = new StopWatch (3, 3, 250);
	 		assertTrue(s1.toString().equals("3:03:250"));
	 		StopWatch s2 = new StopWatch (2, 2, 999);
	 		assertTrue(s2.toString().equals("2:02:999"));	
	 		StopWatch s3 = new StopWatch (0, 12, 3);
	 		assertTrue(s3.toString().equals("0:12:003"));
	 		StopWatch s4 = new StopWatch (0, 0, 0);
	 		assertTrue(s4.toString().equals("0:00:000"));
	 			
	 	}
	 	//testing to String for false
	 		@Test 
	 		public void toStringID2( ) {
	 			StopWatch s1 = new StopWatch(5, 5, 555);
	 			StopWatch s2 = new StopWatch(6, 6, 665);
	 			StopWatch s3 = new StopWatch (0, 0, 0);
	 			assertFalse(s1.toString().equals("0:05:555"));
	 			assertFalse(s2.toString().equals("6:00:665"));
	 			assertFalse(s3.toString().equals("6:06:000"));
	 		}
 	
}

         
        