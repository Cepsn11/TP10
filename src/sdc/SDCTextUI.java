package sdc;

import java.io.InputStreamReader;
import java.io.BufferedReader;

public final class SDCTextUI {

    private SDCTextUI() {
    }

    public static void main(final String[] p) {

	System.out.println("Welcome to sdc. Go ahead type your commands ...");
	SDC sdc = new SDC();

	try {

	    while (true) {
		// prompt
		System.out.print("> ");

		// read a line from keyboard
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String line = br.readLine();

		try {
		    sdc.executeLine(line);
		} catch (ProcessingException e) {
		    System.out.println(e.getMessage());
		}
	    }

	} catch (ShutdownException e) {
	    System.out.println("Thank you for using sdc.");
	} catch (Exception e) {
	    System.out.println("!!! Internal error !!!");
	    e.printStackTrace();
	}
    }
}
