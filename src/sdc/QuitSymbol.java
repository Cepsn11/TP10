package sdc;

import java.util.Map;
import java.util.Stack;

import sdc.exceptions.ShutdownException;

public class QuitSymbol implements Symbol {

	public boolean parse(String s) {
		return s.equals("quit");
	}

	public void execute(Stack<Value> s, Map<String, Value> store) throws ShutdownException {
		throw new ShutdownException();
	}

}