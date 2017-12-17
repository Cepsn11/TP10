package sdc;

import sdc.exceptions.IncompatibleTypeException;

public class SubOperation extends BinaryOperation {

	public boolean parse(String s) {
		return s.equals("-");
	}

	public Value compute(Value v1, Value v2) throws IncompatibleTypeException {
		return ((NumericValue) v1).substract((NumericValue) v2);
	}
}
