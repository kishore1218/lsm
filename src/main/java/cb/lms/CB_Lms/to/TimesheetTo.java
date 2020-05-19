package cb.lms.CB_Lms.to;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class TimesheetTo implements Serializable{
	
	Integer id;
	
	String status;
	
	String facultyName;
	
	Integer facultyId;
	
	String period;
	
	Date submittedDate;
	
	List<DayworkTo> dayswork;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getSubmittedDate() {
		return submittedDate;
	}

	public void setSubmittedDate(Date submittedDate) {
		this.submittedDate = submittedDate;
	}

	public List<DayworkTo> getDayswork() {
		return dayswork;
	}

	public void setDayswork(List<DayworkTo> dayswork) {
		this.dayswork = dayswork;
	}

	public String getFacultyName() {
		return facultyName;
	}

	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
	}

	public Integer getFacultyId() {
		return facultyId;
	}

	public void setFacultyId(Integer facultyId) {
		this.facultyId = facultyId;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

}
