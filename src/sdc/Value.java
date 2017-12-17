package sdc;

import java.util.Map;
import java.util.Stack;

public abstract class Value implements Symbol {

	public void execute(Stack<Value> s, Map<String, Value> store) {
		s.push(this);
	}
}
