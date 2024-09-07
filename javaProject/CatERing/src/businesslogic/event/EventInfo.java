package businesslogic.event;

import businesslogic.menu.Menu;
import businesslogic.user.User;

import java.util.ArrayList;

public class EventInfo {

    private int id;
    private String name;
    private String infos;
    private ArrayList<ServiceInfo> eventInfos;

    public boolean isAssignedToChef(User user) {
        // TODO
        return true;
    }

    public Menu getMenuInUse() {
        //TODO
        return null;
    }

    public boolean hasServerice(ServiceInfo service) {
        //TODO
        return true;
    }
}
