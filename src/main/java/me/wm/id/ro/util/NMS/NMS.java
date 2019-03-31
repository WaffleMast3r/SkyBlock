package me.wm.id.ro.util.NMS;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface NMS {

    void sendTitle(Player p, String title, String subTitle, int fadeIn, int stay, int fadeOut);

    void playAction(Player p, String text);

    void registerCommand(String name, Command clasa);

    void openSign(Player p);

    void openBook(Player p, ItemStack book);

}
