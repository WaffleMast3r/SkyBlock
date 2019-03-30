package me.wm.hybrid.ro.util.gui;

import me.wm.hybrid.ro.util.Language.LanguageManager;
import me.wm.hybrid.ro.util.Logger;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CustomItem {

    private Player p;
    private Material material;
    private int ID, slot, amount;
    private String name, title, defaultTitle;
    private List<String> lore, defaultLore, enchants;
    private CustomItem instance;
    private HashMap<String, Replacer> placeholders;

    public CustomItem(Player p, Material material, int ID, int slot, int amount, String name, String title, List<String> lore, List<String> enchants) {
        this.p = p;
        instance = this;
        this.material = material;
        this.ID = ID;
        this.slot = slot;
        this.amount = amount;
        this.name = name;
        this.title = title;
        this.defaultTitle = title;
        this.lore = lore;
        this.defaultLore = lore;
        this.enchants = enchants;
        placeholders = new HashMap<>();
    }

    public ItemStack build() {
        this.lore = defaultLore;
        this.title = defaultTitle;
        for (String ph : placeholders.keySet()) {
            if (title != null) {
                title = title.replace(ph, placeholders.get(ph).run());
            }

            if (lore != null) {
                List<String> newLore = new ArrayList<>();
                for (String localLore : lore) {
                    newLore.add(localLore.replace(ph, placeholders.get(ph).run()));
                }
                this.lore = newLore;
            }
        }

        ItemStack itm = new ItemStack(material, amount, (short) ID);
        ItemMeta itemMeta = itm.getItemMeta();
        itemMeta.setDisplayName(LanguageManager.getInstance().getStyleFile(p).translateColor(this.title));

        List<String> lastLore = new ArrayList<>();

        for (String l : this.lore) {
            String s = LanguageManager.getInstance().getStyleFile(p).translateColor(l);
            lastLore.add(s);
        }
        itemMeta.setLore(lastLore);

        for (String enchs : this.enchants) {
            String[] ench = enchs.split("@#@");
            itemMeta.addEnchant(Enchantment.getByName(ench[0]), Integer.parseInt(ench[1]), true);
        }

        itm.setItemMeta(itemMeta);
        return itm;
    }

    public String getName() {
        return name;
    }

    public int getSlot() {
        return slot;
    }

    public CustomItem setPlaceholders(HashMap<String, Replacer> placeholders) {
        this.placeholders = placeholders;
        return this;
    }

    /* * * * * *\
     * Getters *
    \* * * * * */

    public Material getMaterial() {
        return material;
    }

    public int getID() {
        return ID;
    }

    public int getAmount() {
        return amount;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getLore() {
        return lore;
    }

    public List<String> getEnchants() {
        return enchants;
    }
}
