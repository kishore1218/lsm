package cb.lms.CB_Lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import cb.lms.CB_Lms.modal.DesciplineAcademic;
import cb.lms.CB_Lms.modal.Faculty;
import cb.lms.CB_Lms.modal.FacultyAcademics;
import cb.lms.CB_Lms.service.AcademicDisciplineService;
import cb.lms.CB_Lms.service.ICommonService;

@RestController
public class AcademicDisciplineController {

	@Autowired
	private AcademicDisciplineService acaService;

	@Autowired
	ICommonService service;

	
	/**
	 * 
	 * @param dispId
	 * @param acaId
	 * @return
	 */
	@DeleteMapping("/Ga/academicDescipline/{dispId}/{acaId}")
	public ResponseEntity<Void> deleteEntity(@PathVariable("dispId") Integer dispId,
			@PathVariable("acaId") Integer acaId) {

		DesciplineAcademic entity = acaService.getAcademicDiscipline(dispId, acaId);

		try {
			service.deleteEntity(entity);

		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
		}

		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}

	
	@DeleteMapping("/Ga/facultyacademic/{facultyId}/{dispacaId}")
	public ResponseEntity<Void> deleteFacultyAcademic(@PathVariable("facultyId") Integer facultyId,
			@PathVariable("dispacaId") Integer dispacaId) {

		FacultyAcademics entity = acaService.getFacultyAcademics(facultyId, dispacaId);

		try {
			service.deleteEntity(entity);

		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
		}

		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}
	
	/**
	 * 
	 * @param dispId
	 * @param acaId
	 * @return
	 */
	@GetMapping("/Ga/academicDescipline/{dispId}/{acaId}")
	public ResponseEntity<DesciplineAcademic> getAcademicDiscipline(@PathVariable("dispId") Integer dispId,
			@PathVariable("acaId") Integer acaId) {

		DesciplineAcademic entity = null;
		try {
			entity = acaService.getAcademicDiscipline(dispId, acaId);
			if (entity == null) {
				return new ResponseEntity<DesciplineAcademic>(HttpStatus.NOT_FOUND);
			}
		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			return new ResponseEntity<DesciplineAcademic>(HttpStatus.NOT_ACCEPTABLE);
		}

		return new ResponseEntity<DesciplineAcademic>(HttpStatus.OK);
	}
	


	
	/**
	 * 
	 * @param facultyId
	 * @param dispId
	 * @param acaId
	 * @return
	 */
	@PostMapping("/Ga/facultyAcademics/{facultyId}/{dispId}/{acaId}")
	public ResponseEntity<Void> addFacultyAcademics(@PathVariable("facultyId") Integer facultyId,
			@PathVariable("dispId") Integer dispId, @PathVariable("acaId") Integer acaId) {

		DesciplineAcademic entity = null;
		try {
			entity = acaService.getAcademicDiscipline(dispId, acaId);
			if (entity == null) {
				return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
			} else {

				FacultyAcademics fAcademics = new FacultyAcademics();
				Faculty faculty = new Faculty();
				faculty.setId(facultyId);
				fAcademics.setFaculty(faculty);

				DesciplineAcademic academicDiscipline = new DesciplineAcademic();
				academicDiscipline.setId(entity.getId());
				fAcademics.setAcademicDispcline(academicDiscipline);
				service.saveEntity(fAcademics);

			}
		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
		}

		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
