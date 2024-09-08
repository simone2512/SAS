package businesslogic.sheet;

import businesslogic.calendar.Workshift;
import businesslogic.user.User;

public class Task {
    private  int id;
    private int time;
    private int doses;
    private CulinaryProcess culinaryProcess;
    private Workshift workshift;
    private User cook;

    public Task(CulinaryProcess culinaryProcess) {
        this.culinaryProcess = culinaryProcess;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setDoses(int doses) {
        this.doses = doses;
    }

    public void setCulinaryProcess(CulinaryProcess culinaryProcess) {
        this.culinaryProcess = culinaryProcess;
    }

    public void setWorkshift(Workshift workshift) {
        this.workshift = workshift;
    }

    public void setCook(User cook) {
        this.cook = cook;
    }

    public User getCook() {
        return this.cook;
    }

    public int getTime() {
        return time;
    }

    public int getDoses() {
        return doses;
    }

    public Workshift getWorkshift() {
        return workshift;
    }
}
