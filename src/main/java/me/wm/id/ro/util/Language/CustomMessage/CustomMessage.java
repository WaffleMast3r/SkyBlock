package me.wm.id.ro.util.Language.CustomMessage;

import me.wm.id.ro.util.Language.LanguageManager;
import me.wm.id.ro.util.Logger;
import net.md_5.bungee.api.chat.*;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class CustomMessage {

    private Player p;
    private CustomMessageContents contents;
    private HashMap<String, String> placeholders;
    private String textAfter;

    public CustomMessage(Player p, CustomMessageContents contents) {
        this.p = p;
        this.contents = contents;
        placeholders = new HashMap<>();
    }

    public CustomMessage setPlaceholders(HashMap<String, String> placeholders) {
        this.placeholders = placeholders;
        return this;
    }

    public BaseComponent[] build() {
        ComponentBuilder cb;

        if (contents.getReplacements() == null) {
            if (contents.getText().startsWith("%prefix%")) {
                cb = LanguageManager.getInstance().getStyleFile(p).getPrefix();
                cb.append(convert(contents.getText().substring(8)));
            } else {
                cb = new ComponentBuilder(convert(contents.getText()));
            }
        } else {

            String text = contents.getText();

            if (text.startsWith("%prefix%")) {
                cb = LanguageManager.getInstance().getStyleFile(p).getPrefix();
                text = text.substring(8);
            } else {
                cb = new ComponentBuilder("");
            }

            ComponentBuilder process;
            for (int i = 0; i < contents.getReplacements().size(); i++) {
                Replacement r = contents.getReplacements().get(i);
                if (!text.contains(r.getReplacement())) {
                    continue;
                }
                process = cb;
                String before = text.substring(0, text.indexOf(r.getReplacement()));
                String after = text.substring(text.indexOf(r.getReplacement()) + r.getReplacement().split("").length);
                text = text.substring(before.split("").length + r.getReplacement().split("").length);

                process.append(convert(before)).reset();

                if (r.getClickEvent() != null && r.getHoverEvent() != null) {
                    process.append(convert(r.getText())).event(
                            new ClickEvent(ClickEvent.Action.valueOf(r.getClickEvent().getAction()), convert(r.getClickEvent().getValue()))
                    ).event(
                            new HoverEvent(HoverEvent.Action.valueOf(r.getHoverEvent().getAction()), TextComponent.fromLegacyText(convert(r.getHoverEvent().getValue())))
                    );
                } else if (r.getClickEvent() != null && r.getHoverEvent() == null) {
                    process.append(convert(r.getText())).event(
                            new ClickEvent(ClickEvent.Action.valueOf(r.getClickEvent().getAction()), convert(r.getClickEvent().getValue()))
                    );
                } else if (r.getClickEvent() == null && r.getHoverEvent() != null) {
                    process.append(convert(r.getText())).event(
                            new HoverEvent(HoverEvent.Action.valueOf(r.getHoverEvent().getAction()), TextComponent.fromLegacyText(convert(r.getHoverEvent().getValue())))
                    );
                } else if (r.getClickEvent() == null && r.getHoverEvent() == null) {
                    process.append(convert(r.getText()));
                }

                if (i == contents.getReplacements().size() - 1) {
                    process.append(convert(after)).reset();
                }

                cb = process;
            }
        }
        return cb.create();
    }

    public String convert(String str) {
        for (String key : placeholders.keySet()) {
            str = str.replace(key, placeholders.get(key));
        }
        return LanguageManager.getInstance().getStyleFile(p).translateJSONColor(str);
    }

    public static class Replacement {

        private String replacement;
        private String text;
        private EventMessage clickEvent;
        private EventMessage hoverEvent;

        public Replacement(String replacement, String text, EventMessage clickEvent, EventMessage hoverEvent) {
            this.replacement = replacement;
            this.text = text;
            this.clickEvent = clickEvent;
            if (clickEvent == null) {
                this.clickEvent = new EventMessage("OPEN_URL", "");
            }
            this.hoverEvent = hoverEvent;
        }

        public String getReplacement() {
            return replacement;
        }

        public String getText() {
            return text;
        }

        public EventMessage getClickEvent() {
            return clickEvent;
        }

        public EventMessage getHoverEvent() {
            return hoverEvent;
        }
    }

    public static class EventMessage {
        private String Action;
        private String Value;

        public EventMessage(String action, String value) {
            Action = action;
            Value = value;
        }

        public String getAction() {
            return Action;
        }

        public String getValue() {
            return Value;
        }
    }

}
