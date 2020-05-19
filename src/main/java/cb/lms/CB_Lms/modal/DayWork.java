package cb.lms.CB_Lms.modal;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import cb.lms.CB_Lms.util.BusinessUtils;

@Entity
@Table(name = "DAY_WORK")
public class DayWork implements IDataEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;

	@Column(name = "HOURS", nullable = false)
	Integer hours;

	@Column(name = "_DATE", nullable = false)
	Date date;

	@Column(name = "COMMENTS", nullable = true)
	String comments;
	
	@Column(name = "ACTIVITY", nullable = true)
	String activity;

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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

//	@Transient
//	public String getDay() {
//
//		LocalDate localDate = BusinessUtils.toLocalDate(this.date);
//		int day = localDate.getDayOfMonth();
//		String weekDay = localDate.getDayOfWeek().name();
//
//		StringBuilder sb = new StringBuilder();
//		sb.append(day);
//		sb.append(" ");
//		sb.append(weekDay);
//
//
//		return sb.toString();
//	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

}
