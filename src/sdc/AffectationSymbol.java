package sdc;

import java.util.Map;
import java.util.Stack;

public class AffectationSymbol implements Symbol {

	@Override
	public boolean parse(String s) {
		return s.equals("=>");
	}

	@Override
	public void execute(Stack<Value> s, Map<String, Value> store) {
		// System.out.println("dump");
		Value v = s.pop();
		store.put("tmp", v);
	}
}
