package cb.lms.CB_Lms.modal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "FACULTY_ACADEMICS")
@NamedQueries({
	@NamedQuery(name = "getFacultyAcademics", query = "from FacultyAcademics facad  where facad.faculty.id=:facultyId and facad.academicDispcline.id=:dispacaId")})

public class FacultyAcademics implements IDataEntity{
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;
	
//	@Column(name = "FACULTY_ID", nullable = false)
//	Integer facultyId;
	
//	@Column(name = "DISP_ACD_YEAR_ID", nullable = false)
//	Integer academic;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FACULTY_ID", nullable = true)
	Faculty faculty;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DISP_ACD_YEAR_ID", nullable = true)
	DesciplineAcademic academicDispcline;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Faculty getFaculty() {
		return faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}

	public DesciplineAcademic getAcademicDispcline() {
		return academicDispcline;
	}

	public void setAcademicDispcline(DesciplineAcademic academicDispcline) {
		this.academicDispcline = academicDispcline;
	}

//	public Integer getFacultyId() {
//		return facultyId;
//	}
//
//	public void setFacultyId(Integer facultyId) {
//		this.facultyId = facultyId;
//	}
	
	


}
