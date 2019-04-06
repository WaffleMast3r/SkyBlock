package me.wm.id.ro.Regions.guis;


import javafx.util.Pair;
import me.wm.id.ro.Regions.Region;
import me.wm.id.ro.Regions.RegionInterogation;
import me.wm.id.ro.Regions.RegionManager;
import me.wm.id.ro.util.ChatDialog;
import me.wm.id.ro.util.Language.LanguageManager;
import me.wm.id.ro.util.gui.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class RegionList extends EasyBrowserGUI<Region> {

    private Player p;

    public RegionList(Player p) {
        super(p, LanguageManager.getInstance().getStyleFile(p).getGUI(p, "RegionList"));
        this.p = p;
        setObjectsPerPage(27);

        super.refresh();
    }

    @Override
    public ArrayList<Region> loadObjects() {
        return new ArrayList<>(RegionManager.getInstance().getRegions().values());
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
                closeInventory();
                paramOBJ.visualize();
            }
        });
    }

    @Override
    public void loadAdditionalButtons() {
        putButton(getItem("create"), new Action() {
            @Override
            public void run(Player paramPlayer, ActionType paramActionType) {
                closeInventory();
                new ChatDialog(p, "Type the name of the region!", new ChatDialog.Action() {
                    @Override
                    public void run(String s) {
                        new RegionInterogation(p, new RegionInterogation.Action() {
                            @Override
                            public void run(Block pos1, Block pos2) {
                                RegionManager.getInstance().createRegion(new Pair<>(pos1, pos2), s);
                            }
                        });
                    }
                });

            }
        });
    }
}
