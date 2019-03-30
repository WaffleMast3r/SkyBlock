package me.wm.id.ro.util.Language;

import me.wm.id.ro.util.gui.*;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class LanguageSelectorMenu extends EasyBrowserGUI<Lang> {

    public LanguageSelectorMenu(Player p) {
        super(p, LanguageManager.getInstance().getStyleFile(p).getGUI(p, "LanguageSelectorMenu"));
        setObjectsPerPage(9);

        super.refresh();
    }

    @Override
    public ArrayList<Lang> loadObjects() {
        return LanguageManager.getInstance().getLanguages();
    }

    @Override
    public Button forEach(Lang paramOBJ) {

        HashMap<String, Replacer> placeholders = new HashMap<>();
        placeholders.put("{LangName}", new Replacer() {
            @Override
            public String run() {
                return paramOBJ.getName();
            }
        });

        return new Button(getItem("Language").setPlaceholders(placeholders), new Action() {
            @Override
            public void run(Player paramPlayer, ActionType paramActionType) {
                LanguageManager.getInstance().setLangForPlayer(paramOBJ, paramPlayer);
                closeInventory();
            }
        });
    }

    @Override
    public void loadAdditionalButtons() {

    }
}
