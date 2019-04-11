package me.wm.id.ro.Regions.listeners;

import me.wm.id.ro.Main;
import me.wm.id.ro.Regions.RegionManager;
import me.wm.id.ro.Regions.RegionProperties;
import me.wm.id.ro.util.Events;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntitySpawnEvent;

import java.util.ArrayList;

public class entitySpawningFlags {
    public entitySpawningFlags() {
        Events.listen(Main.getInstance(), EntitySpawnEvent.class, e ->
            RegionManager.getInstance().getRegions().forEach((i, region) -> {
                if(region.isInRegion(e.getLocation())) {
                    if(region.hasProperty(RegionProperties.ENTITY_SPAWNING)) {
                        if(e.getEntity() instanceof LivingEntity && !(e.getEntity() instanceof ArmorStand)) {
                            e.setCancelled(true);
                        }
                    }

                    if(region.hasProperty(RegionProperties.PREVENT_SPAWNING)) {
                        ((ArrayList) region.getDataForProperty(RegionProperties.PREVENT_SPAWNING)).forEach(f -> {
                            if(e.getEntity().getClass().equals(f)) {
                                e.setCancelled(true);
                            }
                        });
                    }
                }
            })
        );
    }
}
