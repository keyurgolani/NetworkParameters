package org.main.utility.common;

import java.math.BigInteger;
import java.net.Inet6Address;

public class ProjectUtilities {

	public static boolean isPrivateIP(String ipAddressString) {
		boolean isValid = false;

		if (ipAddressString != null && !ipAddressString.isEmpty()) {
			String[] ip = ipAddressString.split("\\.");
			short[] ipNumber = new short[] { Short.parseShort(ip[0]), Short.parseShort(ip[1]), Short.parseShort(ip[2]),
					Short.parseShort(ip[3]) };
			if (ipNumber[0] == 10) {
				isValid = true;
			} else if (ipNumber[0] == 172 && (ipNumber[1] >= 16 && ipNumber[1] <= 31)) {
				isValid = true;
			} else if (ipNumber[0] == 192 && ipNumber[1] == 168) {
				isValid = true;
			}
		}

		return isValid;
	}

	public static String getServiceFromPort(int port) {
		String service = "None";
		switch (port) {
		case 21:
			service = "FTP";
			break;
		case 22:
			service = "SSH";
			break;
		case 23:
			service = "TELNET";
			break;
		case 25:
			service = "SMTP";
			break;
		case 69:
			service = "TFTP";
			break;
		case 80:
			service = "HTTP";
			break;
		case 123:
			service = "NTP";
			break;
		case 443:
			service = "HTTPS";
			break;
		default:
			break;
		}
		return service;
	}

	public static boolean isPrivateIPv6(String sourceIP) {
		boolean isPrivate = false;
		// TODO: Implement check for isPrivateIPv6
		return isPrivate;
	}

	public static String[] removeNullElements(String[] array) {
		int j = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] != null)
				array[j++] = array[i];
		}
		String[] newArray = new String[j];
		System.arraycopy(array, 0, newArray, 0, j);
		return newArray;
	}

}
