package org.main.Pcap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.jnetpcap.packet.JFlow;
import org.jnetpcap.packet.JPacket;
import org.jnetpcap.packet.format.FormatUtils;
import org.jnetpcap.protocol.network.Ip4;
import org.jnetpcap.protocol.network.Ip6;
import org.jnetpcap.protocol.tcpip.Tcp;
import org.jnetpcap.protocol.tcpip.Udp;
import org.main.utility.common.ProjectUtilities;
import org.main.utility.packet.FlagsUtil;

public class ParameterExtract {

	public static long getDuration(JFlow trafficFlow) throws Exception {
		List<JPacket> packets = trafficFlow.getAll();
		long duration = packets.get(packets.size() - 1).getCaptureHeader().timestampInMillis()
				- packets.get(0).getCaptureHeader().timestampInMillis();
		return duration;
	}

	public static int getFlowSize(JFlow trafficFlow) {
		int flowSize = 0;
		List<JPacket> packets = trafficFlow.getAll();
		for (JPacket jPacket : packets) {
			flowSize += jPacket.getTotalSize();
		}
		return flowSize;
	}

	public static String getProtocolType(JFlow trafficFlow) {
		String protocolType = "None";
		List<JPacket> packets = trafficFlow.getAll();
		if (packets.get(0).hasHeader(Udp.ID)) {
			protocolType = "UDP";
		} else if (packets.get(0).hasHeader(Tcp.ID)) {
			protocolType = "TCP";
		} else {
			protocolType = "None";
		}
		return protocolType;
	}

	public static int getService(JFlow trafficFlow) {
		// TODO: IPv6 isPrivate still not working.
		// http://www.iana.org/assignments/service-names-port-numbers/service-names-port-numbers.xml
		int servicePort = 0;
		List<JPacket> packets = trafficFlow.getAll();
		for (JPacket jPacket : packets) {
			Udp udp = new Udp();
			Tcp tcp = new Tcp();
			Ip4 ip4 = new Ip4();
			Ip6 ip6 = new Ip6();
			if (jPacket.hasHeader(ip4)) {
				String sourceIP = FormatUtils.ip(jPacket.getHeader(ip4).source());
				String destinationIP = FormatUtils.ip(jPacket.getHeader(ip4).destination());
				if (jPacket.hasHeader(udp)) {
					System.out.println("Destination Port: " + udp.destination());
					System.out.println("Source Port: " + udp.source());
					if (ProjectUtilities.isPrivateIP(sourceIP)) {
						servicePort = udp.destination();
					} else {
						servicePort = udp.source();
					}
				} else if (jPacket.hasHeader(tcp)) {
					System.out.println("Destination Port: " + tcp.destination());
					System.out.println("Source Port: " + tcp.source());
					if (ProjectUtilities.isPrivateIP(sourceIP)) {
						servicePort = tcp.destination();
					} else {
						servicePort = tcp.source();
					}
				}
			} else if (jPacket.hasHeader(ip6)) {
				String sourceIP = FormatUtils.ip(jPacket.getHeader(ip6).source());
				String destinationIP = FormatUtils.ip(jPacket.getHeader(ip6).destination());
				// TODO: Add the same code as IPv4 for IPv6
				if (jPacket.hasHeader(udp)) {
					System.out.println("Destination Port: " + udp.destination());
					System.out.println("Source Port: " + udp.source());
					if (ProjectUtilities.isPrivateIPv6(sourceIP)) {
						servicePort = udp.destination();
					} else {
						servicePort = udp.source();
					}
				} else if (jPacket.hasHeader(tcp)) {
					System.out.println("Destination Port: " + tcp.destination());
					System.out.println("Source Port: " + tcp.source());
					if (ProjectUtilities.isPrivateIPv6(sourceIP)) {
						servicePort = tcp.destination();
					} else {
						servicePort = tcp.source();
					}
				}
			}
		}
		return servicePort;
	}

