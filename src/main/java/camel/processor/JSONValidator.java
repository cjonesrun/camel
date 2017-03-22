package camel.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import camel.exceptions.JSONValidationException;

public class JSONValidator implements Processor {

	public void process(Exchange exchange) throws Exception {
		String x = exchange.getIn().getBody(String.class);
		
		if (x == null || x.trim().length() == 0)
			throw new JSONValidationException("Empty JSONObject received.");
		
		camel.App.LOG.info(this.getClass().getCanonicalName() + " processing: " + x);
	}
}