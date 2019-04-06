package me.wm.id.ro.Regions;

import javafx.util.Pair;
import me.wm.id.ro.util.Location;
import me.wm.id.ro.util.WaffClass;
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

    public boolean isInRegion(Player p) {
        int px = (int) p.getLocation().getX();
        int py = (int) p.getLocation().getY();
        int pz = (int) p.getLocation().getZ();

        int b1x = Math.min(positions.getKey().getX(), positions.getValue().getX());
        int b1y = Math.min(positions.getKey().getY(), positions.getValue().getY());
        int b1z = Math.min(positions.getKey().getZ(), positions.getValue().getZ());

        int b2x = Math.max(positions.getKey().getX(), positions.getValue().getX());
        int b2y = Math.max(positions.getKey().getY(), positions.getValue().getY());
        int b2z = Math.max(positions.getKey().getZ(), positions.getValue().getZ());

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

    public void visualize() {
        Pair<Location, Location> locs = WaffClass.Loc.getInstance().getMinAndMaxLocations(positions.getKey().getLocation(), positions.getValue().getLocation());
        Location loc1 = locs.getKey();
        Location loc2 = locs.getValue();

        Location loc;
        for (double x = loc1.getX(); x <= loc2.getX(); x++) {
            loc.add(Math.abs(loc2.getX() - x), 0, 0);
            WaffClass.Particles.displayRedstoneParticle(loc, 100, 20, 100);
            loc.subtract(Math.abs(loc2.getX() - x), 0, 0);

            loc.add(Math.abs(loc2.getX() -x), Math.abs(loc2.getY() - loc1.getY()),0);
            WaffClass.Particles.displayRedstoneParticle(loc, 100,20,100);
            loc.subtract(Math.abs(loc2.getX() -x), Math.abs(loc2.getY() - loc1.getY()),0);
        }
    }

}
