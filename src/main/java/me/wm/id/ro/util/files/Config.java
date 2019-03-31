package me.wm.id.ro.util.files;

import me.wm.id.ro.Main;
import me.wm.id.ro.util.ConfigManager;

public class Config {

    public static Config instance = new Config();
    private ConfigManager cfg;

    private Config() {
        cfg = new ConfigManager("Config.yml", "Config.yml", Main.getInstance().getDataFolder().getAbsolutePath());
    }

    public static Config getInstance() {
        return instance;
    }

    public ConfigManager getConfig() {
        return cfg;
    }
}
