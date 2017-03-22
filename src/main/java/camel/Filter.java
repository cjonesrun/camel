package camel;

import org.apache.camel.component.file.GenericFile;

public class Filter<T> implements org.apache.camel.component.file.GenericFileFilter<T>
{

	public boolean accept(GenericFile<T> arg0) {
		System.out.println("File name = " + arg0.getFileName());
		return false;
	}
	
}
