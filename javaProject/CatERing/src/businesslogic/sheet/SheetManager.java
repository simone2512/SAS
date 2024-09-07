package businesslogic.sheet;

import businesslogic.CatERing;
import businesslogic.UseCaseLogicException;
import businesslogic.calendar.Workshift;
import businesslogic.event.EventInfo;
import businesslogic.event.ServiceInfo;
import businesslogic.menu.MenuItem;
import businesslogic.menu.Section;
import businesslogic.user.User;
import java.util.ArrayList;

public class SheetManager {
    private SummarySheet currentSheet;
    private ArrayList<SheetEventReceiver> receivers;

    public SummarySheet generateSummarySheet(ServiceInfo service, EventInfo event) throws UseCaseLogicException {
        User user = CatERing.getInstance().getUserManager().getCurrentUser();

        if(!user.isChef()){
            throw new UseCaseLogicException();
        }

        if(event!=null && event.isAssignedToChef(user) && (event.getMenuInUse()!=null) && event.hasServerice(service)) {
            SummarySheet ss = new SummarySheet(service);

            for (MenuItem fi : event.getMenuInUse().getFreeItems()) {
                Task t = new Task(fi.getItemRecipe());
                ss.getTasks().add(t);
            }

            for (Section s : event.getMenuInUse().getSections()) {
                for (MenuItem i : s.getItems()) {
                    Task t = new Task(i.getItemRecipe());
                    ss.getTasks().add(t);
                }
            }
            setCurrentSheet(ss);
            notifySheetCreated(ss);
            return ss;
        }
        throw new UseCaseLogicException();
    }

    private void notifySheetCreated(SummarySheet ss) {
        for(SheetEventReceiver er :this.receivers){
            ss.updateSheetCreated(currentSheet,ss)
        }
    }

    private void setCurrentSheet(SummarySheet ss) {
        this.currentSheet = ss;
    }

    public SummarySheet openSummarySheet(SummarySheet ss) throws UseCaseLogicException{
        User user = CatERing.getInstance().getUserManager().getCurrentUser();

        if(!user.isChef()){
            throw new UseCaseLogicException();
        }

        if(ss!=null)
            return ss;
        else
            throw new UseCaseLogicException();
    }

    public SummarySheet resetSummarySheet(SummarySheet ss, ServiceInfo service) throws UseCaseLogicException{
        User user = CatERing.getInstance().getUserManager().getCurrentUser();

        if(!user.isChef()){
            throw new UseCaseLogicException();
        }

        if(ss.getService()==service){
            ss.setService(null);
            for(Task t : currentSheet.getTasks()){
                ss.remove(t);
            }

            setCurrentSheet(ss);
            notifySheetResetted(ss);

            return ss;
        }
        throw  new UseCaseLogicException();
    }

    private void notifySheetResetted(SummarySheet ss) {
        for (SheetEventReceiver er :this.receivers){
            er.updateSheetResetted(ss);
        }
    }

    public SummarySheet getCurrentManager(){
        return this.currentSheet;
    }

    public Task addTask(CulinaryProcess cp) throws UseCaseLogicException {
        SheetManager sheetMgr = CatERing.getInstance().getSheetManager();
        if(sheetMgr!=null){
            Task t = sheetMgr.currentSheet.addTask(cp);
            notifyTaskAdded(t);
            return t;
        }
        throw new UseCaseLogicException();
    }

    private void notifyTaskAdded(Task t) {
        for (SheetEventReceiver er : this.receivers){
            er.updateTaskAdded(currentSheet, t);
        }
    }

    public Task deleteTask(Task t) throws UseCaseLogicException{
        SheetManager sheetMgr = CatERing.getInstance().getSheetManager();
        if(sheetMgr!=null){
            t = sheetMgr.currentSheet.removeTask(t);
            notifyTaskRemoved(t);
            return t;
        }
        throw new UseCaseLogicException();
    }

    private void notifyTaskRemoved(Task t) {
        for(SheetEventReceiver er :this.receivers){
            er.updateTaskRemoved(t);
        }
    }

    public Task assignTask(Task t, User cook, Workshift workshift, int time_estimate, int doses) throws UseCaseLogicException{
        SheetManager sheetMgr = CatERing.getInstance().getSheetManager();
        if(sheetMgr.currentSheet!=null && sheetMgr.currentSheet.hasTask(t)){
            t = currentSheet.addAssignementTask(t, workshift, cook, time_estimate, doses);
            notifyAssignementAdded(t);
            return t;
        }
        throw new UseCaseLogicException();
    }

    private void notifyAssignementAdded(Task t){
        for(SheetEventReceiver er :this.receivers){
            er.updateAssignementAdded(t);
        }
    }

    public Task deleteAssignementTask(Task t) throws UseCaseLogicException{
        SheetManager sheetMgr = CatERing.getInstance().getSheetManager();
        if(sheetMgr.currentSheet!=null && sheetMgr.currentSheet.hasTask(t)){
            t = currentSheet.removeAssignementTask(t);
            notifyAssignementRemoved(t);
            return t;
        }
        throw new UseCaseLogicException();
    }

    private void notifyAssignementRemoved(Task t) {
        for(SheetEventReceiver er :this.receivers){
            er.updateAssignementRemoved(t);
        }
    }

    public Task setDosesToReady(Task t) throws UseCaseLogicException{
        SheetManager sheetMgr = CatERing.getInstance().getSheetManager();
        if(sheetMgr.currentSheet!=null && sheetMgr.currentSheet.hasTask(t)){
            t = currentSheet.setDosesToReady(t);
            notifyAssignementModified(t);
            return t;
        }
        throw new UseCaseLogicException;
    }

    private void notifyAssignementModified(Task t) {
        for(SheetEventReceiver er :this.receivers){
            er.updateAssignementModified(t);
        }
    }
}
