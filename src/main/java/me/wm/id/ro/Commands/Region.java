package me.wm.id.ro.Commands;

import me.wm.id.ro.Regions.guis.RegionList;
import me.wm.id.ro.util.commands.EasyCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class Region extends EasyCommand {
    public Region() {
        super("region", "region.admin", "open Region GUI", "/region", Arrays.asList("rg"));
        setOnlyPlayer(true);
    }

    @Override
    public void run(CommandSender p, String[] args) {
        new RegionList((Player) p);
    }

    @Override
    public boolean validate(String[] args) {
        if (args.length == 0) {
            return true;

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
