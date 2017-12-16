package sdc;

import sdc.exceptions.IncompatibleTypeException;

public class MulOperation extends BinaryOperation {

	public boolean parse(String s) {
		return s.equals("*");
	}

	public Value compute(Value v1, Value v2) throws IncompatibleTypeException {
		return ((NumericValue) v1).multiply((NumericValue) v2);
	}

}
