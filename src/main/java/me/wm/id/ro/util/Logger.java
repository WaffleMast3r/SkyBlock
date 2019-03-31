package me.wm.id.ro.util;

import me.wm.id.ro.Main;
import me.wm.id.ro.util.files.Config;
import org.bukkit.Bukkit;

public class Logger {

    private static boolean debugger = true;

    public static void info(String message) {
        Main.getInstance().getServer().getConsoleSender().sendMessage(
                WaffClass.Color.getInstance().translateColor(
                        Config.getInstance().getConfig().getYml().getString("Prefix").replace("%PluginName%", Main.getInstance().getDescription().getName()) + " $0"+ message)
        );
    }

    public static void debug(String message) {
        if (debugger)
            Main.getInstance().getServer().getConsoleSender().sendMessage(WaffClass.Color.getInstance().translateColor(Config.getInstance().getConfig().getYml().getString("Prefix").replace("%PluginName%", Main.getInstance().getDescription().getName()) + " $0"+ message));
    }

    public static void error(String message) {
        Main.getInstance().getServer().getConsoleSender().sendMessage(
                WaffClass.Color.getInstance().translateColor(
                        Config.getInstance().getConfig().getYml().getString("Prefix")
                                .replace("%PluginName%", Main.getInstance().getDescription().getName()) + " @err"+ message)
        );
    }

    public static void broadcast(String message) {
        Bukkit.broadcastMessage(WaffClass.Color.getInstance().translateColor(Config.getInstance().getConfig().getYml().getString("Prefix").replace("%PluginName%", Main.getInstance().getDescription().getName()) + " $0"+ message));
    }

    public static void infoRaw(String toString) {
        Main.getInstance().getServer().getConsoleSender().sendRawMessage(toString);
    }
}
