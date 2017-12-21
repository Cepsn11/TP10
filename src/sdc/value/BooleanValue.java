package sdc.value;

public class BooleanValue extends Value {

	private boolean value;

	public BooleanValue() {
		this.value = false;
	}

	public BooleanValue(boolean value) {
		this.value = value;
	}

	@Override
	public boolean parse(String s) {
		if (!s.equals("true") && !s.equals("false")) {
			return false;
		}
		this.value = Boolean.parseBoolean(s);
		return true;
	}

	public boolean isTrue() {
		return this.value;
	}

	public BooleanValue and(BooleanValue v) {
		return new BooleanValue(this.value && v.value);
	}

	public BooleanValue or(BooleanValue v) {
		return new BooleanValue(this.value || v.value);
	}

	public BooleanValue not() {
		return new BooleanValue(!this.value);
	}

	@Override
	public String toString() {
		return "" + this.value;
	}

}
