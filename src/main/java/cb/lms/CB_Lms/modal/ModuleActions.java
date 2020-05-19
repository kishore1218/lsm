package cb.lms.CB_Lms.modal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "GA_MODULE_ACTIONS")
public class ModuleActions implements IDataEntity{

	@Id
	public Integer id;

	@Column(name = "ACTION", nullable = false)
	String action;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

}
