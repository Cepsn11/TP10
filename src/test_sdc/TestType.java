package test_sdc;

import junit.framework.*;
import sdc.*;
import sdc.exception.IncompatibleTypeException;

public class TestType extends TestCase {

	SDC sdc;

	public void setUp() {
		this.sdc = new SDC();
	}

	/* Teste les types en fonction des opérations */
	public void testTypeOperation() {

		try {
			/* Opération numérique : valeur absolue */
			this.sdc.executeLine("12 ||");
			assertTrue(true);
			try {
				this.sdc.executeLine("true ||");
				assertTrue(false);
			} catch (IncompatibleTypeException e) {
				assertTrue(true);
			}

			/* Opération booléenne : not */
			this.sdc.executeLine("true ~");
			assertTrue(true);
			try {
				this.sdc.executeLine("12 ~");
				assertTrue(false);
			} catch (IncompatibleTypeException e) {
				assertTrue(true);
			}

			/* Opération numérique : add */
			this.sdc.executeLine("3 4 +");
			assertTrue(true);
			try {
				this.sdc.executeLine("3 true +");
				assertTrue(false);
			} catch (IncompatibleTypeException e) {
				assertTrue(true);
			}
			try {
				this.sdc.executeLine("3 3#2 +");
				assertTrue(false);
			} catch (IncompatibleTypeException e) {
				assertTrue(true);
			}

			/* Opération numérique : sub */
			this.sdc.executeLine("3 4 -");
			assertTrue(true);
			try {
				this.sdc.executeLine("3 true -");
				assertTrue(false);
			} catch (IncompatibleTypeException e) {
				assertTrue(true);
			}
			try {
				this.sdc.executeLine("3 3#2 -");
				assertTrue(false);
			} catch (IncompatibleTypeException e) {
				assertTrue(true);
			}

			/* Opération numérique : mult */
			this.sdc.executeLine("3 4 *");
			assertTrue(true);
			try {
				this.sdc.executeLine("3 true *");
				assertTrue(false);
			} catch (IncompatibleTypeException e) {
				assertTrue(true);
			}
			try {
				this.sdc.executeLine("3 3#2 *");
				assertTrue(false);
			} catch (IncompatibleTypeException e) {
				assertTrue(true);
			}

			/* Opération numérique : div */
			this.sdc.executeLine("3 4 /");
			assertTrue(true);
			try {
				this.sdc.executeLine("3 true /");
				assertTrue(false);
			} catch (IncompatibleTypeException e) {
				assertTrue(true);
			}
			try {
				this.sdc.executeLine("3 3#2 /");
				assertTrue(false);
			} catch (IncompatibleTypeException e) {
				assertTrue(true);
			}

			/* Opération booléenne : and */
			this.sdc.executeLine("true false &");
			assertTrue(true);
			try {
				this.sdc.executeLine("3 true &");
				assertTrue(false);
			} catch (IncompatibleTypeException e) {
				assertTrue(true);
			}

			/* Opération booléenne : or */
			this.sdc.executeLine("true false |");
			assertTrue(true);
			try {
				this.sdc.executeLine("3 true |");
				assertTrue(false);
			} catch (IncompatibleTypeException e) {
				assertTrue(true);
			}
		} catch (Exception e) {
			assertEquals(true, false);
		}
	}

}