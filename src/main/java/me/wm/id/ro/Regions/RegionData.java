package me.wm.id.ro.Regions;

import me.wm.id.ro.Main;
import me.wm.id.ro.util.ConfigManager;

import java.io.File;

public class RegionData extends ConfigManager {
    public static RegionData instance = new RegionData();

    private RegionData() {
        super("Regions.yml", Main.getInstance().getDataFolder().getAbsolutePath() + File.separator + "Regions");
    }

    public static RegionData getConfig() {
        return instance;
    }

    public void load() {
        getYml().options().copyDefaults(true);
        getYml().addDefault("regions", null);
        saveConfig();
    }
}
