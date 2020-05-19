package cb.lms.CB_Lms.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cb.lms.CB_Lms.dao.ICommonEntityDao;
import cb.lms.CB_Lms.dao.TimesheetDao;
import cb.lms.CB_Lms.modal.CostCenter;
import cb.lms.CB_Lms.modal.DayWork;
import cb.lms.CB_Lms.modal.Faculty;
import cb.lms.CB_Lms.modal.IDataEntity;
import cb.lms.CB_Lms.modal.TimeSheet;
import cb.lms.CB_Lms.to.TimesheetTo;
import cb.lms.CB_Lms.util.BusinessConstants;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;

@Service
@Transactional
public class TimesheetService {

	@Autowired
	private ICommonEntityDao dao;

	@Autowired
	private TimesheetDao timesheetDao;

	/**
	 * 
	 * @param month
	 * @param year
	 */
	public void genereateTimeSheet(Integer month, Integer year) {

		Set<IDataEntity> faculties = dao.findAll(Faculty.class, null, "id", true);

		CostCenter costCenter = new CostCenter();

		if (faculties != null) {
			faculties.forEach(faculty -> {
	
				createTimesheet(costCenter, month, year, (Faculty) faculty);
			});
		}

		dao.saveEntity(costCenter);

	}

	public List<TimesheetTo> getTimeSheetsByStatus(String status, Integer userId) {

		return timesheetDao.getTimeSheetsByStatus(status, userId);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public TimesheetTo getTimeSheetById(Integer id) {
		return timesheetDao.getTimeSheetsById(id);
	}
	
	/**
	 * 
	 * @param userId
	 * @param costId
	 * @return
	 */
	public List<TimesheetTo> getUserTimeSheetsBycostId(Integer userId,Integer costId) {
		return timesheetDao.getUserTimeSheetsBycostId(userId,costId);
	}
	
	

	/**
	 * 
	 * @param month
	 * @param year
	 * @param faculty
	 */
	private void createTimesheet(CostCenter costCenter, Integer month, Integer year, Faculty faculty) {
		
		
		if(faculty.getRole().getName().equals(BusinessConstants.SUPER_ADMIN_ROLE)) {
			return ;
		}

		YearMonth ym = YearMonth.of(year, month);
		LocalDate currentMonth = ym.atDay(1);
		LocalDate nextMoth = ym.atEndOfMonth();
		int endDay = nextMoth.getDayOfMonth();

		costCenter.setMonth(currentMonth.getMonth().name());
		costCenter.setYear(year);

		List<DayWork> days = new ArrayList<DayWork>();

		while (!currentMonth.isAfter(nextMoth)) {
			DayWork day = new DayWork();
			Date date = Date.from(currentMonth.atStartOfDay(ZoneId.systemDefault()).toInstant());
			day.setDate(date);
			day.setHours(0);
			days.add(day);
			// System.out.print(currentMonth.getDayOfMonth()+ " " );
			if (currentMonth.getDayOfWeek().equals(DayOfWeek.SUNDAY) || (currentMonth.getDayOfMonth() == endDay)) {
				// System.out.println("");
				TimeSheet timesheet = new TimeSheet();
				timesheet.setFaculty(faculty);
				timesheet.setMonth(currentMonth.getMonth().name());
				timesheet.setYear(year);
				timesheet.setStatus(BusinessConstants.TIME_SHEET_STATUS_OPEN);

				timesheet.setStartDate(days.get(0).getDate());
				timesheet.setEndDay(Date.from(currentMonth.atStartOfDay(ZoneId.systemDefault()).toInstant()));

				timesheet.setDayWorks(new ArrayList<DayWork>(days));
				if (costCenter.getTimesheets() == null) {
					costCenter.setTimesheets(new ArrayList<TimeSheet>());
				}
				costCenter.getTimesheets().add(timesheet);
				days.clear();

			}
			currentMonth = currentMonth.plusDays(1);
		}

	}

	public List<TimesheetTo> getSupervisorTimeSheets(String status, Integer userId) {
		return timesheetDao.getSupervisorTimeSheets(status, userId);
	}
}
