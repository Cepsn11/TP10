package sdc;

import java.util.Stack;

public class ViewSymbol implements Symbol {

	public boolean parse(String s) {
		return s.equals("view");
	}

	public void execute(Stack<Value> s) throws ShutdownException {
		int compteur = s.size();
		String display = "";
		for (Value number : s) {

			display += compteur-- + " ----> " + number + "\n";

		}
		System.out.println(display);
	}
}