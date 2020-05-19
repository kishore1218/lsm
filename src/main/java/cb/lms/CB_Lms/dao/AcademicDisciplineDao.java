package cb.lms.CB_Lms.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;


import cb.lms.CB_Lms.modal.DesciplineAcademic;
import cb.lms.CB_Lms.modal.FacultyAcademics;



@Repository
public class AcademicDisciplineDao extends GenericDao {
	
	/**
	 * 
	 * @param softwareId
	 * @param envId
	 * @return
	 */
	public DesciplineAcademic getAcademicDiscipline(Integer dispId, Integer academicId) {

		TypedQuery<DesciplineAcademic> query = getEntityManager().createNamedQuery("getAcademicDiscipline",
				DesciplineAcademic.class);
		query.setParameter("dispId", dispId);
		query.setParameter("academic", academicId);
		List<DesciplineAcademic> results = query.getResultList();

		if (results == null || results.isEmpty()) {
			return null;
		}

		return results.get(0);
	}
	
	/**
	 * 
	 * @param facultyId
	 * @param dispacaId
	 * @return
	 */
	public FacultyAcademics getFacultyAcademics(Integer facultyId, Integer dispacaId) {

		TypedQuery<FacultyAcademics> query = getEntityManager().createNamedQuery("getFacultyAcademics",
				FacultyAcademics.class);
		query.setParameter("facultyId", facultyId);
		query.setParameter("dispacaId", dispacaId);
		List<FacultyAcademics> results = query.getResultList();

		if (results == null || results.isEmpty()) {
			return null;
		}

		return results.get(0);
	}

	
}
