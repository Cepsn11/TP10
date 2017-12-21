package sdc.operation;

import java.util.Optional;
import java.util.Stack;
import java.util.StringTokenizer;

import sdc.SDC;
import sdc.Symbol;
import sdc.exception.IncompatibleTypeException;
import sdc.exception.NumericException;
import sdc.exception.StackException;
import sdc.value.Value;

public abstract class UnaryOperation implements Symbol {

	public abstract Value compute(Value v) throws IncompatibleTypeException;

	@Override
	public void execute(Stack<Value> s, Optional<SDC> sdcOp, Optional<StringTokenizer> stOp)
			throws StackException, IncompatibleTypeException, NumericException {
		Value v = s.pop();
		s.push(this.compute(v));

	}

}
