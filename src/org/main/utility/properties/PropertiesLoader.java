package org.main.utility.properties;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesLoader {

	public static void loadProperties() {
		Properties prop = new Properties();
		try {
			prop.load(new FileReader("projectproperties.properties"));
			// Parameters
			String parametersString = prop.getProperty("parameters");
			String[] parameters = parametersString.split(",");
			for (String parameter : parameters) {
				ProjectProperties.getParameters().add(parameter.trim());
			}
			// Pcap File Name
			ProjectProperties.setInputPcap(prop.getProperty("inputPcap"));
			ProjectProperties.setPortServiceCSV(prop.getProperty("portServiceCSV"));
			ProjectProperties.setCsvSeparateBy(prop.getProperty("csvSeparateBy"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
