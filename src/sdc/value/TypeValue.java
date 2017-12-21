package sdc.value;

import sdc.exception.IncompatibleTypeException;

public enum TypeValue {

	NUMERIC("numeric", "NumericValue"), INTEGER("integer", "IntegerValue"), RATIONAL("rational",
			"RationalValue"), BOOLEAN("boolean", "BooleanValue");

	private static final String PATH = "sdc.value.";

	private String name;
	private String className;

	TypeValue(String name, String className) {
		this.name = name;
		this.className = className;
	}

	/*
	 * Permet de vérifier le type de l'opérande d'une opération unaire (ex :
	 * NotOperation, AbsoluteValueOperation)
	 */

	public void check(Value v1) throws IncompatibleTypeException {
		try {
			if (!(Class.forName(PATH + this.className).isInstance(v1))) {
				throw new IncompatibleTypeException("value must have the type : " + this.name);
			}
		} catch (ClassNotFoundException e) {
			throw new IncompatibleTypeException("unknown type : " + this.name);
		}
	}

	/*
	 * Permet de vérifier le type des opérandes d'une opération binaire (ex :
	 * AddOperation, subOperation, AndOperation, OrOperation...)
	 */

	public void check(Value v1, Value v2) throws IncompatibleTypeException {
		try {
			Class<?> cls = Class.forName(PATH + this.className);
			if (!(cls.isInstance(v1) && cls.isInstance(v2))) {
				throw new IncompatibleTypeException("values must have the type : " + this.name);
			}
		} catch (ClassNotFoundException e) {
			throw new IncompatibleTypeException("unknown type : " + this.name);
		}
	}

	/*
	 * Permet de vérifier qu'une valeur v2 a le même type qu'une valeur v1 pour les
	 * implémentations des opérations (IntegerValue, RationalValue)
	 */

	public static void checkSameType(Value v1, Value v2) throws IncompatibleTypeException {
		Class<?> cls = v1.getClass();

		if (!(cls.isInstance(v2))) {
			throw new IncompatibleTypeException("values must have the same type");
		}
	}

}
