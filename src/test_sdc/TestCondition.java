package test_sdc;

import static org.junit.Assert.*;

import java.util.StringTokenizer;

import junit.framework.TestCase;
import sdc.SDC;
import sdc.exception.ConditionException;
import sdc.instruction.ParseCode;

public class TestCondition extends TestCase {

	SDC sdc;

	public void setUp() {
		this.sdc = new SDC();
	}

	public void testBuildBlocks() {
		System.out.println("################## TEST BUILDBLOCKS ########################\n");

		/* Avec un seul if */
		String code = "\t1 ; Le polynome a une seule racine\n" + "endif\n" + "\nview\n";
		String[] expectedRes = new String[2];
		ParseCode parseCode = new ParseCode(new StringTokenizer(code));
		expectedRes[0] = "1 ; Le polynome a une seule racine";
		expectedRes[1] = "";
		String[] res = parseCode.buildBlocksIfElse();
		assertArrayEquals(expectedRes, res);
		System.out.println("1 - AVEC UN SEUL IF\n");
		System.out.println("---- CODE ----\n" + code);
		System.out.println("---- RESULTAT ----\n-> Block If\n" + res[0] + "\n-> Block Else\n" + res[1] + "\n");

		/* Avec un if et un else */
		code = 	"\t1 ; Le polynome a une seule racine\n"
				+"else\n" 
				+ "\t2 ; Le polynome a 2 racines distinctes\n"
				+ "endif\n" 
				+ "\nview\n";
		parseCode = new ParseCode(new StringTokenizer(code));
		expectedRes[0] = "1 ; Le polynome a une seule racine";
		expectedRes[1] = "2 ; Le polynome a 2 racines distinctes";
		res = parseCode.buildBlocksIfElse();
		assertArrayEquals(expectedRes, res);
		System.out.println("2 - AVEC UN IF ET UN ELSE\n");
		System.out.println("---- CODE ----\n" + code);
		System.out.println("---- RESULTAT ----\n-> Block If\n" + res[0] + "\n-> Block Else\n" + res[1] + "\n");

		/* Avec des if et else imbriqués */
		
		code = 	"\t1 ; Le polynome a une seule racine\n" + "else\n"
				+ "\t0.0 $Delta < if\n"
				+ "\t\t2 ; Le polynome a 2 racines distinctes\n"
				+ "\telse\n"
				+ "\t\t0 ; Le polynome n'a pas de racines reelles\n" 
				+ "\tendif\n" 
				+ "endif\n" 
				+ "\nview\n";
		parseCode = new ParseCode(new StringTokenizer(code));
		expectedRes[0] = "1 ; Le polynome a une seule racine";
		expectedRes[1] = "0.0 $Delta < if 2 ; Le polynome a 2 racines distinctes else 0 ; Le polynome n'a pas de racines reelles endif";
		res = parseCode.buildBlocksIfElse();
		assertArrayEquals(expectedRes, res);
		System.out.println("3 - AVEC DES IF ET ELSE IMBRIQUES 1\n");
		System.out.println("---- CODE ----\n" + code);
		System.out.println("---- RESULTAT ----\n-> Block If\n" + res[0] + "\n-> Block Else\n" + res[1] + "\n");

		code = "4 5 < if 9999 else 9 15 > if 8888 endif endif else 3 3 = if 7777 else 6666 endif";
		parseCode = new ParseCode(new StringTokenizer(code));
		expectedRes[0] = "4 5 < if 9999 else 9 15 > if 8888 endif endif";
		expectedRes[1] = "3 3 = if 7777 else 6666 endif";
		res = parseCode.buildBlocksIfElse();
		assertArrayEquals(expectedRes, res);
		System.out.println("4 - AVEC DES IF ET ELSE IMBRIQUES 2 \n");
		System.out.println("---- CODE ----\n" + code);
		System.out.println("---- RESULTAT ----\n-> Block If\n" + res[0] + "\n-> Block Else\n" + res[1] + "\n");
		System.out.println("################## FIN - TEST BUILDBLOCKS ########################\n");

	}

	public void testCondition() {
		try {
			/* if simple */
			this.sdc.executeLine("true true & if 3 ");
			String expectedRes = "3";
			String res = this.sdc.getLastResult();
			assertEquals(expectedRes, res);

			/* if et else : vrai if */
			this.sdc.executeLine("true true & if 2 else 5 ");
			expectedRes = "2";
			res = this.sdc.getLastResult();
			assertEquals(expectedRes, res);

			/* if et else : vrai else */
			this.sdc.executeLine("true false & if 2 else 5 ");
			expectedRes = "5";
			res = this.sdc.getLastResult();
			assertEquals(expectedRes, res);

			/* if et else imbriqués */
			this.sdc.executeLine(
					"true false & if 4 5 < if 9999 else 9 15 > if 8888 endif endif else 3 3 = if 7777 else 6666 endif");
			expectedRes = "7777";
			res = this.sdc.getLastResult();
			assertEquals(expectedRes, res);

			/* if et else imbriqués avec variables et commentaires */
			String code = "; petit programme pour determiner le nombre de racines d’un polynome\n"
					+ "; du 2eme degre: A X^2 + B X + C\n" + ";\n"
					+ "; entrez ci dessous les 3 cœfficients A B C dans cet ordre\n" + "14#2 10#2 -13#2\n"
					+ "; le programme commence ici\n" + "=> C ; C vaut -6.5\n" + "=> B ; B vaut 5.0\n"
					+ "=> A ; A vaut 7.0\n" + "$B $B * 8#2 $A $C * * - => Delta ; Delta = B*B - 4*A*C\n"
					+ "0#1 $Delta = if\n" + "1 ; Le polynome a une seule racine\n" + "else\n" + "0#1 $Delta < if\n"
					+ "2 ; Le polynome a 2 racines distinctes\n" + "else\n"
					+ "0 ; Le polynome n’a pas de racines reelles\n" + "endif\n" + "endif\n";
			this.sdc.executeLine(code);
			expectedRes = "2";
			res = this.sdc.getLastResult();
			assertEquals(expectedRes, res);

		} catch (Exception e) {
			System.out.println(e);
			assertEquals(true, false);
		}
	}

	public void testConditionException() {

		try {
			this.sdc.executeLine("true if 3");
			assertTrue(true);
			try {
				this.sdc.executeLine("5 if 3");
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
