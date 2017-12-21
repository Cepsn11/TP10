package sdc.instruction;

import java.util.Optional;
import java.util.Stack;
import java.util.StringTokenizer;

import sdc.SDC;
import sdc.Symbol;
import sdc.exception.VariableException;
import sdc.value.Value;

public class Variable implements Symbol {

	private String name;
	private Value value;

	@Override
	public boolean parse(String s) {
		if (!s.startsWith("$")) {
			return false;
		}
		this.name = s.substring(1).toLowerCase();
		return true;
	}

	@Override
	public void execute(Stack<Value> s, Optional<SDC> sdcOp, Optional<StringTokenizer> stOp)
			throws VariableException {
		VariableManager.checkVariableName(this.name);
		this.value = VariableManager.getVariableValue(this.name);
		s.push(this.value);
	}
}
