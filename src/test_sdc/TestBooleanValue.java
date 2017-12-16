package test_sdc;

import junit.framework.TestCase;
import sdc.SDC;
import sdc.exceptions.SymbolNotFoundException;

public class TestBooleanValue extends TestCase {

	SDC sdc;

	public void setUp() {
		this.sdc = new SDC();
	}

	public void testBooleanCreation() {
		try {
			String[] test = { "true", "false", "false", "false" };

			for (String expectedRes : test) {
				this.sdc.executeLine(expectedRes);
				assertEquals(expectedRes, this.sdc.getLastResult());
			}
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

}