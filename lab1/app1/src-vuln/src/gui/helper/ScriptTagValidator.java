package gui.helper;

import java.util.IllegalFormatException;

public class ScriptTagValidator implements ValidatorInterface {

	@Override
	public String validate(String s) throws IllegalStringException {
		if (s.matches(".*< *[Ss][Cc][Rr][Ii][Pp][Tt] *>.*"))
			throw new IllegalStringException();
		return s;
	}

}
