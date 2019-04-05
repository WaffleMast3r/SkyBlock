package me.wm.id.ro.Commands;

import javafx.util.Pair;
import me.wm.id.ro.Regions.RegionInterogation;
import me.wm.id.ro.Regions.RegionManager;
import me.wm.id.ro.util.commands.EasyCommand;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class Region extends EasyCommand {
    public Region() {
        super("region", "region.admin", "region Command", "/region arg1 arg2", Arrays.asList("rg"));
        setOnlyPlayer(true);
    }

    @Override
    public void run(CommandSender p, String[] args) {
        if (args[0].equalsIgnoreCase("create")) {
            new RegionInterogation((Player) p, new RegionInterogation.Action() {
                @Override
                public void run(Block pos1, Block pos2) {
                    RegionManager.getInstance().createRegion(new Pair<>(pos1, pos2), args[1]);
                }
            });
        }
    }

    @Override
    public boolean validate(String[] args) {
        if (args.length == 2) {
            if (args[0].equalsIgnoreCase("create")) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void incorrectUsage(CommandSender p) {
        Bukkit.broadcastMessage("Incorrect usage");
    }

    @Override
    public void noPermission(CommandSender p) {
        Bukkit.broadcastMessage("No Permission!");
    }
}
