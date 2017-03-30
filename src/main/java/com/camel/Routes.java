package com.camel;

import org.apache.camel.builder.RouteBuilder;

import com.camel.processor.*;

public class Routes extends RouteBuilder {

	static final String log = Routes.class.getCanonicalName();
	
	@Override
	public void configure() throws Exception {
		App.LOG.error("package: [" + log + "]");
		// handle incoming files, move incoming to original/ and result to processed/
		from("file:"+ App.WORKING_DIR.getAbsolutePath() +"?include=.*.json" +
				"&move=" + App.WORKING_DIR.getAbsolutePath() + "/original/${file:name}&delay=2000")
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
		.to("log:" + log + "?level=info&showAll=true&multiline=true");
		
		from("direct:errorLogger")
			.to("log:" + log + "?level=error&showAll=true&multiline=true")
			.to("file:" + App.WORKING_DIR.getAbsolutePath() + "/errors");
		
		
	}

}