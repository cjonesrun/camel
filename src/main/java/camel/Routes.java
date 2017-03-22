package camel;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

import camel.exceptions.JSONValidationException;
import camel.processor.*;

public class Routes extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		
		onException(JSONValidationException.class)
		.process(new Processor() {
	          public void process(Exchange exchange) throws Exception {
	                Exception cause = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
	                // we now have the caused exception
	                cause.printStackTrace();
	          }
	        })
		.to("log:camel?showAll=true&multiline=true&level=WARN")
		.to("file:/tmp/camel/errors")
		/*.log(LoggingLevel.ERROR, "")
		.to("file:/tmp/camel/errors")*/;
		
		from("file:/tmp/camel?include=.*.json&move=${file:name}.processed&delay=2000")
		//.errorHandler(deadLetterChannel("direct:errorLogger"))
		//.log(LoggingLevel.INFO, "Validating JSON")
		.process(new JSONValidator())
		//.log(LoggingLevel.INFO, "Transforming Content")
		.bean(Transform.class, "transformContent")
		//.log(LoggingLevel.INFO, "Sending to JMS")
		.to("file:/tmp/camel/processed");
		
		
		//.to("file:/tmp/camel/output");
		
		/*from("direct:errorLogger")
		.process(new Log("errorLogger"));
		*/
		
		
		/*from("file:/tmp/camel/errors?include=.*.json&move=${file:name}.error")
		.log(LoggingLevel.ERROR, "Error files received.");*/
		
		
	}

}