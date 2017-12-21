package sdc.operation;

import sdc.exception.IncompatibleTypeException;
import sdc.exception.NumericException;
import sdc.value.NumericValue;
import sdc.value.TypeValue;
import sdc.value.Value;

public class MulOperation extends BinaryOperation {

	@Override
	public boolean parse(String s) {
		return s.equals("*");
	}

	@Override
	public Value compute(Value v1, Value v2) throws IncompatibleTypeException, NumericException {
		TypeValue.NUMERIC.check(v1, v2);
		NumericValue val1 = (NumericValue) v1;
		NumericValue val2 = (NumericValue) v2;

		return val1.multiply(val2);
	}

}
