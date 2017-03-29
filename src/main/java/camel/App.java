package camel;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;

public class App 
{
	public static Logger LOG = LoggerFactory.getLogger("default");
	public static File WORKING_DIR = new File("working");
	public static final void main(String[] s)
	{
		
		LOG.info("Working Directory: " + WORKING_DIR.getAbsolutePath());
		
		Routes r = new Routes();
		CamelContext ctx = new DefaultCamelContext();
		
		try {
			ctx.addRoutes(r);
			//ctx.setTracing(true);
			
			ctx.start();
			Thread.sleep(5*60*1000);
			ctx.stop();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
