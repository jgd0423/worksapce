package iot;

public class Aircondition implements RemoteControl {
	// Field
	private String aptId;
	double desiredTemperature = 23.0;
	private boolean status = false;
	
	// Constructor
	public Aircondition(String aptId) {
		this.aptId = aptId;
	}
	
	// Method
	@Override
	public boolean on() {
		System.out.println("아파트ID: " + aptId + ", Aircon ON (설정온도: " + desiredTemperature + ")");
		return true;
	}
	
	@Override
	public boolean off() {
		System.out.println("아파트ID: " + aptId + ", Aircon OFF");
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
