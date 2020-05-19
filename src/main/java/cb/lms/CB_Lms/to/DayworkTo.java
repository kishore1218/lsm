package cb.lms.CB_Lms.to;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import cb.lms.CB_Lms.util.BusinessUtils;

public class DayworkTo implements Serializable{
	
	Integer id;
	
	Integer hours;
	
	String comments;
	
	String activity;
	
	Date date;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getHours() {
		return hours;
	}

	public void setHours(Integer hours) {
		this.hours = hours;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}
	
	public String getDay() {

		LocalDate localDate = BusinessUtils.toLocalDate(this.date);
		int day = localDate.getDayOfMonth();
		String weekDay = localDate.getDayOfWeek().name();

		StringBuilder sb = new StringBuilder();
		sb.append(day);
		sb.append(" ");
		sb.append(weekDay);


		return sb.toString();
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	

}
