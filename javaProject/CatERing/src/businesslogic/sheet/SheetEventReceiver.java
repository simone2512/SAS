package businesslogic.sheet;

public interface SheetEventReceiver {

    void updateTaskAdded(SummarySheet currentSheet, Task t);

    void updateSheetResetted(SummarySheet ss);

    void updateTaskRemoved(Task t);

    void updateAssignementAdded(Task t);

    void updateAssignementRemoved(Task t);

    void updateAssignementModified(Task t);
}
