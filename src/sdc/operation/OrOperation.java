package sdc.operation;

import sdc.exception.IncompatibleTypeException;
import sdc.value.BooleanValue;
import sdc.value.TypeValue;
import sdc.value.Value;

public class OrOperation extends BinaryOperation {

	@Override
	public boolean parse(String s) {
		return s.equals("|");
	}

	@Override
	public Value compute(Value v1, Value v2) throws IncompatibleTypeException {
		TypeValue.BOOLEAN.check(v1, v2);
		BooleanValue val1 = (BooleanValue) v1;
		BooleanValue val2 = (BooleanValue) v2;

		return val1.or(val2);
	}
}
