package sdc;

import sdc.exceptions.IncompatibleTypeException;

public class OrOperation extends BinaryOperation {

	public boolean parse(String s) {
		return s.equals("|");
	}

	public Value compute(Value v1, Value v2) throws IncompatibleTypeException {
		return ((BooleanValue) v1).or((BooleanValue) v2);
	}
}
