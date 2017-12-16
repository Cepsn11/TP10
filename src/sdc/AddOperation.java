package sdc;

import java.util.Stack;

public class AddOperation extends BinaryOperation {

	public void execute(Stack<Value> s) throws IncompatibleTypeException {
		NumericValue v1 = (NumericValue) s.pop();
		NumericValue v2 = (NumericValue) s.pop();

		s.push(this.compute(v1, v2));
	}

	public boolean parse(String s) {
		return s.equals("+");
	}

	public Value compute(Value v1, Value v2) throws IncompatibleTypeException {
		return ((NumericValue) v1).add((NumericValue) v2);
	}
}
