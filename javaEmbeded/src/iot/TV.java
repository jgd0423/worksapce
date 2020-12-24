package iot;

public class TV implements RemoteControl {
	// Field
	private String aptId;
	int chanel = 7;
	private boolean status = false;
	
	// Constructor
	public TV(String aptId) {
		this.aptId = aptId;
	}
	
	// Method
	@Override
	public boolean on() {
		System.out.println("아파트ID: " + aptId + ", TV ON (채널: " + chanel +")");
		return true;
	}
	
	@Override
	public boolean off() {
		System.out.println("아파트ID: " + aptId + ", TV OFF");
		return true;
	}
	
	public String getAptId() {
		return aptId;
	}

	public void setAptId(String aptId) {
		this.aptId = aptId;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
}
