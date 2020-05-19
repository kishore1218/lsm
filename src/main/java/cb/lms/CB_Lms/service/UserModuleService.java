package cb.lms.CB_Lms.service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cb.lms.CB_Lms.modal.IDataEntity;
import cb.lms.CB_Lms.modal.ModuleActions;
import cb.lms.CB_Lms.modal.Role;
import cb.lms.CB_Lms.to.ModuleTo;

@Service
@Transactional
public class UserModuleService {

	@Autowired
	ICommonService service;

	private Map<Integer, Set<String>> roleActions = null;
	
	private Map<Integer, List<ModuleTo>> roleModules=null;

	@PostConstruct
	public void init() {

//		buildRoleModulesMap();

	}

	public void buildRoleModulesMap() {

		Set<IDataEntity> roles = service.findAll(Role.class, null, "id", false);

		if (roles != null) {

			//build role Allowed actions
			roleActions = roles.stream().map((IDataEntity t) -> (Role) t)
					.collect(Collectors.toMap(Role::getId, role -> {

						return role.getModules().stream().flatMap(module -> module.getActions().stream())
								.map(ModuleActions::getAction).collect(Collectors.toSet());

					}));
			
			//build role modules
			roleModules=roles.stream().map((IDataEntity t)->(Role)t).collect(Collectors.toMap(Role::getId, role->{
				
				return role.getModules().stream().map(module->{
				    	ModuleTo to=new ModuleTo();
				    	to.setId(module.getId());
				    	to.setPath(module.getPath());
				    	to.setDescription(module.getDescription());
				    	to.setModuleName(module.getModuleName());
				    	to.setIcon(module.getIcon());
				    	return to;
				    }).collect(Collectors.toList());
					
			}));

		}	

	}
	
	
	public List<ModuleTo> getRoleModules(Integer roleId){
		
		if(roleActions==null || roleModules==null) {
			buildRoleModulesMap();
		}
		
		return roleModules.get(roleId);
		
		
	}
	
	 
	
	

}
