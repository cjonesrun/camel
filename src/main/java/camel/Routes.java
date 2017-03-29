package camel;

import org.apache.camel.builder.RouteBuilder;

import camel.processor.*;

public class Routes extends RouteBuilder {


	@Override
	public void configure() throws Exception {
		
		// handle incoming files, send incoming to original/ and result to processed/
		from("file:"+ App.WORKING_DIR.getAbsolutePath() +"?include=.*.json&move=./original/${file:name}&delay=2000")
		//from("file:"+ App.WORKING_DIR.getAbsolutePath() +"?include=.*.json&delay=2000")
		.to("direct:processor")
		.to("file:"+ App.WORKING_DIR.getAbsolutePath() + "/processed");
		
		// handle incoming jms messages
		/*from("jms:queue")
		.to("direct:processor");*/
		
		from("direct:processor")
		.errorHandler(deadLetterChannel("direct:errorLogger"))
		.process(new JSONValidator())
		//.log(LoggingLevel.INFO, "Transforming Content")
		.bean(Transform.class, "transformContent")
		//.log(LoggingLevel.INFO, "Sending to JMS")
		//.to("file:"+ App.WORKING_DIR.getAbsolutePath() + "/processed");
		.to("log:camel?showAll=true&multiline=true&level=WARN");
		
		from("direct:errorLogger")
			.to("log:camel?showAll=true&multiline=true&level=WARN")
			.to("file:" + App.WORKING_DIR.getAbsolutePath() + "/errors");
		
		
	}

}