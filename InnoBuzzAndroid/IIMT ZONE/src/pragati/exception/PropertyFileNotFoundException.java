package pragati.exception;

public class PropertyFileNotFoundException extends Exception {
	
	public PropertyFileNotFoundException() {
		super();
	}
	
	public PropertyFileNotFoundException(String p_msg) {
		super(p_msg);
	}
	
	public PropertyFileNotFoundException(String p_msg,Throwable t) {
		super(p_msg,t);
	}
	
	public PropertyFileNotFoundException(Throwable t) {
		super(t);
	}


}
