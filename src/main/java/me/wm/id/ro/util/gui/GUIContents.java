package me.wm.id.ro.util.gui;

public abstract class GUIContents {

    public abstract String title();

    public abstract int size();

    public abstract CustomItem[] items();
    public abstract CustomFillItem[] fillItems();

    public CustomItem getItemInSlot(int slot) {

        for (CustomItem item : items()) {
            if (item.getSlot() == slot) {
                return item;
            }
        }

        return null;
    }

}
