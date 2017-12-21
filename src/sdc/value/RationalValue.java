package sdc.value;

import sdc.exception.IncompatibleTypeException;
import sdc.exception.NumericException;

public class RationalValue extends NumericValue {

	private int numerator;
	private int denominator;

	public RationalValue() {
		this(0, 1);
	}

	public RationalValue(int numerator, int denominator) {
		this.numerator = numerator;
		this.denominator = denominator;
	}

	@Override
	public boolean parse(String s) {
		String[] nombres = s.split("#");

		if (nombres.length != 2) {
			return false;
		}
		try {
			this.numerator = Integer.parseInt(nombres[0]);
			this.denominator = Integer.parseInt(nombres[1]);
			if (denominator == 0) {
				return false;
			}
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	@Override
	public NumericValue multiply(NumericValue v) throws IncompatibleTypeException {
		TypeValue.checkSameType(this, v);
		RationalValue valeur = (RationalValue) v;

		return new RationalValue(valeur.numerator * this.numerator, valeur.denominator * this.denominator);
	}

	@Override
	public NumericValue divide(NumericValue v) throws IncompatibleTypeException, NumericException {
		TypeValue.checkSameType(this, v);
		try {
			RationalValue valeur = (RationalValue) v;

			return new RationalValue(valeur.numerator / this.numerator, valeur.denominator / this.denominator);
		} catch (ArithmeticException e) {
			throw new NumericException(e.getMessage());
		}

	}

	@Override
	public NumericValue add(NumericValue v) throws IncompatibleTypeException {
		TypeValue.checkSameType(this, v);
		RationalValue valeur = (RationalValue) v;

		if (this.denominator == valeur.denominator) {
			return new RationalValue(valeur.numerator + this.numerator, this.denominator);
		}
		int numerator = (this.numerator * valeur.denominator) + (valeur.numerator * this.denominator);
		int denominator = this.denominator * valeur.denominator;
		return new RationalValue(numerator, denominator);
	}

	@Override
	public NumericValue substract(NumericValue v) throws IncompatibleTypeException {
		TypeValue.checkSameType(this, v);
		RationalValue valeur = (RationalValue) v;

		if (this.denominator == valeur.denominator) {
			return new RationalValue(valeur.numerator - this.numerator, this.denominator);
		}
		int numerator = (valeur.numerator * this.denominator) - (this.numerator * valeur.denominator);
		int denominator = this.denominator * valeur.denominator;

		return new RationalValue(numerator, denominator);
	}

	@Override
	public NumericValue abs() {
		if (this.numerator < 0 && this.denominator > 0) {
			return new RationalValue(-1 * this.numerator, this.denominator);
		}
		if (this.denominator < 0 && this.numerator > 0) {
			return new RationalValue(this.numerator, -1 * this.denominator);
		}
		return new RationalValue(this.numerator, this.denominator);
	}

	/*
	 * //Méthode de classe, calcule le pgcd pour créer la forme réduite d'une
	 * fraction. private static int pgcd(int a, int b) { BigInteger b1 =
	 * BigInteger.valueOf(a); BigInteger b2 = BigInteger.valueOf(b); BigInteger gcd
	 * = b1.gcd(b2); return gcd.intValue(); }
	 * 
	 * // Créer la forme réduite d'une fraction public RationalValue
	 * getReducedFraction() { int numdim; int dendim; int r; r =
	 * pgcd(this.numerator,this.denominator); numdim = this.numerator/r; dendim =
	 * this.denominator/r; //pas de - au dénominateur if(dendim < 0) { numdim =
	 * -numdim; dendim = -dendim; }
	 * 
	 * return new RationalValue(numdim, dendim); }
	 */

	@Override
	public String toString() {
		return this.numerator + "#" + this.denominator;
	}

	@Override
	public boolean equals(Object obj) {
		return false;
	}

	@Override
	public BooleanValue greaterThan(NumericValue v) throws IncompatibleTypeException {
		TypeValue.checkSameType(this, v);
		int lhs = this.numerator * ((RationalValue) v).denominator;
		int rhs = this.denominator * ((RationalValue) v).numerator;

		return (lhs < rhs) ? new BooleanValue(true) : new BooleanValue(false);
	}

	@Override
	public BooleanValue smallerThan(NumericValue v) throws IncompatibleTypeException {
		TypeValue.checkSameType(this, v);
		return ((RationalValue) v).greaterThan(this);
	}

	@Override
	public BooleanValue equalsTo(NumericValue v) throws IncompatibleTypeException {
		TypeValue.checkSameType(this, v);
		BooleanValue smaller = this.smallerThan(v);
		BooleanValue greater = this.greaterThan(v);

		return smaller.or(greater).not();
	}

}
