package sdc;

import sdc.exceptions.*;
import java.util.Stack;

import sdc.exceptions.IncompatibleTypeException;

public abstract class Value implements Symbol {

    public void execute(Stack<Value> s) {
	s.push(this);
    }

    public abstract Value multiply(Value v) throws IncompatibleTypeException;
    public abstract Value divide(Value v) throws IncompatibleTypeException;
    public abstract Value add(Value v) throws IncompatibleTypeException;
    public abstract Value substract(Value v) throws IncompatibleTypeException;
    public abstract Value abs();

}
