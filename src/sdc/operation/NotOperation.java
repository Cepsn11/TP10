package sdc.operation;

import sdc.exception.IncompatibleTypeException;
import sdc.value.BooleanValue;
import sdc.value.TypeValue;
import sdc.value.Value;

public class NotOperation extends UnaryOperation {

	@Override
	public boolean parse(String s) {
		return s.equals("~");
	}

	@Override
	public Value compute(Value v) throws IncompatibleTypeException {
		TypeValue.BOOLEAN.check(v);
		BooleanValue val1 = (BooleanValue) v;

		return val1.not();
	}
}
