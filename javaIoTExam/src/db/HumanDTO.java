package db;

public class HumanDTO {
	// Field
	private String aptId;
	private String security;
	private String light;
	private String aircondition;
	private String television;
	private String cucu;
	
	// Constructor
	public HumanDTO(String aptId, String security, String light, String aircondition, String television, String cucu) {
		this.aptId = aptId;
		this.security = security;
		this.light = light;
		this.aircondition = aircondition;
		this.television = television;
		this.cucu = cucu;
	}
	
	// Method
	
	// Getters and Setters
	public String getAptId() {
		return aptId;
	}

	public void setAptId(String aptId) {
		this.aptId = aptId;
	}

	public String getSecurity() {
		return security;
	}

	public void setSecurity(String security) {
		this.security = security;
	}

	public String getLight() {
		return light;
	}

	public void setLight(String light) {
		this.light = light;
	}

	public String getAircondition() {
		return aircondition;
	}

	public void setAircondition(String aircondition) {
		this.aircondition = aircondition;
	}

	public String getTelevision() {
		return television;
	}

	public void setTelevision(String television) {
		this.television = television;
	}

	public String getCucu() {
		return cucu;
	}

	public void setCucu(String cucu) {
		this.cucu = cucu;
	}
}
