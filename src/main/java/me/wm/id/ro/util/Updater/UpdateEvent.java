package me.wm.hybrid.ro.util.Updater;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class UpdateEvent extends Event {

    private UpdateTime time;
    private static final HandlerList handlers = new HandlerList();

    public UpdateEvent(UpdateTime paramUpdateTime) {
        this.time = paramUpdateTime;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public UpdateTime getUpdateTime() {
        return this.time;
    }
}
