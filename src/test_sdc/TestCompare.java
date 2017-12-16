package test_sdc;

import junit.framework.TestCase;
import sdc.SDC;

public class TestCompare extends TestCase {

	SDC sdc;

	public void setUp() {
		this.sdc = new SDC();
	}

	public void testAbs() {
		try {
			// integer
			this.sdc.executeLine("-2345 1 >");
			String expectedRes = "false";
			String res = this.sdc.getLastResult();
			assertEquals(expectedRes, res);

			this.sdc.executeLine("-2#3 -1#3 <");
			expectedRes = "true";
			res = this.sdc.getLastResult();
			assertEquals(expectedRes, res);

			// rational
			this.sdc.executeLine("-2 -50000 >");
			expectedRes = "true";
			res = this.sdc.getLastResult();
			assertEquals(expectedRes, res);

			this.sdc.executeLine("-23#5 1#3 <");
			expectedRes = "true";
			res = this.sdc.getLastResult();
			assertEquals(expectedRes, res);

			this.sdc.executeLine("-12#-2 12#2 =");
			expectedRes = "true";
			res = this.sdc.getLastResult();
			assertEquals(expectedRes, res);

			this.sdc.executeLine("23#-5 23#5 =");
			expectedRes = "false";
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