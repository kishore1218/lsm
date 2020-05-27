package cb.lms.CB_Lms.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import cb.lms.CB_Lms.modal.CostCenter;
import cb.lms.CB_Lms.modal.TimeSheet;
import cb.lms.CB_Lms.to.DayworkTo;
import cb.lms.CB_Lms.to.TimesheetTo;
import cb.lms.CB_Lms.util.BusinessConstants;


@Repository
public class TimesheetDao extends GenericDao{
	
	
	public static final String TIMESHEET_BY_STATUS_QUERY="from TimeSheet t where t.status=:astatus and t.faculty.id=:id";
	
	public static final String SUPERVISOR_TIMESHEETS_QUERY="from TimeSheet t where t.status=:astatus and t.faculty.supervisor=:supid";
	
	public static final String USER_TIMESHEETS_BY_COSTID_QUERY="from CostCenter as c left join fetch c.timesheets as t where t.status=:astatus and t.faculty.id=:id and c.id=:costId";
	
	
	public List<TimesheetTo> getTimeSheetsByStatus(String status,Integer userId){
		
		TypedQuery<TimeSheet> query  = getEntityManager().createQuery(TIMESHEET_BY_STATUS_QUERY,TimeSheet.class);
		query.setParameter("astatus", status);
		query.setParameter("id", userId);
		List<TimeSheet> results=query.getResultList();		
		
		
		if(results!=null) {
			
			List<TimesheetTo> tos=new ArrayList<TimesheetTo>();
			results.forEach(timesheet->{
				
				TimesheetTo to=new TimesheetTo();
				to.setId(timesheet.getId());
				to.setStatus(timesheet.getStatus());
				to.setPeriod(timesheet.getPeriod());
				to.setFacultyId(timesheet.getFaculty().getId());
				to.setComments(timesheet.getComments());
				to.setFacultyName(timesheet.getFaculty().getFirstName()+" "+timesheet.getFaculty().getLastName());
				tos.add(to);
			});
			
			return tos;
			
		}
		return null;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public TimesheetTo getTimeSheetsById(Integer id) {

		TimeSheet timesheet = findById(TimeSheet.class, "id", id);

		if (timesheet != null) {

			TimesheetTo to = new TimesheetTo();
			to.setId(timesheet.getId());
			to.setStatus(timesheet.getStatus());
			to.setPeriod(timesheet.getPeriod());
			to.setFacultyId(timesheet.getFaculty().getId());
			to.setComments(timesheet.getComments());
			to.setFacultyName(timesheet.getFaculty().getFirstName() + " " + timesheet.getFaculty().getLastName());
			
			if(timesheet.getDayWorks()!=null) {
				
				List<DayworkTo> days=timesheet.getDayWorks().stream().map(daywork->{
					DayworkTo dayto=new DayworkTo();
					dayto.setId(daywork.getId());
					dayto.setActivity(daywork.getActivity());
					dayto.setHours(daywork.getHours());
					dayto.setComments(daywork.getComments());
					dayto.setDate(daywork.getDate());
					return dayto;
				}).collect(Collectors.toList());
				
				to.setDayswork(days);
			}
		return to;
		}
		return null;
	}
	
	/**
	 * 
	 * @param userId
	 * @param costId
	 * @return
	 */
	public List<TimesheetTo> getUserTimeSheetsBycostId(Integer userId,Integer costId) {

		TypedQuery<CostCenter> query  = getEntityManager().createQuery(USER_TIMESHEETS_BY_COSTID_QUERY,CostCenter.class);
		query.setParameter("astatus", BusinessConstants.TIME_SHEET_STATUS_APPROVED);
		query.setParameter("costId", costId);
		query.setParameter("id", userId);
		CostCenter costCenter=query.getSingleResult();
		
		
		
		List<TimesheetTo> tos=null;
		
		if(costCenter.getTimesheets()!=null) {
			
			tos=costCenter.getTimesheets().stream().map(timesheet->{
				TimesheetTo to = new TimesheetTo();
				to.setId(timesheet.getId());
				to.setStatus(timesheet.getStatus());
				to.setPeriod(timesheet.getPeriod());
				to.setFacultyId(timesheet.getFaculty().getId());
				to.setComments(timesheet.getComments());
				to.setFacultyName(timesheet.getFaculty().getFirstName() + " " + timesheet.getFaculty().getLastName());
				
				if(timesheet.getDayWorks()!=null) {
					
					List<DayworkTo> days=timesheet.getDayWorks().stream().map(daywork->{
						DayworkTo dayto=new DayworkTo();
						dayto.setId(daywork.getId());
						dayto.setActivity(daywork.getActivity());
						dayto.setHours(daywork.getHours());
						dayto.setComments(daywork.getComments());
						dayto.setDate(daywork.getDate());
						return dayto;
					}).collect(Collectors.toList());
					
					to.setDayswork(days);
				}
			return to;
			}).collect(Collectors.toList());
		}
			
		return tos;
	}
	
	/**
	 * 
	 * @param status
	 * @param userId
	 * @return
	 */
	public List<TimesheetTo> getSupervisorTimeSheets(String status,Integer userId){
		
		TypedQuery<TimeSheet> query  = getEntityManager().createQuery(SUPERVISOR_TIMESHEETS_QUERY,TimeSheet.class);
		query.setParameter("astatus", status);
		query.setParameter("supid", userId);
		List<TimeSheet> results=query.getResultList();
		
		
		
		if(results!=null) {
			
			List<TimesheetTo> tos=new ArrayList<TimesheetTo>();
			results.forEach(timesheet->{
				
				TimesheetTo to=new TimesheetTo();
				to.setId(timesheet.getId());
				to.setStatus(timesheet.getStatus());
				to.setPeriod(timesheet.getPeriod());
				to.setFacultyId(timesheet.getFaculty().getId());
				to.setComments(timesheet.getComments());
				to.setFacultyName(timesheet.getFaculty().getFirstName()+" "+timesheet.getFaculty().getLastName());
				tos.add(to);
			});
			
			return tos;
			
		}
		return null;
		
	}

}
