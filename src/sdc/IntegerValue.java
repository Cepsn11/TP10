package sdc;

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

}
