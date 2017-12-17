package sdc;

import java.util.Stack;

import sdc.exceptions.IncompatibleTypeException;
import sdc.exceptions.ShutdownException;

public class AbsoluteValueOperation extends UnaryOperation {

	@Override
	public boolean parse(String s) {
		return s.equals("||");
	}

	@Override
	public Value compute(Value v) throws IncompatibleTypeException {
		return ((NumericValue) v).abs();
	}
}
