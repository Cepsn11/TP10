package sdc;

import sdc.exceptions.IncompatibleTypeException;

public class IntegerValue extends NumericValue {

	private int value;

	public IntegerValue() {
		this(0);
	}

	public IntegerValue(int value) {
		this.value = value;
	}

	public boolean parse(String s) {
		try {
			this.value = Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	public String toString() {
		return "" + this.value;
	}

	public NumericValue add(NumericValue v) throws IncompatibleTypeException {

		if (!(v instanceof IntegerValue)) {
			throw new IncompatibleTypeException();
		}

		return new IntegerValue(((IntegerValue) v).value + this.value);
	}

	public NumericValue multiply(NumericValue v) throws IncompatibleTypeException {

		if (!(v instanceof IntegerValue)) {
			throw new IncompatibleTypeException();
		}

		return new IntegerValue(((IntegerValue) v).value * this.value);
	}

	public NumericValue divide(NumericValue v) throws IncompatibleTypeException {

		if (!(v instanceof IntegerValue)) {
			throw new IncompatibleTypeException();
		}

		return new IntegerValue(((IntegerValue) v).value / this.value);
	}

	public NumericValue substract(NumericValue v) throws IncompatibleTypeException {
		if (!(v instanceof IntegerValue)) {
			throw new IncompatibleTypeException();
		}

		return new IntegerValue(((IntegerValue) v).value - this.value);
	}

	public NumericValue abs() {
		// attention à bien créer une nouvelle instance
		if (this.value < 0) {
			return new IntegerValue(-1 * this.value);
		} else {
			return new IntegerValue(this.value);
		}
	}

	public BooleanValue greaterThan(NumericValue v) {
		if (this.value < ((IntegerValue) v).value) {
			return new BooleanValue(true);
		} else {
			return new BooleanValue(false);
		}
	}

	public BooleanValue smallerThan(NumericValue v) {
		return ((IntegerValue) v).greaterThan(this);
	}

	public BooleanValue equals(NumericValue v) {
		if (!Boolean.parseBoolean(this.greaterThan(v).toString())
				&& !Boolean.parseBoolean(this.smallerThan(v).toString())) {
			return new BooleanValue(true);
		} else {
			return new BooleanValue(false);
		}
	}
}
