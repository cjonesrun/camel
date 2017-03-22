package camel;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App 
{
	public static final void main(String[] s)
	{
		Logger LOG = LoggerFactory.getLogger(App.class );
		LOG.info("Logging output to console");
		
		Routes r = new Routes();
		CamelContext ctx = new DefaultCamelContext();
		
		try {
			ctx.addRoutes(r);
			ctx.start();
			Thread.sleep(5*60*1000);
			ctx.stop();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
