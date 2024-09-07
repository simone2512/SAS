package businesslogic.sheet;

import businesslogic.CatERing;
import businesslogic.UseCaseLogicException;
import businesslogic.calendar.Workshift;
import businesslogic.event.ServiceInfo;
import businesslogic.user.User;

import java.util.ArrayList;
import java.util.Calendar;

public class SummarySheet {
    private int id;
    private ArrayList<Task> tasks;
    private ServiceInfo service;

    public SummarySheet(ServiceInfo service) {
        this.service = service;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setService(ServiceInfo service) {
        this.service = service;
    }

    public ServiceInfo getService() {
        return service;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void remove(Task t) {
    }




    public Task addAssignementTask(Task t, Workshift workshift, User cook, int time_estimate, int doses){
        if(cook!=null && cook.isAvailable(workshift) && !workshift.isFull()){
            t.setCook(cook);
            t.setWorkshift(workshift);
        }
        else if(cook == null)
            t.setWorkshift(workshift);
        else if((cook==null && t.getCook()==null) || (cook==null && t.getCook()==null && t.getCook().isAvailable(workshift) && !workshift.isFull()))
            t.setWorkshift(workshift);
        if(time_estimate!=0){
            t.setTime(time_estimate);
        }
        if(doses!=0){
            t.setDoses(doses);
        }
        return t;
    }


    public boolean hasTask(Task t) {
        return true;
        //TODO
    }

    public Task removeAssignementTask(Task t) {
        if(t.getCook()!=null)
            t.setCook(null);
        if(t.getWorkshift()!=null)
            t.setWorkshift(null);
        if(t.getTime()!=0)
            t.setTime(0);
        if(t.getDoses()!=0)
            t.setDoses(0);
        return t;
    }

    public Task setDosesToReady(Task t) {
        t.setDoses(0);
        return t;
    }

    public Task addTask(CulinaryProcess cp) {
        Task t = new Task(cp);
        tasks.add(t);
        return t;
    }

    public Task removeTask(Task t) {
        tasks.remove(t);
        return t;
    }

    public void updateSheetCreated(SummarySheet currentSheet, SummarySheet ss) {
    }
}
