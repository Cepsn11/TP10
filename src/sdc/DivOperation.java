package sdc;

import sdc.exceptions.IncompatibleTypeException;

public class DivOperation extends BinaryOperation {

	public boolean parse(String s) {
		return s.equals("/");
	}

	public NumericValue compute(Value v1, Value v2) throws IncompatibleTypeException {
		return ((NumericValue) v1).divide((NumericValue) v2);
	}
}
