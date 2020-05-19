package cb.lms.CB_Lms.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import cb.lms.CB_Lms.modal.Faculty;
import cb.lms.CB_Lms.to.UserTo;

/**
 * 
 * @author 1595812
 *
 */

@Repository
public class UserDaoImpl extends GenericDao implements IUserDao {

	private static final String GET_ALL_SUPERVISORS = "from Faculty f where f.isSupervisor='Y'";

	private static final String GET_SUPERVISOR_USERS = "from Faculty f where f.supervisor=:supervisorId";

	private static final String REMOVE_ROLE_MODULE = "Delete from RoleModules rm where rm.role.id=:roleId and rm.module.id=:moduleId";

	/**
	 * 
	 * @param emailId
	 * @param pwd
	 * @return
	 */
	public Faculty retrieveUserByCredentials(String userName, String pwd) {

		TypedQuery<Faculty> query = getEntityManager().createNamedQuery("retrieveUserByCredentials", Faculty.class);
		query.setParameter("userName", userName);
		query.setParameter("password", pwd);
		List<Faculty> results = query.getResultList();

		if (results == null || results.isEmpty()) {
			return null;
		}

		return results.get(0);
	}

	/**
	 * 
	 */
	public List<UserTo> getAllSupervisors() {

		TypedQuery<Faculty> query = getEntityManager().createQuery(GET_ALL_SUPERVISORS, Faculty.class);

		List<Faculty> results = query.getResultList();

		List<UserTo> supervisors = new ArrayList<UserTo>();

		if (results != null) {
			results.forEach(faculty -> {
				UserTo to = new UserTo();
				to.setId(faculty.getId());
				to.setFirstName(faculty.getFirstName());
				to.setLastName(faculty.getLastName());

				supervisors.add(to);

			});
			return supervisors;
		}
		return null;
	}

	/**
	 * 
	 * @param supervisorId
	 * @return
	 */
	public List<UserTo> getAllSupervisorUsers(Integer supervisorId) {

		TypedQuery<Faculty> query = getEntityManager().createQuery(GET_SUPERVISOR_USERS, Faculty.class);
		query.setParameter("supervisorId", supervisorId);
		List<Faculty> results = query.getResultList();

		List<UserTo> supervisors = new ArrayList<UserTo>();

		if (results != null) {
			results.forEach(faculty -> {
				UserTo to = new UserTo();
				to.setId(faculty.getId());
				to.setFirstName(faculty.getFirstName());
				to.setLastName(faculty.getLastName());

				supervisors.add(to);

			});
			return supervisors;
		}
		return null;
	}

	public void deleteRoleModule(Integer roleId, Integer moduleId) {

		Query query = getEntityManager().createQuery(REMOVE_ROLE_MODULE);
		query.setParameter("roleId", roleId);
		query.setParameter("moduleId", moduleId);

		query.executeUpdate();

	}

}
