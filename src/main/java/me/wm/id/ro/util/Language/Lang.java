package me.wm.id.ro.util.Language;



import me.wm.id.ro.Main;
import me.wm.id.ro.util.ConfigManager;
import me.wm.id.ro.util.files.Config;

import java.io.File;

public class Lang {

    private String langName;
    private ConfigManager config;
    private String displayName;

    public Lang(String langName, String displayName) {
        this.langName = langName;
        this.displayName = displayName;
        this.config = new ConfigManager("StyleFile_" + langName + ".yml", Main.getInstance().getDataFolder().getAbsolutePath() + File.separator + "languages");

        config.getYml().options().copyDefaults(true);
        config.getYml().addDefault("LanguageDisplayName", displayName);
        config.getYml().addDefault("Prefix", Config.getConfig().getYml().getString("Prefix"));
        config.getYml().addDefault("LanguageColors", Config.getConfig().getYml().getStringList("PluginColors"));
        config.saveConfig();
    }

    public String getLangName() {
        return langName;
    }

    public String getName() {
        return config.getYml().getString("LanguageDisplayName");
    }

    public ConfigManager getConfig() {
        return config;
    }
}
