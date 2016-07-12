package org.main.utility.properties;

import java.util.ArrayList;

public class ProjectProperties {
	private static String portServiceCSV = "";
	private static ArrayList<String> parameters = new ArrayList<String>();
	private static String inputPcap = "";
	private static String csvSeparateBy = "";

	public static ArrayList<String> getParameters() {
		return parameters;
	}

	public static void setParameters(ArrayList<String> parameters) {
		ProjectProperties.parameters = parameters;
	}

	public static String getInputPcap() {
		return inputPcap;
	}

	public static void setInputPcap(String inputPcap) {
		ProjectProperties.inputPcap = inputPcap;
	}

	public static String getPortServiceCSV() {
		return portServiceCSV;
	}

	public static void setPortServiceCSV(String portServiceCSV) {
		ProjectProperties.portServiceCSV = portServiceCSV;
	}

	public static String getCsvSeparateBy() {
		return csvSeparateBy;
	}

	public static void setCsvSeparateBy(String csvSeparateBy) {
		ProjectProperties.csvSeparateBy = csvSeparateBy;
	}
}
