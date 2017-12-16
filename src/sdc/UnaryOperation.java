package sdc;

import java.util.Stack;

import sdc.exceptions.IncompatibleTypeException;

public abstract class UnaryOperation implements Symbol {

	public abstract Value compute(Value v) throws IncompatibleTypeException;

	@Override
	public void execute(Stack<Value> s) throws IncompatibleTypeException {
		Value v = s.pop();
		s.push(this.compute(v));

	}

}
