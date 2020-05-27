package cb.lms.CB_Lms.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cb.lms.CB_Lms.modal.DesciplineAcademic;
import cb.lms.CB_Lms.modal.Discipline;
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

	@Value("${ga.lms.syllabus.upload.dir}")
	String uploaddir;

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

	@PostMapping(value = "/upload/{disciplineId}")
	public ResponseEntity<Void> uploadFile(@PathVariable("disciplineId") Integer disciplineId,
			@RequestParam("file") MultipartFile file) throws IOException {
		System.out.println(String.format("File name '%s' uploaded successfully.", file.getOriginalFilename()));

		String fileName = file.getOriginalFilename();
		String url = uploaddir + fileName;
		FileOutputStream fos = null;
		try {
			File fileToSave = new File(url);
			fileToSave.createNewFile();
			fos = new FileOutputStream(fileToSave);
			fos.write(file.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
		} finally {
			if (fos != null) {
				fos.close();
			}
		}

		Discipline disp = (Discipline) service.findEntity(Discipline.class, "id", disciplineId);
		disp.setSyllabusFile(fileName);
		service.saveEntity(disp);

		return ResponseEntity.ok().build();
	}

	@GetMapping("/download/{fileName}")
	public ResponseEntity downloadFile1(@PathVariable("fileName") String fileName) throws IOException {

		String url = uploaddir + fileName;
		File file = new File(url);
		InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
				.contentType(MediaType.APPLICATION_OCTET_STREAM).contentLength(file.length()).body(resource);
		
		
		
	}

}
