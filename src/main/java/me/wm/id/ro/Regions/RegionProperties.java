package me.wm.id.ro.Regions;

import me.wm.id.ro.util.Language.LanguageManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public enum RegionProperties {

    NO_PVP(0,"$1no-pvp", Material.DIAMOND_SWORD, 1, 0),
    NO_BUILD(1,"$1no-build", Material.STONE, 1, 0),
    MESSAGE_ON_ENTRY(2,"$1message-on-entry", Material.PAPER, 1, 0),
    MESSAGE_ON_LEAVE(3,"$1message-on-leave", Material.PAPER, 1, 0),
    PREVENT_SPAWNING(4,"$1prevent-spawning", Material.ZOMBIE_HEAD, 1, 0),
    ENTITY_SPAWNING(5, "$1entity-spawning", Material.ENDER_EYE, 1, 0),
    PROTECT_TILE_ENTITIES(6, "$1protect-tile-entities", Material.PAINTING, 1, 0);

    private int ID;
    private String propName;
    private Material material;
    private int amount, data;

    RegionProperties(int ID, String propName, Material material, int amount, int data) {
        this.ID = ID;
        this.propName = propName;
        this.material = material;
        this.amount = amount;
        this.data = data;
    }

    public int getID() {
        return ID;
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
