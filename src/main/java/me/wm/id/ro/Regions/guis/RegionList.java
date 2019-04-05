package me.wm.id.ro.Regions.guis;


import me.wm.id.ro.Regions.Region;
import me.wm.id.ro.Regions.RegionManager;
import me.wm.id.ro.util.Language.LanguageManager;
import me.wm.id.ro.util.gui.*;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class RegionList extends EasyBrowserGUI<Region> {

    public RegionList(Player p) {
        super(p, LanguageManager.getInstance().getStyleFile(p).getGUI(p, "RegionList"));
        setObjectsPerPage(27);
        super.refresh();
    }

    @Override
    public ArrayList<Region> loadObjects() {
        return (ArrayList<Region>) RegionManager.getInstance().getRegions().values();
    }

    @Override
    public Button forEach(Region paramOBJ) {
        HashMap<String, Replacer> ph = new HashMap<>();
        ph.put("{Name}", new Replacer() {
            @Override
            public String run() {
                return paramOBJ.getName();
            }
        });

        ph.put("{ID}", new Replacer() {
            @Override
            public String run() {
                return paramOBJ.getId() + "";
            }
        });

        return new Button(getItem("region").setPlaceholders(ph), new Action() {
            @Override
            public void run(Player paramPlayer, ActionType paramActionType) {

            }
        });
    }

    @Override
    public void loadAdditionalButtons() {
        putButton(getItem("create"), new Action() {
            @Override
            public void run(Player paramPlayer, ActionType paramActionType) {

            }
        });
    }
}
