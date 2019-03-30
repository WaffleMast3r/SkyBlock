package me.wm.id.ro.util.Language;

import me.wm.id.ro.util.ConfigManager;
import me.wm.id.ro.util.Language.CustomMessage.CustomMessage;
import me.wm.id.ro.util.Language.CustomMessage.CustomMessageContents;
import me.wm.id.ro.util.StyleFileTemplate;
import me.wm.id.ro.util.files.Config;
import me.wm.id.ro.util.gui.CustomFillItem;
import me.wm.id.ro.util.gui.CustomItem;
import me.wm.id.ro.util.gui.GUIContents;
import me.wm.id.ro.util.scoreboard.ScoreBoardContents;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StyleFileConfig {

    private ConfigManager cfg;
    private StyleFileTemplate styleFileTemplate;
    private HashMap<String, GUIContents> guiCache;
    private HashMap<String, CustomMessage> messageCache;
    private HashMap<String, ScoreBoardContents> scoreboardCache;
    private ArrayList<String> colors;
    private String spl;

    public StyleFileConfig(Lang lang) {
        this.styleFileTemplate = new StyleFileTemplate();
        cfg = lang.getConfig();

        sync();

        cfg.reloadConfig();

        guiCache = new HashMap<>();
        messageCache = new HashMap<>();
        scoreboardCache = new HashMap<>();
        spl = Config.getInstance().getConfig().getYml().getString("ColorCharacter");
        colors = new ArrayList<>();
        colors.addAll(cfg.getYml().getStringList("LanguageColors"));
    }

    private void sync() {

        cfg.getYml().options().copyDefaults(true);

        for (String name : styleFileTemplate.getGUIS().keySet()) {
            GUIContents contents = styleFileTemplate.getGUIS().get(name);
            cfg.getYml().addDefault("Inventories." + name + ".Title", contents.title());
            cfg.getYml().addDefault("Inventories." + name + ".Size", contents.size());
            if (styleFileTemplate.getGUIS().get(name).items() != null)
            for (CustomItem item : styleFileTemplate.getGUIS().get(name).items()) {
                String default_path = "Inventories." + name + ".Items." + item.getName() + ".";
                cfg.getYml().addDefault(default_path + "Material", item.getMaterial().toString());
                cfg.getYml().addDefault(default_path + "ID", item.getID());
                cfg.getYml().addDefault(default_path + "Slot", item.getSlot());
                cfg.getYml().addDefault(default_path + "Amount", item.getAmount());
                cfg.getYml().addDefault(default_path + "Title", item.getTitle());
                cfg.getYml().addDefault(default_path + "Lore", item.getLore());
                cfg.getYml().addDefault(default_path + "Enchants", item.getEnchants());
            }
            if (styleFileTemplate.getGUIS().get(name).fillItems() != null)
            for (CustomFillItem item : styleFileTemplate.getGUIS().get(name).fillItems()){
                String default_path = "Inventories." + name + ".FillItems." + item.getName() + ".";
                cfg.getYml().addDefault(default_path + "Material", item.getMaterial().toString());
                cfg.getYml().addDefault(default_path + "ID", item.getID());
                cfg.getYml().addDefault(default_path + "Slots", item.getSlots());
                cfg.getYml().addDefault(default_path + "Amount", item.getAmount());
                cfg.getYml().addDefault(default_path + "Title", item.getTitle());
                cfg.getYml().addDefault(default_path + "Lore", item.getLore());
                cfg.getYml().addDefault(default_path + "Enchants", item.getEnchants());
            }
        }

        for (String name : styleFileTemplate.getMessages().keySet()) {
            CustomMessageContents customMessage = styleFileTemplate.getMessages().get(name);
            if (customMessage.getReplacements() == null) {
                cfg.getYml().addDefault("Messages." + name, customMessage.getText());
            } else {
                cfg.getYml().addDefault("Messages." + name + ".Text", customMessage.getText());
                for (CustomMessage.Replacement r : customMessage.getReplacements()) {
                    String default_path = "Messages." + name + ".Replacements." + r.getReplacement() + ".";
                    cfg.getYml().addDefault(default_path + "Text", r.getText());
                    if (r.getClickEvent() != null) {
                        cfg.getYml().addDefault(default_path + "ClickEvent.Action", r.getClickEvent().getAction());
                        cfg.getYml().addDefault(default_path + "ClickEvent.Value", r.getClickEvent().getValue());
                    }
                    if (r.getHoverEvent() != null) {
                        cfg.getYml().addDefault(default_path + "HoverEvent.Action", r.getHoverEvent().getAction());
                        cfg.getYml().addDefault(default_path + "HoverEvent.Value", r.getHoverEvent().getValue());
                    }
                }
            }
        }

        for (String name : styleFileTemplate.getScoreBoards().keySet()) {
            ScoreBoardContents contents = styleFileTemplate.getScoreBoards().get(name);
            cfg.getYml().addDefault("Scoreboards." + name + ".Title", contents.getTitle());
            cfg.getYml().addDefault("Scoreboards." + name + ".TickRate", contents.getTickRefresh());
            cfg.getYml().addDefault("Scoreboards." + name + ".Lines", contents.getLines());
        }

        cfg.saveConfig();
    }

    public CustomMessage getMessage(Player p, String path) {
        if (messageCache.containsKey(path)) return messageCache.get(path);

        CustomMessage localCustomMessage = new CustomMessage(p, new CustomMessageContents() {
            @Override
            public String getText() {
                if (cfg.getYml().getString("Messages." + path + ".Text") != null) {
                    return cfg.getYml().getString("Messages." + path + ".Text");
                } else {
                    return cfg.getYml().getString("Messages." + path);
                }
            }

            @Override
            public ArrayList<CustomMessage.Replacement> getReplacements() {
                if (cfg.getYml().getString("Messages." + path + ".Text") == null) {
                    return null;
                }
                ArrayList<CustomMessage.Replacement> replacements = new ArrayList<>();

                for (String s : cfg.getYml().getConfigurationSection("Messages." + path + ".Replacements").getKeys(false)) {
                    String default_path = "Messages." + path + ".Replacements." + s + ".";
                    if (cfg.getYml().getString(default_path + "ClickEvent") != null && cfg.getYml().getString(default_path + "HoverEvent") != null) {
                        replacements.add(new CustomMessage.Replacement(s,
                                cfg.getYml().getString(default_path + "Text"),
                                new CustomMessage.EventMessage(
                                        cfg.getYml().getString(default_path + "ClickEvent.Action"),
                                        cfg.getYml().getString(default_path + "ClickEvent.Value")
                                ),
                                new CustomMessage.EventMessage(
                                        cfg.getYml().getString(default_path + "HoverEvent.Action"),
                                        cfg.getYml().getString(default_path + "HoverEvent.Value")
                                )));
                    } else if (cfg.getYml().getString(default_path + "ClickEvent") == null && cfg.getYml().getString(default_path + "HoverEvent") != null) {
                        replacements.add(new CustomMessage.Replacement(s,
                                cfg.getYml().getString(default_path + "Text"),
                                null, new CustomMessage.EventMessage(
                                cfg.getYml().getString(default_path + "HoverEvent.Action"),
                                cfg.getYml().getString(default_path + "HoverEvent.Value")
                        )));
                    } else if (cfg.getYml().getString(default_path + "ClickEvent") != null && cfg.getYml().getString(default_path + "HoverEvent") == null) {
                        replacements.add(new CustomMessage.Replacement(s,
                                cfg.getYml().getString(default_path + "Text"),
                                new CustomMessage.EventMessage(
                                        cfg.getYml().getString(default_path + "ClickEvent.Action"),
                                        cfg.getYml().getString(default_path + "ClickEvent.Value")
                                ), null));
                    } else if (cfg.getYml().getString(default_path + "ClickEvent") == null && cfg.getYml().getString(default_path + "HoverEvent") == null) {
                        replacements.add(new CustomMessage.Replacement(s,
                                cfg.getYml().getString(default_path + "Text"), null, null));
                    }
                }

                return replacements;
            }
        });
        messageCache.put(path, localCustomMessage);
        return localCustomMessage;
    }

    public GUIContents getGUI(Player p, String name) {
        if (guiCache.containsKey(name)) {
            return guiCache.get(name);
        }

        GUIContents localGUIContents = new GUIContents() {
            @Override
            public String title() {
                return cfg.getYml().getString("Inventories." + name + ".Title");
            }

            @Override
            public int size() {
                return cfg.getYml().getInt("Inventories." + name + ".Size");
            }

            @Override
            public CustomItem[] items() {
                List<CustomItem> localArrayList = new ArrayList<>();
                if (cfg.getYml().getConfigurationSection("Inventories." + name + ".Items") == null) return null;
                for (String itemName : cfg.getYml().getConfigurationSection("Inventories." + name + ".Items").getKeys(false)) {
                    ConfigurationSection item = cfg.getYml().getConfigurationSection("Inventories." + name + ".Items." + itemName);
                    CustomItem localCustomItem = new CustomItem(
                            p, Material.getMaterial(item.getString("Material")),
                            item.getInt("ID"),
                            item.getInt("Slot"),
                            item.getInt("Amount"),
                            itemName,
                            item.getString("Title"),
                            item.getStringList("Lore"),
                            item.getStringList("Enchants")
                    );
                    localArrayList.add(localCustomItem);
                }
                return localArrayList.toArray(new CustomItem[0]);
            }

            @Override
            public CustomFillItem[] fillItems() {
                List<CustomFillItem> localArrayList = new ArrayList<>();
                if (cfg.getYml().getConfigurationSection("Inventories." + name + ".FillItems") == null) return null;
                for (String itemName : cfg.getYml().getConfigurationSection("Inventories." + name + ".FillItems").getKeys(false)) {
                    ConfigurationSection item = cfg.getYml().getConfigurationSection("Inventories." + name + ".FillItems." + itemName);
                    CustomFillItem localCustomItem = new CustomFillItem(
                            p, Material.getMaterial(item.getString("Material")),
                            item.getInt("ID"),
                            item.getString("Slots"),
                            item.getInt("Amount"),
                            itemName,
                            item.getString("Title"),
                            item.getStringList("Lore"),
                            item.getStringList("Enchants")
                    );
                    localArrayList.add(localCustomItem);
                }

                return localArrayList.toArray(new CustomFillItem[0]);
            }

        };
        guiCache.put(name, localGUIContents);
        return localGUIContents;
    }

    public ScoreBoardContents getScoreBoard(String name) {
        if (scoreboardCache.containsKey(name)) {
            return scoreboardCache.get(name);
        }

        ScoreBoardContents localScoreBoardContents = new ScoreBoardContents() {
            @Override
            public String getTitle() {
                return cfg.getYml().getString("Scoreboards." + name + ".Title");
            }

            @Override
            public int getTickRefresh() {
                return cfg.getYml().getInt("Scoreboards." + name + ".TickRate");
            }

            @Override
            public ArrayList<String> getLines() {
                return new ArrayList<>(cfg.getYml().getStringList("Scoreboards." + name + ".Lines"));
            }
        };
        scoreboardCache.put(name, localScoreBoardContents);
        return localScoreBoardContents;
    }

    public ComponentBuilder getPrefix() {

        String c = Config.getInstance().getConfig().getYml().getString("ColorCharacter");

        ComponentBuilder cb = new ComponentBuilder(translateColor(cfg.getYml().getString("Prefix").replaceAll("%PluginName%", Main.getInstance().getDescription().getName())));
        cb.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText(
                translateColor("$0+$1-$0+$1-$0+$1- $2<=[ $1Plugin Information $2]=> $1-$0+$1-$0+$1-$0+\n").replace("$", c) +
                        translateColor("\n").replace("$", c) +
                        translateColor(" $2• $1Name: $4" + Main.getInstance().getDescription().getName() + "\n").replace("$", c) +
                        translateColor(" $2• $1Author: $4" + Main.getInstance().getDescription().getAuthors().get(0) + "\n").replace("$", c) +
                        translateColor(" $2• $1Version: $4" + Main.getInstance().getDescription().getVersion() + "\n").replace("$", c) +
                        translateColor(" $2• $1Website: $4" + Main.getInstance().getDescription().getWebsite() + "\n \n").replace("$", c) +
                        translateColor("$0+$1-$0+$1-$0+$1- $2<=[ $1Plugin Information $2]=> $1-$0+$1-$0+$1-$0+").replace("$", c))))
                .event(new ClickEvent(ClickEvent.Action.OPEN_URL, Main.getInstance().getDescription().getWebsite()));

        return cb.append("").reset();
    }

    public String translateColor(String str) {
        for (int i = 0; i < colors.size(); i++) {
            String from = spl + "" + i;
            String to = "ƒ" + colors.get(i);
            str = str.replace(from, to);
        }
        str = str.replaceAll("@err", "ƒc");
        return ChatColor.translateAlternateColorCodes('ƒ', str);
    }

    public String translateJSONColor(String str) {
        StringBuilder finalString = new StringBuilder();
        String prevColor = "";

        String[] chars = str.split("");

        for (int i = 0; i < chars.length; i++) {
            String c = chars[i];

            if (c.equalsIgnoreCase(spl)) {
                int index;
                try {
                    index = Integer.parseInt(chars[i + 1]);
                    if (colors.get(index) != null) {
                        prevColor = c + chars[i + 1];
                        i++;
                    }
                } catch (Exception ignored) {

                }
            } else if (c.equalsIgnoreCase("\n")) {
                finalString.append(c);
                i++;
            } else {
                finalString.append(prevColor).append(c);
            }
        }

        for (int i = 0; i < colors.size(); i++) {
            String from = spl + "" + i;
            String to = "§" + colors.get(i);
            finalString = new StringBuilder(finalString.toString().replace(from, to));
        }

        return finalString.toString();
    }

}
