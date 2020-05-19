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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;



@Entity
@Table(name = "SYLLABUS")
public class Syllabus implements IDataEntity{
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;

	@Column(name = "NAME", nullable = false)
	private String name;

	@Column(name = "DESC", nullable = true)
	private String desc;
	
	
	@OneToOne
	DesciplineAcademic desciplineAcademic;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "SYLLABUS_ID")
	List<Chapter> chapters;

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

	public DesciplineAcademic getDesciplineAcademic() {
		return desciplineAcademic;
	}

	public void setDesciplineAcademic(DesciplineAcademic desciplineAcademic) {
		this.desciplineAcademic = desciplineAcademic;
	}

	public List<Chapter> getChapters() {
		return chapters;
	}

	public void setChapters(List<Chapter> chapters) {
		this.chapters = chapters;
	}
	
	
	

}
