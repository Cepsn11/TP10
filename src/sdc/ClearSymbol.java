package sdc;

import java.util.Stack;

import sdc.exceptions.ShutdownException;

public class ClearSymbol implements Symbol {
    
    public boolean parse(String s) {
	return s.equals("clear");
    }
    
    public void execute(Stack<Value> s) throws ShutdownException {
	s.clear();
    }

}