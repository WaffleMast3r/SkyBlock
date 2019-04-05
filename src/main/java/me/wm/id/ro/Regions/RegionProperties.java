package me.wm.id.ro.Regions;

import me.wm.id.ro.util.Language.LanguageManager;
import org.bukkit.entity.Player;

public enum RegionProperties {

    NO_PVP("No Pvp"), NO_BUILD("No Build");

    private String propName;

    RegionProperties(String propName) {
        this.propName = propName;
    }

    public void sendMessage(Player p){
        LanguageManager.getInstance().sendMessage(p, "test");
    }

}
