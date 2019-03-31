package me.wm.id.ro.util.commands;

import me.wm.id.ro.Commands.gmc;
import me.wm.id.ro.Main;
import org.bukkit.event.Listener;

import java.util.ArrayList;

public class CommandManager implements Listener {

    private static CommandManager instance = new CommandManager();

    private ArrayList<EasyCommand> commands;

    private CommandManager() {
        Main.getInstance().getServer().getPluginManager().registerEvents(this, Main.getInstance());

        commands = new ArrayList<>();
    }

    public void loadCommands(){
        new helpCommand();
        new gmc();
    }

    public static CommandManager getInstance() {
        return instance;
    }

    public void registerCommand(EasyCommand command) {
        commands.add(command);
    }

    public ArrayList<EasyCommand> getCommands() {
        return commands;
    }
}
