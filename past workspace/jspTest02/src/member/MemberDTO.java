package member;

public class MemberDTO {
	// Field
	private String id;
	private String pw;
	private String checkPw;
	private String name;
	private String phone;
	private String email;
	private int birthYear;
	
	// Constructor
	public MemberDTO() {}

	
	// Method
	
	// Getters and Setters
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getPw() {
		return pw;
	}
	
	public void setPw(String pw) {
		this.pw = pw;
	}
	
	public String getCheckPw() {
		return checkPw;
	}
	
	public void setCheckPw(String checkPw) {
		this.checkPw = checkPw;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public int getBirthYear() {
		return birthYear;
	}
	
	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}
	
}
