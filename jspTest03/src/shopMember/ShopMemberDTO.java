package shopMember;

public class ShopMemberDTO {
	// Field
	private int no;
	private String id;
	private String password;
	private String checkPassword;
	private String name;
	private String phone;
	private String email;
	private int age;
	private String joinDate;
	private String isMember;
	
	
	// Constructor
	public ShopMemberDTO() {}


	
	
	// Method
	
	// Getters and Setters
	public int getNo() {
		return no;
	}


	public void setNo(int no) {
		this.no = no;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getCheckPassword() {
		return checkPassword;
	}


	public void setCheckPassword(String checkPassword) {
		this.checkPassword = checkPassword;
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


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public String getJoinDate() {
		return joinDate;
	}


	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}


	public String getIsMember() {
		return isMember;
	}


	public void setIsMember(String isMember) {
		this.isMember = isMember;
	}
}
