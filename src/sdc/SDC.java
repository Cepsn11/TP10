package sdc;

import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;

import sdc.exceptions.IncompatibleTypeException;
import sdc.exceptions.ShutdownException;
import sdc.exceptions.StackException;
import sdc.exceptions.SymbolNotFoundException;

public class SDC {

	private Factory factory;
	private Stack<Value> stackValue;
	private Map store;

	public SDC() {
		this.factory = new Factory();
		this.stackValue = new Stack<Value>();
		this.store = new HashMap<String, Value>();
	}

	public void executeLine(String line) throws ShutdownException, InternalError, IncompatibleTypeException,
			StackException, SymbolNotFoundException {
		// main method:

		// parse the line to execute
		// tokens are separated by a space
		StringTokenizer st = new StringTokenizer(line);
		while (st.hasMoreTokens()) {

			// read the next token
			String token = st.nextToken();

			// try every registered symbole
			boolean found = false;
			for (Symbol s : this.factory.registered()) {
				if (s.parse(token)) {
					found = true;
					Stack<Value> oldStack = (Stack<Value>) stackValue.clone();
					try {

						s.execute(this.stackValue, this.store);
					} catch (EmptyStackException e) {
						// we might have read some symbols from the stack
						// roll back
						this.stackValue = oldStack;
						throw new StackException("Empty stack --- aborting last operations");
					} catch (IncompatibleTypeException e) {
						// we might have read some symbols from the stack
						// roll back
						this.stackValue = oldStack;
						throw new IncompatibleTypeException(
								"Illegal operations: values must have the same type --- aborting last operations");
					}
					break;
				}
				// continue to the next symbol
			}

			if (!found) {
				throw new SymbolNotFoundException("the token " + token + " has not been recognized. Abort");
			}

		}

	}

	public String getLastResult() {
		try {
			Value v = this.stackValue.peek();
			return v.toString();
		} catch (EmptyStackException e) {
			return "";
		}
	}

}
