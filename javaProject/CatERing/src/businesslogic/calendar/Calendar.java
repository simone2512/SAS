package businesslogic.calendar;

import java.util.ArrayList;
import java.util.Date;

public class Calendar {
    private int id;
    private String category;
    private int expiration;
    private int duration;
    private String place;
    private Date starting_date;
    private Date ending_date;
    private ArrayList<Workshift> workshifts;
    private ArrayList<Grouping> groupings;

    public void setKitchenSpecifications(int duration, String place) {
        this.duration=duration;
        this.place=place;
    }

    public String getCategory() {
        //TODO
    }

}
