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

        /*this is a "overly paranoid" filter. other posibilities would be escaping, or the esapi-library*/
        if (s.matches("[a-zA-Z0-9 ,.-_]+")) {
          return s;
        }
                              
        throw new IllegalStringException();
	}

}
