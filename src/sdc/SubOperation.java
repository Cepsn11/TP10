package sdc;

import java.util.Stack;

public class SubOperation extends BinaryOperation {

    public boolean parse(String s) {
	return s.equals("-");
    }
    
    public Value compute(Value v1, Value v2) throws IncompatibleTypeException {
	
	return v1.substract(v2);
	
    }

}
