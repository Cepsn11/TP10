package test_sdc;

import junit.framework.*;
import sdc.*;
import sdc.exception.VariableException;

public class TestVariable extends TestCase {

	SDC sdc;

	public void setUp() {
		this.sdc = new SDC();
	}

	public void testVariable() {
		try {
			/* Affectation puis utilisation de la variable */
			this.sdc.executeLine("3 => x $x");
			String expectedRes = "3";
			String res = this.sdc.getLastResult();
			assertEquals(expectedRes, res);

			/* Deux affectations pour une même variable */
			this.sdc.executeLine("3 => x 8 => x $x");
			expectedRes = "8";
			res = this.sdc.getLastResult();
			assertEquals(expectedRes, res);

			/* Opération numérique integer sur une variable */
			this.sdc.executeLine("3 => x 4 $x *");
			expectedRes = "12";
			res = this.sdc.getLastResult();
			assertEquals(expectedRes, res);

			/* Résultat d'une opération numérique integer dans une variable */
			this.sdc.executeLine("5 => x 4 $x * => y $y");
			expectedRes = "20";
			res = this.sdc.getLastResult();
			assertEquals(expectedRes, res);

			/* Opération numérique rational sur une variable */
			this.sdc.executeLine("3#2 => x 4#2 $x *");
			expectedRes = "12#4";
			res = this.sdc.getLastResult();
			assertEquals(expectedRes, res);

			/* Résultat d'une opération numérique rational dans une variable */
			this.sdc.executeLine("5#2 => x 4#2 $x * => y $y");
			expectedRes = "20#4";
			res = this.sdc.getLastResult();
			assertEquals(expectedRes, res);

			/* Opération booléenne sur une variable */
			this.sdc.executeLine("true => x false $x &");
			expectedRes = "false";
			res = this.sdc.getLastResult();
			assertEquals(expectedRes, res);

			/* Résultat d'une opération booléenne dans une variable */
			this.sdc.executeLine("true => x false $x | => y $y");
			expectedRes = "true";
			res = this.sdc.getLastResult();
			assertEquals(expectedRes, res);
		} catch (Exception e) {
			System.out.println(e);
			assertEquals(true, false);
		}
	}

	public void testVariableException() {
		try {
			/* Vérifier le nom de la variable à l'affectation */
			this.sdc.executeLine("3 => x");
			assertTrue(true);
			try {
				this.sdc.executeLine("3 =>");
				assertTrue(false);
			} catch (VariableException e) {
				assertTrue(true);
			}
			try {
				this.sdc.executeLine("3 => var-incorrect");
				assertTrue(false);
			} catch (VariableException e) {
				assertTrue(true);
			}

			/* Vérifier si la variable utilisée a été affectée */
			this.sdc.executeLine("3 => x $x");
			assertTrue(true);
			try {
				this.sdc.executeLine("3 => x $z");
				assertTrue(false);
			} catch (VariableException e) {
				assertTrue(true);
			}
		} catch (Exception e) {
			assertEquals(true, false);
		}
	}
	
}