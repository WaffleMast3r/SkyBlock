package me.wm.id.ro.util.commands;

import me.wm.id.ro.Main;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

import java.util.List;

public abstract class EasyCommand extends BukkitCommand {

    private String command;
    private String description;
    private String usage;
    private List<String> aliasses;
    private boolean onlyPlayer;

    public EasyCommand(String command, String description, String usage, List<String> aliasses) {
        super(command, description, usage, aliasses);
        this.command = command;
        this.description = description;
        this.usage = usage;
        this.aliasses = aliasses;

        onlyPlayer = false;

        Main.getNms().registerCommand(command, this);
        CommandManager.getInstance().registerCommand(this);
    }

    public abstract void run(CommandSender p, String[] args);
    public abstract boolean validate(String[] args);
    public abstract void incorrectUsage(CommandSender p);
    public abstract void noPermission(CommandSender p);

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if (onlyPlayer) {
            if (sender instanceof Player) {
                if (validate(args)) {
                    run(sender, args);
                } else {
                    incorrectUsage(sender);
                    return true;
                }
            } else {
                noPermission(sender);
            }
        } else {
            if (validate(args)) {
                run(sender, args);
            } else {
                incorrectUsage(sender);
            }
        }

        return true;
    }

    public void setOnlyPlayer(boolean value) {
        this.onlyPlayer = value;
    }

}
