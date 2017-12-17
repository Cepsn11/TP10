package sdc;

import java.util.Map;
import java.util.Stack;

import sdc.exceptions.IncompatibleTypeException;
import sdc.exceptions.ShutdownException;

public class Variable implements Symbol {

	private String name;
	private Value value;

	public Variable() {
		this.name = "toto";
		this.value = new IntegerValue(0);
	}

	public Variable(Value value) {
		this.name = "toto";
		this.value = value;
	}

	public Variable(String name, Value value) {
		this.name = name;
		this.value = value;
	}

	public void pushValue(Stack<Value> s) {
		s.push(this.value);
	}

	@Override
	public boolean parse(String s) {
		return s.startsWith("$");
	}

	@Override
	public void execute(Stack<Value> s, Map<String, Value> store) throws ShutdownException, IncompatibleTypeException {
		pushValue(s);
	}
}
