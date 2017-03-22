package camel;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;

public class Routes extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		
		onException(Exception.class).process(new Log("errorLog")).to("file:/tmp/camel/errors");
		
		from("file:/tmp/camel?include=.*.json&move=${file:name}.processed&delay=2000")
		//.errorHandler(deadLetterChannel("direct:errorLogger"))
		.process(new Log("one"))
		.log(LoggingLevel.INFO, "log")
		

		.bean(Transform.class, "transformContent")
		.process(new Log("three"));
	
		//.to("file:/tmp/camel/output");
		
		/*from("direct:errorLogger")
		.process(new Log("errorLogger"));
		*/
		
		
		/*from("file:/tmp/camel/errors?include=.*.json&move=${file:name}.processed")
		.process(new Log());*/
		
	}

}