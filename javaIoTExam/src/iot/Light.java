package iot;

public class Light implements RemoteControl {
	// Field
	private String aptId;
	private boolean status = false;
	
	// Constructor
	public Light(String aptId) {
		this.aptId = aptId;
	}
	
	// Method
	@Override
	public boolean on() {
		System.out.println("아파트ID: " + aptId + ", Light ON");
		return true;
	}
	
	@Override
	public boolean off() {
		System.out.println("아파트ID: " + aptId + ", Light OFF");
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
