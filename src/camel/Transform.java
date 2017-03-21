package camel;

public class Transform {

	public String transformContent(String body) throws Exception {
		
		throw new Exception (this.getClass().getCanonicalName() + " failed to process");
		
		//return body.toUpperCase();
	}
}
