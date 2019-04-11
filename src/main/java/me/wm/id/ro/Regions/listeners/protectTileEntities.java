package me.wm.id.ro.Regions.listeners;

import me.wm.id.ro.Main;
import me.wm.id.ro.Regions.RegionManager;
import me.wm.id.ro.Regions.RegionProperties;
import me.wm.id.ro.util.Events;
import me.wm.id.ro.util.Language.LanguageManager;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.hanging.HangingBreakEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class protectTileEntities {
    public protectTileEntities() {
        Events.listen(Main.getInstance(), HangingBreakEvent.class, e -> {
            RegionManager.getInstance().getRegions().forEach((i, region) -> {
                if(region.isInRegion(e.getEntity().getLocation()) && region.hasProperty(RegionProperties.PROTECT_TILE_ENTITIES)) {
                    if (e.getEntity().getNearbyEntities(5.0, 5.0, 5.0).get(0).hasPermission("regions.itemframe-destroy")
                    && e.getCause() == HangingBreakEvent.RemoveCause.ENTITY) return;

                    if(e.getCause() == HangingBreakEvent.RemoveCause.EXPLOSION
                    || e.getCause() == HangingBreakEvent.RemoveCause.ENTITY) {
                        e.setCancelled(true);
                    }
                }
            });
        });

        Events.listen(Main.getInstance(), PlayerInteractEntityEvent.class, e -> {
            RegionManager.getInstance().getRegions().forEach((i, region) -> {
                if(region.isInRegion(e.getRightClicked().getLocation()) && region.hasProperty(RegionProperties.PROTECT_TILE_ENTITIES)) {
                    if(e.getPlayer().hasPermission("regions.itemframe-interact")) return;
                    if(e.getRightClicked() instanceof ItemFrame || e.getRightClicked() instanceof ArmorStand) {
                        LanguageManager.getInstance().sendMessage(e.getPlayer(), "RegionChatWarn");
                        e.setCancelled(true);
                    }
                }
            });
        });

        Events.listen(Main.getInstance(), EntityDamageByEntityEvent.class, e -> {
            RegionManager.getInstance().getRegions().forEach((i, region) -> {
                if (region.isInRegion(e.getEntity().getLocation()) && region.hasProperty(RegionProperties.PROTECT_TILE_ENTITIES)) {

                    if(e.getEntity() instanceof ArmorStand && e.getCause() == EntityDamageByEntityEvent.DamageCause.PROJECTILE) {
                        if(((Projectile) e.getDamager()).getShooter() != null
                                && ((Player) ((Projectile) e.getDamager()).getShooter()).hasPermission("regions.armorstand-destroy")) return;

                        if(((Projectile) e.getDamager()).getShooter() != null) {
                            LanguageManager.getInstance().sendMessage((Player) ((Projectile) e.getDamager()).getShooter(), "RegionChatWarn");
                        } e.setCancelled(true);
                    }

                    if(e.getEntity() instanceof ArmorStand) {
                        if(e.getDamager().hasPermission("regions.armorstand-destroy")) return;

                        if(e.getDamager() instanceof Player) {
                            LanguageManager.getInstance().sendMessage((Player) e.getDamager(), "RegionChatWarn");
                        } e.setCancelled(true);
                    }
                }
            });
        });

        Events.listen(Main.getInstance(), PlayerInteractAtEntityEvent.class, e -> {
            RegionManager.getInstance().getRegions().forEach((i, region) -> {
                if(region.isInRegion(e.getRightClicked().getLocation()) && region.hasProperty(RegionProperties.PROTECT_TILE_ENTITIES)) {
                    if(e.getPlayer().hasPermission("regions.armorstand-interact")) return;

                    if(e.getRightClicked() instanceof ArmorStand) {
                        LanguageManager.getInstance().sendMessage(e.getPlayer(), "RegionChatWarn");
                        e.setCancelled(true);
                    }
                }
            });
        });
    }
}
