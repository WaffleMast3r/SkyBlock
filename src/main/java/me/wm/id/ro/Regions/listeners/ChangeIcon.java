package me.wm.id.ro.Regions.listeners;

import me.wm.id.ro.util.Language.LanguageManager;
import me.wm.id.ro.util.gui.Button;
import me.wm.id.ro.util.gui.EasyBrowserGUI;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;

public class ChangeIcon extends EasyBrowserGUI<Material> {

    public ChangeIcon(Player p) {
        super(p, LanguageManager.getInstance().getStyleFile(p).getGUI(p, "ChangeIcon"));
        setObjectsPerPage(9 * 3);
    }

    @Override
    public ArrayList<Material> loadObjects() {
        return new ArrayList<>(Arrays.asList(Material.values()));
    }

    @Override
    public Button forEach(Material paramOBJ) {
        return null;
    }

    @Override
    public void loadAdditionalButtons() {

    }
}
