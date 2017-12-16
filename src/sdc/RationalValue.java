package sdc;

import sdc.exceptions.IncompatibleTypeException;

public class RationalValue extends Value{

	private int numerator;
	private int denominator;
	
	public RationalValue() {
		this(0, 0);
	}

	public RationalValue(int numerator, int denominator) {
		this.numerator = numerator;
		this.denominator = denominator;
	}
	
	@Override
	public boolean parse(String s) {
		String[] nombres = s.split("#");
		if(nombres.length != 2){
			return false;
		}
		try {
		    this.numerator = Integer.parseInt(nombres[0]);
		    this.denominator = Integer.parseInt(nombres[1]);
		} catch (NumberFormatException e) {
		    return false;
		}
		return true;
	}

	@Override
	public Value multiply(Value v) throws IncompatibleTypeException {
		if (!(v instanceof RationalValue)) {
		    throw new IncompatibleTypeException();
		}
		RationalValue valeur = (RationalValue) v;
		return new RationalValue(valeur.numerator * this.numerator, valeur.denominator * this.denominator);
	}

	@Override
	public Value divide(Value v) throws IncompatibleTypeException {
		if (!(v instanceof RationalValue)) {
		    throw new IncompatibleTypeException();
		}
		RationalValue valeur = (RationalValue) v;
		RationalValue inverse = new RationalValue(this.denominator, this.numerator);
		return valeur.multiply(inverse);
	}

	@Override
	public Value add(Value v) throws IncompatibleTypeException {
		if (!(v instanceof RationalValue)) {
		    throw new IncompatibleTypeException();
		}
		RationalValue valeur = (RationalValue) v;
		int numerator = (this.numerator * valeur.denominator) + (valeur.numerator * this.denominator);
		int denominator = this.denominator * valeur.denominator;
		return new RationalValue(numerator, denominator);
	}

	@Override
	public Value substract(Value v) throws IncompatibleTypeException {
		if (!(v instanceof RationalValue)) {
			throw new IncompatibleTypeException();
		}
		RationalValue valeur = (RationalValue) v;
		int numerator = (this.numerator * valeur.denominator) - (valeur.numerator * this.denominator);
		int denominator = this.denominator * valeur.denominator;
		return new RationalValue(numerator, denominator);
	}

	@Override
	public Value abs() {
		if (this.numerator < 0 && this.denominator > 0) {
		    return new RationalValue(-1 * this.numerator, this.denominator);
		}
		if(this.denominator < 0 && this.numerator > 0){
		    return new RationalValue(this.numerator, -1 * this.denominator);
		}
		return new RationalValue(this.numerator, this.denominator);
	}
	
	@Override
	public String toString(){
		return this.numerator+"#"+this.denominator;
	}
	
	@Override
	public boolean equals(Object obj) {
		return false;
	}

	
}
