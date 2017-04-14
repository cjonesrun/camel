package com.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class Log implements Processor {

	private String prefix;
	
	
	public Log(String prefix) {
		super();
		this.prefix = prefix;
	}


	public void process(Exchange exchange) throws Exception {
		App.LOG.info(prefix + ": Now processing the String: " + exchange.getIn().getBody(String.class));
	}
}