package sdc;

public class BooleanValue extends Value {

	private boolean predicate;

	public BooleanValue() {
		this(true);
	}

	public BooleanValue(boolean predicate) {
		this.predicate = predicate;
	}

	public boolean parse(String s) {
		try {
			this.predicate = Boolean.parseBoolean(s);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
}
