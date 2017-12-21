package test_sdc;

import junit.framework.*;
import sdc.*;
import sdc.exception.NumericException;

public class TestInteger extends TestCase {

	SDC sdc;

	public void setUp() {
		this.sdc = new SDC();
	}

	/* ########## TEST CREATION ########## */

	public void testIntegerCreation() {
		try {
			this.sdc.executeLine("12");
			String expectedRes = "12";
			String res = this.sdc.getLastResult();
			assertEquals(expectedRes, res);

			this.sdc.executeLine("-23");
			expectedRes = "-23";
			res = this.sdc.getLastResult();
			assertEquals(expectedRes, res);
		} catch (Exception e) {
			assertEquals(true, false);
		}
	}

	/* ########## TEST OPERATION NUMERIQUE ########## */

	public void testAdd() {

		try {
			this.sdc.executeLine("12 2 +");
			String expectedRes = "14";
			String res = this.sdc.getLastResult();
			assertEquals(expectedRes, res);

			this.sdc.executeLine("-2 +");
			expectedRes = "12";
			res = this.sdc.getLastResult();
			assertEquals(expectedRes, res);
		} catch (Exception e) {
			assertEquals(true, false);
		}
	}

	public void testSub() {

		try {
			this.sdc.executeLine("12 2 -");
			String expectedRes = "10";
			String res = this.sdc.getLastResult();
			assertEquals(expectedRes, res);

			this.sdc.executeLine("-2 -");
			expectedRes = "12";
			res = this.sdc.getLastResult();
			assertEquals(expectedRes, res);
		} catch (Exception e) {
			assertEquals(true, false);
		}
	}

	public void testMult() {

		try {
			this.sdc.executeLine("12 3 *");
			String expectedRes = "36";
			String res = this.sdc.getLastResult();
			assertEquals(expectedRes, res);

			this.sdc.executeLine("-2 *");
			expectedRes = "-72";
			res = this.sdc.getLastResult();
			assertEquals(expectedRes, res);
		} catch (Exception e) {
			assertEquals(true, false);
		}
	}

	public void testDiv() {

		try {
			this.sdc.executeLine("12 3 /");
			String expectedRes = "4";
			String res = this.sdc.getLastResult();
			assertEquals(expectedRes, res);

			this.sdc.executeLine("-2 /");
			expectedRes = "-2";
			res = this.sdc.getLastResult();
			assertEquals(expectedRes, res);

			/* Division par 0 */
			this.sdc.executeLine("0 /");
			assertTrue(false);

		} catch (NumericException e) {
			assertTrue(true);
		} catch (Exception e) {
			assertEquals(true, false);
		}
	}

	public void testAbs() {
		try {

			this.sdc.executeLine("12 ||");
			String expectedRes = "12";
			String res = this.sdc.getLastResult();
			assertEquals(expectedRes, res);

			this.sdc.executeLine("-23 ||");
			expectedRes = "23";
			res = this.sdc.getLastResult();
			assertEquals(expectedRes, res);

		} catch (Exception e) {
			assertEquals(true, false);
		}
	}

	/* ########## TEST OPERATION COMPARAISON ########## */

	public void testGreaterThan() {
		try {
			this.sdc.executeLine("-2345 1 >");
			String expectedRes = "false";
			String res = this.sdc.getLastResult();
			assertEquals(expectedRes, res);

			this.sdc.executeLine("-3 -5 >");
			expectedRes = "true";
			res = this.sdc.getLastResult();
			assertEquals(expectedRes, res);
		} catch (Exception e) {
			assertEquals(true, false);
		}
	}

	public void testSmallerThan() {
		try {
			this.sdc.executeLine("-5 2 <");
			String expectedRes = "true";
			String res = this.sdc.getLastResult();
			assertEquals(expectedRes, res);

			this.sdc.executeLine("2 -1 <");
			expectedRes = "false";
			res = this.sdc.getLastResult();
			assertEquals(expectedRes, res);
		} catch (Exception e) {
			assertEquals(true, false);
		}
	}

	public void testEqualsTo() {
		try {
			this.sdc.executeLine("10 10 =");
			String expectedRes = "true";
			String res = this.sdc.getLastResult();
			assertEquals(expectedRes, res);

			this.sdc.executeLine("10 3 =");
			expectedRes = "false";
			res = this.sdc.getLastResult();
			assertEquals(expectedRes, res);

			this.sdc.executeLine("10 -10 =");
			expectedRes = "false";
			res = this.sdc.getLastResult();
			assertEquals(expectedRes, res);
		} catch (Exception e) {
			assertEquals(true, false);
		}
	}

}