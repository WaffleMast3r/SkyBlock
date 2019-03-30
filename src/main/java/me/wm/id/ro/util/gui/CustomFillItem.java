package me.wm.hybrid.ro.util.gui;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.List;

public class CustomFillItem extends CustomItem {

    private String slots;

    public CustomFillItem(Player p, Material material, int ID, String slots, int amount, String name, String title, List<String> lore, List<String> enchants) {
        super(p, material, ID, -2, amount, name, title, lore, enchants);
        this.slots = slots;
    }

    public String getSlots(){
        return slots;
    }

}
