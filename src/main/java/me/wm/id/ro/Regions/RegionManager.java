package me.wm.id.ro.Regions;

import javafx.util.Pair;
import me.wm.id.ro.Regions.listeners.noPvP;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Objects;

public class RegionManager {
    public static RegionManager instance = new RegionManager();
    private static HashMap<Integer, Region> regions;

    private RegionManager() {

    }

    public static RegionManager getInstance() {
        return instance;
    }

    public static void Enable() {
        RegionData.getConfig().load();
        regions = new HashMap<>();
        // load from config
        if (RegionData.getConfig().getYml().get("regions") != null)
            for (String key : RegionData.getConfig().getYml().getConfigurationSection("regions").getKeys(false)) {
                final int id = Integer.parseInt(key);

                regions.put(id,
                        new Region(new Pair<>(
                                Bukkit.getWorld(Objects.requireNonNull(RegionData.getConfig().getYml().getString("regions." + key + ".positions.1.world"))).getBlockAt(
                                        new Location(
                                                Bukkit.getWorld(Objects.requireNonNull(RegionData.getConfig().getYml().getString("regions." + key + ".positions.1.world"))),
                                                RegionData.getConfig().getYml().getInt("regions." + key + ".positions.1.x"),
                                                RegionData.getConfig().getYml().getInt("regions." + key + ".positions.1.y"),
                                                RegionData.getConfig().getYml().getInt("regions." + key + ".positions.1.z")
                                        )
                                ),
                                Bukkit.getWorld(Objects.requireNonNull(RegionData.getConfig().getYml().getString("regions." + key + ".positions.2.world"))).getBlockAt(
                                        new Location(
                                                Bukkit.getWorld(Objects.requireNonNull(RegionData.getConfig().getYml().getString("regions." + key + ".positions.2.world"))),
                                                RegionData.getConfig().getYml().getInt("regions." + key + ".positions.2.x"),
                                                RegionData.getConfig().getYml().getInt("regions." + key + ".positions.2.y"),
                                                RegionData.getConfig().getYml().getInt("regions." + key + ".positions.2.z")
                                        )
                                )
                        ), RegionData.getConfig().getYml().getString("regions." + id + ".name"), id));
            }

        //activate Listeners
        new noPvP();

    }

    public void createRegion(Pair<Block, Block> positions, String name) {
        int id = regions.size();
        regions.put(id, new Region(positions, name, id));

        RegionData.getConfig().getYml().set("regions." + id + ".name", name);
        RegionData.getConfig().getYml().set("regions." + id + ".positions.1.world", positions.getKey().getWorld().getName());
        RegionData.getConfig().getYml().set("regions." + id + ".positions.2.world", positions.getValue().getWorld().getName());

        RegionData.getConfig().getYml().set("regions." + id + ".positions.1.x", positions.getKey().getX());
        RegionData.getConfig().getYml().set("regions." + id + ".positions.1.y", positions.getKey().getY());
        RegionData.getConfig().getYml().set("regions." + id + ".positions.1.z", positions.getKey().getZ());

        RegionData.getConfig().getYml().set("regions." + id + ".positions.2.x", positions.getValue().getX());
        RegionData.getConfig().getYml().set("regions." + id + ".positions.2.y", positions.getValue().getY());
        RegionData.getConfig().getYml().set("regions." + id + ".positions.2.z", positions.getValue().getZ());

        RegionData.getConfig().saveConfig();
    }

    public Region isPlayerInRegion(Player p) {
        for (Region r : regions.values()){
            if (r.isInRegion(p)) return r;
        }
        return null;
    }
}
