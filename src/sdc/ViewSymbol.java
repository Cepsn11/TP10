package sdc;

import java.util.Map;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;

import sdc.exceptions.IncompatibleTypeException;
import sdc.exceptions.ShutdownException;

public class ViewSymbol implements Symbol {

	@Override
	public boolean parse(String s) {
		return s.equals("view");
	}

	@Override
	public void execute(Stack<Value> s, Map<String, Value> store) throws ShutdownException, IncompatibleTypeException {
		AtomicInteger counter = new AtomicInteger(s.size());
		s.stream().forEach(v -> System.out.println(counter.getAndDecrement() + " ----> " + v));
	}

}
