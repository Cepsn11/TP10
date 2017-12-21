package test_sdc;

import junit.framework.TestCase;
import sdc.SDC;
import sdc.exception.SymbolNotFoundException;

public class TestBoolean extends TestCase {

	SDC sdc;

	public void setUp() {
		this.sdc = new SDC();
	}

	
	/* ########## TEST CREATION ########## */

	public void testBooleanCreation() {
		try {
			this.sdc.executeLine("true");
			String expectedRes = "true";
			String res = this.sdc.getLastResult();
			assertEquals(expectedRes, res);

			this.sdc.executeLine("false");
			expectedRes = "false";
			res = this.sdc.getLastResult();
			assertEquals(expectedRes, res);
		} catch (Exception e) {
			assertEquals(true, false);
		}
	}

	public void testBooleanCreationFail() {
		String[] test = { "truez", "fALSE", "Faux" };

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

	
	/* ########## TEST OPERATION BOOLEENNE ########## */

	public void testAnd() {
		try {
			this.sdc.executeLine("true false &");
			String expectedRes = "false";
			String res = this.sdc.getLastResult();
			assertEquals(expectedRes, res);

			this.sdc.executeLine("true true &");
			expectedRes = "true";
			res = this.sdc.getLastResult();
			assertEquals(expectedRes, res);
		} catch (Exception e) {
			assertEquals(true, false);
		}
	}

	public void testOr() {
		try {
			this.sdc.executeLine("true false |");
			String expectedRes = "true";
			String res = this.sdc.getLastResult();
			assertEquals(expectedRes, res);

			this.sdc.executeLine("false false |");
			expectedRes = "false";
			res = this.sdc.getLastResult();
			assertEquals(expectedRes, res);
		} catch (Exception e) {
			assertEquals(true, false);
		}
	}

	public void testNot() {
		try {
			this.sdc.executeLine("true ~");
			String expectedRes = "false";
			String res = this.sdc.getLastResult();
			assertEquals(expectedRes, res);

			this.sdc.executeLine("false ~");
			expectedRes = "true";
			res = this.sdc.getLastResult();
			assertEquals(expectedRes, res);
		} catch (Exception e) {
			assertEquals(true, false);
		}
	}

}
