package me.wm.id.ro.util.files;

import me.wm.id.ro.Main;
import me.wm.id.ro.util.ConfigManager;

public class Config extends ConfigManager{

    public static Config instance = new Config();

    private Config() {
        super("Config.yml", "Config.yml", Main.getInstance().getDataFolder().getAbsolutePath());
    }

    public static Config getConfig() {
        return instance;
    }
}
