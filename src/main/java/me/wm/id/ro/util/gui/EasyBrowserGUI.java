package me.wm.hybrid.ro.util.gui;

import org.bukkit.entity.Player;

import java.util.ArrayList;

public abstract class EasyBrowserGUI<OBJ> extends EasyGUI implements IEasyBrowserGUI<OBJ> {

    private int objectsPerPage = 36;
    private int page;
    private ArrayList<ArrayList<OBJ>> pages;

    public EasyBrowserGUI(Player p, GUIContents contents) {
        super(p, contents);
        this.page = 0;
    }

    public void refresh() {
        recalculate();
        sync();
        openGUI();
    }

    public void setObjectsPerPage(int objs) {
        this.objectsPerPage = objs;
    }

    public ArrayList<OBJ> getVisibleContent() {
        return this.pages.get(this.page);
    }

    private void recalculate() {
        ArrayList<OBJ> allObjects = loadObjects();
        this.pages = new ArrayList<>();

        Object currentPage = new ArrayList<OBJ>();
        for (Object object : allObjects) {
            if (((ArrayList) currentPage).size() == objectsPerPage) {
                this.pages.add((ArrayList<OBJ>) currentPage);
                currentPage = new ArrayList<OBJ>();
            }
            ((ArrayList) currentPage).add(object);
        }
        this.pages.add((ArrayList<OBJ>) currentPage);
    }

    public void sync() {
        removeAllButtons();
        loadAdditionalButtons();
        loadFillItems();

        ArrayList localArrayList = getVisibleContent();

        for (Object localObject : localArrayList) {
            Button localButton = forEach((OBJ) localObject);
            addButton(localButton);
        }

        if (this.page != 0) {
            putButton(getItem("Previous"), new Action() {
                public void run(Player paramAnonymousPlayer, ActionType paramAnonymousActionType) {
                    page--;
                    refresh();
                }
            });
        }

        if (this.page < this.pages.size() - 1) {
            putButton(getItem("Next"), new Action() {
                public void run(Player paramAnonymousPlayer, ActionType paramAnonymousActionType) {
                    page++;
                    refresh();
                }
            });
        }
    }
}