package sdc;

import java.util.Stack;

import sdc.exceptions.IncompatibleTypeException;
import sdc.exceptions.ShutdownException;

public class Boolean implements Symbol {

	@Override
	public boolean parse(String s) {
		return s.equals("true") || s.equals("false");
	}
		

	@Override
	public void execute(Stack<Value> s) throws ShutdownException, IncompatibleTypeException {
		// TODO Auto-generated method stub
		
	}

}
