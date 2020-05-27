package cb.lms.CB_Lms.modal;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import cb.lms.CB_Lms.util.BusinessUtils;

@Entity
@Table(name = "TIME_SHEET")
public class TimeSheet implements IDataEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;

	@Column(name = "MONTH", nullable = false)
	String month;

	@Column(name = "Year", nullable = false)
	Integer year;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = true)
	@JsonIgnore
	Faculty faculty;

	@Column(name = "START_DATE", nullable = false)
	Date startDate;

	@Column(name = "END_DATE", nullable = false)
	Date endDay;

	@Column(name = "STATUS", nullable = false)
	String status;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "TIME_SHEET_ID")
	List<DayWork> dayWorks;
	
	@Column(name = "COMMENTS", nullable = true)
	String comments;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Faculty getFaculty() {
		return faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<DayWork> getDayWorks() {
		return dayWorks;
	}

	public void setDayWorks(List<DayWork> dayWorks) {
		this.dayWorks = dayWorks;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDay() {
		return endDay;
	}

	public void setEndDay(Date endDay) {
		this.endDay = endDay;
	}

	@Transient
	public String getPeriod() {

		return BusinessUtils.formatDate(this.startDate) + "  -  " + BusinessUtils.formatDate(this.endDay);
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

}
