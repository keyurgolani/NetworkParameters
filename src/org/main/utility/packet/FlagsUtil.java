package org.main.utility.packet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jnetpcap.packet.JFlow;
import org.jnetpcap.packet.JPacket;
import org.jnetpcap.protocol.tcpip.Tcp;
import org.main.utility.common.ProjectUtilities;

public class FlagsUtil {

	public static boolean isREJ(String[] flagsList) {
		boolean isREJ = false;
		List<String> flags = new ArrayList<String>(Arrays.asList(flagsList));
		if(flags.contains("SYN") && flags.contains("REJ")) {
			if(flags.indexOf("SYN") < flags.indexOf("REJ")) {
				isREJ = true;
			}
		}
		return isREJ;
	}

	public static boolean isS0(String[] flagsList) {
		boolean isS0 = false;
		List<String> flags = new ArrayList<String>(Arrays.asList(flagsList));
		if(flags.contains("SYN") && !flags.contains("ACK")) {
			isS0 = true;
		}
		return isS0;
	}

	public static boolean isS1(String[] flagsList) {
		boolean isS1 = false;
		List<String> flags = new ArrayList<String>(Arrays.asList(flagsList));
		if(flags.contains("SYN") && flags.contains("ACK")) {
			if(!flags.contains("PSH") && !flags.contains("FIN")) {
				isS1 = true;
			}
		}
		return isS1;
	}

	public static boolean isS2(String[] flagsList) {
		boolean isS2 = false;
		List<String> flags = new ArrayList<String>(Arrays.asList(flagsList));
		if(flags.contains("SYN") && flags.contains("ACK")) {
			if(flags.indexOf("FIN") == flags.indexOf("SYN") + 2) {
				isS2 = true;
			}
		}
		return isS2;
	}
	
	public static boolean isS3(String[] flagsList) {
		boolean isS3 = false;
		List<String> flags = new ArrayList<String>(Arrays.asList(flagsList));
		if(flags.contains("SYN") && flags.contains("ACK")) {
			if(flags.indexOf("FIN") == flags.indexOf("SYN") + 3) {
				isS3 = true;
			}
		}
		return isS3;
	}
	
	public static boolean isS4(String[] flagsList) {
		boolean isS4 = false;
		List<String> flags = new ArrayList<String>(Arrays.asList(flagsList));
		if(flags.contains("SYN") && flags.contains("ACK")) {
			if(!flags.get(1).equals("SYN")) {
				isS4 = true;
			}
		}
		return isS4;
	}
	
	public static boolean isRSTO(String[] flagsList) {
		boolean isRSTO = false;
		List<String> flags = new ArrayList<String>(Arrays.asList(flagsList));
		if(flags.contains("RST") && flags.indexOf("RST") % 2 == 0) {
			isRSTO = true;
		}
		return isRSTO;
	}
	
	public static boolean isRSTR(String[] flagsList) {
		boolean isRSTR = false;
		List<String> flags = new ArrayList<String>(Arrays.asList(flagsList));
		if(flags.contains("RST") && flags.indexOf("RST") % 2 == 1) {
			isRSTR = true;
		}
		return isRSTR;
	}

	public static boolean isSS(String[] flagsList) {
		boolean isSS = false;
		List<String> flags = new ArrayList<String>(Arrays.asList(flagsList));
		if(flags.contains("SYN") && flags.contains("FIN")) {
			if(flags.indexOf("FIN") < flags.indexOf("SYN")) {
				isSS = true;
			}
		}
		return isSS;
	}
	
	public static boolean isSH(String[] flagsList) {
		boolean isSH = false;
		List<String> flags = new ArrayList<String>(Arrays.asList(flagsList));
		if(flags.contains("ACK") && flags.contains("FIN")) {
			if(flags.indexOf("FIN") < flags.indexOf("ACK")) {
				isSH = true;
			}
		}
		return isSH;
	}
	
	public static boolean isSHR(String[] flagsList) {
		boolean isSHR = false;
		List<String> flags = new ArrayList<String>(Arrays.asList(flagsList));
		if(flags.contains("SYN") && flags.contains("ACK")) {
			if(flags.indexOf("FIN") < flags.indexOf("SYN")) {
				isSHR = true;
			}
		}
		return isSHR;
	}
	
	public static boolean isOOS1(String[] flagsList) {
		boolean isOOS1 = false;
		List<String> flags = new ArrayList<String>(Arrays.asList(flagsList));
		if(flags.contains("SYN") && flags.contains("ACK")) {
			if(!(flags.get(flags.indexOf("SYN") + 1).equals("SYN") && flags.get(flags.indexOf("SYN") + 2).equals("ACK"))) {
				isOOS1 = true;
			}
		}
		return isOOS1;
	}

	public static String[] getFlagsList(JFlow flow) {
		List<JPacket> packets = flow.getAll();
		String[] flagsList = new String[packets.size()];
		int packetIndex = 0;
		for (JPacket jPacket : packets) {
			Tcp tcp = new Tcp();
			if(jPacket.hasHeader(tcp)) {
				String flag = "";
				for (Tcp.Flag cntrlFlag : tcp.flagsEnum()) {
					flag = flag + cntrlFlag + " ";
				}
				flagsList[packetIndex++] = flag.trim();
			}
		}
		flagsList = ProjectUtilities.removeNullElements(flagsList);
		return flagsList;
	}

}
