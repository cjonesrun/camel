package camel.exceptions;

public class JSONValidationException extends Exception{
	private static final long serialVersionUID = -2677560743549855434L;

	public JSONValidationException() {
		super();
	}

	public JSONValidationException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public JSONValidationException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public JSONValidationException(String arg0) {
		super(arg0);
	}

	public JSONValidationException(Throwable arg0) {
		super(arg0);
	}
	
	

}
