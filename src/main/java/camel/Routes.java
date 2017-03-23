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
		
		from("file:"+ App.WORKING_DIR.getAbsolutePath() +"?include=.*.json&move=./original/${file:name}&delay=2000")
		.errorHandler(deadLetterChannel("direct:errorLogger"))
		//.log(LoggingLevel.INFO, "Validating JSON")
		.process(new JSONValidator())
		//.log(LoggingLevel.INFO, "Transforming Content")
		.bean(Transform.class, "transformContent")
		//.log(LoggingLevel.INFO, "Sending to JMS")
		.to("file:"+ App.WORKING_DIR.getAbsolutePath() + "/processed");
		
		//.to("file:/tmp/camel/output");
		
		from("direct:errorLogger")
			.to("log:camel?showAll=true&multiline=true&level=WARN")
			.to("file:" + App.WORKING_DIR.getAbsolutePath() + "/errors");
		
		
	}

}