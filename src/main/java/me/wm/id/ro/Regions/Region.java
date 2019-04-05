package me.wm.id.ro.Regions;

import javafx.util.Pair;
import me.wm.id.ro.util.WaffClass;
import org.bukkit.Location;
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

    public void visualize() {
        final int b1x = Math.min(positions.getKey().getX(), positions.getValue().getX());
        final int b1y = Math.min(positions.getKey().getY(), positions.getValue().getY());
        final int b1z = Math.min(positions.getKey().getZ(), positions.getValue().getZ());

        final int b2x = Math.max(positions.getKey().getX(), positions.getValue().getX());
        final int b2y = Math.max(positions.getKey().getY(), positions.getValue().getY());
        final int b2z = Math.max(positions.getKey().getZ(), positions.getValue().getZ());

        for (int x = b1x; x <= b2x; x++) {
            Location loc = positions.getKey().getLocation().clone();

            loc.add(Math.abs(loc.getX() - x), 0, 0);
            WaffClass.Particles.displayRedstoneParticle(loc, 100, 255, 100);
            loc.subtract(Math.abs(loc.getX() - x), 0, 0);

            loc.add(Math.abs(loc.getX() - x), Math.abs(loc.getY() - positions.getValue().getY()), 0);
            WaffClass.Particles.displayRedstoneParticle(loc, 100, 255, 100);
            loc.subtract(Math.abs(loc.getX() - x), Math.abs(loc.getY() - positions.getValue().getY()), 0);

            loc.subtract(Math.abs(loc.getX() - x), 0, Math.abs(loc.getZ() - positions.getValue().getZ()));
            WaffClass.Particles.displayRedstoneParticle(loc, 100, 255, 100);
            loc.add(Math.abs(loc.getX() - x), 0, Math.abs(loc.getZ() - positions.getValue().getZ()));

            loc.subtract(Math.abs(loc.getX() - x), Math.abs(loc.getY() - positions.getValue().getY()), Math.abs(loc.getZ() - positions.getValue().getZ()));
            WaffClass.Particles.displayRedstoneParticle(loc, 100, 255, 100);
            loc.subtract(Math.abs(loc.getX() - x), Math.abs(loc.getY() - positions.getValue().getY()), Math.abs(loc.getZ() - positions.getValue().getZ()));
        }
//        for (int x = Math.min(positions.getKey().getX(), positions.getValue().getX()); x <= Math.max(positions.getKey().getX(), positions.getValue().getX()); x++) {
//
//            Location loc = positions.getKey().getLocation().clone();
//
//            loc.add(Math.abs(loc.getX() - x), 0, 0);
//            WaffClass.Particles.displayRedstoneParticle(loc, 100, 255, 100);
//            loc.subtract(Math.abs(loc.getX() - x), 0, 0);
//
//            loc.add(Math.abs(loc.getX() - x), Math.abs(loc.getY() - positions.getValue().getY()), 0);
//            WaffClass.Particles.displayRedstoneParticle(loc, 100, 255, 100);
//            loc.subtract(Math.abs(loc.getX() - x), Math.abs(loc.getY() - positions.getValue().getY()), 0);
//
//            loc.add(Math.abs(loc.getX() - x), 0, Math.abs(loc.getZ() - positions.getValue().getZ()));
//            WaffClass.Particles.displayRedstoneParticle(loc, 100, 255, 100);
//            loc.subtract(Math.abs(loc.getX() - x), 0, Math.abs(loc.getZ() - positions.getValue().getZ()));

//            Location newLoc = positions.getKey().getLocation().clone();
//            newLoc.setX(x);
//            WaffClass.Particles.displayRedstoneParticle(newLoc, 100, 255, 100);
//            Location newLoc2 = n
//            newLoc.setY(positions.getValue().getY());
//            WaffClass.Particles.displayRedstoneParticle(newLoc, 100, 255, 100);
//            newLoc.setZ(positions.getValue().getZ());
//            WaffClass.Particles.displayRedstoneParticle(newLoc, 100, 255, 100);

//        }

//        for (int y = Math.min(positions.getKey().getY(), positions.getValue().getY()); y <= Math.max(positions.getKey().getY(), positions.getValue().getY()); y++) {
//            Location newLoc = positions.getKey().getLocation().clone();
//            newLoc.setY(y);
//            WaffClass.Particles.displayRedstoneParticle(newLoc, 100, 255, 100);
//        }
//
//        for (int z = Math.min(positions.getKey().getZ(), positions.getValue().getZ()); z <= Math.max(positions.getKey().getZ(), positions.getValue().getZ()); z++) {
//            Location newLoc = positions.getKey().getLocation().clone();
//            newLoc.setZ(z);
//            WaffClass.Particles.displayRedstoneParticle(newLoc, 100, 255, 100);
//        }
//
//        for (int x = Math.max(positions.getKey().getX(), positions.getValue().getX()); x >= Math.min(positions.getKey().getX(), positions.getValue().getX()); x--) {
//            Location newLoc = positions.getValue().getLocation().clone();
//            newLoc.setX(x);
//            WaffClass.Particles.displayRedstoneParticle(newLoc, 100, 255, 100);
//        }
//
//        for (int y = Math.max(positions.getKey().getY(), positions.getValue().getY()); y >= Math.min(positions.getKey().getY(), positions.getValue().getY()); y--) {
//            Location newLoc = positions.getValue().getLocation().clone();
//            newLoc.setY(y);
//            WaffClass.Particles.displayRedstoneParticle(newLoc, 100, 255, 100);
//        }
//
//        for (int z = Math.max(positions.getKey().getZ(), positions.getValue().getZ()); z >= Math.min(positions.getKey().getZ(), positions.getValue().getZ()); z--) {
//            Location newLoc = positions.getValue().getLocation().clone();
//            newLoc.setZ(z);
//            WaffClass.Particles.displayRedstoneParticle(newLoc, 100, 255, 100);
//        }

        WaffClass.Particles.displayRedstoneParticle(positions.getKey().getLocation(), 255, 100, 100);
        WaffClass.Particles.displayRedstoneParticle(positions.getValue().getLocation(), 255, 100, 100);

    }

}
