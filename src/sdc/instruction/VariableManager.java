package sdc.instruction;

import java.util.HashMap;

import sdc.exception.VariableException;
import sdc.value.Value;

public class VariableManager {

	private static final String REGEX_VARIABLE = "(?i)[a-z][a-z0-9_]*";

	private static final HashMap<String, Value> variables = new HashMap<>();

	protected static void checkVariableName(String name) throws VariableException {
		if (!name.matches(REGEX_VARIABLE)) {
			throw new VariableException("an affectation requires a valid variable name");
		}
	}

	protected static void setVariable(String name, Value value) {
		variables.put(name, value);
	}

	protected static Value getVariableValue(String name) throws VariableException {
		Value res = variables.get(name);
		
		if (res == null) {
			throw new VariableException("unknown variable");
		}
		return res;
	}

}
