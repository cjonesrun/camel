package camel.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.json.JSONObject;

import camel.exceptions.JSONValidationException;

public class JSONValidator implements Processor {

	public void process(Exchange exchange) throws Exception {
		String x = exchange.getIn().getBody(String.class);
		
		try {
			JSONObject o = new JSONObject(x);
		
			o.put("comment", "here's a comment i just added");
			camel.App.LOG.info(this.getClass().getCanonicalName() + " processing: " + o.toString(1));
			exchange.getIn().setBody(o.toString());
			
		} catch (Exception e) {
			throw new JSONValidationException("Empty JSONObject received.", e);
		}
		
		
		/*if (x == null || x.trim().length() == 0)
			throw new JSONValidationException("Empty JSONObject received.");
		*/
		
	}
}