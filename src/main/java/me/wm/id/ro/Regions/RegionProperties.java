package me.wm.id.ro.Regions;

import me.wm.id.ro.util.Language.LanguageManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public enum RegionProperties {

    NO_PVP("No Pvp", Material.DIAMOND_SWORD, 1, 0),
    NO_BUILD("No Build", Material.STONE, 1, 0),
    ENTER_MESSAGE("Enter Message", Material.PAPER, 1, 0);

    private String propName;
    private Material material;
    private int amount, data;

    RegionProperties(String propName, Material material, int amount, int data) {
        this.propName = propName;
        this.material = material;
        this.amount = amount;
        this.data = data;
    }

    public String getPropName() {
        return propName;
    }

    public Material getMaterial() {
        return material;
    }

    public int getAmount() {
        return amount;
    }

    public int getData() {
        return data;
    }

    public void sendMessage(Player p) {
        LanguageManager.getInstance().sendMessage(p, "test");
    }

}