	public static String getConnectionState(JFlow flow) {
		String connectionState = "SF";
		String[] flagsList = FlagsUtil.getFlagsList(flow);
		// for (String string : flagsList) {
		// System.out.print(string + "<->");
		// }
		// TODO: ReDo the whole conditions
		if (FlagsUtil.isREJ(flagsList)) {
			connectionState = "REJ";
		} else if (FlagsUtil.isS0(flagsList)) {
			connectionState = "S0";
		} else if (FlagsUtil.isS1(flagsList)) {
			connectionState = "S1";
		} else if (FlagsUtil.isS2(flagsList)) {
			connectionState = "S2";
		} else if (FlagsUtil.isS3(flagsList)) {
			connectionState = "S3";
		} else if (FlagsUtil.isS4(flagsList)) {
			connectionState = "S4";
		} else if (FlagsUtil.isRSTO(flagsList)) {
			connectionState = "RSTO";
		} else if (FlagsUtil.isRSTR(flagsList)) {
			connectionState = "RSTR";
		} else if (FlagsUtil.isSS(flagsList)) {
			connectionState = "SS";
		} else if (FlagsUtil.isSH(flagsList)) {
			connectionState = "SH";
		} else if (FlagsUtil.isOOS1(flagsList)) {
			connectionState = "OOS1";
		} else {
			connectionState = "SF";
		}
		return connectionState;
	}

	public static boolean isWrongFragment(JFlow flow) {
		boolean isWrongFragment = false;
		List<JPacket> packets = flow.getAll();
		long frameNumber = packets.get(0).getFrameNumber();
		for (JPacket jPacket : packets) {
			// System.out.println(jPacket.getFrameNumber());
			if (jPacket.getFrameNumber() == frameNumber++) {
				continue;
			} else {
				isWrongFragment = true;
				break;
			}
		}
		return isWrongFragment;
	}

	public static boolean isLand(JFlow flow) {
		boolean isLand = false;
		JPacket packet = flow.getAll().get(0);
		Ip4 ip4 = new Ip4();
		Ip6 ip6 = new Ip6();
		String sourceIP = "";
		String destinationIP = "";
		if (packet.hasHeader(ip4)) {
			sourceIP = FormatUtils.ip(ip4.source());
			destinationIP = FormatUtils.ip(ip4.destination());
			if (ProjectUtilities.isPrivateIP(sourceIP) && ProjectUtilities.isPrivateIP(destinationIP)) {
				isLand = true;
			}
		} else if (packet.hasHeader(ip6)) {
			sourceIP = FormatUtils.ip(ip6.source());
			destinationIP = FormatUtils.ip(ip6.destination());
			if (ProjectUtilities.isPrivateIPv6(sourceIP) && ProjectUtilities.isPrivateIPv6(destinationIP)) {
				isLand = true;
			}
		}
		return isLand;
	}

	public static int getUrgetnCount(JFlow flow) {
		String[] flagsList = FlagsUtil.getFlagsList(flow);
		int count = 0;
		for (String flag : flagsList) {
			if (flag.contains("URG")) {
				count++;
			}
		}
		return count;
	}

	public static double getSyncErrorRate(JFlow flow) {
		int syncCount = 0;
		int syncErrorCount = 0;
		String[] flagsList = FlagsUtil.getFlagsList(flow);
		for (int i = 0; i < flagsList.length; i++) {
			if (flagsList[i].equals("SYN")) {
				syncCount++;
				if (!flagsList[i + 1].equals("SYN ACK")) {
					syncErrorCount++;
				}
			}
		}
		return syncCount == 0 ? 0 : (syncErrorCount / syncCount * 100);
	}

	public static double getResetErrorRate(JFlow flow) {
		int resetCount = 0;
		int resetErrorCount = 0;
		String[] flagsList = FlagsUtil.getFlagsList(flow);
		for (int i = 0; i < flagsList.length; i++) {
			if (flagsList[i].equals("RST")) {
				resetCount++;
				if (!flagsList[i + 1].equals("RST ACK")) {
					resetErrorCount++;
				}
			}
		}
		return resetCount == 0 ? 0 : (resetErrorCount / resetCount * 100);
	}

}
