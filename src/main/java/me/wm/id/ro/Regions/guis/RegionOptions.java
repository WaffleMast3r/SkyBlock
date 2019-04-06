package me.wm.id.ro.Regions.guis;

import me.wm.id.ro.Regions.Region;
import me.wm.id.ro.util.Language.LanguageManager;
import me.wm.id.ro.util.gui.Action;
import me.wm.id.ro.util.gui.ActionType;
import me.wm.id.ro.util.gui.EasyGUI;
import me.wm.id.ro.util.gui.Replacer;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class RegionOptions extends EasyGUI {

    private Region region;

    public RegionOptions(Player p, Region region) {
        super(p, LanguageManager.getInstance().getStyleFile(p).getGUI(p, "RegionOptions"));
        this.region = region;

        refresh();

        super.openGUI();
    }

    private void refresh() {
        putButton(getItem("change_icon"), new Action() {
            @Override
            public void run(Player paramPlayer, ActionType paramActionType) {

            }
        });

        putButton(getItem("properties"), new Action() {
            @Override
            public void run(Player paramPlayer, ActionType paramActionType) {
//                new RegionPropertiesMenu(paramPlayer, region);
            }
        });

        HashMap<String, Replacer> ph = new HashMap<>();
        ph.put("{ID}", new Replacer() {
            @Override
            public String run() {
                return region.getId() + "";
            }
        });

        ph.put("{RegionName}", new Replacer() {
            @Override
            public String run() {
                return region.getName();
            }
        });

        ph.put("{Pos1}", new Replacer() {
            @Override
            public String run() {
                return region.getFirstPosition().getX() + " " + region.getFirstPosition().getY() + " " + region.getFirstPosition().getZ();
            }
        });

        ph.put("{Pos2}", new Replacer() {
            @Override
            public String run() {
                return region.getSecondPosition().getX() + " " + region.getSecondPosition().getY() + " " + region.getSecondPosition().getZ();
            }
        });

        putButton(getItem("info").setPlaceholders(ph), new Action() {
            @Override
            public void run(Player paramPlayer, ActionType paramActionType) {

            }
        });

        putButton(getItem("delete"), new Action() {
            @Override
            public void run(Player paramPlayer, ActionType paramActionType) {

            }
        });

        putButton(getItem("visualize"), new Action() {
            @Override
            public void run(Player paramPlayer, ActionType paramActionType) {
                paramPlayer.closeInventory();
                region.visualize();
            }
        });
    }
}