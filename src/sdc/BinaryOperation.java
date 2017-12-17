package sdc;

import java.util.Map;
import java.util.Stack;

import sdc.exceptions.IncompatibleTypeException;

public abstract class BinaryOperation implements Symbol {

	public abstract Value compute(Value v1, Value v2) throws IncompatibleTypeException;

	public void execute(Stack<Value> s, Map<String, Value> store) throws IncompatibleTypeException {
		Value v1 = s.pop();
		Value v2 = s.pop();

		s.push(this.compute(v1, v2));
	}

}
