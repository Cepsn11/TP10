package sdc;

import java.util.Stack;

public abstract class Value implements Symbol {

	public void execute(Stack<Value> s) {
		s.push(this);
	}
}
