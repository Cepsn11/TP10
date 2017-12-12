package test_sdc;

import junit.framework.*;
import sdc.*;

public class TestAbs extends TestCase {

    SDC sdc;

    public void setUp() {
	this.sdc = new SDC();
    }

    public void testAbs() {
	try {
	    // integer
	    this.sdc.executeLine("12 ||");
	    String expectedRes = "12";
	    String res = this.sdc.getLastResult();
	    assertEquals(expectedRes, res);
	    
	    this.sdc.executeLine("-23 ||");
	    expectedRes = "23";
	    res = this.sdc.getLastResult();
	    assertEquals(expectedRes, res);

	    // rational
	    this.sdc.executeLine("12#2 ||");
	    expectedRes = "12#2";
	    res = this.sdc.getLastResult();
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
	    expectedRes = "-23#-5";
	    res = this.sdc.getLastResult();
	    assertEquals(expectedRes, res);

	} catch (Exception e) {
	    assertEquals(true, false);
	}
    }

}