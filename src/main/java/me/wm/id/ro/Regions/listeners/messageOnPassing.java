package me.wm.id.ro.Regions.listeners;

import static me.wm.id.ro.Regions.RegionProperties.*;
import me.wm.id.ro.Main;
import me.wm.id.ro.Regions.Region;
import me.wm.id.ro.Regions.RegionManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class messageOnPassing {
    private final ArrayList<Player> cooldown = new ArrayList<>();
    private boolean hasMessageOnLeave = false;
    private String leaveMessage = "";

    public messageOnPassing(Player p, long delay) {
        new BukkitRunnable() {
            @Override
            public void run() {
                // Cancel the task if player disconnects
                if(!p.isOnline()) this.cancel();

                Region currentRegion = RegionManager.getInstance().isPlayerInRegion(p);

                // Display message on entry
                if(currentRegion != null && !cooldown.contains(p)) {
                    if(currentRegion.hasProperty(MESSAGE_ON_LEAVE) && !hasMessageOnLeave) {
                        hasMessageOnLeave = true;
                        leaveMessage = (String) currentRegion.getDataForProperty(MESSAGE_ON_LEAVE);
                    }

                    if(currentRegion.hasProperty(MESSAGE_ON_ENTRY) && currentRegion.getDataForProperty(MESSAGE_ON_ENTRY) == null) return;
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', (String) currentRegion.getDataForProperty(MESSAGE_ON_ENTRY)));
                    cooldown.add(p);

                // Display message on leave
                } else if(currentRegion == null && cooldown.contains(p)) {
                    if(hasMessageOnLeave && leaveMessage.equals("")) {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', leaveMessage));
                    }
                    cooldown.remove(p);
                }
            }
        }.runTaskTimer(Main.getInstance(), 0, delay);
    }

}
