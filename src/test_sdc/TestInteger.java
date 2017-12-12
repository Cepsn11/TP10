package test_sdc;

import junit.framework.*;
import sdc.*;

public class TestInteger extends TestCase {

    SDC sdc;

    public void setUp() {
	this.sdc = new SDC();
    }

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

}