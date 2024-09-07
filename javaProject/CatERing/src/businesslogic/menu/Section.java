package businesslogic.menu;

import java.util.ArrayList;

public class Section {
    private String name;
    private ArrayList<MenuItem> sectionItems;

    public Section(String name) {
        this.name = name;
        sectionItems = new ArrayList<>();
    }

    public String toString() {
        String result = name + "\n";
        for (MenuItem mi: sectionItems) {
            result += "\t" + mi.toString() + "\n";
        }
        return result;
    }
    public MenuItem[] getItems() {
        // Converte la lista in un array e lo restituisce
        return sectionItems.toArray(new MenuItem[0]);
    }

}
