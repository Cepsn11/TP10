package test_sdc;

import junit.framework.*;
import sdc.*;
import sdc.exception.ConditionException;
import sdc.exception.IncompatibleTypeException;
import sdc.exception.NumericException;
import sdc.exception.StackException;
import sdc.exception.SymbolNotFoundException;
import sdc.exception.VariableException;

public class TestSDC extends TestCase {

	SDC sdc;

	public void setUp() {
		this.sdc = new SDC();
	}

	public void testRemoveComments() {
		try {
			String code = "; petit programme pour determiner le nombre de racines d’un polynome\n" + 
		    		"; du 2eme degre: A X^2 + B X + C\n" + 
		    		";\n" + 
		    		"; entrez ci dessous les 3 cœfficients A B C dans cet ordre\n" + 
		    		"14#2 10#2 -13#2\n" + 
		    		"; le programme commence ici\n" + 
		    		"=> C ; C vaut -6.5\n" + 
		    		"=> B ; B vaut 5.0\n" + 
		    		"=> A ; A vaut 7.0\n" + 
		    		"$B $B * 8#2 $A $C * * - => Delta ; Delta = B*B - 4*A*C\n" + 
		    		"0#1 $Delta = if\n" + 
		    		"1 ; Le polynome a une seule racine\n" + 
		    		"else\n" + 
		    		"0#1 $Delta < if\n" + 
		    		"2 ; Le polynome a 2 racines distinctes\n" + 
		    		"else\n" + 
		    		"0 ; Le polynome n’a pas de racines reelles\n" + 
		    		"endif\n" + 
		    		"endif\n" + 
		    		"view\n";
			String codeExpected = "14#2 10#2 -13#2  => C  => B  => A  $B $B * 8#2 $A $C * * - => Delta  0#1 $Delta = if 1  else 0#1 $Delta < if 2  else 0  endif endif view";
			assertEquals(codeExpected, SDC.removeComments(code));

		} catch (Exception e) {
			assertEquals(true, false);
		}
	}

	public void testExecuteLine() {
		try {
			/* Cas d'une ligne vide */
			this.sdc.executeLine("");
			String expectedRes = "";
			String res = this.sdc.getLastResult();
			assertEquals(expectedRes, res);

			/* Cas d'un symbole non référencé */
			try {
				this.sdc.executeLine("symboleNonRef");
				assertTrue(false);
			} catch (SymbolNotFoundException e) {
				assertTrue(true);
			}
			
			/*
			 * Cas d'un symbole non référencé : rationnel avec dénominateur à 0
			 * Note : Évite de lancer une NumericException à partir de la méthode parse.
			 */
			try {
				this.sdc.executeLine("2#0");
				assertTrue(false);
			} catch (SymbolNotFoundException e) {
				assertTrue(true);
			}

			/* Cas d'une action sur pile vide */
			try {
				this.sdc.executeLine("+");
				assertTrue(false);
			} catch (StackException e) {
				assertTrue(true);
			}

			/* Cas d'une opération correcte */
			this.sdc.executeLine("2 1 +");
			expectedRes = "3";
			res = this.sdc.getLastResult();
			assertEquals(expectedRes, res);

			/* Cas d'une opération incorrecte : erreur type incompatible */
			try {
				this.sdc.executeLine("3 true +");
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

			/* Cas d'une opération incorrecte : erreur arithmétique */
			try {
				this.sdc.executeLine("3 0 /");
				assertTrue(false);
			} catch (NumericException e) {
				assertTrue(true);
			}

			/* Cas d'une opération incorrecte : erreur d'affectation et variable inconnue */
			try {
				this.sdc.executeLine("3 =>");
				assertTrue(false);
			} catch (VariableException e) {
				assertTrue(true);
			}
			try {
				this.sdc.executeLine("3 => var-incorrecte");
				assertTrue(false);
			} catch (VariableException e) {
				assertTrue(true);
			}
			try {
				this.sdc.executeLine("$y");
				assertTrue(false);
			} catch (VariableException e) {
				assertTrue(true);
			}

			/* Cas d'une opération incorrecte : erreur valeur évaluée par une condition */
			try {
				this.sdc.executeLine("3 if 8 else 9");
				assertTrue(false);
			} catch (ConditionException e) {
				assertTrue(true);
			}

		} catch (Exception e) {
			System.out.println(e);
			assertEquals(true, false);
		}
	}

}