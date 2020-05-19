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


/**
 * 
 * @author 1595812
 *
 */
@Entity
@Table(name = "GA_ROLES")
public class Role implements IDataEntity{
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;
	
	@Column(name = "ROLE_NAME", nullable = false)
	private String name;
	
	@Column(name = "ROLE_DESC", nullable = true)
	private String desc;
	
	
	@ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinTable(
	  name = "ROLE_MODULES", 
	  joinColumns = @JoinColumn(name = "ROLE_ID"), 
	  inverseJoinColumns = @JoinColumn(name = "MODULE_ID"))	
	private List<Module> modules;

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

	public List<Module> getModules() {
		return modules;
	}

	public void setModules(List<Module> modules) {
		this.modules = modules;
	}
	
	

}
