package cb.lms.CB_Lms.util;

import cb.lms.CB_Lms.modal.Role;
import cb.lms.CB_Lms.modal.RoleModules;
import cb.lms.CB_Lms.modal.Syllabus;
import cb.lms.CB_Lms.modal.TimeSheet;
import cb.lms.CB_Lms.modal.AcademicYear;
import cb.lms.CB_Lms.modal.CostCenter;
import cb.lms.CB_Lms.modal.DesciplineAcademic;
import cb.lms.CB_Lms.modal.Discipline;
import cb.lms.CB_Lms.modal.Faculty;
import cb.lms.CB_Lms.modal.FacultyAcademics;
import cb.lms.CB_Lms.modal.Module;

/**
 * 
 * @author 1595812
 *
 */
public class ResourceEntityMapper {

	/**
	 * 
	 * @param resource
	 * @return
	 */
	public static Class getEntity(String resource) {

		if (resource.equals("role") || resource.equals("roles")) {

			return Role.class;

		} else if (resource.equals("faculty") || resource.equals("faculties")) {

			return Faculty.class;
		} else if (resource.equals("academic") || resource.equals("academics")) {

			return AcademicYear.class;
		} else if (resource.equals("discipline") || resource.equals("disciplines")) {

			return Discipline.class;
		} else if (resource.equals("academicDescipline") || resource.equals("academicDesciplines")) {

			return DesciplineAcademic.class;
		} else if (resource.equals("syllabus") || resource.equals("syllabuses")) {

			return Syllabus.class;
		} else if (resource.equals("timesheet") || resource.equals("timesheets")) {

			return TimeSheet.class;
		} else if (resource.equals("facultyacademic") || resource.equals("facultyacademics")) {

			return FacultyAcademics.class;
		} else if (resource.equals("costcenter") || resource.equals("costcenters")) {

			return CostCenter.class;
		} else if (resource.equals("module") || resource.equals("modules")) {

			return Module.class;
		} else if (resource.equals("roleModule") || resource.equals("roleModules")) {

			return RoleModules.class;
		} else {

			throw new RuntimeException("No matching Entity Found");
		}

	}

}
