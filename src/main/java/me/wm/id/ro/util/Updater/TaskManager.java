package me.wm.hybrid.ro.util.Updater;

import me.wm.hybrid.ro.util.Logger;
import org.bukkit.Bukkit;

public class TaskManager {
    private static boolean enabled = false;

    private TaskManager() {
    }

    public static void Enable() {
        if (enabled) {
            return;
        }
        enabled = true;
        Logger.info("TaskManager enabled!");
        for (final UpdateTime localUpdateTime : UpdateTime.values()) {
            Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
                public void run() {
                    Bukkit.getPluginManager().callEvent(new UpdateEvent(localUpdateTime));
                }
            }, 0L, localUpdateTime.getTime());
        }
    }
}
