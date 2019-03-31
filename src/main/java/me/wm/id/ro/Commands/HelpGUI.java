package me.wm.id.ro.Commands;

import me.wm.id.ro.util.Language.LanguageManager;
import me.wm.id.ro.util.commands.CommandManager;
import me.wm.id.ro.util.commands.EasyCommand;
import me.wm.id.ro.util.gui.*;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class HelpGUI extends EasyBrowserGUI<EasyCommand> {

    public HelpGUI(Player p) {
        super(p, LanguageManager.getInstance().getStyleFile(p).getGUI(p, "HelpGUI"));

        setObjectsPerPage(27);

        super.refresh();
    }

    @Override
    public ArrayList<EasyCommand> loadObjects() {
        return CommandManager.getInstance().getCommands();
    }

    @Override
    public Button forEach(EasyCommand paramOBJ) {

        HashMap<String, Replacer> pl = new HashMap<>();
        pl.put("{Command}", new Replacer() {
            @Override
            public String run() {
                return paramOBJ.getName();
            }
        });

        return new Button(getItem("Command").setPlaceholders(pl), new Action() {
            @Override
            public void run(Player paramPlayer, ActionType paramActionType) {

            }
        });
    }

    @Override
    public void loadAdditionalButtons() {

    }
}
