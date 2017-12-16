package sdc;

import sdc.exceptions.IncompatibleTypeException;

public abstract class NumericValue extends Value {
	public abstract NumericValue multiply(NumericValue v) throws IncompatibleTypeException;

	public abstract NumericValue divide(NumericValue v) throws IncompatibleTypeException;

	public abstract NumericValue add(NumericValue v) throws IncompatibleTypeException;

	public abstract NumericValue substract(NumericValue v) throws IncompatibleTypeException;

	public abstract NumericValue abs();
}
