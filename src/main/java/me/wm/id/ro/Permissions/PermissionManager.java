package me.wm.id.ro.Permissions;

import org.bukkit.entity.Player;

public class PermissionManager {

    public static PermissionManager instance = new PermissionManager(null);

    private PermissionManager(Player player) {

    }

    public static PermissionManager getInstance() {
        return instance;
    }

}
