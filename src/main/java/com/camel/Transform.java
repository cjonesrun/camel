package com.camel;

public class Transform {

	public String transformContent(String body) throws Exception {
		
		//throw new Exception (this.getClass().getCanonicalName() + " failed to process");
		
		/*System.out.println(body.getClass().getCanonicalName());
		return body;*/
		
		return body.toUpperCase();
	}
}
