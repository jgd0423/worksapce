package com.test.springStudy.guestbook.model.dto;

import java.util.Date;

public class GuestbookDTO {
	// Field
		private int no;
		private String name;
		private String email;
		private String passwd;
		private String content;
		private Date regiDate;
		
		// Constructor
		public GuestbookDTO() {}
		
		// Getters and Setters
		public int getNo() {
			return no;
		}

		public void setNo(int no) {
			this.no = no;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPasswd() {
			return passwd;
		}

		public void setPasswd(String passwd) {
			this.passwd = passwd;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public Date getRegiDate() {
			return regiDate;
		}

		public void setRegiDate(Date regiDate) {
			this.regiDate = regiDate;
		}
}
