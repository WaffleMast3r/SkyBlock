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

        messages.put("RegionChatConfirmMessage", new CustomMessageContents() {
            @Override
            public String getText() {
                return "%prefix% $0Apasa pe $4{0} $0pentru a creea regiunea, $1{1} $0pentru a selecta din nou si $2{2} $0pentru a anula actiunea";
            }

            @Override
            public ArrayList<CustomMessage.Replacement> getReplacements() {
                ArrayList<CustomMessage.Replacement> replacements = new ArrayList<>();
                replacements.add(new CustomMessage.Replacement("{0}", "CONFIRM", new CustomMessage.EventMessage("RUN_COMMAND", "CONFIRM"), new CustomMessage.EventMessage("SHOW_TEXT", "Click Here to confirm")));
                replacements.add(new CustomMessage.Replacement("{1}", "RESET", new CustomMessage.EventMessage("RUN_COMMAND", "RESET"), new CustomMessage.EventMessage("SHOW_TEXT", "Click Here to reset")));
                replacements.add(new CustomMessage.Replacement("{2}", "CANCEL", new CustomMessage.EventMessage("RUN_COMMAND", "CANCEL"), new CustomMessage.EventMessage("SHOW_TEXT", "Click Here to cancel")));

                return replacements;
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

        guis.put("HelpGUI", new GUIContents() {
            @Override
            public String title() {
                return "List of commands";
            }

            @Override
            public int size() {
                return 54;
            }

            @Override
            public CustomItem[] items() {
                return new CustomItem[]{
                        new CustomItem(null, Material.NAME_TAG, 0, -1, 1, "Command", "$2« $1/$0{Command} $2»",null, null)
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
