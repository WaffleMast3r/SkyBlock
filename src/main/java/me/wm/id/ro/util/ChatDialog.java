package me.wm.id.ro.util;

import me.wm.id.ro.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatDialog implements Listener {

    private Player p;
    private Action action;

    public ChatDialog(Player p, String message, Action action) {
        this.p = p;
        this.action = action;

        Main.getInstance().getServer().getPluginManager().registerEvents(this, Main.getInstance());

        p.sendMessage(message);
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        if (!e.getPlayer().equals(p)) return;
        e.setCancelled(true);

        action.run(e.getMessage());
        HandlerList.unregisterAll(this);
    }

    public interface Action {
        void run(String s);
    }
}
