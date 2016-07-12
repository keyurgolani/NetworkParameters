package org.main.Launch;
import java.util.Collection;
import java.util.List;

import org.jnetpcap.packet.JFlow;
import org.jnetpcap.packet.JPacket;
import org.main.Pcap.ParameterExtract;
import org.main.Pcap.PcapFileHandler;
import org.main.utility.properties.PropertiesLoader;

public class Launch {

	public static void main(String[] args) {
		PropertiesLoader.loadProperties();
		try {
			Collection<JFlow> flowList = PcapFileHandler.getFlowsMap().values();
			for (JFlow flow : flowList) {
//				long duration = ParameterExtract.getDuration(flow);
//				System.out.println("Duration: " + duration);
//				String protocolType = ParameterExtract.getProtocolType(flow);
//				System.out.println(protocolType);
				// TODO: Not done yet.
//				int servicePort = ParameterExtract.getService(flow);
//				System.out.println(servicePort);
				// TODO: Separate the source and destination byte sizes depending upon the ports being used at each end.
//				int flowSize = ParameterExtract.getFlowSize(flow);
//				System.out.println("Size: " + flowSize);
//				String connectionState = ParameterExtract.getConnectionState(flow);
//				System.out.println(connectionState);
//				boolean isWrongFragment = ParameterExtract.isWrongFragment(flow);
//				System.out.println(isWrongFragment);
//				boolean isLand = ParameterExtract.isLand(flow);
//				System.out.println(isLand);
//				int urgentCount = ParameterExtract.getUrgetnCount(flow);
//				System.out.println(urgentCount);
//				double syncErrorRate = ParameterExtract.getSyncErrorRate(flow);
//				System.out.println(syncErrorRate);
//				double resetErrorRate = ParameterExtract.getResetErrorRate(flow);
//				System.out.println(resetErrorRate);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// System.out.println("Duration: " +
		// ParameterExtract.getDuration(ProjectProperties.getInputPcap()) +
		// "ms");
		// CSVExport.generateCsvFile("test.csv");
	}

}
