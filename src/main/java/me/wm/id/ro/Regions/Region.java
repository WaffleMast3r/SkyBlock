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

        return true;
    }

    public boolean hasProperty(RegionProperties... props) {
        return properties.containsAll(Arrays.asList(props));
    }
}
