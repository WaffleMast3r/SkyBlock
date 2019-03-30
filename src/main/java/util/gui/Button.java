package me.wm.hybrid.ro.util.gui;

public class Button {
    private CustomItem item;
    private Action action;

    public Button(CustomItem paramConstructableItem, Action paramAction) {
        this.item = paramConstructableItem;
        this.action = paramAction;
    }

    public CustomItem getItem() {
        return this.item;
    }

    public Action getAction() {
        return this.action;
    }

    public void setAction(Action paramAction) {
        this.action = paramAction;
    }

    public Button clone() {
        return new Button(this.item, this.action);
    }
}
