package me.wm.id.ro.Regions;

import me.wm.id.ro.Main;
import me.wm.id.ro.util.Language.LanguageManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class RegionChatConfirm implements Listener {

    private Player p;
    private Action action;

    public RegionChatConfirm(Player p, Action action) {
        this.p = p;
        this.action = action;

        Main.getInstance().getServer().getPluginManager().registerEvents(this, Main.getInstance());

        LanguageManager.getInstance().sendMessage(p, "RegionChatConfirmMessage");
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        if (!e.getPlayer().equals(p)) return;
        e.setCancelled(true);

        if (e.getMessage().equalsIgnoreCase("confirm")) {
            action.confirm();
            HandlerList.unregisterAll(this);
        } else if (e.getMessage().equalsIgnoreCase("reset")) {
            action.reset();
            LanguageManager.getInstance().sendMessage(p, "RegionChatConfirmMessage");
            HandlerList.unregisterAll(this);
        } else if (e.getMessage().equalsIgnoreCase("cancel")) {
            action.deny();
            LanguageManager.getInstance().sendMessage(p, "RegionChatCancelMessage");
            HandlerList.unregisterAll(this);
        } else {
            LanguageManager.getInstance().sendMessage(p, "RegionChatConfirmMessage");
        }
    }

    public interface Action {
        void confirm();

        void reset();

        void deny();
    }

}
