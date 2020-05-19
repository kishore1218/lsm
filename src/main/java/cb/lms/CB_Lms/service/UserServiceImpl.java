package cb.lms.CB_Lms.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cb.lms.CB_Lms.dao.IUserDao;
import cb.lms.CB_Lms.modal.Faculty;
import cb.lms.CB_Lms.to.UserTo;


/**
 * 
 * @author 1595812
 *
 */
@Service
@Transactional
public class UserServiceImpl implements IUserService{
	
	@Autowired
	IUserDao userDao;
	
	
	/**
	 * 
	 */
	public Faculty retrieveUserByCredentials(String emailId,String pwd) {
		
		return userDao.retrieveUserByCredentials(emailId, pwd);
		
	}
	
	public List<UserTo> getAllSupervisors() {
		
		return userDao.getAllSupervisors();
	}
	
	/**
	 * 
	 * @param supervisorId
	 * @return
	 */
	public List<UserTo> getAllSupervisorUsers(Integer supervisorId) {
		
		return userDao.getAllSupervisorUsers(supervisorId);
	}
	
	
	public void deleteRoleModule(Integer roleId,Integer moduleId) {
		userDao.deleteRoleModule(roleId, moduleId);
	}
}
