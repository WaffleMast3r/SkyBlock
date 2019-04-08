package me.wm.id.ro.Regions;

import javafx.util.Pair;
import me.wm.id.ro.Main;
import me.wm.id.ro.util.Location;
import me.wm.id.ro.util.Updater.UpdateEvent;
import me.wm.id.ro.util.Updater.UpdateTime;
import me.wm.id.ro.util.WaffClass;
import me.wm.id.ro.util.gui.CustomItem;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.Arrays;

public class Region implements Listener {
    private Pair<Block, Block> positions;
    private ArrayList<RegionProperties> properties;
    private String name;
    private int id;
    private boolean visualize = false;

    private String enterMessage = "&bTest message";

    public Region(Pair<Block, Block> positions, String name, int id) {
        this.positions = positions;
        this.name = name;
        this.id = id;

        Main.getInstance().getServer().getPluginManager().registerEvents(this, Main.getInstance());

        properties = new ArrayList<>();
    }

    public boolean isInRegion(Player p) {
        double px = p.getLocation().getX();
        double py = p.getLocation().getY();
        double pz = p.getLocation().getZ();

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

    public Block getFirstPosition() {
        return positions.getKey();
    }

    public Block getSecondPosition() {
        return positions.getValue();
    }

    public void visualize() {
        visualize = !visualize;
    }

    @EventHandler
    public void onUpdate(UpdateEvent event) {
        if (event.getUpdateTime().equals(UpdateTime.SECOND)) {
            if (visualize) {
                Pair<Location, Location> locs = WaffClass.Loc.getInstance().getMinAndMaxLocations(positions.getKey().getLocation(), positions.getValue().getLocation());
                Location loc1 = locs.getKey();
                Location loc2 = locs.getValue();

                Location loc;
                for (double x = loc1.getX(); x <= loc2.getX(); x++) {
                    loc = new Location(loc1.clone());
                    loc.setX(x);
                    WaffClass.Particles.displayRedstoneParticle(loc, 100, 20, 100);

                    loc = new Location(loc1.clone());
                    loc.setX(x);
                    loc.setY(loc2.getY());
                    WaffClass.Particles.displayRedstoneParticle(loc, 100, 20, 100);

                    loc = new Location(loc1.clone());
                    loc.setX(x);
                    loc.setZ(loc2.getZ());
                    WaffClass.Particles.displayRedstoneParticle(loc, 100, 20, 100);

                    loc = new Location(loc1.clone());
                    loc.setX(x);
                    loc.setY(loc2.getY());
                    loc.setZ(loc2.getZ());
                    WaffClass.Particles.displayRedstoneParticle(loc, 100, 20, 100);
                }

                for (double y = loc1.getY(); y <= loc2.getY(); y++) {
                    loc = new Location(loc1.clone());
                    loc.setY(y);
                    WaffClass.Particles.displayRedstoneParticle(loc, 100, 20, 100);

                    loc = new Location(loc1.clone());
                    loc.setY(y);
                    loc.setX(loc2.getX());
                    WaffClass.Particles.displayRedstoneParticle(loc, 100, 20, 100);

                    loc = new Location(loc1.clone());
                    loc.setY(y);
                    loc.setZ(loc2.getZ());
                    WaffClass.Particles.displayRedstoneParticle(loc, 100, 20, 100);

                    loc = new Location(loc1.clone());
                    loc.setX(loc2.getX());
                    loc.setY(y);
                    loc.setZ(loc2.getZ());
                    WaffClass.Particles.displayRedstoneParticle(loc, 100, 20, 100);
                }

                for (double z = loc1.getZ(); z <= loc2.getZ(); z++) {
                    loc = new Location(loc1.clone());
                    loc.setZ(z);
                    WaffClass.Particles.displayRedstoneParticle(loc, 100, 20, 100);

                    loc = new Location(loc1.clone());
                    loc.setZ(z);
                    loc.setX(loc2.getX());
                    WaffClass.Particles.displayRedstoneParticle(loc, 100, 20, 100);

                    loc = new Location(loc1.clone());
                    loc.setZ(z);
                    loc.setY(loc2.getY());
                    WaffClass.Particles.displayRedstoneParticle(loc, 100, 20, 100);

                    loc = new Location(loc1.clone());
                    loc.setX(loc2.getX());
                    loc.setY(loc2.getY());
                    loc.setZ(z);
                    WaffClass.Particles.displayRedstoneParticle(loc, 100, 20, 100);
                }
            }
        }
    }

    public String getPropertyMessageFor(RegionProperties prop) {
        final String name = prop.getPropName();

        // TODO Add more prop name checks here if property has a message attribute
        if(!name.equals("Enter Message")) return null;

        if(enterMessage != null) {
            return enterMessage;
        }

        return null;
    }

    public void appendProperty(RegionProperties property) {
        appendProperty(property, null);
    }

    public void appendProperty(RegionProperties property, Object additionalData) {
        if(!properties.contains(property)) properties.add(property);

        if(additionalData instanceof String) {
            if(property.getPropName().equals("Enter Message")) {
                enterMessage = (String) additionalData;
            }
        }
    }

}
