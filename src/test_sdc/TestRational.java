package test_sdc;

import junit.framework.*;
import sdc.*;
import sdc.exception.NumericException;
import sdc.exception.SymbolNotFoundException;

public class TestRational extends TestCase {

	SDC sdc;

	public void setUp() {
		this.sdc = new SDC();
	}

	
	/* ########## TEST CREATION ########## */

	public void testRationalCreation() {
		try {
			String[] test = { "12#2", "-12#1", "1#2", "0#2" };

			for (String expectedRes : test) {
				this.sdc.executeLine(expectedRes);
				assertEquals(expectedRes, this.sdc.getLastResult());
			}
		} catch (Exception e) {
			assertEquals(true, false);
		}
	}

	public void testRationalCreationFail() {
		String[] test = { "12.0#2", "-12#a", "1a#2" };

		try {
			for (String expectedRes : test) {
				try {
					this.sdc.executeLine(expectedRes);
					assertEquals(false, true);
				} catch (SymbolNotFoundException e) {
					assertEquals(true, true);
				}
			}
		} catch (Exception e) {
			assertEquals(false, true);
		}
	}

	
	/* ########## TEST OPERATION NUMERIQUE ########## */

	public void testMultiply() {
		try {
			this.sdc.executeLine("12#2 2#2 *");
			String expectedRes = "24#4";
			String res = this.sdc.getLastResult();
			assertEquals(expectedRes, res);

			this.sdc.executeLine("1#1 *");
			expectedRes = "24#4";
			res = this.sdc.getLastResult();
			assertEquals(expectedRes, res);
		} catch (Exception e) {
			assertEquals(true, false);
		}
	}

	public void testDivide() {
		try {
			this.sdc.executeLine("48#16 2#4 /");
			String expectedRes = "24#4";
			String res = this.sdc.getLastResult();
			assertEquals(expectedRes, res);

			this.sdc.executeLine("4#2 /");
			expectedRes = "6#2";
			res = this.sdc.getLastResult();
			assertEquals(expectedRes, res);

			this.sdc.executeLine("-2#1 /");
			expectedRes = "-3#2";
			res = this.sdc.getLastResult();
			assertEquals(expectedRes, res);

			this.sdc.executeLine("-1#1 /");
			expectedRes = "3#2";
			res = this.sdc.getLastResult();
			assertEquals(expectedRes, res);

			/* Division par 0 */
			this.sdc.executeLine("0#8 /");
			assertTrue(false);

		} catch (NumericException e) {
			assertTrue(true);

		} catch (Exception e) {
			assertEquals(true, false);
		}
	}

	public void testAdd() {
		try {
			this.sdc.executeLine("3#2 2#2 +");
			String expectedRes = "5#2";
			String res = this.sdc.getLastResult();
			assertEquals(expectedRes, res);

			this.sdc.executeLine("2#3 +");
			expectedRes = "19#6";
			res = this.sdc.getLastResult();
			assertEquals(expectedRes, res);

			this.sdc.executeLine("-1#6 +");
			expectedRes = "18#6";
			res = this.sdc.getLastResult();
			assertEquals(expectedRes, res);

			this.sdc.executeLine("-1#2 +");
			expectedRes = "30#12";
			res = this.sdc.getLastResult();
			assertEquals(expectedRes, res);

		} catch (Exception e) {
			assertEquals(true, false);
		}
	}

	public void testSub() {
		try {
			this.sdc.executeLine("8#4 2#4 -");
			String expectedRes = "6#4";
			String res = this.sdc.getLastResult();
			assertEquals(expectedRes, res);

			this.sdc.executeLine("1#2 -");
			expectedRes = "8#8";
			res = this.sdc.getLastResult();
			assertEquals(expectedRes, res);

			this.sdc.executeLine("-2#8 +");
			expectedRes = "6#8";
			res = this.sdc.getLastResult();
			assertEquals(expectedRes, res);

			this.sdc.executeLine("-1#4 +");
			expectedRes = "16#32";
			res = this.sdc.getLastResult();
			assertEquals(expectedRes, res);

		} catch (Exception e) {
			assertEquals(true, false);
		}
	}

	public void testAbs() {
		try {

			this.sdc.executeLine("12#2 ||");
			String expectedRes = "12#2";
			String res = this.sdc.getLastResult();
			assertEquals(expectedRes, res);

			this.sdc.executeLine("-23#5 ||");
			expectedRes = "23#5";
			res = this.sdc.getLastResult();
			assertEquals(expectedRes, res);

			this.sdc.executeLine("-12#-2 ||");
			expectedRes = "-12#-2";
			res = this.sdc.getLastResult();
			assertEquals(expectedRes, res);

			this.sdc.executeLine("23#-5 ||");
			expectedRes = "23#5";
			res = this.sdc.getLastResult();
			assertEquals(expectedRes, res);

		} catch (Exception e) {
			assertEquals(true, false);
		}
	}

	
	/* ######### TEST OPERATION COMPARAISON ########## */

	public void testGreaterThan() {
		try {
			this.sdc.executeLine("-3#2 -5#2 >");
			String expectedRes = "true";
			String res = this.sdc.getLastResult();
			assertEquals(expectedRes, res);

			this.sdc.executeLine("-3#2 2#4 >");
			expectedRes = "false";
			res = this.sdc.getLastResult();
			assertEquals(expectedRes, res);

		} catch (Exception e) {
			assertEquals(true, false);
		}
	}

	public void testSmallerThan() {
		try {
			this.sdc.executeLine("-23#5 1#3 <");
			String expectedRes = "true";
			String res = this.sdc.getLastResult();
			assertEquals(expectedRes, res);

			this.sdc.executeLine("120#2 20#5 <");
			expectedRes = "false";
			res = this.sdc.getLastResult();
			assertEquals(expectedRes, res);
		} catch (Exception e) {
			assertEquals(true, false);
		}
	}

	public void testEqualsTo() {
		try {
			this.sdc.executeLine("3#2 15#5 =");
			String expectedRes = "false";
			String res = this.sdc.getLastResult();
			assertEquals(expectedRes, res);

			this.sdc.executeLine("-12#-2 12#2 =");
			expectedRes = "true";
			res = this.sdc.getLastResult();
			assertEquals(expectedRes, res);

			this.sdc.executeLine("23#-5 23#5 =");
			expectedRes = "false";
			res = this.sdc.getLastResult();
			assertEquals(expectedRes, res);
		} catch (Exception e) {
			assertEquals(true, false);
		}
	}

}