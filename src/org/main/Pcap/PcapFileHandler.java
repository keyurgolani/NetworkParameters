package org.main.Pcap;

import org.jnetpcap.Pcap;
import org.jnetpcap.packet.JFlowMap;
import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.packet.PcapPacketHandler;
import org.jnetpcap.util.PcapPacketArrayList;
import org.main.utility.properties.ProjectProperties;

public class PcapFileHandler {

	public static PcapPacketArrayList getPacketArrayList(String pcapFile) throws Exception {
		PcapPacketArrayList packets;
		final StringBuilder errbuf = new StringBuilder();
		System.out.println(ProjectProperties.getInputPcap());
		Pcap pcap = Pcap.openOffline(pcapFile, errbuf);
		if (pcap == null) {
			throw new Exception(errbuf.toString());
		}
		PcapPacketHandler<PcapPacketArrayList> jpacketHandler = new PcapPacketHandler<PcapPacketArrayList>() {
			public void nextPacket(PcapPacket packet, PcapPacketArrayList PaketsList) {
				PaketsList.add(packet);
			}
		};
		try {
			packets = new PcapPacketArrayList();
			pcap.loop(Pcap.LOOP_INFINITE, jpacketHandler, packets);
		} finally {
			pcap.close();
		}
		return packets;
	}
	
	public static JFlowMap getFlowsMap() throws Exception {
		JFlowMap map;
		StringBuilder errbuf = new StringBuilder();
		final Pcap pcap = Pcap.openOffline(ProjectProperties.getInputPcap(), errbuf);
		if (pcap == null) {
			throw new Exception(errbuf.toString());
		}
		try {
			map = new JFlowMap();
			pcap.loop(Pcap.LOOP_INFINITE, map, null);
		} finally {
			pcap.close();
		}
		return map;
	}

}
