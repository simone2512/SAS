package businesslogic;

import businesslogic.calendar.CalendarManager;
import businesslogic.menu.MenuManager;
import businesslogic.recipe.RecipeManager;
import businesslogic.sheet.SheetManager;
import businesslogic.user.UserManager;

public class CatERing {
    private static CatERing singleInstance;

    public static CatERing getInstance() {
        if (singleInstance == null) {
            singleInstance = new CatERing();
        }
        return singleInstance;
    }

    private MenuManager menuMgr;
    private RecipeManager recipeMgr;
    private UserManager userMgr;
    private SheetManager sheetMgr;
    private CalendarManager calendarMgr;

    private CatERing() {
        menuMgr = new MenuManager();
        recipeMgr = new RecipeManager();
        userMgr = new UserManager();
        sheetMgr = new SheetManager();
        calendarMgr = new CalendarManager();
    }

    public SheetManager getSheetManager() {return sheetMgr; }

    public MenuManager getMenuManager() {
        return menuMgr;
    }

    public RecipeManager getRecipeManager() {
        return recipeMgr;
    }

    public UserManager getUserManager() {
        return userMgr;
    }

    public CalendarManager getCalendarManager() {return calendarMgr;
    }
}
