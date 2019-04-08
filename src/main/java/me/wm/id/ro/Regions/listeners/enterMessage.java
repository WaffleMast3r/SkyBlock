package me.wm.id.ro.Regions.listeners;

import static me.wm.id.ro.Regions.RegionProperties.*;
import me.wm.id.ro.Main;
import me.wm.id.ro.Regions.Region;
import me.wm.id.ro.Regions.RegionManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;

public class enterMessage {
    private final ArrayList<Player> cooldown = new ArrayList<>();

    public enterMessage(Player p, long delay) {
        new BukkitRunnable() {
            @Override
            public void run() {
                // Cancel the task if player disconnects
                if(!p.isOnline()) this.cancel();

                // Gets the region of the player
                Region currentRegion = RegionManager.getInstance().isPlayerInRegion(p);

                /*
                    Checks if:
                    - player is inside a region
                    - the said region has the required property
                    - the player has entered the region for the first time
                */

                if(currentRegion != null && currentRegion.hasProperty(ENTER_MESSAGE) && !cooldown.contains(p)) {
                    if(currentRegion.getPropertyMessageFor(ENTER_MESSAGE) == null) return;
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', currentRegion.getPropertyMessageFor(ENTER_MESSAGE)));
                    cooldown.add(p);
                } else cooldown.remove(p);
            }
        }.runTaskTimer(Main.getInstance(), 20, delay);
    }

}
