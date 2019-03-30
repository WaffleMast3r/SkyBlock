package me.wm.hybrid.ro.Bungee;

import net.md_5.bungee.api.plugin.Plugin;

public class Main extends Plugin {

    private static Main instance;

    @Override
    public void onEnable() {
        instance = this;
        getProxy().getPluginManager().registerListener(this, new me.wm.hybrid.ro.Bungee.MessengerManager());
        getProxy().registerChannel("BungeeCord");
    }

    @Override
    public void onDisable() {

    }

    public static Main getInstance() {
        return instance;
    }
}
