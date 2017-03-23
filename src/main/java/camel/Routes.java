package camel;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

import camel.exceptions.JSONValidationException;
import camel.processor.*;

import java.io.File;

public class Routes extends RouteBuilder {


	@Override
	public void configure() throws Exception {
		
		/*onException(JSONValidationException.class)
		.process(new Processor() {
	          public void process(Exchange exchange) throws Exception {
	                Exception cause = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
	                // we now have the caused exception
	                cause.printStackTrace();
	          }
	        })
		.to("log:camel?showAll=true&multiline=true&level=WARN")
		.to("file:" + new File("working/errors").getAbsolutePath())
		/*.log(LoggingLevel.ERROR, "")
		.to("file:/tmp/camel/errors");*/
		
		from("file:"+ new File("working").getAbsolutePath() +"?include=.*.json&delete=true&delay=2000")
		.errorHandler(deadLetterChannel("direct:errorLogger"))
		//.log(LoggingLevel.INFO, "Validating JSON")
		.process(new JSONValidator())
		//.log(LoggingLevel.INFO, "Transforming Content")
		.bean(Transform.class, "transformContent")
		//.log(LoggingLevel.INFO, "Sending to JMS")
		.to("file:"+ new File("working/processed").getAbsolutePath());
		
		
		//.to("file:/tmp/camel/output");
		
		from("direct:errorLogger")
			.to("log:camel?showAll=true&multiline=true&level=WARN")
			.to("file:" + new File("working/errors").getAbsolutePath());
		
		/*from("file:/tmp/camel/errors?include=.*.json&move=${file:name}.error")
		.log(LoggingLevel.ERROR, "Error files received.");*/
		
		
	}

}