package test_sdc;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import junit.framework.TestCase;
import sdc.SDC;
import sdc.exception.ShutdownException;

public class TestControlCommand extends TestCase {

	SDC sdc;

	public void setUp() {
		this.sdc = new SDC();
	}

	public void testViewCommand() {
		try {
			this.sdc.executeLine("5 true false");
			OutputStream os = new ByteArrayOutputStream();
			PrintStream ps = new PrintStream(os);
			System.setOut(ps);
			this.sdc.executeLine("view");
			String outExpected = "3 ----> 5\n" + "2 ----> true\n" + "1 ----> false";
			assertEquals(outExpected + System.getProperty("line.separator"), os.toString());
			PrintStream originalOut = System.out;
			System.setOut(originalOut);
		} catch (Exception e) {
			assertEquals(true, false);
		}
	}

	public void testClearCommand() {
		try {
			this.sdc.executeLine("1 2 3 4 5 6 7 8");
			String expectedRes = "";
			this.sdc.executeLine("clear");
			String res = this.sdc.getLastResult();
			assertEquals(expectedRes, res);
		} catch (Exception e) {
			assertEquals(true, false);
		}
	}

	public void testQuitCommand() {
		try {
			try {
				this.sdc.executeLine("quit");
				assertTrue(false);
			} catch (ShutdownException e) {
				assertTrue(true);
			}
		} catch (Exception e) {
			assertEquals(true, false);
		}
	}

}
