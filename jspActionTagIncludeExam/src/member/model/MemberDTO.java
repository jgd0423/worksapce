package member.model;

import java.sql.Timestamp;

public class MemberDTO {
	// Field
		private int no;
		private String id;
		private String passwd;
		private String passwdChk;
		private String name;
		private String gender;
		private int bornYear;
		private Timestamp regiDate;
		
		// Constructor
		public MemberDTO() {}
		
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
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getGender() {
			return gender;
		}
		public void setGender(String gender) {
			this.gender = gender;
		}
		public int getBornYear() {
			return bornYear;
		}
		public void setBornYear(int bornYear) {
			this.bornYear = bornYear;
		}
		public Timestamp getRegiDate() {
			return regiDate;
		}
		public void setRegiDate(Timestamp regiDate) {
			this.regiDate = regiDate;
		}
}
