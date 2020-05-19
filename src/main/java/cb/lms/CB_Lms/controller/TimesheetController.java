package cb.lms.CB_Lms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



import cb.lms.CB_Lms.modal.TimeSheet;
import cb.lms.CB_Lms.service.AcademicDisciplineService;
import cb.lms.CB_Lms.service.CommonService;
import cb.lms.CB_Lms.service.JWTService;
import cb.lms.CB_Lms.service.TimesheetService;
import cb.lms.CB_Lms.to.TimesheetTo;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class TimesheetController {

	@Autowired
	TimesheetService timesheetService;

	@Autowired
	CommonService service;
	
	
	@Autowired
	JWTService jwtService;
	
	@Autowired
	private AcademicDisciplineService acaService;

	/**
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	@PostMapping("/Ga/generateTimesheets/{year}/{month}")
	public ResponseEntity<Void> generateTimeSheets(@PathVariable Integer year, @PathVariable Integer month) {
		try {
			timesheetService.genereateTimeSheet(month, year);
		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
		}

		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@GetMapping("/timesheetByStatus/{status}")
	public ResponseEntity<List<TimesheetTo>> getTimesheetByStatus(@PathVariable("status") String status,
			HttpServletRequest request) {

		List<TimesheetTo> entities = null;
		try {
			entities = timesheetService.getTimeSheetsByStatus(status, getUserIdfromRequest(request));
			if (entities == null) {
				return new ResponseEntity<List<TimesheetTo>>(HttpStatus.NOT_FOUND);
			}
		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			return new ResponseEntity<List<TimesheetTo>>(HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<List<TimesheetTo>>(entities, HttpStatus.OK);

	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/timesheet/{id}")
	public ResponseEntity<TimesheetTo> getEntity(@PathVariable("id") Integer id) {		

		
		TimesheetTo entity=timesheetService.getTimeSheetById(id);
		
		if(entity==null){
			return new ResponseEntity<TimesheetTo>(HttpStatus.NOT_FOUND);
		}	
        return new ResponseEntity<TimesheetTo>(entity, HttpStatus.OK);		
	}	
	
	@GetMapping("/timesheetreport/{userId}/{costId}")
	public ResponseEntity<List<TimesheetTo>> getTimesheetForUserByMonth(@PathVariable("userId") Integer userId,@PathVariable("costId") Integer costId) {		

		
		List<TimesheetTo> entities=timesheetService.getUserTimeSheetsBycostId(userId,costId);
		
		if(entities==null){
			return new ResponseEntity<List<TimesheetTo>>(HttpStatus.NOT_FOUND);
		}	
        return new ResponseEntity<List<TimesheetTo>>(entities, HttpStatus.OK);		
	}
	
	
	@GetMapping("/timesheetActivities")
	public ResponseEntity<List<String>> getFaultyActivities(HttpServletRequest request) {

		List<String> activities = null;
		try {
			activities = acaService.getFacultyActivities(getUserIdfromRequest(request));
			if (activities == null) {
				return new ResponseEntity<List<String>>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<String>>(HttpStatus.NOT_ACCEPTABLE);
		}

		return new ResponseEntity<List<String>>(activities,HttpStatus.OK);
	}
	
	


	@GetMapping("/supervisorTimesheets/{status}")
	public ResponseEntity<List<TimesheetTo>> supervisorTimesheets(@PathVariable("status") String status,
			HttpServletRequest request) {

		List<TimesheetTo> entities = null;
		try {
			entities = timesheetService.getSupervisorTimeSheets(status, getUserIdfromRequest(request));
			if (entities == null) {
				return new ResponseEntity<List<TimesheetTo>>(HttpStatus.NOT_FOUND);
			}
		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			return new ResponseEntity<List<TimesheetTo>>(HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<List<TimesheetTo>>(entities, HttpStatus.OK);

	}

	@PostMapping("/reviewtimesheet")
	public ResponseEntity<Void> approverejctTimesheet(@RequestBody TimesheetTo timesheet) {
		try {

			TimeSheet t = (TimeSheet) service.findEntity(TimeSheet.class, "id", timesheet.getId());

			t.setStatus(timesheet.getStatus());

			service.saveEntity(t);

		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
		}

		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@PostMapping("/submittimesheet")
	public ResponseEntity<Void> submitTimeSheet(@RequestBody TimesheetTo timesheet) {
		try {

			TimeSheet t = (TimeSheet) service.findEntity(TimeSheet.class, "id", timesheet.getId());

			t.setStatus(timesheet.getStatus());

			timesheet.getDayswork().forEach(to -> {

				t.getDayWorks().forEach(domain -> {
					if (domain.getId().equals(to.getId())) {
						domain.setHours(to.getHours());
						domain.setComments(to.getComments());
						domain.setActivity(to.getActivity());
					}
				});
			});

			service.saveEntity(t);

		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
		}

		return new ResponseEntity<Void>(HttpStatus.CREATED);
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
