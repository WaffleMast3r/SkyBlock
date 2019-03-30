package me.wm.hybrid.ro.util.database;

import me.wm.hybrid.ro.util.ConfigManager;

public class MySQLSettings {

    private String host;
    private String port;
    private String user;
    private String password;
    private String database;
    private String prefix;

    public MySQLSettings(ConfigManager cfg) {
        this.host = cfg.getYml().getString("MySQL.host");
        this.port = cfg.getYml().getString("MySQL.port");
        this.user = cfg.getYml().getString("MySQL.user");
        this.password = cfg.getYml().getString("MySQL.pass");
        this.database = cfg.getYml().getString("MySQL.name");
        this.prefix = cfg.getYml().getString("MySQL.pref");
    }

    public String getHost() {
        return host;
    }

    public String getPort() {
        return port;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getDatabase() {
        return database;
    }

    public String getPrefix() {
        return prefix;
    }
}
