package me.wm.id.ro.util.Language;

import me.wm.id.ro.Main;
import me.wm.id.ro.util.Logger;
import me.wm.id.ro.util.database.Database;
import me.wm.id.ro.util.files.Config;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class LanguageManager {

    private static LanguageManager instance = new LanguageManager();
    private Database db;
    private HashMap<String, String> langCache;
    private HashMap<String, Lang> languages;
    private HashMap<String, StyleFileConfig> styleFiles;
    private ArrayList<Lang> langs;

    private LanguageManager() {
        db = new Database(Main.getInstance().getDataFolder().getAbsolutePath() + File.separator + "languages.db") {
            @Override
            public void defaultTables() {
                execute("CREATE TABLE IF NOT EXISTS `language`(`uuid` varchar(36) NOT NULL PRIMARY KEY, `ip` varchar(15) NOT NULL, `language` varchar(20) NOT NULL);");
            }
        };
        langCache = new HashMap<>();
        languages = new HashMap<>();
        styleFiles = new HashMap<>();
    }

    public static LanguageManager getInstance() {
        return instance;
    }

    public void reloadLanguages() {

        langCache = new HashMap<>();
        languages = new HashMap<>();
        styleFiles = new HashMap<>();

        loadLanguages();
    }

    public void loadLanguages() {
        File default_lang = new File(Main.getInstance().getDataFolder().getAbsolutePath() + File.separator + "languages", "StyleFile_" + Config.getConfig().getYml().getString("defaultLanguage") + ".yml");
        if (!default_lang.exists()) {
            default_lang.getParentFile().mkdirs();
            try {
                default_lang.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        File dir = new File(Main.getInstance().getDataFolder().getAbsolutePath() + File.separator + "languages");
        if (dir.exists()) {
            for (File f : Objects.requireNonNull(dir.listFiles())) {
                if (f.isFile() && f.getName().endsWith(".yml")) {
                    if (f.getName().startsWith("StyleFile_")) {
                        String name = f.getName().replaceAll("StyleFile_", "").replaceAll(".yml", "");
                        Lang lang = new Lang(name, name);
                        languages.put(name, lang);
                        styleFiles.put(name, new StyleFileConfig(lang));
                    } else if (f.getName().toLowerCase().startsWith("disabled-stylefile_")) {
                    } else {
                        f.delete();
                    }
                } else {
                    if (!f.getName().equalsIgnoreCase("languages.db"))
                        f.delete();
                }
            }

        }
    }

    public void sendMessage(Player p, String path) {
        p.spigot().sendMessage(LanguageManager.getInstance().getStyleFile(p).getMessage(p, path).build());
    }

    public void sendMessage(Player p, String path, HashMap<String, String> placeholders) {
        p.spigot().sendMessage(LanguageManager.getInstance().getStyleFile(p).getMessage(p, path).setPlaceholders(placeholders).build());
    }

    public void broadcastMessage(String path) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.spigot().sendMessage(LanguageManager.getInstance().getStyleFile(p).getMessage(p, path).build());
        }
    }

    public void broadcastMessage(String path, HashMap<String, String> placeholders) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.spigot().sendMessage(LanguageManager.getInstance().getStyleFile(p).getMessage(p, path).setPlaceholders(placeholders).build());
        }
    }

    public boolean existsLanguage(String lang) {
        for (String l : languages.keySet()) {
            if (l.equals(lang)) {
                return true;
            }
        }
        return false;
    }

    public void setLangForPlayer(Lang lang, Player p) {
        String ip = p.getAddress().toString().split("/")[1].split(":")[0];
        langCache.remove(p.getUniqueId().toString());
        langCache.remove(ip);
        langCache.put(ip, lang.getLangName());
        langCache.put(p.getUniqueId().toString(), lang.getLangName());

        Main.getInstance().getServer().getPluginManager().callEvent(new LanguageUpdateEvent(p));

        db.execute("DELETE FROM `language` WHERE `uuid`='" + p.getUniqueId().toString() + "';");
        db.execute("DELETE FROM `language` WHERE `ip`='" + ip + "';");
        db.execute("INSERT INTO `language` (`uuid`, `ip`, `language`) VALUES('" + p.getUniqueId().toString() + "', '" + ip + "', '" + lang.getLangName() + "')");
    }

    public StyleFileConfig getStyleFile(Player p) {
        return styleFiles.get(getLang(p));
    }

    private String getLang(Player p) {

        String lang;
        String ip = Objects.requireNonNull(p.getAddress().getHostName());
//        String ip = p.getAddress().toString().split("/")[1].split(":")[0];

        if (langCache.containsKey(ip)) {
            if (existsLanguage(langCache.get(ip)))
                return langCache.get(ip);
        }

        if (langCache.containsKey(p.getUniqueId().toString())) {
            if (existsLanguage(langCache.get(p.getUniqueId().toString())))
                return langCache.get(p.getUniqueId().toString());
        }

        lang = db.getString("language", "SELECT * FROM `language` WHERE `uuid`='" + p.getUniqueId().toString() + "';");

        if (lang == null) {
            lang = db.getString("language", "SELECT * FROM `language` WHERE `ip`='" + ip + "';");
        }

        if (lang == null || !existsLanguage(lang)) {
            lang = Config.getConfig().getYml().getString("defaultLanguage");
            db.execute("INSERT INTO `language` (`uuid`, `ip`, `language`) VALUES('" + p.getUniqueId().toString() + "', '" + ip + "', '" + lang + "')");
        }

        langCache.put(ip, lang);
        langCache.put(p.getUniqueId().toString(), lang);
        return lang;
    }

    public ArrayList<Lang> getLanguages() {
        if (langs != null) return langs;

        langs = new ArrayList<>();
        for (String s : languages.keySet()) {
            langs.add(languages.get(s));
        }
        return langs;
    }
}
