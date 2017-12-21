package sdc.instruction;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Stack;
import java.util.StringTokenizer;

import sdc.SDC;
import sdc.Symbol;
import sdc.exception.VariableException;
import sdc.value.Value;

public class AffectationInstruction implements Symbol {

	@Override
	public boolean parse(String s) {
		return s.equals("=>");
	}

	@Override
	public void execute(Stack<Value> s, Optional<SDC> sdcOp, Optional<StringTokenizer> stOp)
			throws VariableException {
		StringTokenizer st = stOp.isPresent() ? stOp.get() : null;
		String name;

		try {
			name = st.nextToken();
		} catch (NoSuchElementException e) {
			throw new VariableException("an affectation requires a variable");
		}
		name = name.toLowerCase();
		Value val = s.pop();
		VariableManager.checkVariableName(name);
		VariableManager.setVariable(name, val);
	}

}
