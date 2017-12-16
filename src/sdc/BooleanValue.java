package sdc;

import sdc.exceptions.IncompatibleTypeException;

public class BooleanValue extends Value {

	private boolean predicate;

	public BooleanValue() {
		this(true);
	}

	public BooleanValue(boolean predicate) {
		this.predicate = predicate;
	}

	public boolean parse(String s) {
		if (s.equals("true")) {
			this.predicate = true;
			return true;
		} else if (s.equals("false")) {
			this.predicate = false;
			return true;
		}
		return false;
	}

	public String toString() {
		return "" + this.predicate;
	}

	public BooleanValue not() {
		// attention à bien créer une nouvelle instance
		return new BooleanValue(!this.predicate);
	}

	public Value or(Value v) throws IncompatibleTypeException {

		if (!(v instanceof BooleanValue)) {
			throw new IncompatibleTypeException();
		}
		return new BooleanValue(this.predicate || ((BooleanValue) v).predicate);
	}

	public Value and(Value v) throws IncompatibleTypeException {

		if (!(v instanceof BooleanValue)) {
			throw new IncompatibleTypeException();
		}
		return new BooleanValue(this.predicate && ((BooleanValue) v).predicate);
	}

}
