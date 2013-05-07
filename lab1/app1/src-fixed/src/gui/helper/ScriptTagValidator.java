package gui.helper;

import java.util.IllegalFormatException;

public class ScriptTagValidator implements ValidatorInterface {

	@Override
	public String validate(String s) throws IllegalStringException {
      /* FIXED: HTML (thus XSS) injection
       * old:
		if (s.matches(".*< *[Ss][Cc][Rr][Ii][Pp][Tt] *>.*"))
			throw new IllegalStringException();
		return s;
       */
      //TODO: how to fix it? :)
	}

}
