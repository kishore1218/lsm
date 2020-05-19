package cb.lms.CB_Lms.controller;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;


import cb.lms.CB_Lms.modal.Role;
import cb.lms.CB_Lms.modal.RoleModules;
import cb.lms.CB_Lms.service.ICommonService;
import cb.lms.CB_Lms.service.IUserService;
import cb.lms.CB_Lms.service.JWTService;
import cb.lms.CB_Lms.service.UserModuleService;
import cb.lms.CB_Lms.to.ModuleTo;
import cb.lms.CB_Lms.to.UserTo;

@RestController
public class UserController {
	
	@Autowired
	IUserService service;
	
	@Autowired
	ICommonService commonservice;
	
	@Autowired
	UserModuleService moduleService;
	
	@Autowired
	JWTService jwtService;
	
	@GetMapping("/supervisors")	
	public ResponseEntity<List<UserTo>> getAllSupervisors() {
		
		
		List<UserTo> entities=service.getAllSupervisors();
		
		if(entities==null){
			return new ResponseEntity<List<UserTo>>(HttpStatus.NOT_FOUND);
		}	
        return new ResponseEntity<List<UserTo>>(entities, HttpStatus.OK);		
	} 
	
	@GetMapping("/supervisorUsers")	
	public ResponseEntity<List<UserTo>> getAllSupervisorUsers(HttpServletRequest request) {
		
		
		Integer supervisorId=getUserIdfromRequest(request);
		
		List<UserTo> entities=service.getAllSupervisorUsers(supervisorId);
		
		if(entities==null){
			return new ResponseEntity<List<UserTo>>(HttpStatus.NOT_FOUND);
		}	
        return new ResponseEntity<List<UserTo>>(entities, HttpStatus.OK);		
	} 
	
	/**
	 * 
	 * @param roleId
	 * @return
	 */
	@GetMapping("/userModules/{roleId}")
	public ResponseEntity<List<ModuleTo>> getAllEntities(@PathVariable("roleId")Integer roleId) {	
	
		
		List<ModuleTo> entities=moduleService.getRoleModules(roleId);
		
		if(entities==null){
			return new ResponseEntity<List<ModuleTo>>(HttpStatus.NOT_FOUND);
		}	
        return new ResponseEntity<List<ModuleTo>>(entities, HttpStatus.OK);		
	}
	
	/**
	 * 
	 * @param role
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/addRoleModule")
	public ResponseEntity<Void> addRoleModule(@RequestBody RoleModules role) throws Exception{
		
		
		try {
			commonservice.saveEntity(role);
			moduleService.buildRoleModulesMap();
		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
		}	
		
		return new ResponseEntity<Void>(HttpStatus.CREATED);		
	}
	
	@DeleteMapping("/deleteroleModule/{roleId}/{moduleId}")
	public ResponseEntity<Void> deleteEntity(@PathVariable("roleId") Integer roleId,
			@PathVariable("moduleId") Integer moduleId) {

		
		try {
			service.deleteRoleModule(roleId, moduleId);
			moduleService.buildRoleModulesMap();
		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
		}

		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}
	
	private Integer getUserIdfromRequest(HttpServletRequest request) {

		final String requestTokenHeader = request.getHeader("authorization");
		Integer userId=null;

		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {

			String jwtToken = requestTokenHeader.substring(7);
			
			userId=(Integer)jwtService.getUserIdFromClaim(jwtToken);

		}
		return userId;
	}

}
