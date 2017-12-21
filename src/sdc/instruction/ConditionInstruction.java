package sdc.instruction;

import java.util.Optional;
import java.util.Stack;
import java.util.StringTokenizer;

import sdc.SDC;
import sdc.Symbol;
import sdc.exception.ConditionException;
import sdc.exception.IncompatibleTypeException;
import sdc.exception.ProcessingException;
import sdc.exception.ShutdownException;
import sdc.value.BooleanValue;
import sdc.value.TypeValue;
import sdc.value.Value;

public class ConditionInstruction implements Symbol {

	@Override
	public boolean parse(String s) {
		return s.equals("if");
	}

	@Override
	public void execute(Stack<Value> s, Optional<SDC> sdcOp, Optional<StringTokenizer> stOp)
			throws ShutdownException, ProcessingException, ConditionException {
		SDC sdc = sdcOp.isPresent() ? sdcOp.get() : null;
		StringTokenizer st = stOp.isPresent() ? stOp.get() : null;
		Value val = s.pop();

		try {
			TypeValue.BOOLEAN.check(val);
		} catch (IncompatibleTypeException e) {
			throw new ConditionException("a condition can only evaluate a boolean value");
		}

		BooleanValue bool = (BooleanValue) val;
		ParseCode parseCode = new ParseCode(st);
		String[] blocksIfElse = parseCode.buildBlocksIfElse();

		if (bool.isTrue()) {
			sdc.executeLine(blocksIfElse[0]);
			return;
		}
		if (!blocksIfElse[1].isEmpty()) {
			sdc.executeLine(blocksIfElse[1]);
		}
	}

}
