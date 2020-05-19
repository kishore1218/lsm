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
@Table(name = "DESCP_ACADEMIC_YEAR")
@NamedQueries({
	@NamedQuery(name = "getAcademicDiscipline", query = "from DesciplineAcademic adisp  where adisp.discipline.id=:dispId and adisp.academic.id=:academic")})

public class DesciplineAcademic implements IDataEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;

//	@Column(name = "DESP_ID", nullable = false)
//	Integer desciplineId;
//
//	@Column(name = "ACD_YEAR_ID", nullable = false)
//	Integer academicId;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "DESP_ID", nullable = true)
	Discipline discipline;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ACD_YEAR_ID", nullable = true)
	AcademicYear academic;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Discipline getDiscipline() {
		return discipline;
	}

	public void setDiscipline(Discipline discipline) {
		this.discipline = discipline;
	}

	public AcademicYear getAcademic() {
		return academic;
	}

	public void setAcademic(AcademicYear academic) {
		this.academic = academic;
	}

//	public Integer getDesciplineId() {
//		return desciplineId;
//	}
//
//	public void setDesciplineId(Integer desciplineId) {
//		this.desciplineId = desciplineId;
//	}
//
//	public Integer getAcademicId() {
//		return academicId;
//	}
//
//	public void setAcademicId(Integer academicId) {
//		this.academicId = academicId;
//	}

}
