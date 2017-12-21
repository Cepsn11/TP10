package sdc.control;

import java.util.Optional;
import java.util.Stack;
import java.util.StringTokenizer;

import sdc.SDC;
import sdc.Symbol;
import sdc.value.Value;

public class ClearSymbol implements Symbol {

	@Override
	public boolean parse(String s) {
		return s.equals("clear");
	}

	@Override
	public void execute(Stack<Value> s, Optional<SDC> sdcOp, Optional<StringTokenizer> stOp) {
		s.clear();
	}

}