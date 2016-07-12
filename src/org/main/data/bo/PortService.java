package org.main.data.bo;

public class PortService {
	
	public enum ProtocolType {UDP, TCP, NONE}
	
	private int port;
	private String serviceName;
	private String type;
	
	public int getPort() {
		return port;
	}
	
	public void setPort(int port) {
		this.port = port;
	}
	
	
	public String getServiceName() {
		return serviceName;
	}
	
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}

}
