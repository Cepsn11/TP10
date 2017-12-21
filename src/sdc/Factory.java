package sdc;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Factory {

	private static final String PATH = "sdc.";
	private ArrayList<String> list;

	public Factory() {
		this.list = new ArrayList<String>();

		/* Valeurs supportées par SDC */
		this.list.add("value.IntegerValue");
		this.list.add("value.RationalValue");
		this.list.add("value.BooleanValue");

		/* Commandes de contrôle */
		this.list.add("control.QuitSymbol");
		this.list.add("control.ClearSymbol");
		this.list.add("control.ViewSymbol");

		/* Opération numérique */
		this.list.add("operation.AddOperation");
		this.list.add("operation.SubOperation");
		this.list.add("operation.DivOperation");
		this.list.add("operation.MulOperation");
		this.list.add("operation.AbsoluteValueOperation");

		/* Opération de comparaison */
		this.list.add("operation.SuperiorOperation");
		this.list.add("operation.InferiorOperation");
		this.list.add("operation.EqualsOperation");

		/* Opération booléenne */
		this.list.add("operation.AndOperation");
		this.list.add("operation.OrOperation");
		this.list.add("operation.NotOperation");

		/* Instructions */
		this.list.add("instruction.Variable");
		this.list.add("instruction.AffectationInstruction");
		this.list.add("instruction.ConditionInstruction");

	}

	public Symbol[] registered() throws InternalError {
		List<Symbol> s = this.list.stream().map(name -> this.createInstance(PATH + name)).collect(Collectors.toList());

		return s.toArray(new Symbol[this.list.size()]);
	}

	private Symbol createInstance(String className) throws InternalError {
		try {
			Class cl = Class.forName(className);
			java.lang.reflect.Constructor co = cl.getConstructor();
			return (Symbol) co.newInstance();
		} catch (Exception e) {
			throw new InternalError();
		}
	}

}
