package me.wm.id.ro.util.gui;

import java.util.ArrayList;

public interface IEasyBrowserGUI<OBJ> {
    ArrayList<OBJ> loadObjects();

    Button forEach(OBJ paramOBJ);

    void loadAdditionalButtons();
}
