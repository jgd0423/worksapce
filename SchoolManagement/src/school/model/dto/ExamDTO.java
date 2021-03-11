package school.model.dto;

import java.util.Date;

public class ExamDTO {
	// Field
	private int no;
	private String name;
	private Date regiDate;
	
	// Constructor
	public ExamDTO() {}
	
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

	public Date getRegiDate() {
		return regiDate;
	}

	public void setRegiDate(Date regiDate) {
		this.regiDate = regiDate;
	}
}
