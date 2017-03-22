package camel.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class JSONValidator implements Processor {

	public void process(Exchange exchange) throws Exception {
		camel.App.LOG.info(this.getClass().getCanonicalName() + " processing: " + exchange.getIn().getBody(String.class));
		
	}
}