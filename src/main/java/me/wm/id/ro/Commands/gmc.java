package me.wm.id.ro.Commands;

import me.wm.id.ro.util.commands.EasyCommand;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;

public class gmc extends EasyCommand {

    public gmc() {
        super("gmc", "gamemode.creative","Gives you creative mode", "/gmc", Collections.emptyList());
        setOnlyPlayer(true);
    }

    @Override
    public void run(CommandSender p, String[] args) {
        ((Player)p).setGameMode(GameMode.CREATIVE);
    }

    @Override
    public boolean validate(String[] args) {
        return args.length == 0;
    }

    @Override
    public void incorrectUsage(CommandSender p) {

    }

    @Override
    public void noPermission(CommandSender p) {

    }
}
