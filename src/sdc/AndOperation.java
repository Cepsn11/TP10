package sdc;

import java.util.Stack;

import sdc.exceptions.IncompatibleTypeException;
import sdc.exceptions.ShutdownException;

public class AndOperation extends BinaryOperation {

	public boolean parse(String s) {
		return s.equals("&");
	}

	public Value compute(Value v1, Value v2) throws IncompatibleTypeException {
		return ((BooleanValue) v1).and((BooleanValue) v2);
	}

}
