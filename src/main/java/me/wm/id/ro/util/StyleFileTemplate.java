package me.wm.id.ro.util;

import me.wm.id.ro.util.Language.CustomMessage.CustomMessage;
import me.wm.id.ro.util.Language.CustomMessage.CustomMessageContents;
import me.wm.id.ro.util.gui.CustomFillItem;
import me.wm.id.ro.util.gui.CustomItem;
import me.wm.id.ro.util.gui.GUIContents;
import me.wm.id.ro.util.scoreboard.ScoreBoardContents;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

@SuppressWarnings("deprecation")
public class StyleFileTemplate {

    public HashMap<String, ScoreBoardContents> getScoreBoards() {
        HashMap<String, ScoreBoardContents> scoreBoards = new HashMap<>();

        return scoreBoards;
    }

    public HashMap<String, CustomMessageContents> getMessages() {
        HashMap<String, CustomMessageContents> messages = new HashMap<>();

        messages.put("in_progress_type", new CustomMessageContents() {
            @Override
            public String getText() {
                return "%prefix% $0Ne pare rau nu puteti folosi $1{Type} $0momentan$2! $0Este in lucru$2!";
            }

            @Override
            public ArrayList<CustomMessage.Replacement> getReplacements() {
                return null;
            }
        });

        return messages;
    }

    //ca sa scrii un lore faci asa

    public HashMap<String, GUIContents> getGUIS() {
        HashMap<String, GUIContents> guis = new HashMap<>();

        guis.put("LanguageSelectorMenu", new GUIContents() {
            @Override
            public String title() {
                return "Select your Language";
            }

            @Override
            public int size() {
                return 18;
            }

            @Override
            public CustomItem[] items() {
                return new CustomItem[]{
                        new CustomItem(null, Material.LEGACY_BOOK_AND_QUILL, 0, -1, 1, "Language", "Select {LangName}", null, null)
                };
            }

            @Override
            public CustomFillItem[] fillItems() {
                return new CustomFillItem[0];
            }
        });

        return guis;
    }

}
