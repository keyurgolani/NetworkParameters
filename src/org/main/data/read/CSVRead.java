package org.main.data.read;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.main.data.bo.PortService;
import org.main.utility.properties.ProjectProperties;

public class CSVRead {
	
	public List<PortService> readPortServiceCSV(String csvFilePath) throws IOException {
		List<PortService> portServiceList = new ArrayList<PortService>();
		String line = "";
		BufferedReader br = new BufferedReader(new FileReader(ProjectProperties.getPortServiceCSV()));
		while ((line = br.readLine()) != null) {
			PortService portService = new PortService();
			String[] service = line.split(ProjectProperties.getCsvSeparateBy());
			portService.setPort(Integer.parseInt(service[1]));
			portService.setServiceName(service[0]);
			if(service[2] == "tcp") {
				portService.setType("TCP");
			} else if(service[2] == "udp") {
				portService.setType("UDP");
			} else {
				portService.setType("None");
			}
			portServiceList.add(portService);
		}
		return portServiceList;
	}

}
