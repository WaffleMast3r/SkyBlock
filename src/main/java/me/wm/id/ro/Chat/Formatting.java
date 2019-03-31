package me.wm.id.ro.Chat;

import me.wm.id.ro.Main;
import me.wm.id.ro.util.ConfigManager;
import me.wm.id.ro.util.Language.Lang;
import me.wm.id.ro.util.Language.LanguageManager;
import me.wm.id.ro.util.Logger;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.io.File;
import java.util.HashMap;

public class Formatting implements Listener {

    private ConfigManager cfg;

    public Formatting() {
        cfg = new ConfigManager("Formatting.yml", Main.getInstance().getDataFolder().getAbsolutePath() + File.separator + "Chat");
        cfg.getYml().addDefault("enabled", true);
        cfg.getYml().addDefault("Format", "{Prefix} $0{Name} $3➢ $2{Message}");
        cfg.getYml().options().copyDefaults(true);
        cfg.saveConfig();

        if (!cfg.getYml().getBoolean("enabled")) return;

        Main.getInstance().getServer().getPluginManager().registerEvents(this, Main.getInstance());

        Logger.info("$0Module Formatting $1Enabled");
        for (Lang l : LanguageManager.getInstance().getLanguages()) {
            l.getConfig().getYml().addDefault("Messages.Chat.Format", cfg.getYml().getString("Format"));
            l.getConfig().getYml().options().copyDefaults(true);
            l.getConfig().saveConfig();
        }
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        e.setCancelled(true);

        HashMap<String, String> pl = new HashMap<>();
        pl.put("{Prefix}", "$0");
        pl.put("{Name}", e.getPlayer().getName());
        pl.put("{Message}", e.getMessage());

        LanguageManager.getInstance().broadcastMessage("Chat.Format", pl);
    }

}
