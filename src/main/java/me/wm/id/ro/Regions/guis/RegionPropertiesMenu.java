package me.wm.id.ro.Regions.guis;

import me.wm.id.ro.Regions.Region;
import me.wm.id.ro.Regions.RegionProperties;
import me.wm.id.ro.util.Language.LanguageManager;
import me.wm.id.ro.util.gui.*;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class RegionPropertiesMenu extends EasyBrowserGUI<RegionProperties> {

    private Region region;

    public RegionPropertiesMenu(Player p, Region region) {
        super(p, LanguageManager.getInstance().getStyleFile(p).getGUI(p, "RegionPropertiesMenu"));
        this.region = region;

        setObjectsPerPage(27);

        super.refresh();
    }

    @Override
    public ArrayList<RegionProperties> loadObjects() {
        return new ArrayList<>(Arrays.asList(RegionProperties.values()));
    }

    @Override
    public Button forEach(RegionProperties paramOBJ) {
        HashMap<String, Replacer> ph = new HashMap<>();

        ph.put("{Name}", new Replacer() {
            @Override
            public String run() {
                return paramOBJ.getPropName();
            }
        });

        ph.put("{Status}", new Replacer() {
            @Override
            public String run() {
                return region.hasProperty(paramOBJ) ? "$8Enabled" : "$9Disabled";
            }
        });

        return new Button(getItem("Property").setPlaceholders(ph).setMaterial(paramOBJ.getMaterial()).setAmount(paramOBJ.getAmount()).setID(paramOBJ.getData()), new Action() {
            @Override
            public void run(Player paramPlayer, ActionType paramActionType) {

            }
        });
    }

    @Override
    public void loadAdditionalButtons() {

    }
}
