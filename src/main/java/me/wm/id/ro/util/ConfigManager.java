package me.wm.id.ro.util;

import me.wm.id.ro.Main;
import org.apache.commons.io.IOUtils;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;

public class ConfigManager {

    private File file;
    private YamlConfiguration conf;

    private String name;
    private String dir;

    public ConfigManager(String name, String dir) {
        this.name = name;
        this.dir = dir;
        loadConfig(null);
    }

    public ConfigManager(String name, String resource, String dir) {
        this.name = name;
        this.dir = dir;
        loadConfig(resource);
    }

    public void reloadConfig() {
        try {
            conf.load(file);
        } catch (IOException | InvalidConfigurationException ignored) {
        }
    }

    private void loadConfig(String resource) {
        file = new File(dir, name);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            if (resource != null) {
                InputStream r = Main.getInstance().getResource(resource);
                try (OutputStream outputStream = new FileOutputStream(file)) {
                    IOUtils.copy(r, outputStream);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        conf = new YamlConfiguration();

        try {
            conf.load(file);
        } catch (IOException | InvalidConfigurationException ignored) {
        }
    }

    public void saveConfig() {
        try {
            conf.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FileConfiguration getYml() {
        return conf;
    }
}