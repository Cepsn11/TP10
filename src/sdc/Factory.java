package sdc;

import java.lang.reflect.*;
import java.util.ArrayList;

public class Factory {

    private ArrayList<String> list;

    public Factory() {
	this.list = new ArrayList<String>();

	// add all types or operation here
	this.list.add("IntegerValue");

	this.list.add("QuitSymbol");
	this.list.add("ClearSymbol");

	this.list.add("AddOperation");
	this.list.add("SubOperation");
	this.list.add("DivOperation");
	this.list.add("MulOperation");
    }


    public Symbol[] registered() throws InternalError {
	ArrayList<Symbol> s = new ArrayList<Symbol>();

	for (String objectName : this.list) {
	    objectName = "sdc." + objectName;
	    s.add(this.createInstance(objectName));
	}

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
