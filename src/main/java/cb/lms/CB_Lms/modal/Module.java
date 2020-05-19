package cb.lms.CB_Lms.modal;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "GA_MODULES")
public class Module implements IDataEntity{

	@Id
	public Integer id;

	@Column(name = "NAME", nullable = false)
	String moduleName;

	@Column(name = "PATH", nullable = false)
	String path;

	@Column(name = "DESC", nullable = false)
	String description;

	@Column(name = "ICON", nullable = false)
	String icon;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "MODULE_ID")
	List<ModuleActions> actions;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}



	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public List<ModuleActions> getActions() {
		return actions;
	}

	public void setActions(List<ModuleActions> actions) {
		this.actions = actions;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
