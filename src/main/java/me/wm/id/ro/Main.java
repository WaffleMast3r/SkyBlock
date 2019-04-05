package me.wm.id.ro;

import me.wm.id.ro.Chat.Formatting;
import me.wm.id.ro.Regions.RegionManager;
import me.wm.id.ro.util.Language.LanguageManager;
import me.wm.id.ro.util.Language.LanguageSelectorMenu;
import me.wm.id.ro.util.Logger;
import me.wm.id.ro.util.NMS.NMS;
import me.wm.id.ro.util.NMS.Versions.v1_13_R2;
import me.wm.id.ro.util.Updater.TaskManager;
import me.wm.id.ro.util.Updater.UpdateEvent;
import me.wm.id.ro.util.Updater.UpdateTime;
import me.wm.id.ro.util.commands.CommandManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;

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
            case "v1_13_R2":
                nms = new v1_13_R2();
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
//            if (!me.wm.id.ro.Main.getConfig().getServer().getPluginManager().isPluginEnabled("MVdWPlaceholderAPI")) {
//                Logger.error("Cannot find the plugin $1PlaceholderAPI!");
//                Logger.error("Disabling the plugin!");
//                approve = false;
//                getServer().getPluginManager().disablePlugin(this);
//                return;
//            }
            LanguageManager.getInstance().loadLanguages();
            Logger.info("$0Plugin enabled!");
            TaskManager.Enable();
            RegionManager.Enable();
            getServer().getPluginManager().registerEvents(this, this);

            loadClasses();
            CommandManager.getInstance().loadCommands();
        } else {
            getServer().getPluginManager().disablePlugin(this);
        }
    }

    private void loadClasses() {
        new Formatting();
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
        } else if (e.getMessage().equalsIgnoreCase("lang")) {
            new LanguageSelectorMenu(e.getPlayer());
        } else if (e.getMessage().equalsIgnoreCase("test")) {
            HashMap<String, String> pl = new HashMap<>();
            pl.put("{Type}", "Chestie");
            LanguageManager.getInstance().sendMessage(e.getPlayer(), "in_progress_type", pl);
        }
        e.setMessage(ChatColor.translateAlternateColorCodes('&', e.getMessage()));
    }

    @EventHandler
    public void setEffect(UpdateEvent e){
        if (e.getUpdateTime().equals(UpdateTime.MINUTE)){
            for (Player p : Bukkit.getOnlinePlayers()){
                p.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 2000, 5));
                p.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, 2000, 5));
            }
        }
    }
}
