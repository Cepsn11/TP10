package sdc.control;


import java.util.Optional;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.concurrent.atomic.AtomicInteger;

import sdc.SDC;
import sdc.Symbol;
import sdc.value.Value;

public class ViewSymbol implements Symbol {

	@Override
	public boolean parse(String s) {
		return s.equals("view");

	}

	@Override
	public void execute(Stack<Value> s, Optional<SDC> sdcOp, Optional<StringTokenizer> stOp) {
		AtomicInteger counter = new AtomicInteger(s.size());
		s.stream().forEach(v -> System.out.println(counter.getAndDecrement() + " ----> " + v));
	}

}
