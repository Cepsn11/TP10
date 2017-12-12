package test_sdc;

import junit.framework.*;
import sdc.*;

public class TestRational extends TestCase {

    SDC sdc;

    public void setUp() {
	this.sdc = new SDC();
    }

    public void testRationalCreation() {
	try {
	    String[] test = {"12#2", "-12#1", "1#2", "0#2"};

	    for (String expectedRes : test) {
		this.sdc.executeLine(expectedRes);
		assertEquals(expectedRes, this.sdc.getLastResult());
	    }
	} catch (Exception e) {
	    assertEquals(true, false);
	}
    }

    public void testRationalCreationFail() {
	String[] test = {"12.0#2", "-12#a", "1a#2"};
	
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

    public void testMultiplyFail() {

	try {
	    this.sdc.executeLine("12#2 2 *");
	    assertEquals(false, true);
	} catch (IncompatibleTypeException e) {
	    assertEquals(true, true);
	} catch (Exception e) {
	    assertEquals(true, false);
	}
	
    }

}