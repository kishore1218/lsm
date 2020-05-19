package cb.lms.CB_Lms.dao;


import java.util.List;

import cb.lms.CB_Lms.modal.Faculty;
import cb.lms.CB_Lms.to.UserTo;




/**
 * 
 * @author 1595812
 *
 */
public interface IUserDao {
	
	/**
	 * 
	 * @param emailId
	 * @param pwd
	 * @return
	 */
	public Faculty retrieveUserByCredentials(String emailId,String pwd);
	
	
	/**
	 * 
	 * @return
	 */
	public List<UserTo> getAllSupervisors();
	
	/**
	 * 
	 * @param supervisorId
	 * @return
	 */
	public List<UserTo> getAllSupervisorUsers(Integer supervisorId);
	
	
	/**
	 * 
	 * @param roleId
	 * @param moduleId
	 */
	public void deleteRoleModule(Integer roleId,Integer moduleId);
	

}
