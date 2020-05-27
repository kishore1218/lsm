package cb.lms.CB_Lms.modal;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "DESCIPLINES")
public class Discipline implements IDataEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;

	@Column(name = "NAME", nullable = false)
	private String name;

	@Column(name = "DESC", nullable = true)
	private String desc;
	
	@Column(name = "SYLLABUS_FILE", nullable = true)
	private String syllabusFile;
	
	
	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinTable(
	  name = "DESCP_ACADEMIC_YEAR", 
	  joinColumns = @JoinColumn(name = "DESP_ID"), 
	  inverseJoinColumns = @JoinColumn(name = "ACD_YEAR_ID"))	
	private List<AcademicYear> academics;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public List<AcademicYear> getAcademics() {
		return academics;
	}

	public void setAcademics(List<AcademicYear> academics) {
		this.academics = academics;
	}

	public String getSyllabusFile() {
		return syllabusFile;
	}

	public void setSyllabusFile(String fileName) {
		this.syllabusFile = fileName;
		
	}

}
