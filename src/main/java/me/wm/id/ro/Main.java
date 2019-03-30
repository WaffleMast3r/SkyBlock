package me.wm.id.ro;

import me.wm.hybrid.ro.util.Language.LanguageManager;
import me.wm.hybrid.ro.util.Logger;
import me.wm.hybrid.ro.util.NMS.NMS;
import me.wm.hybrid.ro.util.NMS.Versions.v1_12_R1;
import me.wm.hybrid.ro.util.Updater.TaskManager;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin implements Listener {

    private static Main instance;
    private static NMS nms;
    private boolean approve = true;

    public static Main getInstance() {
        return instance;
    }

    public static NMS getNms() {
        return nms;
    }

    @Override
    public void onLoad() {

        instance = this;
        String version = Bukkit.getServer().getClass().getName().split("\\.")[3];
        switch (version) {
            case "v1_12_R1":
                nms = new v1_12_R1();
                Logger.info("$0Version Accepted! $2version: $1" + version);
                break;
            default:
                Logger.error("This version is not accepted! $2version: $1" + version);
                approve = false;
                getServer().getPluginManager().disablePlugin(this);
                break;
        }
    }

    @Override
    public void onEnable() {
        if (approve) {
            instance = this;
            if (!me.wm.id.ro.Main.getInstance().getServer().getPluginManager().isPluginEnabled("MVdWPlaceholderAPI")) {
                Logger.error("Cannot find the plugin $1PlaceholderAPI!");
                Logger.error("Disabling the plugin!");
                approve = false;
                getServer().getPluginManager().disablePlugin(this);
                return;
            }
            LanguageManager.getInstance().loadLanguages();
            Logger.info("$0Plugin enabled!");
            TaskManager.Enable();
            getServer().getPluginManager().registerEvents(this, this);
        } else {
            getServer().getPluginManager().disablePlugin(this);
        }
    }

    @Override
    public void onDisable() {
        if (approve) {

        }
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        if (e.getMessage().equalsIgnoreCase("reload")) {
            LanguageManager.getInstance().reloadLanguages();
        } else if (e.getMessage().equalsIgnoreCase("test")) {

        }
    }

}
