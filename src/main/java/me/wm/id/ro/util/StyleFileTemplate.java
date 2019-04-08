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
                return "%prefix% $3Ambele pozitii au fost selectate! Alege una din optiunile urmatoare pentru a incheia: $8{0} $5{1} $9{2}";
            }

            @Override
            public ArrayList<CustomMessage.Replacement> getReplacements() {
                ArrayList<CustomMessage.Replacement> replacements = new ArrayList<>();
                replacements.add(new CustomMessage.Replacement("{0}", "[CONFIRM]", new CustomMessage.EventMessage("RUN_COMMAND", "CONFIRM"), new CustomMessage.EventMessage("SHOW_TEXT", "Click pentru a confirma")));
                replacements.add(new CustomMessage.Replacement("{1}", "[RESET]", new CustomMessage.EventMessage("RUN_COMMAND", "RESET"), new CustomMessage.EventMessage("SHOW_TEXT", "Click pentru a o lua de la capat")));
                replacements.add(new CustomMessage.Replacement("{2}", "[CANCEL]", new CustomMessage.EventMessage("RUN_COMMAND", "CANCEL"), new CustomMessage.EventMessage("SHOW_TEXT", "Click pentru a anula")));

                return replacements;
            }
        });

        messages.put("RegionChatConfirmedMessage", new CustomMessageContents() {
            @Override
            public String getText() {
                return "%prefix% $8Regiunea $10{RegionName}$8 a fost creata cu success";
            }

            @Override
            public ArrayList<CustomMessage.Replacement> getReplacements() {
                return null;
            }
        });

        messages.put("RegionChatCancelMessage", new CustomMessageContents() {
            @Override
            public String getText() {
                return "%prefix% $8Ai anulat crearea regiunii";
            }

            @Override
            public ArrayList<CustomMessage.Replacement> getReplacements() {
                return null;
            }
        });

        messages.put("RegionChatFirstMessage", new CustomMessageContents() {
            @Override
            public String getText() {
                return "%prefix% $3Selecteaza prima pozitie a regiunii folosind click dreapta pe un block";
            }

            @Override
            public ArrayList<CustomMessage.Replacement> getReplacements() {
                return null;
            }
        });

        messages.put("RegionChatSecondMessage", new CustomMessageContents() {
            @Override
            public String getText() {
                return "%prefix% $3Selecteaza a doua pozitie a regiunii folosind click dreapta pe un block";
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

        guis.put("RegionList", new GUIContents() {
            @Override
            public String title() {
                return "Region main menu";
            }

            @Override
            public int size() {
                return 9 * 5;
            }

            @Override
            public CustomItem[] items() {
                return new CustomItem[] {
                        new CustomItem(null, Material.SMOOTH_STONE, 0, -1, 1, "region", "$1{ID} - {Name}", null, null),
                        new CustomItem(null, Material.ANVIL, 0, 40, 1, "create", "$1Create region", null, null)
                };
            }

            @Override
            public CustomFillItem[] fillItems() {
                return new CustomFillItem[0];
            }
        });

        guis.put("RegionOptions", new GUIContents() {
            @Override
            public String title() {
                return "Managing region";
            }

            @Override
            public int size() {
                return 9;
            }

            @Override
            public CustomItem[] items() {
                ArrayList<String> infoLore = new ArrayList<>();
                infoLore.add("$3ID: $4{ID}");
                infoLore.add("$3Name: $4{RegionName}");
                infoLore.add("$3Positions: $4{Pos1} $3/ $4{Pos2}");

                return new CustomItem[] {
                        new CustomItem(null, Material.SMOOTH_STONE, 0, 0, 1, "change_icon", "$1Change region icon", null, null),
                        new CustomItem(null, Material.PAPER, 0, 2, 1, "properties", "$1Region properties", null, null),
                        new CustomItem(null, Material.BOOK, 0, 4, 1, "info", "$1Region information", infoLore, null),
                        new CustomItem(null, Material.BARRIER, 0, 6, 1, "delete", "$9Delete this region", null, null),
                        new CustomItem(null, Material.GLASS, 0, 8, 1, "visualize", "$1Visualize region", null, null)
                };
            }

            @Override
            public CustomFillItem[] fillItems() {
                return new CustomFillItem[0];
            }
        });

        guis.put("RegionPropertiesMenu", new GUIContents() {
            @Override
            public String title() {
                return "Select what properties";
            }

            @Override
            public int size() {
                return 9 * 5;
            }

            @Override
            public CustomItem[] items() {
                return new CustomItem[]{
                        new CustomItem(null, Material.PAPER, 0, -1, 1, "Property", "{Name} - {Status}", null, null)
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
