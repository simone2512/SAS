package businesslogic.sheet;

import java.util.ArrayList;

public class SheetManager {
    private SummarySheet currentSheet;
    private ArrayList<SheetEventReceiver> eventReceivers;
}
