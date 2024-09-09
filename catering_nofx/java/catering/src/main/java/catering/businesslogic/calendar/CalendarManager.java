package businesslogic.calendar;

import businesslogic.CatERing;
import businesslogic.UseCaseLogicException;
import businesslogic.sheet.SheetManager;

import java.util.ArrayList;
import java.util.Date;

public class CalendarManager {
    private Calendar currentcalendar;
    private ArrayList<CalendarEventReceiver> calendarEventReceivers;

    public void setKitchenSpecifications (int duration, String place ) throws UseCaseLogicException{
        CalendarManager calendarMgr = CatERing.getInstance().getCalendarManager();
        if (calendarMgr.currentcalendar!= null && calendarMgr.currentcalendar.getCategory()=="kitchen")
            currentcalendar.setKitchenSpecifications(duration,place);
        notifyKitchenSpecificationsModified();
        throw new UseCaseLogicException();
    }

    private void notifyKitchenSpecificationsModified() {
        //TODO
    }
    public Workshift createWorkshift(Date date, int started_time, int duration , int bonus_time , String place){
        CalendarManager calendarMgr = CatERing.getInstance().getCalendarManager();
        calendarMgr.currentcalendar.addWorkshift(date,started_time,duration,bonus_time,place);
    }
}
