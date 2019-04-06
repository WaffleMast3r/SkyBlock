package me.wm.id.ro.Regions;

import me.wm.id.ro.util.Language.LanguageManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public enum RegionProperties {

    NO_PVP("No Pvp", new ItemStack(Material.DIAMOND_SWORD, 1)),
    NO_BUILD("No Build", new ItemStack(Material.STONE, 1));

    private String propName;
    private ItemStack itemStack;

    RegionProperties(String propName, ItemStack itemstack) {
        this.propName = propName;
        this.itemStack = itemStack;
    }
    public ItemStack getItemStack(){
        return itemStack;
    }

    public void sendMessage(Player p){
        LanguageManager.getInstance().sendMessage(p, "test");
    }

}
