package iot;

public class Cucu implements RemoteControl {
	// Field
	private String aptId;
	private boolean status = false;
	
	// Constructor
	public Cucu(String aptId) {
		this.aptId = aptId;
	}
	
	// Method
	@Override
	public boolean on() {
		System.out.println("아파트ID: " + aptId + ", 취사시작");
		return true;
	}
	
	@Override
	public boolean off() {
		System.out.println("아파트ID: " + aptId + ", 취사종료");
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
