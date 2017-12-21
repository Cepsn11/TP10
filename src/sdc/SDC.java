package sdc;

import java.util.StringTokenizer;

import sdc.exception.ConditionException;
import sdc.exception.IncompatibleTypeException;
import sdc.exception.NumericException;
import sdc.exception.ProcessingException;
import sdc.exception.ShutdownException;
import sdc.exception.StackException;
import sdc.exception.SymbolNotFoundException;
import sdc.exception.VariableException;
import sdc.value.Value;

import java.util.Stack;
import java.util.EmptyStackException;
import java.util.Optional;

public class SDC {

	private Factory factory;
	private Stack<Value> stack;

	public SDC() {
		this.factory = new Factory();
		this.stack = new Stack<Value>();
	}

	public void executeLine(String line) throws ShutdownException, ProcessingException {

		// Supprimer commentaires ";"
		line = removeComments(line);

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

					Stack<Value> oldStack = (Stack<Value>) stack.clone();
					try {
						s.execute(this.stack, Optional.of(this), Optional.of(st));
					} catch (EmptyStackException e) {
						// we might have read some symbols from the stack
						// roll back
						this.stack = oldStack;
						throw new StackException("Empty stack --- aborting last operations");
					} catch (IncompatibleTypeException e) {
						this.stack = oldStack;
						throw new IncompatibleTypeException(
								"Illegal operations: " + e.getMessage() + " --- aborting last operations");
					} catch (NumericException e) {
						this.stack = oldStack;
						throw new NumericException(
								"Illegal operations: " + e.getMessage() + " --- aborting last operations");
					} catch (VariableException e) {
						this.stack = oldStack;
						throw new VariableException(
								"Illegal operations: " + e.getMessage() + " --- aborting last operations");
					} catch (ConditionException e) {
						this.stack = oldStack;
						throw new ConditionException(
								"Illegal operations: " + e.getMessage() + " --- aborting last operations");
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

	public static String removeComments(String code) {
		String res = "";

		for (String line : code.split("\n")) {
			int i = line.indexOf(";");
			res += (i != -1) ? line.substring(0, i) : line;
			res += " ";
		}
		return res.trim();
	}

	public String getLastResult() {
		try {
			Value v = this.stack.peek();
			return v.toString();
		} catch (EmptyStackException e) {
			return "";
		}
	}

}
