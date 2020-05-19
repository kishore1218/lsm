package cb.lms.CB_Lms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cb.lms.CB_Lms.dao.AcademicDisciplineDao;
import cb.lms.CB_Lms.modal.DesciplineAcademic;
import cb.lms.CB_Lms.modal.Faculty;
import cb.lms.CB_Lms.modal.FacultyAcademics;

@Service
public class AcademicDisciplineService {

	@Autowired
	AcademicDisciplineDao dao;

	public DesciplineAcademic getAcademicDiscipline(Integer dispId, Integer academicId) {

		return dao.getAcademicDiscipline(dispId, academicId);
	}
	
	public FacultyAcademics getFacultyAcademics(Integer facultyId, Integer dispacaId) {
		
		return dao.getFacultyAcademics(facultyId, dispacaId);
		
	}
	
	
	public List<String> getFacultyActivities(Integer facultyId){
		
		List<String> activities=new ArrayList<String>();
		activities.add("Vacation");
		activities.add("Sick Leave");
		activities.add("Training");
		activities.add("Public Holiday");
		
		Faculty faculty=dao.findById(Faculty.class, "id", facultyId);
		
		if(faculty.getAcademics()!=null) {
			
			faculty.getAcademics().forEach(academic->{
				activities.add(academic.getDiscipline().getName()+" ( "+academic.getAcademic().getName()+" )");
			});
		}
		
		return activities;
	}

}
