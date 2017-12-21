package sdc.operation;

import sdc.exception.IncompatibleTypeException;
import sdc.value.NumericValue;
import sdc.value.TypeValue;
import sdc.value.Value;

public class AbsoluteValueOperation extends UnaryOperation {

	@Override
	public boolean parse(String s) {
		return s.equals("||");
	}

	@Override
	public Value compute(Value v) throws IncompatibleTypeException {
		TypeValue.NUMERIC.check(v);
		NumericValue val = (NumericValue) v;

		return val.abs();
	}

}
