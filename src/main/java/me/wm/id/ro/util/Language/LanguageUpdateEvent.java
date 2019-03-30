package me.wm.id.ro.util.Language;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class LanguageUpdateEvent extends Event {

    private static final HandlerList handlerList = new HandlerList();

    private Player player;

    public LanguageUpdateEvent(Player player) {
        this.player = player;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }
}
