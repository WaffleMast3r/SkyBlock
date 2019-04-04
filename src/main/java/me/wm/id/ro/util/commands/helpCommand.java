package me.wm.id.ro.util.commands;

import me.wm.id.ro.Commands.HelpGUI;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;

public class helpCommand extends EasyCommand {

    public helpCommand() {
        super("help", "","type /help to get some help", "/<command>", Collections.emptyList());
        setOnlyPlayer(true);
    }

    @Override
    public void run(CommandSender p, String[] args) {
        new HelpGUI((Player) p);
    }

    @Override
    public boolean validate(String[] args) {
        return true;
    }

    @Override
    public void incorrectUsage(CommandSender p) {
        Bukkit.getServer().broadcastMessage("Incorrect Usage!");
    }

    @Override
    public void noPermission(CommandSender p) {
        Bukkit.getServer().broadcastMessage("No Permission");
    }


}
