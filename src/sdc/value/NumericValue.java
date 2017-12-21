package sdc.value;

import sdc.exception.IncompatibleTypeException;
import sdc.exception.NumericException;

public abstract class NumericValue extends Value {

	public abstract NumericValue multiply(NumericValue v) throws IncompatibleTypeException;

	public abstract NumericValue divide(NumericValue v) throws IncompatibleTypeException, NumericException;

	public abstract NumericValue add(NumericValue v) throws IncompatibleTypeException;

	public abstract NumericValue substract(NumericValue v) throws IncompatibleTypeException;

	public abstract NumericValue abs();

	public abstract BooleanValue greaterThan(NumericValue v) throws IncompatibleTypeException;

	public abstract BooleanValue smallerThan(NumericValue v) throws IncompatibleTypeException;

	public abstract BooleanValue equalsTo(NumericValue v) throws IncompatibleTypeException;

}
