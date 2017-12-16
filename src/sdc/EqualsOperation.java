package sdc;

import sdc.exceptions.IncompatibleTypeException;

public class EqualsOperation extends BinaryOperation {

	public boolean parse(String s) {
		return s.equals("=");
	}

	public BooleanValue compute(Value v1, Value v2) throws IncompatibleTypeException {
		return ((NumericValue) v1).equals((NumericValue) v2);
	}

}
