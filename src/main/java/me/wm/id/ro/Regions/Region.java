package me.wm.id.ro.Regions;

import javafx.util.Pair;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;

public class Region {
    private Pair<Block, Block> positions;
    private ArrayList<RegionProperties> properties;
    private String name;
    private int id;

    public Region(Pair<Block, Block> positions, String name, int id) {
        this.positions = positions;
        this.name = name;
        this.id = id;

        properties = new ArrayList<>();
    }

    public boolean isInRegion(Player p){
        final int px = (int) p.getLocation().getX();
        final int py = (int) p.getLocation().getY();
        final int pz = (int) p.getLocation().getZ();

        final int b1x = Math.min(positions.getKey().getX(), positions.getValue().getX());
        final int b1y = Math.min(positions.getKey().getY(), positions.getValue().getY());
        final int b1z = Math.min(positions.getKey().getZ(), positions.getValue().getZ());

        final int b2x = Math.max(positions.getKey().getX(), positions.getValue().getX());
        final int b2y = Math.max(positions.getKey().getY(), positions.getValue().getY());
        final int b2z = Math.max(positions.getKey().getZ(), positions.getValue().getZ());

        return ((px >= b1x && px <= b2x) && (py >= b1y && py <= b2y) && (pz >= b1z && pz <= b2z));
    }

    public boolean hasProperty(RegionProperties... props) {
        return properties.containsAll(Arrays.asList(props));
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
