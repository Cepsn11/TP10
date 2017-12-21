package sdc.value;

import sdc.exception.IncompatibleTypeException;
import sdc.exception.NumericException;

public class IntegerValue extends NumericValue {

	private int value;

	public IntegerValue() {
		this(0);
	}

	public IntegerValue(int value) {
		this.value = value;
	}

	@Override
	public boolean parse(String s) {
		try {
			this.value = Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "" + this.value;
	}

	@Override
	public NumericValue add(NumericValue v) throws IncompatibleTypeException {
		TypeValue.checkSameType(this, v);
		return new IntegerValue(((IntegerValue) v).value + this.value);
	}

	@Override
	public NumericValue multiply(NumericValue v) throws IncompatibleTypeException {
		TypeValue.checkSameType(this, v);
		return new IntegerValue(((IntegerValue) v).value * this.value);
	}

	@Override
	public NumericValue divide(NumericValue v) throws IncompatibleTypeException, NumericException {
		TypeValue.checkSameType(this, v);
		try {
			return new IntegerValue(((IntegerValue) v).value / this.value);
		} catch (ArithmeticException e) {
			throw new NumericException(e.getMessage());
		}
	}

	@Override
	public NumericValue substract(NumericValue v) throws IncompatibleTypeException {
		TypeValue.checkSameType(this, v);
		return new IntegerValue(((IntegerValue) v).value - this.value);
	}

	@Override
	public NumericValue abs() {
		// attention à bien créer une nouvelle instance
		if (this.value < 0) {
			return new IntegerValue(-1 * this.value);
		} else {
			return new IntegerValue(this.value);
		}
	}

	@Override
	public BooleanValue greaterThan(NumericValue v) throws IncompatibleTypeException {
		TypeValue.checkSameType(this, v);
		return (this.value < ((IntegerValue) v).value) ? new BooleanValue(true) : new BooleanValue(false);

	}

	@Override
	public BooleanValue smallerThan(NumericValue v) throws IncompatibleTypeException {
		TypeValue.checkSameType(this, v);
		return ((IntegerValue) v).greaterThan(this);
	}

	@Override
	public BooleanValue equalsTo(NumericValue v) throws IncompatibleTypeException {
		TypeValue.checkSameType(this, v);
		return new BooleanValue(this.value == ((IntegerValue) v).value);
	}

}
