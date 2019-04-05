package me.wm.id.ro.Regions;

import me.wm.id.ro.Main;
import me.wm.id.ro.util.ConfigManager;

import java.io.File;
import java.util.Collections;

public class RegionData extends ConfigManager {
    public static RegionData instance = new RegionData();

    public static RegionData getConfig() {
        return instance;
    }

    private RegionData() {
        super("Regions.yml", Main.getInstance().getDataFolder().getAbsolutePath() + File.separator + "Regions.yml");
    }

    public void load() {
        getYml().options().copyDefaults(true);
        getYml().addDefault("regions", Collections.emptyList());
        saveConfig();
    }
}
