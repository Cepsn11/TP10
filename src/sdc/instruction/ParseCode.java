package sdc.instruction;

import java.util.StringTokenizer;

public class ParseCode {

	private StringTokenizer stk;

	public ParseCode(StringTokenizer stk) {
		this.stk = stk;
	}

	public String[] buildBlocksIfElse() {
		String[] res = { "", "" }; 				// res[0] : bloc if / res[1] : bloc else
		int countIf = 0;
		int iBlock = 0;

		while (this.stk.hasMoreTokens()) {
			String token = this.stk.nextToken();
			if (token.equals("if")) {
				countIf += 1;
			}
			if (token.equals("else") && countIf == 0) {
				iBlock = 1;
				continue;
			}
			if (token.equals("endif") && countIf == 0) {
				break;
			}
			if (token.equals("endif")) {
				countIf -= 1;
			}
			res[iBlock] += " " + token;
		}
		res[0] = res[0].trim();
		res[1] = res[1].trim();
		return res;
	}

	/*
	 * Exemple de méthode si on voulait ajouter l'instruction for à la calculatrice
	 * 
	 * public String[] buildInstructionFor() {
	 * 
	 * }
	 * 
	 * public String[] buildBlockFor() {
	 * 
	 * }
	 * 
	 */

}
