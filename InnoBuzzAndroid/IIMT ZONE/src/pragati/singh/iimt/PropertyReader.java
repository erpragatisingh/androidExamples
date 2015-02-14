package pragati.singh.iimt;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import pragati.exception.PropertyFileNotFoundException;


public class PropertyReader {
	  private Properties l_props = null;
	  
	  
	  public PropertyReader(){
	  }
	  
	  public void readProperties(String p_file_name) throws PropertyFileNotFoundException {
		  File l_property_file = new File(p_file_name + ".properties");
		  if(!l_property_file.exists()){
			  throw new PropertyFileNotFoundException("Property File not found....");
		  }
		  try {
			  InputStream l_file_input_stream = new FileInputStream(l_property_file);
			  l_props = new Properties();
			  l_props.load(l_file_input_stream);
			  
		  } catch(Exception e) {
			  throw new PropertyFileNotFoundException("Property File not found....");
		  }
	  }
	  
	  public Object getProperty(String p_property_name) {
		  if(l_props == null) {
			  return null;
		  }
			  
		  return l_props.getProperty(p_property_name);
	  }
	  
	  
}


