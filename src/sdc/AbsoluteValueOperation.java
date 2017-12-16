package sdc;

import sdc.exceptions.IncompatibleTypeException;

public class AbsoluteValueOperation extends UnaryOperation {

	@Override
	public boolean parse(String s) {
		return s.equals("||");
	}

	@Override
	public Value compute(Value v) throws IncompatibleTypeException {
		return v.abs();
	}

}
