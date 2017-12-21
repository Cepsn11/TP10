package sdc.value;

import sdc.SDC;
import sdc.Symbol;

import java.util.Optional;
import java.util.Stack;
import java.util.StringTokenizer;

public abstract class Value implements Symbol {

	@Override
	public void execute(Stack<Value> s, Optional<SDC> sdcOp, Optional<StringTokenizer> stOp) {
		s.push(this);
	}

}
