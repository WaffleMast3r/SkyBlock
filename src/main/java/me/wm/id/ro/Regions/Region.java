package me.wm.id.ro.Regions;

import javafx.util.Pair;
import org.bukkit.block.Block;

import java.util.ArrayList;

public class Region {
    private Pair<Block, Block> positions;
    private ArrayList<String> properties;
    private String name;
    private int id;

    public Region(Pair<Block, Block> positions, String name, int id) {
        this.positions = positions;
        this.name = name;
        this.id = id;
    }
}
