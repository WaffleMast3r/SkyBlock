package me.wm.id.ro.Regions.listeners;

import me.wm.id.ro.Main;
import me.wm.id.ro.Regions.RegionManager;
import me.wm.id.ro.Regions.RegionProperties;
import me.wm.id.ro.util.Events;
import org.bukkit.event.entity.EntitySpawnEvent;

import java.util.ArrayList;

public class preventEntities {
    public preventEntities() {
        Events.listen(Main.getInstance(), EntitySpawnEvent.class, e ->
            RegionManager.getInstance().getRegions().forEach((i, region) -> {
                if(region.hasProperty(RegionProperties.PREVENT_ENTITIES)
                        && region.isInRegion(e.getLocation())) {

                    ((ArrayList) region.getDataForProperty(RegionProperties.PREVENT_ENTITIES)).forEach(f -> {
                        if(e.getEntity().getClass().equals(f)) {
                            e.setCancelled(true);
                        }
                    });
                }
            })
        );
    }
}
