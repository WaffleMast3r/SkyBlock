package me.wm.id.ro.Regions.listeners;

import me.wm.id.ro.Main;
import me.wm.id.ro.Regions.Region;
import me.wm.id.ro.Regions.RegionManager;
import me.wm.id.ro.Regions.RegionProperties;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class allowPVP implements Listener {

    public allowPVP() {
        Main.getInstance().getServer().getPluginManager().registerEvents(this, Main.getInstance());
    }

    @EventHandler
    public void onAttack(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player)) return;
        if (!(event.getEntity() instanceof Player)) return;
        Region reg = RegionManager.getInstance().isPlayerInRegion((Player) event.getDamager());
        if (reg == null) return;
        if (reg.hasProperty(RegionProperties.NO_PVP)) return;

        Player p1 = (Player) event.getDamager();
        RegionProperties.NO_PVP.sendMessage(p1);
        event.setCancelled(true);
    }
}
