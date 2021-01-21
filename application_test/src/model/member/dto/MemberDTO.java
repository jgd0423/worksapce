package model.member.dto;

import java.sql.Date;

public class MemberDTO {
	// Field
	private String id;
	private String passwd;
	private String passwdChk;
	private Date changeDate;
	
	// Constructor
	public MemberDTO() {}

	// Getters and Setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getPasswdChk() {
		return passwdChk;
	}

	public void setPasswdChk(String passwdChk) {
		this.passwdChk = passwdChk;
	}

	public Date getChangeDate() {
		return changeDate;
	}

	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}
}
